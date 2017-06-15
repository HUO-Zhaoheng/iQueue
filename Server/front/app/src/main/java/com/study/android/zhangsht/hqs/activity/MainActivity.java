package com.study.android.zhangsht.hqs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.study.android.zhangsht.hqs.R;
import com.study.android.zhangsht.hqs.fragment.HomeFragment;
import com.study.android.zhangsht.hqs.fragment.OfficeFragment;
import com.study.android.zhangsht.hqs.fragment.QueueFragment;
import com.study.android.zhangsht.hqs.utils.HttpCallbackListener;
import com.study.android.zhangsht.hqs.utils.HttpTool;

import org.json.JSONObject;

import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity {
    private Fragment officeFragment, queueFragment, homeFragment;
    private Fragment currentFragment;

    private String officeId;

    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container, officeFragment).commit();
        currentFragment = officeFragment;

        Intent intent = getIntent();
        if (intent != null) {
            String status = intent.getStringExtra("status");
            if (status != null) {
                officeId = intent.getStringExtra("officeId");
                if (status.equals("login")) {
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    initOffice();
                } else if (status.equals("register")) {
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                    initOffice();
                }
                Toast.makeText(this, officeId, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void init() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        officeFragment = new OfficeFragment();
        homeFragment = new HomeFragment();
        queueFragment = new QueueFragment();
        handler = new MyHandler();
    }

    private void initOffice() {
        String url = "http://192.168.137.1:8080/iQueue/initData";
        try {
            String params = "opcode=" + URLEncoder.encode("initData", "UTF-8") +
                    "&officeId=" + URLEncoder.encode(officeId, "UTF-8");

            HttpTool.sendRequest(url, params, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    Message message = new Message();
                    message.what = 0;
                    message.obj = response.toString();
                    handler.sendMessage(message);
                }

                @Override
                public void onError(Exception e) {
                    Toast.makeText(MainActivity.this, "初始化失败", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("HttpURLConnection.POST", "login 请求失败");
        }
    }

    class MyHandler extends Handler {
        public MyHandler() {
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String response = (String) msg.obj;
                    String office = "";
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        //第二步：因为单条数据，所以用jsonObject.getString方法直接取出对应键值
                        office = jsonObject.getString("office");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //String office = parseItemJSONWithJSONObject(response);
                    Toast.makeText(MainActivity.this, office, Toast.LENGTH_LONG).show();
                   /* if (status.equals("success")) {

                    } else {
                        Toast.makeText(MainActivity.this, status, Toast.LENGTH_SHORT).show();
                    }*/
                    break;
            }
        }
    }

    private String parseItemJSONWithJSONObject(String jsonData) {
        String office = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            //第二步：因为单条数据，所以用jsonObject.getString方法直接取出对应键值
            office = jsonObject.getString("office");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return office == null ? "" : office;


        /*StringBuffer sb =new StringBuffer();
        try {
            //第一步：将从网络字符串jsonData字符串装入JSONObject，即JSONObject
            JSONObject jsonObject = new JSONObject(jsonData);
            //第二步：因为多条数据，所以将"取出来的、要遍历的"字段装入JSONArray（这里要遍历data字段）
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            //第三步：循环遍历，依次取出JSONObject对象
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                String time = jsonObject2.getString("time");
                String ftime = jsonObject2.getString("ftime");
                String context = jsonObject2.getString("context");
                sb.append("time: " + time+"  "+"ftime: " + ftime+"\n"+"context: " + context+"\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();*/
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_office:
                    switchFragment(officeFragment);
                    return true;
                case R.id.navigation_queue:
                    switchFragment(queueFragment);
                    return true;
                case R.id.navigation_home:
                    switchFragment(homeFragment);
                    return true;
            }
            return false;
        }
    };


    private void switchFragment(Fragment fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if (currentFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(currentFragment)
                        .add(R.id.fragment_container, fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager().beginTransaction().hide(currentFragment).show(fragment).commit();
            }
            currentFragment = fragment;
        }
    }
}
