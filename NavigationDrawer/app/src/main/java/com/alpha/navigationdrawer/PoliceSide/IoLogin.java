package com.alpha.navigationdrawer.PoliceSide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alpha.navigationdrawer.R;

/**
 * Created by haseeb on 11/26/2017.
 */

public class IoLogin extends AppCompatActivity {
    Button ViewFIr,CurrentFir,CurrentInvestigation,Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.io_login);
        ViewFIr=(Button)findViewById(R.id.vfir);
        CurrentFir=(Button)findViewById(R.id.cfir);
        CurrentInvestigation=(Button)findViewById(R.id.cInvestigation);
        Logout=(Button)findViewById(R.id.logout);
        ViewFIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(IoLogin.this, ViewFir.class);
                startActivity(intent);
            }
        });
        CurrentFir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        CurrentInvestigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
