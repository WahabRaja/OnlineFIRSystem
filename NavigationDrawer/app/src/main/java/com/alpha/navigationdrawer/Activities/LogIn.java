package com.alpha.navigationdrawer.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.navigationdrawer.Api.ApiClient;
import com.alpha.navigationdrawer.Api.ApiInterface;
import com.alpha.navigationdrawer.Api.LoginModel;
import com.alpha.navigationdrawer.MainActivity;
import com.alpha.navigationdrawer.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by haseeb on 11/11/2017.
 */

public class LogIn extends AppCompatActivity {
    Button b1,b2;
    Boolean login;
    SharedPreferences ss;
    SharedPreferences.Editor editor;
    EditText cnic,password;
    TextView forget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        cnic=(EditText)findViewById(R.id.cnic);
        password=(EditText)findViewById(R.id.pass1);
        forget=(TextView)findViewById(R.id.forgetpass);
        b2=(Button)findViewById(R.id.btnsignin);
        b1=(Button)findViewById(R.id.btnsignup);
        ss=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        editor=ss.edit();
        login=ss.getBoolean("Login",false);
        if(login)
        {
            Intent intent = new Intent(LogIn.this, MainActivity.class);
            startActivity(intent);
        }
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //forget password
                Intent intent=new Intent(LogIn.this,ForgetPassword.class);
                startActivity(intent);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LogIn.this,SignUp.class);
                startActivity(intent);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
                Call<LoginModel> call=apiInterface.LoginUser(cnic.getText().toString(),password.getText().toString(),2);
                call.enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                        LoginModel res = response.body();
                        if (res.getResponse().equals("Data uploaded successfully")) {
                            Toast.makeText(LogIn.this, "Log in successfully", Toast.LENGTH_SHORT).show();
                            editor.putString("cnic", cnic.getText().toString());
                            editor.commit();


                            AlertDialog alertDialog=new AlertDialog.Builder(LogIn.this).create();
                            alertDialog.setTitle("Log in");
                            alertDialog.setMessage("Remember this Account? ");
                            alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            editor.putBoolean("Login",true);
                                            editor.commit();
                                            dialog.dismiss();
                                            Intent intent = new Intent(LogIn.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                            alertDialog.setButton(android.app.AlertDialog.BUTTON_NEGATIVE, "No",
                                    new DialogInterface.OnClickListener(){
                                        public void onClick(DialogInterface dialog, int which){
                                            dialog.dismiss();
                                            Intent intent = new Intent(LogIn.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }

                                    });

                            alertDialog.show();

                        }
                        else
                        {
                            Toast.makeText(LogIn.this, "Invalid CNIC or Password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {
                        Toast.makeText(LogIn.this, "Enable to login in due to Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }
}
