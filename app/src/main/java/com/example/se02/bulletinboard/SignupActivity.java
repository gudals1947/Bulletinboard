package com.example.se02.bulletinboard;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by 94_08 on 2017-11-26.
 */

public class SignupActivity extends AppCompatActivity {
    private EditText tv_Password;
    private EditText tv_Repassword;
    private EditText tv_Id;
    private EditText tv_Name;
    private boolean condition;
    MemberDB mb_db = new MemberDB(this);
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button bt_MemberCreate = (Button) findViewById(R.id.signup_bt_MemberJoin);
        tv_Password = (EditText) findViewById(R.id.signup_password);
        tv_Repassword = (EditText) findViewById(R.id.signup_password_confirmation);
        tv_Id = (EditText) findViewById(R.id.signup_student_Member);
        tv_Name = (EditText) findViewById(R.id.signup_Name);


        bt_MemberCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("password", "" + tv_Password.getText().toString());
                Log.v("Re_password", "" + tv_Repassword.getText().toString());
                condition = true;
                if(!tv_Password.getText().toString().equals(tv_Repassword.getText().toString())){
                    condition = false;
                    Toast toast = Toast.makeText(SignupActivity.this,"비밀번호를 다시 확인해주세요",Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(tv_Id.getText().toString().equals("")){
                    condition = false;
                    Toast toast = Toast.makeText(SignupActivity.this,"아이디를 입력해주세요",Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(tv_Password.getText().toString().equals("")||tv_Repassword.getText().toString().equals("")||tv_Name.getText().toString().equals("")){
                    condition = false;
                    Toast toast = Toast.makeText(SignupActivity.this,"정보들을 다시 입력해주세요.",Toast.LENGTH_LONG);
                    toast.show();
                }

                if(condition) {
                    try {
                        condition = Find_Search(tv_Id.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(condition){
                    try {
                        Success(tv_Id,tv_Password,tv_Name);
                        Toast toast = Toast.makeText(SignupActivity.this,"가입완료",Toast.LENGTH_SHORT);
                        toast.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }


        });
    }
    private void Success(EditText tv_id, EditText tv_password, EditText tv_name)throws  IOException {
        Log.v("tag","회원가입을 했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + mb_db.TABLE_NAME + "(id, password,name)" +
                    "values('"+ tv_id.getText().toString() +"', '"+ tv_password.getText().toString() +"', '"+ tv_name.getText().toString() +"')";
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }
    private boolean Find_Search(String s) throws IOException{
        boolean condition=true;
        String sql;
        db = mb_db.getReadableDatabase();
        Cursor cursor;
        sql = "SELECT " +mb_db.ID+ " FROM "+ mb_db.TABLE_NAME + " WHERE "+mb_db.ID+" = " + s + "";
        Log.v("id값:",""+sql);
        cursor = db.rawQuery(sql, null);
//        Log.v("cursor",""+cursor.getCount());
        if(cursor.getCount() != 0){
            //존재하는 아이디입니다.
            condition = false;
            Toast toast = Toast.makeText(SignupActivity.this, "존재하는 아이디입니다.", Toast.LENGTH_SHORT);
            toast.show();
        }
        return condition;
    }
}
