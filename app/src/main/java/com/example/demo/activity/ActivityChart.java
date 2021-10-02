package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.demo.R;
import com.example.demo.adapter.ChartMarkerView;
import com.example.demo.base.BaseActivityTwo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ActivityChart extends BaseActivityTwo{

    LineChart mLineChart ,mLineChart1;



    LineChart line_chart1;
    LineChart line_chart2;

    BarChart bar_chart1;
    BarChart bar_chart2;
    PieChart pie_chart1;
    PieChart pie_chart2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        setBackBtn();
        setTitle("Chart");
        mLineChart1 = (LineChart) findViewById(R.id.mLineChart1);
        mLineChart = (LineChart) findViewById(R.id.mLineChart);


        show_mLineChart1();
        showChart();

        //折线图（单）
        line_chart1 =  findViewById(R.id.line_chart1);
        show_line_chart_1();
        //折线图（双）
        line_chart2 =  findViewById(R.id.line_chart2);
        show_line_chart_2();

        //柱状图（单）
        bar_chart1 =  findViewById(R.id.Bar_chat1);
        show_bar_chart_1();
        //柱状图（双）
        bar_chart2 =  findViewById(R.id.Bar_chat2);
        show_bar_chart_2();

        //饼状图实心
        pie_chart1 =  findViewById(R.id.pie_chat1);
        show_pie_chart_1();
       //饼状图空心
        pie_chart2=  findViewById(R.id.pie_chat2);
        show_pie_chart_2();





    }

    //折线图
    private void show_line_chart_1() {
        LineChartManager lineChartManager=new LineChartManager(line_chart1);
        List<Float> xAxisValues=new ArrayList<>();
        List<Float> yAxisValues=new ArrayList<>();
        xAxisValues.add(0.0f);
        xAxisValues.add(1.0f);
        xAxisValues.add(2.0f);
        xAxisValues.add(3.0f);
        xAxisValues.add(4.0f);
        xAxisValues.add(5.0f);
        yAxisValues.add(50f);
        yAxisValues.add(60f);
        yAxisValues.add(70f);
        yAxisValues.add(40f);
        yAxisValues.add(80f);
        yAxisValues.add(60f);
        lineChartManager.showLineChart(xAxisValues,yAxisValues,"", Color.parseColor("#da6268"));
    }
    private void show_line_chart_2(){
        LineChartManager lineChartManager=new LineChartManager(line_chart2);
        //设置x轴的数据
        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            xValues.add((float) i);
        }

        //设置y轴的数据()
        List<List<Float>> yValues = new ArrayList<>();

        List<Float> y1Value = new ArrayList<>();
        List<Float> y2Value = new ArrayList<>();

        List<String> lableNameList = new ArrayList<>();
        lableNameList.add("指数1");
        lableNameList.add("指数2");

        for (int j = 0; j <= 8; j++) {
            float value = (float) (Math.random() * 80);
            y1Value.add(value);
            y2Value.add(value-5);
        }
        yValues.add(y1Value);
        yValues.add(y2Value);

        List<Integer> colorList = new ArrayList<>();
        colorList.add(Color.parseColor("#6785f2"));
        colorList.add(Color.parseColor("#eecc44"));
        lineChartManager.showLineChart(xValues, yValues, lableNameList, colorList);
        lineChartManager.setDescription("");
    }
    //柱状图设置数据
    private void show_bar_chart_1() {
        BarChartManager barChartManager = new BarChartManager(bar_chart1);
        List<BarEntry> yVals = new ArrayList<>();
        yVals.add(new BarEntry(1f, 80f));
        yVals.add(new BarEntry(2f, 50f));
        yVals.add(new BarEntry(3f, 60f));
        yVals.add(new BarEntry(4f, 60f));
        yVals.add(new BarEntry(5f, 70f));
        yVals.add(new BarEntry(6f, 80f));
        String label = "";
        barChartManager.showBarChart(yVals, label, Color.parseColor("#00ff00"));
    }
    private void show_bar_chart_2() {
        BarChartManager barChartManager = new BarChartManager(bar_chart2);
        List<Float> xAxisValues = new ArrayList<>();
        List<List<Float>> yAxisValues = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        List<Integer> colours = new ArrayList<>();
        List<Float> x1 = new ArrayList<>();
        List<Float> x2 = new ArrayList<>();
        xAxisValues.add(1.0f);
        xAxisValues.add(2.0f);
        xAxisValues.add(3.0f);
        xAxisValues.add(4.0f);
        xAxisValues.add(5.0f);
        x1.add(10f);
        x1.add(20f);
        x1.add(30f);
        x1.add(40f);
        x1.add(50f);
        x2.add(50f);
        x2.add(40f);
        x2.add(30f);
        x2.add(20f);
        x2.add(10f);
        yAxisValues.add(x1);
        yAxisValues.add(x2);
        labels.add("");
        labels.add("");
        colours.add(Color.parseColor("#00ff00"));
        colours.add(Color.parseColor("#0000ff"));
        barChartManager.showMoreBarChart(xAxisValues, yAxisValues, labels, colours);
        barChartManager.setXAxis(5, 0, 5);
    }

    //设置数据
    private void show_pie_chart_1() {
//设置每份所占数量
        List<PieEntry> yvals = new ArrayList<>();
        yvals.add(new PieEntry(2.0f, "本科"));
        yvals.add(new PieEntry(3.0f, "硕士"));
        yvals.add(new PieEntry(4.0f, "博士"));
        yvals.add(new PieEntry(5.0f, "大专"));
        yvals.add(new PieEntry(1.0f, "其他"));
// 设置每份的颜色
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#6785f2"));
        colors.add(Color.parseColor("#675cf2"));
        colors.add(Color.parseColor("#496cef"));
        colors.add(Color.parseColor("#aa63fa"));
        colors.add(Color.parseColor("#f5a658"));
        PieChartManagger pieChartManagger=new PieChartManagger(pie_chart1);
        pieChartManagger.showRingPieChart(yvals,colors);
    }
    private void show_pie_chart_2() {
        // 设置每份所占数量
        List<PieEntry> yvals = new ArrayList<>();
        yvals.add(new PieEntry(2.0f, "汉族"));
        yvals.add(new PieEntry(3.0f, "回族"));
        yvals.add(new PieEntry(4.0f, "壮族"));
        yvals.add(new PieEntry(5.0f, "维吾尔族"));
        yvals.add(new PieEntry(6.0f, "土家族"));
        //设置每份的颜色
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#6785f2"));
        colors.add(Color.parseColor("#675cf2"));
        colors.add(Color.parseColor("#496cef"));
        colors.add(Color.parseColor("#aa63fa"));
        colors.add(Color.parseColor("#58a9f5"));
        PieChartManagger pieChartManagger = new PieChartManagger(pie_chart2);
        pieChartManagger.showSolidPieChart(yvals, colors);
    }


    private void show_mLineChart1(){
        //  1.chart格式设置
        mLineChart1.setDrawGridBackground(false);//无背景网格
        mLineChart1.setDrawBorders(false);

        //图表描述
        Description description = new Description();
        description.setText("近一周学习情况");//描述内容
        description.setTextColor(0xff000000);//描述字体颜色
        description.setTextSize(24f);//描述字体大小
        description.setTextAlign(Paint.Align.LEFT);//文字左对齐
        description.setPosition(100,100);//设置图表描述
        mLineChart.setDescription(description);

        mLineChart1.setTouchEnabled(false);//可触摸
        mLineChart1.setDragEnabled(true);//可拖动
        mLineChart1.setScaleEnabled(true);//可放缩

        // 2.获取坐标轴并进行设置
        //获取和设置X轴
        XAxis xAxis = mLineChart.getXAxis();//获取X轴
        xAxis.setEnabled(true);//设置显示X轴
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//X轴位置
        xAxis.setAxisLineWidth(2);//设置X轴宽度
        xAxis.setDrawGridLines(false);//无网格
        xAxis.setDrawAxisLine(true);//显示X轴
        /// X轴数据
        final String[] xValues = {"3.14","3.15","3.16","3.17","3.18","3.19","3.20"};
        //  给X轴设置数据
        xAxis.setValueFormatter(new ValueFormatter() {
        });

        //获取并设置Y轴
        YAxis leftYAxis = mLineChart.getAxisLeft();//获取左侧Y轴
        YAxis rightYAxis = mLineChart.getAxisRight();//获取右侧Y轴
        rightYAxis.setEnabled(false);//禁止显示右侧Y轴
        leftYAxis.setAxisLineWidth(2);
        leftYAxis.setDrawGridLines(false);
        leftYAxis.setStartAtZero(true);//设置从零开始显示

        //  3.添加数据
        ArrayList<Entry> entries1 = new ArrayList<>();//Entry就是折线图上的点
        entries1.add(new Entry(0,85));
        entries1.add(new Entry(1,88));
        entries1.add(new Entry(2,75));
        entries1.add(new Entry(3,69));
        entries1.add(new Entry(4,95));
        entries1.add(new Entry(5,77));
        entries1.add(new Entry(6,88));

        ArrayList<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(0,75));
        entries2.add(new Entry(1,88));
        entries2.add(new Entry(2,55));
        entries2.add(new Entry(3,79));
        entries2.add(new Entry(4,85));
        entries2.add(new Entry(5,97));
        entries2.add(new Entry(6,78));

        //   LineDataSet是点的集合，连成线
        LineDataSet lineDataSet1 = new LineDataSet(entries1,"Listen");
        lineDataSet1.setColor(0xff3f51b5);
        LineDataSet lineDataSet2 = new LineDataSet(entries2,"speech");
        lineDataSet2.setColor(0xffff4081);

        // 线条的集合（可以添加多条线）
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);

        //要给Chart设置的数据（将dataSets作为数据对象）
        LineData lineData = new LineData(dataSets);

        mLineChart1.setData(lineData);//设置数据
        mLineChart1.invalidate();//刷新显示
    }


    public void showChart() {
        // *************************数据转换********************* //
        float[] dataObjects = {5, 2, 3, 1, 5, 3, 7, 6, 1, 4, 6, 9, 4};
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataObjects.length; i++) {
            float data = dataObjects[i];
            entries.add(new Entry(i, data));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Label1");
        dataSet.setColors(Color.BLACK, Color.GRAY, Color.RED, Color.GREEN); // 每个点之间线的颜色，还有其他几个方法，自己看
        dataSet.setValueFormatter(new ValueFormatter() {   // 将值转换为想要显示的形式，比如，某点值为1，变为“1￥”,MP提供了三个默认的转换器，
            // LargeValueFormatter:将大数字变为带单位数字；PercentFormatter：将值转换为百分数；StackedValueFormatter，对于BarChart，是否只显示最大值图还是都显示
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return value + "￥";
            }
        });

        LineData lineData = new LineData(dataSet);
        /*List<ILineDataSet> sets = new ArrayList<>();  // 多条线
        sets.add(dataSet);
        sets.add(dataSet1);
        sets.add(dataSet2);
        LineData lineData = new LineData(sets);
        */
        mLineChart.setData(lineData);


        // **************************图表本身一般样式**************************** //
        //mLineChart.setBackgroundColor(Color.WHITE); // 整个图标的背景色
        mLineChart.setContentDescription("××表");   // 右下角的描述文本,测试并不显示
        Description description = new Description();  // 这部分是深度定制描述文本，颜色，字体等
        description.setText("××表");
        description.setTextColor(Color.RED);
        mLineChart.setDescription(description);
        mLineChart.setNoDataText("暂无数据");   // 没有数据时样式
        mLineChart.setDrawGridBackground(false);    // 绘制区域的背景（默认是一个灰色矩形背景）将绘制，默认false
        mLineChart.setGridBackgroundColor(Color.WHITE);  // 绘制区域的背景色
        mLineChart.setDrawBorders(false);    // 绘制区域边框绘制，默认false
        mLineChart.setBorderColor(Color.GREEN); // 边框颜色
        mLineChart.setBorderWidth(2);   // 边框宽度,dp
        mLineChart.setMaxVisibleValueCount(14);  // 数据点上显示的标签，最大数量，默认100。且dataSet.setDrawValues(true);必须为true。只有当数据数量小于该值才会绘制标签


        // *********************滑动相关*************************** //
        mLineChart.setTouchEnabled(true); // 所有触摸事件,默认true
        mLineChart.setDragEnabled(true);    // 可拖动,默认true
        mLineChart.setScaleEnabled(true);   // 两个轴上的缩放,X,Y分别默认为true
        mLineChart.setScaleXEnabled(true);  // X轴上的缩放,默认true
        mLineChart.setScaleYEnabled(true);  // Y轴上的缩放,默认true
        mLineChart.setPinchZoom(true);  // X,Y轴同时缩放，false则X,Y轴单独缩放,默认false
        mLineChart.setDoubleTapToZoomEnabled(true); // 双击缩放,默认true
        mLineChart.setDragDecelerationEnabled(true);    // 抬起手指，继续滑动,默认true
        mLineChart.setDragDecelerationFrictionCoef(0.9f);   // 摩擦系数,[0-1]，较大值速度会缓慢下降，0，立即停止;1,无效值，并转换为0.9999.默认0.9f.
        mLineChart.setOnChartGestureListener(new OnChartGestureListener() { // 手势监听器
            @Override
            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
                // 按下
            }

            @Override
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
                // 抬起,取消
            }

            @Override
            public void onChartLongPressed(MotionEvent me) {
                // 长按
            }

            @Override
            public void onChartDoubleTapped(MotionEvent me) {
                // 双击
            }

            @Override
            public void onChartSingleTapped(MotionEvent me) {
                // 单击
            }

            @Override
            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
                // 甩动
            }

            @Override
            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
                // 缩放
            }

            @Override
            public void onChartTranslate(MotionEvent me, float dX, float dY) {
                // 移动
            }
        });


        // ************************高亮*************************** //
        mLineChart.setHighlightPerDragEnabled(true);    // 拖拽时能否高亮（十字瞄准触摸到的点），默认true
        mLineChart.setHighlightPerTapEnabled(true); // 双击时能都高亮，默认true
        mLineChart.setMaxHighlightDistance(500);    // 最大高亮距离（dp）,点击位置距离数据点的距离超过这个距离不会高亮，默认500dp
        dataSet.setHighlightEnabled(true);  // 能否高亮,默认true
        dataSet.setDrawHighlightIndicators(true);   // 画高亮指示器,默认true
        dataSet.setDrawHorizontalHighlightIndicator(true);  // 画水平高亮指示器，默认true
        dataSet.setDrawVerticalHighlightIndicator(true);    // 垂直方向高亮指示器,默认true
        dataSet.setHighLightColor(Color.BLACK); // 高亮颜色,默认RGB(255, 187, 115)
        mLineChart.highlightValue(1, 0);    // 高亮指定值，可以指定数据集的值,还有其他几个重载方法
        mLineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // 值选择监听器
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                // 选中
            }

            @Override
            public void onNothingSelected() {
                // 未选中
            }
        });


        // *************************轴****************************** //
        // 由四个元素组成：
        // 标签：即刻度值。也可以自定义，比如时间，距离等等，下面会说一下；
        // 轴线：坐标轴；
        // 网格线：垂直于轴线对应每个值画的轴线；
        // 限制线：最值等线。
        XAxis xAxis = mLineChart.getXAxis();    // 获取X轴
        YAxis yAxis = mLineChart.getAxisLeft(); // 获取Y轴,mLineChart.getAxis(YAxis.AxisDependency.LEFT);也可以获取Y轴
        mLineChart.getAxisRight().setEnabled(false);    // 不绘制右侧的轴线
        xAxis.setEnabled(true); // 轴线是否可编辑,默认true
        xAxis.setDrawLabels(true);  // 是否绘制标签,默认true
        xAxis.setDrawAxisLine(true);    // 是否绘制坐标轴,默认true
        xAxis.setDrawGridLines(false);   // 是否绘制网格线，默认true
        xAxis.setAxisMaximum(10); // 此轴能显示的最大值；
        xAxis.resetAxisMaximum();   // 撤销最大值；
        xAxis.setAxisMinimum(1);    // 此轴显示的最小值；
        xAxis.resetAxisMinimum();   // 撤销最小值；
