package com.alpha.navigationdrawer.PoliceSide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alpha.navigationdrawer.Api.ApiClient;
import com.alpha.navigationdrawer.Api.ApiInterface;
import com.alpha.navigationdrawer.Api.StatusPost;
import com.alpha.navigationdrawer.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by haseeb on 12/5/2017.
 */

public class PostStatus extends AppCompatActivity {
    Button updateStatus;
    EditText StatusText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editinfo);
        updateStatus=(Button)findViewById(R.id.postStatus);
        StatusText=(EditText)findViewById(R.id.status);
        updateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
                Call<StatusPost>call=apiInterface.PostStatus(StatusText.getText().toString(),"I8");
                call.enqueue(new Callback<StatusPost>() {
                    @Override
                    public void onResponse(Call<StatusPost> call, Response<StatusPost> response) {
                        StatusPost res=response.body();
                        Toast.makeText(PostStatus.this, "Response: "+res.getResponse().toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<StatusPost> call, Throwable t) {
                        Toast.makeText(PostStatus.this, "Exception: "+t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
