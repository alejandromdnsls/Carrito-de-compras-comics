/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author alejandroms
 */
public class CRUD {
    
    public static void main(String[]args){
        try{
            Catalogo catalogo = new Catalogo();
            //while(true){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));            
            int opc;
            System.out.println("¿Qué deseas hacer? ");
            System.out.println("1. Agregar un nuevo Cómic");
            System.out.println("2. Eliminar un cómic");
            System.out.println("3. Actualizar un cómic");
            System.out.println("4. Ver los cómics registrados en el cátalogo");
            opc = Integer.parseInt(br.readLine());
            String sku;
            String nombre;
            String descripcion;
            String empresa;
            double precio;
            String img;
            Integer existencia;
            switch(opc){
                case 1:
                    System.out.print("\n\nSKU: ");
                    sku = br.readLine();
                    System.out.print("\nNombre: ");
                    nombre = br.readLine();
                    System.out.print("\nDescripción: ");
                    descripcion = br.readLine();
                    System.out.print("\nEmpresa(Marvel, DC Comics, ...): ");
                    empresa = br.readLine();
                    System.out.print("\nPrecio: ");
                    precio = Double.parseDouble(br.readLine());
                    System.out.print("\nPath de imagen: ");
                    img = br.readLine();
                    System.out.print("\nCantidad en existencia: ");
                    existencia = Integer.parseInt(br.readLine());
                    catalogo.addComic(sku, nombre, descripcion, empresa, precio, img,existencia);                    
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.print("\n\n -------------Catálogo Cómics--------------- ");
                    catalogo.showCatalogo();
                    break;
            }  
            br.close();
            //}
        }catch(Exception e){
            e.printStackTrace();            
        }           
              
    }                                                
}
