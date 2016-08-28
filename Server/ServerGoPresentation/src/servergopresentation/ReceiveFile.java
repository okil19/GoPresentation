
package servergopresentation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 *
 * @author Lucas
 */
public class ReceiveFile {
    public SocketChannel socketChannel;
    public final String directory; 
    public String name;
    
    public ReceiveFile(SocketChannel sc){
        this.directory = "E:\\Test\\";
        this.socketChannel = sc;
        name = null;
    }
    
    public void getAndSaveFile(){
        RandomAccessFile raf = null;
        try {
            Charset charset = Charset.forName("ISO-8859-1");
            ByteBuffer byteBuffer = ByteBuffer.allocate(128);
            socketChannel.read(byteBuffer);
            byteBuffer.flip();
            CharBuffer c = charset.decode(byteBuffer);
            String fileName = c.toString();
            name = fileName;
            
            raf = new RandomAccessFile(new String(directory + fileName), "rw");
            ByteBuffer bufferData = ByteBuffer.allocate(1024);
            FileChannel fileChannel = raf.getChannel();
            
            while (socketChannel.read(bufferData) > 0) {
                bufferData.flip();
                fileChannel.write(bufferData);
                bufferData.clear();
            }
            
            Thread.sleep(1000);
            raf.close();
            fileChannel.close();
            socketChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    } 
}