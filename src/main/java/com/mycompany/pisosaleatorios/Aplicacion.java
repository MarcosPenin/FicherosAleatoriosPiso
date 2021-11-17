package com.mycompany.pisosaleatorios;

import OperacionesRandom.*;
import Utilidades.ControlData;
import Utilidades.Menu;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author a20marcosgp
 */
public class Aplicacion {

    public static void main(String[] args) throws IOException {

        File fDatos = new File("DatosEmpresa.dat");
        Scanner sc = new Scanner(System.in);

        int nRegs = Leer.leer(fDatos);
        Menu miMenu = new Menu(opciones());

        byte opcion;

        System.out.println("*********************************************************************************************");
        System.out.println("**************************COMUNIDAD VALPARAISO********************************************");
        do {

            System.out.println(nRegs);
            System.out.println("*********************************************************************************************");
            System.out.println("Escoja la operación que desea realizar");
            miMenu.printMenu();
            opcion = ControlData.lerByte(sc);

            switch (opcion) {

                case 1:
                    nRegs=Alta.alta(fDatos, nRegs);
                    break;
                case 2:
                    
                    //No borra cuando solo hay un registro
                    nRegs=Baja.bajas(fDatos, nRegs);
                    break;
                case 3:
                    Modificar.modificar(fDatos, nRegs);
                    break;
                case 4:
                    Visualizar.visualizar(fDatos, nRegs);
                    break;
                case 5:
                    Visualizar.visualizar(fDatos, nRegs);
                    break;
                case 6:
                    Buscar.buscar(fDatos, nRegs);
                    break;
            }

        } while (opcion != 7);
    }

    static ArrayList<String> opciones() {
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("Dar de alta un piso");
        opciones.add("Dar de baja un piso");
        opciones.add("Modificar los datos de un piso");
        opciones.add("Listar todos los pisos");
        opciones.add("Listar todos los recibos");
        opciones.add("Ver los pisos de un propietario");
        opciones.add("Salir");
        return opciones;
    }
}
