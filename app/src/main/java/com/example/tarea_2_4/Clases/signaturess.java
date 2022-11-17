package com.example.tarea_2_4.Clases;

import android.content.Intent;
import android.graphics.Bitmap;

public class signaturess {

    private Integer id;
    private String descripcion;
    private Bitmap img;

    public signaturess() {}

    public signaturess(Integer id, String descripcion, Bitmap img) {
        this.id = id;
        this.descripcion = descripcion;
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
