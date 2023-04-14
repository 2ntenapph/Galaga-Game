import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class Menu extends JPanel {
    JButton  best, start, play, back;
    JLabel table, logo;
    ImageIcon bg;
    JTextField tf_name;
    String name;
    String bestScore;

    Menu() {

        bg = new ImageIcon(Objects.requireNonNull(getClass().getResource("Images/space.gif")));

        tf_name = new JTextField();
        tf_name.addActionListener(e -> name = tf_name.getText());


        best = new JButton("Best score");
        best.addActionListener(e -> {
            try {
                FileInputStream file = new FileInputStream("save.dat");
                BufferedInputStream buf = new BufferedInputStream(file);
                ObjectInputStream obj = new ObjectInputStream(buf);

                DataStorage dataStorage = (DataStorage) obj.readObject();

                name = dataStorage.playerName;
                bestScore = dataStorage.bestScore;

                obj.close();

                String text = name + " | " + bestScore;
                table.setText(text);
                table.setVisible(true);
                logo.setVisible(false);
                play.setVisible(false);
                back.setVisible(true);
                best.setVisible(false);

                System.out.println(name + " " + bestScore);

            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        play = new JButton("Play");
        play.addActionListener(e -> {
            tf_name.setVisible(true);
            best.setVisible(false);
            start.setVisible(true);
            play.setVisible(false);
            logo.setVisible(false);
        });

        back = new JButton("Back");
        back.addActionListener(e -> {
            remove(Menu.this);
            tf_name.setVisible(false);
            best.setVisible(true);
            logo.setVisible(true);
            table.setVisible(false);
            start.setVisible(false);
            back.setVisible(false);
            play.setVisible(true);
        });


        start = new JButton("Start");
        start.addActionListener(e -> {
            try {
                FileOutputStream file = new FileOutputStream("save.dat");
                BufferedOutputStream buf = new BufferedOutputStream(file);
                ObjectOutputStream obj = new ObjectOutputStream(buf);

                DataStorage dataStorage = new DataStorage();
                dataStorage.playerName = tf_name.getText();

                System.out.println(dataStorage.playerName + " " + name);
                obj.writeObject(dataStorage);
                obj.close();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(Menu.this);

            // Remove the Menu panel from the parent JFrame
            parentFrame.remove(Menu.this);

            // Add the GalagaLogic panel to the parent JFrame
            GalagaLogic galagaLogic;
            try {
                galagaLogic = new GalagaLogic();
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            parentFrame.add(galagaLogic);

            galagaLogic.setPreferredSize(parentFrame.getSize());

            // Set the parent JFrame's content pane to the GalagaLogic panel
            parentFrame.setContentPane(galagaLogic);

            // Resize and repaint the parent JFrame
            parentFrame.pack();
            parentFrame.setSize(parentFrame.getSize());
            parentFrame.repaint();
        });

        logo = new JLabel("Galaga");
        logo.setFont(new Font("SansSerif", Font.BOLD, 67));
        logo.setForeground(Color.RED);


        table = new JLabel();
        table.setFont(new Font("SansSerif", Font.BOLD, 32));
        table.setForeground(Color.RED);

        add(tf_name);
        add(table);
        add(best);
        add(start);
        add(logo);
        add(play);
        add(back);
        table.setVisible(false);
        tf_name.setVisible(false);
        start.setVisible(false);

        back.setVisible(false);

        best.setVisible(true);
        logo.setVisible(true);
        play.setVisible(true);
        logo.setIcon(null);

        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        FontMetrics fontMetrics;
        int stringWidth;
        int x = (getWidth() - play.getWidth()) / 2;
        int y;

        //clear screen
        g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);



        play.setBounds(x, 405, 200, 50);

        best.setBounds(x, 460, 200, 50);

        start.setBounds(x, 405, 200, 50);

        tf_name.setForeground(Color.WHITE);
        tf_name.setBackground(Color.RED);
        tf_name.setFont(new Font("SansSerif", Font.BOLD, 32));
        tf_name.setHorizontalAlignment(JTextField.CENTER);
        tf_name.setBounds(x, 290, 200, 50);

        back.setBounds(x, 405, 200, 50);


        fontMetrics = table.getFontMetrics(table.getFont());
        stringWidth = fontMetrics.stringWidth(table.getText());
        x = (getWidth() - stringWidth) / 2;
        y = 150;

        table.setBounds(x, y, stringWidth, 250);

        fontMetrics = logo.getFontMetrics(logo.getFont());
        stringWidth = fontMetrics.stringWidth(logo.getText());
        x = (getWidth() - stringWidth) / 2;

        logo.setBounds(x, y, stringWidth, 250);
    }
}
