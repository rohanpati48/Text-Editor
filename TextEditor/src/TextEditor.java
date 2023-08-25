import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    JFrame frame;

    JMenuBar menuBar;

    JTextArea textarea;

    JMenu file, edit;

    JMenuItem newFile,openfile,savefile;

    JMenuItem cut, copy, paste,selectAll,close;

    TextEditor(){
    frame=new JFrame();

    menuBar=new JMenuBar();

    textarea=new JTextArea();



    //initialize the menu
    file=new JMenu("File");
    edit=new JMenu("Edit");

    //initilaize file menu items
    newFile=new JMenuItem("New File");
    openfile=new JMenuItem("Open File");
    savefile=new JMenuItem("Save File");

    //add actionlistener to menu items
    newFile.addActionListener(this);
    openfile.addActionListener(this);
    savefile.addActionListener(this);

    //add menu items to file
    file.add(newFile);
    file.add(openfile);
    file.add(savefile);


    //initialize edit menu items
    cut=new JMenuItem("Cut");
    copy=new JMenuItem("Copy");
    paste=new JMenuItem("Paste");
    selectAll=new JMenuItem("Select All");
    close=new JMenuItem("Close");


    //add zctionlistener to edit menu
     cut.addActionListener(this);
     copy.addActionListener(this);
     paste.addActionListener(this);
     selectAll.addActionListener(this);
     close.addActionListener(this);

    //add menuitems to edit
    edit.add(cut);
    edit.add(copy);
    edit.add(paste);
    edit.add(selectAll);
    edit.add(close);

    //add menu to menubar
    menuBar.add(file);
    menuBar.add(edit);

    //set menubar to frame
    frame.setJMenuBar(menuBar);

    //create content pane
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5, 5));
        panel.setLayout(new BorderLayout(0,0));
        //initialize text area in panel
        panel.add(textarea,BorderLayout.CENTER);
        //create a scroll pane
        JScrollPane scrollPane=new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);

    //set dimension to frame
    frame.setBounds(0,0,400,400);
    frame.setTitle("Text Editor");
    frame.setVisible(true);
    frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
         if(e.getSource()==cut){
             //perform cut operation
             textarea.cut();
         }
         if(e.getSource()==copy){
             //perform copy operation
             textarea.copy();
         }
         if(e.getSource()==selectAll){
             textarea.selectAll();
         }
         if(e.getSource()==paste){
             textarea.paste();
         }
         if(e.getSource()==close){
             //perform close editor operation
             System.exit(0);
         }
         if(e.getSource()==openfile){
             //open a file choser
             JFileChooser fileChooser=new JFileChooser("C:");
             int choseOption=fileChooser.showOpenDialog(null);
             //if we have clicked open button
             if(choseOption==JFileChooser.APPROVE_OPTION){
                 //getting selected file
                 File file=fileChooser.getSelectedFile();
                 //get the path of selected file
                 String filepath= file.getPath();
                 try{
                     //initialize file reader
                     FileReader fileReader=new FileReader(filepath);
                     //initialize buffer reader
                     BufferedReader bufferedReader=new BufferedReader(fileReader);
                     String Intermediate="", output="";
                     //read contents of file line by line
                     while((Intermediate=bufferedReader.readLine())!=null){
                        output+=Intermediate+"\n";
                     }
                     //set the output string to twxt area
                     textarea.setText(output);
                 }
                 catch (IOException fileNotFoundException){
                     fileNotFoundException.printStackTrace();
                 }

             }
         }
         if(e.getSource()==savefile){
             //initialize a file picker
             JFileChooser fileChooser=new JFileChooser("C:");
             //get choose option in file chooser
             int chooseOption=fileChooser.showSaveDialog(null);
             //check if we clicked on save button
             if(chooseOption==JFileChooser.APPROVE_OPTION){
                 //create a new file with choser directory path and file name

                 File file=new File(fileChooser.getSelectedFile().getAbsoluteFile()+".txt");
                 try{
                     //initialize file writer
                     FileWriter fileWriter=new FileWriter(file);
                     //initialize buffer writer
                     BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                     //write contents of text area to file
                     textarea.write(bufferedWriter);
                     bufferedWriter.close();
                 }
                 catch(IOException ioException){
                     ioException.printStackTrace();
                 }
             }
         }
         if(e.getSource()==newFile)
         {
             TextEditor newTextEditor=new TextEditor();
         }
    }
    public static void main(String[] args) {

        TextEditor texteditor=new TextEditor();
    }
}