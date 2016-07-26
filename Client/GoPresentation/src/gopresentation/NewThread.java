
package gopresentation;

/**
 *
 * @author Lucas
 */
public class NewThread extends Thread{
    public String ip;
    public String path;
    
    public NewThread(String addressIp, String pathToFile){
        ip = addressIp;
        path = pathToFile;
    }
    public void run(){
        System.out.println(path); //For test
    }
}
