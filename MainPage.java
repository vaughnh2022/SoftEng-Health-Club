import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPage {

    /**
     * Switch back to the main page.
     */
    public static void backToMain() {
        // Set the background color of the panel container to ensure no black areas
        BootLoader.panelContainer.setBackground(Color.WHITE);

        // Show the main card
        BootLoader.cardLayout.show(BootLoader.panelContainer, "main");

        // Revalidate and repaint to update the layout and visuals
        BootLoader.panelContainer.revalidate();
        BootLoader.panelContainer.repaint();

        // Ensure the frame has a consistent size
        BootLoader.loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Initialize the main page GUI.
     */
    public static void main_init(String userID) {
        // Main panel with GridBagLayout for centering
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Add spacing between components
        gbc.fill = GridBagConstraints.BOTH;

        // Set the panel background to ensure no black areas
        panel.setBackground(Color.WHITE);

        /*
         * Buttons
         */
        JButton button1 = new JButton("Add Member");
        JButton button2 = new JButton("Search Member");
        JButton button3 = new JButton("Check-In Member");
        JButton button4 = new JButton("Add User");
        JButton signOutButton = new JButton("Sign Out");

        // Add buttons to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(button1, gbc);

        gbc.gridx = 1;
        panel.add(button2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(button3, gbc);

        if (AddUser.isAdmin()) {
            gbc.gridx = 1;
            panel.add(button4, gbc);
        }

        // Add the Sign Out button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across both columns
        panel.add(signOutButton, gbc);

        /*
         * Add panel to BootLoader and show
         */
        // Clear all components and reset the container before adding the new panel
        BootLoader.panelContainer.removeAll();
        BootLoader.panelContainer.setBackground(Color.WHITE); // Set container background color

        BootLoader.panelContainer.add(panel, "main");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "main");

        // Revalidate and repaint to ensure visuals are updated
        BootLoader.panelContainer.revalidate();
        BootLoader.panelContainer.repaint();

        // Enforce size consistency
        BootLoader.loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        /*
         * Action listeners for buttons
         */
        button1.addActionListener(e -> Staff.addMember_init());
        button2.addActionListener(e -> SearchMember.searchMember_init());
        button3.addActionListener(e -> CheckinMember.checkin_init());
        button4.addActionListener(e -> {
            if (AddUser.isAdmin()) {
                AddUser.addUserInit();
            }
        });

        signOutButton.addActionListener(e -> {
            BootLoader.currentUserRole = ""; // Clear current role
            BootLoader.bootup_init();       // Reinitialize login
        });
    }
}
