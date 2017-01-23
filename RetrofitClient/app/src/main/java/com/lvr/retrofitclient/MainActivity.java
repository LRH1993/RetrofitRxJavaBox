package com.lvr.retrofitclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lvr.retrofitclient.client.BaseObserver;
import com.lvr.retrofitclient.client.ExceptionHandle;
import com.lvr.retrofitclient.client.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private View btn_get, btn_post;
    String url1 = "http://img0.imgtn.bdimg.com/it/u=205441424,1768829584&fm=21&gp=0.jpg";
    String url2 = "http://wap.dl.pinyin.sogou.com/wapdl/hole/201607/05/SogouInput_android_v8.3_sweb.apk?frm=new_pcjs_index";
    String url3 = "http://apk.hiapk.com/web/api.do?qt=8051&id=723";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_get = findViewById(R.id.bt_get);
        btn_post = findViewById(R.id.bt_post);
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> maps = new HashMap<String, String>();
                maps.put("ip", "21.22.11.33");

                //"http://ip.taobao.com/service/getIpInfo.php?ip=21.22.11.33";
                RetrofitClient.getInstance(MainActivity.this).createBaseApi().get("service/getIpInfo.php"
                        , maps, new BaseObserver<IpResult>(MainActivity.this) {


                            @Override
                            protected void hideDialog() {

                            }

                            @Override
                            protected void showDialog() {

                            }

                            @Override
                            public void onError(ExceptionHandle.ResponeThrowable e) {
                                Log.e("Exception", e.getMessage());
                                Toast.makeText(MainActivity.this, "网络异常", Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onNext(IpResult responseBody) {

                                Toast.makeText(MainActivity.this, responseBody.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });


        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> maps = new HashMap<String, String>();

                maps.put("ip", "21.22.11.33");
                //"http://ip.taobao.com/service/getIpInfo.php?ip=21.22.11.33";
                RetrofitClient.getInstance(MainActivity.this).createBaseApi().post("service/getIpInfo.php"
                        , maps, new BaseObserver<IpResult>(MainActivity.this) {

                            @Override
                            public void onNext(IpResult result) {
                                Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_LONG).show();

                            }

                            @Override
                            protected void hideDialog() {

                            }

                            @Override
                            protected void showDialog() {

                            }

                            @Override
                            public void onError(ExceptionHandle.ResponeThrowable e) {
                                Log.e("Lyk", e.getMessage());
                                if(e.code==1002){
                                    Toast.makeText(MainActivity.this, "网络异常", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
            }
        });
    }


}
