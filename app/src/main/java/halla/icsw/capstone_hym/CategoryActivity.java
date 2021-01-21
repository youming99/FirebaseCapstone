package halla.icsw.capstone_hym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CategoryActivity extends AppCompatActivity {

    TextView gugudanText, countryText, knowledgeText, nickNameText;
    String userId;

    StringBuilder fields;
    DocumentSnapshot doc;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseDatabase.getReference("users");


        nickNameText = findViewById(R.id.nickNameText);

        findViewById(R.id.gugudanText).setOnClickListener(onClick);
        findViewById(R.id.countryText).setOnClickListener(onClick);
        findViewById(R.id.knowledgeText).setOnClickListener(onClick);

        dataRead();
        //userId = user.getUid();

    }

    private TextView.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.gugudanText:
                    Intent intent1 = new Intent(CategoryActivity.this, GugudanActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.countryText:
                    Intent intent2 = new Intent(CategoryActivity.this, CountryActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.knowledgeText:
                    Intent intent3 = new Intent(CategoryActivity.this, StartActivity.class);
                    startActivity(intent3);
                    break;

            }
        }
    };

    private void dataRead(){
        FirebaseUser userId = FirebaseAuth.getInstance().getCurrentUser();
        DocumentReference user = db.collection("users").document(userId.getUid());
        user.get().addOnCompleteListener(new OnCompleteListener < DocumentSnapshot > () {
            @Override
            public void onComplete(@NonNull Task < DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    nickNameText = findViewById(R.id.nickNameText);
                    doc = task.getResult();
                    fields = new StringBuilder("");

//                    fields.append("name: ").append(doc.get("name"));
//                    fields.append("\naddress: ").append(doc.get("address"));
//                    fields.append("\nbirthDay: ").append(doc.get("birthDay"));
//                    fields.append("\nphoneNumber: ").append(doc.get("phoneNumber"));
                    fields.append(doc.get("nickName"));
                    nickNameText.setText(fields.toString()+" 님");

                    Intent intent = new Intent(CategoryActivity.this, StartActivity.class);

                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });

    }

}
