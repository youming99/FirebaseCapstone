package halla.icsw.capstone_hym;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gugudan_SimpleGame_Activity extends AppCompatActivity {

    private Vibrator vibrator; // 진동 선언

    String selectDan = ""; // 게임 범위 선택한 이름
    TextView questionText, answerText, countText, timerText, gameSelectText;

    int dan = 0; // 선택할 단 int형
    int answer = 0; // 정답 int형
    int count = 0; // 정답 갯수 int형
    int i; // 0-9 버튼

    Button button, buttonCancel, buttonSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gugudan__simple_game_);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); // 진동

        questionText = findViewById(R.id.questionText); // 문제 텍스트
        answerText = findViewById(R.id.answerText); // 정답 텍스트
        countText = findViewById(R.id.countText); // 정답 갯수 텍스트
        timerText = findViewById(R.id.timerText); // 타이머 텍스트
        gameSelectText = findViewById(R.id.gameSelectText); // 게임 범위 정하기 텍스트

        findViewById(R.id.gameSelectText).setOnClickListener(onClick); // 게임 선택하기 버튼
        findViewById(R.id.buttonCancel).setOnClickListener(onClickButton); //지우기 버튼
        findViewById(R.id.buttonSend).setOnClickListener(onClickButton); //제출 버튼

        gameStart();


    }

    //게임 범위 선택 onClick
    private TextView.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.gameSelectText:
                    GugudanSelectDialog();
                    break;
            }
        }
    };

    //구구단 게임 범위 선택하는 다이얼로그
    void GugudanSelectDialog() {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("Easy Mode (2-9단)");
        ListItems.add("Hard Mode (2-19단)");
        ListItems.add("2단");
        ListItems.add("3단");
        ListItems.add("4단");
        ListItems.add("5단");
        ListItems.add("6단");
        ListItems.add("7단");
        ListItems.add("8단");
        ListItems.add("9단");
        final CharSequence[] items =  ListItems.toArray(new String[ ListItems.size()]);

        final List SelectedItems  = new ArrayList();
        int defaultItem = 0;
        SelectedItems.add(defaultItem);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("게임 범위 선택하기");
        builder.setSingleChoiceItems(items, defaultItem,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SelectedItems.clear();
                        SelectedItems.add(which);
                    }
                });
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (!SelectedItems.isEmpty()) {
                            int index = (int) SelectedItems.get(0);
                            selectDan = ListItems.get(index);
                        }
                        gameStart();
                        gameSelectText.setText(selectDan + " 게임 중!");

                        // TODO : 타이머 돌릴 총시간
                        String conversionTime = "000030";

                        // 카운트 다운 시작
                        countDown(conversionTime);
                    }
                });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = builder.show();
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
        alertDialog.show();
    }

    void gameStart(){
        Random random1 = new Random(); // 1-9 랜덤 숫자
        Random random2 = new Random(); // 2-9 랜덤 숫자
        Random random3 = new Random(); // 2-19 랜덤 숫자
        Random random4 = new Random(); // 2-19 랜덤 숫자
        int randNum = random1.nextInt(9)+1;
        int randNum2 = random2.nextInt(9)+2;
        int randNum3 = random3.nextInt(19)+2;
        int randNum4 = random4.nextInt(19)+2;
        for (dan=2; dan<=9; dan++) {
            if (selectDan.equals(dan + "단")) {
                questionText.setText(dan + " * " + randNum + " =  ?");
                answer = dan*randNum;
            }
            else if(selectDan.equals("Easy Mode (2-9단)")) {
                questionText.setText(randNum2 + " * " + randNum + " =  ?");
                answer = randNum2 * randNum;
            }
            else if(selectDan.equals("Hard Mode (2-19단)")) {
                questionText.setText(randNum3 + " * " + randNum4 + " =  ?");
                answer = randNum3 * randNum4;
            }
        }
    }

    //버튼 0-9 선언
    public void buttonOnclick(View v) {
        for(i=0; i<=9;i++) {
            button = findViewById(R.id.button0 + i); //버튼 id : b0부터 b9
        }
        button = (Button) v;
        String strnumber = button.getText().toString();
        answerText.setText(answerText.getText().toString()+strnumber);
    }

    // 지우기, 제출 버튼
    Button.OnClickListener onClickButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.buttonCancel: // buttonCancel
                    answerText.setText("");
                    break;
                case R.id.buttonSend: // buttonSend
                    // 문제 맞출 시
                    if(answerText.getText().toString().equals(String.valueOf(answer))){
                        count++;
                        countText.setText(String.valueOf(count));
                        vibrator.vibrate(30); // 0.03초 진동
                        answerText.setText("");
                    }
                    //문제 틀릴 시
                    else if(!answerText.getText().toString().equals(String.valueOf(answer))) {
                        long[] pattern = {30,45,30,30}; // 0.03초 진동, 0.45초 대기, 0.03초 진동 패턴
                        vibrator.vibrate(pattern,-1); // -1은 반복 안함
                        answerText.setText("");
                    }
                    gameStart();
                    break;
            }
        }
    };
    //타이머
    public void countDown(String time) {
        long conversionTime = 0;
        // 1000 단위가 1초
        // 60000 단위가 1분
        // 60000 * 3600 = 1시간
        String getHour = time.substring(0, 2);
        String getMin = time.substring(2, 4);
        String getSecond = time.substring(4, 6);

        // "00"이 아니고, 첫번째 자리가 0 이면 제거
        if (getHour.substring(0, 1) == "0") {
            getHour = getHour.substring(1, 2);
        }
        if (getMin.substring(0, 1) == "0") {
            getMin = getMin.substring(1, 2);
        }
        if (getSecond.substring(0, 1) == "0") {
            getSecond = getSecond.substring(1, 2);
        }

        // 변환시간
        conversionTime = Long.valueOf(getHour) * 1000 * 3600 + Long.valueOf(getMin) * 60 * 1000 + Long.valueOf(getSecond) * 1000;
        // 첫번쨰 인자 : 원하는 시간 (예를들어 30초면 30 x 1000(주기))
        // 두번쨰 인자 : 주기( 1000 = 1초)
        new CountDownTimer(conversionTime, 1000) {
            // 특정 시간마다 뷰 변경
            public void onTick(long millisUntilFinished) {
                // 시간단위
                String hour = String.valueOf(millisUntilFinished / (60 * 60 * 1000));
                // 분단위
                long getMin = millisUntilFinished - (millisUntilFinished / (60 * 60 * 1000)) ;
                String min = String.valueOf(getMin / (60 * 1000)); // 몫
                // 초단위
                String second = String.valueOf((getMin % (60 * 1000)) / 1000); // 나머지
                // 밀리세컨드 단위
                String millis = String.valueOf((getMin % (60 * 1000)) % 1000); // 몫
                // 시간이 한자리면 0을 붙인다
                if (hour.length() == 1) {
                    hour = "0" + hour;
                }
                // 분이 한자리면 0을 붙인다
                if (min.length() == 1) {
                    min = "0" + min;
                }
                // 초가 한자리면 0을 붙인다
                if (second.length() == 1) {
                    second = "0" + second;
                }
                timerText.setText(hour + ":" + min + ":" + second);
            }

            // 제한시간 종료시
            public void onFinish() {
                // 변경 후
                timerText.setText("게임 끝!");
                // TODO : 타이머가 모두 종료될때 어떤 이벤트를 진행할지
                gameFinishDialog();
            }
        }.start();
    }


    void gameFinishDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("게임 결과!");
        builder
                .setMessage(selectDan+" 에서\n" +
                        count+" 개 맞추셨습니다.")
                .setCancelable(false)
                .setPositiveButton("종료",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 프로그램을 종료한다
                                Intent intent = new Intent(Gugudan_SimpleGame_Activity.this,HomeActivity.class);
                                startActivity(intent);
                            }
                        });

        AlertDialog alertDialog = builder.show();
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
        alertDialog.show();
    }

}
