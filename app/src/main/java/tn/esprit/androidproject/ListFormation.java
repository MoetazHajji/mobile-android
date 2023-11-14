package tn.esprit.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ListFormation extends AppCompatActivity {
String fruitList[]={"PhP",".Net","SpringBoot","Angular","Nodejs"};
int fruitImages[]={R.drawable.php,R.drawable.download,R.drawable.spring,R.drawable.angular,R.drawable.node};
ListView listView;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_formation);
      listView=(ListView) findViewById(R.id.customListView);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getBaseContext(),fruitList,fruitImages);
        listView.setAdapter(customBaseAdapter);
    }
}