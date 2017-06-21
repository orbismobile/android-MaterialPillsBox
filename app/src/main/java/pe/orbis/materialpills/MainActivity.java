package pe.orbis.materialpills;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.orbis.materialpills.model.DogEntity;
import pe.orbis.materialpillsbox.MaterialPillsBox;
import pe.orbis.materialpillsbox.OnPillClickListener;

public class MainActivity extends AppCompatActivity implements OnPillClickListener {

    private MaterialPillsBox materialPillsBox;
    private List<Object> objects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        materialPillsBox = (MaterialPillsBox) findViewById(R.id.mtbArea);
        Button btnAddPill = (Button) findViewById(R.id.btnAddPill);
        Button btnDeletePills = (Button) findViewById(R.id.btnDeletePill);

        objects = new ArrayList<>();
        objects.add(new DogEntity("CarlitosDroid", "Boyero de Berna"));
        objects.add(new DogEntity("Jan", "Brabantino"));
        objects.add(new DogEntity("Andres", "Bulldog"));
        objects.add(new DogEntity("Carlo", "Boston"));
        objects.add(new DogEntity("Gerardo", "corg"));
        objects.add(new DogEntity("Amigo ", "carlos "));

        materialPillsBox.initFirstSetup(objects);

        btnAddPill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objects.add(0, new DogEntity("NewName", "NewBreed"));
                materialPillsBox.notifyDataSetChanged();
            }
        });
        btnDeletePills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objects.remove(0);
                materialPillsBox.notifyDataSetChanged();

            }
        });
        materialPillsBox.setOnPillClickListener(this);

    }

    @Override
    public void onPillClick(int position) {
        DogEntity dogEntity = (DogEntity) objects.get(position);
        Toast.makeText(this, "Name: " + dogEntity.getMessage() + " - Breed: " + dogEntity.getBreed(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCloseIconClick(int position) {
        Toast.makeText(this, "Delete: " + position, Toast.LENGTH_SHORT).show();
        objects.remove(position);
        materialPillsBox.notifyDataSetChanged();
    }
}
