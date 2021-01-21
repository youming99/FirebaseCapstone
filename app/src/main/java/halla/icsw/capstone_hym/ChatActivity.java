package halla.icsw.capstone_hym;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ChatActivity extends AppCompatActivity {

    int rankInt= 0;
    String rankStr = "";
    String rankText = "";

    TextView questionText;
    TextView timerText;
    private int answerInt = 0;
    private String answer = "";
    String editAnswer = "";
    private String question = "";

    private String CHAT_NAME;
    private String USER_NAME;

    private ListView chat_view;
    private EditText chat_edit;
    private Button chat_send;
    ArrayList<String> arrayListRank;
    ArrayList<String> arrayListFinalRank;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    String userName[];
    String userScore[];
    String userRank[];
    String userNameStr = "";
    String userScoreStr = "";
    String userRankStr = "";
    String a = "";
    String b = "";
    int ranking[];
    int rn=0;

    ChatDTO chatDTO;

    String finalRankStr= "";
    String finals = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        questionText = findViewById(R.id.questionText); // 문제 텍스트
        timerText = findViewById(R.id.timerText);

        // 위젯 ID 참조
        chat_view = (ListView) findViewById(R.id.chat_view);
        chat_edit = (EditText) findViewById(R.id.chat_edit);
        chat_send = (Button) findViewById(R.id.chat_sent);


        // 로그인 화면에서 받아온 채팅방 이름, 유저 이름 저장
        Intent intent = getIntent();
        CHAT_NAME = intent.getStringExtra("chatName");
        USER_NAME = intent.getStringExtra("userName");

        // 채팅 방 입장
        openChat(CHAT_NAME);

//        gameStart();
        //rank(CHAT_NAME);


        // 메시지 전송 버튼에 대한 클릭 리스너 지정
        chat_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chat_edit.getText().toString().equals(""))
                    return;

                editAnswer = chat_edit.getText().toString();

                ChatDTO chat = new ChatDTO(USER_NAME, chat_edit.getText().toString()); //ChatDTO를 이용하여 데이터를 묶는다.
                databaseReference.child("chat").child(CHAT_NAME).push().setValue(chat); // 데이터 푸쉬

                //시작
