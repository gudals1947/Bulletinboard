package com.example.se02.bulletinboard;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SecondFragment extends Fragment {

    private ListView m_ListView;
    private ArrayAdapter<String> m_Adapter;
    String [] data = {  "수다","온라인게임" , "보드게임", "정건우", "국내축구" ,"해외축구" ,"국내야구" ,"해외야구"
    }; // 다량의 데이터

    String studentname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_second_fragment, container, false);
        m_Adapter = new ArrayAdapter<String>(layout.getContext(), android.R.layout.simple_list_item_1,data);
//
        m_ListView = (ListView) layout.findViewById(R.id.list_item);
        studentname=((NoticeActivity)getContext()).getName().toString();
        m_ListView.setAdapter(m_Adapter);

        m_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("i의값은:",""+i);
                Intent intent = new Intent(layout.getContext(),Chattering.class);
                intent.putExtra("name", data[i]);
                intent.putExtra("index", i);
                intent.putExtra("Studentname",studentname);
                startActivity(intent);
            }
        });

        return layout;
    }
}
