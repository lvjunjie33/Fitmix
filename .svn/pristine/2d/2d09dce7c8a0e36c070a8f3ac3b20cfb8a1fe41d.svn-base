/**
 * Created by wjcaozhi1314 on 2016/4/5.
 */

var map = null;
var GDMap = {};
function init(){
    if(window.share.attributes.model == window.share.MODAL_TYPE_INDOOR){
        var details = window.share.attributes.detail["array"];

        map = new AMap.Map('map', {
            center: [details[parseInt(details.length / 2)].lng, details[parseInt(details.length / 2)].lat],
            zoom: 14,
            mapStyle: 'dark'
        });
        map.plugin(["AMap.ToolBar"], function() {

            //map.addControl(new AMap.ToolBar());
            $(".amap-logo,.amap-copyright").remove();

            GDMap.getJson(details);

            GDMap.crateOverlay([details[0].lng,details[0].lat],"/static/user-run-share/imgs/run_start.png");
            GDMap.crateOverlay([details[details.length-1].lng,details[details.length-1].lat],"/static/user-run-share/imgs/run_end.png");

            setMapStatic(map,details);

        });



    }
}

GDMap.getJson = function (array){
    var points = new Array();
    for (var i = 0; i < array.length; i++) {
        if (array[0].used != undefined) {
            //android
            if (array[i].used == 1 || array[i].used == true) {
                if(i > 2 && array.length > 4) {
                    // 轨迹圆润处理
                    var ax, ay, bx, by, cx, cy, dx, dy;
                    for (var t = 0; t <= 1; t += 0.2) {
                        ax = (-array[i-3].lng + 3 * array[i-2].lng - 3 * array[i-1].lng + array[i].lng) / 6;
                        ay = (-array[i-3].lat + 3 * array[i-2].lat - 3 * array[i-1].lat + array[i].lat) / 6;
                        bx = (array[i-3].lng - 2 * array[i-2].lng + array[i-1].lng) / 2;
                        by = (array[i-3].lat - 2 * array[i-2].lat + array[i-1].lat) / 2;
                        cx = (-array[i-3].lng + array[i-1].lng) / 2;
                        cy = (-array[i-3].lat + array[i-1].lat) / 2;
                        dx = (array[i-3].lng + 4 * array[i-2].lng + array[i-1].lng) / 6;
                        dy = (array[i-3].lat + 4 * array[i-2].lat + array[i-1].lat) / 6;
                        points.push([ax * Math.pow(t, 3) + bx * Math.pow(t, 2) + cx * t + dx, ay * Math.pow(t, 3) + by * Math.pow(t, 2) + cy * t + dy]);
                    }
                } else {
                    points.push([array[i].lng,array[i].lat]);
                }
            }
        } else {
            points.push([array[i].lng,array[i].lat]);
        }
    }

    var polyline = new AMap.Polyline({
        path: points,            // 设置线覆盖物路径
        strokeColor: '#ff8a22',   // 线颜色
        strokeOpacity: 1,         // 线透明度
        strokeWeight: 4,          // 线宽
        strokeStyle: 'solid',     // 线样式
        strokeDasharray: [10, 5], // 补充线样式
        geodesic: false            // 绘制大地线
    });
    polyline.setMap(map);
}

GDMap.crateOverlay = function (detailArray,imgUrl){
    //添加点标记，并使用自己的icon
    new AMap.Marker({
        map: map,
        position: detailArray,
        icon: new AMap.Icon({
            size: new AMap.Size(40, 50),  //图标大小
            image: imgUrl,
            imageOffset: new AMap.Pixel(0, -0)
        })
    });
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
    //111400
    if (MaxX - MinX > (MapWidth - MapXoffset) / 62000 || MaxY - MinY > (MapHeight - 20) / 62000) {
        MapZoom = 16;
        MapXadd = 0.005755
    }
    //55700
    if (MaxX - MinX > (MapWidth - MapXoffset) / 31000 || MaxY - MinY > (MapHeight - 20) / 32000) {
        MapZoom = 15;
        MapXadd = 0.011511
    }
    //27800
    if (MaxX - MinX > (MapWidth - MapXoffset) / 15000 || MaxY - MinY > (MapHeight - 20) / 15000) {
        MapZoom = 14;
        MapXadd = 0.022857
    }
    //13900
    if (MaxX - MinX > (MapWidth - MapXoffset) / 7200 || MaxY - MinY > (MapHeight - 20) / 7200) {
        MapZoom = 13;
        MapXadd = 0.045714
    }
    //7000
    if (MaxX - MinX > (MapWidth - MapXoffset) / 3600 || MaxY - MinY > (MapHeight - 20) / 3600) {
        MapZoom = 12;
        MapXadd = 0.091428
    }
    //3500
    if (MaxX - MinX > (MapWidth - MapXoffset) / 1800 || MaxY - MinY > (MapHeight - 20) / 1800) {
        MapZoom = 11;
        MapXadd = 0.182856
    }
    //;1750
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



    //创建中心点
    var mapElement = document.getElementById("map");
    var mapCenterPoint = [region.center.longitude,region.center.latitude];
    var zoom = getZoom(mapElement.offsetWidth, mapElement.offsetHeight, maxLongitude, minLongitude, maxLatitude, minLatitude);
    // 设置缩放级别和中心点
    map.setZoomAndCenter(zoom-1, mapCenterPoint);


}