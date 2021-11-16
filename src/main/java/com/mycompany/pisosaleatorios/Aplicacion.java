package com.mycompany.pisosaleatorios;

import OperacionesRandom.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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

        OperacionesRandom.Alta.alta(fDatos, nRegs);

    }

}
