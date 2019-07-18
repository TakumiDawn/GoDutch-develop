package edu.illinois.cs411.godutch;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.snackbar.Snackbar;

import android.view.KeyEvent;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    public static String pre_url = "https://f98fbba2.ngrok.io/";

    FabSpeedDial fabSpeedDial;
    private Button insertPurchase;
    private Button insertProduct;
    private Button insertParticipant;
    private Button updateParticipant;
    private Button deleteParticipant;
    private Button listFriends;
    private Button avgPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fabSpeedDial = (FabSpeedDial) findViewById(R.id.fabid);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
                //EditText editText = (EditText) findViewById(R.id.editText);
                //String message = editText.getText().toString();
                //intent.putExtra("Message", message);
                startActivity(intent);
                return true;
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

        insertPurchase = findViewById(R.id.insertPurchase);
        insertPurchase.setOnClickListener(this);
        insertProduct = findViewById(R.id.insertProduct);
        insertProduct.setOnClickListener(this);
        insertParticipant = findViewById(R.id.insertParticipant);
        insertParticipant.setOnClickListener(this);
        updateParticipant = findViewById(R.id.updateParticipant);
        updateParticipant.setOnClickListener(this);
        deleteParticipant = findViewById(R.id.deleteParticipant);
        deleteParticipant.setOnClickListener(this);
        listFriends = findViewById(R.id.listFriends);
        listFriends.setOnClickListener(this);
        avgPrice = findViewById(R.id.avgPrice);
        avgPrice.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        Toast.makeText(this, "Click Requesting", Toast.LENGTH_SHORT).show();
        final TextView textView = (TextView) findViewById(R.id.buttonResponse);
        RequestQueue queue = Volley.newRequestQueue(this);

        if (v.getId() == R.id.insertPurchase){
            Toast.makeText(this, "Insert Purchase Click Requesting", Toast.LENGTH_SHORT).show();

            String url = pre_url + "godutch/api/v1.0/purchases";
            HashMap<String, String> params = new HashMap<>();
            params.put("accountId", "1");
            params.put("storeId", "1");
            params.put("tax", "0.175");
            params.put("date", "10 Jun 2019");

            JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            textView.setText("Response insertPurchase: " + response.toString());
                        }},new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textView.setText("insertPurchase didn't work!");
                    Toast.makeText(MainActivity.this, textView.getText(), Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(postRequest);


        } else if (v.getId() == R.id.insertProduct){
            Toast.makeText(this, "insertProduct Click Requesting", Toast.LENGTH_SHORT).show();

            String url = pre_url + "godutch/api/v1.0/products";
            HashMap<String, String> params = new HashMap<>();
            params.put("quantity", "1");
            params.put("pricePerUnit", "5.74");
            params.put("name", "Marketside Organic Cage Free Large Brown Grade A Eggs, 18 Count");
            params.put("image", "https://i5.wal.co/asr/d12c87ae-3ab2-4467-87b6-a196ecb8f699_4.7c24fc9142322c264f2361df1105a2c5.jpeg-c33dc62efb383b59ba7a1682d9cd95cbb82cfda5-optim-100x100.jpg");
            params.put("purchaseId", "6");

            JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            textView.setText("Response insertProduct: " + response.toString());
                        }},new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textView.setText("insertProduct didn't work!");
                    Toast.makeText(MainActivity.this, textView.getText(), Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(postRequest);

        } else if (v.getId() == R.id.insertParticipant){
            Toast.makeText(this, "Insert Participant Click Requesting", Toast.LENGTH_SHORT).show();
            String url = pre_url + "godutch/api/v1.0/participants";
            HashMap<String, String> params = new HashMap<>();
            params.put("purchaseId", "7");
            params.put("name", "Weili");
            params.put("amount", "5.34");

            JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            textView.setText("Response insertParticipant: " + response.toString());
                            Toast.makeText(MainActivity.this, textView.getText(), Toast.LENGTH_SHORT).show();
                        }},new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textView.setText("insertParticipant didn't work!");
                }
            });
            queue.add(postRequest);



        } else if (v.getId() == R.id.updateParticipant){
            Toast.makeText(this, "updateParticipant Click Requesting", Toast.LENGTH_SHORT).show();
            String url = pre_url + "godutch/api/v1.0/participants/4";
            HashMap<String, String> params = new HashMap<>();
            params.put("purchaseId", "7");
            params.put("name", "WeiliTEST");
            params.put("amount", "5.34");

            JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.PUT, url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            textView.setText("Response updateParticipant: " + response.toString());
                        }},new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textView.setText("updateParticipant didn't work!");
                }
            });
            queue.add(postRequest);


        } else if (v.getId() == R.id.deleteParticipant){
            Toast.makeText(this, "deleteParticipant Click Requesting", Toast.LENGTH_SHORT).show();
            String url = pre_url + "godutch/api/v1.0/participants/4";

            StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            textView.setText("Response deleteParticipant: " + response.toString());
                            Toast.makeText(MainActivity.this, textView.getText(), Toast.LENGTH_SHORT).show();
                        }
                        },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textView.setText("deleteParticipant didn't work!");
                }
            });
            queue.add(deleteRequest);
        } else if (v.getId() == R.id.listFriends) {
            Toast.makeText(this, "listFriends Click Requesting", Toast.LENGTH_SHORT).show();
            String url = pre_url + "godutch/api/v1.0/complex_query1/1";

            StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            textView.setText("Response listFriends: " + response.toString());
                            Toast.makeText(MainActivity.this, textView.getText(), Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textView.setText("listFriends didn't work!");
                }
            });
            queue.add(getRequest);
        } else if (v.getId() == R.id.avgPrice){
            Toast.makeText(this, "listFriends Click Requesting", Toast.LENGTH_SHORT).show();
            String url = pre_url + "godutch/api/v1.0/complex_query2/1";

            StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            textView.setText("Response listFriends: " + response.toString());
                            Toast.makeText(MainActivity.this, textView.getText(), Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textView.setText("listFriends didn't work!");
                }
            });
            queue.add(getRequest);
        }


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

    @SuppressWarnings("StatementWithEmptyBody")
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
