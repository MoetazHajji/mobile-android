package tn.esprit.androidproject.test_management.adaptors;

import android.content.Context;
import android.content.Intent;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tn.esprit.androidproject.R;
import tn.esprit.androidproject.test_management.Test;
import tn.esprit.androidproject.test_management.models.TestModel;


public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    // View holder class for initializing of your views such as TextView and Imageview



    private  List<TestModel> testModelArrayList;
    private Context context;
    private ArrayList<TestModel> filteredList;


    public TestAdapter( Context context, ArrayList<TestModel> testModelArrayList) {
        this.context = context;
        this.testModelArrayList = testModelArrayList;
    }




    @NonNull
    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,  int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position) {


        TestModel model = testModelArrayList.get(position);
        Date testDate = model.getTestDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // You can choose the desired date format
        String formattedDate = dateFormat.format(testDate);
        holder.testName.setText(model.getTestName());
        holder.testDate.setText(formattedDate);


        holder.arrow.setOnClickListener(view -> {
            // If the CardView is already expanded, set its visibility
            // to gone and change the expand less icon to expand more.
            TransitionManager.beginDelayedTransition( holder.cardView, new AutoTransition());
            if ( holder.hiddenView.getVisibility() == View.VISIBLE) {
                // The transition of the hiddenView is carried out by the TransitionManager class.
                // Here we use an object of the AutoTransition Class to create a default transition
                holder.hiddenView.setVisibility(View.GONE);
                holder.arrow.setImageResource(R.drawable.drop_up_arrow);
            }

            // If the CardView is not expanded, set its visibility to
            // visible and change the expand more icon to expand less.
            else {

               holder.hiddenView.setVisibility(View.VISIBLE);
                holder.arrow.setImageResource(R.drawable.drop_down_arrow);

            }
        });

        holder.testDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.testDate.getContext(), Test.class);
                holder.testDate.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return testModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private  TextView testName;
        private  TextView testDate;
        private  ImageButton arrow;
        private  LinearLayout hiddenView;
        private  CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            testName = itemView.findViewById(R.id.test_name_tv);

            testDate = itemView.findViewById(R.id.test_date_tv);



            cardView = itemView.findViewById(R.id.base_cardview);
            arrow = itemView.findViewById(R.id.arrow_button);
            hiddenView = itemView.findViewById(R.id.hidden_view);

        }
    }

    public void updateData(ArrayList<TestModel> filteredList) {
        testModelArrayList.clear();
        testModelArrayList.addAll(filteredList);
        notifyDataSetChanged();
    }

}
