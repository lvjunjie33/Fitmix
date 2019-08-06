/**
 * Created by wjcaozhi1314 on 2016/3/23.
 */
require.config({
    baseUrl: "static",
    paths: {
        "jquery": "jquery-1.11.2.min",
        "Chart": "Chart",
        "chartUtil":"share-new/chartUtil",
        "map": "https://webapi.amap.com/maps?v=1.3&key=0a730f3f39c856cec1fa18bd9289cf11",
        "util": "user-run-share/js/util",
        "MapUtil": "user-run-share/js/MapUtil",
        "recordingFileXml": "user-run-share/js/recordingFileXml"
    },
    shim: {
        "util": {
            exports: "util"
        },
        "Chart": {
            exports: "Chart"
        },
        "MapUtil": {
            deps:["map"],
            exports: "MapUtil"
        },
        "recordingFileXml": {
            exports: "recordingFileXml"
        }
    }
});

require(
    ["jquery","map","chartUtil","util","MapUtil","recordingFileXml"],
    function ($,mapApi,chartUtil,util,MapUtil,recordingFileXml){

        if(window.share.attributes.model == window.share.MODAL_TYPE_OUTDOOR){
            console.log("这条记录是室内模式.....");
        }else{
            console.log("这条记录是室外模式.....");
        }


        //获取屏幕高度和宽度
        var width = $("body").width();
        var height = $(window).height();

        //设置地图高度和宽度
        $("#map").css({
            "height": (height * 0.9) + "px"
        });

        var startTime = new Date(window.share.attributes.startTime).Format("yyyy-MM-dd hh:mm:ss");//开始时间
        var endTime = new Date(window.share.attributes.endTime).Format("yyyy-MM-dd hh:mm:ss");//结束时间
        //$("#addTime").text((new Date(${share.addTime}).Format("yyyy-MM-dd hh:mm:ss")));
        $("#addTime").text(startTime);

        $("#yunTime").text(recordingFileXml.getTimeDisparity(window.share.attributes.runTime));
        $("#runTime").text(chartUtil.putRunTime(recordingFileXml.runTime, window.share.attributes.distance / 1000));


        //获取轨迹文件
        var detail = window.share.attributes.detail;
        var list = detail["array"];
        //判断是否真的存在轨迹
        if (list.length > 0) {
            //初始化地图对象，加载地图
            var map = MapUtil.createMap("map");
            $(".amap-logo").remove();//删除高德地图中一些不需要的元素
            $(".amap-copyright").remove();//删除高德地图中一些不需要的元素

            //将文件解析成坐标数组
            var lineArr = new Array(); //创建线覆盖物节点坐标数组
            //组装 轨迹数组
            for (var i = 0; i < list.length; i++) {
                if (list[i].used != 0) {
                    lineArr.push(MapUtil.createLngLat(list[i]));
                }
            }
            //绘制轨迹
            MapUtil.drawRoute(map, lineArr);
            //设置中心点和缩放级别
            //setZoomAndCenter(map, 15, [getMaxNumber(list,"lng")/2, getMaxNumber(list,"lat")/2]);
            MapUtil.setZoomAndCenter(map, 15, [list[list.length - 1].lng, list[list.length - 1].lat]);

            //设置标记点
            MapUtil.addMarker(map, [list[0].lng, list[0].lat], "static/user-run-share/imgs/run_start.png");
            MapUtil.addMarker(map, [list[list.length - 1].lng, list[list.length - 1].lat], "static/user-run-share/imgs/run_end.png");
        } else {
            //没有轨迹的处理
            $("#map").addClass("mapNull");
            $("#map").append("<p>轨迹文件已经丢失 ... ...</p>");
        }

        //获取记步文件
        var stepDetail = window.share.attributes.stepDetail;
        if (stepDetail["array"].length > 0) {

            //解析记步文件成数组
            var detailAll = recordingFileXml.arrayData(stepDetail["array"]);
            var chartLineDatasets = new Array();
            chartLineDatasets = [];
            var dataArray = new Array();
            dataArray = recordingFileXml.getSpeed(detailAll);//封装成获取后台数据 纵向
            chartLineDatasets.push(
                {
                    fillColor: "#4D3F62",//填充color
                    strokeColor: "#A363E2",//曲线color
                    pointColor: "#945AD8",//节点color
                    pointStrokeColor: "#fff",//节点边框color
                    data: dataArray//封装成获取后台数据 纵向
                }
            );
            console.log(chartLineDatasets);
            chartUtil.createCanvas("myChart", recordingFileXml.getLabels(detailAll, "distance", "公里", 6), chartLineDatasets, $(".myCanvas_div").width(), true);
            $("#speed").text("平均值:" + chartUtil.getAverage(dataArray, stepDetail["array"].length));

            chartLineDatasets = [];
            dataArray = recordingFileXml.getCadence(detailAll);
            chartLineDatasets.push(
                {
                    fillColor: "#6167C3",//填充color
                    strokeColor: "#6167C3",//曲线color
                    pointColor: "#945AD8",//节点color
                    pointStrokeColor: "#fff",//节点边框color
                    data: dataArray//封装成获取后台数据 纵向
                }
            );

            chartUtil.createCanvas("myChart1", recordingFileXml.getLabels(detailAll, "time", "分钟", 6), chartLineDatasets, $(".myCanvas_div").width(), false);
            //$("#detail").text("平均值:" + getAverage(dataArray, stepDetail["array"].length));
            var step = window.share.attributes.step;
            var runTime = window.share.attributes.runTime;
            var number = step / parseInt(runTime / 1000 / 60);
            $("#detail").text("平均值:" + parseInt(number));
        } else {
            alert("记步文件丢失");
        }


    }
);

