package halla.icsw.capstone_hym;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Country_Game2_Activity extends AppCompatActivity {

    String continent, game;
    int randNum = 0;
    TextView questionText, scoreText;
    ImageView picture;
    String imageName;
    String capitals ="";
    String answer;
    int aaa = 0;
    int check = 10;
    int score = 0;
    int a[];
    int[] array;
    int[] randArray = new int[4];

    String[] randArrayStr;
    ArrayList<String> randArrayList;
    int countryNum;
    int randNumber;

    Button b;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference1;
    private DatabaseReference databaseReference11;
    private DatabaseReference databaseReference22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country__game2_);

        Intent intent = getIntent();
        continent = intent.getExtras().getString("continent");
        game = intent.getExtras().getString("game");

        TextView continentTextview = findViewById(R.id.continentText);
        continentTextview.setText(continent + " 대륙의 " +game );

        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);
        picture = findViewById(R.id.picture);




        newGame();


    }

    public void onClick(View v){
        b = (Button) v; //버튼 id : b1부터 4
        if(answer.equals(b.getText())){
            check++;
            if(check == 11){
                score++;
            }
            //b.setTextColor(Color.WHITE);
            questionText.setText("");
            capitals = "";
            scoreText.setText("맞은 개수 : " + score);
            newGame();
        }
        else {
            b.setTextColor(Color.RED);
            check--;
        }
        //Toast.makeText(this, capital, Toast.LENGTH_SHORT).show();

    }

    void newGame(){
        check = 10;
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        button1.setTextColor(Color.BLACK);
        button2.setTextColor(Color.BLACK);
        button3.setTextColor(Color.BLACK);
        button4.setTextColor(Color.BLACK);

        randArrayStr = new String[4];
        questionText.setText("");

        //firebase 데이터 읽어오기
        database = FirebaseDatabase.getInstance();

        if(continent.equals("아시아")){
            a = new int[48];
            for(int i=0; i<48; i++){
                a[i] = i+1;
            }
            allFunction();
            imageName = String.format("Asia%d.jpg", array[countryNum]);
            countryNum = 1;
            capitalGame();
            countryNum ++;
        } else if(continent.equals("유럽")){
            a = new int[53];
            for(int i=0; i<53; i++){
                a[i] = i+1;
            }
            allFunction();
            imageName = String.format("Europe%d.jpg", array[countryNum]);
            countryNum = 1;
            capitalGame();
            countryNum ++;

        } else if(continent.equals("북아메리카(북미)")){
            a = new int[23];
            for(int i=0; i<23; i++){
                a[i] = i+1;
            }
            allFunction();
            imageName = String.format("NAmerica%d.jpg", array[countryNum]);
            countryNum = 1;
            capitalGame();
            countryNum ++;
        } else if(continent.equals("남아메리카(남미)")){
            a = new int[12];
            for(int i=0; i<12; i++){
                a[i] = i+1;
            }
            allFunction();
            imageName = String.format("SAmerica%d.jpg", array[countryNum]);
            countryNum = 1;
            capitalGame();
            countryNum ++;
        } else if(continent.equals("아프리카")){
            a = new int[53];
            for(int i=0; i<53; i++){
                a[i] = i+1;
            }
            allFunction();
            imageName = String.format("Africa%d.jpg", array[countryNum]);
            countryNum = 1;
            capitalGame();
            countryNum ++;
        } else if(continent.equals("오세아니아")){
            a = new int[14];
            for(int i=0; i<14; i++){
                a[i] = i+1;
            }
            allFunction();
            imageName = String.format("Oceania%d.jpg", array[countryNum]);
            countryNum = 1;
            capitalGame();
            countryNum ++;
        }
    }

    void allFunction(){
        List<Integer> list= new ArrayList<>();
        for (int i=0; i<a.length; i++){
            list.add(a[i]);
        }
        Collections.shuffle(list);

        array = new int[list.size()];
        int size=0;
        for(int temp : list){
            array[size++] = temp;
        }

        countryNum = 1;
    }

    //게임하기

    void capitalGame(){
        //국기
        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
        String folderName = "나라_수도";
        String folderName2 = folderName + "/" + continent;

        //imageName = String.format("Asia%d.jpg", asiaRandNum);

        // Storage 이미지 다운로드 경로
        String storagePath = folderName2 + "/" + imageName;
        StorageReference imageRef = mStorageRef.child(storagePath);
        try {
            // Storage 에서 다운받아 저장시킬 임시파일
            final File imageFile = File.createTempFile("images", "jpg");
            imageRef.getFile(imageFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // Success Case
                    Bitmap bitmapImage = BitmapFactory.decodeFile(imageFile.getPath());
                    picture.setImageBitmap(bitmapImage);
                    //Toast.makeText(getApplicationContext(), "Success !!", Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Fail Case
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Fail !!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(game.equals("수도 맞추기")){
            //나라
            databaseReference1 = database.getReference("나라수도").child(continent).child(String.valueOf(array[countryNum])).child("country");
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String a = dataSnapshot.getValue(String.class);
                    questionText.setText(a);

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

            // 수도 랜덤 뽑기
            final Random random = new Random();
            for(int i=0; i<3; i++) {
                if(continent.equals("아시아")){
                    randArray[i] = random.nextInt(48) + 1; // 아시아
                }else if(continent.equals("유럽")){
                    randArray[i] = random.nextInt(53) + 1;
                }else if(continent.equals("북아메리카(북미)")){
                    randArray[i] = random.nextInt(23) + 1;
                }else if(continent.equals("남아메리카(남미)")){
                    randArray[i] = random.nextInt(12) + 1;
                }else if(continent.equals("아프리카")){
                    randArray[i] = random.nextInt(53) + 1;
                }else if(continent.equals("오세아니아")){
                    randArray[i] = random.nextInt(14) + 1;
                }
                for (int j = 0; j < i; j++) {
                    if (randArray[i] == randArray[j] || countryNum == randArray[i]) {
                        i--;
                    }
                }
            }
            randArray[3] = array[countryNum];

            //수도
            databaseReference1 = database.getReference("나라수도").child(continent).child(String.valueOf(randArray[0])).child("capital");
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String a = dataSnapshot.getValue(String.class);
                    capitals += a+"-";

                    databaseReference1 = database.getReference("나라수도").child(continent).child(String.valueOf(randArray[1])).child("capital");
                    databaseReference1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String a = dataSnapshot.getValue(String.class);
                            capitals += a+"-";

                            databaseReference1 = database.getReference("나라수도").child(continent).child(String.valueOf(randArray[2])).child("capital");
                            databaseReference1.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String a = dataSnapshot.getValue(String.class);
                                    capitals += a+"-";

                                    databaseReference1 = database.getReference("나라수도").child(continent).child(String.valueOf(randArray[3])).child("capital");
                                    databaseReference1.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            String a = dataSnapshot.getValue(String.class);
                                            capitals += a+"-";
                                            answer = a;

                                            randArrayStr = capitals.split("-");

                                            List<String> randArrayList2 = new ArrayList<>();

                                            for(String temp2 : randArrayStr){
                                                randArrayList2.add(temp2);
                                            }
                                            Collections.shuffle(randArrayList2);
                                            Button button1 = findViewById(R.id.button1);
                                            Button button2 = findViewById(R.id.button2);
                                            Button button3 = findViewById(R.id.button3);
                                            Button button4 = findViewById(R.id.button4);
                                            button1.setText(String.valueOf(randArrayList2.get(0)));
                                            button2.setText(String.valueOf(randArrayList2.get(1)));
                                            button3.setText(String.valueOf(randArrayList2.get(2)));
                                            button4.setText(String.valueOf(randArrayList2.get(3)));
                                            //questionText.setText(Arrays.toString(randArrayStr));

                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                        }
                                    });
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }

        else if(game.equals("나라 맞추기")){

            //수도
            databaseReference1 = database.getReference("나라수도").child(continent).child(String.valueOf(array[countryNum])).child("capital");
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String a = dataSnapshot.getValue(String.class);
                    questionText.setText(a);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

            // 나라 랜덤 뽑기
            final Random random = new Random();
            for(int i=0; i<3; i++) {
                if(continent.equals("아시아")){
                    randArray[i] = random.nextInt(48) + 1; // 아시아
                }else if(continent.equals("유럽")){
                    randArray[i] = random.nextInt(53) + 1;
                }else if(continent.equals("북아메리카(북미)")){
                    randArray[i] = random.nextInt(23) + 1;
                }else if(continent.equals("남아메리카(남미)")){
                    randArray[i] = random.nextInt(12) + 1;
                }else if(continent.equals("아프리카")){
                    randArray[i] = random.nextInt(53) + 1;
                }else if(continent.equals("오세아니아")){
                    randArray[i] = random.nextInt(14) + 1;
                }
                for (int j = 0; j < i; j++) {
                    if (randArray[i] == randArray[j] || countryNum == randArray[i]) {
                        i--;
                    }
                }
            }
            randArray[3] = array[countryNum];

            //수도
            databaseReference1 = database.getReference("나라수도").child(continent).child(String.valueOf(randArray[0])).child("country");
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String a = dataSnapshot.getValue(String.class);
                    capitals += a+"-";

                    databaseReference1 = database.getReference("나라수도").child(continent).child(String.valueOf(randArray[1])).child("country");
                    databaseReference1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String a = dataSnapshot.getValue(String.class);
                            capitals += a+"-";

                            databaseReference1 = database.getReference("나라수도").child(continent).child(String.valueOf(randArray[2])).child("country");
                            databaseReference1.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String a = dataSnapshot.getValue(String.class);
                                    capitals += a+"-";

                                    databaseReference1 = database.getReference("나라수도").child(continent).child(String.valueOf(randArray[3])).child("country");
                                    databaseReference1.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            String a = dataSnapshot.getValue(String.class);
                                            capitals += a+"-";
                                            answer = a;
                                            randArrayStr = capitals.split("-");

                                            List<String> randArrayList2 = new ArrayList<>();

                                            for(String temp2 : randArrayStr){
                                                randArrayList2.add(temp2);
                                            }
                                            Collections.shuffle(randArrayList2);
                                            Button button1 = findViewById(R.id.button1);
                                            Button button2 = findViewById(R.id.button2);
                                            Button button3 = findViewById(R.id.button3);
                                            Button button4 = findViewById(R.id.button4);
                                            button1.setText(String.valueOf(randArrayList2.get(0)));
                                            button2.setText(String.valueOf(randArrayList2.get(1)));
                                            button3.setText(String.valueOf(randArrayList2.get(2)));
                                            button4.setText(String.valueOf(randArrayList2.get(3)));
                                            //questionText.setText(Arrays.toString(randArrayStr));

                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                        }
                                    });
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }

    }



}