/**
 * Author : Chris Bell
 * Date   : 03/14/2018
 */

import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println(
                    "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput = stdIn.readLine();
            long startTime = System.nanoTime(); // Start timer
            System.out.println(in.readLine());
            long endTime = System.nanoTime(); // Stop timer
            long duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds
            System.out.println("Time: " + duration + "ms");
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}
