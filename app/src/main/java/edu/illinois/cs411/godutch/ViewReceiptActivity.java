package edu.illinois.cs411.godutch;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

public class ViewReceiptActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_receipt);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.ViewReceiptDoneButton);
        FloatingActionButton fab2 = findViewById(R.id.ViewReceiptEditButton);
        fab.setOnClickListener(this);
        fab2.setOnClickListener(this);
    }

    public void onClick(View view) {
        if(view.getId() == R.id.ViewReceiptDoneButton) {
            Toast.makeText(getApplicationContext(), "DONE", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ViewReceiptActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.ViewReceiptEditButton){
            Toast.makeText(getApplicationContext(), "Edit results", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ViewReceiptActivity.this, AddExpenseActivity.class);
            startActivity(intent);
        }
    }
}
