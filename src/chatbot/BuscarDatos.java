/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatbot;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;
/**
 *
 * @author gamei
 */
public class BuscarDatos extends FileController{
    //base de datos de las palabras a responder
    private static final String DATABASE ="./datos.txt";
    //lee las palabras escritas
    private RandomAccessFile fileReader;
    public BuscarDatos(){
       super(DATABASE);
       try{
           fileReader = new RandomAccessFile(file, "r");
       }catch(FileNotFoundException e){
           JOptionPane.showMessageDialog(null,"la base de datos de la traduccion no fue encontrada","Error en la base de datos traduccion",JOptionPane.ERROR_MESSAGE);
           e.printStackTrace();
       }
    }
    public String translate(String word){
        String translation = "ok";
        String palabraGuardada = "";
        String palabra = word;
        int contador = 0,contador2 = 0;
        try{
            String line = fileReader.readLine();
            boolean encontro = false;
            while (line !=null && encontro != true){
                String [] tupla= line.split("#");
                String [] tuplados=palabra.split(" ");
                for (int i=0;i<=tuplados.length-1;i++){
                    for(int j=0;j<tupla.length-1;j++){
                        if(tuplados[i].equalsIgnoreCase(tupla[j])){
                            translation = tupla[tupla.length-1];
                            contador++;
                        }
                    }
                }
                if(contador>=contador2){
                    contador2 = contador;
                    palabraGuardada = translation;
                }
                contador=0;
                line = fileReader.readLine();
            }
        }catch(IOException ioe){
            JOptionPane.showMessageDialog(null,"error al leer la base de datos de palabras","Error en la base de datos traduccion",JOptionPane.ERROR_MESSAGE);
            ioe.printStackTrace();
        }
        finally{
            try{
                fileReader.close();
            }catch(IOException ioe){
                JOptionPane.showMessageDialog(null,"error al cerrar la base de datos palabras","Error en la base de datos traduccion",JOptionPane.ERROR_MESSAGE);
                ioe.printStackTrace();
            }
        }
        return palabraGuardada;
    }
}
