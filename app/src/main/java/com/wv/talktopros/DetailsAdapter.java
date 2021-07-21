package com.wv.talktopros;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailsAdapter extends  RecyclerView.Adapter<DetailsAdapter.MyViewHolder> {

    Context context;
    ArrayList<Specialist> userArrayList;

    public DetailsAdapter(Context context, ArrayList<Specialist> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public DetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull   ViewGroup parent, int viewType)
    {
    View v = LayoutInflater.from(context).inflate(R.layout.spec_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull   DetailsAdapter.MyViewHolder holder, int position) {
        Specialist user;
        user = userArrayList.get(position);
        holder.fname.setText(user.fname);
        holder.lname.setText(user.lname);
        holder.speciality.setText(user.speciality);
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fname,lname,speciality;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
                    fname= itemView.findViewById(R.id.fname);
                    lname=itemView.findViewById(R.id.lname);
                    speciality =itemView.findViewById(R.id.speciality);

        }
    }
}
