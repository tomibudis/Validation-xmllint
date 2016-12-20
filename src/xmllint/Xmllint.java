  package xmllint;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Xmllint {
  public static void main(String[] args) {
    
    JFrame frame = new JFrame("Validation with Xmllint");
    frame.setLayout(new FlowLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JButton button = new JButton("Cari File File");
    
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        JFileChooser fileChooser = new JFileChooser();
        
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                
               
                
                Process proc = Runtime.getRuntime().exec("xmllint --valid "+file.getAbsolutePath());

                // Read the output
                
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(proc.getInputStream()));
                
                String line = "";
                String Succes = "Dokumen valid";
                JOptionPane message = new JOptionPane();
                if((line = reader.readLine()) != null) {
                    String Pesan1 = line;
                    message.showMessageDialog(null,Pesan1,"pesan",JOptionPane.PLAIN_MESSAGE);
                    
                }else{
                    String Pesan1 = Succes;
                    JOptionPane.showMessageDialog(null,Pesan1,"pesan",JOptionPane.PLAIN_MESSAGE);
                    
                }
               
                proc.waitFor();
            } catch (IOException ex) {
                Logger.getLogger(Xmllint.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Xmllint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      }
    });
    frame.add(button);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setSize(500,200);
    frame.setVisible(true);
  }
}