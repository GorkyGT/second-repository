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

public class cCliente {
	//arreglo din�mico de objetos. Arraylist es una coleccion predefinida en JAVA
	ArrayList<Cliente> Lista = new ArrayList<Cliente>();
	//encabezados de columnas de la tabla
	public String[] columnName = {"No.", "Cédula", "Apellidos", "Nombres","Ciudad", "Direccion", "Celular"};

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
	 * Registrar un nuevo estudiante
	 */
	public void nuevo(Cliente e) throws IOException{
		int pos=localizar(e.Cedula);
		if(pos==-1){//si la cédula no esta registrada, se agrega nuevo estudiante
			Lista.add(e);
		}
		else{
			throw new RuntimeException("# de Cédula ya asignado a otro Cliente");
		}
	}

	/**
	 * Modificar datos de un estudiante existente
	 */
	public void modificar(Cliente e, String ced) throws IOException{
		int pos=localizar(ced);
		if(pos>-1){//si estudiante est� registrado se modifica
			Lista.set(pos, e);
		}
		else{
			throw new RuntimeException("No existe un cliente registrado con la cedula ingresada");
		}
	}

	/**
	 * Eliminar un estudiante
	 */
	public void eliminar(String cedula) throws IOException{
		int pos=localizar(cedula);
		if(pos>-1){//si estudiante est� registrado se elimina
			Lista.remove(pos);
		}
		else{
			throw new RuntimeException("No existe un cliente registrado con la cedula ingresada");
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
			Cliente e=(Cliente)Lista.get(i);
			Object[] row={
					new Integer(i+1), e.Cedula, e.Apellidos, e.Nombres, e.Ciudad,
					e.Direccion, e.Celular
			};
			tabla.addRow(row);
		}
		return tabla;
	}

	/**
	 * Algoritmo de busqueda secuencial en el arreglo Lista
	 * @param Cédula
	 * @return posicion en el arreglo del estudiante encontrado
	 */
	public int localizar(String cedula){
		int pos=-1; //se retorna -1 si no se encuentra en el arreglo
		for(int i=0; i<Lista.size(); i++){
			Cliente e=(Cliente)Lista.get(i);
			if(cedula.equals(e.Cedula)){
				pos=i; //posicion encontrada
				break; //finaliza el ciclo for
			}
		}
		return pos;
	}

	/**
	 * Algoritmo de busqueda secuencial mediante criterio de cedula parcial en el arreglo Lista
	 * @param cédula
	 * @return cCliente
	 * @throws IOException
	 */
	public cCliente buscar_cedula(String cedula) throws IOException{
		cCliente ob=new cCliente();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Cliente e=(Cliente)Lista.get(i);
			if(e.Cedula.length() >= cedula.length()){
				cad=e.Cedula.substring(0, cedula.length());
				if(cad.equalsIgnoreCase(cedula)){
					ob.nuevo(e);
				}
			}
		}
		return ob;
	}

	/**
	 * Algoritmo de busqueda secuencial mediante criterio apellido parcial en el arreglo Lista
	 * @param apellido
	 * @return cCliente
	 * @throws IOException
	 */
	public cCliente buscar_apellido(String apellidos) throws IOException{
		cCliente ob=new cCliente();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Cliente e=(Cliente)Lista.get(i);
			if(e.Apellidos.length() >= apellidos.length()){
				cad=e.Apellidos.substring(0, apellidos.length());
				if(cad.equalsIgnoreCase(apellidos)){
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
	public Cliente getCliente(int pos){
		Cliente ob=null;
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
			String path = Global.getPath()+"\\Recursos\\dataclientes.csv";
			br =new BufferedReader(new FileReader(path));
			String line = br.readLine();
			Clear(); //limpiar lista de objetos
			line = br.readLine();
			while (line!=null) {
				String [] row = line.split(SEPARADOR);
				removeTrailingQuotes(row);
				Cliente ob=new Cliente();
				ob.Cedula=row[0];
				ob.Apellidos=row[1];
				ob.Nombres=row[2];
				ob.Ciudad=row[3];
				ob.Direccion= row[4];
				ob.Celular=row[5];
				nuevo(ob);//agregar a la lista
				System.out.println(ob.Apellidos);
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
			String path = Global.getPath()+"\\Recursos\\dataclientes.csv";
			file = new FileWriter(path);
			final String NEXT_LINE = "\n";

			file.append("Cedula").append(SEPARADOR);
			file.append("Apellidos").append(SEPARADOR);
			file.append("Nombres").append(SEPARADOR);
			file.append("Ciudad").append(SEPARADOR);
			file.append("Direccion").append(SEPARADOR);
			file.append("Celular").append(NEXT_LINE);

			for(int i=0; i<Lista.size();i++){
				Cliente ob=(Cliente)Lista.get(i);
				file.append(ob.Cedula).append(SEPARADOR);
				file.append(ob.Apellidos).append(SEPARADOR);
				file.append(ob.Nombres).append(SEPARADOR);
				file.append(ob.Ciudad).append(SEPARADOR);
				file.append(ob.Direccion).append(SEPARADOR);
				file.append(ob.Celular).append(NEXT_LINE);

			}
			file.flush();
			file.close();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}