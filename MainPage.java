import javax.swing.*;
public class MainPage {
    public static void startSecond(String userID){
        second_init();
    }
    public static void second_init(){
        /*
         * panel 
         */ 
        JPanel panel = new JPanel();
        panel.setLayout(null);
        /*
         * buttons 
         */ 
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");
        /*
         * setBounds 
         */ 
        button1.setBounds(50, 50, 100, 30);  
        button2.setBounds(200, 50, 100, 30); 
        button3.setBounds(50, 100, 100, 30); 
        button4.setBounds(200, 100, 100, 30); 
        /*
         * add 
         */ 
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        /*
         * card shift
         */ 
        BootLoader.panelContainer.add(panel,"main");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "main");
    }
}
