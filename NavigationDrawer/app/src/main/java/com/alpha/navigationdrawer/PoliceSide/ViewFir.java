package com.alpha.navigationdrawer.PoliceSide;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.navigationdrawer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haseeb on 11/29/2017.
 */

public class ViewFir extends AppCompatActivity {

    private ArrayList<String> data=new ArrayList<String>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_fir);


        ListView lv=(ListView)findViewById(R.id.firlist);
        generateListContent();
        lv.setAdapter(new MyListAdapter(this, R.layout.fir_list, data));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ViewFir.this, "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void generateListContent() {
        for (int i = 0; i < 55; i++) {
            data.add("This is row number This is row number This is row number This is row number This is row number" + i);
        }
    }

    private class MyListAdapter extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;

        private MyListAdapter(Context context, int resource, List<String> objects) {
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
                viewHolder.firNo=(TextView)convertView.findViewById(R.id.firno);
                viewHolder.name=(TextView)convertView.findViewById(R.id.name);
                viewHolder.cntact=(TextView)convertView.findViewById(R.id.contct);
                viewHolder.cnic=(TextView)convertView.findViewById(R.id.cnic);
                viewHolder.crimetype=(TextView)convertView.findViewById(R.id.crimeType);
                viewHolder.Des=(TextView)convertView.findViewById(R.id.desc);
                viewHolder.Accept=(Button)convertView.findViewById(R.id.AcceptBtn);
                convertView.setTag(viewHolder);
            }
            mainViewholder=(ViewHolder)convertView.getTag();
            mainViewholder.Accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Button was clicked for list item " + position, Toast.LENGTH_SHORT).show();
                }
            });
            mainViewholder.Des.setText(getItem(position));

            return convertView;

        }


        public class ViewHolder {

            Button Accept;
            TextView firNo,name,cntact,cnic,crimetype,Des;
            // ImageView thumbnail;
           /* TextView title;
            Button button;*/
        }
    }
}
