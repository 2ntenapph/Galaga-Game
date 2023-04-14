import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * The GalagaLogic class is a JPanel that implements the MouseMotionListener and MouseListener interfaces.
 * It is responsible for controlling the game logic and displaying the game graphics.
 *
 * @author Anton Filippov | 000843198
 */
public class GalagaLogic extends JPanel implements MouseMotionListener, MouseListener {
    ImageIcon shipimg, bg, alien1Img, alien2Img, alien3Img, bulletImg;
    JTextField label;
    JLabel finalMes;
    Ship ship;
    Bullet bullet;
    Alien alien;
    Alien[] aliens;
    String name, scoreStr;
    JButton btn_save;
    int score = 0, deadAliens = 0, level = 0;
    /**
     * The save() method saves the game state to a file and switches the game screen back to the menu screen.
     */
    public void save() {
        FontMetrics fontMetrics = finalMes.getFontMetrics(finalMes.getFont());
        int x = (getWidth() - btn_save.getWidth()) / 2;
        btn_save.setBounds(x, 350, 200, 50);
        btn_save.setVisible(true);
        x = (getWidth() - fontMetrics.stringWidth(finalMes.getText())) / 2;
        finalMes.setBounds(x, 150, fontMetrics.stringWidth(finalMes.getText()), 50);
        finalMes.setVisible(true);

        btn_save.addActionListener(e -> {
            try {
                FileOutputStream file = new FileOutputStream("save.dat");
                BufferedOutputStream buf = new BufferedOutputStream(file);
                ObjectOutputStream obj = new ObjectOutputStream(buf);

                DataStorage dataStorage = new DataStorage();

                if(dataStorage.score < score){
                    dataStorage.score = score;
                    dataStorage.bestScore = scoreStr;
                    dataStorage.playerName = name;
                    obj.writeObject(dataStorage);
                }
                obj.close();

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(GalagaLogic.this);

                // Remove the Menu panel from the parent JFrame
                remove(GalagaLogic.this);

                // Add the GalagaLogic panel to the parent JFrame
                Menu menu = new Menu();
                add(menu);

                menu.setPreferredSize(parentFrame.getSize());

                // Set the parent JFrame's content pane to the GalagaLogic panel
                parentFrame.setContentPane(menu);

                // Resize and repaint the parent JFrame
                parentFrame.pack();
                parentFrame.setSize(parentFrame.getSize());
                parentFrame.repaint();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }
    /**
     * The GalagaLogic constructor initializes the game graphics and loads the game state from a file.
     * It also starts a new UpdateThread and adds mouse listeners to the game panel.
     */
    public GalagaLogic() throws IOException, ClassNotFoundException {

        FileInputStream file = new FileInputStream("save.dat");
        BufferedInputStream buf = new BufferedInputStream(file);
        ObjectInputStream obj = new ObjectInputStream(buf);

        DataStorage dataStorage = (DataStorage) obj.readObject();
        name = dataStorage.playerName;

        shipimg = new ImageIcon("Images/shipimg.png");

        bg = new ImageIcon("Images/space.gif");

        alien1Img = new ImageIcon("Images/alien1.png");
        alien2Img = new ImageIcon("Images/alien2.png");
        alien3Img = new ImageIcon("Images/alien3.png");
        bulletImg = new ImageIcon("Images/bullet.png");

        bullet = new Bullet(0, 0, 10, 15, bulletImg,  false);

        btn_save = new JButton("Back");
        add(btn_save);
        btn_save.setVisible(false);

        ship = new Ship(260, 480, 30, 30, shipimg, true);

        label = new JTextField();
        add(label);
        label.setVisible(false);

        finalMes = new JLabel("Game Over!");
        add(finalMes);
        finalMes.setFont(new Font("SansSerif", Font.BOLD, 67));
        finalMes.setForeground(Color.RED);
        finalMes.setVisible(false);

        setLevel();

        scoreStr = String.format("%03d", score);

        UpdateThread ut = new UpdateThread(this);
        ut.start();

        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
    }
    /**
     * Generating 1 level
     */
    private void level1(){
        aliens = new Alien[40];
        int x = 150;
        int y = -30;
        for (int i = 0; i < aliens.length; i++) {

            alien = new Alien(
                    x,
                    y,
                    30,
                    30,
                    alien1Img,
                    true)
            ;
            x += 35;
            if (x > 395){
                y -= 40;
                x = 150;
            }
            aliens[i] = alien;
        }
    }
    /**
     * Generating 2 level
     */
    private void level2(){
        aliens = new Alien[55];
        int x = 250;
        int y = -30;
        int clin = 1;
        int frtInLine = x;
        for (int i = 0; i < aliens.length; i++) {

            alien = new Alien(
                    x,
                    y,
                    33,
                    33,
                    alien2Img,
                    true);

            if (x + 35 >= frtInLine + 35 * clin){
                y -= 40;
                frtInLine -= 15;
                x = frtInLine;
                clin++;
            } else {
                x += 35;
            }
            aliens[i] = alien;

        }
    }
    /**
     * Generating 3 level
     */
    private void level3(){
        aliens = new Alien[60];
        int x = 70;
        int y = -30;
        for (int i = 0; i < aliens.length; i++) {

            alien = new Alien(
                    x,
                    y,
                    33,
                    33,
                    alien3Img,
                    true);

            x += 35;
            if (x > 200 && x < 305) {
                x = 370;
            }
            if (x > 500){
                y -= 40;
                x = 70;
            }
            aliens[i] = alien;
        }
    }
    /**
     * Set level randomly
     */
    public void setLevel(){
        switch ((int) ((Math.random() * (3 - 1)) + 1)) {
            case 2 -> level2();
            case 3 -> level3();
            default -> level1();
        }
    }

    /**
     * Paint evey element
     *
     * @param g Graphics element
     */
    public void paintComponent(Graphics g) {
        //clear screen
        g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
        //display aliens
        if (deadAliens == aliens.length){
            setLevel();
            deadAliens = 0;
            level++;
        }
        for (Alien alien : aliens) {
            if (alien.alive)
                alien.draw(g, this);
        }
        //display ship
        ship.draw(g, this);
        bullet.setImg(bulletImg);
        bullet.draw(g, this);

        g.setFont(new Font("sansserif", Font.BOLD, 24));
        g.setColor(Color.RED);
        g.drawString("Level: " + level + " | Name: " + name + " | Score: " + scoreStr, 30, getHeight() - 30);

        if(!ship.alive || alien.y > 600) {
            ship.dead();
            bullet.dead();
            save();
        }
    }

    /**
     * Update positions
     */
    public void updateScene() {
        if(deadAliens < aliens.length)
            for (Alien alien : aliens)
                alien.update(1 + level);
        for (Alien alien : aliens){
            if(alien.intersects(ship)){
                ship.dead();
                System.out.println("Aliens killed you");
            }
            if (alien.intersects(bullet)){
                alien.dead();
                bullet.dead();
                deadAliens++;
                score += 5;
                scoreStr = String.format("%03d", score);
            }
        }
        bullet.update();
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ship.x = e.getX() - 10; // adjust the position of the object to be centered on the cursor
        ship.y = e.getY() - 10;
    }
    public void mouseClicked(MouseEvent e) {
        // Respond to mouse click event
        if (e.getButton() == MouseEvent.BUTTON1) {
            // check if left mouse button was clicked
            System.out.println("Left mouse button clicked!");

            bullet.alive = true;
            bullet.x = ship.x + 10;
            bullet.y = ship.y - 30;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            // check if right mouse button was clicked
            System.out.println("Right mouse button clicked!");
        }
    }

    public void mousePressed(MouseEvent e) {
        // Do nothing
    }

    public void mouseReleased(MouseEvent e) {
        // Do nothing
    }

    public void mouseEntered(MouseEvent e) {
        // Do nothing
    }

    public void mouseExited(MouseEvent e) {
        // Do nothing
    }
}