//        yAxis.setStartAtZero(true);  // 从0开始绘制。已弃用。使用setAxisMinimum(float)；
//        yAxis.setInverted(true); // 反转轴,默认false
        yAxis.setSpaceTop(10);   // 设置最大值到图标顶部的距离占所有数据范围的比例。默认10，y轴独有
        // 算法：比例 = （y轴最大值 - 数据最大值）/ (数据最大值 - 数据最小值) ；
        // 用途：可以通过设置该比例，使线最大最小值不会触碰到图标的边界。
        // 注意：设置一条线可能不太好看，mLineChart.getAxisRight().setSpaceTop(34)也设置比较好;同时，如果设置最小值，最大值，会影响该效果
        yAxis.setSpaceBottom(10);   // 同上，只不过是最小值距离底部比例。默认10，y轴独有
        // yAxis.setShowOnlyMinMax(true);   // 没找到。。。，true, 轴上只显示最大最小标签忽略指定的数量（setLabelCount，如果forced = false).
        yAxis.setLabelCount(4, false); // 纵轴上标签显示的数量,数字不固定。如果force = true,将会画出明确数量，但是可能值导致不均匀，默认（6，false）
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);  // 标签绘制位置。默认再坐标轴外面
        xAxis.setGranularity(1); // 设置X轴值之间最小距离。正常放大到一定地步，标签变为小数值，到一定地步，相邻标签都是一样的。这里是指定相邻标签间最小差，防止重复。
        yAxis.setGranularity(1);    // 同上
        yAxis.setGranularityEnabled(false); // 是否禁用上面颗粒度限制。默认false
        // 轴颜色
        yAxis.setTextColor(Color.RED);  // 标签字体颜色
        yAxis.setTextSize(10);    // 标签字体大小，dp，6-24之间，默认为10dp
        yAxis.setTypeface(null);    // 标签字体
        yAxis.setGridColor(Color.GRAY);    // 网格线颜色，默认GRAY
        yAxis.setGridLineWidth(1);    // 网格线宽度，dp，默认1dp
        yAxis.setAxisLineColor(Color.RED);  // 坐标轴颜色，默认GRAY.测试到一个bug，假如左侧线只有1dp，
        // 那么如果x轴有线且有网格线，当刻度拉的正好位置时会覆盖到y轴的轴线，变为X轴网格线颜色，结局办法是，要么不画轴线，要么就是坐标轴稍微宽点
        xAxis.setAxisLineColor(Color.RED);
        yAxis.setAxisLineWidth(2);  // 坐标轴线宽度，dp，默认1dp
        yAxis.enableGridDashedLine(20, 10, 1);    // 网格线为虚线，lineLength，每段实线长度,spaceLength,虚线间隔长度，phase，起始点（进过测试，最后这个参数也没看出来干啥的）
        // 限制线
        LimitLine ll = new LimitLine(6.5f, "上限"); // 创建限制线, 这个线还有一些相关的绘制属性方法，自行看一下就行，没多少东西。
        yAxis.addLimitLine(ll); // 添加限制线到轴上
        yAxis.removeLimitLine(ll);  // 移除指定的限制线,还有其他的几个移除方法
        yAxis.setDrawLimitLinesBehindData(false); // 限制线在数据之后绘制。默认为false

        // X轴更多属性
        xAxis.setLabelRotationAngle(45);   // 标签倾斜
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  // X轴绘制位置，默认是顶部

        // Y轴更多属性
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);  // 设置dataSet应绘制在Y轴的左轴还是右轴，默认LEFT
        yAxis.setDrawZeroLine(false);    // 绘制值为0的轴，默认false,其实比较有用的就是在柱形图，当有负数时，显示在0轴以下，其他的图这个可能会看到一些奇葩的效果
        yAxis.setZeroLineWidth(10);  // 0轴宽度
        yAxis.setZeroLineColor(Color.BLUE);   // 0轴颜色

        // 轴值转换显示
        xAxis.setValueFormatter(new ValueFormatter() { // 与上面值转换一样，这里就是转换出轴上label的显示。也有几个默认的，不多说了。
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value + "℃";
            }
        });


        // *********************图例***************************** //
        Legend legend = mLineChart.getLegend(); // 获取图例，但是在数据设置给chart之前是不可获取的
        legend.setEnabled(true);    // 是否绘制图例
        legend.setTextColor(Color.GRAY);    // 图例标签字体颜色，默认BLACK
        legend.setTextSize(12); // 图例标签字体大小[6,24]dp,默认10dp
        legend.setTypeface(null);   // 图例标签字体
        legend.setWordWrapEnabled(false);    // 当图例超出时是否换行适配，这个配置会降低性能，且只有图例在底部时才可以适配。默认false
        legend.setMaxSizePercent(1f); // 设置，默认0.95f,图例最大尺寸区域占图表区域之外的比例
       // legend.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);    // 图例显示位置，已弃用
        legend.setForm(Legend.LegendForm.CIRCLE);   // 设置图例的形状，SQUARE, CIRCLE 或者 LINE
        legend.setFormSize(8); // 图例图形尺寸，dp，默认8dp
        legend.setXEntrySpace(6);  // 设置水平图例间间距，默认6dp
        legend.setYEntrySpace(0);  // 设置垂直图例间间距，默认0
        legend.setFormToTextSpace(5);    // 设置图例的标签与图形之间的距离，默认5dp
        legend.setWordWrapEnabled(true);   // 图标单词是否适配。只有在底部才会有效，
        legend.setCustom(new LegendEntry[]{new LegendEntry("label1", Legend.LegendForm.CIRCLE, 10, 5, null, Color.RED),
                new LegendEntry("label2", Legend.LegendForm.CIRCLE, 10, 5, null, Color.GRAY),
                new LegendEntry("label3", Legend.LegendForm.CIRCLE, 10, 5, null, Color.RED)}); // 这个应该是之前的setCustom(int[] colors, String[] labels)方法
        // 这个方法会把前面设置的图例都去掉，重置为指定的图例。
        legend.resetCustom();   // 去掉上面方法设置的图例，然后之前dataSet中设置的会重新显示。
