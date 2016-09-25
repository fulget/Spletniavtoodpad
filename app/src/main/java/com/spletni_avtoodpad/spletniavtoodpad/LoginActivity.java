package com.spletni_avtoodpad.spletniavtoodpad;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.kosalgeek.genasync12.*;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG = this.getClass().getName();
    Button logBt;
    EditText email, password;
    CheckBox checkBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        logBt = (Button)findViewById(R.id.logBt);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        checkBox = (CheckBox)findViewById(R.id.checkBox);

        logBt.setOnClickListener(this);


            }

    @Override
    public void onClick(View v) {
        HashMap data = new HashMap();
        data.put("txtEmail", email.getText().toString());
        data.put("txtPassword", password.getText().toString() );

        PostResponseAsyncTask task = new PostResponseAsyncTask(LoginActivity.this, data, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d(TAG, s);
                if (s.contains("success")) {
                    Intent in = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(in);

                }
            }
        });
        task.execute("garage.spletni-avtoodpad.si/APP/index.php");

    }
}
