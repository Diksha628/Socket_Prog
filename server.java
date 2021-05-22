import java.io.*;
import java.net.*;

class server
{
   public static void main(String argv[]) throws Exception
      {
         String clientSentence;

         ServerSocket welcomeSocket = new ServerSocket(6789);

         System.out.println("Server started");
  
         System.out.println("Waiting for a client ...");
         Socket connectionSocket = welcomeSocket.accept();
         System.out.println("Client connected");
         
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            System.out.println("Ping command received from : "+welcomeSocket.getInetAddress() +" with string "+ clientSentence);
            if(clientSentence == "Over"){
               System.out.println("Connection is terminated from client side");
               connectionSocket.close();
               return ;
            }
            String sentence = inFromUser.readLine();
            outToClient.writeBytes(sentence + '\n');
            System.out.println("Server:" + sentence);

            if(sentence == "Over"){
               System.out.println("Connection is terminated from server side");
               connectionSocket.close();
               return;
            }
         
         connectionSocket.close();
      }
}