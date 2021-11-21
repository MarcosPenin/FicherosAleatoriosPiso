package OperacionesRandom;

import POJO.Atico;
import POJO.Duplex;
import POJO.Piso;
import Utilidades.ControlData;
import java.io.*;
import java.util.ResourceBundle;
import java.util.Scanner;

// @author 
public class Baja {

    public static int bajas(File fDatos, int nRegs) throws IOException {

        Scanner sc = new Scanner(System.in);

        File temporal = new File("temporal.dat");
        RandomAccessFile rafTemp = new RandomAccessFile(temporal, "rw");
        RandomAccessFile rafDatos = new RandomAccessFile(fDatos, "r");

        Piso piso = null;
        boolean flag=false;
        String referencia, nombre;
        float tipoPiso, cuotaFija, litrosAguaCaliente, pasosCalefaccion, totalReciboComunidad,indeterminado;

        System.out.println("Introduzca referencia a borrar:");
        String referenciaBorrar = ControlData.lerString(sc);

        for (int i = 0; i < nRegs; i++) {
            rafDatos.seek(i * 140);
            referencia = rafDatos.readUTF();
            tipoPiso = rafDatos.readChar();
            nombre = rafDatos.readUTF();
            cuotaFija = rafDatos.readFloat();
            litrosAguaCaliente = rafDatos.readFloat();
            pasosCalefaccion = rafDatos.readFloat();
            totalReciboComunidad = rafDatos.readFloat();
            indeterminado=rafDatos.readFloat();

            if (tipoPiso == 'D') {
                piso = new Duplex(referencia, 'D', nombre, cuotaFija, litrosAguaCaliente, pasosCalefaccion, indeterminado);
            }
            if (tipoPiso == 'A') {
                piso = new Duplex(referencia, 'D', nombre, cuotaFija, litrosAguaCaliente, pasosCalefaccion, indeterminado);
            }

            if (referencia.compareToIgnoreCase(referenciaBorrar) == 0) {
                flag=true;
                rafTemp.seek(i * 140);
                rafTemp.writeUTF(piso.getReferencia());
                rafTemp.writeChar(piso.getTipoPiso());
                rafTemp.writeUTF(piso.getNombre());
                rafTemp.writeFloat(piso.getCuotaFija());
                rafTemp.writeFloat(piso.getLitrosAguaCaliente());
                rafTemp.writeFloat(piso.getPasosCalefaccion());
                rafTemp.writeFloat(piso.getTotalReciboComunidad());
                if (piso.getTipoPiso() == 'D') {
                    rafTemp.writeFloat(((Duplex) piso).getCuotaExtra());
                }
                if (piso.getTipoPiso() == 'A') {
                    rafTemp.writeFloat(((Atico) piso).getTerraza());
                }
            }

        }
        rafDatos.close();
        rafTemp.close();

        fDatos.delete();
        temporal.renameTo(fDatos);

        if (!flag) {
            System.out.println("--- Piso NO ENCONTRADO ---");

        } else {
            System.out.println("---  PISO BORRADO  ---");
            nRegs--;
        }
        return nRegs;

    }

}
