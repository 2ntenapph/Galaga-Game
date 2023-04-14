import javax.swing.*;
/**
 * The Ship class represents a game object that is a ship.
 * It extends the GameObject class and inherits its properties.
 */
public class Ship extends GameObject{
    /**
     * Creates a new Ship object with the specified parameters.
     * @param x the x-coordinate of the ship's position@param y the y-coordinate of the ship's position
     * @param width the width of the ship
     * @param height the height of the ship
     * @param img the ImageIcon representing the ship's image
     * @param alive a boolean value indicating whether the ship is alive or not
     */
    public Ship(int x, int y, int width, int height, ImageIcon img, boolean alive) {
        super(x, y, width, height, img, alive);
    }
}