package tn.esprit.androidproject.Config;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tn.esprit.androidproject.ProfileUser.UpdatePositionActivity;
import tn.esprit.androidproject.R;

public class PositionAdapter extends RecyclerView.Adapter<PositionAdapter.MyViewHolder>{
    private Context context;
    private Activity activity;
    ArrayList<String> _id,pos_title,pos_companyName,pos_startDate,pos_endDate,pos_location,pos_industry,pos_description;

    public PositionAdapter(
            Activity activity,
            Context context,
            ArrayList _id,
            ArrayList pos_title,
            ArrayList pos_companyName,
            ArrayList pos_startDate,
            ArrayList pos_endDate,
            ArrayList pos_location,
            ArrayList pos_industry,
            ArrayList pos_description

    ){
        this.activity=activity;
        this.context = context;
        this._id=_id;
        this.pos_title=pos_title;
        this.pos_companyName=pos_companyName;
        this.pos_startDate=pos_startDate;
        this.pos_endDate=pos_endDate;
        this.pos_description = pos_description;
        this.pos_location = pos_location;
        this.pos_industry = pos_industry;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_positions_list, parent,false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int position ) {
        myViewHolder._id.setText(String.valueOf(_id.get(position)));
        myViewHolder.title.setText(String.valueOf(pos_title.get(position)));
        myViewHolder.companyName.setText(String.valueOf(pos_companyName.get(position)));
        myViewHolder.endDate.setText(String.valueOf(pos_endDate.get(position)));
        myViewHolder.startDate.setText(String.valueOf(pos_startDate.get(position)));
        myViewHolder.location.setText(String.valueOf(pos_location.get(position)));
        myViewHolder.description.setText(String.valueOf(pos_description.get(position)));
        myViewHolder.industry.setText(String.valueOf(pos_industry.get(position)));
        if (context != null && activity != null) {
            myViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdatePositionActivity.class);
                    intent.putExtra("_id", String.valueOf(pos_title.get(position)));
                    intent.putExtra("title", String.valueOf(pos_title.get(position)));
                    intent.putExtra("companyName", String.valueOf(pos_companyName.get(position)));
                    intent.putExtra("endDate", String.valueOf(pos_endDate.get(position)));
                    intent.putExtra("startDate", String.valueOf(pos_startDate.get(position)));
                    intent.putExtra("location", String.valueOf(pos_location.get(position)));
                    intent.putExtra("description", String.valueOf(pos_description.get(position)));
                    intent.putExtra("industry", String.valueOf(pos_industry.get(position)));
                    activity.startActivityForResult(intent, 1);
                }
            });
        } else {
            Log.e("MyAdapter", "Context or activity is null");
        }
    }

    @Override
    public int getItemCount() {
        return pos_title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView _id,title, companyName, startDate,endDate,location,description,industry;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            _id = itemView.findViewById(R.id.position_id);
            title = itemView.findViewById(R.id.positionTitle);
            companyName = itemView.findViewById(R.id.positionCompanyName);
            startDate = itemView.findViewById(R.id.positionStartDate);
            endDate = itemView.findViewById(R.id.positionEndDate);
            location = itemView.findViewById(R.id.location);
            description = itemView.findViewById(R.id.description);
            industry = itemView.findViewById(R.id.industryPos);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
//            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
//            mainLayout.setAnimation(translate_anim);
        }
    }
}
