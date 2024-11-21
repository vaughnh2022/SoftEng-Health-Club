/*
* imports
*/
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
public class BootLoader {
    static final CardLayout cardLayout = new CardLayout();
    public static JPanel panelContainer = new JPanel(cardLayout);
    static JFrame loginFrame;
    public static void main(String[] args){
        /*
         * Initial JFrame
         */
        loginFrame=new JFrame("Softeng Club Login");
        loginFrame.setSize(400,400);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * JThings
         */
        JPanel loginCard = new JPanel(); 
            loginCard.setLayout(null);
        JLabel softengTitle=new JLabel("Softeng Health Club login");
        JLabel subTitle =new JLabel("please enter UserID");
        JButton enterB=new JButton("Enter");
        JTextArea inputArea = new JTextArea();
         /*
         * setBounds 
         */ 
        softengTitle.setBounds(120, 30, 200, 25);
        subTitle.setBounds(145, 70, 200, 25);
        subTitle.setFont(new Font("Arial", Font.PLAIN, 10));
        inputArea.setBounds(100, 110, 200, 30);
        enterB.setBounds(150, 160, 100, 30);
        /*
         * add commands
         */
        loginCard.add(softengTitle); loginCard.add(subTitle);
        loginCard.add(inputArea); loginCard.add(enterB);
        panelContainer.add(loginCard,"login panel"); 
        loginFrame.add(panelContainer); 
        loginFrame.setVisible(true);


        enterB.addActionListener(
            new ActionListener(){  
                    @Override
                public void actionPerformed(ActionEvent e){  
                    String userID = inputArea.getText();
                    if(userIdCheck(userID)){
                        //end of this page, move to homePage
                        //MainPage.startSecond(userID);
                        System.out.println("swag");
                    }  
                }     
            }
        );
    }
    static boolean userIdCheck(String id){
        return true;
    }
}