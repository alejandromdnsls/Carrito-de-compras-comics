/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientecarrito;

import catalogo.Comic;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author alejandroms
 */
public class ClienteCarrito {
    public static void main(String[]args){
        try{
            Socket cl = new Socket("127.0.0.1", 1234);
            ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());
            ArrayList<Comic> catalogo = (ArrayList<Comic>)ois.readObject();
            System.out.println(catalogo.size());
            for(int i = 0; catalogo.size() > i; i++){
                Comic comic = (Comic)catalogo.get(i);                               
                System.out.print("\n\nSKU: " + comic.getSku());                
                System.out.print("\nNombre: " + comic.getNombre());                
                System.out.print("\nDescripción: " + comic.getDescripcion());               
                System.out.print("\nEmpresa: " + comic.getEmpresa());
                System.out.print("\nPrecio: " + comic.getPrecio()); 
            }
            ois.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
