package com.example.ticketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation app_splash, btn_to_top;
    ImageView app_logo;
    TextView app_subtitle;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUsernameLocal();

        // Load animation
        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        btn_to_top = AnimationUtils.loadAnimation(this, R.anim.btn_to_top);

        // Load Element
        app_logo = findViewById(R.id.app_logo);
        app_subtitle = findViewById(R.id.app_subtitle);

        // run animation
        app_logo.startAnimation(app_splash);
        app_subtitle.startAnimation(btn_to_top);

    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
        if (username_key_new.isEmpty()){
            // setting timer untuk 1 detik
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // merubaha activity ke activity lain
                    Intent gogetstarted = new Intent(MainActivity.this, GetStartedAct.class);
                    startActivity(gogetstarted);
                    finish();
                }
            }, 2000); // 2000 ms = 2s
        }
        else {
            // setting timer untuk 1 detik
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // merubaha activity ke activity lain
                    Intent goGetHome = new Intent(MainActivity.this, HomeAct.class);
                    startActivity(goGetHome);
                    finish();
                }
            }, 2000); // 2000 ms = 2s
        }
    }
}
