import javax.swing.*;
/**
 * Represents a bullet game object.
 */
public class Bullet extends GameObject{
    /**
     * Constructs a Bullet object with the specified properties.
     *
     * @param x      the x-coordinate of the bullet's position
     * @param y      the y-coordinate of the bullet's position
     * @param width  the width of the bullet's bounding box
     * @param height the height of the bullet's bounding box
     * @param img    the ImageIcon to be displayed for the bullet
     * @param alive  the boolean value indicating whether the bullet is alive
     */
    public Bullet(int x, int y, int width, int height, ImageIcon img, boolean alive) {
        super(x, y, width, height, img, alive);
    }
    /**
     * Updates the bullet's position by subtracting 20 from its y-coordinate.
     */
    public void update(){
        y -= 20;
    }
    /**
     * Sets the bullet's position, ImageIcon, and alive status to their initial values.
     */
    public void dead(){
        x = 0;
        y = 0;
        this.img = null;
        this.alive = false;
    }
}
