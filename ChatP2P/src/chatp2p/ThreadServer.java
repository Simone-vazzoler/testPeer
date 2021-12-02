/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatp2p;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zenmetsu
 */
public class ThreadServer extends Thread{
    private DatagramSocket Server;
    private DatagramPacket packet;
    private byte[] Bytedata;
    private boolean stop;
    
    Scanner myObj;

    public ThreadServer() throws SocketException {
        Server = new DatagramSocket(666);
        Bytedata = new byte[1024];
        packet = new DatagramPacket(Bytedata, Bytedata.length);
        stop = false;
        
        myObj = new Scanner(System.in);
    }

    public void run() {
        while (stop == false) {
            
            String[] campi = RiceviPacchetto().split(";");
            
            switch (campi[0]) {
                case "c": {
                    System.out.println("Accettare la connessione da: " + campi[1] + "?");
                    String risposta = myObj.nextLine();
                    
                    if (risposta.equals("si")) {
                        InviaPacchetto("y;pluto");
                    } else {
                        InviaPacchetto("n");
                    }
                }
                case "m": {
                    System.out.println("Inserisci messaggio");
                    String messaggio = myObj.nextLine();
                    InviaPacchetto(messaggio);
                }
                case "d": {
                    setStop(true);
                }
            }
        }
    }

    public String RiceviPacchetto() {
        String messaggioRicevuto = "";
        try {
            Server.receive(packet);
            byte[] dataReceived = packet.getData();
            messaggioRicevuto = new String(dataReceived, 0, packet.getLength());

        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messaggioRicevuto;

    }

    public void InviaPacchetto(String riga) {
        try {
            byte[] responseBuffer = riga.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            responsePacket.setAddress(packet.getAddress());
            responsePacket.setPort(packet.getPort());
            Server.send(responsePacket);

        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
