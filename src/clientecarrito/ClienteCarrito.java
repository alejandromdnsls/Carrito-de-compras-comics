/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientecarrito;

import catalogo.Comic;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author alejandroms
 */
public class ClienteCarrito {

    public static void main(String[] args) {
        try {
            Socket cl = new Socket("127.0.0.1", 1234);
            ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());
            ArrayList<Comic> catalogo = (ArrayList<Comic>) ois.readObject();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
            Map<String, Integer> compras = new HashMap<>();
            boolean seguirComprando = true;
            boolean confirmarCompra = false;

            while (!confirmarCompra) {
                seguirComprando = true;
                compras = new HashMap<>();
                System.out.println(catalogo.size());
                for (int i = 0; catalogo.size() > i; i++) {
                    Comic comic = (Comic) catalogo.get(i);
                    System.out.print("\n\nSKU: " + comic.getSku());
                    System.out.print("\nNombre: " + comic.getNombre());
                    System.out.print("\nDescripción: " + comic.getDescripcion());
                    System.out.print("\nEmpresa: " + comic.getEmpresa());
                    System.out.print("\nPrecio: " + comic.getPrecio());
                    System.out.println("\nEn existencia: " + comic.getExistencia());
                }

                while (seguirComprando) {
                    System.out.println("\nIngresar SKU de comic a comprar");
                    String SKU = br.readLine();
                    if (hayEnExistencia(SKU, catalogo)) {
                        System.out.println("ingresar cantidad");
                        Integer cantidad = Integer.parseInt(br.readLine());
                        if (haySolicitados(SKU, cantidad, catalogo)) {
                            compras.put(SKU, cantidad);
                        } else {
                            System.out.println("Lo sentimos por el momento no contamos con suficientes ejemplares");
                        }
                    } else {
                        System.out.println("Lo sentimos por el momento no contamos con existencias");
                    }
                    System.out.println("Deseas agregar algo más al carrito (S/N)?");
                    String respuesta = br.readLine();
                    seguirComprando = (respuesta.equals("S") || respuesta.equals("s"));
                }

                System.out.println("\n\nSu pedido es: ");
                mostrarOrden(compras, catalogo);

                System.out.println("Confirmar compra (S/N)");
                String respuesta = br.readLine();
                confirmarCompra = (respuesta.equals("S") || respuesta.equals("s"));

            }

            oos.writeObject(compras);
            oos.flush();
            
            if(ois.readInt() == 1){
                System.out.println("Tu pedido se ha realizado con éxito");
            }else{
                System.out.println("Ha ocurrido un error al realizar tu pedido, intentalo de nuevo");
            }
            
            oos.close();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean hayEnExistencia(String SKU, ArrayList<Comic> catalogo) {
        boolean hasExistencia = false;
        for (Comic comic : catalogo) {
            if (comic.getSku().equals(SKU)) {
                hasExistencia = comic.getExistencia() > 0;
            }
        }
        return hasExistencia;
    }

    private static boolean haySolicitados(String SKU, Integer cantidad, ArrayList<Comic> catalogo) {
        boolean haySolicitados = false;
        for (Comic comic : catalogo) {
            if (comic.getSku().equals(SKU)) {
                haySolicitados = comic.getExistencia() > cantidad;
            }
        }
        return haySolicitados;
    }

    private static void mostrarOrden(Map<String, Integer> compras, ArrayList<Comic> catalogo) {
        System.out.println("*** SKU \t***" + "** NOMBRE *** " + "\t" + "\t*** Cantidad ***" );
        for (Comic comic : catalogo) {
            if (compras.containsKey(comic.getSku())) {
                System.out.println("*" + comic.getSku()+ "\t" + comic.getNombre() + ":\t" + compras.get(comic.getSku()));
            }
        }
    }

}
