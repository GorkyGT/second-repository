package Controladores;
/**
 * @author Gorky Villareal
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

public class cFactura {
	//arreglo din�mico de objetos. Arraylist es una coleccion predefinida en JAVA
	ArrayList<Factura> Lista = new ArrayList<Factura>();
	//encabezados de columnas de la tabla
	//Num_factura , Vnombre , Cedula , Apellido , Nombre , Fecha_emision , Total_factura , Forma_pago

	public String[] columnName = {"No.Factura", "Nombre de Factura", "Cedula", "Apellido", "Nombre", "Fecha Emision", "Total Factura", "Forma de Pago", "Cedula Vendedor", "Codigo Producto", "Nombre Producto"};

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
	 * Registra un nuevo Factura
	 */
	public void nuevo(Factura e) throws IOException{
		int pos=localizar(e.Cedula);
		if(pos==-1){//si cedula no esta registrada, se agrega nuevo Factura
			Lista.add(e);
		}
		else{
			throw new RuntimeException("Número de Factura ya ha sido asignado a otra Factura");
		}
	}

	/**
	 * Modificar datos de un Factura existente
	 */
	public void modificar(Factura e, String ced) throws IOException{
		int pos=localizar(ced);
		if(pos>-1){//si estudiante est� registrado se modifica
			Lista.set(pos, e);
		}
		else{
			throw new RuntimeException("No existe un cliente registrado con la cedula ingresada");
		}
	}
