package Vistas;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.SwingConstants;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import Controladores.cProducto;
import Modelos.Producto;

import java.awt.Point;
import java.io.IOException;

import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTable;



public class panelProducto extends JPanel {
	//datos globales
	public cProducto list=new cProducto();  //  @jve:decl-index=0:
	String ced=""; //para editar  //  @jve:decl-index=0:

	private static final long serialVersionUID = 1L;
	private JButton btGuardar = null;
	private JPanel paneldatos = null;
	private JLabel lbcodigo = null;
	private JLabel lbnombre = null;
	private JLabel lbcategria = null;
	private JTextField txtCodigo = null;
	private JTextField txtNombre = null;
	private JTextField txtCategoria = null;
	private JLabel IbStock = null;
	private JTextField txtMarca = null;
	private JLabel lbmarca = null;
	private JLabel lbpreciounitario = null;
	private JTextField txtPrecio_Uni = null;
	private JButton btNuevo = null;
	private JButton btBuscar = null;
	private JLabel lbTitulo = null;
	private JScrollPane ScrollPane = null;
	private JTable tabla = null;
	private JPanel panelBuscar = null;
	private JLabel lbBuscar = null;
	private JTextField txtDato = null;
	private JButton btListar = null;
	private JPanel panelBotones = null;
	private JButton btEliminar = null;
	private JButton btEditar = null;
	private JButton btCancelar = null;
	private JTextField txtStock = null;


	/**********************METODOS PERSONALIZADOS ********************/
	/*
	 * Leer datos del formulario y guardar en un objeto
	 */
    public Producto leer()
    {
    	Producto ob=null;
        if(form_validado()){
            ob=new Producto();
            ob.Codigo_P=txtCodigo.getText();
            ob.Nombre_P=txtNombre.getText();
            ob.Categoria=txtCategoria.getText();
            ob.Stock=Integer.parseInt(txtStock.getText());
            ob.Marca=txtMarca.getText();
            ob.Precio_uni=Double.parseDouble(txtPrecio_Uni.getText());
            System.out.print(ob.toString());
        }
        return ob;
    }

    /*
     * Validar formulario
     */
    public boolean form_validado(){
        boolean ok=true;
        String men="Campos con errores";
        //validar requerido
        if(!Validaciones.esRequerido(txtCodigo)){
            ok=false;
            men += ", Codigo";
        }

        if(!Validaciones.esRequerido(txtNombre)){
            ok=false;
            men += ", Nombre";
        }

        if(!Validaciones.esLetras(txtCategoria)){
            ok=false;
            men += ", Categoria";
        }

        if(!Validaciones.esFlotante(txtStock)){
            ok=false;
            men += ", Stock";
        }

        if(!Validaciones.esLetras(txtMarca)){
            ok=false;
            men += ", Marca";
        }

        if(!Validaciones.esFlotante(txtPrecio_Uni)){
            ok=false;
            men += ", Precio Unitario";
        }

        if(!ok)frmPrincipal.lbMensaje.setText(men);
        else frmPrincipal.lbMensaje.setText("");
        //validar más controles
        return ok;
    }

    /*
     * Metodo para limpiar cajas de texto
     */
    public void limpiar_textos()
    {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtCategoria.setText("");
        txtStock.setText("");
        txtMarca.setText("");
        txtPrecio_Uni.setText("");
        txtCodigo.requestFocus();  //envia curso o enfoque a la caja de texto codigo
        frmPrincipal.lbMensaje.setText("");
    }

    /*
     * quitar validaciones
     */

    public void quitar_validaciones(){
    	Validaciones.pinta_text(txtCodigo);
    	Validaciones.pinta_text(txtNombre);
    	Validaciones.pinta_text(txtCategoria);
    	Validaciones.pinta_text(txtStock);
    	Validaciones.pinta_text(txtMarca);
    	Validaciones.pinta_text(txtPrecio_Uni);
    	frmPrincipal.lbMensaje.setText("");
    }

