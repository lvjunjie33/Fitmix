<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/7/26
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=98dfV32uFI52e1iUnlpYKdbbXSBYhiQY"></script>
    <script src="/static/jquery-1.11.2.min.js"></script>
    <title>设置点的新图标</title>
</head>
<body>
<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">

    var searchManager = function() {
        function rebuildMap() {
            getTarget();
        }

        function getTarget() {
            $.ajax({
                type: "GET",
                async: true,
                dataType: "json",
                url: "/search/get/target.json",
                data: {
                },
                success: function (data) {
                    parseData(data)
                    viewMap();
                }
            });
        }

        function parseData(data) {
            var all_ = data['searchStats'];
            searchManager.targets = all_;
            $.each(all_, function(a, b){
                //b是对象
                /*
                 avatar:""
                 bpm:83
                 calorie:220
                 distance:5113
                 id:1
                 step:5793
                 tracks:Array(456)
                 userId:1151049
                 userName:"荣"
                 userRunId:2705550
                */
            });
        }

        var viewMap = function() {

            //创建坐标点对象
            function buildPoint(node) {
                return new BMap.Point(node.lng, node.lat);
            }

            // 创建带图标的标注
            function addMarker(point, iconLink, lable){
//                iconLink = "http://lbsyun.baidu.com/jsdemo/img/fox.gif";
                if (iconLink == undefined || iconLink == null || iconLink == '') {
                    var marker = new BMap.Marker(point);
                    map.addOverlay(marker);
                    addLable(marker, lable)
                    return;
                }
                var myIcon = new BMap.Icon(iconLink, new BMap.Size(300, 157));
                var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
                map.addOverlay(marker);
                addLable(marker, lable)
            }

            //添加标签
            function addLable(m, lable) {
                if (lable != undefined && lable != null && lable != '') {
                    m.setLabel(new BMap.Label(lable,{offset:new BMap.Size(20,-10)}))
                }
            }

            //绘制线条
            function connectionLine(points) {

                if (points == undefined || points == null) {
                    points = [
                        new BMap.Point(106.553865,29.553935),//起始点的经纬度
                        new BMap.Point(106.553765,29.553535),
                        new BMap.Point(106.554865,29.552935)//终止点的经纬度
                    ];
                }

                var polyline = new BMap.Polyline(points, {strokeColor:"red",//设置颜色
                    strokeWeight:3, //宽度
                    strokeOpacity:0.5});//透明度
                map.addOverlay(polyline);
            }

            //锁定当前地址
            function lockingPoint() {
                var point_;
                var geolocation = new BMap.Geolocation();
                geolocation.getCurrentPosition(function(r){
                    if(this.getStatus() == BMAP_STATUS_SUCCESS){
                        var mk = new BMap.Marker(r.point);
                        map.addOverlay(mk);
                        map.panTo(r.point);
                        point_ = new BMap.Point(r.point.lng, r.point.lat);
//                        alert('您的位置：'+r.point.lng+','+r.point.lat);
                    }
                    else {
                        alert('failed'+this.getStatus());
                    }
                },{enableHighAccuracy: true})
                //关于状态码
                //BMAP_STATUS_SUCCESS	检索成功。对应数值“0”。
                //BMAP_STATUS_CITY_LIST	城市列表。对应数值“1”。
                //BMAP_STATUS_UNKNOWN_LOCATION	位置结果未知。对应数值“2”。
                //BMAP_STATUS_UNKNOWN_ROUTE	导航结果未知。对应数值“3”。
                //BMAP_STATUS_INVALID_KEY	非法密钥。对应数值“4”。
                //BMAP_STATUS_INVALID_REQUEST	非法请求。对应数值“5”。
                //BMAP_STATUS_PERMISSION_DENIED	没有权限。对应数值“6”。(自 1.1 新增)
                //BMAP_STATUS_SERVICE_UNAVAILABLE	服务不可用。对应数值“7”。(自 1.1 新增)
                //BMAP_STATUS_TIMEOUT	超时。对应数值“8”。(自 1.1 新增)
                return point_;
            }

            function formatLable(uName, distance, step, bpm, calorie) {
                return uName + "运动了" + distance + "米,走了" + step + "步,bpm为" + bpm + ",消耗了" + calorie + "卡路里";
            }

            {

                // 百度地图API功能
                var map = new BMap.Map("allmap");

                var  point = lockingPoint();
                if (point == undefined || point == null || point == '') {
                    point = new BMap.Point(116.404, 39.915);
                }
                map.centerAndZoom(point, 15);

                map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
                map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用


                $.each(searchManager.targets, function(a, b) {
                    var points_ = new Array();
                    if (b.tracks != undefined && b.tracks != null && b.tracks != '') {
                        $.each(b.tracks, function(i, j) {
                            points_[i] = new BMap.Point(j.lng, j.lat);
                        });
                    }
                    var outset = buildPoint(b.outset);//开始点
                    addMarker(outset, 0); //标点

                    var finish = buildPoint(b.finish);//结束点
                    addMarker(finish, 0,formatLable(b.userName, b.distance, b.step, b.bpm, b.calorie))
                    connectionLine(points_);//画轨迹
                });
            }

        };

        return {
            rebuildMap:rebuildMap, targets : null
        };
    }();

    searchManager.rebuildMap();
</script>
