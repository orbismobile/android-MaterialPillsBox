package pe.orbis.materialpills.model;

import pe.orbis.materialpillsbox.PillEntity;

/**
 * Created by Carlos Vargas on 6/11/17.
 */

public class DogEntity extends PillEntity {

    private String breed;

    public DogEntity() {
    }

    public DogEntity(String name, String breed) {
        super(name);
        this.breed = breed;
    }

    public DogEntity(String name, String breed, boolean pressed) {
        super(name, pressed);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}

