/**
 * Created by wjcaozhi1314 on 2016/3/23.
 */
function getScaleStepWidth(maxNumber) {
    return parseInt(maxNumber / 3);
}

var createCanvas = function (objId, labels, data, width, dataSetFill, scaleStepWidth, scaleSteps, xLabelsSkip) {
    var lineData = {
        labels: labels,//labels,//后台数据 横向
        datasets: data
    };
    var canvas = document.getElementById(objId);
    var cwidth = $("#" + objId).css("width");
    cwidth = cwidth.replace("px", "");
    canvas.width = parseInt(cwidth) * 2;
    canvas.height = 600;
    var fontSize = $("html").css("font-size").replace("px", "");
    fontSize = fontSize * 0.6;

    var ctx = $("#" + objId).get(0).getContext("2d");
    new Chart(ctx).Line(lineData, {
        responsive: true,
        xLabelsSkip: xLabelsSkip,
        datasetFill: dataSetFill,
        scaleOverride: true,   //是否用硬编码重写y轴网格线
        scaleSteps: scaleSteps,        //y轴刻度的个数
        scaleStepWidth: scaleStepWidth,   //y轴每个刻度的宽度
        scaleStartValue: 0,    //y轴的起始值

        pointDot: false,        //是否显示点
        pointDotRadius: 5,     //点的半径
        // pointDotStrokeWidth: 1,//点的线宽
        // datasetStrokeWidth: 3, //数据线的线宽
        animation: true,       //是否有动画效果
        animationSteps: 60,    //动画的步数
        // 数据集行程
        datasetStroke: true,
        // 是否使用贝塞尔曲线? 即:线条是否弯曲
        bezierCurve: true,
        // 是否显示网格
        scaleShowGridLines: true,
        // 网格颜色
        scaleGridLineColor: "#35343a",
        // 网格宽度
        scaleGridLineWidth: 1,
        scaleShowLabels:true,
        // Y/X轴的颜色
        scaleLineColor: "#35343a",
        // X,Y轴的宽度
        scaleLineWidth: 2,
        showTooltips: false,
        //是否旋转
        customXLabelRota:false,
        // 字大小
        scaleFontSize: fontSize,
        scaleFontColor: "#cbcbcb",
        scaleShowHorizontalLines: true, // 是否显示横向线
        scaleShowVerticalLines: true, // 是否显示竖向线
        barShowStroke : true // 是否显示线
        //scaleFontFamily: "big_noodle_titling"
    });
}

var createHeartCanvas = function (objId, labels, data, width, dataSetFill, scaleStepWidth, scaleSteps, xLabelsSkip) {

    var lineData = {
        labels: labels,//labels,//后台数据 横向
        datasets: data
    };
    var canvas = document.getElementById(objId);
    var cwidth = $("#" + objId).css("width");
    cwidth = cwidth.replace("px", "");
    canvas.width = parseInt(cwidth) * 2;
    canvas.height = 600;
    var fontSize = $("html").css("font-size").replace("px", "");
    fontSize = fontSize * 0.6;

    var ctx = $("#" + objId).get(0).getContext("2d");
    new Chart(ctx).Line(lineData, {
        responsive: true,
        xLabelsSkip: xLabelsSkip,
        datasetFill: dataSetFill,
        scaleOverride: true,   //是否用硬编码重写y轴网格线
        scaleSteps: scaleSteps,        //y轴刻度的个数
        scaleStepWidth: scaleStepWidth,   //y轴每个刻度的宽度
        scaleStartValue: 0,    //y轴的起始值
        pointDot: false,        //是否显示点
        pointDotRadius: 5,     //点的半径
        // pointDotStrokeWidth: 1,//点的线宽
        // datasetStrokeWidth: 3, //数据线的线宽
        animation: true,       //是否有动画效果
        animationSteps: 60,    //动画的步数
        // 数据集行程
        datasetStroke: true,
        // 是否使用贝塞尔曲线? 即:线条是否弯曲
        bezierCurve: true,
        // 是否显示网格
        scaleShowGridLines: true,
        // 网格颜色
        scaleGridLineColor: "#35343a",
        // 网格宽度
        scaleGridLineWidth: 1,
        scaleShowLabels:true,
        // Y/X轴的颜色
        scaleLineColor: "#35343a",
        // X,Y轴的宽度
        scaleLineWidth: 2,
        showTooltips: false,
        //是否旋转
        customXLabelRota:false,
        // 字大小
        scaleFontSize: fontSize,
        scaleFontColor: "#cbcbcb",
        scaleShowHorizontalLines: true, // 是否显示横向线
        scaleShowVerticalLines: true, // 是否显示竖向线
        barShowStroke : true // 是否显示线
        //scaleFontFamily: "big_noodle_titling"
    });
}

/***
 * 获取数组平均值
 * @param array
 * @param size
 * @returns {Number}
 */
var getAverage = function (array, size) {
    var r = 0;
    if (array.length > 0) {
        for (var i = 0; i < array.length; i++) {
            r += parseInt(array[i]);
        }
        console.log("r == " + r);
        if (r > 0) {
            r = r / size;
        }
    }
    return parseFloat(r).toFixed(2);
}
/***
 * ios
 * 配速算法
 * font f1 = 1/(公里/分钟);
 * int datam = (int)f1;//分
 * int datas = (int)(f1 - datam)*60;//秒
 * String datam+"'"datas;//陪速
 *
 *
 * @param mm 总时长 单位分钟
 * @param gl 总路程 单位公里
 * @returns {string}
 */
var putRunTime = function putRunTime(mm, gl) {
    //暂时 使用ios算法计算 配速
    var f1 = parseFloat(1 / (gl / mm));
    var mm = parseInt(f1);
    var ss = parseInt((f1 - mm) * 60).toString();
    ss = formatTime(ss);
    var time = mm + "'" + ss + "\"";
    //return f1.replace(".","'");//配速
    return time;
}