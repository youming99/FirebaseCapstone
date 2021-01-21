package halla.icsw.capstone_hym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Gugudan2_9Activity extends AppCompatActivity {

    String danName = "";
    String[] danList;
    ListView listView;
    TextView danText;
    int btRightCount = 0;
    int btLeftCount = 0;
    int dan = 0;

    TextView leftText;
    TextView rightText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gugudan2_9);

        danText = findViewById(R.id.danText);
        listView = findViewById(R.id.listView);
        leftText = findViewById(R.id.leftButton);
        rightText = findViewById(R.id.rightButton);
        leftText.setVisibility(View.VISIBLE);
        rightText.setVisibility(View.VISIBLE);

        //Gugudan_Study_Activity에서 Intent 값 받아옴
        Intent intent = getIntent();
        danName = intent.getStringExtra("danName");

        danText.setText(danName);

        danList = new String[9];

        for (dan = 2; dan <= 9; dan ++) {
            if (danName.equals(dan + " 단")) {
                final ArrayList<String> danArrayList = new ArrayList<>();
                for (int i=1; i<=9; i++){
                    danArrayList.add(dan + " * " + i +" = "+ dan*i);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        this, R.layout.list_style, danArrayList);
                listView.setAdapter(adapter);

                if(dan==2){
                    leftText.setVisibility(View.INVISIBLE);
                }
                else if(dan==9){
                    rightText.setVisibility(View.INVISIBLE);
                }

            }
        }

    }

    public void leftOnclick(View view) {
        rightText.setVisibility(View.VISIBLE);
        for (dan = 2; dan <= 9; dan++) {
            if (danName.equals(dan + " 단")) {

                btRightCount--;

                if(btRightCount>=0) {
                    dan += btRightCount;
                    if ((dan <= 9 && dan>=2 ) || (btRightCount+dan <=9 && btRightCount+dan>=2)) {
                        danText.setText(dan + " 단");
                        final ArrayList<String> danArrayList = new ArrayList<>();
                        for (int i = 1; i <= 9; i++) {
                            danArrayList.add(dan + " * " + i + " = " + dan * i);
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                this, R.layout.list_style, danArrayList);
                        listView.setAdapter(adapter);

                        if(dan==2) {
                            leftText.setVisibility(View.INVISIBLE);
                        }
                    }
                }

                else if (btRightCount<0) {
                    if (btRightCount+dan>=2){
                        danText.setText((btRightCount + dan) + " 단");
                        final ArrayList<String> danArrayList = new ArrayList<>();
                        for (int i = 1; i <= 9; i++) {
                            danArrayList.add((btRightCount + dan) + " * " + i + " = " + (btRightCount + dan) * i);
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                this, R.layout.list_style, danArrayList);
                        listView.setAdapter(adapter);

                        if((btRightCount+dan)==2) {
                            leftText.setVisibility(View.INVISIBLE);
                        }
                    }

                }

            }
        }
    }


    public void rightOnclick(View view) {
        leftText.setVisibility(View.VISIBLE);
        for (dan = 2; dan <= 9; dan++) {
            if (danName.equals(dan + " 단")) {
                btRightCount++;

                if(btRightCount>=0) {
                    dan += btRightCount;

                    if ((dan <= 9 && dan>=2 ) || (btRightCount+dan <=9 && btRightCount+dan>=2)) {
                        danText.setText(dan + " 단");
                        final ArrayList<String> danArrayList = new ArrayList<>();
                        for (int i = 1; i <= 9; i++) {
                            danArrayList.add(dan + " * " + i + " = " + dan * i);
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                this, R.layout.list_style, danArrayList);
                        listView.setAdapter(adapter);

                        if(dan==9) {
                            rightText.setVisibility(View.INVISIBLE);
                        }
                    }
                }
                else if (btRightCount<0) {
                    if ((dan <= 9 && dan>=2 ) || (btRightCount+dan <=9 || btRightCount+dan>=2)) {
                        danText.setText(btRightCount + dan + " 단");
                        final ArrayList<String> danArrayList = new ArrayList<>();
                        for (int i = 1; i <= 9; i++) {
                            danArrayList.add((btRightCount + dan) + " * " + i + " = " + (btRightCount + dan) * i);
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                this, R.layout.list_style, danArrayList);
                        listView.setAdapter(adapter);

                        if((btRightCount+dan)==9) {
                            rightText.setVisibility(View.INVISIBLE);
                        }
                    }
                }

            }
        }
    }


}
