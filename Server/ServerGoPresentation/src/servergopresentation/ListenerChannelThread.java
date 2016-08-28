
package servergopresentation;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 *
 * @author Lucas
 */
public class ListenerChannelThread extends Thread {
    public ServerSocketChannel serverSocketChannel;
    public SocketChannel socketChannel;
    public final int PORT = 64000;
    
    public ListenerChannelThread(){
        serverSocketChannel = null;
        socketChannel = null;
    }
    
    public void run(){
        try{
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            while(true){
                socketChannel = serverSocketChannel.accept();
                if(socketChannel.isConnected()){
                    ReceiveFile receiveFile = new ReceiveFile(socketChannel);
                    receiveFile.getAndSaveFile();
                    System.out.println("New file has been received: " +receiveFile.name);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }    
}
