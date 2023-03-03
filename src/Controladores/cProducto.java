package Controladores;
/**
 * @author Mario Arias
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

public class cProducto {
	//arreglo din�mico de objetos. Arraylist es una coleccion predefinida en JAVA
	ArrayList<Producto> Lista = new ArrayList<Producto>();
	//encabezados de columnas de la tabla
	public String[] columnName = {"No.", "Código", "Nombre","Categoria", "Stock", "Marca", "Precio Unitario"};

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
	 * Registra un nuevo Producto
	 */
	public void nuevo(Producto e) throws IOException{
		int pos=localizar(e.Codigo_P);
		if(pos==-1){//si Codigo_P no esta registrada, se agrega nuevo Producto
			Lista.add(e);
		}
		else{
			throw new RuntimeException("# de Codigo ya asignado a otro Producto");
		}
	}

	/**
	 * Modificar datos de un Producto existente
	 */
	public void modificar(Producto e, String ced) throws IOException{
		int pos=localizar(ced);
		if(pos>-1){//si Producto est� registrado se modifica
			Lista.set(pos, e);
		}
		else{
			throw new RuntimeException("No existe un Producto registrado con la Codigo_P ingresada");
		}
	}

	/**
	 * Eliminar un Producto
	 */
	public void eliminar(String Codigo_P) throws IOException{
		int pos=localizar(Codigo_P);
		if(pos>-1){//si Producto est� registrado se elimina
			Lista.remove(pos);
		}
		else{
			throw new RuntimeException("No existe un Producto registrado con la Codigo_P ingresada");
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
        	Producto e=(Producto)Lista.get(i);
        	Object[] row={
        			new Integer(i+1), e. Codigo_P, e.Nombre_P, e.Categoria,
        			e.Stock, e.Marca, e.Precio_uni
        			};
            tabla.addRow(row);
        }
        return tabla;
    }

	/**
	 * Algoritmo de busqueda secuencial en el arreglo Lista
	 * @param Codigo_P
	 * @return posicion en el arreglo del Producto encontrado
	 */
	public int localizar(String codigo_P){
		int pos=-1; //se retorna -1 si no se encuentra en el arreglo
		for(int i=0; i<Lista.size(); i++){
			Producto e=(Producto)Lista.get(i);
			if(codigo_P.equals(e.Codigo_P)){
				pos=i; //posicion encontrada
				break; //finaliza el ciclo for
			}
		}
		return pos;
	}

	/**
	 * Algoritmo de busqueda secuencial mediante criterio de Codigo_P parcial en el arreglo Lista
	 * @param Codigo_P
	 * @return cProducto
	 * @throws IOException
	 */
	public cProducto buscar_Codigo_P(String Codigo_P) throws IOException{
		cProducto ob=new cProducto();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Producto e=(Producto)Lista.get(i);
			if(e.Codigo_P.length() >= Codigo_P.length()){
				cad=e.Codigo_P.substring(0, Codigo_P.length());
				if(cad.equalsIgnoreCase(Codigo_P)){
					ob.nuevo(e);
				}
			}
		}
		return ob;
	}

	/**
	 * Algoritmo de busqueda secuencial mediante criterio apellido parcial en el arreglo Lista
	 * @param apellido
	 * @return cProducto
	 * @throws IOException
	 */
	public cProducto buscar_nombre(String nombre_P) throws IOException{
		cProducto ob=new cProducto();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Producto e=(Producto)Lista.get(i);
			if(e.Nombre_P.length() >= nombre_P.length()){
				cad=e.Nombre_P.substring(0, nombre_P.length());
				if(cad.equalsIgnoreCase(nombre_P)){
					ob.nuevo(e);
				}
			}
		}
		return ob;
	}

	/**
	 * Retornar un objeto del arreglo Lista
	 * @param pos es la posicion del objeto en el arreglo
	 * @return objeto encontrado
	 */
	public Producto getProducto(int pos){
		Producto ob=null;
		if(pos>=0 && pos<Lista.size()){
			ob=Lista.get(pos);
		}
		return ob;
	}

	public static final String SEPARADOR=";";
    public static final String QUOTE="\"";

	public void leer() throws IOException{
		BufferedReader br = null;
	    try {
	    	String path = Global.getPath()+"\\Recursos\\dataProductos.csv";
	    	br =new BufferedReader(new FileReader(path));
	        String line = br.readLine();
	        Clear(); //limpiar lista de objetos
	        line = br.readLine();
	        while (line!=null) {
	           String [] row = line.split(SEPARADOR);
	           removeTrailingQuotes(row);
	           Producto ob=new Producto();
	           ob.Codigo_P=row[0];
	           ob.Nombre_P=row[1];
	           ob.Categoria=row[2];
	           ob.Stock=Integer.parseInt(row[3]);
	           ob.Marca=row[4];
	           ob.Precio_uni=Double.parseDouble(row[5]);
	           nuevo(ob);//agregar a la lista
	           System.out.println(ob.Nombre_P);
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

	public void guardar() throws IOException{
		FileWriter file;
	    try {
	    	String path = Global.getPath()+"\\Recursos\\dataProductos.csv";
	    	file = new FileWriter(path);
	    	final String NEXT_LINE = "\n";

	    	file.append("Codigo_P").append(SEPARADOR);
			file.append("Nombre_P").append(SEPARADOR);
			file.append("Categoria").append(SEPARADOR);
			file.append("Stock").append(SEPARADOR);
			file.append("Marca").append(SEPARADOR);
			file.append("Precio_uni").append(NEXT_LINE);

	    	for(int i=0; i<Lista.size();i++){
	        	Producto ob=(Producto)Lista.get(i);
				file.append(ob.Codigo_P).append(SEPARADOR);
				file.append(ob.Nombre_P).append(SEPARADOR);
				file.append(ob.Categoria).append(SEPARADOR);
				file.append(ob.Stock+"").append(SEPARADOR);
				file.append(ob.Marca).append(SEPARADOR);
				file.append(ob.Precio_uni+"").append(NEXT_LINE);

	        }
	    	file.flush();
			file.close();
	     } catch (Exception e) {
	    	 System.out.print(e.getMessage());
	     }
	}
}