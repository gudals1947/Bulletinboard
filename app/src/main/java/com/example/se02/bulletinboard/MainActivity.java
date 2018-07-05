package com.example.se02.bulletinboard;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button bt_signup;
    private Button bt_Login;
    private EditText tf_id;
    private EditText tf_password;
    MemberDB mb_db = new MemberDB(this);
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_signup = (Button) findViewById(R.id.signupButton);
        tf_id =(EditText)findViewById(R.id.Member_number_input);
        tf_password =(EditText)findViewById(R.id.passwordInput);



        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        bt_Login = (Button) findViewById(R.id.loginButton);

        bt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = tf_id.getText().toString();
                String password =tf_password.getText().toString();
                String name="";
                Cursor cursor;
                boolean condition=true;
                int n=0;
                db = mb_db.getReadableDatabase();
                String sql = "SELECT " +mb_db.ID+" , "+mb_db.PASSWORD+", "+mb_db.NAME+
                        " FROM "+ mb_db.TABLE_NAME + " WHERE "+mb_db.ID+" = " + id + " and "+mb_db.PASSWORD+" = '"+password+"'" ;

                try {
                    cursor = db.rawQuery(sql, null);
                    cursor.moveToFirst();
                    Log.v("확인값은:", ":" + cursor.getString(0));
                    Log.v("확인값은:", ":" + cursor.getString(1));
                    Log.v("확인값은:", ":" + cursor.getString(2));
                    name = cursor.getString(2);
                }catch (Exception e){
                    Log.v("에러","입니다"+e);
                    condition=false;
                }

                if(!condition){
                    Toast toast=Toast.makeText(MainActivity.this,"아이디와 비밀번호를 확인해주세요.",Toast.LENGTH_SHORT);
                    toast.show();
                }
                if(condition) {
                    Toast toast =Toast.makeText(MainActivity.this,name+" 학생님 환영합니다",Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
                    intent.putExtra("studentName", name);
                    startActivity(intent);
                    tf_id.setText("");
                    tf_password.setText("");

                }
            }
        });
    }
}
