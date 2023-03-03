package Modelos;
/**
 * @author Anthony_Lima
 * @date 18/02/2023
 */
public class Vendedor {
	//cedula_v, nombres_v, apellidos_v, telefono_v, ciudad_v.
	public String cedula;
	public String Nombres;
	public String apellidos;
	public String telefono;
	public String Ciudad;

	//Constructores
	public Vendedor(){}
	public Vendedor(String cedula, String Nombres, String apellidos, String telefono, String ciudad){
		this.cedula=cedula;
		this.Nombres=Nombres;
		this.apellidos=apellidos;
		this.telefono=telefono;
		this.Ciudad=ciudad;

	}


}
