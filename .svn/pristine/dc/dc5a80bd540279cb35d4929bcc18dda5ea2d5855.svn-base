/**
 * Created by Administrator on 2015/5/13.
 */

(function(window){
    var lch = function () {} || new Object() || {};
    lch.webSocket = function () {
        return function init (url, msgfn) {
            var webSocket = new WebSocket(url);
            webSocket.onopen = function(event) {
                initSend();
            };
            webSocket.onmessage = function(event) {
                msgfn(event);
            };
            function initSend () {
                webSocket.send('{"msg":"111","na":"路过.","ui":66,"sm":"y"}');
            };
        }
    };
    window.lch = lch;
})(window);
