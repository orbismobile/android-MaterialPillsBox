package pe.elcomercio.materialpills;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.elcomercio.materialpillsbox.MaterialPillsBox;

public class DeletePillsActivity extends AppCompatActivity {

    MaterialPillsBox materialPillsBox;
    LayoutInflater mInflater;
    private List<String> pillNamesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_pills);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        materialPillsBox = (MaterialPillsBox) findViewById(R.id.mtbArea);
        mInflater = LayoutInflater.from(this);

        for (int i = 0; i < 10; i++) {
            pillNamesList.add("Init " + i);
        }

        for (int i = 0; i < pillNamesList.size(); i++) {
            initPillsBox(i, false);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pillNamesList.add("New " + pillNamesList.size());
                initPillsBox(pillNamesList.size()-1, true);
                Log.e("ADDED", "TAG " + pillNamesList.get(pillNamesList.size()-1));
            }
        });
    }


    public void initPillsBox(int i, boolean addAtFirst) {
        final LinearLayout linear = (LinearLayout) mInflater.inflate(R.layout.pills_box_layout, materialPillsBox, false);
        TextView tv1 = (TextView) linear.findViewById(R.id.lblPill);
        tv1.setText(pillNamesList.get(i));
        linear.setTag(i);
        if(addAtFirst){
            materialPillsBox.addView(linear, 0);
        }else{
            materialPillsBox.addView(linear);
        }

        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("REMOVED", "TAG " + linear.getTag());
                v.setBackgroundColor(Color.CYAN);
//                materialPillsBox.removeView(v);
            }
        });
    }
}
