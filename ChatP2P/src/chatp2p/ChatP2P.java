/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatp2p;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Zenmetsu
 */
public class ChatP2P {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, SocketException {
        ThreadServer server = new ThreadServer();
        server.start();
        
        Client client = new Client();
        
        while(true)
        {
            Scanner myObj = new Scanner(System.in); 
            System.out.println("Inserisci comando");
            String comando = myObj.nextLine();

            switch(comando)
            {
                case "c":
                {
                    client.InviaPacchetto("c;pippo"); //invio pacchetto per la connessione
                    String[] risposta = client.RiceviPacchetto(); //ricevo pacchetto con risposta ed eventuale nome destinatario
                    if(risposta[0].equals("n")) //se il destinatario non accetta
                    {
                        server.setStop(true); //stoppo il server
                    }
                    else
                        System.out.println("Nome destinatario: " + risposta[1]); //visualizzo il nome del destinatario
                }
                case "m":
                {
                    System.out.println("Inserisci messaggio: ");
                    String mess = myObj.nextLine();
                    
                    client.InviaPacchetto("m;" + mess);
                    System.out.println(client.RiceviPacchetto()[1]); //visualizzo il messaggio
                }
                case "d":
                {
                    client.InviaPacchetto("d");
                    server.setStop(true);
                }
            }
        }
    }
    
}
