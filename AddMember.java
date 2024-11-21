import javax.swing.*;
public class AddMember {
    /*
     * 
     * 
     * 
     * 
     * GUI code (final)
     * 
     * 
     * 
     */
    public static void addMember_init(){
        /*
         * Main panel
         */
        JPanel panel = new JPanel();
        panel.setLayout(null); // Using absolute positioning with setBounds()

        /*
         * Title
         */
        JLabel titleLabel = new JLabel("Add Member");
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        titleLabel.setBounds(150, 10, 200, 30); // Position and size
        panel.add(titleLabel);

        /*
         * Name
         */
        JLabel nameLabel = new JLabel("Name, EX. John Smith:");
        nameLabel.setBounds(20, 50, 200, 25);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(230, 50, 200, 25);
        panel.add(nameField);

        /*
         * Email
         */
        JLabel emailLabel = new JLabel("Email, EX. thing@website.com:");
        emailLabel.setBounds(20, 90, 200, 25);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(230, 90, 200, 25);
        panel.add(emailField);

        /*
         * Phone Number
         */
        JLabel phoneLabel = new JLabel("Phone Number, EX. (xxx)-xxx-xxxx:");
        phoneLabel.setBounds(20, 130, 250, 25);
        panel.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(230, 130, 200, 25);
        panel.add(phoneField);

        /*
         * Address
         */
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(20, 170, 200, 25);
        panel.add(addressLabel);

        JLabel streetLabel = new JLabel("Street:");
        streetLabel.setBounds(20, 210, 200, 25);
        panel.add(streetLabel);

        JTextField streetField = new JTextField();
        streetField.setBounds(230, 210, 200, 25);
        panel.add(streetField);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setBounds(20, 250, 200, 25);
        panel.add(cityLabel);

        JTextField cityField = new JTextField();
        cityField.setBounds(230, 250, 200, 25);
        panel.add(cityField);

        JLabel stateLabel = new JLabel("State:");
        stateLabel.setBounds(20, 290, 200, 25);
        panel.add(stateLabel);

        JTextField stateField = new JTextField();
        stateField.setBounds(230, 290, 200, 25);
        panel.add(stateField);

        JLabel zipLabel = new JLabel("ZIP Code:");
        zipLabel.setBounds(20, 330, 200, 25);
        panel.add(zipLabel);

        JTextField zipField = new JTextField();
        zipField.setBounds(230, 330, 200, 25);
        panel.add(zipField);

        /*
         * Buttons
         */
        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 340, 100, 30);
        panel.add(backButton);

        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(250, 340, 100, 30);
        panel.add(enterButton);
        /*
         * load panel
         */
        BootLoader.panelContainer.add(panel,"addMember");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "addMember");
        BootLoader.loginFrame.setSize(500,500);BootLoader.loginFrame.setSize(500,500);
    }
}
