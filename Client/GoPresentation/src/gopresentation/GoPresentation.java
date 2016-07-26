
package gopresentation;

import java.awt.Container;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author Lucas
 */
public class GoPresentation extends JFrame implements ActionListener {
    
    private static GoPresentation instance = null;
    public JPanel panel;
    public JLabel label;
    public JButton selectFileButton;
    public JButton sendButton;
    public JButton clickAllCheckBox;
    public JButton unclickAllCheckBox;
    public FileDialog uploadFile;
    public JTextField filePath;
    public String typeFile;
    public JCheckBox checkTeamA;
    public JCheckBox checkTeamB;
    public JCheckBox checkTeamC;
    public JCheckBox checkTeamD;
    
    private GoPresentation(){   
        panel = new JPanel();
        panel.setLayout(null);
        
        label = new JLabel("Select file to upload:");
        selectFileButton = new JButton("Select file");
        sendButton = new JButton("Send to TV");
        filePath = new JTextField("No file selected");
        clickAllCheckBox = new JButton("Select all");
        unclickAllCheckBox = new JButton("Clear");
        checkTeamA = new JCheckBox("TV for team A");
        checkTeamB = new JCheckBox("TV for team B");
        checkTeamC = new JCheckBox("TV for team C");
        checkTeamD = new JCheckBox("TV for team D");
        
        label.setBounds(10, 10, 150, 25);
        selectFileButton.setBounds(160, 10, 100, 20);
        sendButton.setBounds(535, 10, 150, 20);
        filePath.setBounds(10, 40, 675, 20);
        clickAllCheckBox.setBounds(10, 100, 100, 20);
        unclickAllCheckBox.setBounds(120, 100, 100, 20);
        checkTeamA.setBounds(10, 130, 110, 20);
        checkTeamB.setBounds(10, 155, 110, 20);
        checkTeamC.setBounds(10, 180, 110, 20);
        checkTeamD.setBounds(10, 205, 110, 20);
        
        selectFileButton.addActionListener(this);
        clickAllCheckBox.addActionListener(this);
        unclickAllCheckBox.addActionListener(this);
        sendButton.addActionListener(this);
        checkTeamA.addActionListener(this);
        checkTeamB.addActionListener(this);
        checkTeamC.addActionListener(this);
        checkTeamD.addActionListener(this);
        
        panel.add(label);
        panel.add(selectFileButton);
        panel.add(sendButton);
        panel.add(filePath);
        panel.add(clickAllCheckBox);
        panel.add(unclickAllCheckBox);
        panel.add(checkTeamA);
        panel.add(checkTeamB);
        panel.add(checkTeamC);
        panel.add(checkTeamD);
        
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
        boolean canSend = false;
        
        if(source == sendButton){
            boolean stepOne = true;
            boolean stepTwo = true;
            if(typeFile == null){
                JOptionPane.showMessageDialog(instance, "No file selected.", "Info", JOptionPane.OK_OPTION);   
                stepOne = false;
            }
            if(!(checkTeamA.isSelected() || checkTeamB.isSelected() || checkTeamC.isSelected() || checkTeamD.isSelected())){
                JOptionPane.showMessageDialog(instance, "Select at least one TV.", "Info", JOptionPane.OK_OPTION);
                stepTwo = false;
            }
            if(stepOne && stepTwo){
                String ipTeamA = "IP A";
                String ipTeamB = "IP B";
                String ipTeamC = "IP C";
                String ipTeamD = "IP D";
                JOptionPane.showMessageDialog(instance, "Trying to send...", "Connecting...", JOptionPane.OK_OPTION);
                //new ConnectToServer(ipTeam, filePath); //(String, String)
            }
        }
            
        if(source == selectFileButton){
            uploadFile = new FileDialog(instance, "Open file...",FileDialog.LOAD);
            uploadFile.setBounds(180, 10, 50, 25);
            uploadFile.setVisible(true);
            typeFile = uploadFile.getFile();
            if(typeFile != null)
                filePath.setText(uploadFile.getDirectory() + uploadFile.getFile());
        }
        
        if(source == clickAllCheckBox){
            checkTeamA.setSelected(true);
            checkTeamB.setSelected(true);
            checkTeamC.setSelected(true);
            checkTeamD.setSelected(true);
        }
        
        if(source == unclickAllCheckBox){
            checkTeamA.setSelected(false);
            checkTeamB.setSelected(false);
            checkTeamC.setSelected(false);
            checkTeamD.setSelected(false);
        }
    }
    
}
