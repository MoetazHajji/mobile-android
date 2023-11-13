package tn.esprit.androidproject.Config;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tn.esprit.androidproject.ProfileUser.UpdatePositionActivity;
import tn.esprit.androidproject.R;

public class PositionAdapter extends RecyclerView.Adapter<PositionAdapter.MyViewHolder>{
    private Context context;
    private Activity activity;
    ArrayList<String> pos_title,pos_companyName,pos_startDate,pos_endDate;

    public PositionAdapter(
            Context context,
            ArrayList pos_title,
            ArrayList pos_companyName,
            ArrayList pos_startDate,
            ArrayList pos_endDate
    ){
        this.context = context;
        this.pos_title=pos_title;
        this.pos_companyName=pos_companyName;
        this.pos_startDate=pos_startDate;
        this.pos_endDate=pos_endDate;
    }
    @NonNull
    @Override
    public PositionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_positions_list, parent,false);
        return new PositionAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PositionAdapter.MyViewHolder myViewHolder, int position) {
        myViewHolder.title.setText(String.valueOf(pos_title.get(position)));
        myViewHolder.companyName.setText(String.valueOf(pos_companyName.get(position)));
        myViewHolder.endDate.setText(String.valueOf(pos_endDate.get(position)));
        myViewHolder.startDate.setText(String.valueOf(pos_startDate.get(position)));
        myViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdatePositionActivity.class);
                intent.putExtra("title", String.valueOf(pos_title.get(position)));
                intent.putExtra("companyName", String.valueOf(pos_companyName.get(position)));
                intent.putExtra("endDate", String.valueOf(pos_endDate.get(position)));
                intent.putExtra("startDate", String.valueOf(pos_startDate.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pos_title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, companyName, startDate,endDate;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.positionTitle);
            companyName = itemView.findViewById(R.id.positionCompanyName);
            startDate = itemView.findViewById(R.id.positionStartDate);
            endDate = itemView.findViewById(R.id.positionEndDate);
        }
    }
}
