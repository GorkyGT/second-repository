package Controladores;
/**
 * @author Anthony_Lima
 * @date 18/02/2023
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.DefaultTableModel;


import Modelos.*;

public class cVendedor {
	//arreglo din�mico de objetos. Arraylist es una coleccion predefinida en JAVA
	ArrayList<Vendedor> Lista = new ArrayList<Vendedor>();
	//encabezados de columnas de la tabla

	public String[] columnName = {"cedula", "Nombres", "apellido", "Telefono","Ciudad"};

	/**
	 * Retorna la cantidad de objetos del arreglo
	 */
	public int Count(){
		return Lista.size();
	}

	/**
	 * Borrar todos los elementos del arreglo
	 */
	public void Clear(){
		Lista.clear();
	}

	/**
	 * Registra un nuevo Vendedor
	 */
	public void nuevo(Vendedor e) throws IOException{
		int pos=localizar(e.cedula);
		if(pos==-1){//si cedula no esta registrada el numero de cedulas, se agrega nuevo num de cedula
			Lista.add(e);
		}
		else{
			throw new RuntimeException("# de Cédula ya asignado a otro Vendedor");
		}
	}

	/**
	 * Modificar datos de un Vendedor existente
	 */
	public void modificar(Vendedor e, String cedula) throws IOException{
		int pos=localizar(cedula);
		if(pos>-1){//si Vendedor est� registrado se modifica
			Lista.set(pos, e);
		}
		else{
			throw new RuntimeException("No existe un Vendedor registrado con la cedula ingresada");
		}
	}

	/**
	 * Eliminar un Vendedor
	 */
	public void eliminar(String cedula) throws IOException{
		int pos=localizar(cedula);
		if(pos>-1){//si Vendedor est� registrado se elimina
			Lista.remove(pos);
		}
		else{
			throw new RuntimeException("No existe un Vendedor registrado con la cedula ingresada");
		}
	}

	/**
	 * Lista datos en un defaultablemodel para presentar en una tabla
	 */
    public DefaultTableModel getTabla(){
        DefaultTableModel tabla = new DefaultTableModel(columnName, 0){
        	@Override
            public boolean isCellEditable(int row, int column) {  return false; }
        };
        for(int i=0; i<Lista.size();i++){
        	Vendedor e=(Vendedor)Lista.get(i);
        	//cedula, nombres, apellidos, telefono, ciudad.
        	Object[] row={
        			e.cedula, e.Nombres, e.apellidos, e.telefono, e.Ciudad
        			};
            tabla.addRow(row);
        }
        return tabla;
    }

	/**
	 * Algoritmo de busqueda secuencial en el arreglo Lista
	 * @param i2
	 * @return posicion en el arreglo del Vendedor encontrado
	 */
	public int localizar(String cedula ){
		int pos=-1; //se retorna -1 si no se encuentra en el arreglo
		for(int i=0; i<Lista.size(); i++){
			Vendedor e=(Vendedor)Lista.get(i);
			if(cedula.equals(e.cedula)){
				pos=i; //posicion encontrada
				break; //finaliza el ciclo for
			}
		}
		return pos;
	}

	/**
	 * Algoritmo de busqueda secuencial mediante criterio de cedula parcial en el arreglo Lista
	 * @param cedula
	 * @return cVendedor
	 * @throws IOException
	 */
	public cVendedor buscar_cedula(String cedula) throws IOException{
		cVendedor ob=new cVendedor();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Vendedor e=(Vendedor)Lista.get(i);
			if(e.cedula.length() >= cedula.length()){
				cad=e.cedula.substring(0, cedula.length());
				if(cad.equalsIgnoreCase(cedula)){
					ob.nuevo(e);
				}
			}
		}	
		return ob;
	}
	/**
	 * Algoritmo de busqueda secuencial mediante criterio de Nombres en el arreglo Lista
	 * @param cedula
	 * @return cVendedor
	 * @throws IOException
	 */
	public cVendedor buscar_apellidos(String apellidos) throws IOException{
		cVendedor ob=new cVendedor();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Vendedor e=(Vendedor)Lista.get(i);
				if(e.apellidos.toUpperCase().startsWith(apellidos.toUpperCase())){
					ob.nuevo(e);
				}
		}
		return ob;
	}

	/**
	 * Retornar un objeto del arreglo Lista
	 * @param pos es la posicion del objeto en el arreglo
	 * @return objeto encontrado
	 */
	public Vendedor getVendedor(int pos){
		Vendedor ob=null;
		if(pos>=0 && pos<Lista.size()){
			ob=Lista.get(pos);
		}
		return ob;
	}

	//leer datos en un archivo de texto separado por ;

	public static final String SEPARADOR=";";
    public static final String QUOTE="\"";

	public void leer() throws IOException{
		BufferedReader br = null;
	    try {
	    	String path = Global.getPath()+"\\Recursos\\dataVendedor.csv";
	    	br =new BufferedReader(new FileReader(path));
	        String line = br.readLine();
	        Clear(); //limpiar lista de objetos
	        line = br.readLine();
	        while (line!=null) {
	           String [] row = line.split(SEPARADOR);
	           removeTrailingQuotes(row);
	           Vendedor ob=new Vendedor();
	         //cedula_v, nombres_v, apellidos_v, telefono_v, ciudad_v.
	           ob.cedula=row[0];
	           ob.Nombres=row[1];
	           ob.apellidos=row[2];
	           ob.telefono=row[3];
	           ob.Ciudad=row[4];
	           nuevo(ob);//agregar a la lista

        System.out.println(Arrays.toString(row));
	           line = br.readLine();
	        }
	     } catch (Exception e) {

	     } finally {
	        if (null!=br) {
	           br.close();
	        }
	     }
	}
	//eliminar comillas
	private static String[] removeTrailingQuotes(String[] fields) {

	    String result[] = new String[fields.length];

	    for (int i=0;i<result.length;i++){
	       result[i] = fields[i].replaceAll("^"+QUOTE, "").replaceAll(QUOTE+"$", "");
	    }
	    return result;
	 }

	//Guardar datos del arreglo
	public void guardar() throws IOException{
		FileWriter file;
	    try {
	    	String path = Global.getPath()+"\\Recursos\\dataVendedor.csv";
	    	file = new FileWriter(path);
	    	final String NEXT_LINE = "\n";

	    	file.append("cedula").append(SEPARADOR);
			file.append("nombres").append(SEPARADOR);
			file.append("apellidos").append(SEPARADOR);
			file.append("telefono").append(SEPARADOR);
			file.append("ciudad").append(NEXT_LINE); //siempre la ultima linea es NEXT_LINE

	    	for(int i=0; i<Lista.size();i++){
	        	Vendedor ob=(Vendedor)Lista.get(i);
	        	//cedula_v, nombres_v, apellidos_v, telefono_v, ciudad_v.
				file.append(ob.cedula +"").append(SEPARADOR);
				file.append(ob.Nombres +"").append(SEPARADOR);
				file.append(ob.apellidos).append(SEPARADOR);
				file.append(ob.telefono).append(SEPARADOR);
				file.append(ob.Ciudad +"").append(NEXT_LINE);

	        }
	    	file.flush();
			file.close();
	     } catch (Exception e) {
	    	 System.out.print(e.getMessage());
	     }
	}
}