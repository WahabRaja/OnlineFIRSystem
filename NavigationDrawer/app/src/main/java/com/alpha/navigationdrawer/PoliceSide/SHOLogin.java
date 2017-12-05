package com.alpha.navigationdrawer.PoliceSide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alpha.navigationdrawer.R;

/**
 * Created by haseeb on 12/4/2017.
 */

public class SHOLogin extends AppCompatActivity {
    Button MonitorFir,InfoGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sho_loggedin);
        MonitorFir=(Button)findViewById(R.id.monitorBtn);
        InfoGroup=(Button)findViewById(R.id.infoGrp);
        MonitorFir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SHOLogin.this,Monitor.class);
                startActivity(intent);
            }
        });
        InfoGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SHOLogin.this,Monitor.class);
                startActivity(intent);
            }
        });
    }
}
