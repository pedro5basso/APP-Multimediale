package com.angel.erasmushelper;

import android.R.layout;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.angel.erasmushelper.entidades_t_usuarios.User;
import com.angel.erasmushelper.utilities.Utilities;

import java.util.ArrayList;

public class AdminActivity extends Activity {

    Button bttSearchUser, bttRefresh, bttDelete, bttShowDB;

    EditText etSearchUser, etUsername, etEmail, etPassword;

    ListView lvListDataBase;

    ArrayList<String> infoList;
    ArrayList<User> userList;
    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);

        conn = new ConexionSQLiteHelper(this,"db_users", null, 1);

        bttSearchUser = findViewById(R.id.bttSearch);
        bttRefresh = findViewById(R.id.bttRefresh);
        bttDelete = findViewById(R.id.bttDelete);
        bttShowDB = findViewById(R.id.bttShowdb);


        etSearchUser = findViewById(R.id.etSearchUsername);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPawssord);

        lvListDataBase = findViewById(R.id.lvListDataBase);



        bttSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchUser();
            }
        });

        bttShowDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDB();
                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, infoList);
                lvListDataBase.setAdapter(adapter);
            }
        });
        
        bttDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
            }
        });
        
        bttRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshUser();
            }
        });

        lvListDataBase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String info ="Username: " + userList.get(position).getUsername()+"\n";
                info += "Email: " + userList.get(position).getEmail()+"\n";
                info += "Password: " + userList.get(position).getPassword()+"\n";

                Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void refreshUser() {

        SQLiteDatabase db = conn.getWritableDatabase();
        String[] params = {etSearchUser.getText().toString()}; //parámetros a consultar
        ContentValues values = new ContentValues();
        values.put(Utilities.USERNAME_COLUMN,etUsername.getText().toString());
        values.put(Utilities.EMAIL_COLUMN,etEmail.getText().toString());
        values.put(Utilities.PASSWORD_COLUMN,etPassword.getText().toString());

        db.update(Utilities.USERS_TABLE,values,Utilities.USERNAME_COLUMN + "=?", params);
        Toast.makeText(getApplicationContext(),"Process Done", Toast.LENGTH_LONG).show();
        db.close();

    }

    private void deleteUser() {

        SQLiteDatabase db = conn.getWritableDatabase();
        String[] params = {etSearchUser.getText().toString()}; //parámetros a consultar

        db.delete(Utilities.USERS_TABLE,Utilities.USERNAME_COLUMN+"=?",params);
        Toast.makeText(getApplicationContext(),"Delete Success", Toast.LENGTH_LONG).show();
        etSearchUser.setText("");
        clean();
        db.close();

    }

    private void showDB() {

        //conn = new ConexionSQLiteHelper(this,"db_users", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();

        User user = null;
        userList = new ArrayList<User>();

        //select * from users

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilities.USERS_TABLE, null);

        while(cursor.moveToNext()){


            String username = cursor.getString(0);
            String email = cursor.getString(1);
            String password = cursor.getString(2);

            user = new User(username,email,password);

            userList.add(user);
        }
        obtainList();


    }

    private void obtainList() {

        infoList = new ArrayList<String>();

        for(int i = 0; i < userList.size(); i++){
            infoList.add(userList.get(i).getUsername() + " - " + userList.get(i).getEmail() + " - "+
                    userList.get(i).getPassword());
        }
    }

    private void searchUser() {

        SQLiteDatabase db = conn.getReadableDatabase();

        String[] params = {etSearchUser.getText().toString()}; //parámetros a consultar

        try{

            //SELECT username,email,password FROM users WHERE username=?
            Cursor cursor = db.rawQuery("SELECT "+ Utilities.USERNAME_COLUMN + "," + Utilities.EMAIL_COLUMN + "," + Utilities.PASSWORD_COLUMN+
                    " FROM " + Utilities.USERS_TABLE + " WHERE " + Utilities.USERNAME_COLUMN + "=?", params);

            cursor.moveToFirst();

            etUsername.setText(cursor.getString(0));
            etEmail.setText(cursor.getString(1));
            etPassword.setText(cursor.getString(2));

            cursor.close();




        }catch (Exception e){

            Toast.makeText(getApplicationContext(), "Dont Exist!", Toast.LENGTH_LONG).show();
            clean();
        }


    }

    private void clean() {

        etUsername.setText("");
        etEmail.setText("");
        etPassword.setText("");
    }

    //funcionalidades: buscar en la DB, modificar usuario, borrar usuario y mostrar la base de datos.
}
