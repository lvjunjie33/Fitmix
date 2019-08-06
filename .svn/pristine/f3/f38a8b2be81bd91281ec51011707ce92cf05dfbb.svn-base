var polyline = null;
var lineArr = new Array(); //创建线覆盖物节点坐标数组
var polylineColor = "#ff8b22";//轨迹颜色
var marker;
var bianjiImg = "static/img/paoren.gif";//标记点图片

/***
 * 设置地图中心点
 * 根据websocket返回的数据设置
 */
function setCenter(locationX, locationY) {
    var contet = new AMap.LngLat(locationX, locationY);
    //map.setZoomAndCenter(mapZoom, contet);
    map.setCenter(contet);
    marker.setMap(map);
};
/***
 * 添加点标记
 * @param LngLatX
 * @param LngLatY
 */
function addMarker(LngLatX, LngLatY) {

    map.clearMap(); //清空所有覆盖物
    /*marker = new AMap.Marker({
        offset: new AMap.Pixel(-45, -50),//相对于基点的位置
        icon: bianjiImg, //static/image/dong.png
        position: new AMap.LngLat(LngLatX, LngLatY)
    });*/

    marker = new AMap.Marker({ //添加自定义点标记
        map: map,
        position: [LngLatX, LngLatY], //基点位置
        offset: new AMap.Pixel(-17, -42), //相对于基点的偏移位置
        draggable: false,  //是否可拖动
        content: '<img src="'+bianjiImg+'" id="paorenImg" class="paorenImg" >'   //自定义点标记覆盖物内容 onload="renImg()"//添加跑步人物图片加载完成后的动作
    });

    marker.setMap(map); //在地图上添加点
}
/***
 * 绘制路线轨迹
 * @param locationX    经度
 * @param locationY    纬度
 */

function drawRoute(locationX, locationY) {

    if (lineArr.length == 0 || lineArr.length % 10 == 0) {
        addMarker(locationX, locationY);
        setCenter(locationX, locationY);
    } else {
        addMarker(locationX, locationY); //添加标记点
    }


    lineArr.push(new AMap.LngLat(locationX, locationY));
    polyline = new AMap.Polyline({
        path: lineArr, //设置线覆盖物路径
        strokeColor: polylineColor, //线颜色
        strokeOpacity: 1, //线透明度
        strokeWeight: 5, //线宽
        strokeStyle: "solid", //线样式
        strokeDasharray: [10, 5] //补充线样式
    });
    polyline.setMap(map);
}