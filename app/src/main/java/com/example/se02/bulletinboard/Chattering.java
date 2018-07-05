package com.example.se02.bulletinboard;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Chattering extends AppCompatActivity {
    private TextView notice;
    private Button bt_back;
    private Button bt_write;
    String name;
    String Studentname;
    WriteDB helper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chattering);
        notice = (TextView) findViewById(R.id.noticeId);
        notice.setTextColor(Color.BLACK);
        Intent intent =getIntent();
        name = intent.getExtras().getString("name");
        Studentname = intent.getExtras().getString("Studentname");
        notice.setText(name+" 게시판");
        bt_back = (Button)findViewById(R.id.bt_back);
        // 빈 데이터 리스트 생성.
        final ArrayList<String> items = new ArrayList<String>() ;
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.

        // listview 생성 및 adapter 지정.
        final ListView listview = (ListView) findViewById(R.id.ListView1) ;
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
        bt_write = (Button)findViewById(R.id.bt_create_list);


        helper = new WriteDB(this,name);
        database = helper.getReadableDatabase();
        Cursor cursor = queryData();
        if(cursor != null){
            String[] columns = {"title","content","studentname"};
            int [] resIds = {R.id.text01,R.id.text02,R.id.text03};
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(Chattering.this,R.layout.listitem,cursor, columns,resIds);
            listview.setAdapter(adapter);
        }
        bt_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                int count;
//                count = adapter.getCount();
//
//                // 아이템 추가.
//                items.add("LIST" + Integer.toString(count + 1));
//
//                // listview 갱신
//                adapter.notifyDataSetChanged();
                Intent intent1 = new Intent(Chattering.this,WriteActivity.class);
                intent1.putExtra("studentName", Studentname);
                intent1.putExtra("name", name);
                startActivity(intent1);
                finish();
            }
        });

    }
    private Cursor queryData(){
        String sql = "select * from "+name+" order by _id DESC";
        Cursor cursor = database.rawQuery(sql, null);

        if(cursor != null){
            int count = cursor.getCount();
        }
        return cursor;
    }
}
