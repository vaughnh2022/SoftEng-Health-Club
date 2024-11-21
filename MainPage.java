import javax.swing.*;
public class MainPage {
    public static void startSecond(String userID){
        second_init();
    }
    public static void second_init(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        // Create 4 buttons
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");

        button1.setBounds(50, 50, 100, 30);  // Button 1 at (50, 50) with size 100x30
        button2.setBounds(200, 50, 100, 30); // Button 2 at (200, 50) with size 100x30
        button3.setBounds(50, 100, 100, 30); // Button 3 at (50, 100) with size 100x30
        button4.setBounds(200, 100, 100, 30); // Button 4 at (200, 100) with size 100x30

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        BootLoader.panelContainer.add(panel,"main");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "main");
    }
}
