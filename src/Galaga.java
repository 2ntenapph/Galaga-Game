import javax.swing.*;

/**
 * Represents the Galaga game.
 */
public class Galaga {
    /**
     * Starts the Galaga game by creating a JFrame and adding a Menu object to its content pane.
     *
     * @param args the command line
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Galaga!");
        Menu menu = new Menu();
        frame.getContentPane().add(menu);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }
}