    /*
     * Ver registro
     */
    public void ver_registro(int pos)
    {
        if(pos>=0 && pos<list.Count())
        {
            Producto ob=list.getProducto(pos);
            txtCodigo.setText(ob.Codigo_P);
            txtNombre.setText(ob.Nombre_P);
            txtCategoria.setText(ob.Categoria);
            txtStock.setText(ob.Stock+"");
            txtMarca.setText(ob.Marca);
            txtPrecio_Uni.setText(ob.Precio_uni+"");
        }
    }

    /*
     * Buscar datos segun cedula
     */
    public void buscar(){
    	try{
            int pos=list.localizar(txtCodigo.getText());
            if (pos>-1)
            {
                ver_registro(pos);
            }
            else frmPrincipal.lbMensaje.setText("Registro no encontrado");
        }catch(Exception ex){
        	frmPrincipal.lbMensaje.setText(ex.getMessage());
        }
    }

    /*
     * Guardar estudiante, cuando es nuevo o se modifica un existente
     */
    public void guardar(){
    	Producto ob=leer();
		try{
			if(ob!=null){
				if (ced.equals("")){//guardar un nuevo producto
					list.nuevo(ob);
				}
				else{//guardar datos de producto editado
					list.modificar(ob,ced);
				}
				frmPrincipal.lbMensaje.setText("Registro guardado exitosamente");
				list.guardar(); //guarda en el archivo csv
				tabla.setModel(list.getTabla());
				//deshabilitar textos
		        habilitar_textos(false);
		        //habilitar botones
		        habilitar_botones(true);
			}
		}catch(Exception ex){
			frmPrincipal.lbMensaje.setText(ex.getMessage());
			System.out.println(ex.getMessage());
		}
    }

    /*
     * Buscar datos por criterios cedula y apellido
     */
    public void buscar_varios()
    {
        try{
            cProducto p=list.buscar_Codigo_P(txtDato.getText()); //busca por codigo
            if(p.Count()==0)p=list.buscar_nombre(txtDato.getText()); //buscar por nombre
            tabla.setModel(p.getTabla());
        }catch(Exception ex){frmPrincipal.lbMensaje.setText(ex.getMessage());}
    }

    /*
     * Eliminar datos de un producto
     */
    public void eliminar(){
    	try{
			list.eliminar(txtCodigo.getText());
			list.guardar();
			tabla.setModel(list.getTabla());
			frmPrincipal.lbMensaje.setText("Registro eliminado");
        }catch(Exception ex){frmPrincipal.lbMensaje.setText(ex.getMessage());}
    }


    /*
     * Habilitar o desabilitar textos
     */
    public void habilitar_textos(Boolean ok)
    {
    	txtCodigo.setEditable(ok);
        txtNombre.setEditable(ok);
        txtCategoria.setEditable(ok);
        txtStock.setEditable(ok);
        txtMarca.setEditable(ok);
        txtPrecio_Uni.setEditable(ok);
    }

    /*
     * Habilitar o desabilitar botones
     */

    public void habilitar_botones(Boolean ok)
    {
        btNuevo.setEnabled(ok);
        btEditar.setEnabled(ok);
        //btBuscar.setEnabled(ok);
        btEliminar.setEnabled(ok);

        //hacen lo contrario de los otros botones
        btGuardar.setEnabled(!ok);
        btCancelar.setEnabled(!ok);
    }


    /**********************METODOS AUTOGENERADOS******************/

