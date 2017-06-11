package pe.elcomercio.materialpills.model;

import pe.elcomercio.materialpillsbox.PillEntity;

/**
 * Created by carlos on 6/11/17.
 */

public class DogEntity extends PillEntity {

    private String breed;

    public DogEntity(String name, String breed) {
        super(name);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}

