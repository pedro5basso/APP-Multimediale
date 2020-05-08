package com.angel.erasmushelper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.angel.erasmushelper.utilities.Utilities;

public class RegisterActivity extends Activity {

    TextView tvLogin, tvRegister, tvEmail, tvUsername, tvlogregsocialmedia;
    EditText etEmail, etUsername, etPassword;
    Boolean register = true;
    Button bttRegLog = null;
    Boolean useralreadyexists, checkpassword;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);



        bttRegLog = findViewById(R.id.bttRegLog);

        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPawssord);

        tvUsername = findViewById(R.id.tvUsername);
        tvlogregsocialmedia = findViewById(R.id.tvlogregsocialmedia);
        tvEmail = findViewById(R.id.tvEmail);
        tvLogin = findViewById(R.id.tvLogin);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvLogin.setText(">Login");
                tvRegister.setText("Register");
                tvLogin.setTextColor(Color.WHITE);
                tvRegister.setTextColor(Color.BLACK);
                register = false;
                bttRegLog.setText("Login");
                tvEmail.setVisibility(View.GONE);
                etEmail.setVisibility(View.GONE);
                tvUsername.setText("username or email");
                tvlogregsocialmedia.setText("Login with");



            }
        });

        tvRegister = findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvRegister.setText(">Register");
                tvLogin.setText("Login");
                tvLogin.setTextColor(Color.BLACK);
                tvRegister.setTextColor(Color.WHITE);
                register= true;
                bttRegLog.setText("Register");
                tvEmail.setVisibility(View.VISIBLE);
                etEmail.setVisibility(View.VISIBLE);
                tvUsername.setText("username");
                tvlogregsocialmedia.setText("Register with");




            }
        });

        bttRegLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(register == true){
                    RegisterUserSQL();
                }
                else
                {
                    if(register == false){
                        LoginUserSQL();
                    }

                }

            }
        });




    }

    private void LoginUserSQL() {

        conn = new ConexionSQLiteHelper(this,"db_users", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();



        String user_mail = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        useralreadyexists = CheckUsername();

        checkpassword= CheckPass(password);

        if(user_mail.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(),"Some Fields are empty!", Toast.LENGTH_LONG).show();
        }else{
            if(useralreadyexists && checkpassword){
                Toast.makeText(getApplicationContext(),"Login Succesfull", Toast.LENGTH_LONG).show();
                Intent intent = new Intent("com.erasmushelper.Home_Activity");
                startActivity(intent);
            }else
            {
                if(useralreadyexists && !checkpassword){
                    Toast.makeText(getApplicationContext(),"Wrong Password", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Didnt login with this username", Toast.LENGTH_LONG).show();
                }

            }
        }


    }

    private Boolean CheckPass(String _passw) {
        conn = new ConexionSQLiteHelper(this,"db_users", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] params = {etPassword.getText().toString()};
        Boolean flag = null;

        try{
            Cursor cursor = db.rawQuery("SELECT * FROM " + Utilities.USERS_TABLE + " WHERE " +
                    Utilities.PASSWORD_COLUMN + "=?", params);

            cursor.moveToFirst();

            String password =cursor.getString(2);

            if(_passw.equals(password))
                flag = true;
            else
                flag = false;


        }catch(Exception e){
            flag = false;
        }


        return (flag);

    }

    private void RegisterUserSQL() {

        conn = new ConexionSQLiteHelper(this,"db_users", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        //insert into users (username,email,password) values ('userprueba','user@prueba.es','prueba123')

        if(etUsername.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Some Fields are empty!", Toast.LENGTH_LONG).show();
        }else
        {

            useralreadyexists = CheckUsername();

            if(useralreadyexists)
            {
                Toast.makeText(getApplicationContext(), "User Already Exists", Toast.LENGTH_LONG).show();
            }else
            {
                try{
                    String insert ="INSERT INTO " + Utilities.USERS_TABLE + " ("+ Utilities.USERNAME_COLUMN +
                            ","+ Utilities.EMAIL_COLUMN +","+ Utilities.PASSWORD_COLUMN +") VALUES ('"
                            + etUsername.getText().toString() + "','"+ etEmail.getText().toString()+
                            "','"+ etPassword.getText().toString()+ "') ";


                    db.execSQL(insert);
                    db.close();
                    Toast.makeText(getApplicationContext(), "Register Succesful!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent("com.erasmushelper.Home_Activity");
                    startActivity(intent);
                    clean();
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Dont Exist!", Toast.LENGTH_LONG).show();
                    clean();
                }
            }


        }


    }

    private Boolean CheckUsername() {

        conn = new ConexionSQLiteHelper(this,"db_users", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] params1 = {etUsername.getText().toString()};
        //String[] params2 = {etEmail.getText().toString()};
        Boolean flag = null;

        try{

            db.execSQL("SELECT * FROM " + Utilities.USERS_TABLE + " WHERE " +
                    Utilities.USERNAME_COLUMN + "=?", params1);

           // db.execSQL("SELECT * FROM " + Utilities.USERS_TABLE + " WHERE " +
                     //Utilities.EMAIL_COLUMN + "=?", params2);


            flag = false;

        }catch(Exception e){
            flag = true;
        }


        return (flag);
    }

    private void clean() {

        etUsername.setText("");
        etEmail.setText("");
        etPassword.setText("");
    }

}
