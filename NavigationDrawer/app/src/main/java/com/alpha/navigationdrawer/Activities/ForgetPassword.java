package com.alpha.navigationdrawer.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alpha.navigationdrawer.Api.ApiClient;
import com.alpha.navigationdrawer.Api.ApiInterface;
import com.alpha.navigationdrawer.Api.forgetModel;
import com.alpha.navigationdrawer.MainActivity;
import com.alpha.navigationdrawer.R;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by haseeb on 11/21/2017.
 */

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener {
    Button Proceed,Submit;
    EditText verification,cnic;
    String verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpassword);
        Proceed=(Button)findViewById(R.id.proceed);
        Submit=(Button)findViewById(R.id.submit);
        cnic=(EditText)findViewById(R.id.cnic);
        verification=(EditText)findViewById(R.id.vericficationCode);
        Proceed.setOnClickListener(this);
        Submit.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.proceed:
                ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
                Call<forgetModel> call=apiInterface.ForgetUser(cnic.getText().toString(),4);
                call.enqueue(new Callback<forgetModel>() {
                    @Override
                    public void onResponse(Call<forgetModel> call, Response<forgetModel> response) {
                        forgetModel res=response.body();
                        verify=res.getResponse().toString();
                        if(!res.getResponse().equals(null))
                        {
                            AlertDialog alertDialog=new AlertDialog.Builder(ForgetPassword.this).create();
                            alertDialog.setTitle("Verification Code");
                            alertDialog.setMessage("Verification Code has been sent to Email. Kindly verify it.. ");
                            alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "Okay",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            Intent intent = new Intent(ForgetPassword.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });

                        }
                    }

                    @Override
                    public void onFailure(Call<forgetModel> call, Throwable t) {
                        AlertDialog alertDialog=new AlertDialog.Builder(ForgetPassword.this).create();
                        alertDialog.setTitle("Network Error");
                        alertDialog.setMessage("Error in network. May be your internet connection is off");
                        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "Okay",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                });
                    }
                });
                if(!cnic.getText().toString().equals("")) {
                    verification.setEnabled(true);
                    Submit.setEnabled(true);
                }
                break;
            case  R.id.submit:
                if(verification.getText().toString().equals(verify))
                {
                    Intent intent = new Intent(ForgetPassword.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    AlertDialog alertDialog=new AlertDialog.Builder(ForgetPassword.this).create();
                    alertDialog.setTitle("Passcode Error");
                    alertDialog.setMessage("Verification code error. Kindly recheck your Email");
                    alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "Okay",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            });
                }
                break;
        }

    }
    private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 7) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        verify=saltStr;
        return saltStr;
    }


}
