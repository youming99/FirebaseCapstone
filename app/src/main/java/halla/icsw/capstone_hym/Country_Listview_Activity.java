package halla.icsw.capstone_hym;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Country_Listview_Activity extends AppCompatActivity {

    private RecyclerView recycleView;
    private RecyclerView.Adapter adpater;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Country_Capital> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference1;

    String continent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country__listview_);

        // 대륙 이름 갖고와서 텍스트에 출력
        Intent intent = getIntent();
        continent = intent.getStringExtra("study");
        TextView continentText = findViewById(R.id.continentText);
        continentText.setText(continent);



        recycleView = findViewById(R.id.recyclerView);
        recycleView.setHasFixedSize(true); // 기존 성능 강화
        layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // 어레이리스트

        database = FirebaseDatabase.getInstance();

        if(continent.equals("아시아")) {
            databaseReference1 = database.getReference("나라수도").child("아시아");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                    arrayList.clear(); // 기존 배열리스트가 존재하지 않기 위해 초기화 (방지)
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 리스트 추출
                        Country_Capital country_capital = snapshot.getValue(Country_Capital.class); // 만들어놨던 클래스에 데이터 담음
                        arrayList.add(country_capital); // 담은 데이터들을 배열 리스트에 넣고 리사이클뷰에 보냄

                    }
                    adpater.notifyDataSetChanged(); // 리스트 저장 및 새로고침

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // 디비를 가져오던 중 에러 발생 시
                }
            });

            adpater = new CustomAdapter(arrayList, this);
            recycleView.setAdapter(adpater); // 리사이클뷰에 어댑터 연결
        }
        else if(continent.equals("유럽")) {
            databaseReference1 = database.getReference("나라수도").child("유럽");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                    arrayList.clear(); // 기존 배열리스트가 존재하지 않기 위해 초기화 (방지)
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 리스트 추출
                        Country_Capital country_capital = snapshot.getValue(Country_Capital.class); // 만들어놨던 클래스에 데이터 담음
                        arrayList.add(country_capital); // 담은 데이터들을 배열 리스트에 넣고 리사이클뷰에 보냄

                    }
                    adpater.notifyDataSetChanged(); // 리스트 저장 및 새로고침

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // 디비를 가져오던 중 에러 발생 시
                }
            });

            adpater = new CustomAdapter(arrayList, this);
            recycleView.setAdapter(adpater); // 리사이클뷰에 어댑터 연결
        }
        else if(continent.equals("북아메리카")) {
            databaseReference1 = database.getReference("나라수도").child("북아메리카(북미)");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                    arrayList.clear(); // 기존 배열리스트가 존재하지 않기 위해 초기화 (방지)
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 리스트 추출
                        Country_Capital country_capital = snapshot.getValue(Country_Capital.class); // 만들어놨던 클래스에 데이터 담음
                        arrayList.add(country_capital); // 담은 데이터들을 배열 리스트에 넣고 리사이클뷰에 보냄

                    }
                    adpater.notifyDataSetChanged(); // 리스트 저장 및 새로고침

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // 디비를 가져오던 중 에러 발생 시
                }
            });

            adpater = new CustomAdapter(arrayList, this);
            recycleView.setAdapter(adpater); // 리사이클뷰에 어댑터 연결
        }
        else if(continent.equals("남아메리카")) {
            databaseReference1 = database.getReference("나라수도").child("남아메리카(남미)");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                    arrayList.clear(); // 기존 배열리스트가 존재하지 않기 위해 초기화 (방지)
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 리스트 추출
                        Country_Capital country_capital = snapshot.getValue(Country_Capital.class); // 만들어놨던 클래스에 데이터 담음
                        arrayList.add(country_capital); // 담은 데이터들을 배열 리스트에 넣고 리사이클뷰에 보냄

                    }
                    adpater.notifyDataSetChanged(); // 리스트 저장 및 새로고침

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // 디비를 가져오던 중 에러 발생 시
                }
            });

            adpater = new CustomAdapter(arrayList, this);
            recycleView.setAdapter(adpater); // 리사이클뷰에 어댑터 연결
        }
        else if(continent.equals("아프리카")) {
            databaseReference1 = database.getReference("나라수도").child("아프리카");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                    arrayList.clear(); // 기존 배열리스트가 존재하지 않기 위해 초기화 (방지)
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 리스트 추출
                        Country_Capital country_capital = snapshot.getValue(Country_Capital.class); // 만들어놨던 클래스에 데이터 담음
                        arrayList.add(country_capital); // 담은 데이터들을 배열 리스트에 넣고 리사이클뷰에 보냄

                    }
                    adpater.notifyDataSetChanged(); // 리스트 저장 및 새로고침

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // 디비를 가져오던 중 에러 발생 시
                }
            });

            adpater = new CustomAdapter(arrayList, this);
            recycleView.setAdapter(adpater); // 리사이클뷰에 어댑터 연결
        }
        else if(continent.equals("오세아니아")) {
            databaseReference1 = database.getReference("나라수도").child("오세아니아");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                    arrayList.clear(); // 기존 배열리스트가 존재하지 않기 위해 초기화 (방지)
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 리스트 추출
                        Country_Capital country_capital = snapshot.getValue(Country_Capital.class); // 만들어놨던 클래스에 데이터 담음
                        arrayList.add(country_capital); // 담은 데이터들을 배열 리스트에 넣고 리사이클뷰에 보냄

                    }
                    adpater.notifyDataSetChanged(); // 리스트 저장 및 새로고침

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // 디비를 가져오던 중 에러 발생 시
                }
            });

            adpater = new CustomAdapter(arrayList, this);
            recycleView.setAdapter(adpater); // 리사이클뷰에 어댑터 연결
        }

        DividerItemDecoration myDivider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        myDivider.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        recycleView.addItemDecoration(myDivider);


    }
}
