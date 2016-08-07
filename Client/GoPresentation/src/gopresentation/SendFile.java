
package gopresentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        socketChannel = openChannel(ip);
        sendFile(socketChannel, file);
        
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
    
    public void sendFile(SocketChannel sc, String f){
        RandomAccessFile raf = null;
        try{
            File fileToSend = new File(f);
            raf = new RandomAccessFile(fileToSend, "r");
            FileChannel inputChannel = raf.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            
            //sending filename...
            int i = f.lastIndexOf("\\\\");
            f = f.substring(i+2);
            Charset charset = Charset.forName("ISO-8859-1");
            CharBuffer charBuffer = CharBuffer.wrap(f);
            ByteBuffer byteBuffer = charset.encode(charBuffer);
            byteBuffer.compact();
            byteBuffer.flip();
    
            while (byteBuffer.hasRemaining())
            {
                sc.write(byteBuffer);
            }
            Thread.sleep(1000);
            
            //sending file...
            while(inputChannel.read(buffer) > 0){
                buffer.flip();
                sc.write(buffer);
                buffer.clear();
            }
            Thread.sleep(1000);
            sc.close();
            raf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(SendFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
