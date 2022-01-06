package com.lxj.xpopupextdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopupext.bean.JsonBean;
import com.lxj.xpopupext.listener.CityPickerListener;
import com.lxj.xpopupext.listener.CommonPickerListener;
import com.lxj.xpopupext.listener.TimePickerListener;
import com.lxj.xpopupext.popup.CityPickerPopup;
import com.lxj.xpopupext.popup.CommonPickerPopup;
import com.lxj.xpopupext.popup.TimePickerPopup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XPopup.setPrimaryColor(getResources().getColor(R.color.colorPrimary));
        findViewById(R.id.btnTimer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar date = Calendar.getInstance();
                date.set(2000, 5, 1);
                Calendar date2 = Calendar.getInstance();
                date2.set(2020, 5, 1);
                TimePickerPopup popup = new TimePickerPopup(MainActivity.this)
//                        .setMode(TimePickerPopup.Mode.YMDHMS)
                        .setDefaultDate(date)  //设置默认选中日期
//                        .setYearRange(1990, 1999) //设置年份范围
                        .setDateRange(date, date2) //设置日期范围
                        .setTimePickerListener(new TimePickerListener() {
                            @Override
                            public void onTimeChanged(Date date) {
                                //时间改变
                            }

                            @Override
                            public void onTimeConfirm(Date date, View view) {
                                //点击确认时间
                                Toast.makeText(MainActivity.this, "选择的时间：" + date.toLocaleString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                new XPopup.Builder(MainActivity.this)
                        .borderRadius(30)
//                        .isDarkTheme(true)
                        .asCustom(popup)
                        .show();
            }
        });

        findViewById(R.id.btnCity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityPickerPopup popup = new CityPickerPopup(MainActivity.this);
                popup.setJsonBeanList(getJsonBeanList());
                popup.setCityPickerListener(new CityPickerListener() {
                    @Override
                    public void onCityConfirm(String province, String city, String area, View v) {
                        Log.e("tag", province + " - " + city + " - " + area);
                        Toast.makeText(MainActivity.this, province + " - " + city + " - " + area, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCityChange(String province, String city, String area) {
                        Log.e("tag", province + " - " + city + " - " + area);
                        Toast.makeText(MainActivity.this, province + " - " + city + " - " + area, Toast.LENGTH_SHORT).show();
                    }
                });
                new XPopup.Builder(MainActivity.this)
                        .borderRadius(30)
//                        .isDarkTheme(true)
                        .asCustom(popup)
                        .show();
            }
        });

        findViewById(R.id.btnCommon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonPickerPopup popup = new CommonPickerPopup(MainActivity.this);
                ArrayList<String> list = new ArrayList<String>();
                list.add("小猫");
                list.add("小狗");
                list.add("小羊");
                popup.setPickerData(list)
                        .setCurrentItem(1);
                popup.setCommonPickerListener(new CommonPickerListener() {
                    @Override
                    public void onItemSelected(int index, String data) {
                        Toast.makeText(MainActivity.this, "选中的是 " + data, Toast.LENGTH_SHORT).show();
                    }
                });
                new XPopup.Builder(MainActivity.this)
                        .borderRadius(30)
                        .isDarkTheme(true)
                        .asCustom(popup)
                        .show();
            }
        });
    }

    private List<JsonBean> getJsonBeanList() {
        List<JsonBean.CityBean> cityBeanList = new ArrayList<>();
        cityBeanList.add(new JsonBean.CityBean("合肥市", new ArrayList<String>(
                Arrays.asList("庐阳区",
                        "瑶海区",
                        "蜀山区",
                        "包河区",
                        "长丰县",
                        "肥东县",
                        "肥西县",
                        "其他"))));
        cityBeanList.add(new JsonBean.CityBean("六安市", new ArrayList<String>(
                Arrays.asList(
                        "金安区",
                        "裕安区",
                        "寿县",
                        "霍山县",
                        "霍邱县",
                        "舒城县",
                        "金寨县",
                        "其他"))));
        JsonBean jsonBean = new JsonBean("安徽省", cityBeanList);
        List<JsonBean> jsonBeanList = new ArrayList<>();
        jsonBeanList.add(jsonBean);
        return jsonBeanList;
    }
}