import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Calculator extends JFrame {
    int buttonX = 0;
    int buttonY = 90;
    int maxLength = 10;
    double curNum = 0;
    double prevNum = 0;
    String wholePart = "";
    String fractionPart = "";
    String displayStr = "";
    Boolean isFraction = false;

    public Calculator() {
        this.setTitle("Basic Calulator");
        this.setSize(400, 610);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(120, 128, 122));
        ImageIcon tempIcon = new ImageIcon("img/appIcon.png");
        Image icon = tempIcon.getImage();
        this.setIconImage(icon);
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
        
        String[] symbols = {"C", "←", "%", "÷", "7", "8", "9", "x", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "="};
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
                        case "C":
                            isFraction = false;
                            wholePart = "";
                            fractionPart = "";
                            curNum = 0;
                            displayStr = "";
                            curDisplay.setText(displayStr);
                            break;

                        case "←":
                            switch (displayStr.charAt(displayStr.length() - 1)) {
                                case '.':
                                    
                                    break;
                            
                                default:
                                    break;
                            }
                            break;

                        case "%":
                            break;

                        case "+/-":
                            if (!displayStr.equals("")) {
                                if (wholePart.equals("0") && !fractionPart.equals(".") && Double.parseDouble(fractionPart) > 0) {
                                    wholePart = "-" + wholePart;
                                }
                                else {
                                    wholePart = String.format("%.0f", 0 - Double.parseDouble(wholePart));
                                }
                                displayStr = wholePart + fractionPart;
                                curNum = Double.parseDouble(displayStr);
                                curDisplay.setText(displayStr);
                                System.out.println(wholePart);
                                System.out.println(fractionPart);
                                System.out.println("num = " + curNum);
                            }
                            break;

                        case ".":
                            if (!isFraction) {
                                isFraction = true;
                                if (displayStr.length() < maxLength) {
                                    if (wholePart.equals("")) {
                                        curNum = 0;
                                        wholePart = "0";
                                    }
                                    fractionPart = ".";
                                    displayStr = wholePart + fractionPart;
                                }
                                curDisplay.setText(displayStr);
                                System.out.println(wholePart);
                                System.out.println(fractionPart);
                                System.out.println("num = " + curNum);
                            }
                            break;

                        case "÷":
                            break;

                        case "x":
                            break;

                        case "-":
                            break;

                        case "+":
                            break;

                        case "=":
                            break;
                    
                        default:
                            if (displayStr.length() < maxLength) {
                                if (isFraction) {
                                    fractionPart += command;
                                    displayStr = wholePart + fractionPart;
                                    curNum = Double.parseDouble(displayStr);
                                }
                                else {
                                    if (!(curNum == 0 && command.equals("0"))) {
                                        wholePart += command;
                                        displayStr = wholePart + fractionPart;
                                        curNum = Double.parseDouble(displayStr);
                                    }
                                    else {
                                        wholePart = "";
                                    }
                                }
                                
                                curDisplay.setText(displayStr);
                                System.out.println(wholePart);
                                System.out.println(fractionPart);
                                System.out.println("num = " + curNum);
                            }
                            break;
                    }

                    if (displayStr.indexOf('-') != -1) {
                        if (displayStr.indexOf('.') != -1) {
                            maxLength = 12;
                        }
                        else {
                            maxLength = 11;
                        }
                    }
                    else if (displayStr.indexOf('.') != -1) {
                        maxLength = 11;
                    }
                    else {
                        maxLength = 10;
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