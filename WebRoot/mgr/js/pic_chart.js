$(document).ready(function(e) {
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
	$.post(Mgr.rootUrl +"countMonthCommoditySales.do", {
		cid : GetQueryString("id")
	}, function(data, status) {

		if (status) {
			create(data);

		} else {
			alert('请求失败');
		}

	});

	GetSerialChart();
	MakeChart(json);
});
var json = [];

function create(data) {
	$.each(data[0], function(idx, obj) {
		json.push({
			"name" : obj,
			"value" : data[1][idx]
		});
	});
  //  json[0].value=1;
	GetSerialChart();
	linerChart();
	MakeChart(json);
}
// 柱状图
function GetSerialChart() {

	chart = new AmCharts.AmSerialChart();
	chart.dataProvider = json;
	// json数据的key
	chart.categoryField = "name";
	// 不选择
	chart.rotate = false;
	// 值越大柱状图面积越大
	chart.depth3D = 20;
	// 柱子旋转角度角度
	chart.angle = 30;
	var mCtCategoryAxis = chart.categoryAxis;
	mCtCategoryAxis.axisColor = "#efefef";
	// 背景颜色透明度
	mCtCategoryAxis.fillAlpha = 0.5;
	// 背景边框线透明度
	mCtCategoryAxis.gridAlpha = 0;
	mCtCategoryAxis.fillColor = "#efefef";
	var valueAxis = new AmCharts.ValueAxis();
	// 左边刻度线颜色
	valueAxis.axisColor = "#ccc";
	// 标题
	valueAxis.title = "3D柱状图Demo";
	// 刻度线透明度
	valueAxis.gridAlpha = 0.2;
	chart.addValueAxis(valueAxis);
	var graph = new AmCharts.AmGraph();
	graph.title = "value";
	graph.valueField = "value";
	graph.type = "column";
	// 鼠标移入提示信息
	graph.balloonText = "数据[[category]] [[value]]";
	// 边框透明度
	graph.lineAlpha = 0.3;
	// 填充颜色
	graph.fillColors = "#b9121b";
	graph.fillAlphas = 1;

	chart.addGraph(graph);

	// CURSOR
	var chartCursor = new AmCharts.ChartCursor();
	chartCursor.cursorAlpha = 0;
	chartCursor.zoomable = false;
	chartCursor.categoryBalloonEnabled = false;
	chart.addChartCursor(chartCursor);

	chart.creditsPosition = "top-right";

	// 显示在Main div中
	chart.write("cylindrical");
}
// 折线图

function linerChart() {
	var chart = new AmCharts.AmSerialChart();
	chart.dataProvider = json;
	chart.categoryField = "name";
	chart.angle = 30;
	chart.depth3D = 20;
	// 标题
	// chart.addTitle("3D折线图Demo", 15);
	var graph = new AmCharts.AmGraph();
	chart.addGraph(graph);
	graph.valueField = "value";
	// 背景颜色透明度
	graph.fillAlphas = 0.3;
	// 类型
	graph.type = "line";
	// 圆角
	graph.bullet = "round";
	// 线颜色
	graph.lineColor = "#8e3e1f";
	// 提示信息
	graph.balloonText = "[[name]]: [[value]]";
	var categoryAxis = chart.categoryAxis;
	categoryAxis.autoGridCount = false;
	categoryAxis.gridCount = json.length;
	categoryAxis.gridPosition = "start";
	chart.write("line");
}
// 饼图
// 根据json数据生成饼状图，并将饼状图显示到div中
function MakeChart(value) {
	chartData = eval(value);
	// 饼状图
	chart = new AmCharts.AmPieChart();
	chart.dataProvider = chartData;
	// 标题数据
	chart.titleField = "name";
	// 值数据
	chart.valueField = "value";
	// 边框线颜色
	chart.outlineColor = "#fff";
	// 边框线的透明度
	chart.outlineAlpha = .8;
	// 边框线的狂宽度
	chart.outlineThickness = 1;
	chart.depth3D = 20;
	chart.angle = 30;
	chart.write("pie");
}