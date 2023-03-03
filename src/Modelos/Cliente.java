package Modelos;
/**
 * @author Anthony_Lima
 * @date 18/02/2023
 */
public class Cliente{
	//datos de la clase
	public String Cedula;
	public String Apellidos;
	public String Nombres;
	public String Ciudad;
	public String Direccion;
	public String Celular;
	//constructores
	public Cliente(){} //constructor vacio
	public Cliente(String cedula, String apellidos, String nombres, String ciudad, String direccion, String celular) {
		this.Cedula = cedula;
		this.Apellidos = apellidos;
		this.Nombres = nombres;
		this.Ciudad= ciudad;
		this.Direccion = direccion;
		this.Celular= celular;
	}
}