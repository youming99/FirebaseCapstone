package halla.icsw.capstone_hym;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import me.relex.circleindicator.CircleIndicator;

public class FirstFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    FragmentPagerAdapter adapterViewPager;
    CircleIndicator indicator;

    TextView gugudanunderstatndText, gugudanstudyText, gugudanSimpleGameText, gugudanCarGameText, gugudanChatGameText;
    AlertDialog.Builder builder2;


    // newInstance constructor for creating fragment with arguments
    public static FirstFragment newInstance(int page, String title) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;


    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_first_fragment, container, false);

        view.findViewById(R.id.gugudanunderstatndText).setOnClickListener(onClick);
        view.findViewById(R.id.gugudanstudyText).setOnClickListener(onClick);
        view.findViewById(R.id.gugudanSimpleGameText).setOnClickListener(onClick);
        view.findViewById(R.id.gugudanCarGameText).setOnClickListener(onClick);
        view.findViewById(R.id.gugudanChatGameText).setOnClickListener(onClick);


        return view;
    }

    private TextView.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.gugudanunderstatndText:
                    gugudanUnderstandDialog();
                    break;
                case R.id.gugudanstudyText:
                    gugudanStudyDialog();
                    break;
                case R.id.gugudanSimpleGameText:
                    Intent intent1 = new Intent(getActivity(), Gugudan_SimpleGame_Activity.class);
                    startActivity(intent1);
                    break;
                case R.id.gugudanCarGameText:
                    Intent intent4 = new Intent(getActivity(), Gugudan_CarGame_Activity.class);
                    startActivity(intent4);
                    break;
                case R.id.gugudanChatGameText:
                    Intent intent5 = new Intent(getActivity(), StartActivity.class);
                    startActivity(intent5);
                    break;

            }
        }
    };

    //구구단 이해하기 다이얼로그
    void gugudanUnderstandDialog() {
        final CharSequence[] understand = {"구구단송", "구구단 이해하기"};
        builder2 = new AlertDialog.Builder(getActivity());
        builder2.setTitle("영상 모드 고르기");
        builder2.setItems(understand, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,
                                int id) {
                if (understand[id].equals("구구단송")) {
                    String url = "https://www.youtube.com/watch?v=6EGjhlWU4xI";
                    Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent2);

                }
                if (understand[id].equals("구구단 이해하기")) {
                    String url = "https://www.youtube.com/watch?v=5qx75Cb8Nqw";
                    Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent2);
                }
                //dialog.dismiss();
            }
        });
        AlertDialog alertDialog2 = builder2.show();
        alertDialog2.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
        alertDialog2.show();
    }

    //구구단 공부하기 다이얼로그
    void gugudanStudyDialog() {
        final CharSequence[] studys = {"2단 ~ 9단", "2단 ~ 19단"};
        builder2 = new AlertDialog.Builder(getActivity());
        builder2.setTitle("공부할 모드 고르기");
        builder2.setItems(studys, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,
                                int id) {
                if (studys[id].equals("2단 ~ 9단")) {
                    Intent intent = new Intent(getActivity(), Gugudan_Study_Activity.class);
                    intent.putExtra("study", studys[id]);
                    startActivity(intent);
                }
                if (studys[id].equals("2단 ~ 19단")) {
                    Intent intent = new Intent(getActivity(), Gugudan_Study2_Activity.class);
                    intent.putExtra("study", studys[id]);
                    startActivity(intent);
                }
                //dialog.dismiss();
            }
        });
        AlertDialog alertDialog2 = builder2.show();
        alertDialog2.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
        alertDialog2.show();
    }

}
