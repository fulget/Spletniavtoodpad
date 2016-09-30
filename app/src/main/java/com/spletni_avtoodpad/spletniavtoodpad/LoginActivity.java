package com.spletni_avtoodpad.spletniavtoodpad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.kosalgeek.android.md5simply.MD5;
import com.kosalgeek.genasync12.*;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    final String TAG = this.getClass().getName();
    Button logBt;
    EditText email, password;
    CheckBox checkBox;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    boolean checkFlag;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        logBt = (Button) findViewById(R.id.logBt);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(this);
        checkFlag = checkBox.isChecked();
        Log.d(TAG, "checkFlag: " + checkFlag);

        logBt.setOnClickListener(this);

        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        editor = pref.edit();

        //
        String email = pref.getString("email", "");
        String password = pref.getString("password", "");
        Log.d(TAG, pref.getString("password", ""));

        HashMap data = new HashMap();
        data.put("txtEmail", email);
        data.put("txtPassword", password);

        if (!(email.equals("") && password.equals(""))) {
            PostResponseAsyncTask task = new PostResponseAsyncTask(LoginActivity.this, data,
                    new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            Log.d(TAG, s);
                            if (s.contains("success")) {
                                Intent in = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(in);
                            }
                        }
                    });

            task.execute("http://garage.spletni-avtoodpad.si/APP/index.php");
        }

    }

    @Override
    public void onClick(View v) {
        HashMap data = new HashMap();
        data.put("txtEmail", email.getText().toString());
        data.put("txtPassword", MD5.encrypt( password.getText().toString() ));

        PostResponseAsyncTask task = new PostResponseAsyncTask(LoginActivity.this, data,
                new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d(TAG, s);
                if (s.contains("success")) {

                    if (checkFlag) {
                        editor.putString("email", email.getText().toString());
                        editor.putString("password", password.getText().toString());
                        editor.apply();
                    }

                        Intent in = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(in);


                }
            }
        });
        task.execute("http://garage.spletni-avtoodpad.si/APP/index.php");

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
       checkFlag = isChecked;
        Log.d(TAG, "checkFlag: " + checkFlag);
    }
}
