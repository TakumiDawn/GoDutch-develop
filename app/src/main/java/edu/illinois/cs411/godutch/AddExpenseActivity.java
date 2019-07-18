package edu.illinois.cs411.godutch;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddExpenseActivity extends AppCompatActivity implements View.OnClickListener {
    private Button selectDate;
    private Button doneButton;
    private TextView date;

    private EditText storeName;

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


        //set done button
        doneButton = findViewById(R.id.addExpenseDoneButton);
        doneButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.addExpenseDoneButton) {
            //Toast.makeText(this, "TEST HERE", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, splitActivity.class);
            startActivity(intent);
        }
    }





}
