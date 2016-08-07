
package servergopresentation;

/**
 *
 * @author Lucas
 */
public class ServerGoPresentation {

    public static void main(String[] args) {
        new ListenerChannelThread().start();
        new ListenerNormalSocketThread().start();
    }
    
}