//        legend.setExtra(new int[]{Color.RED, Color.GRAY, Color.GREEN}, new String[]{"label1", "label2", "label3"}); // 添加图例，颜色与label数量要一致。
        // 如果前面已经在dataSet中设置了颜色，那么之前的图例就存在，这个只是添加在后面的图例，并不一定有对应数据。


        mLineChart.invalidate();    // 重绘
        // ********************其他******************************* //
        mLineChart.setLogEnabled(false);    // 是否打印日志，默认false
//        mLineChart.notifyDataSetChanged();  // 通知有值变化，重绘，一般动态添加数据时用到

        // ******************指定缩放显示范围************************* //
        // 这里要说一下，下面并不是指定其初始显示的范围，所以，很可能大家觉得没有效果。其实这几个方法目的是限制缩放时的可见范围最值。
//        mLineChart.setVisibleXRangeMaximum(6); // X轴缩小可见最大范围，这里测试有点问题，范围不是指定的，可以缩小到更多范围。
//        mLineChart.setVisibleXRangeMinimum(4);  // X轴放大最低可见范围，最小意思是，再怎么放大范围也至少要有4，但是一开始显示的时候范围可能很大。
//        mLineChart.setVisibleYRangeMaximum(4, YAxis.AxisDependency.LEFT);   // Y缩小时可见最大范围，后面是其适用的轴。测试发现两边轴都是有效的
//        mLineChart.setVisibleYRangeMinimum(2, YAxis.AxisDependency.LEFT);   // Y轴放大时可见最小范围。
//        mLineChart.setVisibleYRange(3, 5, YAxis.AxisDependency.LEFT);   // y轴缩放时可见最小和最大范围。但是测试发现不能放大3的范围，但是也是符合这个限制的
//        mLineChart.setVisibleXRange(3, 6);  // X轴缩放时可见最小和最大范围。测试也有点问题
//        mLineChart.setViewPortOffsets(10, 0, 10, 0);    // 图表绘制区的偏移量设置,这个会忽略MP的自动计算偏移。
        // 比如，自动时，图例与绘制区是分开的，但是自己写就可能重和在一起。慎用
