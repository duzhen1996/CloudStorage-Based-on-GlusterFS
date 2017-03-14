//Flot Pie Chart
$(function() {

    var data = [{
        label: "已使用",
        data: 5
    }, {
        label: "未使用",
        data: 50
    }];

    var plotObj = $.plot($("#flot-pie-chart"), data, {
        series: {
            pie: {
                show: true
            }
        },
        colors: ["#CB4B4B", "#5196D7"],
        grid: {
            hoverable: true
        },
        tooltip: true,
        tooltipOpts: {
            content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
            shifts: {
                x: 20,
                y: 0
            },
            defaultTheme: false
        }
    });

});
