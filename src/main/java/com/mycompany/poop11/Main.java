/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poop11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

/**
 * Clase main
 * @author cecitowers
 */
public class Main {
    /**
     * Método main desde donde se realizan las actividades
     * @param args string de arrays
     */
    public static void main(String[] args) {
        /**
         * Actividada File
         * Crea un archivo de texto, en caso de que no exista
         */
        System.out.println("..... File .....");
        File archivo = new File("archivo.txt");
        System.out.println(archivo.exists());
        if(!archivo.exists())
            try {
                boolean seCreo = archivo.createNewFile();
                System.out.println(seCreo);
                System.out.println(archivo.exists());
            } catch (IOException ex) {
                ex.getMessage();
                //Da más información, todas las clases donde hubo problema
                ex.getStackTrace();
        }
        
        
        /**
         * Escribe el texto en el nuevo archivo indicado
         * Por medio de bytes
         */
        System.out.println("\n..... FileOutputStream ......");
        FileOutputStream fos = null;
        byte[] buffer = new byte[81];
        int nBytes;
        
        try {
            System.out.println("Escribir el texto a guardar en archivo: ");
            nBytes = System.in.read(buffer);
            fos = new FileOutputStream("fos.txt");
            fos.write(buffer,0,nBytes);
        } catch (IOException ex) {
                System.out.println(ex.getMessage());
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException ex) {
                    System.out.println(ex.getMessage());
            }
        }
        
        
        /**
         * Imprime el texto del archivo indicado
         * Por medio de bytes
         */
        System.out.println("\n..... FileInputStream .....");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("fos.txt");
            try {
                nBytes = fis.read(buffer, 0, 81);
                String texto = new String(buffer, 0, nBytes);
                System.out.println(texto);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } try {
                if (fis != null)
                    fis.close();
            } catch (IOException ex) {
                    System.out.println(ex.getMessage());
            }
        
        
        /**
         * Escribe el texto en el nuevo archivo indicado
         * Por medio de un buffered reader
         */
        System.out.println("\n..... FileWriter ....");
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escriba texto para archivo: ");
            String texto2 = br.readLine();
            
            FileWriter fw = new FileWriter("fw.csv"); 
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(fw);
            
            salida.println(texto2);
            for (int i = 0; i < 10; i++) {
                salida.println(i + "Llena del for");
            }
            for (int i = 0; i < 5; i++) {
                salida.println("Antonio,Ayala,Barbosa,459021,25,50");
            }
            salida.close();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        /**
         * Imprime el texto del archivo indicado
         * Por medio de un buffered reader
         */
        System.out.println("\n..... FileReader .....");
        try{
            BufferedReader br;
            FileReader fr = new FileReader("fw.csv");
            br = new BufferedReader(fr);
            System.out.println("El texto del archivo es: ");
            String linea = br.readLine();
            while(linea != null){
                System.out.println(linea);
                linea = br.readLine();
            }
            br.close();
        } catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        /**
         * Separa una línea por comas y lo manda imprimir por medio de un tokenizer
         */
        System.out.println("\n..... StringTokenizer .....");
        String linea = "Ramiro,Juarez,Perez,765432,21,22";
        StringTokenizer tokenizer = new StringTokenizer(linea,",");
        while(tokenizer.hasMoreTokens())
            System.out.println(tokenizer.nextToken());
        
        
        /**
         * Lee un archivo y lo imprime línea por línea por medio de un tokenizer
         */
        System.out.println("\n..... FileReader & Tokenizer .....");
        try{
            BufferedReader br;
            FileReader fr = new FileReader("fw.csv");
            br = new BufferedReader(fr);
            System.out.println("El texto del archivo es: ");
            linea = br.readLine();
            while(linea != null){
                tokenizer = new StringTokenizer(linea, "");
                while(tokenizer.hasMoreTokens())
                    System.out.println(tokenizer.nextToken());
                linea = br.readLine();
            }
            br.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        /**
         * Indica la fecha y hora actual
         */
        System.out.println("\n...... Serialización .....");
        Date date = new Date();
        System.out.println(date);
        try {
            FileOutputStream f = new FileOutputStream("Fecha.ser");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            oos.writeObject(date);
            oos.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        /**
         * Indica la fecha actualizada y la pasada
         */
        System.out.println("\n..... Deserialización .....");
        try {
            TimeUnit.SECONDS.sleep(10);
            Date date2 = new Date();
            System.out.println("La fecha actualizada es: " + date2);
            FileInputStream f = new FileInputStream("fecha.ser");
            ObjectInputStream ois = new ObjectInputStream(f);
            Date date3 = (Date) ois.readObject();
            ois.close();
            System.out.println("Objeto deserializado: " + date3);
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
