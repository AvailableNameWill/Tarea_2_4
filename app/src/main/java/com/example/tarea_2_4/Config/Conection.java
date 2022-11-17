package com.example.tarea_2_4.Config;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;

import com.example.tarea_2_4.Clases.Transacs;
import com.example.tarea_2_4.Clases.signaturess;

import java.util.ArrayList;

public class Conection extends SQLiteOpenHelper {

    public Conection(Context context, String dnName, SQLiteDatabase.CursorFactory fact, int version){
        super(context, dnName, fact, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Transacs.createTblSigns);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(Transacs.dropSigns);
        onCreate(db);
    }

    public boolean saveData(String descripcion, byte[] img){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Transacs.descripcion, descripcion);
        cv.put(Transacs.img, img);

        long ins = db.insert(Transacs.tblName, Transacs.id, cv);
        if(ins == -1)return false;
        else return true;
    }

    public ArrayList<signaturess> getSigns(ArrayList<signaturess> _signs){
        SQLiteDatabase db = this.getReadableDatabase();

        signaturess signs = null;
        _signs = new ArrayList<signaturess>();
        Cursor cursor = db.rawQuery("select * from " + Transacs.tblName, null);

        while (cursor.moveToNext()){
            signs = new signaturess();
            signs.setId(cursor.getInt(0));
            signs.setDescripcion(cursor.getString(1));
            byte[] dbImg = cursor.getBlob(2);
            signs.setImg(BitmapFactory.decodeByteArray(dbImg, 0, dbImg.length));
            _signs.add(signs);
        }

        cursor.close();
        return _signs;
    }
}
