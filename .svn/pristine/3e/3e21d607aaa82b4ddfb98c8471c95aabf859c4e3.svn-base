(function (window) {
    var BUMap = {
        scriptURL: "http://api.map.baidu.com/api?v=2.0&ak=gtpqUarGuGxYjfg9EX3MpxlUB8R2Ucsx&callback=init",
        scriptType: "text/javascript",
        zoom: 14
    };
    window.BUMapObj = BUMap;
})(window);

function createImg(map, src) {
    var img = document.createElement("img");
    img.style.position = "absolute";
    img.style.width = "20px";
    img.src = src;

    map.getPanes().labelPane.appendChild(img);

}
/***
 *
 * @param MapWidth
 * @param MapHeight
 * @param MaxX lng
 * @param MinX lng
 * @param MaxY lat
 * @param MinY lat
 * @returns {number}
 */
function getZoom(MapWidth, MapHeight, MaxX, MinX, MaxY, MinY) {

    var MapZoom = 18;
    var MapXadd = 0.001436;
    var MapXoffset = 0;
    if (MapWidth < 800) MapXoffset = 0;
    if (MaxX - MinX > (MapWidth - MapXoffset) / 130000 || MaxY - MinY > (MapHeight - 20) / 130000) {
        MapZoom = 17;
        MapXadd = 0.002873
    }
    ; //111400
    if (MaxX - MinX > (MapWidth - MapXoffset) / 62000 || MaxY - MinY > (MapHeight - 20) / 62000) {
        MapZoom = 16;
        MapXadd = 0.005755
    }
    ;//55700
    if (MaxX - MinX > (MapWidth - MapXoffset) / 31000 || MaxY - MinY > (MapHeight - 20) / 32000) {
        MapZoom = 15;
        MapXadd = 0.011511
    }
    ;//27800
    if (MaxX - MinX > (MapWidth - MapXoffset) / 15000 || MaxY - MinY > (MapHeight - 20) / 15000) {
        MapZoom = 14;
        MapXadd = 0.022857
    }
    ;//13900
    if (MaxX - MinX > (MapWidth - MapXoffset) / 7200 || MaxY - MinY > (MapHeight - 20) / 7200) {
        MapZoom = 13;
        MapXadd = 0.045714
    }
    ;//7000
    if (MaxX - MinX > (MapWidth - MapXoffset) / 3600 || MaxY - MinY > (MapHeight - 20) / 3600) {
        MapZoom = 12;
        MapXadd = 0.091428
    }
    ;//3500
    if (MaxX - MinX > (MapWidth - MapXoffset) / 1800 || MaxY - MinY > (MapHeight - 20) / 1800) {
        MapZoom = 11;
        MapXadd = 0.182856
    }
    ;//1750
    if (MaxX - MinX > (MapWidth - MapXoffset) / 900 || MaxY - MinY > (MapHeight - 20) / 900) {
        MapZoom = 10;
        MapXadd = 0.365712
    }
    MapXadd = 0;
    return MapZoom;

}
/****
 * 设置地图中心点和缩放级别
 *
 * @param map
 * @param points
 */
