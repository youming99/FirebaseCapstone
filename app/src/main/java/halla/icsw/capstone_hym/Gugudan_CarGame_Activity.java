package halla.icsw.capstone_hym;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Gugudan_CarGame_Activity extends AppCompatActivity {

            private Vibrator vibrator; // 진동 선언

            FrameLayout frameLayout;

            TextView answer0, answer1, answer2, answer3;
            TextView questionText;
            ImageView road; // GIF 이미지
            ImageView car; // 자동차 이미지

            String gameMode = "자동차 게임";

            float location; // 현재 자동차의 x값 위치
            float wlocation; // 레이아웃/4*1의 위치
            float lastlocation; //정답 확인시 자동차의 위치
            int startWidth; // 화면의 시작 x값
            int endWidth; // 화면의 끝 x값
            int viewWidth; // 레이아웃 끝 x값


            TextView textViews;
            int AA = 2;
            int left = 0;
            int right = 0;
            int answer = 0;
            int array[];
            int speed = 9000;
            int life = 0;
            int count = 0;

            ImageView life1, life2, life3;

            TimerTask timerTask;
            Timer timer;
            MyView view;


            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_gugudan__car_game_);

                vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); // 진동

                view = (MyView)findViewById(R.id.view);


                // 선언
                road = (ImageView) findViewById(R.id.gif_image);
                car = (ImageView) findViewById(R.id.car);
                questionText=(TextView)findViewById(R.id.question);
                answer0 = (TextView) findViewById(R.id.answer0);
                answer1 = (TextView) findViewById(R.id.answer1);
                answer2 = (TextView) findViewById(R.id.answer2);
                answer3 = (TextView) findViewById(R.id.answer3);

                life1 = findViewById(R.id.life1);
                life2 = findViewById(R.id.life2);
                life3 = findViewById(R.id.life3);

                // GIF 배경화면
                Glide.with(this)
                        .load(R.drawable.road6)
                        .into(new DrawableImageViewTarget(road));


            }


            public void questionOnclick(final View view){
                final Handler mHandler = new Handler();


//        Timer timer = new Timer();
//
//        TimerTask TT = new TimerTask() {
//            @Override
//            public void run() { mHandler.postDelayed(new Runnable()  {
//                public void run() {
//                    // 시간 지난 후 실행할 코딩
//                    speed+=3;
//                }
//            }, 9000);
//
//            }
//        };
//        timer.schedule(TT, 1, 10000); //Timer 실행

                chungdoll(view);
//        duration = 1000*speed;

                //애니메이션
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim1);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        answerCheck();
                        if(speed>4000) {
                            speed-=500;
                        } else if(speed==4000){
                            speed=4000;
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                animation.setDuration(speed);
                answer0.startAnimation(animation);
                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim2);
                animation2.setDuration(speed);
                answer1.startAnimation(animation2);
                Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim3);
        animation3.setDuration(speed);
        answer2.startAnimation(animation3);
        Animation animation4 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim4);
        animation4.setDuration(speed);
        answer3.startAnimation(animation4);

        // Easy 문제 코드
        Random random1 = new Random();
        Random random2 = new Random();
        left = random1.nextInt(9) + 2;
        right = random2.nextInt(9) + 1;
        answer = left * right;


        questionText.setText(left + " * " + right + " =  ?");

        if(answer % 2 == 0) {
            ArrayList<Integer> randAnswer = new ArrayList<Integer>();
            randAnswer.add(answer);
            randAnswer.add(answer+AA);
            randAnswer.add(answer+(2*AA));
            randAnswer.add(answer-AA);
            Collections.shuffle(randAnswer);
            array = new int[randAnswer.size()];
            for(int j=0; j<4; j++){
                array[j] = randAnswer.get(j).intValue();
            }
            for(int i=0; i<4;i++) {
                textViews = findViewById(R.id.answer0 + i); //버튼 id : b1부터 b25
                textViews.setText(String.valueOf(array[i]));
            }
        } else if(answer % 1 == 0) {
            ArrayList<Integer> randAnswer = new ArrayList<Integer>();
            randAnswer.add(answer);
            randAnswer.add(answer+AA);
            randAnswer.add(answer+(2*AA));
            randAnswer.add(answer-AA);
            Collections.shuffle(randAnswer);
            array = new int[randAnswer.size()];
            for(int j=0; j<4; j++){
                array[j] = randAnswer.get(j).intValue();
            }
            for(int i=0; i<4;i++) {
                textViews = findViewById(R.id.answer0 + i); //버튼 id : b1부터 b25
                textViews.setText(String.valueOf(array[i]));
            }
        } else if(left == 10) {
            ArrayList<Integer> randAnswer = new ArrayList<Integer>();
            randAnswer.add(answer);
            randAnswer.add(answer+10);
            randAnswer.add(answer-10);
            randAnswer.add(answer+20);
            Collections.shuffle(randAnswer);
            array = new int[randAnswer.size()];
            for(int j=0; j<4; j++){
                array[j] = randAnswer.get(j).intValue();
            }
            for(int i=0; i<4;i++) {
                textViews = findViewById(R.id.answer0 + i); //버튼 id : b1부터 b25
                textViews.setText(String.valueOf(array[i]));
            }
        } else if(left%5 == 0) {
            ArrayList<Integer> randAnswer = new ArrayList<Integer>();
            randAnswer.add(answer);
            randAnswer.add(answer+5);
            randAnswer.add(answer-5);
            randAnswer.add(answer+10);
            Collections.shuffle(randAnswer);
            array = new int[randAnswer.size()];
            for(int j=0; j<4; j++){
                array[j] = randAnswer.get(j).intValue();
            }
            for(int i=0; i<4;i++) {
                textViews = findViewById(R.id.answer0 + i); //버튼 id : b1부터 b25
                textViews.setText(String.valueOf(array[i]));
            }
        }

    }

    void chungdoll(final View view){

        //자동차 TouchListener
        wlocation = view.getWidth()/4*1; // 1차선 도로
        startWidth = view.getWidth()/4*0; // 시작 x값 = 1차선 도로
        endWidth = view.getWidth()/4*3; // 끝 x값 = 4차선 도로
        location = car.getX();
        // 현재 자동차 위치

        car.setX(lastlocation); // 방금전에 있던 차선에 자동차 위치

        car.setOnTouchListener(new View.OnTouchListener() {
            float pointX;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) { // 처음 눌렀을 때
                    pointX = event.getX();

                    if(pointX<0 && location<0){
                        location = startWidth;
                    }
                    if(car.getX()>viewWidth) {
                        location = endWidth;
                    }
                }

                if(event.getAction() == MotionEvent.ACTION_MOVE) { // 누르고 움직였을 때
                    if (pointX + 150 < event.getX()) { // 오른쪽 150정도 해야 버벅임 없음
                        if(car.getX() == endWidth) { // 화면의 맨 오른쪽일 경우에 화면 벗어나지 않도록
                            car.setX(endWidth);
                        }
                        else if (car.getX() < endWidth) {
                            location = car.getX();
                            car.setX(location + wlocation);
                        }
                    }

                    else if (pointX - 150 > event.getX()) { // 왼쪽 150정도 해야 버벅임 없음
                        if(car.getX() == startWidth){ // 화면의 맨 왼쪽일 경우에 화면 벗어나지 않도록
                            car.setX(startWidth);
                        }
                        else if (car.getX() >startWidth) {
                            location = car.getX();
                            car.setX(location - wlocation);
                        }
                    }
                }else if(event.getAction() == MotionEvent.ACTION_UP) {
                    // 누른걸 때었을 때
                    return false;
                }
                return true;
            }
        });


    }

    void answerCheck(){
        // 정답 체크
        if(answer == Integer.parseInt(answer0.getText().toString())
                && car.getX() == startWidth) {
            count++;
            Toast.makeText(this, "정답!", Toast.LENGTH_SHORT).show();
        } else if(answer == Integer.parseInt(answer1.getText().toString())
                && car.getX() == wlocation*1) {
            count++;
            Toast.makeText(this, "정답!", Toast.LENGTH_SHORT).show();
        }else if(answer == Integer.parseInt(answer2.getText().toString())
                && car.getX() == wlocation*2) {
            count++;
            Toast.makeText(this, "정답!", Toast.LENGTH_SHORT).show();
        }else if(answer == Integer.parseInt(answer3.getText().toString())
                && car.getX() == wlocation*3) {
            count++;
            Toast.makeText(this, "정답!", Toast.LENGTH_SHORT).show();
        }else{
            long[] pattern = {30,45,30,30}; // 0.03초 진동, 0.45초 대기, 0.03초 진동 패턴
            vibrator.vibrate(pattern,-1); // -1은 반복 안함
            life++;
            life();
            Toast.makeText(this, "땡!!", Toast.LENGTH_SHORT).show();
        }
        lastlocation=car.getX();
        questionOnclick(view);

    }

    void life(){
        // 목숨
        if(life==0){
            life1.setVisibility(View.VISIBLE);
            life2.setVisibility(View.VISIBLE);
            life3.setVisibility(View.VISIBLE);
        } else if(life==1){
            life1.setVisibility(View.INVISIBLE);
            life2.setVisibility(View.VISIBLE);
            life3.setVisibility(View.VISIBLE);
        } else if(life==2){
            life1.setVisibility(View.INVISIBLE);
            life2.setVisibility(View.INVISIBLE);
            life3.setVisibility(View.VISIBLE);
        } else if(life==3){
            life1.setVisibility(View.INVISIBLE);
            life2.setVisibility(View.INVISIBLE);
            life3.setVisibility(View.INVISIBLE);
            gameFinishDialog();
        }
    }

    void gameFinishDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("게임 결과!");
        builder
                .setMessage(gameMode+" 에서\n" +
                        count+" 개 맞추셨습니다.")
                .setCancelable(false)
                .setPositiveButton("종료",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 프로그램을 종료한다
                               Intent intent = new Intent(Gugudan_CarGame_Activity.this,HomeActivity.class);
                                startActivity(intent);
                            }
                        });

        AlertDialog alertDialog = builder.show();
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
        alertDialog.show();
    }



}


