import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPage {
    /**
     * Switch back to the main page.
     */
    public static void backToMain() {
        BootLoader.cardLayout.show(BootLoader.panelContainer, "main");
        BootLoader.loginFrame.setSize(800, 800);
    }

    /**
     * Initialize the main page GUI.
     */
    public static void main_init(String userID) {
        /*
         * Main panel
         */
        JPanel panel = new JPanel(new GridBagLayout()); // Use GridBagLayout for automatic centering
        panel.setLayout(new GridLayout(2, 2, 10, 10));

        /*
         * Buttons
         */
        JButton button1 = new JButton("Add Member");
        JButton button2 = new JButton("Search Member");
        JButton button3 = new JButton("Check-In Member");
        JButton button4 = new JButton("Add User");

        
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        if (AddUser.isAdmin()) {
            panel.add(button4);
        }        
        /*
         * Add panel to BootLoader and show
         */
        BootLoader.panelContainer.add(panel, "main");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "main");
        BootLoader.loginFrame.setSize(400, 300);

        /*
         * Action listeners
         */
        button1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Staff.addMember_init();
                    }
                }
        );

        button2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SearchMember.searchMember_init();
                    }
                }
        );

        button3.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CheckinMember.checkin_init();
                    }
                }
        );
        button4.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (AddUser.isAdmin()) {
                            AddUser.addUserInit();
                        }
                    }
                }
        );
    }
}