function setMapStatic(map, points) {

    var minLatitude = 999.0000000000000000;
    var minLongitude = 999.0000000000000000;
    var maxLatitude = 0.1000000000000000;
    var maxLongitude = 0.1000000000000000;


    //--------------------------------------------------------------------------------------------------------
    for (var i = 1; i < points.length; i++) {

        //console.log("lng: " + points[i].lng + "    lat: " + points[i].lat + " -- " + (points[i-1] > points[i]));

        if (points[i].lat < minLatitude) {
            minLatitude = points[i].lat;
        }
        if (points[i].lng < minLongitude) {
            minLongitude = points[i].lng;
        }

        if (points[i].lat > maxLatitude) {
            maxLatitude = points[i].lat;
        }
        if (points[i].lng > maxLongitude) {
            maxLongitude = points[i].lng;
        }

    }
    console.log("minLatitude :" + minLatitude);
    console.log("minLongitude :" + minLongitude);
    console.log("maxLatitude :" + maxLatitude);
    console.log("maxLongitude :" + maxLongitude);
    //中心点
    var region = {
        center: {latitude: 0, longitude: 0},
        span: {latitudeDelta: 0, longitudeDelta: 0}
    };
    region.center.latitude = (minLatitude + maxLatitude) / 2;
    region.center.longitude = (minLongitude + maxLongitude) / 2;
    //范围点
    region.span.latitudeDelta = (maxLatitude - minLatitude) + 0.001;
    region.span.longitudeDelta = (maxLongitude - minLongitude) + 0.0001;

    console.log(region);

    //--------------------------------------------------------------------------------------------------------

    //创建中心点
    var mapCenterPoint = new BMap.Point(region.center.longitude, region.center.latitude);
    console.log("mapCenterPoint : ");
    console.log(mapCenterPoint);
    map.setCenter(mapCenterPoint);
    console.log(map.getCenter());

    //创建地图可见范围

    var mapElement = document.getElementById("map");
    var zoom = getZoom(mapElement.offsetWidth, mapElement.offsetHeight, maxLongitude, minLongitude, maxLatitude, minLatitude);
    console.log("zoom : " + zoom);
    map.setZoom(zoom);

    /*var pointA = new BMap.Point(maxLongitude,maxLatitude);  // 创建点坐标A--大渡口区
     var pointB = new BMap.Point(minLongitude,minLatitude);  // 创建点坐标B--江北区
     var jl = parseInt(map.getDistance(pointA,pointB));
     console.log('从大渡口区到江北区的距离是：'+(map.getDistance(pointA,pointB)).toFixed(2)+' 米。');  //获取两点距离,保留小数点后两位

     if(jl < 400){
     map.setZoom(17);
     }*/

    /*var bs = map.getBounds();   //获取可视区域
     var bssw = bs.getSouthWest();   //可视区域左下角
     var bsne = bs.getNorthEast();   //可视区域右上角
     console.log("当前地图可视范围是：" + bssw.lng + "," + bssw.lat + "到" + bsne.lng + "," + bsne.lat);
     map.enableScrollWheelZoom();
     try {
     var b = new BMap.Bounds(new BMap.Point(maxLongitude, maxLatitude),new BMap.Point(minLatitude, minLatitude));
     bs.set
     BMapLib.AreaRestriction.setBounds(map, b);
     }catch (e){
     console.log("错误信息:");
     console.log(e);
     }
     var bs = map.getBounds();   //获取可视区域
     var bssw = bs.getSouthWest();   //可视区域左下角
     var bsne = bs.getNorthEast();   //可视区域右上角
     console.log("当前地图可视范围是：" + bssw.lng + "," + bssw.lat + "到" + bsne.lng + "," + bsne.lat);*/


}

function cs(details) {

    var lengthNumber = details.length;
    var H = 10;

    //坐标转换完之后的回调函数
    translateCallback_1 = function (data) {
        console.log("translateCallback_1 ____________ ");
        console.log(data.status);
        if (data.status === 0) {
            for (var i = 0; i < data.points.length; i++) {
                console.log(data.points[i]);
            }
        }
    }
    var pointArr = [];
    for (var i = 0; i < lengthNumber; i++) {
        if (details[0].used != undefined) {
            //android
            if (details[i].used == 0) {
                pointArr.push(new BMap.Point(details[i].lng, details[i].lat));
            }
        } else {
            pointArr.push(new BMap.Point(details[i].lng, details[i].lat));
        }
    }

    var convertor = new BMap.Convertor();
    convertor.translate(pointArr, 3, 5, translateCallback_1);


}
var points = [];
function init() {
    var details = window.share.attributes.detail["array"];

    if (details.length > 0) {
//----------------------------------------------------------
        //cs(details);
//----------------------------------------------------------

        BUMap.point.lng = details[parseInt(details.length / 2)].lng;
        BUMap.point.lat = details[parseInt(details.length / 2)].lat;
        //创建百度地图
        var map = BUMap.createBUMap(BUMap.point);
        map.addEventListener("tilesloaded", function () {
            console.log("地图加载完毕");
        });
        //var points = BUMap.getJson(details);//折线数组

        points = BUMap.getJson(details);//折线数组


        BUMap.point.lng = points[0].lng;
        BUMap.point.lat = points[0].lat;
        //创建折线
        BUMap.cratePolyline(map, points, BUMap.stroke);
        //创建标记点 自定义图标
        BUMap.myIcon = new BMap.Icon("static/user-run-share/imgs/run_start.png", new BMap.Size(22, 32 * 1.9));//开始图标
        //创建标记点 自定义
        BUMap.crateOverlay(map, BUMap.point, BUMap.myIcon);
        //var big = new BMap.Point(BUMap.point.lng, BUMap.point.lat);

        BUMap.point.lng = points[points.length - 1].lng;
        BUMap.point.lat = points[points.length - 1].lat;
        BUMap.myIcon = new BMap.Icon("static/user-run-share/imgs/run_end.png", new BMap.Size(22, 32 * 1.9));//结束图标
        BUMap.crateOverlay(map, BUMap.point, BUMap.myIcon);
        //var end = new BMap.Point(BUMap.point.lng, BUMap.point.lat);

        setMapStatic(map, points);

    }
}

