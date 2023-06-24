import javax.swing.*;
import java.awt.*;

public class UNoteApp extends JFrame{
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //define the color of the background
                Color bgcolor = new Color(52, 52, 51);

                NotePanel Note = new NotePanel();
                JFrame MainFrame = new JFrame();

                MainFrame.getContentPane().setBackground(bgcolor);
                Note.setLayout(null);
                MainFrame.getContentPane().add(Note);
                
                //setup the frame of our application
                MainFrame.setTitle("UNote Application");
                MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                MainFrame.setSize(900, 600);
                MainFrame.setResizable(false);
                MainFrame.setVisible(true);
                MainFrame.setLayout(null);;
                MainFrame.setLocationRelativeTo(null); 
                

            }
        });
    }
}