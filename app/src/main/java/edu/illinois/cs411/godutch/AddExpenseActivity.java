package edu.illinois.cs411.godutch;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;

public class AddExpenseActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private Button selectDate;
    private Button doneButton;
    private TextView date;

    private EditText storeName;

    private Button addNewParticipantButton;
    private EditText addNewParticipantName;
    private String addedName;
    private LinearLayout participantsListLayout;
    private LinearLayout.LayoutParams layoutParams;
    protected List<ParticipantNameData> participantsNameList;

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private ParticipantNameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set purchase date
        selectDate = findViewById(R.id.btnDate);
        date = findViewById(R.id.tvSelectedDate);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddExpenseActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText( month + "/" + day + "/" +  year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
        //set store Name
        storeName = findViewById(R.id.storeName);
        storeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //participant list
        recyclerView = (RecyclerView) findViewById(R.id.participantsList_RecyclerView);
        participantsNameList = new ArrayList<>();
        getParticipantList(0);

//        Toast.makeText(AddExpenseActivity.this, "Hello?", Toast.LENGTH_SHORT).show();
//        for(ParticipantNameData pName: participantsNameList){
//            Toast.makeText(AddExpenseActivity.this, pName.getName(), Toast.LENGTH_SHORT).show();
//        }

        gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new ParticipantNameAdapter(this, participantsNameList);
        recyclerView.setAdapter(adapter);


        //new part
        addNewParticipantButton = findViewById(R.id.addNewParticipantButton);
        addNewParticipantButton.setOnClickListener(this);
        addNewParticipantName = findViewById(R.id.addNewParticipantName);
        addNewParticipantName.addTextChangedListener(this);



        //set done button
        doneButton = findViewById(R.id.addExpenseDoneButton);
        doneButton.setOnClickListener(this);
    }

    private List<ParticipantNameData> getParticipantList(int id) {


        AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                OkHttpClient client = new OkHttpClient();
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url(MainActivity.pre_url + "godutch/api/v1.0/complex_query1/"+"takumi")
                        .build();

                try {
                    okhttp3.Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i=0; i<array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        ParticipantNameData data = new ParticipantNameData(object.getString("name"));
                        participantsNameList.add(data);
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
//                    Toast.makeText(AddExpenseActivity.this, "No name", Toast.LENGTH_SHORT).show();
                    System.out.println("End of content");
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
//                adapter.notifyDataSetChanged();
            }
        };

        task.execute(id);
        return participantsNameList;
    }

    public void onClick(View v) {
        if (v.getId() == R.id.addExpenseDoneButton) {
            //Toast.makeText(this, "TEST HERE", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, splitActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.addNewParticipantButton){
            String url = MainActivity.pre_url + "godutch/api/v1.0/participants";
            RequestQueue queue = Volley.newRequestQueue(this);
            HashMap<String, String> params = new HashMap<>();
            params.put("purchaseId", "5");
            params.put("name", addedName);
            params.put("amount", "17.5");

            JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(AddExpenseActivity.this,"added " + addedName + " to P4", Toast.LENGTH_SHORT).show();
//                            participantsNameList = new ArrayList<>();
                            try {
//                                Toast.makeText(AddExpenseActivity.this,"Updating " + addedName + "to list", Toast.LENGTH_SHORT).show();
                                Thread.sleep(2000); //other ways to do this
                                participantsNameList = new ArrayList<>();
                                getParticipantList(0);
                                recyclerView.setLayoutManager(gridLayoutManager);
                                recyclerView.setAdapter(adapter);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    textView.setText();
                    Toast.makeText(AddExpenseActivity.this, "That didn't work!!!", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(postRequest);

        }
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        addedName = addNewParticipantName.getText().toString();

    }

//    private void generateUI(String pName, String pID) {
//        TextView textView = new TextView(AddExpenseActivity.this);
//        View greyPadding = new View(AddExpenseActivity.this);
//
//        participantsList.addView(textView);
//        participantsList.addView(greyPadding);
//
//        textView.setLayoutParams(layoutParams);
//        textView.setPadding(20, 25, 20, 25);
//        textView.setText(pName + " - " + pID);
//        textView.setHint(pID);
//        textView.setHapticFeedbackEnabled(true);
//        textView.setOnClickListener(gameClickListener);
//
//        greyPadding.setLayoutParams(layoutParams);
//        greyPadding.setBackgroundColor(Color.DKGRAY);
//        greyPadding.setMinimumHeight(1);
//    }



}
