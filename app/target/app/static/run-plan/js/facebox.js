/*
 * Facebox (for jQuery)
 * version: 1.3
 * @requires jQuery v1.2 or later
 * @homepage https://github.com/defunkt/facebox
 *
 * Licensed under the MIT:
 *   http://www.opensource.org/licenses/mit-license.php
 *
 * Copyright Forever Chris Wanstrath, Kyle Neath
 *
 * Usage:
 *
 *  jQuery(document).ready(function() {
 *    jQuery('a[rel*=facebox]').facebox()
 *  })
 *
 *  <a href="#terms" rel="facebox">Terms</a>
 *    Loads the #terms div in the box
 *
 *  <a href="terms.html" rel="facebox">Terms</a>
 *    Loads the terms.html page in the box
 *
 *  <a href="terms.png" rel="facebox">Terms</a>
 *    Loads the terms.png image in the box
 *
 *
 *  You can also use it programmatically:
 *
 *    jQuery.facebox('some html')
 *    jQuery.facebox('some html', 'my-groovy-style')
 *
 *  The above will open a facebox with "some html" as the content.
 *
 *    jQuery.facebox(function($) {
 *      $.get('blah.html', function(data) { $.facebox(data) })
 *    })
 *
 *  The above will show a loading screen before the passed function is called,
 *  allowing for a better ajaxy experience.
 *
 *  The facebox function can also display an ajax page, an image, or the contents of a div:
 *
 *    jQuery.facebox({ ajax: 'remote.html' })
 *    jQuery.facebox({ ajax: 'remote.html' }, 'my-groovy-style')
 *    jQuery.facebox({ image: 'stairs.jpg' })
 *    jQuery.facebox({ image: 'stairs.jpg' }, 'my-groovy-style')
 *    jQuery.facebox({ div: '#box' })
 *    jQuery.facebox({ div: '#box' }, 'my-groovy-style')
 *
 *  Want to close the facebox?  Trigger the 'close.facebox' document event:
 *
 *    jQuery(document).trigger('close.facebox')
 *
 *  Facebox also has a bunch of other hooks:
 *
 *    loading.facebox
 *    beforeReveal.facebox
 *    reveal.facebox (aliased as 'afterReveal.facebox')
 *    init.facebox
 *    afterClose.facebox
 *
 *  Simply bind a function to any of these hooks:
 *
 *   $(document).bind('reveal.facebox', function() { ...stuff to do after the facebox and contents are revealed... })
 *
 */
