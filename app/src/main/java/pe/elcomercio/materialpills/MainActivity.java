package pe.elcomercio.materialpills;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.elcomercio.materialpillsbox.MaterialPillsBox;

public class MainActivity extends AppCompatActivity {

    MaterialPillsBox materialPillsBox;
    private List<String> pillNamesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        materialPillsBox = (MaterialPillsBox) findViewById(R.id.mtbArea);


        pillNamesList.add("CarlitosMB");
        pillNamesList.add("CarlitosDroid");
        pillNamesList.add("Jan");
        pillNamesList.add("Ricardo");
        pillNamesList.add("Carlos");


        for (int i = 0; i < pillNamesList.size(); i++) {
            final LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.pills_box_layout, materialPillsBox, false);
            ImageView imageView = (ImageView) linearLayout.findViewById(R.id.imgClose);
            TextView textView = (TextView) linearLayout.findViewById(R.id.lblPill);
            textView.setText(pillNamesList.get(i));
            linearLayout.setTag(i);
            textView.setTag(i);
            materialPillsBox.addView(linearLayout);


            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = (int) v.getTag();
                    View view = materialPillsBox.getChildAt(i);
                    Toast.makeText(MainActivity.this, ""+ i , Toast.LENGTH_SHORT).show();
                    materialPillsBox.removeView(view);


                }
            });

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pillNamesList.add("Nuevo ");

                final LinearLayout aloha = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.pills_box_layout, materialPillsBox, false);
                TextView textView = (TextView) aloha.findViewById(R.id.lblPill);
                textView.setText(pillNamesList.get(pillNamesList.size() -1));
                aloha.setTag(pillNamesList.size());
                textView.setTag(pillNamesList.size());
                materialPillsBox.addView(aloha);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = (int) v.getTag();
                        View view = materialPillsBox.getChildAt(i);
                        Toast.makeText(MainActivity.this, ""+ i , Toast.LENGTH_SHORT).show();
                        materialPillsBox.removeView(view);

                        Toast.makeText(MainActivity.this, "CARLITOS  "  +  aloha.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });
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
