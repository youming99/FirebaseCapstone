package halla.icsw.capstone_hym;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> implements OnListItemClickListener {
    OnListItemClickListener mListener;

    private ArrayList<Country_Capital> arrayList;
    private Context context;
    public Intent intent;

    public CustomAdapter(ArrayList<Country_Capital> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        holder.setOnListItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getPicture())
                .into(holder.picture);
        holder.capitalText.setText(arrayList.get(position).getCapital());
        holder.countryText.setText(arrayList.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        return (arrayList!=null?arrayList.size():0);
    }

    @Override
    public void onListItemClick(int position) { // recycleview list 클릭 시
        //Toast.makeText(context, arrayList.get(position).getCountry(), Toast.LENGTH_SHORT).show();
        intent = new Intent(context, Country_Big_Activity.class);
        intent.putExtra("country",arrayList.get(position).getCountry());
        intent.putExtra("capital",arrayList.get(position).getCapital());
        intent.putExtra("picture",arrayList.get(position).getPicture());
        ((Country_Listview_Activity)context).startActivity(intent);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView picture;
        TextView countryText;
        TextView capitalText;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.picture = itemView.findViewById(R.id.picture);
            this.countryText = itemView.findViewById(R.id.countryText);
            this.capitalText = itemView.findViewById(R.id.capitalText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onListItemClick(getAdapterPosition());
                }
            });

        }

        public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
            mListener = onListItemClickListener;
        }
    }


}


