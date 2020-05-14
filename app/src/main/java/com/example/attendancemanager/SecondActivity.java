package com.example.attendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    SharedPreferences sp;
    String subjectname,filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        subjectname = getIntent().getStringExtra("subject");
        filename = getIntent().getStringExtra("file");

        TextView subjecttxt = findViewById(R.id.subjecttxt);
        subjecttxt.setText(subjectname);

        sp = getSharedPreferences(filename,0);
        refresh();
    }

    public void refresh(){
        int present = sp.getInt("present",0);
        int total = sp.getInt("total",0);
        TextView presenttxt = findViewById(R.id.percenttxt);
        if(total==0)
            presenttxt.setText(present+"/"+total+"    "+0+"%");
        else
            presenttxt.setText(present+"/"+total+"    "+(present*100/total)+"%");

    }

    public void absentClick(View v){
        int total = sp.getInt("total",0);
        total += 1;
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("total",total);
        editor.apply();
        refresh();
    }

    public void presentClick(View v){
        int present = sp.getInt("present",0);
        int total = sp.getInt("total",0);
        present += 1;
        total += 1;
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("present",present);
        editor.putInt("total",total);
        editor.apply();
        refresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    public void editClick(View v){
        Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
        intent.putExtra("subject",subjectname);
        intent.putExtra("file",filename);
        startActivity(intent);
    }

}
