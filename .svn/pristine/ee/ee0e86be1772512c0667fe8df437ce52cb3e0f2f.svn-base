/**
 * Created by lc on 2018/5/25.
 */
var fonts = $('.step-speed').css("font-size");
var font = parseInt(fonts)*4/5; // 横纵坐标的字体大小值
// 换算时间，把安卓的时间换算成跟ios时间一样,距离也是
(function(global) {
    var array = share.attributes.stepDetail.array;
    var startTime = share.attributes.startTime;
    var len = array.length;
    var xLabel = [];
    if(array[0].time > 10000) {
        for(var i = 0; i < len; i++) {
            array[i].time = array[i].time / 1000;
            array[i].distance = array[i].distance / 1000;
        }
    }
    // 处理数据
    // 处理数据
    //for(var i = 1; i < len; i++) {
    //    if(+array[i].step < +array[i-1].step) {
    //        array[i].step = array[i-1].step;
    //    }
    //    if(+array[i].distance < +array[i-1].distance) {
    //        array[i].distance = array[i-1].distance;
    //    }
    //}

    var flag = 0;
    var splitArr;
    var space = 60; // 正常来说都是60
    var runTime = array[len - 1].time - array[0].time;
    if(runTime > 1860) {
        space = runTime / 30;
    }
    //if(array.length > 104 || runTime > 1860) {
    var splitArr = array.filter(function(item,index,arr) {
        if(item.time - arr[flag].time > space) {
            flag = index;
            return true;
        }
    });
    //}

    // 判断最后一个数据是不是元数据的最后一个数据
    if(splitArr && flag < array.length - 1) {
        splitArr.push(array[array.length - 1]);
    }
    console.log(splitArr);
    var endTime;
    var len;
    if(splitArr) {
        len = splitArr.length;
        global.xLabel = getXLabel2(runTime,len);
        global.newArr = splitArr;
    }else {
        len = array.length;
        global.xLabel = getXLabel(runTime,len);
        global.newArr = array;
    }
    //var splitP = array.length/52; // 把数据拆分成52等分
    //if(array.length>=104) {
    //    global.newArray = array.filter(function(item,index,arr) {
    //        if((index+1) % splitP <= 0.5) {
    //            return item;
    //        }
    //    });
    //}else {
    //    global.newArray = [];
    //}
})(this);
function getXLabel(time,len) {
    // 根据传过来的时间和数据长度计算横坐标
    var allMinutes = time/60;// 获得总分钟数的8等分
    var xLabel = [];// 横坐标
    var num = 0;
    var mark = false;
    var xPoint = []; // 把每个数据分成8等分（无论多少分钟，这里的len好像固定52）
    var splitPoint = allMinutes/8<1?(allMinutes/8).toFixed(2):allMinutes/8;  // 把数据分成对应的等分数据(一半时间都超过3分钟，如果真有两分钟的话，以后再说)
    // 0.1是随便设置，横坐标只显示整数，
    for(var i = 0; i <= 8; i++) {
        xPoint.push(i*((len-1)/8));
    }

    var prevIndex;
    for(var i = 0; i < len; i++) {
        for(var j = 0; j < xPoint.length; j++) {
            if(Math.abs(i - xPoint[j]) <= 0.5) {
                mark = true;
                break;
            }
        }
        if(mark) {
            if(prevIndex && (i - prevIndex)==1) {
                xLabel.push(0.1);
            }else {
                xLabel.push((num*splitPoint).toFixed(1));
                num++;
            }
            prevIndex = i;
        }else {
            xLabel.push(0.1);
        }
        mark = false;
        //if(xPoint.indexOf(i)==0) {
        //    xLabel.push((num*splitPoint).toFixed(1));
        //    num++;
        //}else {
        //    xLabel.push(0.1);
        //}

        //xLabel.push((i%splitPoint==0&&i%8==0||i%9==0||i%10==0||i%11==0||i%12==0)?i/splitPoint:0.1);
    }
    console.log(xLabel);
    return xLabel;
}
function getXLabel2(time,len) {
    // 根据传过来的时间和数据长度计算横坐标
    var allMinutes = (time / 60).toFixed(1);
    var xLabel = [];
    var splitTime = allMinutes / (len -1);
    for(var i = 0;i < len;i++) {
        xLabel.push((i*splitTime).toFixed(0));
    }
    return xLabel;
}

