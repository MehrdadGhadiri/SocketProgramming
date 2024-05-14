/*
 * SrvrIncomingMsgThread.java
 * author Mehrdad Ghadiri
 */

package chatserver;
import java.net.*;
import java.io.*;
import java.util.*;
public class SrvrIncomingMsgThread extends Thread
{
    ChatServer chatserver;
    Socket socket;
    public SrvrIncomingMsgThread(ChatServer chatserver,Socket socket)
    {
        this.chatserver = chatserver;
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
                chatserver.sendToAll(msg);
            }
        }
        catch (Exception ex) 
        {
            //IOException 
        }
        finally
        {
            chatserver.closeConnction(socket);
        }
    }
}