(function($) {
  $.facebox = function(data, klass) {
    $.facebox.loading()

    if (data.ajax) fillFaceboxFromAjax(data.ajax, klass)
    else if (data.image) fillFaceboxFromImage(data.image, klass)
    else if (data.div) fillFaceboxFromHref(data.div, klass, $(this))
    else if ($.isFunction(data)) data.call($)
    else $.facebox.reveal(data, klass)
  }

  /*
   * Public, $.facebox methods
   */

  $.extend($.facebox, {
    settings: {
      opacity      : 0.2,
      overlay      : true,
      loadingImage : '/facebox/loading.gif',
      closeImage   : '/facebox/closelabel.gif',
      imageTypes   : [ 'png', 'jpg', 'jpeg', 'gif' ],
      defaultAjaxType  : 'html',
      faceboxHtml  : '\
    <div id="facebox" style="display:none;"> \
      <div class="popup"> \
        <table> \
          <tbody> \
            <tr> \
              <td class="tl"/><td class="tm"/><td class="tr"/> \
            </tr> \
            <tr> \
              <td class="ml"/> \
              <td class="body"> \
                <div class="pRelative"> \
                  <div class="content"> \
                  </div> \
                  <a href="#" class="close" /> \
                </div> \
              </td> \
              <td class="mr"/> \
            </tr> \
            <tr> \
              <td class="bl"/><td class="bm"/><td class="br"/> \
            </tr> \
          </tbody> \
        </table> \
      </div> \
    </div>'
    },

    loading: function() {
      init()
      if ($('#facebox .loading').length == 1) return true
      showOverlay()

      $('#facebox .content').empty()
      $('#facebox .body').children().hide().end().
        append('<div class="faceBoxloading"><img src="'+$.facebox.settings.loadingImage+'"/></div>')

      var el = $.facebox.settings.currentElement,
          leftVar = $(window).width() / 2 - 205

      $('#facebox .body').width(374).height(128)
      if (el && el.attr('data-facebox-height') && el.attr('data-facebox-width')) {
        $('#facebox .body').height(el.attr('data-facebox-height'))
                           .width(el.attr('data-facebox-width'))
                           .css({'vertical-align':'middle','text-align':'center'})
        if ($.browser.version == '7.0') $('#facebox').width(parseInt(el.attr('data-facebox-width'))+16)
        leftVar = $(window).width() / 2 - parseInt(el.attr('data-facebox-width')) / 2
      }

      $('#facebox').css({
        top: getPageScroll()[1] + (getPageHeight() / 10),
        left:	leftVar
      }).show()

      $(document).bind('keydown.facebox', function(e) {
        if (e.keyCode == 27) $.facebox.close()
        return true
      })
      $(document).trigger('loading.facebox')
    },

    reveal: function(data, klass, width, height) {
      $(document).trigger('beforeReveal.facebox')

      if (klass) $('#facebox .content').addClass(klass)
      var el = $('<div class="faceboxStack"/>')
        .data('width', width ? width : $('#facebox .body').width())
        .data('height', height ? height : $('#facebox .body').height())
      $('#facebox .content').append(el)
      el.append(data)
      $('#facebox .faceBoxloading').remove()
      $('#facebox .body').children().fadeIn('normal')
      $('#facebox .body').css({'vertical-align':'top','text-align':'left'})
      $('#facebox').css('left', $(window).width() / 2 - ($('#facebox .popup').outerWidth() / 2))

      $(document).trigger('reveal.facebox').trigger('afterReveal.facebox')
    },

    push: function(href, width, height) {
      width = width ? parseInt(width, 10) : 374
      height = height ? parseInt(height, 10) : 128
      var left = ($(window).width() - width) / 2
      var top = getPageScroll()[1] + (height > 600 ? 2 : (getPageHeight() / 10)) // don't waste too much screen

      // loading
      $('#facebox .content').children().hide().end().
        append('<div class="faceBoxloading"><img src="'+$.facebox.settings.loadingImage+'"/></div>')
      $('#facebox').css({'left':left,'top':top})
        .find('.body').width(width).height(height).css({'vertical-align':'middle','text-align':'center'})

      $.get(href, function(data) {
        $.facebox.reveal(data, '', width, height)
      })
      return false
    },

    pop: function() {
      // remove last
      $('.faceBoxloading').remove()
      var top = $('.faceboxStack:last')
      if (top[0]) top.remove()

      // show last
      top = $('.faceboxStack:last')
      if (top[0]) {
        var width = top.data('width')
        var height = top.data('height')
        var left = ($(window).width() - width) / 2
        $('#facebox').css('left', left)
          .find('.body').width(width).height(height)
        top.fadeIn()
      } else {
        $(document).trigger('close.facebox')
      }
      return false
    },

    close: function() {
      $(document).trigger('close.facebox')
      return false
    }
  })

  /*
   * Public, $.fn methods
   */

  $.fn.facebox = function(settings) {
    if ($(this).length == 0) return

    init(settings)

    function clickHandler(e) {
      e.preventDefault();

      $.facebox.settings.currentElement = $(this)
      $.facebox.loading(true)
      $.facebox.settings.currentElement = null

      // support for rel="facebox.inline_popup" syntax, to add a class
      // also supports deprecated "facebox[.inline_popup]" syntax
      var klass = this.rel.match(/facebox\[?\.(\w+)\]?/)
      if (klass) klass = klass[1]

      fillFaceboxFromHref(this.href, klass, $(this))
    }

    return this.bind('click.facebox', clickHandler)
  }

  /*
   * Private methods
   */

  // called one time to setup facebox on this page
  function init(settings) {
    if ($.facebox.settings.inited) return true
    else $.facebox.settings.inited = true

    $(document).trigger('init.facebox')
    makeCompatible()

    var imageTypes = $.facebox.settings.imageTypes.join('|')
    $.facebox.settings.imageTypesRegexp = new RegExp('\.(' + imageTypes + ')$', 'i')

    if (settings) $.extend($.facebox.settings, settings)
    $('body').append($.facebox.settings.faceboxHtml)

    var preload = [ new Image(), new Image() ]
    preload[0].src = $.facebox.settings.closeImage
    preload[1].src = $.facebox.settings.loadingImage

    $('#facebox').find('.b:first, .bl, .br, .tl, .tr').each(function() {
      preload.push(new Image())
      preload.slice(-1).src = $(this).css('background-image').replace(/url\((.+)\)/, '$1')
    })

    $('#facebox .close').click($.facebox.close)
    $('#facebox .close_image').attr('src', $.facebox.settings.closeImage)
  }

  // getPageScroll() by quirksmode.com
  function getPageScroll() {
    var xScroll, yScroll;
    if (self.pageYOffset) {
      yScroll = self.pageYOffset;
      xScroll = self.pageXOffset;
    } else if (document.documentElement && document.documentElement.scrollTop) {	 // Explorer 6 Strict
      yScroll = document.documentElement.scrollTop;
      xScroll = document.documentElement.scrollLeft;
    } else if (document.body) {// all other Explorers
      yScroll = document.body.scrollTop;
      xScroll = document.body.scrollLeft;
    }
    return new Array(xScroll,yScroll)
  }

  // Adapted from getPageSize() by quirksmode.com
  function getPageHeight() {
    var windowHeight
    if (self.innerHeight) {	// all except Explorer
      windowHeight = self.innerHeight;
    } else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
      windowHeight = document.documentElement.clientHeight;
    } else if (document.body) { // other Explorers
      windowHeight = document.body.clientHeight;
    }
    return windowHeight
  }

  // Backwards compatibility
  function makeCompatible() {
    var $s = $.facebox.settings

    $s.loadingImage = $s.loading_image || $s.loadingImage
    $s.closeImage = $s.close_image || $s.closeImage
    $s.imageTypes = $s.image_types || $s.imageTypes
    $s.defaultAjaxType = $s.default_ajax_type || $s.defaultAjaxType
    $s.faceboxHtml = $s.facebox_html || $s.faceboxHtml
  }

  // Figures out what you want to display and displays it
  // formats are:
  //     div: #id
  //   image: blah.extension
  //    ajax: anything else
  function fillFaceboxFromHref(href, klass, obj) {
    // div
    if (href.match(/#/)) {
      var url    = window.location.href.split('#')[0]
      var target = href.replace(url,'')
      if (target === '#') return
      $.facebox.reveal($(target).html(), klass)

    // image.
    // NOTE: Add "js-content-type" because some facebook image url isn't point to the image directly. so here it can't be displayed as image
    // This attribute forces facebox to treat containing element as a image.
    } else if (obj.attr("js-content-type") == "image" || href.match($.facebox.settings.imageTypesRegexp)) {
      fillFaceboxFromImage(href, klass)
    // ajax
    } else {
      fillFaceboxFromAjax(href, klass)
    }
  }

  function fillFaceboxFromImage(href, klass) {
    var image = new Image()
    image.onload = function() {
      $.facebox.reveal('<div class="image"><img src="' + image.src + '" /></div>', klass)
    }
    image.src = href
  }

  function fillFaceboxFromAjax(href, klass) {
    if (window.location.pathname.split(".").length == 1) {   // url doesn't include extension
      $.get(href, function(data) { $.facebox.reveal(data, klass); }, $.facebox.settings.defaultAjaxType)
    } else {
      $.get(href, function(data) { $.facebox.reveal(data, klass); })
    }
  }

  function skipOverlay() {
    return $.facebox.settings.overlay == false || $.facebox.settings.opacity === null
  }

  var is_ipad = !!navigator.userAgent.match(/iPad/i);

  function showOverlay() {
    if (skipOverlay()) return

    if ($('#facebox_overlay').length == 0)
      $("body").append('<div id="facebox_overlay" class="facebox_hide"></div>')

    $('#facebox_overlay').hide().addClass("facebox_overlayBG")
      .css('opacity', $.facebox.settings.opacity)
      .click(function() { $(document).trigger('close.facebox') })
      .fadeIn(200);
    if ( is_ipad ) {
      $('#facebox_overlay').css('position','absolute').height(document.height);
    }
    return false
  }

  function hideOverlay() {
    if (skipOverlay()) return

    $('#facebox_overlay').fadeOut(200, function(){
      $("#facebox_overlay").removeClass("facebox_overlayBG")
      $("#facebox_overlay").addClass("facebox_hide")
      $("#facebox_overlay").remove()
    })

    return false
  }

  /*
   * Bindings
   */

  $(document).bind('close.facebox', function() {
    $(document).unbind('keydown.facebox')
    $('#facebox').fadeOut(function() {
      $('#facebox .content').removeClass().addClass('content')
      $('#facebox .loading').remove()
      $(document).trigger('afterClose.facebox')
    })
    hideOverlay()
  })

})(jQuery);
