package edu.illinois.cs411.godutch;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class splitActivity extends AppCompatActivity {

//    private final String android_version_names[] = {
//            "Donut",
//            "Eclair",
//            "Froyo",
//            "Gingerbread",
//            "Honeycomb",
//            "Ice Cream Sandwich",
//            "Jelly Bean",
//            "KitKat",
//            "Lollipop",
//            "Marshmallow"
//    };
//
//    private final String android_image_urls[] = {
//            "http://api.learn2crack.com/android/images/donut.png",
//            "http://api.learn2crack.com/android/images/eclair.png",
//            "http://api.learn2crack.com/android/images/froyo.png",
//            "http://api.learn2crack.com/android/images/ginger.png",
//            "http://api.learn2crack.com/android/images/honey.png",
//            "http://api.learn2crack.com/android/images/icecream.png",
//            "http://api.learn2crack.com/android/images/jellybean.png",
//            "http://api.learn2crack.com/android/images/kitkat.png",
//            "http://api.learn2crack.com/android/images/lollipop.png",
//            "http://api.learn2crack.com/android/images/marshmallow.png"
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.saveButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save to data base
            }
        });
        //initViews();
        getTest();

    }

    private void getTest(){
        final TextView textView = (TextView) findViewById(R.id.product_name);

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = MainActivity.pre_url + "godutch/api/v1.0/purchases/1";

        //GET
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.print("first statement. " + response);
                        textView.setText("Response is: "+ response.substring(0,5));
                        Toast.makeText(splitActivity.this, "Request" + textView.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);


//// Access the RequestQueue through your singleton class.
//        url = "https://479a7df0.ngrok.io/godutch/api/v1.0/users";
//        HashMap<String, String> params = new HashMap<>();
//        params.put("name", "Alif");
//        params.put("email", "rafsa@illinois.edu");
//        params.put("password", "fafDSrwg!");
//
//        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
//                new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                // responseLog.d("Response", response);
//                textView.setText("Response: " + response.toString());
//                }},new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                textView.setText("That didn't work!");
//                }
//        });
//        queue.add(postRequest);

    }















//        private void initViewsTest(){
//        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
//
//        ArrayList androidVersions = prepareData();
//        DataAdapter adapter = new DataAdapter(getApplicationContext(), androidVersions);
//        recyclerView.setAdapter(adapter);
//    }

//    private void initViews(){
//        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
//
//        ArrayList androidVersions = prepareData();
//        DataAdapter adapter = new DataAdapter(getApplicationContext(), androidVersions);
//        recyclerView.setAdapter(adapter);
//    }
//
//    private ArrayList prepareData(){
//        ArrayList android_version = new ArrayList<>();
//        for(int i=0; i<android_version_names.length;i++){
//            item androidVersion = new item();
//            androidVersion.setAndroid_version_name(android_version_names[i]);
//            androidVersion.setAndroid_image_url(android_image_urls[i]);
//            android_version.add(androidVersion);
//        }
//        return android_version;
//    }



}
