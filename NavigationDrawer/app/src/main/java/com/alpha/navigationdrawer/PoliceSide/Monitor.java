package com.alpha.navigationdrawer.PoliceSide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alpha.navigationdrawer.R;

/**
 * Created by haseeb on 12/4/2017.
 */

public class Monitor extends AppCompatActivity {
    Button Initial,Final;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitor);
        Initial=(Button)findViewById(R.id.initalrep);
        Final=(Button)findViewById(R.id.finalrep);
        Initial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
