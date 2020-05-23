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

public class SuccessBuyTicketAct extends AppCompatActivity {

    Button btn_view_ticket, btn_my_dashboard;
    Animation app_splash, top_to_bottom, btn_to_top;
    TextView app_title, app_subtitle;
    ImageView icon_success_ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_buy_ticket);

        btn_view_ticket = findViewById(R.id.btn_view_ticket);
        btn_my_dashboard = findViewById(R.id.btn_my_dashboard);
        app_title = findViewById(R.id.app_title);
        app_subtitle = findViewById(R.id.app_subtitle);
        icon_success_ticket = findViewById(R.id.icon_success_ticket);

        // load Animation
        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        top_to_bottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
        btn_to_top = AnimationUtils.loadAnimation(this, R.anim.btn_to_top);

        //run animation
        icon_success_ticket.startAnimation(app_splash);

        app_title.startAnimation(top_to_bottom);
        app_subtitle.startAnimation(top_to_bottom);

        btn_view_ticket.startAnimation(btn_to_top);
        btn_my_dashboard.startAnimation(btn_to_top);

        btn_view_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMyProfile = new Intent(SuccessBuyTicketAct.this, MyProfileAct.class);
                startActivity(goToMyProfile);
            }
        });

        btn_my_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMyDashboard = new Intent(SuccessBuyTicketAct.this, HomeAct.class);
                startActivity(goToMyDashboard);
            }
        });
    }
}
