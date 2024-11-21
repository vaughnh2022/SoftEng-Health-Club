import java.awt.event.*;
import javax.swing.*;
public class AddMember {
    /*
     * 
     * 
     * 
     * 
     * team code
     * 
     * return "" if correct, else return a string describing error
     * 
     */
    public static String addMemberCheck(String street,String city,String state,String zip){
        return "incorrect input";
    }
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
        titleLabel.setBounds(175, 10, 200, 30); // Position and size
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
        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(200, 170, 200, 25);
        panel.add(addressLabel);

        JLabel streetLabel = new JLabel("Street, EX. Sheridan Rd:");
        streetLabel.setBounds(20, 210, 200, 25);
        panel.add(streetLabel);

        JTextField streetField = new JTextField();
        streetField.setBounds(230, 210, 200, 25);
        panel.add(streetField);

        JLabel cityLabel = new JLabel("City, EX. Chicago:");
        cityLabel.setBounds(20, 250, 200, 25);
        panel.add(cityLabel);

        JTextField cityField = new JTextField();
        cityField.setBounds(230, 250, 200, 25);
        panel.add(cityField);

        JLabel stateLabel = new JLabel("State, EX: IL:");
        stateLabel.setBounds(20, 290, 200, 25);
        panel.add(stateLabel);

        JTextField stateField = new JTextField();
        stateField.setBounds(230, 290, 200, 25);
        panel.add(stateField);

        JLabel zipLabel = new JLabel("ZIP Code, EX. 60626:");
        zipLabel.setBounds(20, 330, 200, 25);
        panel.add(zipLabel);

        JTextField zipField = new JTextField();
        zipField.setBounds(230, 330, 200, 25);
        panel.add(zipField);

        JLabel errorLabel = new JLabel("");
        errorLabel.setBounds(200, 360, 200, 25);
        panel.add(errorLabel);
        /*
         * Buttons
         */
        JButton backButton = new JButton("Back");
        backButton.setBounds(250, 400, 100, 30);
        panel.add(backButton);

        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(100, 400, 100, 30);
        panel.add(enterButton);
        /*
         * load panel
         */
        BootLoader.panelContainer.add(panel,"addMember");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "addMember");
        BootLoader.loginFrame.setSize(500,500);BootLoader.loginFrame.setSize(500,500);
        /*
         * action listeners
         */
        backButton.addActionListener(
            new ActionListener(){  
                    @Override
                public void actionPerformed(ActionEvent e){  
                    zipField.setText("");
                    stateField.setText("");
                    streetField.setText("");
                    cityField.setText("");
                    errorLabel.setText("");
                    MainPage.backToMain();
                }     
            }
        );
        enterButton.addActionListener(
            new ActionListener(){  
                    @Override
                public void actionPerformed(ActionEvent e){  
                    String zip = zipField.getText();
                    String state = stateField.getText();
                    String street = streetField.getText();
                    String city = cityField.getText();
                    String checkBol = addMemberCheck(street,city,state,zip);
                    if (checkBol.equals("")) {
                        MainPage.backToMain();
                    } else {
                        errorLabel.setText(checkBol);
                    }
                }     
            }
        );
    }
}
