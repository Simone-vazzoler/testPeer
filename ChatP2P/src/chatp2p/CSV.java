/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatp2p;

/**
 *
 * @author Zenmetsu
 */
public class CSV {
    
    public CSV() {}
    
    static String[] fromCsv(String dati)
    {
        String[] riga = dati.split(";");
        return riga;
    }
    
    static String toCSV(String[] campi)
    {
        String riga = "";
        for(int i = 0; i < campi.length - 1; i++)
        {
            riga += campi[i] + ";";
        }
        riga += campi[campi.length - 1];
        return riga;
    }
    
    
}
