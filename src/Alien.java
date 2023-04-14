import javax.swing.*;

/**
 * Represents an alien game object.
 */
public class Alien extends GameObject {
    /**
     * Constructs an Alien object with the specified properties.
     *
     * @param x      the x-coordinate of the alien's position
     * @param y      the y-coordinate of the alien's position
     * @param width  the width of the alien's bounding box
     * @param height the height of the alien's bounding box
     * @param img    the ImageIcon to be displayed for the alien
     * @param alive  the boolean value indicating whether the alien is alive
     */
    public Alien(int x, int y, int width, int height, ImageIcon img, boolean alive) {
        super(x, y, width, height, img, alive);
    }

    /**
     * Updates the alien's position by adding the specified speed to its y-coordinate.
     *
     * @param speed the speed at which to update the alien's position
     */
    public void update(int speed){
        y += speed;
    }
}
