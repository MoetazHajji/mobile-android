package tn.esprit.androidproject.Config;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tn.esprit.androidproject.R;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.MyViewHolder> {

    private Context context;
    ArrayList<String> educ_school,educ_degree,educ_startDate,educ_endDate;

    public EducationAdapter(
            Context context,
            ArrayList educ_school,
            ArrayList educ_degree,
            ArrayList educ_startDate,
            ArrayList educ_endDate
    ){
        this.context = context;
        this.educ_endDate=educ_endDate;
        this.educ_degree=educ_degree;
        this.educ_startDate=educ_startDate;
        this.educ_school=educ_school;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_education_list_acitviy, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.degree.setText(String.valueOf(educ_degree.get(position)));
        myViewHolder.school.setText(String.valueOf(educ_school.get(position)));
        myViewHolder.endDate.setText(String.valueOf(educ_endDate.get(position)));
        myViewHolder.startDate.setText(String.valueOf(educ_startDate.get(position)));
    }

    @Override
    public int getItemCount() {
        return educ_school.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView school, degree, startDate,endDate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            school = itemView.findViewById(R.id.educationSchool);
            degree = itemView.findViewById(R.id.educationDegree);
            startDate = itemView.findViewById(R.id.educationStartDate);
            endDate = itemView.findViewById(R.id.educationEndDate);
        }
    }
}
