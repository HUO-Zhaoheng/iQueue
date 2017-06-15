package com.study.android.zhangsht.hqs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Handler;
import android.os.Message;

import com.study.android.zhangsht.hqs.R;
import com.study.android.zhangsht.hqs.utils.HttpCallbackListener;
import com.study.android.zhangsht.hqs.utils.HttpTool;

import org.json.JSONObject;

import java.net.URLEncoder;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText rOffice;
    private EditText rUsername;
    private EditText rPassword;
    private EditText rConfirmPwd;

    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
    }

    private void init() {
        rOffice = (EditText) findViewById(R.id.officeId);
        rUsername = (EditText) findViewById(R.id.r_username);
        rPassword = (EditText) findViewById(R.id.r_password);
        rConfirmPwd = (EditText) findViewById(R.id.r_confirm_pwd);
        findViewById(R.id.sign_up_button).setOnClickListener(this);
        handler = new MyHandler();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_up_button:
                register();
                break;
        }
    }

    private void register() {
        String url = "http://192.168.137.1:8080/iQueue/user";
        try {
            String params = "opcode=" + URLEncoder.encode("register", "UTF-8") +
                    "&officeId=" + URLEncoder.encode(rOffice.getText().toString(), "UTF-8") +
                    "&username=" + URLEncoder.encode(rUsername.getText().toString(), "UTF-8")
                    + "&password=" + URLEncoder.encode(rPassword.getText().toString(), "UTF-8");

            HttpTool.sendRequest(url, params, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    Message message = new Message();
                    message.what = 1;
                    message.obj = response.toString();
                    handler.sendMessage(message);
                }

                @Override
                public void onError(Exception e) {
                    Toast.makeText(SignUpActivity.this, "登陆请求失败", Toast.LENGTH_SHORT).show();
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
                case 1:
                    String response = (String) msg.obj;
                    StartActivityWithData(response);
                    break;
            }
        }
    }

    private void StartActivityWithData(final String response) {
        String status = "";
        String officeId = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            //第二步：因为单条数据，所以用jsonObject.getString方法直接取出对应键值
            status = jsonObject.getString("status");
            officeId = jsonObject.getString("oid");
        } catch (Exception e) {
            Toast.makeText(SignUpActivity.this, "响应解析失败", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        if (status.equals("success")) {
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            intent.putExtra("status", "register");
            intent.putExtra("officeId", officeId);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(SignUpActivity.this, status, Toast.LENGTH_SHORT).show();
        }
    }


}
