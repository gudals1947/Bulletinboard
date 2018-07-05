package com.example.se02.bulletinboard;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FirstFragment extends Fragment {
    SQLiteDatabase database;
    FullWriteDB helper;

   public FirstFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_first_fragment, container, false);
        TextView textView = (TextView)layout.findViewById(R.id.tv_studentname);
        String name=((NoticeActivity)getContext()).getName().toString();
        Log.v("이름은:",""+name);
        textView.setText(name+"님 안녕하세요!");
        ListView listView = (ListView)layout.findViewById(R.id.list01);
        helper = new FullWriteDB(layout.getContext());
        database = helper.getReadableDatabase();
        Cursor cursor = queryData();
        if(cursor != null){
            String[] columns = {"title","content","studentname"};
            int [] resIds = {R.id.text01,R.id.text02,R.id.text03};
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(layout.getContext(),R.layout.listitem,cursor, columns,resIds);
            listView.setAdapter(adapter);
        }
        return layout;
    }
    private Cursor queryData(){
        String sql = "select * from Full order by _id DESC";
        Cursor cursor = database.rawQuery(sql, null);

        if(cursor != null){
            int count = cursor.getCount();
        }
        return cursor;
    }

}
