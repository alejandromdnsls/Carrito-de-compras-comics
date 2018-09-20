/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogo;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author alejandroms
 */
public class Catalogo {
    String archivo;
    
    public Catalogo(){
        this.archivo = "Catalogo.bin";
    }
    
    public void addComic(String sku, String nombre, String descripcion, String empresa, double precio, String img, Integer existencia){
        try{
            File file = new File(this.archivo);
            ObjectOutputStream oos = null;
            if(file.exists())
                oos = new ObjectOutputStream(new FileOutputStream(this.archivo, true));
            else
                oos = new ObjectOutputStream(new FileOutputStream(this.archivo));              
            Comic comic = new Comic(sku, nombre, descripcion, empresa, precio, img, existencia);
            oos.writeObject(comic);           
            oos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void showCatalogo() throws IOException, ClassNotFoundException{                
        //ObjectInputStream ois = null;
        FileInputStream fis = new FileInputStream(this.archivo);
        try{
            //ois = new ObjectInputStream(new FileInputStream(this.archivo));            
            while(true){
                ObjectInputStream ois = new ObjectInputStream(fis);
                Comic comic = (Comic)ois.readObject();
                System.out.print("\n\nSKU: " + comic.getSku());                
                System.out.print("\nNombre: " + comic.getNombre());                
                System.out.print("\nDescripción: " + comic.getDescripcion());               
                System.out.print("\nEmpresa: " + comic.getEmpresa());
                System.out.print("\nPrecio: " + comic.getPrecio());                
            }            
        //}catch(ExcetFileNotFound e)
        }catch(EOFException e){
            System.out.println("\n\nCómics mostrados con éxito\n");            
        }finally{
            fis.close();
        }
    }
    public ArrayList<Comic> getCatalogo() throws IOException, ClassNotFoundException{
        ArrayList<Comic> catalogo = new ArrayList<>();
        FileInputStream fis = new FileInputStream(this.archivo);
        try{
            //ois = new ObjectInputStream(new FileInputStream(this.archivo));            
            while(true){
                ObjectInputStream ois = new ObjectInputStream(fis);
                Comic comic = (Comic)ois.readObject();
                catalogo.add(comic);                            
            }                   
        }catch(EOFException e){           
            return catalogo;
        }finally{
            fis.close();
        }        
        
    }    
    public void deleteComic(){
        
    }
    
    public void updateComic(){
        
    }
}
