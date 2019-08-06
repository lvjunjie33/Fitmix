(function($) {
	// Pass a link element to resize facebox frame
  $.dropdownboxUrl = function (url, self, data) {
    $.dropdownbox.settings.currentElement = self;
    $.dropdownbox({ ajax: url, data: data });
    $.dropdownbox.settings.currentElement = null;
  };

  // For ajax way to replace some html
  // $.faceWithSize("www.google.com", "200x300")
  $.dropdownboxWithSize = function (url, size) {
    var self = $("<div></div>");
    self.attr("data-dropdownbox-width", size.split('x')[0]).
         attr("data-dropdownbox-height", size.split('x')[1]);
    $.dropdownboxUrl(url, self);
  };

  $.dropdownbox = function(opts) {
    $.dropdownbox.loading()

    if (opts.ajax) fillDropdownboxFromAjax(opts.ajax, opts.data)
    else if (opts.image) fillDropdownboxFromImage(opts.image)
    else if (opts.div) fillDropdownboxFromHref(opts.div)
    else if ($.isFunction(opts)) opts.call($)
    else $.dropdownbox.reveal(opts)
	}

	$.extend($.dropdownbox, {
    settings: {
      opacity      		: 0.2,
      overlay      		: true,
      loadingImage 		: '/dropdownbox/loading_blue_bg.gif',
      closeImage   		: '/dropdownbox/closelabel.gif',
      imageTypes   		: [ 'png', 'jpg', 'jpeg', 'gif' ],
	    dropdownboxHtml : '\
	    <div id="dropdownbox" style="display:none;"> \
				      <div class="popup"> \
				        <table> \
				          <tbody> \
				            <tr> \
				              <td class="body"> \
				                 <div class="pRelative"> \
				                  <div class="content"> \
				                  </div> \
				                  <a href="#" class="close" /> \
				                </div> \
				              </td> \
				            </tr> \
				          </tbody> \
				        </table> \
				      </div> \
				    </div>'
    },

    loading: function() {
      init()
      if ($('#dropdownbox .loading').length == 1) return true
      showOverlay()

			// add loading bar.
      $('#dropdownbox .content').empty()
      $('#dropdownbox .pRelative').children().hide()

			// adjust position.
      var el = $.dropdownbox.settings.currentElement
			var leftVar = ($(window).width() - 925) / 2
			var dd_height = el ? el.attr('data-dropdownbox-height') : 300
      var loadingImgMargin = parseInt(dd_height)/2-56

			// set customized dropdownbox size
      if (el && el.attr('data-dropdownbox-height')) {
        $('#dropdownbox .body').height(el.attr('data-dropdownbox-height'))
					 .width(925)
					 .css({'vertical-align':'middle','text-align':'center'})
                               .find('.pRelative').height(el.attr('data-dropdownbox-height')).addClass('dropdownboxLoadingBox')
			}

			// show dropdownbox
      $('#dropdownbox').css({
        top: 0, // getPageScroll()[1] + (getPageHeight() / 10),
        left:	leftVar
      }).slideDown('500',function(){
          $('#dropdownbox .pRelative').append('<div class="dropdownboxloading"><img src="'+$.dropdownbox.settings.loadingImage+'" style="margin-top:'+ loadingImgMargin +'px" /></div>')
         })


			// bind event
      $(document).bind('keydown.dropdownbox', function(e) {
        if (e.keyCode == 27) $.dropdownbox.close()
        return true
      })
      $(document).trigger('loading.dropdownbox')
    },

    reveal: function(data, height) {
      $(document).trigger('beforeReveal.dropdownbox')

      var el = $('<div class="dropdownboxStack"/>').data('height', height ? height : $('#dropdownbox .body').height())
      $('#dropdownbox .content').append(el)
      el.append(data)
      $('#dropdownbox .dropdownboxloading').remove()
      $('#dropdownbox .pRelative').children().show()
      $('#dropdownbox .body').width(925).css({'vertical-align':'top','text-align':'left'}).find('.pRelative').removeClass('dropdownboxLoadingBox')

      $(document).trigger('reveal.dropdownbox').trigger('afterReveal.dropdownbox')
    },

    push: function(href, height) {
      height = height ? parseInt(height, 10) : 300
      var loadingImgMargin = height / 2 - 56

      // loading
      $('#dropdownbox .content').children().hide()
      $('#dropdownbox .body').height(height).css({'vertical-align':'middle','text-align':'center'})
      $('#dropdownbox .pRelative').height(height).addClass('dropdownboxLoadingBox')
      $('#dropdownbox .content').append('<div class="dropdownboxloading"><img src="'+$.dropdownbox.settings.loadingImage+'" style="margin-top:' + loadingImgMargin + 'px"/></div>')

      $.get(href, function(data) {
        $.dropdownbox.reveal(data, height)
      })
      return false
    },

    pop: function() {
      // remove last
      $('.dropdownboxloading').remove()
      var top = $('.dropdownboxStack:last')
      if (top[0]) top.remove()

      // show last
      top = $('.dropdownboxStack:last')
      if (top[0])
        $('#dropdownbox .body').height(top.fadeIn().data('height'))
      else
        $(document).trigger('close.dropdownbox')
      return false
    },

    close: function() {
      $(document).trigger('close.dropdownbox')
      return false
    }
  })

  /*
   * Public, $.fn methods
   */

  $.fn.dropdownbox = function(settings) {
    if ($(this).length == 0) return
    init(settings)
    return this.bind('click.dropdownbox', clickHandler)
  }

  function clickHandler(e) {
    e.preventDefault();
    $.dropdownbox.settings.currentElement = $(this)
    $.dropdownbox.loading(true)
    $.dropdownbox.settings.currentElement = null
    fillDropdownboxFromHref(this.href)
  }

  /*
   * Private methods
   */

  // called one time to setup dropdownbox on this page
  function init(settings) {
    if ($.dropdownbox.settings.inited) return true
    else $.dropdownbox.settings.inited = true

    $(document).trigger('init.dropdownbox')

    var imageTypes = $.dropdownbox.settings.imageTypes.join('|')
    $.dropdownbox.settings.imageTypesRegexp = new RegExp('\.(' + imageTypes + ')$', 'i')

    if (settings) $.extend($.dropdownbox.settings, settings)
    $('body').append($.dropdownbox.settings.dropdownboxHtml)

    $('#dropdownbox .close').click($.dropdownbox.close)
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

  // Figures out what you want to display and displays it
  // formats are:
  //     div: #id
  //   image: blah.extension
  //    ajax: anything else
  function fillDropdownboxFromHref(href) {
    // div
    if (href.match(/#/)) {
      var url    = window.location.href.split('#')[0]
      var target = href.replace(url,'')
      if (target === '#') return
      $.dropdownbox.reveal($(target).html())

    // image
    } else if (href.match($.dropdownbox.settings.imageTypesRegexp)) {
      fillDropdownboxFromImage(href)
    // ajax
    } else {
      fillDropdownboxFromAjax(href)
    }
  }

  function fillDropdownboxFromImage(href) {
    var image = new Image()
    image.onload = function() {
      $.dropdownbox.reveal('<div class="image"><img src="' + image.src + '" /></div>')
    }
    image.src = href
  }

  function fillDropdownboxFromAjax(url, data) {
    $.get(url, data, $.dropdownbox.reveal);
  }

  function skipOverlay() {
    return $.dropdownbox.settings.overlay == false || $.dropdownbox.settings.opacity === null
  }

  var is_ipad = !!navigator.userAgent.match(/iPad/i);

  function showOverlay() {
    if (skipOverlay()) return

    if ($('#dropdownbox_overlay').length == 0)
      $("body").append('<div id="dropdownbox_overlay" class="dropdownbox_hide"></div>')

    $('#dropdownbox_overlay').hide().addClass("dropdownbox_overlayBG")
      .css('opacity', $.dropdownbox.settings.opacity)
      .click(function() { $(document).trigger('close.dropdownbox') })
      .fadeIn(200);
    if ( is_ipad ) {
      $('#dropdownbox_overlay').css('position','absolute').height(document.height);
    }
    return false
  }

  function hideOverlay() {
    if (skipOverlay()) return

    $('#dropdownbox_overlay').fadeOut(200, function(){
      $("#dropdownbox_overlay").removeClass("dropdownbox_overlayBG")
      $("#dropdownbox_overlay").addClass("dropdownbox_hide")
      $("#dropdownbox_overlay").remove()
    })

    return false
  }

  /*
   * Bindings
   */

  $(document).bind('close.dropdownbox', function() {
    $(document).unbind('keydown.dropdownbox')
    $('#dropdownbox').slideUp('500', function() {
			hideOverlay()
      $('#dropdownbox .content').removeClass().addClass('content')
      $('#dropdownbox .loading').remove()
      $(document).trigger('afterClose.dropdownbox')
    })
  })
})(jQuery);
