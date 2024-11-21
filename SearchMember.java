import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class SearchMember {
    /*
     * 
     * 
     * 
     * 
     * team code
     * 
     * given a string with either a memberID or a name
     * 
     * 
     * The system will return name, last name, email and account expiration date as a string to print
     * 
     * return "" if error
     * 
     */
    public static String findMember(String text){
        return "";
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
    public static void searchMember_init(){
        JPanel panel= new JPanel(); 
            panel.setLayout(null);
        JLabel title=new JLabel("Member search");
        JLabel subTitle =new JLabel("please enter either memberID or name of the member");
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
        BootLoader.panelContainer.add(panel,"search panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "search panel");
        BootLoader.loginFrame.setSize(400,400);
        /*
         * action listeners
         */
        enterB.addActionListener(
            new ActionListener(){  
                    @Override
                public void actionPerformed(ActionEvent e){  
                    String output = findMember(inputArea.getText());
                    if(output.equals("")){
                        errorTitle.setText("incorrect input");
                    } else {
                        name_init(output);
                    }
                }     
            }
        );
        backB.addActionListener(
            new ActionListener(){  
                    @Override
                public void actionPerformed(ActionEvent e){  
                    MainPage.backToMain();
                }     
            }
        );
    }
    public static void name_init(String person){
        /*
         * JPanel
         */
        JPanel panel = new JPanel();
        panel.setLayout(null); 

        // Add a label with text
        JLabel textLabel = new JLabel(person);
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
        BootLoader.panelContainer.add(panel,"name panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "name panel");
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
