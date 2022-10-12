import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.*;//for file reader
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.text.html.HTML.Attribute.N;

public class Notepadd extends JFrame implements ActionListener {
    JMenuBar menubar = new JMenuBar();
    //this three are the menu's
    JMenu file = new JMenu("File");
    JMenu edit = new JMenu("Edit");
    JMenu help = new JMenu("Help");

    //initializing item names for file
    JMenuItem newFile = new JMenuItem("New");
    JMenuItem openFile = new JMenuItem("Open");
    JMenuItem saveFile= new JMenuItem("Save");
    JMenuItem print = new JMenuItem("Print");
    JMenuItem exit = new JMenuItem("Exit");

    //initializing item names for edit

    JMenuItem cut = new JMenuItem("Cut");
    JMenuItem  copy= new JMenuItem("Copy");
    JMenuItem paste = new JMenuItem("Paste");
    JMenuItem selectAll = new JMenuItem("SelectAll");

    //initializing item names for help
    JMenuItem about = new JMenuItem("About");

    JTextArea textArea = new JTextArea();


    Notepadd(){
         setTitle("Notepad App");//title
         setBounds(100,100,800,600);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         ImageIcon image = new ImageIcon("noteicon.png");
         this.setIconImage(image.getImage());//adding iconn

      setJMenuBar(menubar);// bcz of these we are adding the menu in top
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        //adding items to file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(print);
        file.add(exit);

        //adding items to edit
        edit.add(cut );
        edit.add( copy);
        edit.add( paste);
        edit.add(selectAll);

        //adding an item to help
        help.add(about);


        //When screen size is limited, we use a scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//it stops when the text is at end of the horizontal
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
         scrollPane.setBorder(BorderFactory.createRaisedBevelBorder());
        textArea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,16));
        //this will help to nextLine for our texts
        textArea.setLineWrap(true);



        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        about.addActionListener(this);




        //here the shortcut  keys
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
         copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
         about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK));



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("New")) {
            textArea.setText(null);
        }
           else if(e.getActionCommand().equalsIgnoreCase("Open")) {

            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("text Files only(.txt)","txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);

            int action = fileChooser.showOpenDialog(null);
            if(action!=JFileChooser.APPROVE_OPTION){
                return;
            }
            else {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                    textArea.read(reader,null);
                }catch(IOException ex){
                    ex.printStackTrace();
                }

            }
    }
        else if(e.getActionCommand().equalsIgnoreCase("Save")) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("text Files only(.txt)","txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);
            int action = fileChooser.showSaveDialog(null);//aprrove or cancel value returned
            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }
            else{
                String fileName = fileChooser.getSelectedFile().getAbsolutePath().toString();
                //in this file name the user .txt is given or not we are checking
                if(fileName.contains(".txt"))
                    fileName += ".txt";
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    textArea.write(writer);
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }

        }
        else if(e.getActionCommand().equalsIgnoreCase("Print")) {
            try {
                textArea.print();
            }catch (PrinterException ex){
                Logger.getLogger(Notepadd.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        else if(e.getActionCommand().equalsIgnoreCase("Exit")) {
                System.exit(0);

        }
        else if(e.getActionCommand().equalsIgnoreCase("Cut")) {
                textArea.cut();//built in functions

        }
        else if(e.getActionCommand().equalsIgnoreCase("Copy")) {
                textArea.copy();

        }
        else if(e.getActionCommand().equalsIgnoreCase("Paste")) {
                textArea.paste();
        }
        else if(e.getActionCommand().equalsIgnoreCase("SelectAll")) {
                textArea.selectAll();
        }
        else if(e.getActionCommand().equalsIgnoreCase("About")) {
            new About().setVisible(true);

        }
        }
    public static void main(String[] args) throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new Notepadd().setVisible(true);
    }
    }



