package halla.icsw.capstone_hym;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class GugudanActivity extends AppCompatActivity {

    AlertDialog.Builder builder;
    AlertDialog.Builder builder2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gugudan);

        findViewById(R.id.gugudanunderstandText).setOnClickListener(onClick); // 구구단 이해하기
        findViewById(R.id.gugudanstudyText).setOnClickListener(onClick); // 구구단 공부하기
        findViewById(R.id.gugudanReviewText).setOnClickListener(onClick); // 구구단 복습하기
        findViewById(R.id.gugudangameText).setOnClickListener(onClick); // 구구단 게임하기
        findViewById(R.id.gugudanrankText).setOnClickListener(onClick); // 구구단 랭킹
    }

    private TextView.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.gugudanunderstandText: // 구구단 이해하기
                    gugudanUnderstandDialog();
                    break;
                case R.id.gugudanstudyText: // 구구단 공부하기
//                    Intent intent2 = new Intent(GugudanActivity.this, Gugudan_Study_Activity.class);
//                    startActivity(intent2);
                    gugudanStudyDialog();
                    break;
                case R.id.gugudanReviewText: // 구구단 복습하기
//                    Intent intent2 = new Intent(GugudanActivity.this, Gugudan_Study_Activity.class);
//                    startActivity(intent2);
                    break;
                case R.id.gugudangameText: // 구구단 게임하기
                    //Intent intent = new Intent(MainActivity.this,Game2Activity.class);
//                  startActivity(intent);
                    break;
                case R.id.gugudanrankText: // 구구단 랭킹
                    break;

            }
        }
    };

    //구구단 이해하기 다이얼로그
    void gugudanUnderstandDialog() {
        final CharSequence[] understand = {"구구단송", "구구단 이해하기"};
        builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("공부할 모드 고르기");
        builder2.setItems(understand, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,
                                int id) {
                if (understand[id].equals("구구단송")) {
                    String url ="https://www.youtube.com/watch?v=6EGjhlWU4xI";
                    Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent2);

                }
                if (understand[id].equals("구구단 이해하기")) {
                    String url ="https://www.youtube.com/watch?v=5qx75Cb8Nqw";
                    Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent2);
                }
                //dialog.dismiss();
            }
        });
        AlertDialog alertDialog2 = builder2.show();
        alertDialog2.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
        alertDialog2.show();
    }


    //구구단 공부하기 다이얼로그
    void gugudanStudyDialog(){
        final CharSequence[] studys = {"2단 ~ 9단", "2단 ~ 19단", "단순 게임으로 암기하기"};
        builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("공부할 모드 고르기");
        builder2.setItems(studys, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,
                                int id) {
                if (studys[id].equals("2단 ~ 9단")){
                    Intent intent = new Intent(GugudanActivity.this,Gugudan_Study_Activity.class);
                    intent.putExtra("study", studys[id]);
                    startActivity(intent);
                }
                if (studys[id].equals("2단 ~ 19단")){
                    Intent intent = new Intent(GugudanActivity.this,Gugudan_Study2_Activity.class);
                    intent.putExtra("study", studys[id]);
                    startActivity(intent);
                }
                if (studys[id].equals("단순 게임으로 암기하기")){
                    Intent intent = new Intent(GugudanActivity.this,Gugudan_SimpleGame_Activity.class);
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


    //구구단 게임하기 다이얼로그
//    void gugudanGameDialog()
//    {
//        final CharSequence[] games = { "간단한 게임 하기", "자동차 게임 하기"};
//        builder = new AlertDialog.Builder(this);
//        builder.setTitle("게임 고르기");
//        builder.setItems(games, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog,
//                                int id) {
//                if (games[id].equals("간단한 게임 하기")){
//                    Intent intent = new Intent(GugudanActivity.this,Gugudan_SimpleGame_Activity.class);
//                    startActivity(intent);
//                }
//                if (games[id].equals("자동차 게임 하기")){
////                            Intent intent = new Intent(MainActivity.this,Game2Activity.class);
////                            startActivity(intent);
//                }
//                //dialog.dismiss();
//            }
//        });
//
//
//        AlertDialog alertDialog = builder.show();
//        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
//        alertDialog.show();
//    }

}
