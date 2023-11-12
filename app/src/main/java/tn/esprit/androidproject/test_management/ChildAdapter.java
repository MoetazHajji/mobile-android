package tn.esprit.androidproject.test_management;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tn.esprit.androidproject.R;

public class ChildAdapter  extends RecyclerView.Adapter<ChildAdapter.ViewHolder>{

    private ArrayList<QuizModel> quizList;

    public ChildAdapter(ArrayList<QuizModel> quizList) {
        this.quizList = quizList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hidden_child, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuizModel quizModel = quizList.get(position);
        holder.bind(quizModel);
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagev);
            textView = itemView.findViewById(R.id.quiz1);
        }

        public void bind(QuizModel quizModel) {
            imageView.setImageResource(R.drawable.questionmark);
            textView.setText(quizModel.getName());
        }
    }


}
