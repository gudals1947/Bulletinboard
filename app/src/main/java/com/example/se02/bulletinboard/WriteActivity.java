package com.example.se02.bulletinboard;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.se02.bulletinboard.R;

public class WriteActivity extends AppCompatActivity {
    String name;
    String studentName;
    TextView textView;
    Button bt_create;
    EditText title;
    EditText context;
    WriteDB  mb_db;
    SQLiteDatabase db;
    FullWriteDB  mb_db2;
    SQLiteDatabase db2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        final Intent intent =getIntent();
        intent.getExtras();
        name = intent.getExtras().getString("name");
        studentName=intent.getExtras().getString("studentName");
        textView=(TextView)findViewById(R.id.tv_author);
        title =(EditText)findViewById(R.id.ed_title);
        context =(EditText)findViewById(R.id.insert_memo_edit);
        textView.setText(studentName);
        bt_create =(Button)findViewById(R.id.bt_create);
        mb_db=new WriteDB(this,name);
        mb_db2=new FullWriteDB(this);
        db = mb_db.getWritableDatabase();
        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean succers=true;
                if(title.getText().toString().equals("")){
                    Toast toast = Toast.makeText(WriteActivity.this,"제목을 입력하세요",Toast.LENGTH_SHORT);
                    toast.show();
                    succers=false;
                }
                if(context.getText().toString().equals("")){
                    Toast toast = Toast.makeText(WriteActivity.this,"내용을 입력하세요",Toast.LENGTH_SHORT);
                    toast.show();
                    succers=false;
                }
                if(succers){

                    db.beginTransaction();
                    try {
                        String sql = "INSERT INTO " + mb_db.TABLE_NAME2 + "(title, content,studentname)" +
                                "values('"+ title.getText().toString() +"', '"+ context.getText().toString() +"', '"+ textView.getText().toString() +"')";
                        db.execSQL(sql);
                        db.setTransactionSuccessful();

                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        db.endTransaction();
                    }

                    try {
                        db2 = mb_db2.getWritableDatabase();
                        db2.beginTransaction();
                        String sql = "INSERT INTO " + mb_db2.TABLE_NAME2 + "(title, content,studentname)" +
                                "values('"+ title.getText().toString() +"', '"+ context.getText().toString() +"', '"+ textView.getText().toString() +"')";
                        db2.execSQL(sql);
                        db2.setTransactionSuccessful();

                    }
                    catch (Exception e1){
                        e1.printStackTrace();
                    }finally {
                        db2.endTransaction();
                    }
                    Intent intent = new Intent(WriteActivity.this,Chattering.class);
                    intent.putExtra("studentName", studentName);
                    intent.putExtra("name", name);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

}