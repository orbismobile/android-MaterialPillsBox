package pe.elcomercio.materialpills;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import pe.elcomercio.materialpillsbox.MaterialPillsBox;
import pe.elcomercio.materialpillsbox.PillEntity;

public class DeletePillsActivity extends AppCompatActivity {

    MaterialPillsBox materialPillsBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_pills);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        materialPillsBox = (MaterialPillsBox) findViewById(R.id.mtbArea);

        materialPillsBox.addPill(new PillEntity(0, "CarlitosDroid"));
        materialPillsBox.addPill(new PillEntity(0, "CarlosMB"));
        materialPillsBox.addPill(new PillEntity(1, "OrbisMobile"));
        materialPillsBox.addPill(new PillEntity(2, "Jan"));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialPillsBox.addPillAtPosition(0, new PillEntity(3, "New Tag"));
            }
        });
    }
}
