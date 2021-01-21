package halla.icsw.capstone_hym;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CountryActivity extends AppCompatActivity {

    AlertDialog.Builder builder;
    AlertDialog.Builder builder2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        findViewById(R.id.countryStudyText).setOnClickListener(onClick); // 나라수도 공부하기
        findViewById(R.id.countryGameText).setOnClickListener(onClick); // 나라수도 게임하기
        findViewById(R.id.countryRankText).setOnClickListener(onClick); // 나라수도 랭킹
    }

    private TextView.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.countryStudyText: // 나라수도 공부하기
//                    Intent intent2 = new Intent(GugudanActivity.this, Gugudan_Study_Activity.class);
//                    startActivity(intent2);
                    countryStudyDialog();
                    break;
                case R.id.countryGameText: // 나라수도 게임하기
                    countryGameDialog();
                    break;
                case R.id.countryRankText: // 나라수도 랭킹
                    break;

            }
        }
    };



    //나라수도 공부하기 다이얼로그
    void countryStudyDialog(){
        final CharSequence[] studys = {"아시아", "유럽", "북아메리카(북미)", "남아메리카(남미)", "아프리카", "오세아니아"};
        builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("공부할 모드 고르기");
        builder2.setItems(studys, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,
                                int id) {
                if (studys[id].equals("아시아")){
                    Intent intent = new Intent(CountryActivity.this,Country_Listview_Activity.class);
                    intent.putExtra("study", studys[id]);
                    startActivity(intent);
                }
                else if (studys[id].equals("유럽")){
                    Intent intent = new Intent(CountryActivity.this,Country_Listview_Activity.class);
                    intent.putExtra("study", studys[id]);
                    startActivity(intent);
                }
                else if (studys[id].equals("북아메리카(북미)")){
                    Intent intent = new Intent(CountryActivity.this,Country_Listview_Activity.class);
                    intent.putExtra("study", studys[id]);
                    startActivity(intent);
                }
                else if (studys[id].equals("남아메리카(남미)")){
                    Intent intent = new Intent(CountryActivity.this,Country_Listview_Activity.class);
                    intent.putExtra("study", studys[id]);
                    startActivity(intent);
                }
                else if (studys[id].equals("아프리카")){
                    Intent intent = new Intent(CountryActivity.this,Country_Listview_Activity.class);
                    intent.putExtra("study", studys[id]);
                    startActivity(intent);
                }
                else if (studys[id].equals("오세아니아")){
                    Intent intent = new Intent(CountryActivity.this,Country_Listview_Activity.class);
                    intent.putExtra("study", studys[id]);
                    startActivity(intent);
                }
                //dialog.dismiss();
            }
        });


        AlertDialog alertDialog = builder2.show();
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
        alertDialog.show();
    }


    //나라수도 게임하기 다이얼로그
    void countryGameDialog()
    {
        final CharSequence[] games = {"아시아", "유럽", "북아메리카(북미)", "남아메리카(남미)", "아프리카", "오세아니아", "전세계"};
        builder = new AlertDialog.Builder(this);
        builder.setTitle("게임할 대륙 고르기");
        builder.setItems(games, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (games[id].equals("아시아")){
                    Intent intent = new Intent(CountryActivity.this,Country_Game1_Activity.class);
                    intent.putExtra("game", games[id]);
                    startActivity(intent);
                }
                else if (games[id].equals("유럽")){
                    Intent intent = new Intent(CountryActivity.this,Country_Game1_Activity.class);
                    intent.putExtra("game", games[id]);
                    startActivity(intent);
                }
                else if (games[id].equals("북아메리카(북미)")){
                    Intent intent = new Intent(CountryActivity.this,Country_Game1_Activity.class);
                    intent.putExtra("game", games[id]);
                    startActivity(intent);
                }
                else if (games[id].equals("남아메리카(남미)")){
                    Intent intent = new Intent(CountryActivity.this,Country_Game1_Activity.class);
                    intent.putExtra("game", games[id]);
                    startActivity(intent);
                }
                else if (games[id].equals("아프리카")){
                    Intent intent = new Intent(CountryActivity.this,Country_Game1_Activity.class);
                    intent.putExtra("game", games[id]);
                    startActivity(intent);
                }
                else if (games[id].equals("오세아니아")){
                    Intent intent = new Intent(CountryActivity.this,Country_Game1_Activity.class);
                    intent.putExtra("game", games[id]);
                    startActivity(intent);
                }
                else if (games[id].equals("전세계")){
                    Intent intent = new Intent(CountryActivity.this,Country_Game1_Activity.class);
                    intent.putExtra("game", games[id]);
                    startActivity(intent);
                }
                //dialog.dismiss();
            }
        });


        AlertDialog alertDialog = builder.show();
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
        alertDialog.show();
    }

}
