package edu.illinois.cs411.godutch;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
//
//import com.android.volley.Request;
//import com.android.volley.Response;
import com.google.android.material.internal.NavigationMenu;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    public static String pre_url = "https://de52896e.ngrok.io/";

    FabSpeedDial fabSpeedDial;

    Button getParticipants;
    Button getHistory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getHistory = findViewById(R.id.viewTestButton);
        getHistory.setOnClickListener(this);


        //handles others
        fabSpeedDial = (FabSpeedDial) findViewById(R.id.fabidMain);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.upload){
                    Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LoadImageActivity.class);
                    startActivity(intent);
                    return true;
                }
                else
                    return false;//change here for camera
            }

            @Override
            public void onMenuClosed() {

            }
        });






        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }




    @Override
    public void onClick(View v){
        if (v.getId() == R.id.viewTestButton){
            Intent intent = new Intent(MainActivity.this, ReceiptHistoryActivity.class);
            startActivity(intent);
        }
//        Toast.makeText(this, "Click Requesting", Toast.LENGTH_SHORT).show();
//        final TextView textView = (TextView) findViewById(R.id.buttonResponse);
//        RequestQueue queue = Volley.newRequestQueue(this);

//        if (v.getId() == R.id.insertPurchase){
//            Toast.makeText(this, "Insert Purchase Click Requesting", Toast.LENGTH_SHORT).show();
//
//            String url = pre_url + "godutch/api/v1.0/purchases";
//            HashMap<String, String> params = new HashMap<>();
//            params.put("accountId", "1");
//            params.put("storeId", "1");
//            params.put("tax", "0.175");
//            params.put("date", "10 Jun 2019");
//
//            JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            textView.setText("Response insertPurchase: " + response.toString());
//                        }},new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    textView.setText("insertPurchase didn't work!");
//                    Toast.makeText(MainActivity.this, textView.getText(), Toast.LENGTH_SHORT).show();
//                }
//            });
//            queue.add(postRequest);
//        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
