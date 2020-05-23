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

public class GetStartedAct extends AppCompatActivity {

    Button btn_sign_in, btn_new_account_create;
    Animation top_to_bottom, btn_to_top;
    ImageView emblem_app;
    TextView intro_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        top_to_bottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
        btn_to_top = AnimationUtils.loadAnimation(this, R.anim.btn_to_top);

        // Load Element
        emblem_app = findViewById(R.id.emblem_app);
        intro_app = findViewById(R.id.intro_app);
        btn_sign_in = findViewById(R.id.btn_sign_in);
        btn_new_account_create = findViewById(R.id.btn_new_account_create);

        // run animation
        emblem_app.startAnimation(top_to_bottom);
        intro_app.startAnimation(top_to_bottom);
        btn_sign_in.startAnimation(btn_to_top);
        btn_new_account_create.startAnimation(btn_to_top);

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosign = new Intent(GetStartedAct.this, SignInAct.class);
                startActivity(gotosign);
            }
        });

        btn_new_account_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToRegisterOne = new Intent(GetStartedAct.this, RegisterOneAct.class);
                startActivity(goToRegisterOne);
            }
        });
    }
}
