
package gopresentation;

import javax.swing.JFrame;

/**
 * @author Lucas
 */
public class GoPresentation extends JFrame {
    
    private static GoPresentation instance = null;
    private GoPresentation(){
        setSize(600, 450);
        setTitle("Go Presentation");
    }
    
    public static synchronized GoPresentation getInstance(){
        if(instance == null){
            instance = new GoPresentation();
        }
        return instance;
    }

    public static void main(String[] args) {
        GoPresentation mainWindow = GoPresentation.getInstance();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocation(400, 200);
        mainWindow.setVisible(true);
       
    }
    
}
