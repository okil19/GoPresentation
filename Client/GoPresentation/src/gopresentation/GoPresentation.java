
package gopresentation;

import java.awt.Container;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

/**
 * @author Lucas
 */
public class GoPresentation extends JFrame implements ActionListener {
    
    private static GoPresentation instance = null;
    public JPanel panel;
    public JLabel label;
    public JButton uploadButton;
    public FileDialog uploadFile;
    public JTextField filePath;
    
    private GoPresentation(){   
        panel = new JPanel();
        panel.setLayout(null);
        
        label = new JLabel("Select file to upload:");
        uploadButton = new JButton("Upload");
        filePath = new JTextField();
        
        label.setBounds(10, 10, 150, 25);
        uploadButton.setBounds(160, 10, 80, 20);
        filePath.setBounds(10, 40, 350, 20);
        
        uploadButton.addActionListener(this);
        
        panel.add(label);
        panel.add(uploadButton);
        panel.add(filePath);
        filePath.setEditable(false);
        
        Container container = getContentPane();
        container.add(panel);
        
        setSize(700, 450);
        setTitle("Go Presentation");
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    
    public static synchronized GoPresentation getInstance(){
        if(instance == null){
            instance = new GoPresentation();
        }
        return instance;
    }

    public static void main(String[] args) {
        GoPresentation mainWindow = GoPresentation.getInstance();
         
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == uploadButton){
            uploadFile = new FileDialog(instance, "Wczytaj",FileDialog.LOAD);
            uploadFile.setBounds(180, 10, 50, 25);
            uploadFile.setVisible(true);
        }
    }
    
}
