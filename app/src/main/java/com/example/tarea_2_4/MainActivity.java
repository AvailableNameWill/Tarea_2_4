package com.example.tarea_2_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button btnTakeSignA, btnViewSignA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getObj();

        btnTakeSignA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Take_Sign_Activity.class);
                startActivity(intent);
            }
        });

        btnViewSignA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), View_Sign_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void getObj(){
        btnTakeSignA = (Button) findViewById(R.id.btnTakeSignA);
        btnViewSignA = (Button) findViewById(R.id.btnViewSignA);
    }
}