package Vistas;

import javax.swing.ButtonGroup;
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
import javax.swing.JRadioButton;

import Controladores.cDetalle_FacturaV2;
import Controladores.cCliente;
import Controladores.cProducto;
import Modelos.Producto;
import Modelos.Detalle_Factura;
import Modelos.Cliente;

import java.awt.Point;
import java.io.IOException;

import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyEvent;



public class panelDetalle_FacturaV2 extends JPanel {
	//datos globales
	public cCliente listC=new cCliente();  //  @jve:decl-index=0:
	public cProducto listP=new cProducto();  //  @jve:decl-index=0:
	public cDetalle_FacturaV2 listD=new cDetalle_FacturaV2();  //  @jve:decl-index=0:
	String ced=""; //para editar  //  @jve:decl-index=0:

	private static final long serialVersionUID = 1L;
	private JButton btGuardar = null;
	private JPanel paneldatos = null;
	private JLabel lbcedula = null;
	private JLabel lbNombre_P = null;
	private JLabel lbStock = null;
	private JTextField txtCodigo_P = null;
	private JTextField txtNombre_P = null;
	private JTextField txtStock = null;
	private JLabel lbEstado = null;
	private JRadioButton rbPendiente = null;
	private JRadioButton rbEntregado = null;
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
	private JLabel lbPrecio_uni = null;
	private JTextField txtPrecio_uni = null;
	private JLabel lbCedula = null;
	private JTextField txtCedula = null;
	private JLabel lbCiudad = null;
	private JTextField txtCiudad = null;
	private JLabel lbDireccion = null;
	private JTextField txtDireccion = null;
	private JTextField txtCelular = null;
	private JLabel lbCelular = null;
	private JButton btBuscarCedula = null;
	/**********************METODOS PERSONALIZADOS ********************/
	/*
	 * Leer datos del formulario y guardar en un objeto
	 */
    public Detalle_Factura leerD()
    {
    	Detalle_Factura ob=null;
        if(form_validado()){
            ob=new Detalle_Factura();
            ob.Codigo_P=txtCodigo_P.getText();
            ob.Nombre_P=txtNombre_P.getText();
            ob.Stock=Integer.parseInt(txtStock.getText());
            ob.Precio_Uni=Double.parseDouble(txtPrecio_uni.getText());
            ob.Estado=rbPendiente.isSelected()?"Entregado":"Pendiente";
            ob.Cedula=txtCedula.getText();
            ob.Ciudad=txtCiudad.getText();
            ob.Direccion=txtDireccion.getText();
            ob.Celular=txtCelular.getText();
            System.out.print(ob.toString());
        }
        return ob;
    }
    public Cliente leerC()
    {
    	Cliente ob=null;
    	if (form_validado()){
    		ob.Cedula=txtCedula.getText();
    	}
    	return ob;
    }
    public Producto leerP()
    {
    	Producto ob=null;
    	if (form_validado()){
    		ob.Codigo_P=txtCodigo_P.getText();
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
        if(!Validaciones.esCedula(txtCodigo_P)){
            ok=false;
            men += ", Codigo Producto";
        }

        if(!Validaciones.esNumero(txtStock)){
            ok=false;
            men += ", Stock";
        }

        if(!Validaciones.esRequerido(txtNombre_P)){
            ok=false;
            men += ", Nombre Producto";
        }

        if(!Validaciones.esLetras(txtDireccion)){
            ok=false;
            men += ", Dirección";
        }
        if (!Validaciones.esPrecio(txtPrecio_uni)){
        	ok=false;
        	men += ", Precio Unitario";
        }
        if (!Validaciones.esCedula(txtCedula)){
        	ok=false;
        	men += ", Cedula";
        }
        if (!Validaciones.esLetras(txtCiudad)){
        	ok=false;
        	men += ", Ciudad";
        }
        if (!Validaciones.esTelefono(txtCelular)){
        	ok=false;
        	men += ", Celular";
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
    	txtCodigo_P.setText("");
        txtNombre_P.setText("");
        txtStock.setText("");
        txtPrecio_uni.setText("");
        txtCedula.setText("");
        txtCiudad.setText("");
        txtCelular.setText("");
        txtDireccion.setText("");
        txtCodigo_P.requestFocus();  //envia curso o enfoque a la caja de texto cedula
        frmPrincipal.lbMensaje.setText("");
    }

    /*
     * quitar validaciones
     */

    public void quitar_validaciones(){
    	Validaciones.pinta_text(txtCodigo_P);
    	Validaciones.pinta_text(txtNombre_P);
    	Validaciones.pinta_text(txtStock);
    	Validaciones.pinta_text(txtPrecio_uni);
    	Validaciones.pinta_text(txtCedula);
    	Validaciones.pinta_text(txtCiudad);
    	Validaciones.pinta_text(txtDireccion);
    	Validaciones.pinta_text(txtCelular);
    	frmPrincipal.lbMensaje.setText("");
    }

    /*
     * Ver registro
     */
    public void ver_registro(int pos)
    {
        if(pos>=0 && pos<listD.Count())
        {
            Detalle_Factura ob=listD.getDetalle_Factura(pos);
            txtCodigo_P.setText(ob.Codigo_P);
            txtNombre_P.setText(ob.Nombre_P);
            txtStock.setText(ob.Stock+"");
            txtPrecio_uni.setText(ob.Precio_Uni+"");
            if(ob.Estado.equals("Entregado"))
            	rbEntregado.setSelected(true);
            else rbPendiente.setSelected(true);
            txtCedula.setText(ob.Cedula);
            txtCiudad.setText(ob.Ciudad);
            txtDireccion.setText(ob.Direccion);
            txtCelular.setText(ob.Celular);
        }
    }
    public void ver_registroC (int pos)
    {
    	if (pos>=0 && pos<listC.Count())
    	{
    		Cliente ob=listC.getCliente(pos);
    		txtCedula.setText(ob.Cedula);
    		txtCiudad.setText(ob.Ciudad);
    		txtDireccion.setText(ob.Direccion);
    		txtCelular.setText(ob.Celular);
    	}
    }
    public void ver_registroP (int pos)
    {
    	if (pos>=0 && pos<listP.Count())
    	{
    		Producto ob=listP.getProducto(pos);
    		txtCodigo_P.setText(ob.Codigo_P);
    		txtNombre_P.setText(ob.Nombre_P);
    		txtPrecio_uni.setText(ob.Precio_uni+"");
    	}
    }
    /*
     * Buscar datos segun cedula
     */
    public void buscar(){
    	try{
            int pos=listD.localizar(txtCodigo_P.getText());
            if (pos>-1)
            {
                ver_registro(pos);
            }
            else frmPrincipal.lbMensaje.setText("Registro no encontrado");
        }catch(Exception ex){
        	frmPrincipal.lbMensaje.setText(ex.getMessage());
        }
    }
    public void buscarP(){
    	try{
            int pos=listP.localizar(txtCodigo_P.getText());
            if (pos>-1)
            {
                ver_registroP(pos);
            }
            else frmPrincipal.lbMensaje.setText("Registro no encontrado");
        }catch(Exception ex){
        	frmPrincipal.lbMensaje.setText(ex.getMessage());
        }
    }
    public void buscarC(){
    	try{
    		int pos=listC.localizar(txtCedula.getText());
    		if (pos>-1)
    		{
    			ver_registroC(pos);
    		}
    		else frmPrincipal.lbMensaje.setText("Registro no encontrado");
    	}catch (Exception ex){
    		frmPrincipal.lbMensaje.setText(ex.getMessage()) ;
    	}
    }

    /*
     * Guardar estudiante, cuando es nuevo o se modifica un existente
     */
    public void guardar(){
    	Detalle_Factura ob=leerD();
		try{
			if(ob!=null){
				if (ced.equals("")){//guardar un nuevo estudiante
					listD.nuevo(ob);
				}
				else{//guardar datos de estudiante editado
					listD.modificar(ob,ced);
				}
				frmPrincipal.lbMensaje.setText("Registro guardado exitosamente");
				listD.guardar(); //guarda en el archivo csv
				tabla.setModel(listD.getTabla());
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
            cDetalle_FacturaV2 p=listD.buscar_codigo_producto(txtDato.getText()); //busca por cedula
            if(p.Count()==0)p=listD.buscar_cedula(txtDato.getText()); //buscar por apellido
            tabla.setModel(p.getTabla());
        }catch(Exception ex){frmPrincipal.lbMensaje.setText(ex.getMessage());}
    }

    /*
     * Eliminar datos de un estudiante
     */
    public void eliminar(){
    	try{
			listD.eliminar(txtCodigo_P.getText());
			listD.guardar();
			tabla.setModel(listD.getTabla());
			frmPrincipal.lbMensaje.setText("Registro eliminado");
        }catch(Exception ex){frmPrincipal.lbMensaje.setText(ex.getMessage());}
    }


    /*
     * Habilitar o desabilitar textos
     */
    public void habilitar_textos(Boolean ok)
    {
    	txtCodigo_P.setEditable(ok);
        txtNombre_P.setEditable(ok);
        txtStock.setEditable(ok);
        txtPrecio_uni.setEditable(ok);
        txtCedula.setEditable(ok);
        txtCiudad.setEditable(ok);
        txtDireccion.setEditable(ok);
        txtCelular.setEditable(ok);

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
	public panelDetalle_FacturaV2() throws IOException {
		super();
		initialize();
		listD.leer();
		listC.leer();
		listP.leer();
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
		lbTitulo.setBounds(new Rectangle(14, 15, 935, 35));
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 14));
		lbTitulo.setForeground(Color.blue);
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
		lbTitulo.setText("Detalles de Factura");
		lbTitulo.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
		lbTitulo.setBackground(SystemColor.text);
		this.setSize(960, 660);
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
			lbCelular = new JLabel();
			lbCelular.setText("No. Celular:");
			lbCelular.setSize(new Dimension(112, 25));
			lbCelular.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbCelular.setLocation(new Point(385, 131));
			lbDireccion = new JLabel();
			lbDireccion.setText("Dirección:");
			lbDireccion.setSize(new Dimension(112, 25));
			lbDireccion.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbDireccion.setLocation(new Point(385, 95));
			lbCiudad = new JLabel();
			lbCiudad.setText("Ciudad:");
			lbCiudad.setSize(new Dimension(112, 25));
			lbCiudad.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbCiudad.setLocation(new Point(385, 59));
			lbCedula = new JLabel();
			lbCedula.setText("Cédula del Cliente:");
			lbCedula.setSize(new Dimension(112, 25));
			lbCedula.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbCedula.setLocation(new Point(385, 23));
			lbPrecio_uni = new JLabel();
			lbPrecio_uni.setText("Precio Unitario");
			lbPrecio_uni.setSize(new Dimension(112, 25));
			lbPrecio_uni.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbPrecio_uni.setLocation(new Point(14, 131));
			lbEstado = new JLabel();
			lbEstado.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbEstado.setLocation(new Point(385, 161));
			lbEstado.setSize(new Dimension(112, 25));
			lbEstado.setText("Estado");
			lbStock = new JLabel();
			lbStock.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbStock.setLocation(new Point(14, 95));
			lbStock.setSize(new Dimension(112, 25));
			lbStock.setText("Cantidad:");
			lbNombre_P = new JLabel();
			lbNombre_P.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbNombre_P.setLocation(new Point(14, 59));
			lbNombre_P.setSize(new Dimension(112, 25));
			lbNombre_P.setText("Nombre Producto");
			lbcedula = new JLabel();
			lbcedula.setText("Codigo Producto:");
			lbcedula.setLocation(new Point(14, 23));
			lbcedula.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbcedula.setSize(new Dimension(112, 25));
			paneldatos = new JPanel();
			paneldatos.setLayout(null);
			paneldatos.setBorder(BorderFactory.createTitledBorder(null, "Datos de Detalles de Factura", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			paneldatos.setSize(new Dimension(737, 242));
			paneldatos.setLocation(new Point(14, 55));
			paneldatos.add(lbcedula, null);
			paneldatos.add(lbNombre_P, null);
			paneldatos.add(lbStock, null);
			paneldatos.add(getTxtCodigo_P(), null);
			paneldatos.add(getTxtNombre_P(), null);
			paneldatos.add(getTxtStock(), null);
			paneldatos.add(lbEstado, null);
			paneldatos.add(getRbPendiente(), null);
			paneldatos.add(getRbEntregado(), null);
			ButtonGroup grupoGenero = new ButtonGroup();
			grupoGenero.add(rbPendiente);
			grupoGenero.add(rbEntregado);


			paneldatos.add(getBtBuscar(), null);
			paneldatos.add(lbPrecio_uni, null);
			paneldatos.add(getTxtPrecio_uni(), null);
			paneldatos.add(lbCedula, null);
			paneldatos.add(getTxtCedula(), null);
			paneldatos.add(lbCiudad, null);
			paneldatos.add(getTxtCiudad(), null);
			paneldatos.add(lbDireccion, null);
			paneldatos.add(getTxtDireccion(), null);
			paneldatos.add(getTxtCelular(), null);
			paneldatos.add(lbCelular, null);
			paneldatos.add(getBtBuscarCedula(), null);
		}
		return paneldatos;
	}

	/**
	 * This method initializes txtCodigo_P
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCodigo_P() {
		if (txtCodigo_P == null) {
			txtCodigo_P = new JTextField();
			txtCodigo_P.setLocation(new Point(128, 23));
			txtCodigo_P.setPreferredSize(new Dimension(5, 25));
			txtCodigo_P.setFont(new Font("Dialog", Font.PLAIN, 12));
			txtCodigo_P.setSize(new Dimension(228, 25));
			txtCodigo_P.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esCodigo(txtCodigo_P)) Validaciones.pinta_text(txtCodigo_P);
				}
			});
		}
		return txtCodigo_P;
	}

	/**
	 * This method initializes txtNombre_P
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNombre_P() {
		if (txtNombre_P == null) {
			txtNombre_P = new JTextField();
			txtNombre_P.setSize(new Dimension(228, 25));
			txtNombre_P.setLocation(new Point(128, 59));
			txtNombre_P.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esRequerido(txtNombre_P)) Validaciones.pinta_text(txtNombre_P);
				}
			});
		}
		return txtNombre_P;
	}

	/**
	 * This method initializes txtStock
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtStock() {
		if (txtStock == null) {
			txtStock = new JTextField();
			txtStock.setLocation(new Point(128, 95));
			txtStock.setSize(new Dimension(228, 25));
			txtStock.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esNumero(txtStock)) Validaciones.pinta_text(txtStock);
				}
			});
		}
		return txtStock;
	}

	/**
	 * This method initializes rbPendiente
	 *
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getRbPendiente() {
		if (rbPendiente == null) {
			rbPendiente = new JRadioButton();
			rbPendiente.setText("Entregado");
			rbPendiente.setSize(new Dimension(91, 24));
			rbPendiente.setLocation(new Point(582, 162));
		}
		return rbPendiente;
	}

	/**
	 * This method initializes rbEntregado
	 *
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getRbEntregado() {
		if (rbEntregado == null) {
			rbEntregado = new JRadioButton();
			rbEntregado.setText("Pendiente");
			rbEntregado.setSelected(true);
			rbEntregado.setBounds(new Rectangle(498, 162, 91, 24));
		}
		return rbEntregado;
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
			btBuscar.setText("Buscar Producto");
			btBuscar.setBounds(new Rectangle(11, 193, 166, 40));
			btBuscar.setIcon(new ImageIcon(getClass().getResource("/Recursos/buscar.png")));
			btBuscar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					buscar();
					buscarP();
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
			ScrollPane.setLocation(new Point(14, 355));
			ScrollPane.setViewportView(getTabla());
			ScrollPane.setSize(new Dimension(935, 293));
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
				    if(pos>=0 && pos <listD.Count())
				       ver_registro(pos);
				}
			});
			tabla.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					int pos= tabla.getSelectedRow();
				    if(pos>=0 && pos <listD.Count())
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
			panelBuscar.setSize(new Dimension(738, 47));
			panelBuscar.setLocation(new Point(14, 303));
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
					tabla.setModel(panelDetalle_FacturaV2.this.listD.getTabla());
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
			panelBotones.setBounds(new Rectangle(756, 55, 191, 295));
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
					if(!txtCodigo_P.getText().trim().equals("")){
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
					if (!txtCodigo_P.getText().trim().equals("")){
						ced=txtCodigo_P.getText().trim(); //captura la cédula antes de modificar
						txtCodigo_P.requestFocus();  //envia curso o enfoque a la caja de texto cedula
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
			        int pos=listD.localizar(ced);
			        if(pos>=0)ver_registro(pos);
			        else limpiar_textos();
				}
			});
		}
		return btCancelar;
	}

	/**
	 * This method initializes txtPrecio_uni
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtPrecio_uni() {
		if (txtPrecio_uni == null) {
			txtPrecio_uni = new JTextField();
			txtPrecio_uni.setLocation(new Point(128, 131));
			txtPrecio_uni.setSize(new Dimension(228, 25));
			txtPrecio_uni.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esPrecio(txtPrecio_uni)) Validaciones.pinta_text(txtPrecio_uni);
				}
			});
		}
		return txtPrecio_uni;
	}

	/**
	 * This method initializes txtCedula
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCedula() {
		if (txtCedula == null) {
			txtCedula = new JTextField();
			txtCedula.setLocation(new Point(495, 23));
			txtCedula.setSize(new Dimension(228, 25));
			txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esCedula(txtCedula)) Validaciones.pinta_text(txtCedula);
				}
			});
		}
		return txtCedula;
	}

	/**
	 * This method initializes txtCiudad
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCiudad() {
		if (txtCiudad == null) {
			txtCiudad = new JTextField();
			txtCiudad.setLocation(new Point(495, 59));
			txtCiudad.setSize(new Dimension(228, 25));
			txtCiudad.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esLetras(txtCiudad)) Validaciones.pinta_text(txtCiudad);
				}
			});
		}
		return txtCiudad;
	}

	/**
	 * This method initializes txtDireccion
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtDireccion() {
		if (txtDireccion == null) {
			txtDireccion = new JTextField();
			txtDireccion.setLocation(new Point(495, 95));
			txtDireccion.setSize(new Dimension(228, 25));
			txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esLetras(txtDireccion)) Validaciones.pinta_text(txtDireccion);
				}
			});
		}
		return txtDireccion;
	}

	/**
	 * This method initializes txtCelular
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCelular() {
		if (txtCelular == null) {
			txtCelular = new JTextField();
			txtCelular.setLocation(new Point(495, 131));
			txtCelular.setSize(new Dimension(228, 25));
			txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esTelefono(txtCelular)) Validaciones.pinta_text(txtCelular);
				}
			});
		}
		return txtCelular;
	}

	/**
	 * This method initializes btBuscarCedula
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtBuscarCedula() {
		if (btBuscarCedula == null) {
			btBuscarCedula = new JButton();
			btBuscarCedula.setText("Buscar Cliente");
			btBuscarCedula.setLocation(new Point(385, 193));
			btBuscarCedula.setSize(new Dimension(166, 40));
			btBuscarCedula.setIcon(new ImageIcon(getClass().getResource("/Recursos/buscar.png")));
			btBuscarCedula.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					buscarC();
				}
			});
		}
		return btBuscarCedula;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
