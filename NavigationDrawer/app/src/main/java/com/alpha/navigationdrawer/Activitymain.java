package com.alpha.navigationdrawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alpha.navigationdrawer.Activities.LogIn;
import com.alpha.navigationdrawer.PoliceSide.Plogin;

/**
 * Created by haseeb on 11/29/2017.
 */

public class Activitymain extends AppCompatActivity {
    Button policeSide,UserSide;
    SharedPreferences ss;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        policeSide=(Button)findViewById(R.id.police);
        UserSide=(Button)findViewById(R.id.User);
        policeSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Activitymain.this, Plogin.class);
                startActivity(intent);

            }
        });
    UserSide.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(Activitymain.this, LogIn.class);
            startActivity(intent);
            finish();
        }
    });
    }
}
