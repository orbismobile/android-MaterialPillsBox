package pe.orbis.materialpillsbox;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 5/20/17.
 *
 */

public class PillEntity {

    private int id;
    private String message = "default";
    private boolean pressed = false;
    private int imgResource;

    protected PillEntity() {
    }

    protected PillEntity(String message){
        this.message = message;
    }

    public PillEntity(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public PillEntity(String message, boolean pressed) {
        this.id = id;
        this.message = message;
        this.pressed = pressed;
    }

    public PillEntity(String message, int imgResource, boolean pressed) {
        this.id = id;
        this.message = message;
        this.pressed = pressed;
        this.imgResource = imgResource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }
}
