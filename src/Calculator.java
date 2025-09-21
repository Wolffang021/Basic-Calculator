import javax.swing.*;

class Calculator extends JFrame{
    public Calculator() {
        this.setTitle("Basic Calulator");
        this.setSize(400, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new Calculator();
    }
}