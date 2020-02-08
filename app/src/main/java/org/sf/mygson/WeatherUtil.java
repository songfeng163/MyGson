package org.sf.mygson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 处理天气信息的工具类
 * @author 宋锋
 */
public class WeatherUtil {
    // 解析json格式数据，返回天气集合
    public static List<WeatherInfo> getWeatherListFromJson(InputStream is) throws Exception{
        byte[] buffer = new byte[is.available()];
        is.read(buffer);
        String json = new String(buffer, "utf-8");
        // 使用Gson库来进行解析
        Gson gson = new Gson();

        Type listType = new TypeToken<List<WeatherInfo>>(){}.getType();
        List<WeatherInfo> list = gson.fromJson(json, listType);

        return list;
    }
}