	/**
	 * This is the default constructor
	 * @throws IOException
	 */
	public panelProducto() throws IOException {
		super();
		initialize();
		list.leer();
		//formato de la tabla
		tabla.getTableHeader().setBackground(new Color(100, 200, 200));
        tabla.getTableHeader().setForeground(Color.BLACK);

        //desabilitar textos
        habilitar_textos(false);
        //habilitar botones
        habilitar_botones(true);

	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		lbTitulo = new JLabel();
		lbTitulo.setBounds(new Rectangle(14, 15, 748, 35));
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 14));
		lbTitulo.setForeground(Color.blue);
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
		lbTitulo.setText("Gestión de Productos");
		lbTitulo.setBackground(Color.white);
		this.setSize(785, 661);
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setOpaque(true);
		this.add(getPaneldatos(), null);
		this.add(lbTitulo, null);
		this.add(getScrollPane(), null);
		this.add(getPanelBuscar(), null);
		this.add(getPanelBotones(), null);
	}

	/**
	 * This method initializes btGuardar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtGuardar() {
		if (btGuardar == null) {
			btGuardar = new JButton();
			btGuardar.setIcon(new ImageIcon(getClass().getResource("/Recursos/save.png")));
			btGuardar.setLocation(new Point(39, 152));
			btGuardar.setSize(new Dimension(120, 40));
			btGuardar.setText("Guardar");
			btGuardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e){
					guardar();

				}
			});
		}
		return btGuardar;
	}

	/**
	 * This method initializes paneldatos
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPaneldatos() {
		if (paneldatos == null) {
			lbpreciounitario = new JLabel();
			lbpreciounitario.setBounds(new Rectangle(15, 189, 94, 25));
			lbpreciounitario.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbpreciounitario.setText("Precio Unitario:");
			lbmarca = new JLabel();
			lbmarca.setBounds(new Rectangle(15, 150, 92, 25));
			lbmarca.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbmarca.setText("Marca:");
			IbStock = new JLabel();
			IbStock.setBounds(new Rectangle(15, 113, 81, 25));
			IbStock.setFont(new Font("Dialog", Font.PLAIN, 12));
			IbStock.setText("Stock:");
			lbcategria = new JLabel();
			lbcategria.setBounds(new Rectangle(15, 84, 82, 24));
			lbcategria.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbcategria.setText("Categoría:");
			lbnombre = new JLabel();
			lbnombre.setBounds(new Rectangle(15, 54, 82, 23));
			lbnombre.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbnombre.setText("Nombre:");
			lbcodigo = new JLabel();
			lbcodigo.setText("Código:");
			lbcodigo.setLocation(new Point(15, 27));
			lbcodigo.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbcodigo.setName("");
			lbcodigo.setSize(new Dimension(80, 20));
			paneldatos = new JPanel();
			paneldatos.setLayout(null);
			paneldatos.setBounds(new Rectangle(15, 53, 546, 242));
			paneldatos.setBorder(BorderFactory.createTitledBorder(null, "Datos del producto", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			paneldatos.add(lbcodigo, null);
			paneldatos.add(lbnombre, null);
			paneldatos.add(lbcategria, null);
			paneldatos.add(getTxtCodigo(), null);
			paneldatos.add(getTxtNombre(), null);
			paneldatos.add(getTxtCategoria(), null);
			paneldatos.add(IbStock, null);
			paneldatos.add(getTxtMarca(), null);

			paneldatos.add(lbmarca, null);
			paneldatos.add(lbpreciounitario, null);

			paneldatos.add(getTxtPrecio_Uni(), null);
			paneldatos.add(getBtBuscar(), null);
			paneldatos.add(getTxtStock(), null);
		}
		return paneldatos;
	}

	/**
	 * This method initializes txtCodigo
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCodigo() {
		if (txtCodigo == null) {
			txtCodigo = new JTextField();
			txtCodigo.setLocation(new Point(181, 24));
			txtCodigo.setPreferredSize(new Dimension(5, 25));
			txtCodigo.setFont(new Font("Dialog", Font.PLAIN, 12));
			txtCodigo.setSize(new Dimension(176, 25));
			txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esRequerido(txtCodigo)) Validaciones.pinta_text(txtCodigo);
				}
			});
		}
		return txtCodigo;
	}

	/**
	 * This method initializes txtNombre
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setSize(new Dimension(330, 25));
			txtNombre.setLocation(new Point(181, 58));
			txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esRequerido(txtNombre)) Validaciones.pinta_text(txtNombre);
				}
			});
		}
		return txtNombre;
	}

	/**
	 * This method initializes txtCategoria
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCategoria() {
		if (txtCategoria == null) {
			txtCategoria = new JTextField();
			txtCategoria.setLocation(new Point(181, 87));
			txtCategoria.setSize(new Dimension(330, 25));
			txtCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esLetras(txtCategoria)) Validaciones.pinta_text(txtCategoria);
				}
			});
		}
		return txtCategoria;
	}

	/**
	 * This method initializes txtMarca
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtMarca() {
		if (txtMarca == null) {
			txtMarca = new JTextField();
			txtMarca.setLocation(new Point(181, 148));
			txtMarca.setSize(new Dimension(330, 25));
			txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esRequerido(txtMarca)) Validaciones.pinta_text(txtMarca);
				}
			});
		}
		return txtMarca;
	}

	/**
	 * This method initializes txtPrecio_Uni
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtPrecio_Uni() {
		if (txtPrecio_Uni == null) {
			txtPrecio_Uni = new JTextField();
			txtPrecio_Uni.setLocation(new Point(181, 187));
			txtPrecio_Uni.setSize(new Dimension(330, 25));
		}
		return txtPrecio_Uni;
	}

	/**
	 * This method initializes btNuevo
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtNuevo() {
		if (btNuevo == null) {
			btNuevo = new JButton();
			btNuevo.setIcon(new ImageIcon(getClass().getResource("/Recursos/nuevo.png")));
			btNuevo.setHorizontalAlignment(SwingConstants.LEFT);
			btNuevo.setHorizontalTextPosition(SwingConstants.RIGHT);
			btNuevo.setLocation(new Point(39, 11));
			btNuevo.setSize(new Dimension(120, 40));
			btNuevo.setText("Nuevo");
			btNuevo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					quitar_validaciones();
					limpiar_textos();
					ced=""; //nuevo
					//habilitar textos
			        habilitar_textos(true);
			        //deshabilitar botones
			        habilitar_botones(false);

				}
			});
		}
		return btNuevo;
	}

	/**
	 * This method initializes btBuscar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtBuscar() {
		if (btBuscar == null) {
			btBuscar = new JButton();
			btBuscar.setText("Buscar");
			btBuscar.setBounds(new Rectangle(365, 13, 114, 40));
			btBuscar.setIcon(new ImageIcon(getClass().getResource("/Recursos/buscar.png")));
			btBuscar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					buscar();
				}
			});
		}
		return btBuscar;
	}

	/**
	 * This method initializes ScrollPane
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getScrollPane() {
		if (ScrollPane == null) {
			ScrollPane = new JScrollPane();
			ScrollPane.setBounds(new Rectangle(15, 355, 751, 293));
			ScrollPane.setViewportView(getTabla());
		}
		return ScrollPane;
	}

	/**
	 * This method initializes tabla
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getTabla() {
		if (tabla == null) {
			tabla = new JTable();
			tabla.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					int pos= tabla.getSelectedRow();
				    if(pos>=0 && pos <list.Count())
				       ver_registro(pos);
				}
			});
			tabla.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					int pos= tabla.getSelectedRow();
				    if(pos>=0 && pos <list.Count())
				       ver_registro(pos);
				}
			});
		}
		return tabla;
	}

	/**
	 * This method initializes panelBuscar
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelBuscar() {
		if (panelBuscar == null) {
			lbBuscar = new JLabel();
			lbBuscar.setBounds(new Rectangle(12, 12, 124, 27));
			lbBuscar.setText("Dato a Buscar:");
			panelBuscar = new JPanel();
			panelBuscar.setLayout(null);
			panelBuscar.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			panelBuscar.setBackground(SystemColor.control);
			panelBuscar.setSize(new Dimension(546, 47));
			panelBuscar.setLocation(new Point(15, 303));
			panelBuscar.add(lbBuscar, null);
			panelBuscar.add(getTxtDato(), null);
		}
		return panelBuscar;
	}

	/**
	 * This method initializes txtDato
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtDato() {
		if (txtDato == null) {
			txtDato = new JTextField();
			txtDato.setBounds(new Rectangle(145, 13, 361, 26));
			txtDato.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					buscar_varios();
				}
			});
		}
		return txtDato;
	}

	/**
	 * This method initializes btListar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtListar() {
		if (btListar == null) {
			btListar = new JButton();
			btListar.setText("Listar");
			btListar.setLocation(new Point(39, 246));
			btListar.setSize(new Dimension(120, 40));
			btListar.setIcon(new ImageIcon(getClass().getResource("/Recursos/tabla.png")));
			btListar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					tabla.setModel(panelProducto.this.list.getTabla());
					tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
					frmPrincipal.lbMensaje.setText("");
				}
			});
		}
		return btListar;
	}

	/**
	 * This method initializes panelBotones
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(null);
			panelBotones.setBounds(new Rectangle(570, 55, 193, 295));
			panelBotones.add(getBtNuevo(), null);
			panelBotones.add(getBtGuardar(), null);
			panelBotones.add(getBtListar(), null);
			panelBotones.add(getBtEliminar(), null);
			panelBotones.add(getBtEditar(), null);
			panelBotones.add(getBtCancelar(), null);
		}
		return panelBotones;
	}

	/**
	 * This method initializes btEliminar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton();
			btEliminar.setText("Eliminar");
			btEliminar.setSize(new Dimension(120, 40));
			btEliminar.setLocation(new Point(39, 199));
			btEliminar.setIcon(new ImageIcon(getClass().getResource("/Recursos/eliminar.png")));
			btEliminar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!txtCodigo.getText().trim().equals("")){
					    int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminiar este registro?","Sistema Facturacion", JOptionPane.YES_NO_OPTION);
					    if (respuesta == JOptionPane.YES_OPTION) {
					    	eliminar();
					    }
					}
					else frmPrincipal.lbMensaje.setText("Seleccione el registro a eliminar");
				}
			});
		}
		return btEliminar;
	}

	/**
	 * This method initializes btEditar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtEditar() {
		if (btEditar == null) {
			btEditar = new JButton();
			btEditar.setHorizontalTextPosition(SwingConstants.RIGHT);
			btEditar.setIcon(new ImageIcon(getClass().getResource("/Recursos/modificar.png")));
			btEditar.setText("Editar");
			btEditar.setLocation(new Point(39, 58));
			btEditar.setSize(new Dimension(120, 40));
			btEditar.setHorizontalAlignment(SwingConstants.LEFT);
			btEditar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (!txtCodigo.getText().trim().equals("")){
						ced=txtCodigo.getText().trim(); //captura la cédula antes de modificar
						txtCodigo.requestFocus();  //envia curso o enfoque a la caja de texto cedula
				        frmPrincipal.lbMensaje.setText("");
						//habilitar textos
				        habilitar_textos(true);
				        //deshabilitar botones
				        habilitar_botones(false);
				        quitar_validaciones();
					}
					else frmPrincipal.lbMensaje.setText("Seleccione o busque un registro a editar");
				}
			});
		}
		return btEditar;
	}

	/**
	 * This method initializes btCancelar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton();
			btCancelar.setHorizontalTextPosition(SwingConstants.RIGHT);
			btCancelar.setIcon(new ImageIcon(getClass().getResource("/Recursos/undo.png")));
			btCancelar.setText("Cancelar");
			btCancelar.setLocation(new Point(39, 105));
			btCancelar.setSize(new Dimension(120, 40));
			btCancelar.setHorizontalAlignment(SwingConstants.LEFT);
			btCancelar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//quitar validaciones
					quitar_validaciones();
					//desabilitar textos
			        habilitar_textos(false);
			        //habilitar botones
			        habilitar_botones(true);
			        //cargar registro anterior a la modificación
			        int pos=list.localizar(ced);
			        if(pos>=0)ver_registro(pos);
			        else limpiar_textos();
				}
			});
		}
		return btCancelar;
	}

	/**
	 * This method initializes txtStock
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtStock() {
		if (txtStock == null) {
			txtStock = new JTextField();
			txtStock.setBounds(new Rectangle(181, 119, 329, 22));
		}
		return txtStock;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
