/*
 * ReceiveMsgThread.java
 * author Mehrdad Ghadiri
 */

package chatclient;
import java.io.*;
import java.net.*;
import java.util.*;
public class ReceiveMsgThread extends Thread
{
    Socket socket;
    
    public ReceiveMsgThread(Socket socket)
    {
        this.socket = socket;
        start();
    }
    public void run()
    {
        try
        {
        DataInputStream din = new DataInputStream(socket.getInputStream());
        
        while(true)
        {
            String msg = din.readUTF();
            System.out.print(msg+"\n");
        }
        }
        catch(Exception e)
        {
            //do nothing
        }  
    }
}
