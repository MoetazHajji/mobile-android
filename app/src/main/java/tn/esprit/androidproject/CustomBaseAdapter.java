package tn.esprit.androidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {
Context context;
String listFruit[];
int listImages[];
LayoutInflater inflater;
    public CustomBaseAdapter(Context ctx, String [] fruitList,int [] images){
        this.context=ctx;
        this.listFruit=fruitList;
        this.listImages=images;
        inflater = LayoutInflater.from(ctx);

    }
    @Override
    public int getCount() {
        return listFruit.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView txtView=(TextView) view.findViewById(R.id.textView);
        ImageView formImg=(ImageView) view.findViewById(R.id.imageIcom);
        txtView.setText(listFruit[i]);
        formImg.setImageResource(listImages[i]);
        return view;
    }
}
