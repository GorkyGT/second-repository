package Modelos;
/**
 * @author Damian Minuche
 * @date 18/02/2023
 */
public class Producto {
	//datos de la clase
	public String Codigo_P;
	public String Nombre_P;
	public String Categoria;
	public int Stock;
	public String Marca;
	public double Precio_uni;
	//constructores
	public Producto(){}
	public Producto(String codigo_P,String nombre_P, String categoria, int stock, String marca, double precio_uni){

		Codigo_P=codigo_P;
		Nombre_P=nombre_P;
		Categoria=categoria;
		Stock=stock;
		Marca=marca;
		Precio_uni=precio_uni;
	}
}

