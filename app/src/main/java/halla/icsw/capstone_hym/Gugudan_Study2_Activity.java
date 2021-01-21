package halla.icsw.capstone_hym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Gugudan_Study2_Activity extends AppCompatActivity {

    TextView danText;
    ListView listView;
    String[] danList;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gugudan__study2_);

        danText = findViewById(R.id.danText);
        listView = findViewById(R.id.listView);
        danList = new String[18];

        final ArrayList<String> list = new ArrayList<>();

        for (i=2; i<=19; i++) {
            list.add(i + " 단");
        }

        // 2단~19단 리스트뷰에 뿌리기 위해 ArrayAdapter 사용
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, //context(액티비티 인스턴스)
               R.layout.list_style, // 한 줄에 하나의 텍스트 아이템만 보여주는 레이아웃 파일
                // 한 줄에 보여지는 아이템 갯수나 구성을 변경하려면 여기에 새로만든 레이아웃을 지정하면 됩니다.
                list  // 데이터가 저장되어 있는 ArrayList 객체
        );
        listView.setAdapter(adapter);

        //listView 클릭 시
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String danName = (String)adapterView.getAdapter().getItem(position);
                Intent intent = new Intent(Gugudan_Study2_Activity.this, Gugudan2_19Activity.class);
                intent.putExtra("danName",danName);
                startActivity(intent);
            }
        });

    }
}
