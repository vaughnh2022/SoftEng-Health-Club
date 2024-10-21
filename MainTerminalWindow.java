/* 
 * This is the main class that runs the user interface.
 * 
 * This class passes a string that is the users command for the project to Input Reader Class.
 * 
 * Input Reader Class returns an answer string that this class prints out for the user. 
 * 
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainTerminalWindow {
    static JFrame frame = new JFrame("Terminal"); //jFrame constructor
    static JTextArea terminalArea = new JTextArea(); //text area for terminal
    public static void main(String[] args){
        //frame setup
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // terminalArea setup
        terminalArea.setEditable(false);  
        terminalArea.setBackground(Color.BLACK);
        terminalArea.setForeground(Color.GREEN);
        terminalArea.setFont(new Font("terminalFont", Font.PLAIN, 15));

        // Scroll plane for terminal area to continue scrolling
        JScrollPane scrollPane = new JScrollPane(terminalArea);

        // user input textField setup
        JTextField inputField = new JTextField();
        inputField.setBackground(Color.BLACK);
        inputField.setForeground(Color.GREEN);
        inputField.setFont(new Font("terminalFont", Font.PLAIN, 15));
        
        // layout setup for the frame
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputField, BorderLayout.SOUTH);
        terminalArea.append("$ " + "Welcome to the Terminal! Type 'cl' for a full list of commands." + "\n");

        // actionListener to accept input
        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = inputField.getText();
                inputField.setText("");  // Clear input
                if(command.equals("clear")){ //clear checker
                    terminalArea.setText("");
                    terminalArea.append("$ " + "Welcome to the Terminal! Type 'cl' for a full list of commands." + "\n");
                } else {
                terminalArea.append("$ " + command + "\n"); //puts what command you typed
                String result = InputReaderClass.firstInputReader(command);
                terminalArea.append(result + "\n");
                terminalArea.setCaretPosition(terminalArea.getDocument().getLength()); // Scroll down automatically
                }
            }
        });
        frame.setVisible(true);//makes frame visible
    }
}
