package com.example.tarea_2_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tarea_2_4.Clases.CustomAdapter;
import com.example.tarea_2_4.Clases.Transacs;
import com.example.tarea_2_4.Clases.signaturess;
import com.example.tarea_2_4.Config.Conection;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;

public class View_Sign_Activity extends AppCompatActivity {

    private RecyclerView rView;
    private RecyclerView.LayoutManager lManager;
    private RecyclerView.Adapter rAdapter;
    private ArrayList<signaturess> data;

    private Conection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sign);

        con = new Conection(this, Transacs.dbName, null, 1);
        data = con.getSigns(data);

        getObj();

        rAdapter = new CustomAdapter(data);
        rView.setAdapter(rAdapter);
    }

    private void getObj(){
        rView = (RecyclerView) findViewById(R.id.rView);
        rView.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        rView.setLayoutManager(lManager);
        rView.setItemAnimator(new DefaultItemAnimator());
    }
}