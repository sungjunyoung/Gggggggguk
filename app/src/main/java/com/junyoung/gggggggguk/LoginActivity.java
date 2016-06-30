package com.junyoung.gggggggguk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText = (EditText) findViewById(R.id.nickname);
        intent = new Intent(this,MainActivity.class);
    }

    public void clickConfirm(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        if(editText.getText().toString().equals("")){
            Toast.makeText(this,"닉네임을 입력해 주세요",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            User.setNickname(editText.getText().toString());
            startActivity(intent);
        }
    }
}