//        mLineChart.resetViewPortOffsets();  // 重置上面的偏移量设置。
//        mLineChart.setExtraOffsets(10, 0, 10, 0);   // 这个与上面的区别是不会忽略其自己计算的偏移。

        // **************************移动******************************** //
//        mLineChart.fitScreen(); // 重置所有缩放与拖动，使图标完全符合其边界
//        mLineChart.moveViewToX(30); // 想指定向偏移，比如原本显示前三个点，现在显示后三个，如果没有缩放其实看不出啥效果

//        mLineChart.moveViewTo(30, 10, YAxis.AxisDependency.LEFT);    // 向指定方向偏移,如果没有缩放其实看不出啥效果,后面的轴没啥效果
//        mLineChart.moveViewToAnimated(30, 10, YAxis.AxisDependency.LEFT, 2000); // 同上面那个，但是有动画效果
//        mLineChart.centerViewTo(30, 10, YAxis.AxisDependency.LEFT); // 将视图中心移动到指定位置，也是要缩放才有效果
//        mLineChart.centerViewToAnimated(30, 10, YAxis.AxisDependency.LEFT, 2000); // 同上面那个，但是有动画效果


        // ****************************自动缩放********************************** //
        // 这里的缩放效果会收到setVisibleXRangeMaximum等范围影响，
//        mLineChart.zoomIn();    // 自动放大1.4倍，没看出效果
//        mLineChart.zoomOut();   // 自动缩小0.7倍，没看出效果
//        mLineChart.zoom(2f, 2f, 2, 3, YAxis.AxisDependency.LEFT);
//        mLineChart.zoomAndCenterAnimated(1.4f, 1.4f, 2, 3, YAxis.AxisDependency.LEFT, 3000);    // 缩放，有动画，报了个空指针。。。


        // ************************动画************************************** //
        /*mLineChart.animateX(3000);  // 数据从左到右动画依次显示
        mLineChart.animateY(3000);  // 数据从下到上动画依次显示*/
//        mLineChart.animateXY(3000, 3000);   // 上面两个的结合
      //  mLineChart.animateX(3000, Easing.EasingOption.EaseInQuad); // 动画播放随时间变化的速率，有点像插值器。后面这个有的不能用


        // **************************所有数据样式************************************ //
        mLineChart.setMarker(new ChartMarkerView(this, R.layout.item_chart, "温度:", "℃"));    // 点击数据点显示的pop，有俩默认的，MarkerImage：一张图片，MarkerView:一个layout布局,也可以自己定义.这里这个是我自定义的一个MarkerView。

        lineData.setValueTextColor(Color.RED);   // 该条线的
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.BLACK);
        colors.add(Color.GRAY);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        lineData.setValueTextColors(colors); // 字体添加颜色，按顺序给数据上色，不足则重复使用,也可以在单个dataSet上添加
        lineData.setValueTextSize(12);   // 文字大小
        lineData.setValueTypeface(null); // 文字字体
        lineData.setValueFormatter(new ValueFormatter() {  // 所有数据显示的数据值
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return value + "￥";
            }
        });
        lineData.setDrawValues(true);   // 绘制每个点的值
        // 上面这些都是data集合中的相关属性，也可以针对每个dataSet来设置


        // **************************图表本身特殊样式******************************** //
        mLineChart.setAutoScaleMinMaxEnabled(false);    // y轴是否自动缩放；当缩放时，y轴的显示会自动根据x轴范围内数据的最大最小值而调整。财务报表比较有用，默认false
        mLineChart.setKeepPositionOnRotation(false); // 设置当屏幕方向变化时，是否保留之前的缩放与滚动位置，默认：false


        // *****************************其他的chart************************* //
        // 下面只有barChart(柱状图)有用
        BarChart mBarChart = (BarChart) findViewById(R.id.mBarChart);
        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, 1));
        barEntries.add(new BarEntry(1, 2));
        barEntries.add(new BarEntry(2, 3));
        barEntries.add(new BarEntry(3, -1));
        BarDataSet iBarDataSet = new BarDataSet(barEntries, "bar label");
        iBarDataSet.setColors(colors);
        iBarDataSet.setValueTextColors(colors);
        BarData barData = new BarData(iBarDataSet); // 可以添加多个set，即可化成group组
        mBarChart.setData(barData);
