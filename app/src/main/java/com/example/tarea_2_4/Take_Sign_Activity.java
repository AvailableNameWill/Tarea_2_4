package com.example.tarea_2_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tarea_2_4.Clases.Transacs;
import com.example.tarea_2_4.Config.Conection;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;

public class Take_Sign_Activity extends AppCompatActivity {

    private ImageView iv;
    private Button btnSave;
    private EditText txtDesc;

    private float floatStartX = -1, floatStartY = -1, floatEndX = -1,floatEndY = -1;
    private Bitmap bit;
    private Canvas canvas;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_sign);

        permisos();
        getObj();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty()){
                    saveToDB();
                }
            }
        });
    }

    private void saveToDB(){
        try{
            Conection con = new Conection(this, Transacs.dbName, null, 1);
            String desc = txtDesc.getText().toString();
            ByteArrayOutputStream ba = new ByteArrayOutputStream();
            Bitmap bImg = ((BitmapDrawable)iv.getDrawable()).getBitmap();
            bImg.compress(Bitmap.CompressFormat.JPEG, 25, ba);
            byte[] sImg = ba.toByteArray();

            boolean insert = con.saveData(desc, sImg);

            System.out.println(getExternalCacheDir());

            if(insert) Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "!!Error", Toast.LENGTH_SHORT).show();

            clean();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void drawPaintSketch(){
        if(bit == null){
            bit = Bitmap.createBitmap( iv.getWidth(), iv.getHeight(), Bitmap.Config.ARGB_8888);

            canvas = new Canvas(bit);

            paint.setColor(Color.BLACK);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(8);
        }

        canvas.drawLine(floatStartX, floatStartY-220, floatEndX, floatEndY-220, paint);
        iv.setImageBitmap(bit);
    }

    @Override
    public boolean onTouchEvent(MotionEvent evt){
        if(evt.getAction() == MotionEvent.ACTION_DOWN){
            floatStartX = evt.getX();
            floatStartY = evt.getY();
        }

        if(evt.getAction() == MotionEvent.ACTION_MOVE){
            floatEndX = evt.getX();
            floatEndY = evt.getY();

            drawPaintSketch();

            floatStartX = evt.getX();
            floatStartY = evt.getY();
        }

        if(evt.getAction() == MotionEvent.ACTION_UP){
            floatEndX = evt.getX();
            floatEndY = evt.getY();

            drawPaintSketch();
        }

        return super.onTouchEvent(evt);
    }

    /*public void btnSaveImg(View view){

    }*/

    private boolean isEmpty(){
        if(txtDesc.getText().toString().isEmpty()){
            Toast.makeText(this, "No puede dejar campos vacios", Toast.LENGTH_SHORT).show();
            return true;
        }
        else return false;
    }

    private void getObj(){
        iv = (ImageView) findViewById(R.id.iv);
        btnSave = (Button) findViewById(R.id.btnSave);
        txtDesc = (EditText) findViewById(R.id.txtDesc);
        paint = new Paint();
    }

    private void clean(){
        txtDesc.setText("");
        iv.setImageBitmap(null);
        bit = null;
    }

    private void permisos(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
    }
}