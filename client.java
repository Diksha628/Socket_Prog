import java.io.*;
import java.net.*;

class client{
	public static void main(String argv[]) throws Exception
	{
		String sentence;
		String modifiedSentence;
		long t1,t2;

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket("127.0.0.1",6789);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));

		System.out.println("start a chat with the server ." + '\n');
		System.out.println("Type Over to terminate the session");
		
		sentence = inFromUser.readLine();
		t1 = System.currentTimeMillis();
		System.out.println("Pinging "+ clientSocket.getInetAddress()+" with string "+sentence);
        
		outToServer.writeBytes(sentence + '\n');
		if(sentence == "Over"){
			System.out.println("Session terminated from client side .");
			clientSocket.close();
	        return;
		}

		modifiedSentence  = inFromServer.readLine();
		t2 = System.currentTimeMillis();
		System.out.println("Reply from "+ clientSocket.getInetAddress() +" String "+ modifiedSentence+" Length : "+ modifiedSentence.length());

		System.out.println("Sent : "+ sentence.length()+" Received : "+ modifiedSentence.length()+" Lost : "+(sentence.length()- modifiedSentence.length()));
		System.out.println("Approx. Time in Milliseconds  = "+(t2-t1));

            
		if(modifiedSentence == "Over"){
			System.out.println("Session terminated from server side .");
			clientSocket.close();
	        return;
		}  

	    clientSocket.close();
	    return;
	
	}
}