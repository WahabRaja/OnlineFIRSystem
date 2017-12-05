package com.alpha.navigationdrawer.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alpha.navigationdrawer.Api.ApiClient;
import com.alpha.navigationdrawer.Api.ApiInterface;
import com.alpha.navigationdrawer.Api.FIrLaunch;
import com.alpha.navigationdrawer.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by haseeb on 11/22/2017.
 */

public class RegisterFIR extends AppCompatActivity {
    String policeStation;
    String CrimeType,prefString;
    Button launchFIR;
    EditText des,time;
    SharedPreferences ss;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerfir);
        des=(EditText)findViewById(R.id.des);
        time=(EditText)findViewById(R.id.dateNtime);
        launchFIR=(Button)findViewById(R.id.launchfir);
        ss=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        editor=ss.edit();
        final Spinner spinner=(Spinner)findViewById(R.id.spinner_view);
        final ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                    R.array.Planet,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Select Respective Police Station!");
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // adapterView.getItemAtPosition(i);
                policeStation= (String) adapterView.getItemAtPosition(i);
                Toast.makeText(RegisterFIR.this, "Police station name: " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                //   Log.d("spinner result",adapterView.getTag().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(RegisterFIR.this,"Select location" , Toast.LENGTH_SHORT).show();
            }
        });

        final Spinner Crimespinner=(Spinner)findViewById(R.id.crime_spinner);
        final ArrayAdapter<CharSequence> Arrayadapter=ArrayAdapter.createFromResource(this,
                R.array.CrimeType,android.R.layout.simple_spinner_item);
        Arrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Crimespinner.setPrompt("Select Crime Type");
        Crimespinner.setAdapter(Arrayadapter);
        Crimespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // adapterView.getItemAtPosition(i);
                CrimeType= (String) adapterView.getItemAtPosition(i);
                Toast.makeText(RegisterFIR.this, "Crime Type: "+ CrimeType, Toast.LENGTH_SHORT).show();
                //    Log.d("Crimespinner result",adapterView.getTag().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(RegisterFIR.this,"Select location" , Toast.LENGTH_SHORT).show();
            }
        });
        launchFIR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
                prefString=ss.getString("cnic",null);
                Call<FIrLaunch> call=apiInterface.FirLaunch(des.getText().toString(),time.getText().toString(),
                        policeStation,CrimeType,Integer.parseInt(prefString));
                call.enqueue(new Callback<FIrLaunch>() {
                    @Override
                    public void onResponse(Call<FIrLaunch> call, Response<FIrLaunch> response) {
                        FIrLaunch res=response.body();
                        Toast.makeText(RegisterFIR.this, "Response "+res.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("Tag", "onResponse: "+res.toString());
                        des.setText(null);
                        time.setText(null);
                    }

                    @Override
                    public void onFailure(Call<FIrLaunch> call, Throwable t) {
                        Toast.makeText(RegisterFIR.this, "Exception "+t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }


}
