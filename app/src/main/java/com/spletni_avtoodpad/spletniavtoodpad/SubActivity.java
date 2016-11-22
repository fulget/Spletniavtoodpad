package com.spletni_avtoodpad.spletniavtoodpad;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class SubActivity extends AppCompatActivity {
    final String TAG = this.getClass().getName();

    SharedPreferences pref;
    SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);

        Log.d(TAG, pref.getString("email",""));
        Log.d(TAG, pref.getString("password",""));

        FloatingActionButton fabLogaout = (FloatingActionButton) findViewById(R.id.fabLogaout);
        fabLogaout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                editor = pref.edit();
                editor.clear();
                editor.commit();
                Intent in = new Intent(SubActivity.this, LoginActivity.class);
                startActivity(in);
            }
        });
    }


}
