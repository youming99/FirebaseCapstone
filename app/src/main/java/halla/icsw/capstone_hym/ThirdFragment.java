package halla.icsw.capstone_hym;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class ThirdFragment  extends Fragment {
    // Store instance variables
    private String title;
    private int page;

    AlertDialog.Builder builder2;
    LinearLayout countryStudyLayout, countryGameLayout, layout;
    TextView asiaText, europeText, nAmericaText, sAmericaText, africaText, oceaniaText, asiaGameText, europeGameText, nAmericaGameText, sAmericaGameText, africaGameText, oceaniaGameText, allWorldGameText ;


    private CharSequence[] game = {"나라 맞추기", "수도 맞추기"};
    AlertDialog alertDialog2;
    // newInstance constructor for creating fragment with arguments
    public static ThirdFragment newInstance(int page, String title) {
        ThirdFragment fragment = new ThirdFragment();
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
    private TextView.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.countryStudyText: // 나라수도 공부하기
                    countryStudyLayout.setVisibility(View.VISIBLE);
                    countryGameLayout.setVisibility(View.GONE);
                    break;
                case R.id.countryGameText: // 나라수도 게임하기
                    countryStudyLayout.setVisibility(View.GONE);
                    countryGameLayout.setVisibility(View.VISIBLE);
                    break;

            }
        }
    };

    private TextView.OnClickListener onClick2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.asiaText:
                    Intent intent = new Intent(getActivity(),Country_Listview_Activity.class);
                    intent.putExtra("study", "아시아");
                    startActivity(intent);
                    break;

                case R.id.europeText:
                    intent = new Intent(getActivity(),Country_Listview_Activity.class);
                    intent.putExtra("study", "유럽");
                    startActivity(intent);
                    break;

                case R.id.nAmericaText:
                    intent = new Intent(getActivity(),Country_Listview_Activity.class);
                    intent.putExtra("study", "북아메리카");
                    startActivity(intent);
                    break;

                case R.id.sAmericaText:
                    intent = new Intent(getActivity(),Country_Listview_Activity.class);
                    intent.putExtra("study", "남아메리카");
                    startActivity(intent);
                    break;

                case R.id.africaText:
                    intent = new Intent(getActivity(),Country_Listview_Activity.class);
                    intent.putExtra("study", "아프리카");
                    startActivity(intent);
                    break;

                case R.id. oceaniaText:
                    intent = new Intent(getActivity(),Country_Listview_Activity.class);
                    intent.putExtra("study", "오세아니아");
                    startActivity(intent);
                    break;


            }

        }
    };

    private TextView.OnClickListener onClick3 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.asiaGameText:
                    builder2 = new AlertDialog.Builder(getActivity());
                    builder2.setTitle("게임할 모드 고르기");
                    builder2.setItems(game, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int id) {
                            if (game[id].equals("나라 맞추기")) {
                                Intent intent = new Intent(getActivity(), Country_Game2_Activity.class);
                                intent.putExtra("continent", asiaGameText.getText().toString());
                                intent.putExtra("game", game[id]);
                                startActivity(intent);
                            }
                            if (game[id].equals("수도 맞추기")) {
                                Intent intent = new Intent(getActivity(), Country_Game2_Activity.class);
                                intent.putExtra("continent", asiaGameText.getText().toString());
                                intent.putExtra("game", game[id]);
                                startActivity(intent);
                            }
                            //dialog.dismiss();
                        }
                    });
                    alertDialog2 = builder2.show();
                    alertDialog2.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
                    alertDialog2.show();
                    break;

                case R.id.europeGameText:
                    builder2 = new AlertDialog.Builder(getActivity());
                    builder2.setTitle("게임할 모드 고르기");
                    builder2.setItems(game, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int id) {
                            if (game[id].equals("나라 맞추기")) {
                                Intent intent = new Intent(getActivity(), Country_Game2_Activity.class);
                                intent.putExtra("continent", europeGameText.getText().toString());
                                intent.putExtra("game", game[id]);
                                startActivity(intent);
                            }
                            if (game[id].equals("수도 맞추기")) {
                                Intent intent = new Intent(getActivity(), Country_Game2_Activity.class);
                                intent.putExtra("continent", europeGameText.getText().toString());
                                intent.putExtra("game", game[id]);
                                startActivity(intent);
                            }
                            //dialog.dismiss();
                        }
                    });
                    alertDialog2 = builder2.show();
                    alertDialog2.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
                    alertDialog2.show();
                    break;

                case R.id.nAmericaGameText:
                    builder2 = new AlertDialog.Builder(getActivity());
                    builder2.setTitle("게임할 모드 고르기");
                    builder2.setItems(game, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int id) {
                            if (game[id].equals("나라 맞추기")) {
                                Intent intent = new Intent(getActivity(), Country_Game2_Activity.class);
                                intent.putExtra("continent", nAmericaGameText.getText().toString());
                                intent.putExtra("game", game[id]);
                                startActivity(intent);
                            }
                            if (game[id].equals("수도 맞추기")) {
                                Intent intent = new Intent(getActivity(), Country_Game2_Activity.class);
                                intent.putExtra("continent", nAmericaGameText.getText().toString());
                                intent.putExtra("game", game[id]);
                                startActivity(intent);
                            }
                            //dialog.dismiss();
                        }
                    });
                    alertDialog2 = builder2.show();
                    alertDialog2.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
                    alertDialog2.show();
                    break;

                case R.id.sAmericaGameText:
                    builder2 = new AlertDialog.Builder(getActivity());
                    builder2.setTitle("게임할 모드 고르기");
                    builder2.setItems(game, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int id) {
                            if (game[id].equals("나라 맞추기")) {
                                Intent intent = new Intent(getActivity(), Country_Game2_Activity.class);
                                intent.putExtra("continent", sAmericaGameText.getText().toString());
                                intent.putExtra("game", game[id]);
                                startActivity(intent);
                            }
                            if (game[id].equals("수도 맞추기")) {
                                Intent intent = new Intent(getActivity(), Country_Game2_Activity.class);
                                intent.putExtra("continent", sAmericaGameText.getText().toString());
                                intent.putExtra("game", game[id]);
                                startActivity(intent);
                            }
                            //dialog.dismiss();
                        }
                    });
                    alertDialog2 = builder2.show();
                    alertDialog2.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
                    alertDialog2.show();
                    break;

                case R.id.africaGameText:
                    builder2 = new AlertDialog.Builder(getActivity());
                    builder2.setTitle("게임할 모드 고르기");
                    builder2.setItems(game, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int id) {
                            if (game[id].equals("나라 맞추기")) {
                                Intent intent = new Intent(getActivity(), Country_Game2_Activity.class);
                                intent.putExtra("continent", africaGameText.getText().toString());
                                intent.putExtra("game", game[id]);
                                startActivity(intent);
                            }
                            if (game[id].equals("수도 맞추기")) {
                                Intent intent = new Intent(getActivity(), Country_Game2_Activity.class);
                                intent.putExtra("continent", africaGameText.getText().toString());
                                intent.putExtra("game", game[id]);
                                startActivity(intent);
                            }
                            //dialog.dismiss();
                        }
                    });
                    alertDialog2 = builder2.show();
                    alertDialog2.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
                    alertDialog2.show();
                    break;

                case R.id. oceaniaGameText:
                    builder2 = new AlertDialog.Builder(getActivity());
                    builder2.setTitle("게임할 모드 고르기");
                    builder2.setItems(game, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int id) {
                            if (game[id].equals("나라 맞추기")) {
                                Intent intent = new Intent(getActivity(), Country_Game2_Activity.class);
                                intent.putExtra("continent", oceaniaGameText.getText().toString());
                                intent.putExtra("game", game[id]);
                                startActivity(intent);
                            }
                            if (game[id].equals("수도 맞추기")) {
                                Intent intent = new Intent(getActivity(), Country_Game2_Activity.class);
                                intent.putExtra("continent", oceaniaGameText.getText().toString());
                                intent.putExtra("game", game[id]);
                                startActivity(intent);
                            }
                            //dialog.dismiss();
                        }
                    });
                    alertDialog2 = builder2.show();
                    alertDialog2.getWindow().setBackgroundDrawableResource(R.drawable.dialog1);
                    alertDialog2.show();
                    break;