//                if(editAnswer.equals("시작")){
//                    databaseReference.child("start").child(CHAT_NAME).setValue("시작"); // 데이터 푸쉬
////                    gameStart();
////                    // TODO : 타이머 돌릴 총시간
////                    String conversionTime = "000010";
////
////                    // 카운트 다운 시작
////                    countDown(conversionTime);
//
//                }
                //firstStart();


                databaseReference.child("gugudan").child(CHAT_NAME).child("answer").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String strAnswer = dataSnapshot.getValue(String.class);

                        //정답을 맞췄을 때
                        if(editAnswer.equals(strAnswer)) {
                            rankInt++;
                            rankStr = String.valueOf(rankInt);
                            databaseReference.child("rank").child(CHAT_NAME).child(USER_NAME).child("rank").setValue(rankStr);

                            //rank(USER_NAME);
                            gameStart();
                            Toast.makeText(ChatActivity.this, USER_NAME+"님 1점 추가 획득!", Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                chat_edit.setText(""); //입력창 초기화
            }
        });
    }

    private void addMessage(DataSnapshot dataSnapshot, ArrayAdapter<String> adapter) {
        chatDTO = dataSnapshot.getValue(ChatDTO.class);
        adapter.add(chatDTO.getUserName() + " 님 : " + chatDTO.getMessage());
        if(chatDTO.getMessage().equals("시작")) {
            firstStart();
        }

    }

    private void removeMessage(DataSnapshot dataSnapshot, ArrayAdapter<String> adapter) {
        ChatDTO chatDTO = dataSnapshot.getValue(ChatDTO.class);
        adapter.remove(chatDTO.getUserName() + " : " + chatDTO.getMessage());
    }

    private void openChat(String chatName) {

        Toast.makeText(this, "참여자들 중 한 명만 '시작'을 입력하면 게임이 시작됩니다.", Toast.LENGTH_SHORT).show();

        // 리스트 어댑터 생성 및 세팅
        final ArrayAdapter<String> adapter

                = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        chat_view.setAdapter(adapter);

        // 데이터 받아오기 및 어댑터 데이터 추가 및 삭제 등..리스너 관리
        databaseReference.child("chat").child(chatName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                addMessage(dataSnapshot, adapter);

                Log.e("LOG", "s:"+s);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                removeMessage(dataSnapshot, adapter);
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }


    private void firstStart(){

        if(chatDTO.getMessage().equals("시작")) {
            gameStart();

            // TODO : 타이머 돌릴 총시간
            String conversionTime = "000060";

            // 카운트 다운 시작
            countDown(conversionTime);
        }

    }



    private void gameStart(){

        Random random1 = new Random(); // 1-9 랜덤 숫자
        Random random2 = new Random(); // 2-9 랜덤 숫자
        int randNum = random1.nextInt(9)+1;
        int randNum2 = random2.nextInt(9)+2;
        question = randNum2 + " * " + randNum + " =  ?";
        answerInt = randNum2 * randNum;
        answer = String.valueOf(answerInt);

        //문제 넣기
        databaseReference.child("gugudan").child(CHAT_NAME).child("question").setValue(question);
        databaseReference.child("gugudan").child(CHAT_NAME).child("answer").setValue(answer);

        databaseReference.child("gugudan").child(CHAT_NAME).child("question").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String question1 = dataSnapshot.getValue(String.class);
                questionText.setText(question1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void score(){

        // 구현된 코드
        databaseReference.child("rank").child(CHAT_NAME).child(USER_NAME).child("rank").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rankText = dataSnapshot.getValue(String.class);
                //Toast.makeText(ChatActivity.this, rankText, Toast.LENGTH_SHORT).show();
                dialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void rank(){

        //USER_NAME 불러오는 코드
        databaseReference.child("rank").child(CHAT_NAME).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String text = dataSnapshot.getKey();
                userNameStr += text+"/";
                userName = userNameStr.split("/");


                //USER_NAME의 점수 불러오는 코드
                for(int i=userName.length-1; i<userName.length; i++) {
                    a = userName[i];
                    databaseReference.child("rank").child(CHAT_NAME).child(String.valueOf(a)).child("rank").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String text = dataSnapshot.getValue(String.class);
                            userScoreStr += text+",";
                            userScore = userScoreStr.split(",");

                            String aa= Arrays.toString(userScore)+"\n".split(",");

                            String aaa= userName[userName.length-1];


                            ArrayList arrayList = new ArrayList<>();
                            for(String temp : userName){
                                arrayList.add(temp);
                            }
                            ArrayList<String> arrayList2 = new ArrayList<>();
                            for(String temp2 : userScore){
                                arrayList2.add(temp2);
                            }
                            arrayListRank = new ArrayList<>();
                            for(int i=0; i<userScore.length; i++){
                                int a=0;

                                String userNames = "";
                                String userScores = "";

                                userNames = userName[i] + " ";
                                userScores = userScore[i]+" 점 - ";

                                arrayListRank.add(userScores+userNames);
                                a++;
                            }
                            Collections.sort(arrayListRank);
                            Collections.reverse(arrayListRank);

                            arrayListFinalRank = new ArrayList<>();
                            int j = 1;
                            arrayListFinalRank.add("1등 : " + arrayListRank.get(0));

                            for(int i=1; i<arrayListRank.size(); i++){
                                if(arrayListRank.get(i).substring(0,arrayListRank.get(i).indexOf(" 점"))
                                        .equals(arrayListRank.get(i-1).substring(0,arrayListRank.get(i).indexOf(" 점")))) {
                                    arrayListFinalRank.add(j+"등 : " + arrayListRank.get(i));
                                }
                                else{
                                    j++;
                                    arrayListFinalRank.add(j+"등 : " + arrayListRank.get(i));
                                }
                            }


//                            for(int i=arrayListFinalRank.size()-1; i<arrayListFinalRank.size(); i++){
//                                finalRankStr += String.valueOf(arrayListFinalRank.get(i)) + "\n";
//                            }
                            //finalRankStr = finals + "\n";




//                            dialogItemList = new ArrayList<>();
//
//                            for(int i=0;i<image.length;i++)
//                            {
//                                Map<String, Object> itemMap = new HashMap<>();
//                                itemMap.put(TAG_IMAGE, image[i]);
//                                itemMap.put(TAG_TEXT, textA[i]);
//
//                                dialogItemList.add(itemMap);
//                            }

                            showAlertDialog();



                            //chat_edit.setText(arrayListFinalRank.toString());


                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("게임 결과!");
        builder
                .setMessage(CHAT_NAME+" 방에서" +USER_NAME + " 님은\n" +
                        rankText +" 개 맞추셨습니다.\n")
                .setCancelable(false)

                .setNegativeButton("랭크 보기",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                rank();
                                //showAlertDialog();

                                // 다이얼로그를 취소한다
                                //dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = builder.show();
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
        alertDialog.show();
    }

    private void showAlertDialog()
    {
        //rank();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(CHAT_NAME+" 방에서 순위는?");
        builder.setMessage(arrayListFinalRank.toString()+"\n");
        builder.setCancelable(false)
                .setPositiveButton("종료",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {

                                // 프로그램을 종료한다
                                Intent intent = new Intent(ChatActivity.this,HomeActivity.class);
                                startActivity(intent);

                                databaseReference.child("chat").child(CHAT_NAME).setValue(null);
                                databaseReference.child("gugudan").child(CHAT_NAME).setValue(null);
                                databaseReference.child("rank").child(CHAT_NAME).setValue(null);
                                databaseReference.child("start").child(CHAT_NAME).setValue(null);

                            }
                        });

        AlertDialog alertDialog = builder.show();
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
        alertDialog.show();
    }



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
                score();
            }
        }.start();
    }
}