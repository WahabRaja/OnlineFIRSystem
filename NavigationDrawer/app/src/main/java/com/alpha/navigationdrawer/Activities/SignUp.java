package com.alpha.navigationdrawer.Activities;

import android.content.Intent;
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
import com.alpha.navigationdrawer.Api.SignUpModel;
import com.alpha.navigationdrawer.MainActivity;
import com.alpha.navigationdrawer.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by haseeb on 11/21/2017.
 */

public class SignUp extends AppCompatActivity  {
    Button b1;
    int Action;
    String policeStation;
    EditText name,cnic,email,mobileno,mothername,password,policeSpinner;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        b1=(Button)findViewById(R.id.signUpBtn);
        name=(EditText)findViewById(R.id.name);
        cnic=(EditText)findViewById(R.id.cnic);
        email=(EditText)findViewById(R.id.email);
        mobileno=(EditText)findViewById(R.id.mobileNo);
        mothername=(EditText)findViewById(R.id.motherName);
        password=(EditText)findViewById(R.id.password);
        //Spinner
        final Spinner spinner=(Spinner)findViewById(R.id.spinner_view);
        final ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.Planet,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Select any planet!");
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // adapterView.getItemAtPosition(i);
                policeStation= (String) adapterView.getItemAtPosition(i);
                Toast.makeText(SignUp.this, "Police station name: " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                //    Log.d("spinner result",adapterView.getTag().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(SignUp.this,"Select location" , Toast.LENGTH_SHORT).show();
            }
        });







        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUp.this, "Signing In", Toast.LENGTH_SHORT).show();
                Toast.makeText(SignUp.this, "Result value "+name.getText().toString()+" "+cnic.getText().toString()+
                        email.getText().toString()+mothername.getText().toString()+
                        mobileno.getText().toString()+policeStation+
                        password.getText().toString(),Toast.LENGTH_LONG).show();
               Action=1;

                ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
                Call<SignUpModel> call=apiInterface.RegisterUser(name.getText().toString(),cnic.getText().toString(),
                        email.getText().toString(),mothername.getText().toString(),
                        mobileno.getText().toString(),policeStation,
                        password.getText().toString(),Action);
                call.enqueue(new Callback<SignUpModel>() {
                    @Override
                    public void onResponse(Call<SignUpModel> call, Response<SignUpModel> response) {
                        SignUpModel res=response.body();
                        Toast.makeText(SignUp.this, "Response: "+res, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(SignUp.this, MainActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<SignUpModel> call, Throwable t) {
                        Toast.makeText(SignUp.this, "Exception: "+t.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("Exception: ",t.toString());
                    }
                });
            }
        });
    }
}
