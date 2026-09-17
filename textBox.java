//For the purposes of reading my textbooks quicker
//Other plans: Immovable red word, likely 2nd or 3rd. Additionally TTS, Repeat option, slow down for larger words.
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import java.util.*;

public class textBox extends JFrame {

    static JFrame mainFrame;
    static JPanel mainPanel;
    static JTextField inputField;
    static JButton enterButton;

    static JLabel textDisplay;
    static JButton cancelButton;
    static JButton increaseSpeed;
    static JButton decreaseSpeed;
    static JLabel speedDisplay;


    public static double changeSpeed(double currentSpeed, Boolean increase, Boolean decrease) {
        currentSpeed = Double.parseDouble(speedDisplay.getText());
        if (increase) {
            currentSpeed += 0.5;
        } else if (decrease) {
            currentSpeed -= 0.5;
        }
        increase = false;
        decrease = false;
        speedDisplay.setText(String.valueOf(currentSpeed));
        return currentSpeed;
    }

    public static void textSubmitted() {
        String input = inputField.getText();
        java.util.List<String> wordList = Arrays.asList(input.split(" "));
        
        inputField.setVisible(false);
        enterButton.setVisible(false);
        System.out.println(wordList);
        inputField.setText("");

        textDisplay.setVisible(true);
        cancelButton.setVisible(true);
        decreaseSpeed.setVisible(true);
        speedDisplay.setVisible(true);
        increaseSpeed.setVisible(true); 
        System.out.println("submitted");

        final int[] wordsInput = {0};
        
        //3000, 1.0 - 20WPM. 5.0 - 100WPM. 10.0 - 200WPM. 20.0 - 400WPM. 25.0 - 500WPM. 50.0 - 1000WPM
        javax.swing.Timer timer = new javax.swing.Timer(3000 / (int) (Double.parseDouble(speedDisplay.getText())), e -> {
            if (wordsInput[0] < wordList.size()) {
                String word = wordList.get(wordsInput[0]);
                textDisplay.setText(word);
                System.out.println(word);
                wordsInput[0]++;
            } else {
                ((javax.swing.Timer) e.getSource()).stop();
            }
        });
        timer.start();        
    }

    public static void textCancelled() {
        inputField.setVisible(true);
        enterButton.setVisible(true);

        textDisplay.setVisible(false);
        cancelButton.setVisible(false);
        decreaseSpeed.setVisible(false);
        speedDisplay.setVisible(false);
        increaseSpeed.setVisible(false); 
    }


    public static void main(String[] args) {
        mainFrame = new JFrame("Reading");

        //Text Display for the actual words you'll read
        textDisplay = new JLabel("Text", SwingConstants.CENTER);
        textDisplay.setVisible(false);
        textDisplay.setBounds(25, 25, 350, 225);
        textDisplay.setFont(new Font(null, ABORT, 55));

        //Button to return to the input field
        cancelButton = new JButton("Cancel");
        cancelButton.setVisible(false);
        cancelButton.setBounds(100, 250, 100, 30);
        cancelButton.addActionListener(e -> {
                textCancelled();
        });

        //Decrease speed button
        decreaseSpeed = new JButton("-");
        decreaseSpeed.setVisible(false);
        decreaseSpeed.setBounds(250, 250, 40, 30);
        decreaseSpeed.addActionListener(e -> {
                changeSpeed(1.0, false, true);  
                System.out.println("decreased speed");
        });

        //Display for the current speed. I have been reading at speed 20.
        speedDisplay = new JLabel("20.0");
        speedDisplay.setVisible(false);
        speedDisplay.setBounds(300, 250, 30, 30);
        speedDisplay.setBackground(java.awt.Color.lightGray);

        //Increase speed button
        increaseSpeed = new JButton("+");
        increaseSpeed.setVisible(false);
        increaseSpeed.setBounds(330, 250, 45, 30);
        increaseSpeed.addActionListener(e -> {
                changeSpeed(1.0, true, false);
                System.out.println("increased speed");
        });

        //Input Field
        inputField = new JTextField(20);
        inputField.setBounds(25, 25, 350, 225);

        //Submit Button
        enterButton = new JButton("Submit"); 
        enterButton.setBounds(150, 250, 100, 30);
        enterButton.addActionListener(e -> {
                textSubmitted(); 
        });

        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 425, 325);
        mainPanel.setBackground(java.awt.Color.GRAY);

        mainPanel.add(inputField);
        mainPanel.add(enterButton);
        mainPanel.add(cancelButton);
        mainPanel.add(decreaseSpeed);
        mainPanel.add(speedDisplay);
        mainPanel.add(increaseSpeed);
        mainPanel.add(textDisplay);
        mainFrame.add(mainPanel);
        
        mainFrame.setSize(425,325);
        mainPanel.setLayout(null);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

    }
}

