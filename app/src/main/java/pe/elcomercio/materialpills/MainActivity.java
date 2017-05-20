package pe.elcomercio.materialpills;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.elcomercio.materialpillsbox.MaterialPillsBox;

public class MainActivity extends AppCompatActivity {

    MaterialPillsBox materialPillsBox;
    LayoutInflater mInflater;
    private List<String> pillNamesList = new ArrayList<>();
//    private String[] mVals = new String[]
//            {"Do", "one thing", "at a time", "and do well.", "Never", "forget",
//                    "to say", "thanks.", "Keep on", "going ", "never give up."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        materialPillsBox = (MaterialPillsBox) findViewById(R.id.mtbArea);
        mInflater = LayoutInflater.from(this);

        for (int i = 0; i < 10; i++) {
            pillNamesList.add("Init " + i);
        }

        for (int i = 0; i < pillNamesList.size(); i++) {
            initFlowlayout2(i);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pillNamesList.add("New " + pillNamesList.size());
                initFlowlayout2(pillNamesList.size()-1);
                Log.e("ADDED", "TAG " + pillNamesList.get(pillNamesList.size()-1));
            }
        });
    }

    public void initFlowlayout2(int i) {
        final LinearLayout linear = (LinearLayout) mInflater.inflate(R.layout.pills_box_layout, materialPillsBox, false);
        TextView tv1 = (TextView) linear.findViewById(R.id.lblPill);
        tv1.setText(pillNamesList.get(i));
        linear.setTag(i);
        materialPillsBox.addView(linear);
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("REMOVED", "TAG " + linear.getTag());
                materialPillsBox.removeView(v);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
