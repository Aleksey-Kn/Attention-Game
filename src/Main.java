import javax.swing.*;

public class Main extends JFrame {
    Main(){
        super("Attention game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 10, 500, 700);
        setLayout(null);


    }

    public static void main(String[] args) {
        new Main();
    }
}
