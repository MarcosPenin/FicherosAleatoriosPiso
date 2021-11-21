package OperacionesRandom;

import POJO.Atico;
import POJO.Duplex;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;

/**
 *
 * @author a20marcosgp
 */
public class Visualizar {

    public static void visualizar(File fDatos, int nRegs) throws IOException {

        RandomAccessFile rafDatos = new RandomAccessFile(fDatos, "r");

        System.out.println("LISTA DE PISOS:");

        String referencia, nombre;
        char tipoPiso;
        float cuotaFija, litrosAguaCaliente, pasosCalefaccion, totalCuotaRecibo, indeterminado;

        for (int i = 0; i < nRegs; i++) {
            rafDatos.seek(i * 140);
            referencia = rafDatos.readUTF();
            tipoPiso = rafDatos.readChar();
            nombre = rafDatos.readUTF();
            cuotaFija = rafDatos.readFloat();
            litrosAguaCaliente = rafDatos.readFloat();
            pasosCalefaccion = rafDatos.readFloat();
            totalCuotaRecibo=rafDatos.readFloat();
            indeterminado = rafDatos.readFloat();
            System.out.println("Referencia: " + referencia + "\nTipo piso: " + tipoPiso + "\nNombre: " + nombre + "\nCuotaFija: " + cuotaFija
                    + "\nLitros agua caliente: " + litrosAguaCaliente + "\nPasos calefacción: " + pasosCalefaccion + "\nTotal recibo: " + totalCuotaRecibo);

            if (tipoPiso == 'D') {
                System.out.println("Cuota extra: " + indeterminado);
            }
            if (tipoPiso == 'A') {
                System.out.println("Metros terraza: " + indeterminado);
            }
            System.out.println("--------------------------------");

        }

        rafDatos.close();
    }
    
     public static void visualizarRecibos(File fDatos, int nRegs) throws IOException { 
        LocalDate fecha = LocalDate.now();
        int dia=fecha.getDayOfMonth();     
         if(dia==25){
             Visualizar.visualizar(fDatos, nRegs);           
         }else{
             System.out.println("Los recibos solo pueden consultarse en el día 25 de cada mes");
         }
  
}}
