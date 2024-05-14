/*
 * ChatClient.java
 * author Mehrdad Ghadiri
 */

package chatclient;
import java.io.*;
import java.net.*;
import java.util.*;
import chatclient.*;
public class ChatClient 
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(System.in);
	System.out.print("Enter server IP address : ");
        String address = in.next();
	System.out.print("Enter server port number : ");
	int port = in.nextInt();
        Socket socket = new Socket(InetAddress.getByName(address),port);
        new SendMsgThread(socket);
        new ReceiveMsgThread(socket);
    }
}
