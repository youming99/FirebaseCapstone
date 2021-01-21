package halla.icsw.capstone_hym;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class SecondFragment  extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    private LinearLayout loginLayout;

    // newInstance constructor for creating fragment with arguments
    public static SecondFragment newInstance(int page, String title) {
        SecondFragment fragment = new SecondFragment();
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
        View view = inflater.inflate(R.layout.activity_second_fragment, container, false);
        ImageView g1 = (ImageView) view.findViewById(R.id.g1Image);
        Glide.with(this).load(R.drawable.g1).into(g1);
        ImageView g2 = (ImageView) view.findViewById(R.id.g2Image);
        Glide.with(this).load(R.drawable.g2).into(g2);

        loginLayout = view.findViewById(R.id.loginButton);
        loginLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