//        mBarChart.groupBars(1980f, 20, 0);  // 设置group组间隔
        mBarChart.setFitBars(true);    // 在bar开头结尾两边添加一般bar宽的留白
        mBarChart.setDrawValueAboveBar(false);    // 所有值都绘制在柱形外顶部，而不是柱形内顶部。默认true
        mBarChart.setDrawBarShadow(false);   // 柱形阴影，一般有值被绘制，但是值到顶部的位置为空，这个方法设置也画这部分，但是性能下降约40%，默认false
        // setDrawValuesForWholeStack(boolean enabled);  // 没有该方法。。。是否绘制堆积的每个值，还是只是画堆积的总值，
        // setDrawHighlightArrow(true);  // 没有该方法。。。是否绘制高亮箭头

        // 下面只有PieChart(饼状图)有用
        PieChart mPieChart = (PieChart) findViewById(R.id.mPieChart);
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(1, "11"));
        pieEntries.add(new PieEntry(2, "22"));
        pieEntries.add(new PieEntry(3, "33"));
        PieDataSet iPieDataSet = new PieDataSet(pieEntries, "pie label");
        iPieDataSet.setColors(colors);
        iPieDataSet.setValueTextColors(colors);
        iPieDataSet.setSliceSpace(3);   // 每块之间的距离
        PieData pieData = new PieData(iPieDataSet);
        mPieChart.setData(pieData);
        mPieChart.setDrawSliceText(true);   // : 将X值绘制到饼状图环切片内,否则不显示。默认true,已弃用，用下面setDrawEntryLabels()
        mPieChart.setDrawEntryLabels(true);   // 同上,默认true，记住颜色和环不要一样，否则会显示不出来
        mPieChart.setUsePercentValues(true);    // 表内数据用百分比替代，而不是原先的值。并且ValueFormatter中提供的值也是该百分比的。默认false
        mPieChart.setCenterText("asc"); // 圆环中心的文字，会自动适配不会被覆盖
        mPieChart.setCenterTextRadiusPercent(100f); // 中心文本边界框矩形半径比例，默认是100%.
        mPieChart.setHoleRadius(60);  // 设置中心圆半径占整个饼形图圆半径（图表半径）的百分比。默认50%
        mPieChart.setTransparentCircleRadius(70);   // 设置环形与中心圆之间的透明圆环半径占图表半径的百分比。默认55%（比如，中心圆为50%占比，而透明环设置为55%占比，要去掉中心圆的占比，也就是环只有5%的占比）
        mPieChart.setTransparentCircleColor(Color.RED); // 上述透明圆环的颜色
        mPieChart.setTransparentCircleAlpha(50);    // 上述透明圆环的透明度[0-255]，默认100
        mPieChart.setMaxAngle(360);    // 设置整个饼形图的角度，默认是360°即一个整圆，也可以设置为弧，这样现实的值也会重新计算

        // 下面只有RadarChart(雷达图)有用
        RadarChart mRadarChart = (RadarChart) findViewById(R.id.mRadarChart);
        List<RadarEntry> radarEntries = new ArrayList<>();
        radarEntries.add(new RadarEntry(1, "111"));
        radarEntries.add(new RadarEntry(2, "222"));
        radarEntries.add(new RadarEntry(3, "333"));
        radarEntries.add(new RadarEntry(4, "444"));
        radarEntries.add(new RadarEntry(5, "555"));
        RadarDataSet iRadarDataSet = new RadarDataSet(radarEntries, "bar label");
        iRadarDataSet.setColors(colors);
        iRadarDataSet.setValueTextColors(colors);
        RadarData radarData = new RadarData(iRadarDataSet);
        mRadarChart.setData(radarData);
        mRadarChart.setSkipWebLineCount(8); // 允许不绘制从中心发出的线，当线多时较有用。默认为0

        // *************************其他********************************* //
        // 上面介绍了MP的大部分常用的api，基本可以满足绝大部分的需求，还有部分不常用的暂时不说了，以后用的着再下面补充
//        mLineChart.clear(); // 清空
    }


    public class LineChartManager {
        private LineChart lineChart;
        private YAxis leftAxis;   //左边Y轴
        private YAxis rightAxis;  //右边Y轴
        private XAxis xAxis;      //X轴

        public LineChartManager(LineChart mLineChart) {
            this.lineChart = mLineChart;
            leftAxis = lineChart.getAxisLeft();
            rightAxis = lineChart.getAxisRight();
            xAxis = lineChart.getXAxis();
        }

        /**
         * 初始化LineChart
         */
        private void initLineChart(boolean legendShow) {
            lineChart.setDrawGridBackground(false);
            //显示边界
            lineChart.setDrawBorders(false);

            //设置动画效果
            lineChart.animateX(1000);

            lineChart.setTouchEnabled(true); // 所有触摸事件,默认true
            lineChart.setDragEnabled(false);    // 可拖动,默认true
            lineChart.setScaleEnabled(false);   // 两个轴上的缩放,X,Y分别默认为true
            lineChart.setScaleXEnabled(false);  // X轴上的缩放,默认true
            lineChart.setScaleYEnabled(false);  // Y轴上的缩放,默认true
            lineChart.setPinchZoom(false);  // X,Y轴同时缩放，false则X,Y轴单独缩放,默认false
            lineChart.setDoubleTapToZoomEnabled(false); // 双击缩放,默认true
            lineChart.setDragDecelerationEnabled(true);    // 抬起手指，继续滑动,默认true

            //折线图例 标签 设置
            Legend legend = lineChart.getLegend();
            if (legendShow) {
                legend.setDrawInside(false);
                legend.setFormSize(8);
                legend.setXEntrySpace(7f);
                legend.setYEntrySpace(0f);
                legend.setYOffset(0f);
                // legend.setForm(Legend.LegendForm.SQUARE);
                // 文字的大小
                legend.setTextSize(12);
                //显示位置
                legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
                legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
                legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);

            } else {
                legend.setForm(Legend.LegendForm.NONE);
            }


            //XY轴的设置
            //X轴设置显示位置在底部
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setAxisMinimum(0f);
            xAxis.setGranularity(1f);
            // 不绘制网格线

            xAxis.setDrawGridLines(true);
            xAxis.setGridColor(Color.parseColor("#d8d8d8"));
            //设置最后和第一个标签不超出x轴
            xAxis.setAvoidFirstLastClipping(true);
//        设置线的宽度
            xAxis.setAxisLineWidth(1.0f);
            xAxis.setAxisLineColor(Color.parseColor("#d5d5d5"));

            //保证Y轴从0开始，不然会上移一点
            leftAxis.setAxisMinimum(0f);
            // 显示数字但不显示线
            leftAxis.setDrawAxisLine(true);
            leftAxis.setTextColor(Color.parseColor("#d5d5d5"));

            leftAxis.setDrawGridLines(true);
//        设置网格为虚线
            leftAxis.enableGridDashedLine(10f, 10f, 0f);
            leftAxis.setGridColor(Color.parseColor("#d8d8d8"));
            leftAxis.setAxisLineColor(Color.parseColor("#d5d5d5"));
            rightAxis.setAxisMinimum(0f);
            // 线跟数据都不显示
            rightAxis.setEnabled(false); //右侧Y轴不显示


        }

        /**
         * 初始化曲线 每一个LineDataSet代表一条线
         *
         * @param lineDataSet
         * @param color
         * @param mode        折线图是否填充
         */
        private void initLineDataSet(LineDataSet lineDataSet, int color, boolean mode) {
            lineDataSet.setColor(color);
            lineDataSet.setCircleColor(color);
            lineDataSet.setLineWidth(2f);
            lineDataSet.setCircleRadius(3f);
            //设置曲线值的圆点是实心还是空心
            lineDataSet.setDrawCircleHole(true);
            lineDataSet.setValueTextSize(9f);

            // 不显示具体值
            lineDataSet.setDrawValues(false);

//        lineDataSet.setHighlightEnabled(false);
            //设置折线图填充
            lineDataSet.setDrawFilled(mode);
            //设置填充颜色
            lineDataSet.setFillColor(color);
//        lineDataSet.setFormLineWidth(2f);
//        lineDataSet.setFormSize(15.f);
            //线模式为圆滑曲线（默认折线）
            lineDataSet.setMode(LineDataSet.Mode.LINEAR);
        }

        /**
         * 跟showLineCharDouble配对
         *
         * @param lineDataSet
         * @param color
         * @param mode
         */
        private void initLineCusDataSet(LineDataSet lineDataSet, int color, boolean mode) {
            initLineDataSet(lineDataSet, color, mode);
            lineDataSet.setDrawValues(false);
            lineDataSet.setDrawCircles(false);
        }

        /**
         * 展示折线图(一条)
         *
         * @param xAxisValues
         * @param yAxisValues
         * @param label
         * @param color
         */
        public void showLineChart(List<Float> xAxisValues, List<Float> yAxisValues, String label, int color) {
            initLineChart(false);
            ArrayList<Entry> entries = new ArrayList<>();
            for (int i = 0; i < xAxisValues.size(); i++) {
                entries.add(new Entry(xAxisValues.get(i), yAxisValues.get(i)));
            }
            // 每一个LineDataSet代表一条线
            LineDataSet lineDataSet = new LineDataSet(entries, label);
            initLineDataSet(lineDataSet, color, true);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDataSet);
            LineData data = new LineData(dataSets);
            //设置X轴的刻度数
