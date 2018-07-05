package com.example.se02.bulletinboard;

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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class ThirdFragment extends Fragment {
    ArrayAdapter<CharSequence> adspin1, adspin2;
    Button button;
    LinearLayout layout;
    Spinner spinner2;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        layout = (LinearLayout) inflater.inflate(R.layout.activity_third_rragment, container, false);
        Spinner spinner = (Spinner)layout.findViewById(R.id.spinner);
        spinner2 = (Spinner) layout.findViewById(R.id.spinner2);
        adspin1 = ArrayAdapter.createFromResource(layout.getContext(), R.array.school_subject, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adspin1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("adspin1:",""+i);
                if (adspin1.getItem(i).equals("공과대학")) {
                    adspin2 = ArrayAdapter.createFromResource(layout.getContext(), R.array.it_subject, android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(adspin2);
                }
                if(adspin1.getItem(i).equals("경영대학")){
                    adspin2 = ArrayAdapter.createFromResource(layout.getContext(), R.array.Business_subject, android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(adspin2);
                }
                if(adspin1.getItem(i).equals("보건복지교육대학")){
                    adspin2 = ArrayAdapter.createFromResource(layout.getContext(), R.array.health_subject, android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(adspin2);
                }
                if(adspin1.getItem(i).equals("건축디자인대학")||adspin1.getItem(i).equals("인문사회대학")){
                    adspin2 = ArrayAdapter.createFromResource(layout.getContext(), R.array.wait_subject, android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(adspin2);
                    Toast toast = Toast.makeText(layout.getContext(),"서비스준비중입니다.",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button = (Button)layout.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spinner2.getSelectedItem().toString().equals("컴퓨터공학과")) {

                }
                else{
                    Toast toast = Toast.makeText(layout.getContext(),"서비스준비중입니다^^ 컴퓨터공학과 만 사용가능",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        return layout;
    }
}
