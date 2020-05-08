package com.angel.erasmushelper.utilities;

public class Utilities {

    //Tabla usuarios

    public static final String USERS_TABLE = "users";
    public static final String USERNAME_COLUMN = "username";
    public static final String EMAIL_COLUMN = "email";
    public static final String PASSWORD_COLUMN = "password";

    public static final String  CREATE_USER_TABLE = "CREATE TABLE "+
            USERS_TABLE + "("+USERNAME_COLUMN+" TEXT," +
            " "+ EMAIL_COLUMN +" TEXT, " + PASSWORD_COLUMN +  " TEXT, " +
            "PRIMARY KEY ("+USERNAME_COLUMN+")";

}
