package com.example.tarea_2_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.tarea_2_4.Clases.Transacs;
import com.example.tarea_2_4.Clases.signaturess;
import com.example.tarea_2_4.Config.Conection;

import java.util.ArrayList;

public class test extends AppCompatActivity {

    private ArrayList<signaturess> data;

    private Conection con;
    private ImageView imgg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        imgg = (ImageView) findViewById(R.id.imgg);
        con = new Conection(this, Transacs.dbName, null, 1);
        data = con.getSigns(data);

        imgg.setImageBitmap(data.get(3).getImg());
    }
}