function drawMainBuPin(xLabel,array){
    var yLabel;
    var sum = 0;
    var oldArr = share.attributes.stepDetail.array;
    yLabel = array.map(function(item,index,arr) {
        if(index == 0) {
            if(item.time - oldArr[0].time == 0) {
                sum = 0;
            }else {
                sum += (item.step - oldArr[0].step) * 60 / (item.time - oldArr[0].time);
            }
            return (item.step - oldArr[0].step) * 60 / (item.time - oldArr[0].time);
        }else if(item.step - arr[index-1].step == 0) {
            return 0;
        }else if(item.time - arr[index-1].time == 0) {
            return 0;
        }
        sum += (item.step - arr[index-1].step) * 60 / (item.time - arr[index-1].time);
        return Math.round((item.step - arr[index-1].step) * 60 / (item.time - arr[index-1].time))
    });
    //console.log(yLabel)
    //var yLabel = [];
    //for(var i = 0; i <= 8; i++) {
    //    yLabel.push(array.indexOf(i*(array.length-1)/8)!=-1?array[i*(array.length-1)/8]:array.indexOf(Math.round(i*(array.length-1)/8))!=-1?array[Math.round(i*(array.length-1)/8)]:array[Math.ceil(i*(array.length-1)/8)]);
    //}
    //yLabel = yLabel.map(function(item,index,arr) {
    //    if(index == 0) {
    //        return item.step / item.time * 60
    //    }
    //    return (item.step - arr[index-1].step) / (item.time - arr[index-1].time) * 60
    //});
    $('.avg-step').text('平均:'+ Math.round(sum/array.length) +'步/分钟');
    var myChart = echarts.init(document.getElementById('mainBuPin'));

    // 横坐标数据展示
    var val = xLabel[Math.floor(xLabel.length / 4)];
    var valArr = [];
    // 判断xLabel的长度，小于20就不用截取几个点显示
    if(xLabel.length <= 20) {
        // 让字符串数字变成数字
        valArr = xLabel.map(function(item) {
            return item*1;
        });
    }else {
        for(var i = 0;i < 4;i++) {
            valArr.push(i*val);
        }
        valArr.push(+xLabel[xLabel.length - 1]);
    }
    var optionBuPin = {
        xAxis: {
            data: xLabel,
            boundaryGap:false,
            splitLine:{show: true},//去除网格线
            axisLine: {
                lineStyle: {
                    color:'#303032',
                    width:3
                }
            },
            axisLabel:{
                show: true,
                textStyle:{
                    fontSize: font,
                    color: '#d1cdd5'
                    // 让字体变大
                },
                interval: function(index,value) {
                    if(value == 0) {
                        return false;
                    }
                    return value!=0.1 && valArr.indexOf(+value)!=-1;
                }
            },
            //axisTick:{
            //    lineStyle:{
            //        color:'#fff'
            //    }
            //},//刻度线颜色
            splitLine: {show: false}

        },
        yAxis: {
            type: 'value',
            min: Math.min.apply(null,yLabel)>=40?Math.min.apply(null,yLabel) - 40:Math.min.apply(null,yLabel),
            max: 280,
            splitLine:{
                show: true,
                lineStyle: {
                    // 使用深浅的间隔色
                    color: '#303032'
                }
                //interval:2
            },//去除网格线
            axisLine:{
                lineStyle:{
                    color:'#303032',
                    width:3//这里是为了突出显示加上的
                }
            },
            //axisTick:{
            //    lineStyle:{
            //        color:'#fff'
            //    }
            //},//刻度线颜色
            axisLabel:{
                show: true,
                textStyle:{
                    fontSize:font,
                    color: '#d1cdd5'
                    // 让字体变大
                }
            }
        },
        grid: {
            x:"8%",
            x2:"4%",
            y:"9%",
            y2:"10%"
        },
        series: [{
            type: 'line',
            showSymbol: false,
            itemStyle: {
                normal: {
                    color: "#ebb166",
                    lineStyle: {
                        color: "#ebb166"
                    }
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgba(235,177,102,0.3)'
                    }, {
                        offset: 1,
                        color: 'rgba(235,177,102,0)'
                    }])
                }
            },
            smooth:true,
            data: yLabel
        }]
    };
    myChart.setOption(optionBuPin);
}

