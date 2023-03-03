package Modelos;
/**
 * @author Gorky Villareal
 * @date 18/02/2023
 */
public class Factura {
	//num_factura, ruc, cedula_c, nombre_factura, fecha_emision, total_factura, forma_pago
	public String Num_factura;
	public String Nombre_fac;
	public String Cedula;
	public String Apellido;
	public String Nombre;
	public String Fecha_emision;
	public double Total_factura;
	public String Forma_pago;
	public String CedulaV;
	public String Codigo_P;
	public String Nombre_P;
	//Constructores
	//Vacio

	public Factura(){}
    //Num_factura , Nombre_fac , Cedula , Apellido , Nombre , Fecha_emision , Total_factura , Forma_pago
	//comun o con parametros
	public Factura(String num_factura , String nombre_fac , String cedula, String apellido, String nombre, String fecha_emision, double total_factura, String forma_pago, String cedulav, String codigo_p,String nombre_p){
		this.Num_factura=num_factura;
		this.Nombre_fac=nombre_fac;
		this.Cedula=cedula;
		this.Apellido=apellido;
		this.Nombre=nombre;
		this.Fecha_emision=fecha_emision;
		this.Total_factura=total_factura;
		this.Forma_pago=forma_pago;
		this.CedulaV=cedulav;
		this.Codigo_P=codigo_p;
		this.Nombre_P=nombre_p;
	}
}
