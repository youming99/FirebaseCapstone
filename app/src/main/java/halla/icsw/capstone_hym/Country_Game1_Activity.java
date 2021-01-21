package halla.icsw.capstone_hym;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class Country_Game1_Activity extends AppCompatActivity {

    String gameName = "";
    String country = "";
    String capital = "";
    String capital2 = "";
    String[] asiaCountries;
    String[] capitals;
    String[] capitals2;
    String[] answerCapital;
    String[] everyCapital;
    String[] randomCapital;
    String[] lastCapital;

    int asiaNum = 48;
    int asiaRandNum = 0;
    int x = 0;
    int p;
    boolean qq;

    Random random;

    TextView questionText, answerText;

    ImageView iv_preview;
    String imageName;

    StringBuilder sb;

    Button b;
    Button backButton, sendButton;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference1;
    private DatabaseReference databaseReference11;
    private DatabaseReference databaseReference22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country__game1_);

        //대륙 이름 가져와서 텍스트에 출력
        Intent intent = getIntent();
        gameName = intent.getStringExtra("game");
        TextView continent = findViewById(R.id.continentText);
        continent.setText(gameName);

        //questionText, answerText 선언
        questionText = findViewById(R.id.questionText);
        answerText = findViewById(R.id.answerText);

//        if(gameName.equals("아시아")){
//            asiaRandNum = random.nextInt(48)+1;
//            imageName = String.format("Asia%d.jpg", asiaRandNum);
//            newGame();
//        }
        backButton = findViewById(R.id.backButton);
        sendButton = findViewById(R.id.sendButton);




        newGame();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (answerText.getText().toString().equals("")){

                }
                else {
                    answerText.setText(answerText.getText().toString().substring(0, answerText.getText().toString().length() - 1));
                }

            }
        });



    }



    public void newGame(){


        //firebase 데이터 읽어오기
        database = FirebaseDatabase.getInstance();

        //if(gameName.equals("아시아")) {
        // asiaCountries = new String[48];
//            for(asiaNum=1; asiaNum<=10; asiaNum++) {
//                databaseReference1 = database.getReference("나라수도").child("아시아").child(String.valueOf(asiaNum)).child("country");
//                databaseReference11 = database.getReference("나라수도").child("아시아").child(String.valueOf(asiaNum)).child("capital");
//
//                databaseReference1.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        databaseReference1 = databaseReference1.child("country");
//                        String a = dataSnapshot.getValue(String.class);
//                        asiaCountry += a +",";
//                        asiaCountries = asiaCountry.split(",");
//                        for (int i = 0; i < asiaNum; i++) {
//                            // 나라 이름이 다 들어갔는지 확인하기 위한 코드
//                            // questionText.setText(Arrays.toString(asiaCountries));
//                        }
//
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                    }
//                });
//
//                databaseReference11.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        String a = dataSnapshot.getValue(String.class);
//                        asiaCapital += a +",";
//                        asiaCapitals = asiaCapital.split(",");
//                        for (int i = 0; i < asiaNum; i++) {
//                            // 나라 이름이 다 들어갔는지 확인하기 위한 코드
//                           // questionText.setText(Arrays.toString(asiaCapitals));
//                        }
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                    }
//                });
//            }

        //랜덤

        final Random random = new Random();

        if(gameName.equals("아시아")) {
            asiaRandNum = random.nextInt(48)+1;
            imageName = String.format("Asia%d.jpg", asiaRandNum);
        }
        else if(gameName.equals("유럽")) {
            asiaRandNum = random.nextInt(53)+1;
            imageName = String.format("Europe%d.jpg", asiaRandNum);
        }
        else if(gameName.equals("북아메리카(북미)")) {
            asiaRandNum = random.nextInt(23)+1;
            imageName = String.format("NAmerica%d.jpg", asiaRandNum);
        }
        else if(gameName.equals("남아메리카(남미)")) {
            asiaRandNum = random.nextInt(12)+1;
            imageName = String.format("SAmerica%d.jpg", asiaRandNum);
        }
        else if(gameName.equals("아프리카")) {
            asiaRandNum = random.nextInt(52)+1;
            imageName = String.format("Africa%d.jpg", asiaRandNum);
        }
        else if(gameName.equals("오세아니아")) {
            asiaRandNum = random.nextInt(14)+1;
            imageName = String.format("Oceania%d.jpg", asiaRandNum);
        }


        //asiaRandNum = 48;

        //국기

        iv_preview = findViewById(R.id.picture);
        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
        String folderName = "나라_수도";
        String folderName2 = folderName + "/" + gameName;

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
                    iv_preview.setImageBitmap(bitmapImage);
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


        //나라
        databaseReference1 = database.getReference("나라수도").child(gameName).child(String.valueOf(asiaRandNum)).child("country");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                databaseReference1 = databaseReference1.child("country");
                String a = dataSnapshot.getValue(String.class);
                country = a ;
                questionText.setText(country);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //수도 (questionText)
        databaseReference11 = database.getReference("나라수도").child(gameName).child(String.valueOf(asiaRandNum)).child("capital");
        databaseReference11.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String a = dataSnapshot.getValue(String.class);
                capital = a;
                capital = capital.replace(" ", "");
                capitals = capital.split("");
                answerCapital = new String[capital.length()];
                for(int i=1; i<=capital.length(); i++){
                    answerCapital[i-1] = capitals[i];
                }
                //answerText.setText(Arrays.toString(answerCapital));

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



        //섞을 수도 (버튼)
        randomCapital = new String[(16-capital.length())];
        p=1;
        x = 1;
        for (int m=1; m<asiaRandNum; m++){
            databaseReference22 = database.getReference("나라수도").child(gameName).child(String.valueOf(x)).child("capital");
            databaseReference22.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String a = dataSnapshot.getValue(String.class);

                    capital2+=a;
                    capital2 = capital2.replace(" ", "");

                    capitals2 = capital2.split("");


                    //randomCapital[p - 1] = capitals2[p];

                    //p++;
                    //x++;

                    for(int i=0; i<16-capital.length(); i++){
                        int randomm= random.nextInt(capital2.length())+1;
                        randomCapital[i]=capitals2[randomm];
                    }
                    List<String> list4=new ArrayList<String>();
                    for (int i=0; i<capital.length(); i++){
                        list4.add(answerCapital[i]);

                    }

                    for(int i=0; i<16-capital.length(); i++){
                        list4.add(randomCapital[i]);
                    }

                    Collections.shuffle(list4);

                   list4.remove(" ");


//                    List<String> list = new ArrayList<String>();
//                    for(int i=0; i<capital2.length(); i++){
//                        list.add(capitals2[i]);
//                    }
//                    Collections.shuffle(list);
//                    List<String> list2= new ArrayList<String>(new HashSet<String>(list));
//                    for (int i=0; i<20; i++){
//                        list2.add(list.get(i));
//                    }
//
//                    list2.remove(null);
//                    List<String> list3 = new ArrayList<>();
//                    for(int i=0; i<capital.length(); i++){
//                        list3.add(answerCapital[i]);
//                    }
//                    for(int i=16-capital.length(); i<16; i++){
//                        list3.add(list2.get(i));
//                    }
                    //button 선언
                    for(int i=0; i <16;i++) {
                        b = findViewById(R.id.button1 + i); //버튼 id : b1부터 b25
                        b.setText(String.valueOf(list4.get(i)));
                        // b.setVisibility(View.VISIBLE);
                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            x++;
        }

    }


    public void onClick(View v){
        b = (Button) v; //버튼 id : b1부터 16
        answerText.setText(answerText.getText().toString() + b.getText().toString());
        Toast.makeText(this, capital, Toast.LENGTH_SHORT).show();

    }

    public void sendOnclick(View v){
        if(answerText.getText().toString().equals(capital)) {
            newGame();
            answerText.setText("");
        }else  {
            Toast.makeText(Country_Game1_Activity.this, "정답은 : "+ capital, Toast.LENGTH_SHORT).show();
            newGame();
            answerText.setText("");
        }
    }

}