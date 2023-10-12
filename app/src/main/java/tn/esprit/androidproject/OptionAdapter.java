package tn.esprit.androidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class OptionAdapter extends ArrayAdapter<String> {

    public OptionAdapter(@NonNull Context context, int resource, @NonNull List<String> options) {
        super(context, resource, options);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_option, parent, false);
        }

        String optionText = getItem(position);

        TextView optionTextView = convertView.findViewById(R.id.optionTextView);
        optionTextView.setText(optionText);

        return convertView;
    }
}
