package pe.elcomercio.materialpills;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import pe.elcomercio.materialpillsbox.MaterialPillsBox;
import pe.elcomercio.materialpillsbox.PillEntity;

public class DeletePillsActivity extends AppCompatActivity {

    MaterialPillsBox materialPillsBox;
    private Button btnAddPill;
    private Button btnDeletePills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_pills);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        materialPillsBox = (MaterialPillsBox) findViewById(R.id.mtbArea);
        btnAddPill = (Button) findViewById(R.id.btnAddPill);
        btnDeletePills = (Button) findViewById(R.id.btnDeletePills);

        List<PillEntity> pillEntityList = new ArrayList<>();
        pillEntityList.add(new PillEntity(0, "CarlitosDroid"));
        pillEntityList.add(new PillEntity(0, "CarlosMB"));
        pillEntityList.add(new PillEntity(2, "OrbisMobile"));
        pillEntityList.add(new PillEntity(2, "OrbisMobile"));
        pillEntityList.add(new PillEntity(2, "OrbisMobile"));

        materialPillsBox.addPillsList(pillEntityList);

        btnAddPill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialPillsBox.addPill(new PillEntity(2, "OrbisMobile"));
            }
        });
        btnDeletePills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialPillsBox.removeAllPills();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialPillsBox.addPillAtPosition(0, new PillEntity(3, "New Tag"));
            }
        });
    }
}