function loadJSScript() {
    var scriptElemnt = document.createElement("script");
    scriptElemnt.type = window.BUMapObj.scriptType;
    scriptElemnt.src = window.BUMapObj.scriptURL;
    document.body.appendChild(scriptElemnt);
}

var BUMap = {};
BUMap.point = {
    lng: 0, //经度
    lat: 0 //纬度
}

/***
 * strokeColor 折线Color  string
 * strokeWeight 折线宽度      number
 * strokeOpacity 透明度       number 0-1
 *
 */
BUMap.stroke = {
    strokeColor: "#ff8a22",
    strokeWeight: 4,
    strokeOpacity: 1
}
/***
 * 默认自定义标记点
 */
BUMap.myIcon = null;

/***
 * 创建地图
 * @param point 坐标点
 */
BUMap.createBUMap = function (point) {
    var map = new BMap.Map("map");
    map.centerAndZoom(new BMap.Point(point.lng, point.lat), window.BUMapObj.zoom); // 初始化地图,设置中心点坐标和地图级别
    //设置地图风格
    /*map.setMapStyle({
     style: "midnight"
     });*/


    return map;
}
/***
 * 创建折线
 * @param map 地图对线
 * @param {Object} Points 折线数组 [new BMap.Point(116.399, 39.910),new BMap.Point(116.399, 39.910),new BMap.Point(116.399, 39.910)]
 * @param stroke 折线样式对线
 */
BUMap.cratePolyline = function (map, Points, stroke) {
    //创建折线
    var polyline = new BMap.Polyline(Points, stroke);
    //增加折线
    map.addOverlay(polyline);
}

/***
 * 创建定位图标
 *
 * @param {Object} map        //地图对象
 * @param {Object} point    //坐标点
 * @param {Object} myIcon    //自定义图标对象 无自定义为null
 */
BUMap.crateOverlay = function (map, point, myIcon) {

    var pt = new BMap.Point(point.lng, point.lat);

    var marker; // 创建标注
    if (myIcon != null) { //自定义 图标
        marker = new BMap.Marker(pt, {
            icon: myIcon
        });
    } else { //默认图标
        marker = new BMap.Marker(pt);
    }
    // 将标注添加到地图中
    map.addOverlay(marker);
    return marker;
}

BUMap.setLabel = function (marker, labelStr) {
    var laber = new BMap.Label(labelStr, {
        offset: new BMap.Size(20, -10)
    });
    marker.setLabel(laber);
}
/***
 * 解析数组中的经纬度 并返回一个仅为度数组
 * @param array
 */
BUMap.getJson = function (array) {
    console.log("array : "+array.length);
    var points = new Array();
    for (var i = 0; i < array.length; i++) {
        if (array[0].used != undefined) {
            //android
            if (array[i].used == 1) {
                points.push(new BMap.Point(array[i].lng, array[i].lat));
            }
        } else {
            points.push(new BMap.Point(array[i].lng, array[i].lat));
        }
    }
    return points;
}