package com.example.fetch_json_internet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btn;
    private static final String JSON_URL = "https://mocki.io/v1/9c310c36-b865-4121-b8f8-74f0c3f59dcb";
    List<Hero_data> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);
        dataList = new ArrayList<>();

        btn = findViewById(R.id.btn_fetch);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDataList();
            }
        });
    }

    private void loadDataList() {
        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);


                        try {
                            //getting the whole json object from the response
                            Log.i("json", response.toString());
                            JSONObject obj = new JSONObject(response);

                            Log.i("datas", obj.toString());
                            JSONArray DataArray = obj.getJSONArray("datas");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < DataArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject dataObject = DataArray.getJSONObject(i);

                                //creating a tutorial object and giving them the values from json object
                                Hero_data tutorial = new Hero_data(dataObject.getString("first_name"), dataObject.getString("last_name"), dataObject.getString("avatar"), dataObject.getString("email"), dataObject.getString("gender"), dataObject.getString("ip_address"));

                                //adding the tutorial to dataList
                                dataList.add(tutorial);
                            }

                            //creating custom adapter object
                            MyAdapter adapter = new MyAdapter(dataList, getApplicationContext());

                            //adding the adapter to listview
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
                //creating a request queue
    }
}