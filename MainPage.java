import java.awt.event.*;
import javax.swing.*;
public class MainPage {
    /*
     * 
     * 
     * 
     * 
     * GUI code (final) Nothing to code in this class
     * 
     * 
     * 
     */
    public static void backToMain(){
        BootLoader.cardLayout.show(BootLoader.panelContainer, "main");
        BootLoader.loginFrame.setSize(400,200);
    }
    public static void main_init(String userID){
        /*
         * panel 
         */ 
        JPanel panel = new JPanel();
        panel.setLayout(null);
        /*
         * buttons 
         */ 
        JButton button1 = new JButton("add member");
        JButton button2 = new JButton("search member");
        JButton button3 = new JButton("checkin member");
        /*
         * setBounds 
         */ 
        button1.setBounds(125, 50, 150, 30);  
        button2.setBounds(50, 100, 150, 30); 
        button3.setBounds(200, 100, 150, 30); 
        /*
         * add 
         */ 
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        /*
         * card shift
         */ 
        BootLoader.panelContainer.add(panel,"main");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "main"); 
        /*
         * add 
         */ 
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        /*
         * card shift
         */ 
        BootLoader.panelContainer.add(panel,"main");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "main");
        BootLoader.loginFrame.setSize(400,200);
        /*
         * action listeners
         */
        button1.addActionListener(
            new ActionListener(){  
                    @Override
                public void actionPerformed(ActionEvent e){  
                    AddMember.addMember_init();
                }     
            }
        );
        button2.addActionListener(
            new ActionListener(){  
                    @Override
                public void actionPerformed(ActionEvent e){  
                    SearchMember.searchMember_init();
                }     
            }
        );
        button3.addActionListener(
            new ActionListener(){  
                    @Override
                public void actionPerformed(ActionEvent e){  
                    CheckinMember.checkin_init();
                }     
            }
        );
    }
}
