package OperacionesRandom;

import POJO.Atico;
import POJO.Duplex;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author a20marcosgp
 */
public class Visualizar {

    public static void visualizar(File fDatos, int nRegs) throws IOException {

        RandomAccessFile rafDatos = new RandomAccessFile(fDatos, "r");

        System.out.println("LISTA DE EMPRESAS:");

        for (int i = 0; i < nRegs; i++) {
            rafDatos.seek(i * 140);
            String referencia=rafDatos.readUTF();
            char tipoPiso=rafDatos.readChar();
       
            System.out.println("Referencia: "+referencia+"\nTipo piso: "+tipoPiso+"\nNombre: "+rafDatos.readUTF()+"\nCuotaFija: "+rafDatos.readUTF()+
                    "\nLitros agua caliente: "+rafDatos.readFloat()+"\nPasos calefacción: "+rafDatos.readFloat()+"\nTotal recibo: "+rafDatos.readFloat());
          
            if (tipoPiso == 'D') {
                System.out.println("Cuota extra: )"+rafDatos.readFloat());
            }
            if (tipoPiso == 'A') {
                System.out.println("Metros terraza: "+rafDatos.readFloat());
            }
           
        }

        rafDatos.close();

    }
}
