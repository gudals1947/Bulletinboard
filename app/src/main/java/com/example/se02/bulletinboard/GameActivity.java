package com.example.se02.bulletinboard;


import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.example.se02.bulletinboard.customadapter.AnsAdapter;
import com.example.se02.bulletinboard.customadapter.AnsListBean;

import java.util.ArrayList;

public class GameActivity extends ActionBarActivity {
    private int ranNum;
    private int cnt = 0;
    private AnsAdapter ansAdapter;
    private ArrayList<AnsListBean> ansArrayList = new ArrayList<AnsListBean>();
    public static boolean isCorrect = false;
    public static Context context;


    EditText ansEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        context=this;
        ranNum =generateRanNum(context);
        ansEditText = (EditText) findViewById(R.id.ansEditTextId);
        ListView ansList = (ListView) findViewById(R.id.ansListId);
        ansAdapter = new AnsAdapter(this,R.layout.anslist, ansArrayList);
        ansList.setAdapter(ansAdapter);
    }

    private int generateRanNum(Context context) {
        BaseballGame m = new BaseballGame(context);
        int ranNum = m.genRanNum();
        return ranNum;
    }

    public void mOnClick(View v) {
        switch(v.getId()){
            case R.id.ansBtnId:
                cnt++;
                ansArrayList = doCompare();
                ansEditText.setText("");
                ansAdapter.notifyDataSetChanged();

                if(isCorrect) {
                    alertDialog();
                    isCorrect = !isCorrect;
                }
                break;
        }
    }

    private void alertDialog() {
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ranNum = generateRanNum(context);
                ansArrayList.clear();
                ansAdapter.notifyDataSetChanged();
            }
        };

        Builder builder = new Builder(this);
        builder.setTitle("정답입니다.(시도회수 : " + cnt + ")\n재시작하시겠습니까?");
        builder.setPositiveButton("Yes", listener);
        builder.setNegativeButton("No", null);
        cnt = 0;
        builder.show();
    }


    private ArrayList<AnsListBean> doCompare() {
        BaseballGame m = new BaseballGame(getApplicationContext());
        String ansNum = (String) ansEditText.getText().toString();
        int ans = m.isValid(ansNum);

        if( ans != -1 ) {
            String result = m.process(ans, ranNum);
            System.out.println(result);
            AnsListBean ansListBean = new AnsListBean();
            ansListBean.setAnsNum(ansNum);
            if(!result.contains("4 strike")) {
                ansListBean.setAnsResult(result);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            } else {
                ansListBean.setAnsResult("정답");
                isCorrect = !isCorrect;
            }
            ansArrayList.add(ansListBean);
        }
        return ansArrayList;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
