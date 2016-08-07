
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
    
    public ListenerChannelThread(){
        serverSocketChannel = null;
        socketChannel = null;
    }
    
    public void run(){
        try{
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(64000));
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            while(true){
                socketChannel = serverSocketChannel.accept();
                if(socketChannel.isConnected()){
                    ReceiveFile receiveFile = new ReceiveFile(socketChannel);
                    receiveFile.getAndSaveFile();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }    
}
