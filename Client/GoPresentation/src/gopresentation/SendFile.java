
package gopresentation;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

/**
 *
 * @author lokla
 */
public class SendFile {
    public SocketChannel socketChannel;
    public static final int PORT = 64000;
    public String file;
    
    public SendFile(String ip, String f){
        file = f.replaceAll("\\\\", "\\\\\\\\"); 
        //socketChannel = openChannel(ip);
        //sendFile(socketChannel, file);
        
        System.out.println(file + " - " + ip); // test
    }

    public SocketChannel openChannel(String ip) {
        SocketAddress socketAddress;
        socketChannel = null;
        ip = "localhost";
        try{
            socketChannel = SocketChannel.open();
            socketAddress = new InetSocketAddress(ip, PORT);
            socketChannel.connect(socketAddress);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("No connection");
        }
        
        return socketChannel;
    }
    
    public void sendFile(SocketChannel sc, String f) {
        // to be continue...
    }
}
