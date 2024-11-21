import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
public class CheckinMember {
    /*
     * 
     * 
     * 
     * 
     * team code
     * 
     * return "" if valid member Id
     * return an error string if invalid
     * return "alert" if they valid checked in with an alert
     * 
     */
    public static String checkin(String memberID){
        return memberID;
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
    public static void checkin_init(){
        JPanel panel= new JPanel(); 
            panel.setLayout(null);
        JLabel title=new JLabel("Member Checkin");
        JLabel subTitle =new JLabel("please enter memberID, search member if memberID is unknown");
        JLabel errorTitle = new JLabel();
        JButton enterB=new JButton("Enter");
        JButton backB = new JButton("Back");
        JTextArea inputArea = new JTextArea();
         /*
         * setBounds 
         */ 
        title.setBounds(120, 30, 200, 25);
        subTitle.setBounds(35, 70, 300, 25);
        subTitle.setFont(new Font("Arial", Font.PLAIN, 10));
        inputArea.setBounds(50, 110, 250, 30);
        enterB.setBounds(50, 160, 100, 30);
        backB.setBounds(170, 160, 100, 30);
        errorTitle.setBounds(35, 200, 300, 25); 
        /*
         * add commands
         */
        panel.add(title); panel.add(subTitle); panel.add(errorTitle);
        panel.add(inputArea); panel.add(enterB); panel.add(backB);
        BootLoader.panelContainer.add(panel,"checkin panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "checkin panel");
        BootLoader.loginFrame.setSize(400,400);
        /*
         * action listeners
         */
        backB.addActionListener(
            new ActionListener(){  
                    @Override
                public void actionPerformed(ActionEvent e){
                    inputArea.setText("");
                    errorTitle.setText("");  
                    MainPage.backToMain();
                }     
            }
        );
        enterB.addActionListener(
            new ActionListener(){  
                    @Override
                public void actionPerformed(ActionEvent e){
                    String response = checkin(inputArea.getText());
                    if (response.equals("")) {
                        inputArea.setText("");  
                        MainPage.backToMain();
                    } else if(response.equals("alert")) {
                        alert_init();
                    } else {
                        errorTitle.setText(response);
                    }
                }     
            }
        );
    }
    public static void alert_init(){
        /*
         * JPanel
         */
        JPanel panel = new JPanel();
        panel.setLayout(null); 

        // Add a label with text
        JLabel textLabel = new JLabel("ALERT: this member's membership expires within 30 days.");
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
        BootLoader.panelContainer.add(panel,"alert panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "alert panel");
        BootLoader.loginFrame.setSize(400,400);
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
