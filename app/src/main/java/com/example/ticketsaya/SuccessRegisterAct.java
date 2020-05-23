package com.example.ticketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SuccessRegisterAct extends AppCompatActivity {

    Button btn_explore;
    Animation app_splash, top_to_bottom, btn_to_top;
    ImageView icon_success;
    TextView app_tittle, app_subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_register);

        icon_success = findViewById(R.id.icon_success);
        app_tittle = findViewById(R.id.app_tittle);
        app_subtitle = findViewById(R.id.app_subtitle);
        btn_explore = findViewById(R.id.btn_explore);

        // load Animation
        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        top_to_bottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
        btn_to_top = AnimationUtils.loadAnimation(this, R.anim.btn_to_top);

        // run animation
        icon_success.startAnimation(app_splash);
        app_tittle.startAnimation(top_to_bottom);
        app_subtitle.startAnimation(top_to_bottom);
        btn_explore.startAnimation(btn_to_top);

        btn_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(SuccessRegisterAct.this, HomeAct.class);
                startActivity(goToHome);
            }
        });
    }
}
