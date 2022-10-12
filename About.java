import javax.swing.*;
import java.awt.*;

public class About extends JFrame {
    About(){
        setBounds(100,100,600,500);
        setTitle("About Notepad");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        ImageIcon image = new ImageIcon("noteicon.png");
        this.setIconImage(image.getImage());//adding iconn
        ImageIcon icon = new ImageIcon("finalise.jpeg");
        JLabel label = new JLabel(icon);
        label.setBounds(100,50,130, 150);
        this.add(label);
        // in j label we cant use /n that why we have used html code  br for new line
        JLabel textLabel = new JLabel("<html>Welcome to NotePad<br> NotePad is a Simple text editor for Microsoft Windows and a basic text-editing program   which enables computer users to create documents<br>All  rights reserved@2022</html>");
        textLabel.setBounds(120,120,400,300);
        textLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,18));
        add(textLabel);


    }

    public static void main(String[] args) {
        new About().setVisible(true);
    }
}
