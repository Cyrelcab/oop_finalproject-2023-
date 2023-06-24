import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
//import java.io.FilenameFilter;
import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class NotePanel extends Panel implements ActionListener {
    String titleNote, text;
    int response;
    File filename, directory;
    BufferedWriter writer;
    BufferedReader reader;
    JFileChooser fileChooser;
    JButton addNote, viewNote, backButton, saveButton, deleteButton;
    JTextArea takeNote = new JTextArea();
    JScrollPane scroll;

    Color colorb1 = new Color(208, 112, 80);
    Color colorb2 = new Color(77, 100, 195);
    Color textAreabg = new Color(185, 183, 189);

    NotePanel() {

        // add note button
        addNote = new JButton("Add Note");
        addNote.setFont(new Font("Arial", Font.BOLD, 30));
        addNote.setBackground(colorb1);
        addNote.setForeground(Color.white);
        addNote.setBounds(300, 160, 270, 80);
        addNote.setVisible(true);
        addNote.setBorder(null);
        addNote.setFocusable(false);
        this.add(addNote);

        //view note button
        viewNote = new JButton("View Note");
        viewNote.setFont(new Font("Arial", Font.BOLD, 30));
        viewNote.setBackground(colorb2);
        viewNote.setForeground(Color.white);
        viewNote.setBounds(300, 255, 270, 80);
        viewNote.setVisible(true);
        viewNote.setBorder(null);
        viewNote.setFocusable(false);
        this.add(viewNote);

        // creating the text area
        takeNote.getText();
        takeNote.setBounds(10, 30, 500, 200);
        takeNote.setBackground(textAreabg);
        takeNote.setForeground(Color.white);
        takeNote.setLineWrap(true);
        takeNote.setFont(new Font("Arial", Font.PLAIN, 30));

        // set the scrollbar
        scroll = new JScrollPane(takeNote);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(100, 5, 700, 350);
        scroll.setVisible(false);
        this.add(scroll);

        // create the back button
        backButton = new JButton("Back");
        backButton.setBackground(colorb2);
        backButton.setBounds(360, 470, 150, 50);
        backButton.setBorder(null);
        backButton.setFocusable(false);
        backButton.setForeground(Color.white);
        backButton.setFont(new Font("Arial", Font.BOLD, 30));
        backButton.setVisible(false);
        this.add(backButton);

        //create the save button
        saveButton = new JButton("Save");
        saveButton.setBackground(colorb1);
        saveButton.setBounds(360, 400, 150, 50);
        saveButton.setBorder(null);
        saveButton.setFocusable(false);
        saveButton.setForeground(Color.white);
        saveButton.setFont(new Font("Arial", Font.BOLD, 30));
        saveButton.setVisible(false);
        this.add(saveButton);

        //create the delete button
        deleteButton = new JButton("Delete");
        deleteButton.setBackground(colorb2);
        deleteButton.setBounds(360, 470, 150, 50);
        deleteButton.setBorder(null);
        deleteButton.setFocusable(false);
        deleteButton.setForeground(Color.white);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 30));
        deleteButton.setVisible(false);
        this.add(deleteButton);
        

        addNote.addActionListener(this);
        viewNote.addActionListener(this);
        backButton.addActionListener(this);
        saveButton.addActionListener(this);
        deleteButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addNote) {
            AddNote();
        } 
        else if (e.getSource() == backButton) {
            backNote();
        }
        else if(e.getSource() == saveButton){
            saveNote();
        }
        else if(e.getSource() == viewNote){
            ViewNote();
        }
        else if(e.getSource() == deleteButton){
            deleteNote();
        }
    }


    //method for adding a note
    private void AddNote() {
        titleNote = JOptionPane.showInputDialog(null, "Enter note title:", "UNote Title", 1);

        if (titleNote != null) {
            try {
                directory = new File("C:\\Users\\Acer\\Downloads\\CSC-Project\\Notes");
                filename = new File(directory, titleNote + ".txt");

                if (filename.exists()) {
                    JOptionPane.showMessageDialog(null, "File already exists!", "UNote Title", 1);
                } else {
                    writer = new BufferedWriter(new FileWriter(filename));
                    addNote.setVisible(false);
                    viewNote.setVisible(false);
                    scroll.setVisible(true);
                    backButton.setVisible(true);
                    saveButton.setVisible(true);
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            JOptionPane.getRootFrame().dispose();
        }
    }

    //method for saving the note
    private void saveNote(){
        try {
            writer.write(takeNote.getText());
            writer.close();
            JOptionPane.showMessageDialog(null, "Text saved successfully!", "UNote Application", 1);
            System.out.println("Text was saved...");
            takeNote.setText(null);
            scroll.setVisible(false);
            backButton.setVisible(false);
            saveButton.setVisible(false);
            deleteButton.setVisible(false);
            addNote.setVisible(true);
            viewNote.setVisible(true);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            System.out.println("An error occurred: " + e1.getMessage());
            e1.printStackTrace();
        }
    }

    //method for viewing the note
    private void ViewNote(){
        fileChooser = new JFileChooser("C:\\Users\\Acer\\Downloads\\CSC-Project\\Notes");

        response = fileChooser.showOpenDialog(null);

        try {
            if(response == JFileChooser.APPROVE_OPTION){
                addNote.setVisible(false);
                viewNote.setVisible(false);
                scroll.setVisible(true);
                filename = new File(fileChooser.getSelectedFile().getAbsolutePath());
    
                reader = new BufferedReader(new FileReader(filename));
 
                while((text = reader.readLine()) != null){
                    takeNote.setText(text);
                    takeNote.setEditable(true);
                } 
                reader.close();
                writer = new BufferedWriter(new FileWriter(filename));
                saveButton.setVisible(true);
                deleteButton.setVisible(true);
            }
        } catch (Exception e2) {
            // TODO: handle exception
            System.out.println("An error occurred: " + e2.getMessage());
            e2.printStackTrace();
        }

    }

    //method for back note
    private void backNote(){
        if(!takeNote.getText().isEmpty()){
                try {
                    writer.write(takeNote.getText());
                    writer.close();
                    takeNote.setText(null);
                    System.out.println("Text was saved...");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    System.out.println("An error occurred: " + e1.getMessage());
                    e1.printStackTrace();
                }

            }else{
                System.out.println("Filename: " + filename);
                try {
                    writer.close();
                    filename.delete();
                    System.out.println("File deleted...");
                } catch (IOException e2) {
                    // TODO Auto-generated catch block
                    System.out.println("An error occurred: " + e2.getMessage());
                    e2.printStackTrace();
                }
            }
            scroll.setVisible(false);
            backButton.setVisible(false);
            saveButton.setVisible(false);
            addNote.setVisible(true);
            viewNote.setVisible(true);
    }

    //method for deleting the note
    private void deleteNote(){
        try {
                writer.close();
                filename.delete();
                takeNote.setText(null);
                takeNote.setEditable(true);
                scroll.setVisible(false);
                deleteButton.setVisible(false);
                saveButton.setVisible(false);
                addNote.setVisible(true);
                viewNote.setVisible(true);
                System.out.println(filename + " successfully delete...");
            } catch (Exception e2) {
                // TODO: handle exception
                System.out.println("An error occurred: " + e2.getMessage());
                e2.printStackTrace();
            }
    }
}
