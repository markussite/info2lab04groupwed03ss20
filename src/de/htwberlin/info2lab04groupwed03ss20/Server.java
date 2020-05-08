package de.htwberlin.info2lab04groupwed03ss20;
import java.net.*;
import java.io.*;
public class Server {
        public static void main(String[] args) throws IOException {

            if (args.length != 1) {
                System.err.println("Usage: java EchoServer <port number>");
                System.exit(1);
            }

            int portNumber = Integer.parseInt(args[0]);

            try (
                    ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter outding = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
            ) {
                String inputLine;

                while ((inputLine = in.readLine()) != null) {


                    //echo ding
                    outding.println(inputLine);

                    System.out.println("client: "+inputLine);
                    if(inputLine.equals("quit")){
                        clientSocket.close();
                        serverSocket.close();
                    }
                    String ding = stdIn.readLine();
                    System.out.println("input: " + ding);
                    outding.println(ding);
                }
            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port "
                        + portNumber + " or listening for a connection");
                System.out.println(e.getMessage());
            }
        }
}

