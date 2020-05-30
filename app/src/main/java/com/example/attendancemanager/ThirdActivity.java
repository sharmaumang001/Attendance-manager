package com.example.attendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import es.dmoral.toasty.Toasty;

public class ThirdActivity extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        String subjectName = getIntent().getStringExtra("subject");
        String filename = getIntent().getStringExtra("file");

        TextView subjecttxt = findViewById(R.id.subjecttxt);
        subjecttxt.setText(subjectName);

        sp = getSharedPreferences(filename,0);
    }

    public void update(View v){
        EditText presenttxt = findViewById(R.id.presenttxt);
        EditText totaltxt = findViewById(R.id.totaltxt);
        int present = Integer.parseInt(presenttxt.getText().toString());
        int total = Integer.parseInt(totaltxt.getText().toString());
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("present",present);
        editor.putInt("total",total);
        editor.apply();
        finish();
        Toasty.success(getApplicationContext(),"Edit complete",Toasty.LENGTH_SHORT).show();


    }
}
