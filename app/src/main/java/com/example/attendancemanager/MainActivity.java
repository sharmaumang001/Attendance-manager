package com.example.attendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = findViewById(R.id.listview);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String subjectName = (String) listView.getItemAtPosition(position);
                String fileName = subjectName.replace(" ","_");
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("subject",subjectName);
                intent.putExtra("file",fileName);
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int percent = overall();
        TextView overalltxt = findViewById(R.id.overalltxt);
        overalltxt.setText("Overall : "+percent+"%");
    }

    public int overall(){
        String[] subjects = getResources().getStringArray(R.array.subjects);
        int total = 0, present = 0;
        for(int i=0; i<subjects.length; i++){
            String filename = subjects[i].replace(" ","_");
            SharedPreferences sp = getSharedPreferences(filename,0);
            total += sp.getInt("total",0);
            present += sp.getInt("present",0);
        }
        return total!=0 ? present*100/total : 0;
    }

}
