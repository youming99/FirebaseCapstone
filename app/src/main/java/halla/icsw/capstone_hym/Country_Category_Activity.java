package halla.icsw.capstone_hym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Country_Category_Activity extends AppCompatActivity {
    private TextView asiaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country__category_);

        findViewById(R.id.asiaText).setOnClickListener(onClick);
        findViewById(R.id.uropeText).setOnClickListener(onClick);
        findViewById(R.id.northAmericaText).setOnClickListener(onClick);
    }

    private TextView.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.asiaText:
                    Intent intent1 = new Intent(Country_Category_Activity.this, Country_Listview_Activity.class);
//                    intent1.getStringExtra(asiaText.getText().toString(),"대륙");
                    startActivity(intent1);
                    break;
                case R.id.uropeText:
                    Intent intent2 = new Intent(Country_Category_Activity.this, Country_Listview_Activity.class);
                    startActivity(intent2);
                    break;
                case R.id.northAmericaText:
                    break;

            }
        }
    };
}
