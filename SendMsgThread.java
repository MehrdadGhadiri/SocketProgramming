/*
 * SendMsgThread.java
 * author Mehrdad Ghadiri
 */

package chatclient;
import java.io.*;
import java.net.*;
import java.util.*;
public class SendMsgThread extends Thread
{
    Socket socket;
    public SendMsgThread(Socket socket)
    {
        this.socket = socket;
        start();
    }
    public void run()
    {
        try
        {
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        while(true)
        {
            Scanner in = new Scanner(System.in);
            String msg = in.nextLine();
            dout.writeUTF(InetAddress.getLocalHost().getHostAddress()+" : "+msg);
        }
        }
        catch(Exception e)
        {
            //do nothing
        }  
    
    }
}
