import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.io.*;
class editor extends JFrame implements ActionListener {
    // creating the text area
    JTextArea t;
    // creating the frame to acomadate text area and menubar
    JFrame f;

    editor() {
        // Initialising the frame
        f = new JFrame("Text Editor");
        // setting the overall theme of the application

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.metallicLookAndFeel");

            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {

        }

        // Initialising the texy area

        t = new JTextArea();

        // creating the menubar

        JMenuBar m = new JMenuBar();


        // Initialing the file menu
        JMenu f1 = new JMenu("File");

        // Intialising the Menu Items
        JMenuItem m1 = new JMenuItem("New");
        JMenuItem m2 = new JMenuItem("Open");
        JMenuItem m3 = new JMenuItem("Save");
        JMenuItem m4 = new JMenuItem("Print");

        //Adding the ActionListner
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);

        //Adding the new items to the file menu
        f1.add(m1);
        f1.add(m2);
        f1.add(m3);
        f1.add(m4);

        // creating edit menu
        JMenu f2 = new JMenu("Edit");
        JMenuItem m5 = new JMenuItem("Copy");
        JMenuItem m6 = new JMenuItem("Cut");
        JMenuItem m7 = new JMenuItem("Paste");

        m5.addActionListener(this);
        m6.addActionListener(this);
        m7.addActionListener(this);

        f2.add(m5);
        f2.add(m6);
        f2.add(m7);

        JMenuItem c = new JMenuItem("Exit");
        c.addActionListener(this);

        m.add(f1);
        m.add(f2);
        m.add(c);

        f.add(t);
        f.setJMenuBar(m);
        f.setSize(500, 500);
        f.show();


    }

    public void actionPerformed(ActionEvent e) {
       String s = e.getActionCommand();
       if(s.equals("New")){
        t.setText("");
       }
       else if (s.equals("Open")) {
           JFileChooser j = new JFileChooser("C:");
           int r = j.showOpenDialog(null);
           if(r == JFileChooser.APPROVE_OPTION){
               File fi = new File(j.getSelectedFile().getAbsolutePath());
               try{
                   String s1 = "",s2 = "";
                   FileReader fr = new FileReader(fi);
                   BufferedReader br = new BufferedReader(fr);
                   s2 = br.readLine();
                   while ((s1 = br.readLine())!=null){
                       s2 = s2 + "\n" + s1;
                   }
                   t.setText(s2);
               }
               catch (Exception e1){
                   JOptionPane.showMessageDialog(f,e1.getMessage());
               }
           }
           else
               JOptionPane.showMessageDialog(f,"Operation Cancelled");
       }
       else if (s.equals("Save")) {
           JFileChooser j = new JFileChooser("C:");
           int r = j.showOpenDialog(null);
           if(r == JFileChooser.APPROVE_OPTION){
               File fi = new File(j.getSelectedFile().getAbsolutePath());
               try{
               FileWriter fw = new FileWriter(fi);
               BufferedWriter bw = new BufferedWriter(fw);
               bw.write(t.getText());
               bw.flush();
               bw.close();
               }
               catch (Exception e1){
                   JOptionPane.showMessageDialog(f,e1.getMessage());
               }
           }
           else
               JOptionPane.showMessageDialog(f,"Operation Cancelled");
       }
       else if (s.equals("Print")) {
         try{
             t.print();
         }
         catch (Exception e1){
             JOptionPane.showMessageDialog(f,e1.getMessage());
         }
       }
       else if (s.equals("Cut")) {
        t.cut();
       }
       else if (s.equals("Copy")) {
         t.copy();
       }
       else if (s.equals("Paste")) {
        t.paste();
       }
       else if(s.equals("Exit")){
        f.setVisible(false);
       }

    }


    public static void main(String[] args) {
          editor e = new editor();
    }
}