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

    /**
     * Permito modificar los campos con sentido. Entiendo que no se puede
     * moficar la referencia de un piso ni su tipo.
     *
     * @param fDatos
     * @param nRegs
     * @throws IOException
     */
    public static void modificar(File fDatos, int nRegs) throws IOException {

        Scanner sc = new Scanner(System.in);
        RandomAccessFile rafDatos = new RandomAccessFile(fDatos, "rw");
        Menu miMenu = new Menu(opciones());
        boolean flag = false;

        String referencia, nombre;
        char tipoPiso = 'P';
        float cuotaFija, litrosAguaCaliente, pasosCalefaccion, totalReciboComunidad, cuotaExtra = 0, terraza = 0;
        Piso piso = null;
        System.out.println("Introduzca la referencia del piso a modificar:");
        String referenciaLeida = ControlData.lerString(sc);

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
            }
            if (tipoPiso == 'A') {
                terraza = rafDatos.readFloat();
            }

            if (referencia.compareToIgnoreCase(referenciaLeida) == 0) {
                flag = true;
                System.out.println("¿Qué desea modificar?");
                miMenu.printMenu();
                byte opcion = ControlData.lerByte(sc);

                switch (opcion) {
                    case 1:
                        System.out.println("Introduce el nuevo nombre");
                        nombre = ControlData.lerString(sc);
                        break;
                    case 2:
                        System.out.println("Introduce los litros de agua");
                        litrosAguaCaliente = ControlData.lerFloat(sc);
                        break;
                    case 3:
                        System.out.println("Introduce los pasos de calefacción");
                        pasosCalefaccion = ControlData.lerFloat(sc);
                        break;
                    case 4:
                        System.out.println("Modificar la cuota fija");
                        cuotaFija = ControlData.lerFloat(sc);
                        break;
                    case 5:
                        if (tipoPiso == 'D') {
                            System.out.println("Introduce la nueva cuota extra");
                            cuotaExtra = ControlData.lerFloat(sc);
                        } else {
                            System.out.println("Los áticos no tienen cuota extra;");
                        }
                        break;
                    case 6:
                        if (tipoPiso == 'A') {
                            System.out.println("Introduce los metros de terraza");
                            terraza = ControlData.lerFloat(sc);
                        } else {
                            System.out.println("Los dúplex no tienen terraza");
                        }
                        break;
                }

                /**
                 * Necesito crear un nuevo piso para poder llamar al método que
                 * calcula el total del recibo, que puede haber variado al
                 * modificarse los litros de agua, la cuota extra...
                 */
                if (tipoPiso == 'D') {
                    piso = new Duplex(referencia, tipoPiso, nombre, cuotaFija, litrosAguaCaliente, pasosCalefaccion, cuotaExtra);
                }
                if (tipoPiso == 'A') {
                    piso = new Atico(referencia, tipoPiso, nombre, cuotaFija, litrosAguaCaliente, pasosCalefaccion, terraza);
                }
                totalReciboComunidad = piso.totalRbo();

                if (piso.getTamañoReal() <= 140) {

                    rafDatos.seek(i * 140);
                    rafDatos.writeUTF(referencia);
                    rafDatos.writeChar(tipoPiso);
                    rafDatos.writeUTF(nombre);
                    rafDatos.writeFloat(cuotaFija);
                    rafDatos.writeFloat(litrosAguaCaliente);
                    rafDatos.writeFloat(pasosCalefaccion);
                    rafDatos.writeFloat(totalReciboComunidad);
                    if (piso.getTipoPiso() == 'D') {
                        rafDatos.writeFloat(cuotaExtra);
                    }
                    if (piso.getTipoPiso() == 'A') {
                        rafDatos.writeFloat(terraza);
                    }
                } else {
                    System.out.println("Tamaño máximo excedido. Piso no modificado");
                }
            }
        }
        rafDatos.close();
        if (!flag) {
            System.out.println("-----PISO NO ENCONTRADO-----");
        }
    }

    public static ArrayList<String> opciones() {
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("Modificar el nombre");
        opciones.add("Modificar los litros de agua");
        opciones.add("Modificar los pasos de calefacción");
        opciones.add("Modificar la cuota fija");
        opciones.add("Modificar la cuota extra(para duplex)");
        opciones.add("Modificar los metros de la terraza(para áticos)");
        opciones.add("Salir");
        return opciones;
    }
}
