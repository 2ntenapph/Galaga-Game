import javax.swing.*;
import java.awt.*;
/**
 * The GameObject class represents a game object that can be drawn on the screen and moved around.
 * It extends the Rectangle class and includes methods for setting the object's image, moving the object,
 * checking if it is alive, and drawing it on a Graphics object.
 *
 * @author Anton Filippov || 000843198
 */
public class GameObject extends Rectangle {
    ImageIcon img;
    boolean alive;
    /**
     * Creates a new GameObject instance with the specified position, dimensions, image, and alive status.
     *
     * @param x      the x-coordinate of the object's position
     * @param y      the y-coordinate of the object's position
     * @param width  the width of the object
     * @param height the height of the object
     * @param img    the ImageIcon representing the object's image
     * @param alive  a boolean indicating whether the object is alive or not
     */
    public GameObject(int x, int y, int width, int height, ImageIcon img, boolean alive) {
        super(x, y, width, height);
        this.img = img;
        this.alive = alive;
    }
    /**
     * Sets the image of the game object to the specified ImageIcon.
     *
     * @param img the ImageIcon to set as the object's image
     */
    public void setImg(ImageIcon img) {
        this.img = img;
    }
    /**
     * Sets the game object's x, y, width, height, image, and alive status to 0, null, and false, respectively.
     */
    public void dead() {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        this.img = null;
        this.alive = false;
    }

    /**
     * Draws the game object on the specified Graphics object and Component, using its image and position.
     *
     * @param g the Graphics object to draw the object on
     * @param c the Component to draw the object on
     */
    public void draw(Graphics g, Component c) {
        if (alive) {
            if( img != null )
                g.drawImage(img.getImage(),x,y,width,height,c);
            else
            {
                g.setColor(Color.BLUE);
                g.fillRect(x,y,width,height);
            }
        }
    }
}

