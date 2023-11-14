package tn.esprit.androidproject.test_management.adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import tn.esprit.androidproject.R;
import tn.esprit.androidproject.test_management.models.TestModel;
import tn.esprit.androidproject.test_management.activities.EditActivity;
import tn.esprit.androidproject.test_management.constants.Constants;
import tn.esprit.androidproject.test_management.database.AppDatabase;

public class TestAdaptor extends RecyclerView.Adapter<TestAdaptor.MyViewHolder>  {
    private Context context;
    private List<TestModel> tTestList;

    public TestAdaptor(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout, viewGroup, false);
        return new MyViewHolder(view);
    }

//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
//        Date testDate = tTestList.get(i).getTestDate();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // You can choose the desired date format
//        String formattedDate = dateFormat.format(testDate);
//
//        myViewHolder.testName.setText(tTestList.get(i).getTestName());
//        myViewHolder.testDate.setText(formattedDate);
//
//    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TestModel currentTest = tTestList.get(position);

        if (currentTest != null && currentTest.getTestDate() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date testDate = currentTest.getTestDate();

            // Check if the date is not null before formatting
            if (testDate != null) {
                String formattedDate = dateFormat.format(testDate.getTime());

                // Now use the formattedDate as needed
                holder.testDate.setText(formattedDate);
            } else {
                // Handle the case where the date is null
                holder.testDate.setText("N/A");
            }

            // Other binding logic...
            holder.testName.setText(tTestList.get(position).getTestName());
        }
    }

    @Override
    public int getItemCount() {
        if (tTestList== null) {
            return 0;
        }
        return tTestList.size();

    }

    public void setTasks(List<TestModel> personList) {
        tTestList = personList;
        notifyDataSetChanged();
    }

    public List<TestModel> getTasks() {

        return tTestList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView testName, testDate;
        ImageView editImage;
        AppDatabase mDb;

        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            mDb = AppDatabase.getInstance(context);
            testName = itemView.findViewById(R.id.test_name_tv);
            testDate = itemView.findViewById(R.id.test_date_tv);
            editImage = itemView.findViewById(R.id.icon);
            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int elementId = tTestList.get(getAdapterPosition()).getIdTest();
                    Intent i = new Intent(context, EditActivity.class);
                    i.putExtra(Constants.UPDATE_Test_Id, elementId);
                    context.startActivity(i);
                }
            });
        }
    }
}
