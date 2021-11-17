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
public class Modificar {

    public static void modificar(File fDatos, int nRegs) throws IOException {

        Scanner sc = new Scanner(System.in);
        RandomAccessFile rafDatos = new RandomAccessFile(fDatos, "rw");
        Menu miMenu = new Menu(opciones());

        Piso piso = null;
        String referencia, nombre;
        char tipoPiso;
        float cuotaFija, litrosAguaCaliente, pasosCalefaccion, totalReciboComunidad, cuotaExtra, terraza;
        System.out.println("Introduzca la referencia del piso a modificar:");
        String referenciaLeida = ControlData.lerString(sc);
        System.out.println("¿Qué desea modificar?");
        miMenu.printMenu();
        byte opcion = ControlData.lerByte(sc);

        for (int i = 0; i < nRegs; i++) {
            rafDatos.seek(i * 140);
            referencia = rafDatos.readUTF();
            tipoPiso = rafDatos.readChar();
            nombre = rafDatos.readUTF();
            cuotaFija = rafDatos.readFloat();
            litrosAguaCaliente = rafDatos.readFloat();
            pasosCalefaccion = rafDatos.readFloat();
            if (tipoPiso == 'D') {
                cuotaExtra = rafDatos.readFloat();
                piso = new Duplex(referencia, tipoPiso, nombre, cuotaFija, litrosAguaCaliente, pasosCalefaccion, cuotaExtra);
            }
            if (tipoPiso == 'A') {
                terraza = rafDatos.readFloat();
                piso = new Atico(referencia, tipoPiso, nombre, cuotaFija, litrosAguaCaliente, pasosCalefaccion, terraza);
            }

            if (piso.getReferencia().compareToIgnoreCase(referenciaLeida) == 0) {
                switch (opcion) {
                    case 1:
                        System.out.println("Introduce el nuevo nombre");
                        nombre = ControlData.lerString(sc);
                        piso.setNombre(nombre);
                        break;
                    case 2:
                        System.out.println("Introduce los litros de agua");
                        litrosAguaCaliente = ControlData.lerFloat(sc);
                        piso.setLitrosAguaCaliente(litrosAguaCaliente);
                        break;
                    case 3:
                        System.out.println("Introduce los pasos de calefacción");
                        pasosCalefaccion = ControlData.lerFloat(sc);
                        piso.setPasosCalefaccion(pasosCalefaccion);
                        break;
                    case 4:
                        System.out.println("Modificar la cuota fija");
                        cuotaFija = ControlData.lerFloat(sc);
                        piso.setCuotaFija(cuotaFija);
                        break;
                    case 5:
                        if (piso instanceof Duplex) {
                            System.out.println("Introduce la nueva cuota extra");
                            cuotaExtra = ControlData.lerFloat(sc);
                            ((Duplex) piso).setCuotaExtra(cuotaExtra);

                        } else {
                            System.out.println("Los áticos no tienen cuota extra;");
                        }
                        break;
                    case 6:
                        if (piso instanceof Atico) {
                            System.out.println("Introduce los metros de terraza");
                            terraza = ControlData.lerFloat(sc);
                            ((Atico) piso).setTerraza(terraza);
                        } else {
                            System.out.println("Los dúplex no tienen terraza");
                        }
                }
                //Calculo de nuevo el recibo de la comunidad
                piso.setTotalReciboComunidad(piso.getTotalReciboComunidad());
            }
        }
    }

    public static ArrayList<String> opciones() {
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("Modificar el nombre");
        opciones.add("Modificar los litros de agua");
        opciones.add("Modificar los pasos de calefacción");
        opciones.add("Modificar la cuota fija");
        opciones.add("Modificar la cuota extra(para duplex)");
        opciones.add("Ver los metros de la terraza");
        opciones.add("Salir");
        return opciones;
    }
}
