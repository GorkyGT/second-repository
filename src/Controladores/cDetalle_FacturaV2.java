package Controladores;
/**
 * @author Damián Minuche
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

public class cDetalle_FacturaV2 {
	//arreglo din�mico de objetos. Arraylist es una coleccion predefinida en JAVA
	ArrayList<Detalle_Factura> Lista = new ArrayList<Detalle_Factura>();
	//encabezados de columnas de la tabla
	//Num_factura , Codigo_P , Nombre_P , Stock , Precio_Uni  , Estado , Cedula ,  Ciudad , Direccion , Celular

	public String[] columnName = {"No. Factura", "Codigo Producto", "Nombre Producto", "Stock","Precio Unitario", "Estado", "Cedula", "Ciudad", "Direccion", "Celular"};

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
	 * Registra un nuevo estudiante
	 */
	public void nuevo(Detalle_Factura e) throws IOException{
		int pos=localizar(e.Codigo_P);
		if(pos==-1){//si el codigo no esta registrado, se agrega nuevo producto
			Lista.add(e);
		}
		else{
			throw new RuntimeException("# de Codigo ya asignado a otro producto");
		}
	}

	/**
	 * Modificar datos de un estudiante existente
	 */
	public void modificar(Detalle_Factura e, String ced) throws IOException{
		int pos=localizar(ced);
		if(pos>-1){//si producto está registrado se modifica
			Lista.set(pos, e);
		}
		else{
			throw new RuntimeException("No existe un producto registrado con el codigo ingresado");
		}
	}

	/**
	 * Eliminar un estudiante
	 */
	public void eliminar(String codigo_p) throws IOException{
		int pos=localizar(codigo_p);
		if(pos>-1){//si producto está registrado ese elimina
			Lista.remove(pos);
		}
		else{
			throw new RuntimeException("No existe un producto registrado con el codigo ingresado");
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
        	String num="00";
        	Detalle_Factura e=(Detalle_Factura)Lista.get(i);
        	Object[] row={
        			//Num_factura , Codigo_P , Nombre_P , Stock , Precio_Uni  , Estado , Cedula ,  Ciudad , Direccion , Celular
        			new Integer(i+num+1+i), e.Codigo_P, e.Nombre_P, e.Stock, e.Precio_Uni,
        			e.Estado, e.Cedula, e.Ciudad, e.Direccion, e.Celular
        			};
            tabla.addRow(row);
        }
        return tabla;
    }

	/**
	 * Algoritmo de busqueda secuencial en el arreglo Lista
	 * @param cedula
	 * @return posicion en el arreglo del estudiante encontrado
	 */
	public int localizar(String codigo_p){
		int pos=-1; //se retorna -1 si no se encuentra en el arreglo
		for(int i=0; i<Lista.size(); i++){
			Detalle_Factura e=(Detalle_Factura)Lista.get(i);
			if(codigo_p.equals(e.Codigo_P)){
				pos=i; //posicion encontrada
				break; //finaliza el ciclo for
			}
		}
		return pos;
	}

	/**
	 * Algoritmo de busqueda secuencial mediante criterio de cedula parcial en el arreglo Lista
	 * @param cedula
	 * @return cEstudiante
	 * @throws IOException
	 */
	public cDetalle_FacturaV2 buscar_codigo_producto(String codigo_p) throws IOException{
		cDetalle_FacturaV2 ob=new cDetalle_FacturaV2();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Detalle_Factura e=(Detalle_Factura)Lista.get(i);
			if(e.Codigo_P.length() >= codigo_p.length()){
				cad=e.Codigo_P.substring(0, codigo_p.length());
				if(cad.equalsIgnoreCase(codigo_p)){
					ob.nuevo(e);
				}
			}
		}
		return ob;
	}

	/**
	 * Algoritmo de busqueda secuencial mediante criterio apellido parcial en el arreglo Lista
	 * @param apellido
	 * @return cEstudiante
	 * @throws IOException
	 */
	public cDetalle_FacturaV2 buscar_cedula(String cedula) throws IOException{
		cDetalle_FacturaV2 ob=new cDetalle_FacturaV2();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Detalle_Factura e=(Detalle_Factura)Lista.get(i);
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
	 * Retornar un objeto del arreglo Lista
	 * @param pos es la posicion del objeto en el arreglo
	 * @return objeto encontrado
	 */
	public Detalle_Factura getDetalle_Factura(int pos){
		Detalle_Factura ob=null;
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
	    	String path = Global.getPath()+"\\Recursos\\dataDetalles_Facturas.csv";
	    	br =new BufferedReader(new FileReader(path));
	        String line = br.readLine();
	        Clear(); //limpiar lista de objetos
	        line = br.readLine();
	        while (line!=null) {
	           String [] row = line.split(SEPARADOR);
	           removeTrailingQuotes(row);
	       	//Num_factura , Codigo_P , Nombre_P , Stock , Precio_Uni  , Estado , Cedula ,  Ciudad , Direccion , Celular

	           Detalle_Factura ob=new Detalle_Factura();
	           ob.Num_factura=row[0];
	           ob.Codigo_P=row[1];
	           ob.Nombre_P=row[2];
	           ob.Stock=Integer.parseInt(row[3]);
	           ob.Precio_Uni=Double.parseDouble(row[4]);
	           ob.Estado=row[5];
	           ob.Cedula=row[6];
	           ob.Ciudad=row[7];
	           ob.Direccion=row[8];
	           ob.Celular=row[9];
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
	    	String path = Global.getPath()+"\\Recursos\\dataDetalles_Facturas.csv";
	    	file = new FileWriter(path);
	    	final String NEXT_LINE = "\n";
	    	file.append("No. Factura").append(SEPARADOR);
	    	file.append("Codigo Producto").append(SEPARADOR);
			file.append("Nombre de Producto").append(SEPARADOR);
			file.append("Stock").append(SEPARADOR);
			file.append("Precio Unitario").append(SEPARADOR);
			file.append("Estado").append(SEPARADOR);
			file.append("Cédula").append(SEPARADOR);
			file.append("Ciudad").append(SEPARADOR);
			file.append("Direcci�n").append(SEPARADOR);
			file.append("Celular").append(NEXT_LINE);

	    	for(int i=0; i<Lista.size();i++){
	        	Detalle_Factura ob=(Detalle_Factura)Lista.get(i);
	        	file.append(ob.Num_factura+"").append(SEPARADOR);
	        	file.append(ob.Codigo_P).append(SEPARADOR);
				file.append(ob.Nombre_P).append(SEPARADOR);
				file.append(ob.Stock+"").append(SEPARADOR);
				file.append(ob.Precio_Uni+"").append(SEPARADOR);
				file.append(ob.Estado).append(SEPARADOR);
				file.append(ob.Cedula).append(SEPARADOR);
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