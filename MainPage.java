import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPage {
    /**
     * Switch back to the main page.
     */
    public static void backToMain() {
        BootLoader.cardLayout.show(BootLoader.panelContainer, "main");
        BootLoader.loginFrame.setSize(400, 200);
    }

    /**
     * Initialize the main page GUI.
     */
    public static void main_init(String userID) {
        /*
         * Main panel
         */
        JPanel panel = new JPanel(new GridBagLayout()); // Use GridBagLayout for automatic centering
        GridBagConstraints gbc = new GridBagConstraints(); // For layout adjustments
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components

        /*
         * Buttons
         */
        JButton button1 = new JButton("Add Member");
        JButton button2 = new JButton("Search Member");
        JButton button3 = new JButton("Check-In Member");

        // Set fill and anchor for proper alignment
        gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch buttons horizontally
        gbc.anchor = GridBagConstraints.CENTER; // Center all components

        // Add the "Add Member" button
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        panel.add(button1, gbc);

        // Add the "Search Member" button
        gbc.gridx = 0; // Column 0
        gbc.gridy = 1; // Row 1
        panel.add(button2, gbc);

        // Add the "Check-In Member" button
        gbc.gridx = 0; // Column 0
        gbc.gridy = 2; // Row 2
        panel.add(button3, gbc);

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
    }
}
