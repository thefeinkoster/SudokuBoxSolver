package solver;

import Initializer.Debugger;

import java.io.*;
import java.net.Socket;

public class TCPConnection extends Thread {

    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private Socket socket;
    //SOCKET kann server oder clint socket sein. -> Es muss nicht eine doppelte verbindung aufgebaut werden


    public TCPConnection (Socket socket){
        this.socket = socket;
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Debugger.__("Connection established using ip: "+socket.getRemoteSocketAddress()+ " and port: "+socket.getPort(),this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected String readLine() throws IOException {
        String line = null;
        line = this.bufferedReader.readLine();
        return line;
    }

    protected boolean sendMessage(String message) throws IOException {
        //System.out.println("sending message "+message);
        this.bufferedWriter.write(message + "\n");
        this.bufferedWriter.flush();
        return true;
    }


    protected void closeConnection() throws IOException {
        bufferedWriter.flush();
        bufferedWriter.close();
        bufferedReader.close();
        socket.close();
    }




}
