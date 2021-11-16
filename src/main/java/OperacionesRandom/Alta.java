package OperacionesRandom;

import POJO.Atico;
import POJO.Duplex;
import POJO.Piso;
import Utilidades.ControlData;
import Utilidades.Menu;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author a20marcosgp
 */
public class Alta {

    static Scanner sc = new Scanner(System.in);

    public static int alta(File fDatos, int nRegs) throws IOException {
        Piso piso;
        byte op;
        piso = altaPiso();

        if (piso.getTamañoReal() <= piso.getTamañoMax()) {
            RandomAccessFile rafDatos = new RandomAccessFile(fDatos, "rw");

            rafDatos.seek(nRegs * piso.getTamañoMax());
            rafDatos.writeUTF(piso.getReferencia());
            rafDatos.writeChar(piso.getTipoPiso());
            rafDatos.writeUTF(piso.getNombre());
            rafDatos.writeFloat(piso.getCuotaFija());
            rafDatos.writeFloat(piso.getLitrosAguaCaliente());
            rafDatos.writeFloat(piso.getPasosCalefaccion());
            rafDatos.writeFloat(piso.getTotalReciboComunidad());
            if (piso.getTipoPiso() == 'D') {
                rafDatos.writeFloat(((Duplex) piso).getCuotaExtra());
            }
            if (piso.getTipoPiso() == 'A') {
                rafDatos.writeFloat(((Atico) piso).getTerraza());
            }
            rafDatos.close();
            nRegs++;
        } else {
            System.out.println("Tamaño Excedido");
        }
        return nRegs;
    }

    public static Piso altaPiso() throws IOException {

        Menu menuPiso = new Menu(tipoPiso());
        String referencia, nombre;
        char tipoPiso;
        float cuotaFija, litrosAguaCaliente, pasosCalefaccion, totalReciboComunidad, terraza, cuotaExtra = 0;
        byte opcion;
        Piso piso = null;

        System.out.println("Introduzca la referencia:");
        referencia = ControlData.lerString(sc);
        System.out.println("Introduzca el nombre");
        nombre = ControlData.lerString(sc);
        System.out.println("¿Cuál es su cuota fija?");
        cuotaFija = ControlData.lerFloat(sc);
        System.out.println("¿Cuántos litros de agua caliente ha consumido?");
        litrosAguaCaliente = ControlData.lerFloat(sc);
        System.out.println("¿Cuántos pasos de calefacción ha consumido?");
        pasosCalefaccion = ControlData.lerFloat(sc);

        System.out.println("¿Es un dúplex o un ático?");
        menuPiso.printMenu();
        opcion = ControlData.lerByte(sc);

        if (opcion == 1) {
            tipoPiso = 'D';
            System.out.println("¿Cuál es su cuota extra?");
            cuotaExtra=ControlData.lerFloat(sc);
            piso = new Duplex(referencia, tipoPiso, nombre, cuotaFija, litrosAguaCaliente, pasosCalefaccion, cuotaExtra);
            piso.setTotalReciboComunidad(piso.getTotalReciboComunidad());

        }
        if (opcion == 2) {
            tipoPiso = 'A';
            System.out.println("¿Cuántos metros de terraza tiene?");
            terraza = ControlData.lerFloat(sc);
            piso = new Atico(referencia, tipoPiso, nombre, cuotaFija, litrosAguaCaliente, pasosCalefaccion, cuotaExtra);
            piso.setTotalReciboComunidad(piso.getTotalReciboComunidad());
        }
        return piso;

    }

    static ArrayList<String> tipoPiso() {
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("Dúplex");
        opciones.add("Ático");
        return opciones;
    }

}
