package POJO;

import Utilidades.ControlData;
import java.io.*;
import java.util.ResourceBundle;
import java.util.Scanner;

// @author 
public class Bajas {

    public static int bajas(File fDatos, int nRegs) throws IOException {

        Scanner sc = new Scanner(System.in);

        File temporal = new File("temporal.dat");
        RandomAccessFile rafTemp = new RandomAccessFile(temporal, "rw");
        RandomAccessFile rafDatos = new RandomAccessFile(fDatos, "r");

        Piso piso = null;
        String referencia, nombre;
        float tipoPiso, cuotaFija, litrosAguaCaliente, pasosCalefaccion, totalReciboComunidad, cuotaExtra, terraza;
        byte b = 0;

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

            if (tipoPiso == 'D') {
                cuotaExtra = rafDatos.readFloat();
                piso = new Duplex(referencia, 'D', nombre, cuotaFija, litrosAguaCaliente, pasosCalefaccion, cuotaExtra);

            }
            if (tipoPiso == 'A') {
                terraza = rafDatos.readFloat();
                piso = new Duplex(referencia, 'D', nombre, cuotaFija, litrosAguaCaliente, pasosCalefaccion, terraza);
            }

            if (piso.getReferencia().compareToIgnoreCase(referenciaBorrar) != 0) {
                rafTemp.seek(b * 140);
                rafTemp.writeUTF(piso.getReferencia());
                rafTemp.writeChar(piso.getTipoPiso());
                rafTemp.writeUTF(nombre);
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
                b++;
            }

        }
        rafDatos.close();
        rafTemp.close();

        fDatos.delete();
        temporal.renameTo(fDatos);

        if (b == nRegs) {
            System.out.println("--- Piso NO ENCONTRADA ---");

        } else {
            System.out.println("---  PISO BORRADO  ---");
            nRegs--;
        }

        return nRegs;

    }

}
