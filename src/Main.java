import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main extends JFrame {
    int reit;
    int[] fine = new int[2];
    MainPanel panel;

    Main(){
        super("Attention game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 10, 800, 700);
        setLayout(null);
        setFocusable(true);

        try {
            reit = new Scanner(new File("reit.txt")).nextInt();
        }catch (FileNotFoundException e){
            reit = 1000;
        }

        JLabel label = new JLabel("Ваш рейтинг: " + reit);
        label.setFont(new Font("Calibri", Font.PLAIN, 36));
        label.setBounds(250, 150, 400, 300);
        add(label);
        JButton start = new JButton("Начать");
        start.setBounds(300, 450, 200, 150);
        start.addActionListener(l -> {
            getContentPane().removeAll();
            repaint();
            panel = new MainPanel(this, reit, fine);
            add(panel);
            panel.start();
        });
        add(start);

        setVisible(true);
    }

    void contin(){
        dispose();
        System.out.println("Штраф за время:" + fine[0]);
        System.out.println("Штраф за неправильные ответы: " + fine[1]);
        System.exit(0);
    }

    public static void main(String[] args) {
        new Main();
    }
}