/*	public void modificar(Factura e, String cedula) throws IOException{
		int pos=localizar(cedula);
		if(pos>-1){//si Factura est� registrado se modifica
			Lista.set(pos, e);
		}
		else{
			throw new RuntimeException("No existe una Factura registrada con el n�mero de cedula ingresada");
		}
	}

	/**
	 * Eliminar un Factura
	 */
	public void eliminar(String cedula) throws IOException{
		int pos=localizar(cedula);
		if(pos>-1){//si Factura est� registrado se elimina
			Lista.remove(pos);
		}
		else{
			throw new RuntimeException("No existe una Factura registrada con el n�mero de factura ingresada");
		}
	}
	/*public void eliminar(String cedula) throws IOException{
		int pos=localizar(cedula);
		if(pos>-1){//si Factura está registrado se elimina
			Lista.remove(pos);
		}
		else{
			throw new RuntimeException("No existe una Factura registrada con el número de cédula  ingresada");
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
            //Num_factura , Nombre_fac , Cedula , Apellido , Nombre , Fecha_emision , Total_factura , Forma_pago
        	String num="N-00";
        	Factura e=(Factura)Lista.get(i);
        	String nombre="Factura de compra";
        	Object[] row={

        			e.Num_factura=new String(num+i), e.Nombre_fac=new String(nombre), e.Cedula,e.Apellido, e.Nombre,
        			e.Fecha_emision, e.Total_factura, e.Forma_pago, e.CedulaV, e.Codigo_P, e.Nombre_P
        			};
            tabla.addRow(row);
        }
        return tabla;
    }

	/**
	 * Algoritmo de busqueda secuencial en el arreglo Lista
	 * @param cedula
	 * @return posicion en el arreglo del Factura encontrado
	 */
	/*public int localizar(String cedula){
		int pos=-1; //se retorna -1 si no se encuentra en el arreglo
		for(int i=0; i<Lista.size(); i++){
			Factura e=(Factura)Lista.get(i);
			if(cedula.equals(e.Cedula)){
				pos=i; //posicion encontrada
				break; //finaliza el ciclo for
			}
		}
		return pos;
	}
	*/
    public int localizar(String cedula){
		int pos=-1; //se retorna -1 si no se encuentra en el arreglo
		for(int i=0; i<Lista.size(); i++){
			Factura e=(Factura)Lista.get(i);
			if(cedula.equals(e.Cedula)){
				pos=i; //posicion encontrada
				break; //finaliza el ciclo for
			}
		}
		return pos;
	}

	/**
	 * Algoritmo de busqueda secuencial mediante criterio de num_factura parcial en el arreglo Lista
	 * @param num_factura
	 * @return cFactura
	 * @throws IOException
	 */
	public cFactura buscar_Num_factura(String num_factura) throws IOException{
		cFactura ob=new cFactura();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Factura e=(Factura)Lista.get(i);
			if(e.Num_factura.length() >= num_factura.length()){
				cad=e.Num_factura.substring(0, num_factura.length());
				if(cad.equalsIgnoreCase(num_factura)){
					ob.nuevo(e);
				}
			}
		}
		return ob;
	}
	/**
	 * Algoritmo de busqueda secuencial mediante criterio de cedula parcial en el arreglo Lista
	 * @param fecha_emision
	 * @return cFactura
	 * @throws IOException
	 */

	public cFactura buscar_cedula(String cedula) throws IOException{
		cFactura ob=new cFactura();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Factura e=(Factura)Lista.get(i);
			if(e.Cedula.length() >= cedula.length()){
				cad=e.Cedula.substring(0, cedula.length());
				if(cad.equalsIgnoreCase(cedula)){
					ob.nuevo(e);
				}
			}
		}
		return ob;
	}

	public cFactura buscar_nombre(String nombre) throws IOException{
		cFactura ob=new cFactura();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Factura e=(Factura)Lista.get(i);
			if(e.Nombre.length() >= nombre.length()){
				cad=e.Nombre.substring(0, nombre.length());
				if(cad.equalsIgnoreCase(nombre)){
					ob.nuevo(e);
				}
			}
		}
		return ob;
	}
	public cFactura buscar_apellido(String apellido) throws IOException{
		cFactura ob=new cFactura();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Factura e=(Factura)Lista.get(i);
			if(e.Apellido.length() >= apellido.length()){
				cad=e.Apellido.substring(0, apellido.length());
				if(cad.equalsIgnoreCase(apellido)){
					ob.nuevo(e);
				}
			}
		}
		return ob;
	}
	public cFactura buscar_cedulav(String cedulav) throws IOException{
		cFactura ob=new cFactura();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Factura e=(Factura)Lista.get(i);
			if(e.CedulaV.length() >= cedulav.length()){
				cad=e.CedulaV.substring(0, cedulav.length());
				if(cad.equalsIgnoreCase(cedulav)){
					ob.nuevo(e);
				}
			}
		}
		return ob;
	}
	public cFactura buscar_codigop(String codigo_p) throws IOException{
		cFactura ob=new cFactura();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Factura e=(Factura)Lista.get(i);
			if(e.Codigo_P.length() >= codigo_p.length()){
				cad=e.Codigo_P.substring(0, codigo_p.length());
				if(cad.equalsIgnoreCase(codigo_p)){
					ob.nuevo(e);
				}
			}
		}
		return ob;
	}
	public cFactura buscar_nombrep(String nombre_p) throws IOException{
		cFactura ob=new cFactura();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Factura e=(Factura)Lista.get(i);
			if(e.Nombre_P.length() >= nombre_p.length()){
				cad=e.Nombre_P.substring(0, nombre_p.length());
				if(cad.equalsIgnoreCase(nombre_p)){
					ob.nuevo(e);
				}
			}
		}
		return ob;
	}
	/**
	 *
	 * Retornar un objeto del arreglo Lista
	 * @param pos es la posicion del objeto en el arreglo
	 * @return objeto encontrado
	 */
	public Factura getFactura(int pos){
		Factura ob=null;
		if(pos>=0 && pos<Lista.size()){
			ob=Lista.get(pos);
		}
		return ob;
	}
	//Leer datos de un archivo de texto separado por ;
	public static final String SEPARADOR=";";
    public static final String QUOTE="\"";

	public void leerF() throws IOException{
		BufferedReader br = null;
	    try {
	    	String path=Global.getPath()+"\\Recursos\\dataFacturas.csv";
	    	br =new BufferedReader(new FileReader(path));
	        String line = br.readLine();
	        Clear(); //limpiar lista de objetos
	        line = br.readLine();
	        while (line!=null) {
	           String [] row = line.split(SEPARADOR);
	           removeTrailingQuotes(row);
	         //Num_factura , Vnombre , Cedula , Apellido , Nombre , Fecha_emision , Total_factura , Forma_pago
	           Factura ob=new Factura();
	           ob.Num_factura=(row[0]);
	           ob.Nombre_fac=row[1];
	           ob.Cedula=row[2];
	           ob.Apellido=row[3];
	           ob.Nombre=row[4];
	           ob.Fecha_emision= row[5];
	           ob.Total_factura=Double.parseDouble(row[6]);
	           ob.Forma_pago=row[7];
	           ob.CedulaV=row[8];
	           ob.Codigo_P=row[9];
	           ob.Nombre_P=row[10];
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

	public void guardar() throws IOException{
		FileWriter file;
	    try {
	    	String path = Global.getPath()+"\\Recursos\\dataFacturas.csv";
	    	file = new FileWriter(path);
	    	final String NEXT_LINE = "\n";
	    	//num_factura, ruc, Cedula, nombre_factura, fecha_emision, subtotal, iva, total_factura, forma_pago

	    	file.append("No.Factura").append(SEPARADOR);
	    	file.append("Nombre de Factura").append(SEPARADOR);
			file.append("Cédula").append(SEPARADOR);
			file.append("Apellido ").append(SEPARADOR);
			file.append("Nombre").append(SEPARADOR);
			file.append("Fecha Emision").append(SEPARADOR);
			file.append("Total Factura").append(SEPARADOR);
			file.append("Forma de Pago").append(SEPARADOR);
			file.append("Cédula Vendedor").append(SEPARADOR);
			file.append("Código Producto").append(SEPARADOR);
			file.append("Nombre Producto").append(NEXT_LINE);

	    	for(int i=0; i<Lista.size();i++){

	        	Factura ob=(Factura)Lista.get(i);
				file.append(ob.Num_factura + "").append(SEPARADOR); //Si no es string poner < + "" >
				file.append(ob.Nombre_fac).append(SEPARADOR);
				file.append(ob.Cedula).append(SEPARADOR);
				file.append(ob.Apellido).append(SEPARADOR);
				file.append(ob.Nombre).append(SEPARADOR);
				file.append(ob.Fecha_emision ).append(SEPARADOR);
				file.append(ob.Total_factura+ "").append(SEPARADOR);
				file.append(ob.Forma_pago).append(SEPARADOR);
				file.append(ob.CedulaV).append(SEPARADOR);
				file.append(ob.Codigo_P).append(SEPARADOR);
				file.append(ob.Nombre_P).append(NEXT_LINE);
	        }
	    	file.flush();
			file.close();
	     } catch (Exception e) {
	    	 System.out.print(e.getMessage());
	     }
	}
}
