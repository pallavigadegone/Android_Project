package com.example.Toy_World;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.navbar.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
                 Boolean check=  preferences.getBoolean("flag",false);
                 if(check){
                     Intent intent = new Intent(Splash.this, Homepage.class);
                     startActivity(intent);
                     finish();

                 }
                 else{
                     Intent intent = new Intent(Splash.this, MainActivity.class);
                     startActivity(intent);
                     finish();


                 }



            }
        }, 3000);
    }
}