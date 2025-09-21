import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Calculator extends JFrame {
    int buttonX = 0;
    int buttonY = 90;
    double displayNum = 0;
    String displayStr = "";
    Boolean isFraction = false;

    public Calculator() {
        this.setTitle("Basic Calulator");
        this.setSize(400, 610);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(120, 128, 122));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        JLabel prevDisplay = new JLabel();
        prevDisplay.setBounds(0, 0, 385, 30);
        Font tempPrevDisplayFont = prevDisplay.getFont();
        Font prevDisplayFont = tempPrevDisplayFont.deriveFont(18f);
        prevDisplay.setFont(prevDisplayFont);
        prevDisplay.setHorizontalAlignment(JLabel.RIGHT);
        this.add(prevDisplay);
        
        JLabel curDisplay = new JLabel();
        curDisplay.setBounds(0, 30, 385, 60);
        Font tempCurDisplayFont = curDisplay.getFont();
        Font curDisplayFont = tempCurDisplayFont.deriveFont(50f);
        curDisplay.setFont(curDisplayFont);
        curDisplay.setHorizontalAlignment(JLabel.RIGHT);
        this.add(curDisplay);
        
        String[] symbols = {"C", "()", "%", "÷", "7", "8", "9", "x", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "="};
        JButton[] button = new JButton[20];
        for (int i = 0; i < 20; i++) {
            button[i] = new JButton(symbols[i]);
            button[i].setBounds(buttonX, buttonY, 96, 96);
            button[i].setBackground(new Color(188, 196, 190));
            button[i].setForeground(Color.BLACK);
            Font tempFont = button[i].getFont();
            Font buttonFont = tempFont.deriveFont(25f);
            button[i].setFont(buttonFont);
            this.add(button[i]);

            button[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();
                    switch (command) {
                        case "1":
                        case "2":
                        case "3":
                        case "4":
                        case "5":
                        case "6":
                        case "7":
                        case "8":
                        case "9":
                        case "0":
                            if (displayStr.length() < 13) {
                                displayNum *= 10;
                                displayNum += Double.parseDouble(command);
                                if (isFraction) {
                                    displayStr = String.format("%.2f", displayNum);
                                }
                                else {
                                    displayStr = String.format("%.0f", displayNum);
                                }
                                curDisplay.setText(displayStr);
                            }
                            break;
                    
                        default:
                            break;
                    }
                }
            });

            if (buttonX < 200) {
                buttonX += 96;
            }
            else {
                buttonX = 0;
                buttonY += 96;
            }
        }
        
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new Calculator();
    }
}