/*
* imports
*/
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
public class BootLoader extends Staff {
    /*
     * 
     * return true if correct userID
     * 
     */
    static boolean userIdCheck(String id){
        return
                isMemberPresent(id);
    }
    /*
     * 
     * 
     * 
     * GUI code (final)
     * 
     * 
     */
    static final CardLayout cardLayout = new CardLayout();
    public static JPanel panelContainer = new JPanel(cardLayout);
    static JFrame   loginFrame;
    public static void main(String[] args){
        /*
         * Initial JFrame
         */
        loginFrame=new JFrame("Softeng Club Login");
        loginFrame.setSize(400,400);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bootup_init();
    }
    public static void bootup_init(){
        /*
         * JThings
         */
        JPanel loginCard = new JPanel(); 
            loginCard.setLayout(null);
        JLabel softengTitle=new JLabel("Softeng Health Club login");
        JLabel subTitle =new JLabel("please enter UserID");
        JButton enterB=new JButton("Enter");
        JTextArea inputArea = new JTextArea();
        JLabel errorTitle= new JLabel();
         /*
         * setBounds 
         */ 
        softengTitle.setBounds(120, 30, 200, 25);
        subTitle.setBounds(145, 70, 200, 25);
        subTitle.setFont(new Font("Arial", Font.PLAIN, 10));
        inputArea.setBounds(100, 110, 200, 30);
        enterB.setBounds(150, 160, 100, 30);
        errorTitle.setBounds(35, 200, 300, 25);
        /*
         * add commands
         */
        loginCard.add(softengTitle); loginCard.add(subTitle);
        loginCard.add(inputArea); loginCard.add(enterB); loginCard.add(errorTitle);
        panelContainer.add(loginCard,"login panel");
        loginFrame.add(panelContainer); 
        loginFrame.setVisible(true); 
        /*
         * action listeners
         */
        enterB.addActionListener(
            new ActionListener(){  
                    @Override
                public void actionPerformed(ActionEvent e){  
                    String userID = inputArea.getText();
                    if(userIdCheck(userID)){
                        MainPage.main_init(userID);
                    } else {
                        errorTitle.setText("Not a valid userID");
                    }
                }     
            }
        );
    }
}