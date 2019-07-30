package edu.illinois.cs411.godutch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ReceiptHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CustomAdapter adapter;
    private List<PurchaseData> data_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_history);

        //refresh the storedView;
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        data_list = new ArrayList<>();
        load_data_from_server(0);

        gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new CustomAdapter(this, data_list);
        recyclerView.setAdapter(adapter);

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
////                int threshold =1;
////                int count = recyclerView.getChildCount();
////                if(gridLayoutManager.findLastCompletelyVisibleItemPosition() < count-1){
////                    load_data_from_server(data_list.get(data_list.size()-1).getPurchaseId());
////                    Toast.makeText(ReceiptHistoryActivity.this, "count: " + data_list.size(), Toast.LENGTH_SHORT).show();
////
////                    Toast.makeText(ReceiptHistoryActivity.this, "findLastCompletelyVisibleItemPosition: " + gridLayoutManager.findLastCompletelyVisibleItemPosition(), Toast.LENGTH_SHORT).show();
////                }
//
//            }
//        });
    }

    private void load_data_from_server(int id) {
        AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(MainActivity.pre_url + "godutch/api/v1.0/purchases/"+"takumi")
                        .build();

                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i=0; i<array.length(); i++){

                        JSONObject object = array.getJSONObject(i);

                        PurchaseData data = new PurchaseData(object.getInt("purchaseId"), object.getString("date"), object.getString("storeAddress"),
                                object.getString("userName"), object.getDouble("tax"));

                        data_list.add(data);
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("End of content");
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
            }
        };

        task.execute(id);

    }
}
