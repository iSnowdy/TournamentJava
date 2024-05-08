package Tournament.Build;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.*;
import java.security.Key;

public class KeyListenerMinigame extends JFrame implements KeyListener {
    private TextField textField;
    private JLabel displayLabel;
    private StringBuilder arrowBuffer; // With this we will save the arrow inputs
    private int keyPressCounter;
    private int maxInputs;
    private String input;
    private int timer;
    protected boolean result;

    private final Cron cron;

    public KeyListenerMinigame(int maxInputs, String input, int timer) {
        this.arrowBuffer = new StringBuilder();
        this.maxInputs = maxInputs;
        this.input = input;
        this.timer = timer;
        this.keyPressCounter = 0;

        // JFrame appearance
        createFrame();
        createTextField();
        createTextLabel(input);
        // Consider somehow positioning the cursor on the TextField on pop up
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent event) { // Method that is invoked when a window is opened
                textField.requestFocusInWindow();
            }
        });
        // Cron Set Up
        this.cron = new Cron(timer, this);
        cron.countDownTimer();

        // "this" is the way to reference the current Window running in this method so Cron can access and close it
    }

    // Methods to create the Visuals
    private void createFrame() {
        setTitle("ARROW MINIGAME");
        setSize(600, 200); // Consider modifying later on
        setLayout(new FlowLayout());
        setBackground(Color.white); // For the Frame
    }
    private void createTextField() {
        Font font = new Font("SansSerif", Font.BOLD, 16);
        // We are using this font because its highly compatible for characters such as the arrows
        this.textField = new TextField(40);
        this.textField.addKeyListener(this);
        this.textField.setBackground(Color.PINK);
        this.textField.setFont(font);
        add(textField);
        // System.out.println(displayLabel.getFont().getFontName());
    }
    private void createTextLabel(String input) {
        Font font = new Font("DejaVu sans", Font.PLAIN, 20);
        // input String example: "→ ↑ ↓ ↓ ↑ → ↑ ↑ ↑ → → → ↓ ← ↓ ↑ ↑ ↓ ↓ ←"
        this.displayLabel = new JLabel(input);
        this.displayLabel.setFont(font);
        add(displayLabel);

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
        setLocationRelativeTo(null); // Window pop up in the middle of the screen
    }

    // Interface Methods from KeyLister
    @Override
    public void keyTyped(KeyEvent event) {} // No need for this method

    @Override
    public void keyReleased(KeyEvent event) {} // No need for this method

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.VK_UP ||
                keyCode == KeyEvent.VK_DOWN ||
                keyCode == KeyEvent.VK_RIGHT ||
                keyCode == KeyEvent.VK_LEFT) {
            System.out.println("You have pressed: " + KeyEvent.getKeyText(keyCode));
        }
        // Only the pressed Key Inputs will be printed
        // Consider printing on the textField only the Arrow inputs
        this.textField.setText(KeyEvent.getKeyText(keyCode));
        // Add "textField.getText() + " for concatenation

        appendArrowInput(event.getKeyCode());
        this.keyPressCounter ++;
        if (this.keyPressCounter >= this.maxInputs) processArrowInputs();
    }

    // Other methods
    private StringBuilder appendArrowInput(int keyCode) { // Consider returning StringBuilder
        if (keyCode == KeyEvent.VK_UP) {
            this.arrowBuffer.append("↑ ");
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.arrowBuffer.append("→ ");
        } else if (keyCode == KeyEvent.VK_DOWN) {
            this.arrowBuffer.append("↓ ");
        } else if (keyCode == KeyEvent.VK_LEFT) {
            this.arrowBuffer.append("← ");
        }
        return arrowBuffer;
    }

    /*private void processArrowInputs () {
        if (this.arrowBuffer.length() > maxInputs) {
            System.out.println("All arrows pressed: " + this.arrowBuffer.toString());
            this.keyPressCounter = 0;
            this.arrowBuffer.setLength(0); // Restart the buffer
            dispose(); // To close the Window
        } else {
            System.out.println("Arrow pressed: " + this.arrowBuffer.toString());
        }
    }*/
    private void processArrowInputs () {
        if (this.arrowBuffer.length() >= maxInputs * 2) { // 2 because there are empty spaces in between each arrow
            int matches = 0;
            for (int i = 0; i < maxInputs * 2; i += 2) {
                char userArrow = arrowBuffer.charAt(i);
                char requiredArrow = input.charAt(i);
                if (userArrow == requiredArrow) {
                    matches++;
                }
            }

            if (matches == maxInputs) {
                System.out.println("Congratulations! You have pressed all inputs correctly");
                System.out.println("Your Fighter will now be able to attack your opponent");
                cron.shutDownTimer();
                this.result = true;
            } else {
                System.out.println("You have not successfully completed the MiniGame");
                cron.shutDownTimer();
                this.result = false;
            }
            // Clean up the buffer
            keyPressCounter = 0;
            arrowBuffer.setLength(0);
            dispose();
        }
    }

    // Getters & Setters
    public StringBuilder getArrowBuffer() {
        return arrowBuffer;
    }
    public String getInput() {
        System.out.println("The user's input to fulfill is: ");
        return input;
    }

    public boolean getResult() {
        return result;
    }
}