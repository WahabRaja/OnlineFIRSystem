package com.alpha.navigationdrawer.PoliceSide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alpha.navigationdrawer.Api.ApiClient;
import com.alpha.navigationdrawer.Api.ApiInterface;
import com.alpha.navigationdrawer.Api.PloginModel;
import com.alpha.navigationdrawer.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by haseeb on 11/26/2017.
 */

public class Plogin extends AppCompatActivity {
    EditText ID,Password;
    String Designation;
    Button Submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.police_login);
        ID=(EditText)findViewById(R.id.Policeid);
        Password=(EditText)findViewById(R.id.policePassword);
        final Spinner spinner= (Spinner)findViewById(R.id.desig_Spinner);
        final ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.DesignationType,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Select Designation");
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // adapterView.getItemAtPosition(i);
                Designation= (String) adapterView.getItemAtPosition(i);
                Toast.makeText(Plogin.this, "Police station name: " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();


                //    Log.d("spinner result",adapterView.getTag().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Plogin.this, "Select Designation ", Toast.LENGTH_SHORT).show();
            }

        });
        Submit=(Button)findViewById(R.id.loginBtn);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
                Call<PloginModel> call=apiInterface.LoginPolice(ID.getText().toString(),Password.getText().toString(),1);
                call.enqueue(new Callback<PloginModel>() {
                    @Override
                    public void onResponse(Call<PloginModel> call, Response<PloginModel> response) {
                        PloginModel res=response.body();

                        if(res.getResponse().equals("Data uploaded successfully")) {
                            Toast.makeText(Plogin.this, "Log in successfully", Toast.LENGTH_SHORT).show();
                            if(Designation.equals("Investigation Officer"))
                            {
                                Intent intent = new Intent(Plogin.this, IoLogin.class);
                                startActivity(intent);

                            }
                            else if(Designation.equals("SHO"))
                            {
                                Intent intent = new Intent(Plogin.this, SHOLogin.class);
                                startActivity(intent);
                            }
                            else if(Designation.equals("IG"))
                            {
                                Intent intent = new Intent(Plogin.this, IoLogin.class);
                                startActivity(intent);
                            }
                        }
                        else
                        {
                            Toast.makeText(Plogin.this, "Invalid ID or Password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PloginModel> call, Throwable t) {
                        Toast.makeText(Plogin.this, "Exception: "+t.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

}
