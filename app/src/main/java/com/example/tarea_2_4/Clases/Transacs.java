package com.example.tarea_2_4.Clases;

public class Transacs {

    public static final String dbName = "tarea_2_4";
    public static final String tblName = "signs";

    public static final String id = "id";
    public static final String descripcion = "descripcion";
    public static final String img = "img";

    public static final String createTblSigns = "CREATE TABLE signs (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                "descripcion TEXT, img BLOB)";

    public static final String getSigns = "SELECT * FROM " + Transacs.tblName;

    public static final String dropSigns = "DROP IF EXISTS signs";
}
