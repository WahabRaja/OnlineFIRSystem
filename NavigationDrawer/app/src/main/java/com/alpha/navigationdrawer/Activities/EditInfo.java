package com.alpha.navigationdrawer.Activities;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.alpha.navigationdrawer.Api.EditInfoModel;
import com.alpha.navigationdrawer.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by haseeb on 11/22/2017.
 */

public class EditInfo extends AppCompatActivity {
    String policeStation,prefString;
    EditText Email,Contact;
    Button update;
    SharedPreferences ss;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editinfo);
        Email=(EditText)findViewById(R.id.Email);
        Contact=(EditText)findViewById(R.id.contactno);
        update=(Button)findViewById(R.id.UpdateData);
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
                Toast.makeText(EditInfo.this, "Police station name: " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                //   Log.d("spinner result",adapterView.getTag().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(EditInfo.this,"Select location" , Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefString=ss.getString("cnic",null);
                Toast.makeText(EditInfo.this, "Data uploaded", Toast.LENGTH_SHORT).show();
                ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
                Call<EditInfoModel> call=apiInterface.UpdateInfo(Contact.getText().toString(),Email.getText().toString(),policeStation,prefString,3);
                call.enqueue(new Callback<EditInfoModel>() {
                    @Override
                    public void onResponse(Call<EditInfoModel> call, Response<EditInfoModel> response) {
                        EditInfoModel res=response.body();
                        Toast.makeText(EditInfo.this, "Response: "+res.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<EditInfoModel> call, Throwable t) {
                        Toast.makeText(EditInfo.this, "Exception: "+t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                finish();
            }
        });
    }
}
