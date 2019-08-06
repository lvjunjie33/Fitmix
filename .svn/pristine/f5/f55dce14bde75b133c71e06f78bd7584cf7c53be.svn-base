/**
 * Created by wjcaozhi1314 on 2016/3/7.
 * 记步文件解析
 */

var pedometer = {} || new Object();

/***
 * 获取步数总和
 * @param array
 */
pedometer.getStepAll = function (array) {
    var step = 0;
    for (var i = 0; i < array.length; i++) {
        step += parseFloat(array[i].step);
    }
    return step.toFixed(2);
}
/***
 *  总时间
 * @param array
 * @returns {string}
 */
pedometer.getTimeALl = function (array) {
    var time = 0;
    for (var i = 0; i < array.length; i++) {
        time += parseFloat(array[i].time);
    }
    return time.toFixed(2);
}
/***
 * 获取距离总和
 * @param array
 */
pedometer.getDistanceAll = function (array) {
    var distance = 0;
    for (var i = 0; i < array.length; i++) {
        distance += parseFloat(array[i].distance);
    }
    return distance.toFixed(2);
}
/***
 * 距离数组
 * @param array
 * @returns {Array}
 */
pedometer.getDistanceArray = function (array) {
    var distance = new Array();
    for (var i = 0; i < array.length; i++) {
        distance[i] = parseFloat(array[i].distance);
    }
    distance[distance.length] = "公里";
    return distance;
}
/***
 * 获取步频的方法
 * 步数➗时间(分钟)
 * @param array
 */
pedometer.getCadence = function (array) {
    var resultList = new Array();
    for (var i = 1; i < array.length; i++) {
        var objA = array[i - 1];
        var objB = array[i];

        var step = objB.step - objA.step;
        var time = parseFloat(objB.time - objA.time).toFixed(2);
        var resultNumber = parseInt(step / time);
        if (resultNumber > 0) {
            //步数➗时间(分钟)
            resultList.push(parseInt(resultNumber > 220 ? 220 : resultNumber));
        }
    }
    return resultList;
}
/***
 * 设置速度的方法
 * 米/秒*3.6
 * @param array
 */
pedometer.getSpeed = function (array) {
    var resultList = new Array();
    for (var i = 1; i < array.length; i++) {
        var objA = array[i - 1];
        var objB = array[i];

        var distance = (objB.distance - objA.distance) * 1000;//将公里转米
        var time = (objB.time - objA.time) * 60;//将分钟转秒
        var resultNumber = parseInt(distance / time * 3.6);
        if (resultNumber > 0) {
            resultList.push(resultNumber);
        }
    }
    return resultList;
}

/***
 * 根据分钟设置x周节点
 */
pedometer.getLineTimes = function (array) {
    var labels = new Array();
    for (var i = 0; i < array.length; i++) {
        labels[i] = array[i].time;
    }
    labels[labels.length] = "分钟";
    return labels;
}
/***
 * 获取运动时长
 *
 */
pedometer.getTimeDisparity = function (time) {
    var retrunDateStr = "";
    //总时长 分钟
    pedometer.runTime = parseFloat((time / 1000 / 60).toFixed(2));
    //小时
    var hh = parseInt(pedometer.runTime / 60);
    //秒
    var ss = parseInt(((pedometer.runTime - parseInt(pedometer.runTime)) * 60));
    var mm = parseInt(pedometer.runTime) - hh * 60;//分钟

    retrunDateStr = formatTime(hh) + ":" + formatTime(mm) + ":" + formatTime(ss);
    return retrunDateStr;

}
/**
 * 格式化时间
 * @param time
 * @returns {*}
 */
function formatTime(time) {
    if (time.toString().length < 2) {
        time = "0" + time;
    }
    return time;
}
/***
 * 图标横向标记点
 * @param array
 * @param page 需要获取的字段名称
 * @param unit
 * @param max 坐标点最大个数
 */
pedometer.getLabels = function (array, page, unit, max) {
    var max = max;//最大坐标数量
    var j = 1;
    var length = array.length;//数据最大个数
    var resultList = new Array();
    if (length < max) {
        max = length;
    } else {
        j = parseInt(length / max);
    }
    for (var i = 0; i < max - 1; i++) {
        resultList[i] = array[i * j][page];
    }
    //添加最高点
    resultList[resultList.length] = maxArray(array, page);
    resultList[resultList.length] = unit;
    return resultList;
}
/***
 * 获取做大的属性值
 * @param array
 * @param page
 * @returns {*}
 */
function maxArray(array, page) {
    var max = array[0][page];
    for (var i = 1; i < array.length; i++) {
        if (max < parseFloat(array[i][page])) {
            max = parseFloat(array[i][page]);
        }
    }
    return max;
}

/**
 * 将android和ios数据进行统计
 * @param array
 * @returns {*}
 */
pedometer.arrayData = function (array) {
    if (parseInt(array[0].time) > 1000000) {//android
        var time = array[0].time;
        //转成ios类型
        for (var i = 0; i < array.length; i++) {
            //distance 米 转 公里
            array[i].distance = parseFloat((array[i].distance / 1000).toFixed(2));
            //time 时间挫转分钟
            if (i == 0) {
                array[i].time = 0;
            } else {
                array[i].time = parseFloat(((new Date(array[i].time) - new Date(time)) / 1000 / 60).toFixed(2));
            }
        }
    } else {
        for (var i = 0; i < array.length; i++) {
            //time 秒转分钟
            array[i].time = parseInt(array[i].time) / 60;
            array[i].time = array[i].time.toFixed(2);
        }
    }
    return array;
}
/***
 * 转换json文件中的float
 * @param str
 * @returns {string|XML|*}
 */
function stringFormatFloat(str){
    str = str.replace("'",".");
    str = str.replace("\"","");
    return parseFloat(str);
}

function getMax(number1,number2){
    return number1 > number2 ? number1 : number2;
}

function getMin(number1,number2){
    return number1 < number2 ? number1 : number2;
}

/***
 * 计算最大配速和最小配速
 * @param array
 */
pedometer.getMaxAndMin = function (array){
    var obj={
        max:0.00,
        min:stringFormatFloat(array[0].speed)
    }
    for (var i = 0;i < array.length;i++){
        //4'13"
        var speed = stringFormatFloat(array[i].speed);
        obj.max = getMax(obj.max,speed);
        obj.min = getMin(obj.min,speed);
    }
    return obj;
}

function pingjun(array){
    var returnValue = 0;
    for (var i = 0 ; i < array.length;i++){
        returnValue+=array[i];
    }
    return returnValue / array.length;
}
