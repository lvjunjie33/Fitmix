/**
 * Created by wjcaozhi1314 on 2016/3/31.
 *
 * 数据图表
 */
// 监听竖屏横屏切换
window.addEventListener('orientationchange', function(event){
    if ( window.orientation == 180 || window.orientation==0 ) {
        console.log("竖屏");
        window.location.reload();
    }
    if( window.orientation == 90 || window.orientation == -90 ) {
        console.log("横屏");
        window.location.reload();
    }
});

// 根据速度表传过来的数据生成横坐标
function getXLabel(time,len) {
    // 根据传过来的时间和数据长度计算横坐标
    var allMinutes = time/60;// 获得总分钟数
    var xLabel = [];// 横坐标
    var num = 0;
    var xPoint = Math.floor(len/8); // 把每个数据分成8等分（无论多少分钟，这里的len好像固定52）
    var splitPoint = allMinutes/8<1?(allMinutes/8).toFixed(2):Math.floor(allMinutes/8);  // 把数据分成对应的等分数据(一半时间都超过3分钟，如果真有两分钟的话，以后再说)
    // 0.1是随便设置，横坐标只显示整数，
    for(var i = 0; i < len; i++) {
        if(i%xPoint==0) {
            xLabel.push((num*splitPoint).toFixed(1));
            num++;
        }else {
            xLabel.push(0.1);
        }
        //xLabel.push((i%splitPoint==0&&i%8==0||i%9==0||i%10==0||i%11==0||i%12==0)?i/splitPoint:0.1);
    }
    return xLabel;
}

//速度曲线
function drawMainSuDu(xLabel,speedChart){
    var suduArr = speedChart.array;
    $('.avg-speed').text('平均:' + speedChart.Avg/100 + '公里/小时');
    // 速度纵坐标
    yLabel = suduArr.map(function(item) {
        // Value值一个乘以100的千米/小时单位，除以100就是真正的km/h,所以换算成米就是除以100乘以1000
        return Math.ceil(item.Value*10/60);
    });
    var suDuChart = echarts.init(document.getElementById('mainSuDu'));
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
                    return value!=0.1 && value!=0;
                }
            }

        },
        yAxis: {
            type: 'value',
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

//心率曲线
function drawMainHeart(xLabel,hrChart) {
    console.log('画心率');
    var heartArr = hrChart.array;
    // 心率纵坐标
    var yLabel = heartArr.map(function(item) {
        // Value为心率多少
        return item.Value;
    });
    //初始化图标
    $('.avg-heart').text('平均:' + hrChart.Avg + '次/分钟')
    var myChart = echarts.init(document.getElementById('mainHeart'));
    var optionHeart = {
        xAxis: {
            type: 'category',
            boundaryGap:false,
            data: xLabel,
            splitLine:{
                show:false,
                lineStyle: {
                    // 使用深浅的间隔色
                    color: '#303032'
                }
            },
            axisLine: {
                lineStyle: {
                    color:'#303032',
                    width:3
                }
            },
            axisLabel:{
                show: true,
                textStyle:{
                    fontSize:font,
                    color: '#d1cdd5'
                    // 让字体变大
                },
                // 设置间隔，只要是整数就返回true
                interval: function(index,value) {
                    return value!=0.1 && value!=0;
                }
            }
        },
        yAxis: {
            type: 'value',
            min: Math.min.apply(null,yLabel) - 2,
            max: Math.max.apply(null,yLabel) + 2,
            splitLine:{
                show: true,
                lineStyle: {
                    // 使用深浅的间隔色
                    color: '#303032'
                }
            },//去除网格线
            axisLine:{
                lineStyle:{
                    color:'#303032',
                    width:3//这里是为了突出显示加上的
                }
            },
            axisTick:{
                lineStyle:{
                    color:'#303032'
                }
            },//刻度线颜色
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
                    color: "#eb2a61",
                    lineStyle: {
                        color: "#eb2a61"
                    }
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgba(235,42,97,0.3)'
                    }, {
                        offset: 1,
                        color: 'rgba(235,42,97,0)'
                    }])
                }
            },
            smooth:true,  //曲线变平滑的
//                    data: heartData,
            data: yLabel
        }]
    };
    myChart.setOption(optionHeart);
}

//海拔曲线
function drawMainAltitude(xLabel,altitudeChart) {
    // 画图
    var altitudeArr = altitudeChart.array;
    // 海拔纵坐标
    var yLabel = altitudeArr.map(function(item,index,array) {
        // Value为心率海拔高度，应该除以100吧
        return item.Value/10;
    });
    //初始化图标
    $('.avg-altitude').text('平均:' + altitudeChart.Avg/10 + '米/分钟')
    var myChart = echarts.init(document.getElementById('mainAltitude'));
    var optionHeart = {
        xAxis: {
            type: 'category',
            boundaryGap:false,
            data: xLabel,
            splitLine:{
                show:false,
                lineStyle: {
                    // 使用深浅的间隔色
                    color: '#303032'
                }
            },
            axisLine: {
                lineStyle: {
                    color:'#303032',
                    width:3
                }
            },
            axisLabel:{
                show: true,
                textStyle:{
                    fontSize:font,
                    color: '#d1cdd5'
                    // 让字体变大
                },
                // 设置间隔，只要是整数就返回true
                interval: function(index,value) {
                    return value!=0.1 && value!=0;
                }
            }
        },
        yAxis: {
            type: 'value',
            min: Math.min.apply(null,yLabel) - 2,
            max: Math.max.apply(null,yLabel) + 2,
            splitLine:{
                show: true,
                lineStyle: {
                    // 使用深浅的间隔色
                    color: '#303032'
                }
            },//去除网格线
            axisLine:{
                lineStyle:{
                    color:'#303032',
                    width:3//这里是为了突出显示加上的
                }
            },
            axisTick:{
                lineStyle:{
                    color:'#303032'
                }
            },//刻度线颜色
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
                    color: "green",
                    lineStyle: {
                        color: "green"
                    }
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgba(0,255,0,0.3)'
                    }, {
                        offset: 1,
                        color: 'rgba(0,255,0,0)'
                    }])
                }
            },
            smooth:true,  //曲线变平滑的
//                    data: heartData,
            data: yLabel
        }]
    };
    myChart.setOption(optionHeart);
}

//步频曲线
function drawMainBuPin(xLabel,bpmChart){
    var buPinArr = bpmChart.array;
    var yLabel = buPinArr.map(function(item) {
        return item.Value;
    });
    $('.avg-step').text('平均:'+ bpmChart.Avg +'步/分钟');
    var myChart = echarts.init(document.getElementById('mainBuPin'));
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
            //axisTick:{
            //    lineStyle:{
            //        color:'#fff'
            //    }
            //},//刻度线颜色
            splitLine: {show: false},
            axisLabel:{
                show: true,
                textStyle:{
                    fontSize:font,
                    color: '#d1cdd5'
                    // 让字体变大
                },
                // 设置间隔，只要是整数就返回true
                interval: function(index,value) {
                    return value!=0.1 && value!=0;
                }
            }

        },
        yAxis: {
            type: 'value',
            min: Math.min.apply(null,yLabel) - 2,
            max: Math.max.apply(null,yLabel) + 2,
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