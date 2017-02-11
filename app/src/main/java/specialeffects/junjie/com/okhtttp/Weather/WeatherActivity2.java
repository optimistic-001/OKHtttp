package specialeffects.junjie.com.okhtttp.Weather;

import android.app.Activity;
import android.os.Bundle;

import com.jn.chart.charts.LineChart;
import com.jn.chart.data.Entry;
import com.jn.chart.manager.LineChartManager;

import java.util.ArrayList;

import specialeffects.junjie.com.okhtttp.R;

/**
 * Created by JIE on 2016/10/27.
 */

public class WeatherActivity2 extends Activity {
    private LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_layout2);
        mLineChart = (LineChart) findViewById(R.id.chart);
        initView();
    }

    private void initView() {
        //设置图表的描述
        mLineChart.setDescription("全省移网");
        //设置x轴的数据
        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            xValues.add("" + i);
        }
        //设置y轴的数据
        ArrayList<Entry> yValue = new ArrayList<>();
        yValue.add(new Entry(Float.valueOf("32"), 1));
        yValue.add(new Entry(Float.valueOf("30"), 2));
        yValue.add(new Entry(Float.valueOf("25"), 3));
        yValue.add(new Entry(Float.valueOf("21"), 4));
        yValue.add(new Entry(Float.valueOf("22"), 5));
        yValue.add(new Entry(Float.valueOf("26"), 6));
        yValue.add(new Entry(Float.valueOf("23"), 7));
        //设置折线的名称
        LineChartManager.setLineName("当月值");
        //创建一条折线的图表
        LineChartManager.initSingleLineChart(this,mLineChart,xValues,yValue);
        //设置第二条折线y轴的数据
        ArrayList<Entry> yValue1 = new ArrayList<>();
        yValue1.add(new Entry(Float.valueOf("22"), 1));
        yValue1.add(new Entry(Float.valueOf("23"), 2));
        yValue1.add(new Entry(Float.valueOf("25"), 3));
        yValue1.add(new Entry(Float.valueOf("26"), 4));
        yValue1.add(new Entry(Float.valueOf("27"), 5));
        yValue1.add(new Entry(Float.valueOf("28"), 6));
        yValue1.add(new Entry(Float.valueOf("30"), 7));
        LineChartManager.setLineName1("上月值");
        //创建两条折线的图表
//        LineChartManager.initDoubleLineChart(this, mLineChart, xValues, yValue, yValue1);
    }

}
