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

    public KeyListenerMinigame(int maxInputs, String input) {
        this.arrowBuffer = new StringBuilder();
        this.maxInputs = maxInputs;
        this.input = input;
        this.keyPressCounter = 0;

        // JFrame appearance
        createFrame();
        createTextField();
        createTextLabel(input);
        // Consider somehow positioning the cursor on the TextField on pop up
        Cron cron = new Cron(10);
        cron.countDownTimer(); // It is not closing the Window when the count down is done
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

    private void processArrowInputs () {
        if (this.arrowBuffer.length() > maxInputs) {
            System.out.println("All arrows pressed: " + this.arrowBuffer.toString());
            this.keyPressCounter = 0;
            this.arrowBuffer.setLength(0); // Restart the buffer
            dispose(); // To close the Window
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

    /*public static void main(String[] args) {
        KeyListenerMinigame listenerMinigame = new KeyListenerMinigame(5, "→ ↑ ↓ ↓ ↑");
    }*/
}
