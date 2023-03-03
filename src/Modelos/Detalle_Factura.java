package Modelos;
/**
 * @author Mario Arias
 * @date 18/02/2023
 */
public class Detalle_Factura {
	public String Num_factura;
	public String Codigo_P;
	public String Nombre_P;
	public int Stock;
	public double Precio_Uni;
	public String Estado;
	public String Cedula;
	public String Ciudad;
	public String Direccion;
	public String Celular;
	public Detalle_Factura(){}
	//Num_factura , Codigo_P , Nombre_P , Stock , Precio_Uni  , Estado , Cedula ,  Ciudad , Direccion , Celular
	public Detalle_Factura(String num_factura, String codigo_p, String nombre_p, int stock, double precio_uni, String estado , String cedula, String ciudad, String direccion, String celular){
		this.Num_factura=num_factura;
		this.Codigo_P=codigo_p;
		this.Nombre_P=nombre_p;
		this.Stock=stock;
		this.Precio_Uni=precio_uni;
		this.Estado=estado;
		this.Cedula=cedula;
		this.Ciudad=ciudad;
		this.Direccion=direccion;
		this.Celular=celular;
	}
}
