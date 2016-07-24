
package gopresentation;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JMenuItem;

/**
 * @author Lucas
 */
public class GoPresentation extends JFrame {
    
    private static GoPresentation instance = null;
    public JPanel panel;
    public JLabel label;

    
    
    private GoPresentation(){
        setSize(700, 450);
        setTitle("Go Presentation");
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
        panel = new JPanel();
        panel.setLayout(null);
        
        label = new JLabel("Select file to upload:");
        
        label.setBounds(10, 10, 150, 25);
        panel.add(label);
        
        Container container = getContentPane();
        container.add(panel);
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
    
}
