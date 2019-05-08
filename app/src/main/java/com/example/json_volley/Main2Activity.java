package com.example.json_volley;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    String id, name, username, email, street, suite, city, zipcode, address,noHp;

    private RecyclerView recyclerView;
    private Adapter_Json2 adapt;
    private ArrayList<Char_> charlist;
    private Button btnGetdata;
    private BufferedReader reader = null;
    private HttpURLConnection connection = null;
    ProgressDialog progressDialog;
    SwipeRefreshLayout mswipeRefresh;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        btnGetdata = (Button) findViewById(R.id.buttonData);
        mswipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeFresh);
        requestQueue = Volley.newRequestQueue(this);


        mswipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                buatJsonArrayRq();

            }
        });

        btnGetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buatJsonArrayRq();

            }
        });


    }

    public void buatJsonArrayRq() {
        progressDialog = new ProgressDialog(Main2Activity.this);
        progressDialog.setMessage("Harap tunggu");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String requestUrl = "http://210.210.154.65/api/kontak";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, requestUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                charlist = new ArrayList<>();
                try {
                    JSONArray values = response.getJSONArray("values");
                    for (int i = 0; i < values.length(); i++) {
                        JSONObject pengguna = values.getJSONObject(i);
                        id = pengguna.getString("id");
                        name = pengguna.getString("nama");
                        email = pengguna.getString("email");
                        noHp=pengguna.getString("nohp");
                        address=pengguna.getString("alamat");

                        charlist.add(new Char_(id, name,email,noHp,address));
                    }

                } catch (JSONException x) {
                    x.printStackTrace();

                }
                mswipeRefresh.setRefreshing(false);
                progressDialog.dismiss();

                adapt = new Adapter_Json2(charlist);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Main2Activity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapt);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                mswipeRefresh.setRefreshing(false);
                progressDialog.dismiss();
                Log.i("Volley error: ", String.valueOf(error));

            }
        });
        requestQueue.add(jsonObjectRequest);
    }


}
