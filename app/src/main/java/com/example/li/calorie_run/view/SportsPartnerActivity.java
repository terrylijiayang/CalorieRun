package com.example.li.calorie_run.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.li.calorie_run.R;

public class SportsPartnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_partner);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageView add=(ImageView)this.findViewById(R.id.btn_sportclub_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SportsPartnerActivity.this, SportsIssueActivity.class);
                startActivity(intent);
            }
        });

        Button btn_detail1=(Button)this.findViewById(R.id.btn_join_1);
        btn_detail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SportsPartnerActivity.this, SportDetailActivity.class);
                startActivity(intent);
            }
        });

        Button btn_detail2=(Button)this.findViewById(R.id.btn_join_2);
        btn_detail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SportsPartnerActivity.this, SportDetailActivity.class);
                startActivity(intent);
            }
        });

    }
}
