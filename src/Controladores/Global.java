package Controladores;
/**
 * @author Anthony_Lima
 * @date 18/02/2023
 */
import java.io.File;

public class Global {

	//obtiene ruta del c�digo fuente del Proyecto
    public static String getPath(){
        //extraer ruta del proyecto de forma din�mica
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        //eliminar los dos caracteres del final del path
        path=path.substring(0,path.length()-2);
        System.out.println("Path del Proyecto " + path);
        return path+="\\src\\";
    }



}
