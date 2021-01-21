package halla.icsw.capstone_hym;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Country_Big_Activity extends AppCompatActivity {

    private TextView continentText, countryText, capitalText;
    private ImageView picture;


    private FirebaseDatabase database;
    private DatabaseReference databaseReference, databaseReference1, databaseReference2;

    String asiaNum = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country__big_);

        databaseReference = FirebaseDatabase.getInstance().getReference();


        continentText = findViewById(R.id.continentText);
        countryText = findViewById(R.id.countryText);
        capitalText = findViewById(R.id.capitalText);
        picture = findViewById(R.id.imageView);

        Intent intent = getIntent();
        countryText.setText("나라 : " + intent.getStringExtra("country"));
//        capitalText.setText("수도 : " + intent.getStringExtra("capital"));

        database = FirebaseDatabase.getInstance();

        for(int i=1; i<=9; i++) {
            asiaNum = String.format("Asia0%d", i);


            databaseReference1 = databaseReference.child("나라수도").child("아시아").child(asiaNum).child("country");
            databaseReference2 = databaseReference.child("나라수도").child("아시아").child(asiaNum).child("capital");
            if(databaseReference1.equals(intent.getStringExtra("country"))) {
                databaseReference2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String a = dataSnapshot.getValue(String.class);
                            capitalText.setText(a);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }


        }

        databaseReference2 = database.getReference("나라수도").child("아시아").child(countryText.getText().toString()).getParent();
        if(countryText.getText().toString().equals(databaseReference1)) {



        }


    }
}
