/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OperacionesRandom;

import POJO.Piso;
import Utilidades.ControlData;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author a20marcosgp
 */
public class Buscar {

    public static void buscar(File fDatos, int nRegs) throws IOException {

        Scanner sc = new Scanner(System.in);
        RandomAccessFile rafDatos = new RandomAccessFile(fDatos, "r");
        Piso piso;
        boolean flag = false;

        System.out.println("Introduzca el nombre a buscar:");
        String nombreNuevo = ControlData.lerString(sc);

        for (int i = 0; i < nRegs; i++) {
            rafDatos.seek(i * 140);
            String referencia = rafDatos.readUTF();
            char tipoPiso = rafDatos.readChar();
            String nombre = rafDatos.readUTF();
            Float cuotaFija = rafDatos.readFloat();
            Float litrosAguaCaliente = rafDatos.readFloat();
            Float pasosCalefaccion = rafDatos.readFloat();
            Float totalReciboComunidad = rafDatos.readFloat();
            Float indeterminado = rafDatos.readFloat();

            if (nombreNuevo.compareToIgnoreCase(nombre) == 0) {
                 flag = true;
                System.out.println("Referencia: " + referencia + "\nTipo piso: " + tipoPiso + "\nNombre: " + nombre + "\nCuotaFija: " + cuotaFija
                        + "\nLitros agua caliente: " + litrosAguaCaliente + "\nPasos calefacción: " + pasosCalefaccion + "\nTotal recibo: " + totalReciboComunidad);

                if (tipoPiso == 'D') {
                    System.out.println("Cuota extra: " + indeterminado);
                }
                if (tipoPiso == 'A') {
                    System.out.println("Metros terraza: " + indeterminado);
                }               
                System.out.println("-----------------------------------");
               
            }
        }
        if (!flag) {
            System.out.println("--- PROPIETARIO NO ENCONTRADO ---");
        }
    }
}
