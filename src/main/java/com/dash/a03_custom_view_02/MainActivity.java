package com.dash.a03_custom_view_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dash.a03_custom_view_02.bean.DetalBean;
import com.dash.a03_custom_view_02.util.OkHttp3Util;
import com.dash.a03_custom_view_02.view.CombineView;
import com.dash.a03_custom_view_02.view.CustomBanner;
import com.dash.a03_custom_view_02.view.SwitchButton;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private CustomBanner customBanner;
    private SwitchButton switchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*CombineView combineView = findViewById(R.id.combine_view);

        combineView.setText("是否开启夜间模式");
        combineView.setChecked(true);*/

        /*customBanner = findViewById(R.id.custom_banner);

        //请求数据进行解析展示
        getDataFromNet();*/

        switchButton = findViewById(R.id.switch_button);
        switchButton.setBackImage(R.drawable.switch_background);
        switchButton.setSlideImage(R.drawable.slide_button_background);
        switchButton.setState(false);

    }

    private void getDataFromNet() {

        OkHttp3Util.doGet("https://www.zhaoapi.cn/product/getProductDetail?Pid=1&source=android", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();

                    final DetalBean detalBean = new Gson().fromJson(json,DetalBean.class);

                    final List<String> list = new ArrayList<>();

                    String[] images = detalBean.getData().getImages().split("\\|");
                    for (int i = 0;i<images.length;i++){
                        list.add(images[i]);
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //设置时间
                            customBanner.setTimeSecond(5);

                            //设置显示轮播
                            customBanner.setImageUrls(list);

                            //设置点击事件
                            customBanner.setClickListner(new CustomBanner.OnClickLisner() {
                                @Override
                                public void onItemClick(int position) {
                                    Toast.makeText(MainActivity.this,"点击",Toast.LENGTH_SHORT).show();


                                }
                            });

                        }
                    });

                }
            }
        });

    }
}
