/*
 * ChatServer.java
 * author Mehrdad Ghadiri
 */

package chatserver;
import java.io.*;
import java.net.*;
import java.util.*;
public class ChatServer
{
    private ServerSocket ss;
    private Hashtable outputStreams = new Hashtable();
    public ChatServer(int port) throws IOException
    {
        listen(port);
    }
    private void listen(int port) throws IOException
    {
        ss = new ServerSocket(port);
        System.out.println("We are listen to : "+ss);
        while(true)
        {
        Socket s = ss.accept();
        System.out.println("Connection from : "+s);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        outputStreams.put(s, dout);
        new SrvrIncomingMsgThread(this,s);
        }
    }
    Enumeration getOutputStreams()
    {
       return outputStreams.elements();
    }
    public void sendToAll(String msg)
    {
        synchronized(outputStreams)
        {
            for(Enumeration e = getOutputStreams();e.hasMoreElements();)
            {
                DataOutputStream dout = (DataOutputStream) e.nextElement();
                try
                {
                    dout.writeUTF(msg);
                }
                catch(Exception ee)
                {
                    //do nothing
                }
            }
        }
    }
    void closeConnction(Socket s)
    {
        synchronized(outputStreams)
        {
            System.out.print("Close connection to :"+s+"\n");
            outputStreams.remove(s);
            try
            {
                s.close();
            }
            catch(Exception ee)
            {
                //do nothing
            }
                
        }
    }
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
	System.out.print("Enter port number : ");
        int port = in.nextInt();
        try
        {
        new ChatServer(port);
        }
        catch(IOException e)
        {
            System.out.println("There is a fatal server Error!");
        }
    }
    
}
