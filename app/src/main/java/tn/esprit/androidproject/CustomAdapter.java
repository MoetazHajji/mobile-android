package tn.esprit.androidproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    //int position;
    private ArrayList formation_id, formation_titre, formation_description, formation_objectif;

    CustomAdapter(Activity activity, Context context, ArrayList formation_id, ArrayList formation_titre, ArrayList formation_description,
                  ArrayList formation_objectif){
        this.activity = activity;
        this.context = context;
        this.formation_id = formation_id;
        this.formation_titre = formation_titre;
        this.formation_description = formation_description;
        this.formation_objectif = formation_objectif;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    public void onBindViewHolder( @NonNull final MyViewHolder holder, final int position) {
        // this.position=position;
        holder.formation_id_txt.setText(String.valueOf(formation_id.get(position)));
        holder.formation_titre_txt.setText(String.valueOf(formation_titre.get(position)));
        holder.formation_description_txt.setText(String.valueOf(formation_description.get(position)));
        holder.formation_objectif_txt.setText(String.valueOf(formation_objectif.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id", String.valueOf(formation_id.get(position)));
            intent.putExtra("titre", String.valueOf(formation_titre.get(position)));
            intent.putExtra("description", String.valueOf(formation_description.get(position)));
            intent.putExtra("objectif", String.valueOf(formation_objectif.get(position)));
            activity.startActivityForResult(intent, 1);
        });

        /*holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(formation_id.get(position)));
                intent.putExtra("titre", String.valueOf(formation_titre.get(position)));
                intent.putExtra("description", String.valueOf(formation_description.get(position)));
                intent.putExtra("objectif", String.valueOf(formation_objectif.get(position)));
                context.startActivity(intent);
            }
        });*/


    }

    @Override
    public int getItemCount() {
        return formation_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView formation_id_txt, formation_titre_txt, formation_description_txt, formation_objectif_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            formation_id_txt = itemView.findViewById(R.id.formation_titre_txt);
            formation_titre_txt = itemView.findViewById(R.id.formation_titre_txt);
            formation_description_txt = itemView.findViewById(R.id.formation_description_txt);
            formation_objectif_txt = itemView.findViewById(R.id.formation_objectif_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}

