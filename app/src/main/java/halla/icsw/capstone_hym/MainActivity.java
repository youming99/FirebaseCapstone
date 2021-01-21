package halla.icsw.capstone_hym;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

public class MainActivity extends AppCompatActivity {
    String userId;
    TextView nickNameText;

    StringBuilder fields;
    DocumentSnapshot doc;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //mDatabase = mFirebaseDatabase.getReference("users");


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseDatabase.getReference("users");
        //userId = user.getUid();




        nickNameText = findViewById(R.id.nickNameText);
        //nickNameText.setText(userId + "님");

        findViewById(R.id.signUpButton).setOnClickListener(onClickListener);
        findViewById(R.id.loginButton).setOnClickListener(onClickListener);
        findViewById(R.id.logoutButton).setOnClickListener(onClickListener);

        //dataRead();
        //userId = user.getUid();
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.signUpButton :
                    Intent intent = new Intent(MainActivity.this, SignUp_Activity.class);
                    startActivity(intent);
                    break;
                case R.id.loginButton :
                    Intent intent2 = new Intent(MainActivity.this, LogIn_Activity.class);
                    startActivity(intent2);
                    break;
                case R.id.logoutButton :
                    logoutDialog();
                    break;
            }
        }
    };

    private void dataRead(){
        FirebaseUser userId = FirebaseAuth.getInstance().getCurrentUser();
        DocumentReference user = db.collection("users").document(userId.getUid());
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
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

                    //Intent intent = new Intent(CategoryActivity.this, StartActivity.class);

                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Intent intent2 = new Intent(MainActivity.this, LogIn_Activity.class);
                        startActivity(intent2);
                    }
                });

    }

    //로그아웃 다이얼로그
    void logoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("로그아웃 하시겠습니까?");
        builder
                .setMessage("예/아니오 선택하시오.")
                .setCancelable(false)
                .setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 프로그램을 종료한다
                               FirebaseAuth.getInstance().signOut();
                            }
                        })
                .setNegativeButton("아니오",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 다이얼로그를 취소한다
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = builder.show();
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
        alertDialog.show();
    }
}