//速度曲线
function drawMainSuDu(xLabel,array){
    $('#su-span').text('公里/小时');
    // 速度纵坐标
    var sum = 0;
    var oldArr = share.attributes.stepDetail.array;
    var yLabel = array.map(function(item,index,arr) {
        if(index == 0) {
            if(item.time - oldArr[0].time == 0) {
                sum = 0;
            }else {
                sum += (item.distance - oldArr[0].distance) * 3600 / (item.time - oldArr[0].time);
            }
            return ((item.distance - oldArr[0].distance) * 3600 / (item.time - oldArr[0].time)).toFixed(1)
        }
        if(item.time - arr[index-1].time == 0) {return 0}
        sum += (item.distance - arr[index-1].distance)*3600 / (item.time - arr[index-1].time);
        return ((item.distance - arr[index-1].distance) * 3600 / (item.time - arr[index-1].time)).toFixed(1);
    });
    $('.avg-speed').text('平均:' + (sum/array.length).toFixed(2) + '公里/小时');
    var suDuChart = echarts.init(document.getElementById('mainSuDu'));
    // 横坐标数据展示
    var val = xLabel[Math.floor(xLabel.length / 4)];
    var valArr = [];
    // 判断xLabel的长度，小于20就不用截取几个点显示
    if(xLabel.length <= 20) {
        // 让字符串数字变成数字
        valArr = xLabel.map(function(item) {
            return item*1;
        });
    }else {
        for(var i = 0;i < 4;i++) {
            valArr.push(i*val);
        }
        valArr.push(+xLabel[xLabel.length - 1]);
    }
    var optionSuDu = {
        xAxis: {
            data: xLabel,
            boundaryGap:false,
            splitLine:{show: true},//去除网格线
            axisLine: {
                lineStyle: {
                    color:'#303032',
                    width:3
                }
            },
            splitLine: {show: false},
            axisLabel:{
                show: true,
                textStyle:{
                    fontSize: font,
                    color: '#d1cdd5'
                    // 让字体变大
                },
                interval: function(index,value) {
                    if(value == 0) {
                        return false;
                    }
                    return value!=0.1 && valArr.indexOf(+value)!=-1;
                }
            }

        },
        yAxis: {
            type: 'value',
            min: Math.min.apply(null,yLabel)>=40?Math.floor(Math.min.apply(null,yLabel) - 40):Math.floor(Math.min.apply(null,yLabel)),
            max: 12,
            splitLine:{
                show: true,
                lineStyle: {
                    // 使用深浅的间隔色
                    color: '#303032'
                }
                //interval:2
            },//去除网格线
            axisLine:{

                lineStyle:{
                    color:'#303032',
                    width:3//这里是为了突出显示加上的
                }
            },
            //axisTick:{
            //    lineStyle:{
            //        color:'#fff'
            //    }
            //},//刻度线颜色
            axisLabel:{
                show: true,
                textStyle:{
                    fontSize: font,
                    color: '#d1cdd5'
                    // 让字体变大
                }
            }
        },
        grid: {
            x:"8%",
            x2:"4%",
            y:"9%",
            y2:"10%"
        },
        series: [{
            type: 'line',
            showSymbol: false,
            itemStyle: {
                normal: {
                    color: "#6d87f8",
                    lineStyle: {
                        color: "#6d87f8"
                    }
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgba(109,135,248,0.3)'
                    }, {
                        offset: 1,
                        color: 'rgba(109,135,248,0)'
                    }])
                }
            },
            smooth:true,
            data:yLabel
        }]
    };
    suDuChart.setOption(optionSuDu);
}

(function() {
    if (newArr.length > 0) {
        drawMainBuPin(xLabel,newArr);
        drawMainSuDu(xLabel,newArr);
    } else {
        $(".myCanvas").remove();
        $(".outText").show();
    }
})();