//        xAxis.setLabelCount(xAxisValues.size(), false);
            xAxis.setTextColor(Color.parseColor("#a1a1a1"));
            //文字倾斜度
//        xAxis.setLabelRotationAngle(-45);
            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    if (value == 0) {
                        return "阜成门";
                    }
                    if (value == 2) {
                        return "国贸";
                    }
                    if (value == 3) {
                        return "积水潭";
                    }
                    if (value == 4) {
                        return "三元桥";
                    }
                    if (value == 5) {
                        return "西直门";
                    }
                    return "西直门";
                }
            });

            lineChart.setData(data);

        }


        /**
         * 展示折线图(一条)
         *
         * @param xAxisValues
         * @param yAxisValues
         * @param label
         * @param color
         */
        public void showAirLineChart(List<Float> xAxisValues, List<Float> yAxisValues, String label, int color) {
            initLineChart(false);
            ArrayList<Entry> entries = new ArrayList<>();
            for (int i = 0; i < xAxisValues.size(); i++) {
                entries.add(new Entry(xAxisValues.get(i), yAxisValues.get(i)));
            }
            // 每一个LineDataSet代表一条线
            LineDataSet lineDataSet = new LineDataSet(entries, label);
            initLineDataSet(lineDataSet, color, true);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDataSet);
            LineData data = new LineData(dataSets);
            //设置X轴的刻度数
//        xAxis.setLabelCount(xAxisValues.size(), false);
            xAxis.setTextColor(Color.parseColor("#a1a1a1"));
            //文字倾斜度
