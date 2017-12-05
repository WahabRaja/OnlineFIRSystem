package com.alpha.navigationdrawer.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.navigationdrawer.Api.ApiClient;
import com.alpha.navigationdrawer.Api.ApiInterface;
import com.alpha.navigationdrawer.Api.StatusViewDetail;
import com.alpha.navigationdrawer.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by haseeb on 11/22/2017.
 */

public class StatusView extends AppCompatActivity {
    private ArrayList<String> data = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewstatus);
        ListView lv = (ListView) findViewById(R.id.listview);
        generateListContent();
        lv.setAdapter(new MyListAdaper(this, R.layout.status_list_, data));
       /* lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(StatusView.this, "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
            }
        });*/

        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<StatusViewDetail> call=apiInterface.Status("I8");
        call.enqueue(new Callback<StatusViewDetail>() {
            @Override
            public void onResponse(Call<StatusViewDetail> call, Response<StatusViewDetail> response) {

                StatusViewDetail res=response.body();
                Log.d("Arraylist", "onResponse: "+res.getResponse());
                Toast.makeText(StatusView.this, "ye wala "+res.getResponse(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<StatusViewDetail> call, Throwable t) {
                Toast.makeText(StatusView.this, "Exception: "+t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("Exception ","Exception: "+t.toString());
            }
        });

    }

    private void generateListContent() {
        for (int i = 0; i < 55; i++) {
            data.add("This is row number This is row number This is row number This is row number This is row number" + i);
        }
    }


    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;

        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                // viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.list_item_thumbnail);
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text);
                viewHolder.button = (Button) convertView.findViewById(R.id.list_item_btn);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Button was clicked for list item " + position, Toast.LENGTH_SHORT).show();
                }
            });
            mainViewholder.title.setText(getItem(position));

            return convertView;
        }


        public class ViewHolder {

            // ImageView thumbnail;
            TextView title;
            Button button;
        }
    }
}