//                case R.id. allWorldGameText:
//                    intent = new Intent(getActivity(),Country_Game1_Activity.class);
//                    intent.putExtra("game", "전세계");
//                    startActivity(intent);
//                    break;
            }

        }
    };


    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_third_fragment, container, false);
        countryStudyLayout = view.findViewById(R.id.countryStudyLayout);
        countryGameLayout = view.findViewById(R.id.countryGameLayout);
        asiaText = view.findViewById(R.id.asiaText);
        europeText = view.findViewById(R.id.europeText);
        nAmericaText = view.findViewById(R.id.nAmericaText);
        sAmericaText = view.findViewById(R.id.sAmericaText);
        africaText = view.findViewById(R.id.africaText);
        oceaniaText = view.findViewById(R.id.oceaniaText);

        asiaGameText = view.findViewById(R.id.asiaGameText);
        europeGameText = view.findViewById(R.id.europeGameText);
        nAmericaGameText = view.findViewById(R.id.nAmericaGameText);
        sAmericaGameText = view.findViewById(R.id.sAmericaGameText);
        africaGameText = view.findViewById(R.id.africaGameText);
        oceaniaGameText = view.findViewById(R.id.oceaniaGameText);
        //allWorldGameText = view.findViewById(R.id.allWorldGameText);

        view.findViewById(R.id.countryStudyText).setOnClickListener(onClick); // 나라수도 공부하기
        view.findViewById(R.id.countryGameText).setOnClickListener(onClick); // 나라수도 게임하기

        view.findViewById(R.id.asiaText).setOnClickListener(onClick2);
        view.findViewById(R.id.europeText).setOnClickListener(onClick2);
        view.findViewById(R.id.nAmericaText).setOnClickListener(onClick2);
        view.findViewById(R.id.sAmericaText).setOnClickListener(onClick2);
        view.findViewById(R.id.africaText).setOnClickListener(onClick2);
        view.findViewById(R.id.oceaniaText).setOnClickListener(onClick2);

        view.findViewById(R.id.asiaGameText).setOnClickListener(onClick3);
        view.findViewById(R.id.europeGameText).setOnClickListener(onClick3);
        view.findViewById(R.id.nAmericaGameText).setOnClickListener(onClick3);
        view.findViewById(R.id.sAmericaGameText).setOnClickListener(onClick3);
        view.findViewById(R.id.africaGameText).setOnClickListener(onClick3);
        view.findViewById(R.id.oceaniaGameText).setOnClickListener(onClick3);
        //view.findViewById(R.id.allWorldGameText).setOnClickListener(onClick3);
        return view;
    }

}