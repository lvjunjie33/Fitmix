/**
 * Created by wjcaozhi1314 on 2016/2/29.
 */
var polyline = null;//轨迹对象
var Marker = {//点标记对象
    icon_beg: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
    icon_end: "http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png"
}

/***
 * 创建高德地图
 * @param map
 */
function createMap(mapId, array) {
    console.log("创建高德地图 .... ");
    var map = new AMap.Map(mapId, {
        center: array,
        zoom: 17
    });
    map.plugin(["AMap.ToolBar"], function () {
        map.addControl(new AMap.ToolBar());
    });
    console.log(map + " ... ... ...");
    return map;
}

/***
 * 设置地图缩放级别
 * @param number
 */
function setZoom(map, zoom) {
    map.setZoom(zoom);
}
/***
 * 设置地图中心点
 * 根据websocket返回的数据设置
 */
function setCenter(map, array) {
    var contet = new AMap.LngLat(array[0], array[1]);
    map.setCenter(contet);
}
/***
 *  设置地图中心和缩放级别
 * @param map
 * @param zoom
 * @param lng
 * @param lat
 * @param array
 */
function setZoomAndCenter(map, zoom, array) {
    map.setZoomAndCenter(zoom, array);
}
/***
 * 添加点标记
 * @param map
 * @param array
 */
function addMarker(map, array, icon) {
    var marker = new AMap.Marker({
        position: array, //基点位置
        //offset: new AMap.Pixel(0, -50), //相对于基点的偏移位置
        draggable: false,  //是否可拖动
        content: '<img src="' + icon + '" style="height: 50px;left: -10px;top: -14px;position: relative;" >'   //自定义点标记覆盖物内容 onload="renImg()"//添加跑步人物图片加载完成后的动作
    });
    marker.setMap(map);
}
/***
 * 绘制路线轨迹
 * @param map
 * @param array
 */
function drawRoute(map, array) {

    this.polyline = new AMap.Polyline({
        path: array,          //设置线覆盖物路径
        strokeColor: "red", //线颜色
        strokeOpacity: 1,       //线透明度
        strokeWeight: 6,        //线宽
        strokeStyle: "solid",   //线样式
        strokeDasharray: [10, 5] //补充线样式
    });
    this.polyline.setMap(map);
}
/***
 * 设置轨迹点
 * @param obj
 * @returns {AMap.LngLat}
 */
function createLngLat(obj) {
    return new AMap.LngLat(obj.lng, obj.lat);
}

/*define(
 function () {
 return {
 polyline: null,//轨迹对象
 Marker: {//点标记对象
 icon_beg: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
 icon_end: "http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png"
 },
 /!***
 * 创建高德地图
 * @param map
 *!/
 createMap: function (mapId) {
 console.log("创建高德地图 .... ");
 var map = new AMap.Map(mapId, {});
 console.log(map+" ... ... ...");
 return map;
 },
 /!***
 * 设置地图缩放级别
 * @param number
 *!/
 setZoom: function (map, zoom) {
 map.setZoom(zoom);
 },
 /!***
 * 设置地图中心点
 * 根据websocket返回的数据设置
 *!/
 setCenter: function (array) {
 var contet = new AMap.LngLat(array[0], array[1]);
 map.setCenter(contet);
 marker.setMap(map);
 },
 /!***
 *  设置地图中心和缩放级别
 * @param map
 * @param zoom
 * @param lng
 * @param lat
 * @param array
 *!/
 setZoomAndCenter: function (map, zoom, array) {
 map.setZoomAndCenter(zoom, array);
 },
 /!***
 * 添加点标记
 * @param map
 * @param array
 *!/
 addMarker: function (map, array, icon) {
 var marker = new AMap.Marker({
 position: array, //基点位置
 //offset: new AMap.Pixel(0, -50), //相对于基点的偏移位置
 draggable: false,  //是否可拖动
 content: '<img src="' + icon + '" style="height: 50px;left: -10px;top: -14px;position: relative;" >'   //自定义点标记覆盖物内容 onload="renImg()"//添加跑步人物图片加载完成后的动作
 });
 marker.setMap(map);
 },
 /!***
 * 绘制路线轨迹
 * @param map
 * @param array
 *!/
 drawRoute: function (map, array) {

 this.polyline = new AMap.Polyline({
 path: array,          //设置线覆盖物路径
 strokeColor: "red", //线颜色
 strokeOpacity: 1,       //线透明度
 strokeWeight: 5,        //线宽
 strokeStyle: "solid",   //线样式
 strokeDasharray: [10, 5] //补充线样式
 });
 this.polyline.setMap(map);
 },
 /!***
 * 设置轨迹点
 * @param obj
 * @returns {AMap.LngLat}
 *!/
 createLngLat: function (obj) {
 return new AMap.LngLat(obj.lng, obj.lat);
 }
 }
 }
 );*/

// 计算经纬度距离
var EARTH_RADIUS = 6378.137;//地球半径

function rad(d) {
    return d * Math.PI / 180.0;
}

function getDistance(lat1, lng1, lat2, lng2) {
    var radLat1 = rad(lat1);
    var radLat2 = rad(lat2);
    var a = radLat1 - radLat2;
    var b = rad(lng1) - rad(lng2);

    var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
            Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
    s = s * EARTH_RADIUS;
    s = Math.round(s * 10000) / 10000;
    return s;
}

