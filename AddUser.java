import java.awt.event.*;
import javax.swing.*;

public class AddUser {
    /*
     * 
     * backend code
     * 
     * 
     * return the users new UserID
     * 
     */
    public static String addNewUser(Boolean isStaff,String name){
        return "";
    }
    public static boolean isAdmin(){
        return true;
    }
    /*
     * 
     * 
     * GUI code
     * 
     * 
     */
    public static void addUserInit(){
        /*
         * JPanel
         */
        JPanel panel = new JPanel();
        panel.setLayout(null);
        /*
         * title
         */
        JLabel addUserTitle = new JLabel("Add User");
        addUserTitle.setBounds(100, 10, 100, 25);
        panel.add(addUserTitle);
        /*
         * name and perm labels
         */
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 50, 50, 25); 
        panel.add(nameLabel);
        JLabel permLabel = new JLabel("Perms:");
        permLabel.setBounds(20, 90, 50, 25); 
        panel.add(permLabel);
        /*
         * name text feild
         */
        JTextField nameField = new JTextField();
        nameField.setBounds(80, 50, 150, 25); 
        panel.add(nameField);
        /*
         * buttons
         */
        JButton switchButton = new JButton("staff<-admin");
        switchButton.setBounds(80, 90, 150, 25);
        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(170, 150, 80, 30); 
        JButton backButton = new JButton("back");
        backButton.setBounds(50, 150, 80, 30); 
        panel.add(switchButton);
        panel.add(enterButton);
        panel.add(backButton);
        /*
         * update panel
         */
        BootLoader.panelContainer.add(panel, "add user");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "add user");
        BootLoader.loginFrame.setSize(300, 300);
        /*
         * action listeners
         */
        switchButton.addActionListener(
            new ActionListener(){  
                    @Override
                public void actionPerformed(ActionEvent e){  
                    if (switchButton.getText().equals("staff<-admin")) {
                        switchButton.setText("staff->admin");
                    } else {
                        switchButton.setText("staff<-admin");
                    }
                }     
            }
        );
        enterButton.addActionListener(
            new ActionListener(){  
                    @Override
                public void actionPerformed(ActionEvent e){  
                    addNewUser(switchButton.getText().equals("staff<-admin"),nameField.getText());
                }     
            }
        );
        backButton.addActionListener(
            new ActionListener(){  
                    @Override
                public void actionPerformed(ActionEvent e){  
                    MainPage.backToMain();
                }     
            }
        );
    }
    public static void alert_init(String userID) {
        /*
         * JPanel
         */
        JPanel panel = new JPanel();
        panel.setLayout(null);
        /*
         * userID label
         */
        JLabel textLabel = new JLabel("This user's userID is " + userID);
        textLabel.setBounds(25, 50, 350, 25);
        /*
         * button
         */
        JButton nextButton = new JButton("Next");
        nextButton.setBounds(150, 100, 100, 30);
        /*
         * add
         */
        panel.add(textLabel);
        panel.add(nextButton);
        BootLoader.panelContainer.add(panel, "user aler panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "user alert panel");
        BootLoader.loginFrame.setSize(400, 400);
        /*
         * action listeners
         */
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage.backToMain();
            }
        });

    } 
}
