

////Flot Multiple Axes Line Chart
//$(function() {
//    var bandwide = [
//        [1, 61.05],
//        [2, 58.32],
//        [3, 57.35],
//        [4, 56.31],
//        [5, 55.55],
//
//    ];
//    var cpu = [
//        [1, 0.7580],
//        [2, 0.7580],
//        [3, 0.75470],
//        [4, 0.75490],
//
//    ];
//
//    function euroFormatter(v, axis) {
//        return v.toFixed(axis.tickDecimals);
//    }
//
//    function doPlot(position) {
//        $.plot($("#flot-line-chart-multi"), [{
//            data: bandwide,
//            label: "带宽"
//        }, {
//            data: cpu,
//            label: "CPU占用",
//            yaxis: 2
//        },], {
//            xaxes: [{
//                mode: 'time'
//            }],
//            yaxes: [{
//                min: 0
//            }, {
//                // align if we are to the right
//                alignTicksWithAxis: position == "right" ? 1 : null,
//                position: position,
//                tickFormatter: euroFormatter
//            }],
//            legend: {
//                position: 'sw'
//            },
//            grid: {
//                hoverable: true //IMPORTANT! this is needed for tooltip to work
//            },
//            tooltip: true,
//            tooltipOpts: {
//                content: "%s was %y",
//
//                onHover: function(flotItem, $tooltipEl) {
//                    // console.log(flotItem, $tooltipEl);
//                }
//            }
//
//        });
//    }
//
//    doPlot("right");
//
//    $("button").click(function() {
//        doPlot($(this).text());
//    });
//});


//Flot Bar Chart

//$(function() {
//
//    var barOptions = {
//        series: {
//            bars: {
//                show: true,
//                //barWidth: 43200000//这里是500天的秒数
//                barWidth: 86400
//            }
//        },
//        xaxis: {
//            mode: "time",
//            timeformat: "%m/%d",
//            minTickSize: [1, "day"]
//        },
//        grid: {
//            hoverable: true
//        },
//        legend: {
//            show: false
//        },
//        tooltip: true,
//        tooltipOpts: {
//            content: "%x,%y次"
//        }
//    };
//    //这里是控制的是条形图的
//    var barData = {
//        label: "bar",
//        data: [
//            [1354521600000, 1000],
//            [1355040000000, 2000],
//            [1355223600000, 3000],
//            [1355306400000, 4000],
//            [1355487300000, 5000],
//            [1355571900000, 6000]
//        ]
//    };
//    $.plot($("#flot-bar-chart"), [barData], barOptions);
//
//});
////Flot Moving Line Chart
//$(function() {
//
//    var container = $("#flot-line-chart-moving");
//
//    // Determine how many data points to keep based on the placeholder's initial size;
//    // this gives us a nice high-res plot while avoiding more than one point per pixel.
//
//    var maximum = container.outerWidth() / 2 || 300;
//
//    //
//
//    var data = [];
//
//    function getRandomData() {
//
//        if (data.length) {
//            data = data.slice(1);
//        }
//
//        while (data.length < maximum) {
//            var previous = data.length ? data[data.length - 1] : 50;
//            var y = previous + Math.random() * 10 - 5;
//            data.push(y < 0 ? 0 : y > 100 ? 100 : y);
//        }
//
//        // zip the generated y values with the x values
//
//        var res = [];
//        for (var i = 0; i < data.length; ++i) {
//            res.push([i, data[i]])
//        }
//
//        return res;
//    }
//
//    //
//
//    series = [{
//        data: getRandomData(),
//        lines: {
//            fill: true
//        }
//    }];
//
//    //
//
//    var plot = $.plot(container, series, {
//        grid: {
//            borderWidth: 1,
//            minBorderMargin: 20,
//            labelMargin: 10,
//            backgroundColor: {
//                colors: ["#fff", "#e4f4f4"]
//            },
//            margin: {
//                top: 8,
//                bottom: 20,
//                left: 20
//            },
//            markings: function(axes) {
//                var markings = [];
//                var xaxis = axes.xaxis;
//                for (var x = Math.floor(xaxis.min); x < xaxis.max; x += xaxis.tickSize * 2) {
//                    markings.push({
//                        xaxis: {
//                            from: x,
//                            to: x + xaxis.tickSize
//                        },
//                        color: "rgba(232, 232, 255, 0.2)"
//                    });
//                }
//                return markings;
//            }
//        },
//        xaxis: {
//            tickFormatter: function() {
//                return "";
//            }
//        },
//        yaxis: {
//            min: 0,
//            max: 110
//        },
//        legend: {
//            show: true
//        }
//    });
//
//    // Update the random dataset at 25FPS for a smoothly-animating chart
//
//    setInterval(function updateRandom() {
//        series[0].data = getRandomData();
//        plot.setData(series);
//        plot.draw();
//    }, 40);
//
//});
//
////Flot Pie Chart
//$(function() {
//
//    var data = [{
//        label: "已使用",
//        data: 3
//    }, {
//        label: "未使用",
//        data: 7
//    }];
//
//    var plotObj = $.plot($("#flot-pie-chart"), data, {
//        series: {
//            pie: {
//                show: true
//            }
//        },
//        colors: ["#CB4B4B", "#5196D7"],
//        grid: {
//            hoverable: true
//        },
//        tooltip: true,
//        tooltipOpts: {
//            content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
//            shifts: {
//                x: 20,
//                y: 0
//            },
//            defaultTheme: false
//        }
//    });
//
//});
//
////Flot Pie Chart
//$(function() {
//
//    var data = [{
//        label: "node1",
//        data: 1
//    }, {
//        label: "node2",
//        data: 3
//    }, {
//        label: "node3",
//        data: 9
//    }, {
//        label: "node4",
//        data: 20
//    }];
//
//    var plotObj = $.plot($("#flot-pie-chart-nodes"), data, {
//        series: {
//            pie: {
//                show: true
//            }
//        },
//        grid: {
//            hoverable: true
//        },
//        tooltip: true,
//        tooltipOpts: {
//            content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
//            shifts: {
//                x: 20,
//                y: 0
//            },
//            defaultTheme: false
//        }
//    });
//
//});
//
//
////Flot Line Chart
//$(document).ready(function() {
//    console.log("document ready");
//    var offset = 0;
//    plot();
//
//    function plot() {
//        var sin = [],
//            cos = [];
//        for (var i = 0; i < 12; i += 0.2) {
//            sin.push([i, Math.sin(i + offset)]);
//            cos.push([i, Math.cos(i + offset)]);
//        }
//
//        var options = {
//            series: {
//                lines: {
//                    show: true
//                },
//                points: {
//                    show: true
//                }
//            },
//            grid: {
//                hoverable: true //IMPORTANT! this is needed for tooltip to work
//            },
//            yaxis: {
//                min: -1.2,
//                max: 1.2
//            },
//            tooltip: true,
//            tooltipOpts: {
//                content: "'%s' of %x.1 is %y.4",
//                shifts: {
//                    x: -60,
//                    y: 25
//                }
//            }
//        };
//
//        var plotObj = $.plot($("#flot-line-chart"), [{
//                data: sin,
//                label: "sin(x)"
//            }, {
//                data: cos,
//                label: "cos(x)"
//            }],
//            options);
//    }
//});