//        xAxis.setLabelRotationAngle(-45);
            lineChart.setData(data);

        }


        /**
         * 展示线性图(多条)
         *
         * @param xAxisValues
         * @param yAxisValues 多条曲线Y轴数据集合的集合
         * @param labels
         * @param colours
         */
        public void showLineChart(List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours) {
            initLineChart(true);
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            for (int i = 0; i < yAxisValues.size(); i++) {
                ArrayList<Entry> entries = new ArrayList<>();
                for (int j = 0; j < yAxisValues.get(i).size(); j++) {
                    if (j >= xAxisValues.size()) {
                        j = xAxisValues.size() - 1;
                    }
                    entries.add(new Entry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
                }
                LineDataSet lineDataSet = new LineDataSet(entries, labels.get(i));

                initLineDataSet(lineDataSet, colours.get(i), true);
                lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
                dataSets.add(lineDataSet);
            }
            LineData data = new LineData(dataSets);
            xAxis.setLabelCount(xAxisValues.size(), true);
            String[] xValues = {"6:00", "9:00", "12:00", "15:00", "18:00"};
            xAxis.setValueFormatter(new ValueFormatter(xValues));

            lineChart.setData(data);
        }

        public class ValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter implements IAxisValueFormatter {

            private String[] xValues;

            public ValueFormatter(String[] xValues) {
                this.xValues = xValues;
            }

            public ValueFormatter() {

            }

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xValues[(int) value];
            }

        }

        /**
         * 只显示两条 其中一条是影 定制化
         *
         * @param xAxisValues
         * @param yAxisValues 只有两条数据
         * @param labels
         * @param colours
         */
        public void showLineCharDouble(List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours) {
            initLineChart(true);
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            //
            for (int i = 0; i < 2; i++) {
                ArrayList<Entry> entries = new ArrayList<>();
                for (int j = 0; j < yAxisValues.get(i).size(); j++) {
                    if (j >= xAxisValues.size()) {
                        j = xAxisValues.size() - 1;
                    }
                    entries.add(new Entry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
                }
                LineDataSet lineDataSet = new LineDataSet(entries, labels.get(i));

                initLineCusDataSet(lineDataSet, colours.get(i), false);
//            if (i == 1) {
//                initLineCusDataSet(lineDataSet, colours.get(i), false);
//            } else {
//                initLineDataSet(lineDataSet, colours.get(i), false);
//            }

                dataSets.add(lineDataSet);
            }
            LineData data = new LineData(dataSets);
            xAxis.setLabelCount(xAxisValues.size(), true);
            lineChart.setData(data);
        }

        /**
         * 设置Y轴值
         *
         * @param max
         * @param min
         * @param labelCount
         */
        public void setYAxis(float max, float min, int labelCount) {
            if (max < min) {
                return;
            }
            leftAxis.setAxisMaximum(max);
            leftAxis.setAxisMinimum(min);
            leftAxis.setLabelCount(labelCount, false);

            rightAxis.setAxisMaximum(max);
            rightAxis.setAxisMinimum(min);
            rightAxis.setLabelCount(labelCount, false);
            lineChart.invalidate();
        }

        /**
         * 设置X轴的值
         *
         * @param max
         * @param min
         * @param labelCount
         */
        public void setXAxis(float max, float min, int labelCount) {
            xAxis.setAxisMaximum(max);
            xAxis.setAxisMinimum(min);
            xAxis.setLabelCount(labelCount, true);

            lineChart.invalidate();
        }

        /**
         * 设置高限制线
         *
         * @param high
         * @param name
         */
        public void setHightLimitLine(float high, String name, int color) {
            if (name == null) {
                name = "高限制线";
            }
            LimitLine hightLimit = new LimitLine(high, name);
            hightLimit.setLineWidth(2f);
            hightLimit.setTextSize(10f);
            hightLimit.setLineColor(color);
            hightLimit.setTextColor(color);
            leftAxis.addLimitLine(hightLimit);
            lineChart.invalidate();
        }

        /**
         * 设置低限制线
         *
         * @param low
         * @param name
         */
        public void setLowLimitLine(int low, String name) {
            if (name == null) {
                name = "低限制线";
            }
            LimitLine hightLimit = new LimitLine(low, name);
            hightLimit.setLineWidth(4f);
            hightLimit.setTextSize(10f);
            leftAxis.addLimitLine(hightLimit);
            lineChart.invalidate();
        }

        /**
         * 设置描述信息
         *
         * @param str
         */
        public void setDescription(String str) {
            Description description = new Description();
            description.setText(str);
            lineChart.setDescription(description);
            lineChart.invalidate();
        }
    }

    public class BarChartManager {

        private BarChart mBarChart;
        private YAxis leftAxis;
        private YAxis rightAxis;
        private XAxis xAxis;
        private DecimalFormat mFormat;

        public BarChartManager(BarChart barChart) {
            this.mBarChart = barChart;
            leftAxis = mBarChart.getAxisLeft();
            rightAxis = mBarChart.getAxisRight();
            xAxis = mBarChart.getXAxis();
        }

        /**
         * 初始化LineChart
         */
        private void initLineChart() {
            mFormat = new DecimalFormat("#,###.##");
            //背景颜色
            //mBarChart.setBackgroundColor(Color.WHITE);
            //是否显示网格背景
            mBarChart.setDrawGridBackground(false);
            //显示每条背景阴影
            mBarChart.setDrawBarShadow(false);
            //设置图标边框的颜色
            mBarChart.setBorderColor(Color.parseColor("#ff0000"));
//        mBarChart.setHighlightFullBarEnabled(false);
            mBarChart.setTouchEnabled(true); // 所有触摸事件,默认true
            mBarChart.setDragEnabled(true);    // 可拖动,默认true
            mBarChart.setScaleEnabled(false);   // 两个轴上的缩放,X,Y分别默认为true
            mBarChart.setScaleXEnabled(false);  // X轴上的缩放,默认true
            mBarChart.setScaleYEnabled(false);  // Y轴上的缩放,默认true
            mBarChart.setPinchZoom(false);  // X,Y轴同时缩放，false则X,Y轴单独缩放,默认false
            mBarChart.setDoubleTapToZoomEnabled(false); // 双击缩放,默认true
            mBarChart.setDragDecelerationEnabled(true);    // 抬起手指，继续滑动,默认true



            //显示边界
            mBarChart.setDrawBorders(false);
            //设置XY动画效果
            //mBarChart.animateY(1000, Easing.EasingOption.Linear);
            //mBarChart.animateX(1000, Easing.EasingOption.Linear);
//      不显示描述信息
            mBarChart.getDescription().setEnabled(false);
//         图例设置
            Legend legend = mBarChart.getLegend();
            //不显示图例
            legend.setForm(Legend.LegendForm.NONE);
//        图例文字的大小
            legend.setTextSize(11f);
            //显示位置
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            legend.setDrawInside(false);
            //XY轴的设置
            //X轴设置显示位置在底部
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//      X轴最小间距
            xAxis.setGranularity(1f);
//      不绘制网格线
            xAxis.setDrawGridLines(false);
//      X轴字体样式
            xAxis.setTypeface(Typeface.DEFAULT_BOLD);
//      设置X轴文字剧中对齐
            xAxis.setCenterAxisLabels(true);
//
//       保证Y轴从0开始，不然会上移一点
            leftAxis.setDrawGridLines(false);
            rightAxis.setAxisMinimum(0f);
            leftAxis.setAxisMinimum(0f);
            leftAxis.setTextColor(Color.parseColor("#d5d5d5"));
//        // 线跟数据都不显示
            rightAxis.setEnabled(false); //右侧Y轴不显示
        }

        /**
         * 展示柱状图(一条)
         */
        public void showBarChart(List<BarEntry> yVals, String label, int color) {
            initLineChart();

            // 每一个BarDataSet代表一类柱状图
            BarDataSet barDataSet = new BarDataSet(yVals, label);
            barDataSet.setColor(color);
            //是否显示顶部的值
            barDataSet.setDrawValues(true);
//        文字的大小
            barDataSet.setValueTextSize(9f);

            barDataSet.setFormLineWidth(1f);
            barDataSet.setFormSize(15.0f);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(barDataSet);
            BarData data = new BarData(dataSets);
//      设置宽度
            data.setBarWidth(0.3f);
            //设置X轴的刻度数
            String[] xValues = {"东城", "西城", "朝阳", "丰台", "石景山", "海淀区", "海淀区"};
            String[] yValues = {"91%", "92%", "93%", "94%", "95%", "96%"};
            xAxis.setLabelCount(yVals.size() + 1, true);
            xAxis.setDrawLabels(true);
            ValueFormatter xAxisFormatter = new XAxisValueFormatter(xValues);
            xAxis.setValueFormatter(xAxisFormatter);
            xAxis.setTextColor(Color.parseColor("#d5d5d5"));
            xAxis.setAxisLineColor(Color.parseColor("#d5d5d5"));
            ValueFormatter custom = new MyYAxisValueFormatter(yValues);
            leftAxis.setValueFormatter(custom);
//        leftAxis.setLabelCount(yValues.length + 1, false);
            leftAxis.setAxisLineColor(Color.parseColor("#d5d5d5"));
//        设置Y轴的最小值和最大值
            leftAxis.setAxisMaximum(80f);
            leftAxis.setAxisMinimum(50f);
            mBarChart.setData(data);
        }


        public class MyYAxisValueFormatter extends ValueFormatter implements IAxisValueFormatter {

            private String[] xValues;

            public MyYAxisValueFormatter(String[] yValues) {
                xValues = yValues;
            }

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
//            Log.e("TAG", "xValues[(int) value]====="+xValues[(int) value]);
                return mFormat.format(value) + "%";
            }
        }


        public class XAxisValueFormatter extends ValueFormatter implements IAxisValueFormatter {

            private String[] xValues;

            public XAxisValueFormatter(String[] xValues) {
                this.xValues = xValues;
            }

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Log.e("TAG", "============"+value);
                return xValues[(int) value];
            }

        }

        /**
         * 展示柱状图(多条)
         * +-+
         *
         * @param xAxisValues
         * @param yAxisValues
         * @param labels
         * @param colours
         */
        public void showMoreBarChart(final List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours) {
            initLineChart();
            BarData data = new BarData();
            for (int i = 0; i < yAxisValues.size(); i++) {
                ArrayList<BarEntry> entries = new ArrayList<>();
                for (int j = 0; j < yAxisValues.get(i).size(); j++) {

                    entries.add(new BarEntry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
                }
                BarDataSet barDataSet = new BarDataSet(entries, labels.get(i));

                barDataSet.setColor(colours.get(i));
                barDataSet.setValueTextColor(colours.get(i));
                barDataSet.setValueTextSize(10f);
                barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
                data.addDataSet(barDataSet);
            }
            int amount = yAxisValues.size();

            float groupSpace = 0.3f; //柱状图组之间的间距
            float barSpace = (float) ((1 - 0.12) / amount / 10); // x4 DataSet
            float barWidth = (float) ((1 - 0.3) / amount / 10 * 9); // x4 DataSet

            // (0.2 + 0.02) * 4 + 0.08 = 1.00 -> interval per "group"
            xAxis.setLabelCount(xAxisValues.size() - 1, false);
            data.setBarWidth(barWidth);
            final String[] xValues = {"小学", "初中", "高中", "专科", "本科"};
            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    for (int i=0;i<xAxisValues.size();i++){
                        if(value==(xAxisValues.get(i)-1)) {
                            return xValues[i];
                        }
                    }
                    return "";
                }
            });
            data.groupBars(0, groupSpace, barSpace);
            mBarChart.setData(data);
        }

        /**
         * 设置Y轴值
         *
         * @param max
         * @param min
         * @param labelCount
         */
        public void setYAxis(float max, float min, int labelCount) {
            if (max < min) {
                return;
            }
            leftAxis.setAxisMaximum(max);
            leftAxis.setAxisMinimum(min);
            leftAxis.setLabelCount(labelCount, false);

            rightAxis.setAxisMaximum(max);
            rightAxis.setAxisMinimum(min);
            rightAxis.setLabelCount(labelCount, false);
            mBarChart.invalidate();
        }

        /**
         * 设置X轴的值
         *
         * @param max
         * @param min
         * @param labelCount
         */
        public void setXAxis(float max, float min, int labelCount) {
            xAxis.setAxisMaximum(max);
            xAxis.setAxisMinimum(min);
            xAxis.setLabelCount(labelCount, false);

            mBarChart.invalidate();
        }

        /**
         * 设置高限制线
         *
         * @param high
         * @param name
         */
        public void setHightLimitLine(float high, String name, int color) {
            if (name == null) {
                name = "高限制线";
            }
            LimitLine hightLimit = new LimitLine(high, name);
            hightLimit.setLineWidth(4f);
            hightLimit.setTextSize(10f);
            hightLimit.setLineColor(color);
            hightLimit.setTextColor(color);
            leftAxis.addLimitLine(hightLimit);
            mBarChart.invalidate();
        }

        /**
         * 设置低限制线
         *
         * @param low
         * @param name
         */
        public void setLowLimitLine(int low, String name) {
            if (name == null) {
                name = "低限制线";
            }
            LimitLine hightLimit = new LimitLine(low, name);
            hightLimit.setLineWidth(4f);
            hightLimit.setTextSize(10f);
            leftAxis.addLimitLine(hightLimit);
            mBarChart.invalidate();
        }

        /**
         * 设置描述信息
         *
         * @param str
         */
        public void setDescription(String str) {
            Description description = new Description();
            description.setText(str);
            mBarChart.setDescription(description);
            mBarChart.invalidate();
        }
    }

    public class PieChartManagger {

        public PieChart pieChart;

        public PieChartManagger(PieChart pieChart) {
            this.pieChart = pieChart;
            initPieChart();
        }

        //初始化
        private void initPieChart() {
            //  是否显示中间的洞
            pieChart.setDrawHoleEnabled(false);
            pieChart.setHoleRadius(40f);//设置中间洞的大小

            // 半透明圈
            pieChart.setTransparentCircleRadius(30f);
            pieChart.setTransparentCircleColor(Color.WHITE); //设置半透明圆圈的颜色
            pieChart.setTransparentCircleAlpha(125); //设置半透明圆圈的透明度

            //饼状图中间可以添加文字
            pieChart.setDrawCenterText(false);
            pieChart.setCenterText("民族"); //设置中间文字
            pieChart.setCenterTextColor(Color.parseColor("#a1a1a1")); //中间问题的颜色
            pieChart.setCenterTextSizePixels(36);  //中间文字的大小px
            pieChart.setCenterTextRadiusPercent(1f);
            pieChart.setCenterTextTypeface(Typeface.DEFAULT); //中间文字的样式
            pieChart.setCenterTextOffset(0, 0); //中间文字的偏移量


            pieChart.setRotationAngle(0);// 初始旋转角度
            pieChart.setRotationEnabled(true);// 可以手动旋转
            pieChart.setUsePercentValues(true);//显示成百分比
            pieChart.getDescription().setEnabled(false); //取消右下角描述

            //是否显示每个部分的文字描述
            pieChart.setDrawEntryLabels(false);
            pieChart.setEntryLabelColor(Color.RED); //描述文字的颜色
            pieChart.setEntryLabelTextSize(14);//描述文字的大小
            pieChart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD); //描述文字的样式

            //图相对于上下左右的偏移
            pieChart.setExtraOffsets(20, 8, 75, 8);
            //图标的背景色
            pieChart.setBackgroundColor(Color.TRANSPARENT);
//        设置pieChart图表转动阻力摩擦系数[0,1]
            pieChart.setDragDecelerationFrictionCoef(0.75f);

            //获取图例
            Legend legend = pieChart.getLegend();
            legend.setOrientation(Legend.LegendOrientation.VERTICAL);  //设置图例水平显示
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); //顶部
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); //右对其

            legend.setXEntrySpace(7f);//x轴的间距
            legend.setYEntrySpace(10f); //y轴的间距
            legend.setYOffset(10f);  //图例的y偏移量
            legend.setXOffset(10f);  //图例x的偏移量
            legend.setTextColor(Color.parseColor("#a1a1a1")); //图例文字的颜色
            legend.setTextSize(13);  //图例文字的大小

        }


        /**
         * 显示实心圆
         * @param yvals
         * @param colors
         */
        public void showSolidPieChart(List<PieEntry> yvals, List<Integer> colors) {
            //数据集合
            PieDataSet dataset = new PieDataSet(yvals, "");
            //填充每个区域的颜色
            dataset.setColors(colors);
            //是否在图上显示数值
            dataset.setDrawValues(true);
//        文字的大小
            dataset.setValueTextSize(14);
//        文字的颜色
            dataset.setValueTextColor(Color.RED);
//        文字的样式
            dataset.setValueTypeface(Typeface.DEFAULT_BOLD);

//      当值位置为外边线时，表示线的前半段长度。
            dataset.setValueLinePart1Length(0.4f);
//      当值位置为外边线时，表示线的后半段长度。
            dataset.setValueLinePart2Length(0.4f);
//      当ValuePosits为OutsiDice时，指示偏移为切片大小的百分比
            dataset.setValueLinePart1OffsetPercentage(80f);
            // 当值位置为外边线时，表示线的颜色。
            dataset.setValueLineColor(Color.parseColor("#a1a1a1"));
//        设置Y值的位置是在圆内还是圆外
            dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        设置Y轴描述线和填充区域的颜色一致
            dataset.setUsingSliceColorAsValueLineColor(false);
//        设置每条之前的间隙
            dataset.setSliceSpace(0);

            //设置饼状Item被选中时变化的距离
            dataset.setSelectionShift(5f);
            //填充数据
            PieData pieData = new PieData(dataset);
//        格式化显示的数据为%百分比
            pieData.setValueFormatter(new PercentFormatter());
//        显示试图
            pieChart.setData(pieData);
        }


        /**
         * 显示圆环
         * @param yvals
         * @param colors
         */
        public void  showRingPieChart(List<PieEntry> yvals, List<Integer> colors){
            //显示为圆环
            pieChart.setDrawHoleEnabled(true);
            pieChart.setHoleRadius(85f);//设置中间洞的大小

            //数据集合
            PieDataSet dataset = new PieDataSet(yvals, "");
            //填充每个区域的颜色
            dataset.setColors(colors);
            //是否在图上显示数值
            dataset.setDrawValues(true);
//        文字的大小
            dataset.setValueTextSize(14);
//        文字的颜色
            dataset.setValueTextColor(Color.RED);
//        文字的样式
            dataset.setValueTypeface(Typeface.DEFAULT_BOLD);

//      当值位置为外边线时，表示线的前半段长度。
            dataset.setValueLinePart1Length(0.4f);
//      当值位置为外边线时，表示线的后半段长度。
            dataset.setValueLinePart2Length(0.4f);
//      当ValuePosits为OutsiDice时，指示偏移为切片大小的百分比
            dataset.setValueLinePart1OffsetPercentage(80f);
            // 当值位置为外边线时，表示线的颜色。
            dataset.setValueLineColor(Color.parseColor("#a1a1a1"));
//        设置Y值的位置是在圆内还是圆外
            dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        设置Y轴描述线和填充区域的颜色一致
            dataset.setUsingSliceColorAsValueLineColor(false);
//        设置每条之前的间隙
            dataset.setSliceSpace(0);

            //设置饼状Item被选中时变化的距离
            dataset.setSelectionShift(5f);
            //填充数据
            PieData pieData = new PieData(dataset);
//        格式化显示的数据为%百分比
            pieData.setValueFormatter(new PercentFormatter());
//        显示试图
            pieChart.setData(pieData);
        }
    }




}