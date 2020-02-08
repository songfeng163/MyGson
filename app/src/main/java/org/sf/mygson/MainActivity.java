package org.sf.mygson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_city, tv_weather, tv_temp, tv_pm, tv_wind;
    private ImageView iv_icon;
    private Button btn_bj, btn_sh, btn_gz, btn_mh;
    private List<WeatherInfo> list;   // 全局的数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();   // 初始化控件
        initData();   // 初始化数据
    }

    // 初始化控件
    private void initView(){
        tv_city = findViewById(R.id.tv_city);
        tv_weather = findViewById(R.id.tv_weather);
        tv_temp = findViewById(R.id.tv_temp);
        tv_pm = findViewById(R.id.tv_pm);
        tv_wind = findViewById(R.id.tv_wind);
        iv_icon = findViewById(R.id.iv_icon);

        findViewById(R.id.btn_bj).setOnClickListener(this);
        findViewById(R.id.btn_sh).setOnClickListener(this);
        findViewById(R.id.btn_gz).setOnClickListener(this);
        findViewById(R.id.btn_mh).setOnClickListener(this);
    }

    // 初始化数据的方法
    private void initData(){
        try{
            InputStream is = this.getResources().openRawResource(R.raw.weather2);
            list = WeatherUtil.getWeatherListFromJson(is);

            showCityWeather("北京", R.drawable.cloud_sun);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


    // 点击事件处理
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_bj:   // 北京
                showCityWeather("北京", R.drawable.cloud_sun);
                break;
            case R.id.btn_sh:   // 上海
                showCityWeather("上海", R.drawable.sun);
                break;
            case R.id.btn_gz:   // 广州
                showCityWeather("广州", R.drawable.clouds);
                break;
            case R.id.btn_mh:   // 漠河
                showCityWeather("漠河", R.drawable.snow);
                break;
        }
    }

    /**
     * 显示城市天气的方法
     * @param city 城市名称
     * @param iconNumber 天气的图标
     */
    private void showCityWeather(String city, int iconNumber){
        for(WeatherInfo item : list){
            if(item.getName().equals(city)){
                tv_city.setText(item.getName());
                tv_temp.setText(item.getTemp());
                tv_weather.setText(item.getWeather());
                tv_pm.setText(item.getPm());
                tv_wind.setText(item.getWind());

                iv_icon.setImageResource(iconNumber);

                break;    // 跳出循环
            }
        }
    }
}
