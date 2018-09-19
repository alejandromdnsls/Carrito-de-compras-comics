/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcarrito;

import catalogo.Catalogo;
import catalogo.Comic;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author alejandroms
 */
public class ServidorCarrito {
    public static void main(String[]args){
        try{
            ServerSocket s = new ServerSocket(1234);
            System.out.println("Esperando cliente...");
            for(;;){
                Socket cl = s.accept();
                System.out.println("Conexión establecida desde: "
                        + cl.getInetAddress() + ":" + cl.getPort());
                ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
                Catalogo catalogo = new Catalogo();
                ArrayList<Comic> comics = catalogo.getCatalogo();
                oos.writeObject(comics);
                oos.flush();
                oos.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
