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

import Controladores.cFactura;
import Modelos.Factura;
import Modelos.Cliente;
import Controladores.cCliente;
import java.awt.Point;
import java.io.IOException;

import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import javax.swing.JTree;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import java.awt.Scrollbar;
import javax.swing.JToolBar;
import javax.swing.JPasswordField;
import java.awt.Frame;
import javax.swing.JDialog;
import java.awt.BorderLayout;



public class panelFacturar extends JPanel {
	//datos globales
	public cCliente listC=new cCliente();  //  @jve:decl-index=0:
	public cFactura listF=new cFactura();  //  @jve:decl-index=0:
	String ced=""; //para editar  //  @jve:decl-index=0:
	//int cod;
	private static final long serialVersionUID = 1L;
	private JButton btGuardar = null;
	private JPanel panelfactura = null;
	private JLabel lbcedula = null;
	private JLabel lbapellido = null;
	private JLabel lbbombre = null;
	private JTextField txtCedula = null;
	private JTextField txtApellidos = null;
	private JTextField txtNombres = null;
	private JLabel jbNum_fac = null;
	private JRadioButton rbDeposito = null;
	private JRadioButton rbEfectivo = null;
	private JLabel jbFecha_emision = null;
	private JLabel jbTotal_factura = null;
	private JTextField txtFecha_emision = null;


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
	private JLabel jbintruccion = null;
	private JLabel jbForma_pago = null;
	private JTextField txtTotal_fac = null;
	private JLabel jbCedulaV = null;
	private JLabel lbCodigo_P = null;
	private JLabel lbNombre_P = null;
	private JTextField txtCedulaV = null;
	private JTextField txtCodigo_P = null;
	private JTextField txtNombre_P = null;
	private JLabel lbMostrarF = null;
	private JLabel lbMostrarnF = null;
	/**********************METODOS PERSONALIZADOS ********************/
	/*
	 * Leer datos del formulario y guardar en un objeto
	 */
	/*public panelFacturar() {
		super();
		initialize();
		//leerdatos de estudiantes y detalles desde archivos
		try {
			listF.leerF();
			listDf.leerDf();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
    public Factura leerF()
    {
    	Factura ob=null;
        if(form_validado()){
            ob=new Factura();
            //ob.Num_factura=Integer.parseInt(txtNum_factura.getText());
            //ob.Nombre_fac=txtNombre_fac.getText();
            ob.Cedula=txtCedula.getText();
            ob.Apellido=txtApellidos.getText();
            ob.Nombre=txtNombres.getText();
            ob.Fecha_emision=txtFecha_emision.getText();
            ob.Total_factura=Double.parseDouble(txtTotal_fac.getText());
            ob.Forma_pago=rbDeposito.isSelected()?"Efectivo":"Deposito";
            ob.CedulaV=txtCedulaV.getText();
            ob.Codigo_P=txtCodigo_P.getText();
            ob.Nombre_P=txtNombre_P.getText();
            System.out.print(ob.toString());
        }
        return ob;
    }

    public Cliente leer()
    {
    	Cliente ob=null;
    	if(form_validado()){
    		ob=new Cliente();
    		ob.Cedula=txtCedula.getText();
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
        if(!Validaciones.esCedula(txtCedula)){
            ok=false;
            men += ", Cedula";
        }

        if(!Validaciones.esLetras(txtNombres)){
            ok=false;
            men += ", Nombre";
        }

        if(!Validaciones.esLetras(txtApellidos)){
            ok=false;
            men += ", Apellido";
        }

        /*if(!Validaciones.esNumero(txtNum_factura)){
            ok=false;
            men += ", Número Factura";
        }
        if(!Validaciones.esLetras(txtNombre_fac)){
            ok=false;
            men += ", Nombre Factura";
        }
        */
        if(!Validaciones.esFecha(txtFecha_emision)){
            ok=false;
            men += ", Fecha Emision";
        }
        if(!Validaciones.esPrecio(txtTotal_fac)){
            ok=false;
            men += ", Total Factura";
        }
        if(!Validaciones.esCedula(txtCedulaV)){
            ok=false;
            men += ", Cedula Vendedor";
        }
        if(!Validaciones.esCedula(txtCodigo_P)){
            ok=false;
            men += ", Codigo Producto";
        }
        if(!Validaciones.esLetras(txtNombre_P)){
            ok=false;
            men += ", Nombre Producto";
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
        txtCedula.setText("");
        txtApellidos.setText("");
        txtNombres.setText("");
        lbMostrarF.setText("");
        lbMostrarnF.setText("");
        txtFecha_emision.setText("");
        txtTotal_fac.setText("");
        txtCedulaV.setText("");
        txtCodigo_P.setText("");
        txtNombre_P.setText("");
        txtCedula.requestFocus();  //envia curso o enfoque a la caja de texto cedula
        frmPrincipal.lbMensaje.setText("");
    }

    /*
     * quitar validaciones
     */

    public void quitar_validaciones(){
    	Validaciones.pinta_text(txtCedula);
    	Validaciones.pinta_text(txtApellidos);
    	Validaciones.pinta_text(txtNombres);
    	//Validaciones.pinta_text(txtNum_factura);
    	//Validaciones.pinta_text(txtNombre_fac);
    	Validaciones.pinta_text(txtFecha_emision);
    	Validaciones.pinta_text(txtTotal_fac);
    	Validaciones.pinta_text(txtCedulaV);
    	Validaciones.pinta_text(txtCodigo_P);
    	Validaciones.pinta_text(txtNombre_P);
    	frmPrincipal.lbMensaje.setText("");
    }

    /*
     * Ver registro
     */
    public void ver_registroF(int pos)
    {
        if(pos>=0 && pos<listF.Count())
        {

        	Factura ob=listF.getFactura(pos);
        	lbMostrarF.setText(ob.Num_factura+"");
            lbMostrarnF.setText(ob.Nombre_fac);
            txtCedula.setText(ob.Cedula);
            txtApellidos.setText(ob.Apellido);
            txtNombres.setText(ob.Nombre);
            txtFecha_emision.setText(ob.Fecha_emision);
            txtTotal_fac.setText(ob.Total_factura+"");
            if(ob.Forma_pago.equals("Efectivo"))
            	rbEfectivo.setSelected(true);
            else rbDeposito.setSelected(true);
            txtCedulaV.setText(ob.CedulaV);
            txtCodigo_P.setText(ob.Codigo_P);
            txtNombre_P.setText(ob.Nombre_P);
        }
    }
    public void ver_registroC(int pos)
    	{
    		if(pos>=0 && pos<listC.Count())
    		{
    			Cliente ob=listC.getCliente(pos);
    			txtCedula.setText(ob.Cedula);
    			txtApellidos.setText(ob.Apellidos);
    			txtNombres.setText(ob.Nombres);
    		}
    	}
    /*
     * Buscar datos segun cedula
     */
    public void buscar(){
    	try{
            int pos=listF.localizar(txtCedula.getText());
            if (pos>-1)

            {
                ver_registroF(pos);
            }

            else frmPrincipal.lbMensaje.setText("Registro no encontrado");
        }catch(Exception ex){
        	frmPrincipal.lbMensaje.setText(ex.getMessage());
        }
    }
   /* public void buscarDf(){
    	try{
            int pos=listDf.localizar(Integer.parseInt(txtCodigo_P.getText()));
            if (pos>-1)
            {
                ver_registroF(pos);
            }
            else frmPrincipal.lbMensaje.setText("Registro no encontrado");
        }catch(Exception ex){
        	frmPrincipal.lbMensaje.setText(ex.getMessage());
        }
    }

    */
    public void buscarC(){
    	try{
            int pos=listC.localizar(txtCedula.getText());
            if (pos>-1)

            {
                ver_registroC(pos);
            }
            else frmPrincipal.lbMensaje.setText("Registro no encontrado");
        }catch(Exception ex){
        	frmPrincipal.lbMensaje.setText(ex.getMessage());
        }
    }
    /*
     * Guardar Factura, cuando es nuevo o se modifica un existente
     */
    public void guardar(){
    	Factura ob=leerF();
		try{
			if(ob!=null){
				if (ced.equals("")){//guardar un nuevo estudiante
					listF.nuevo(ob);
				}
				else{//guardar datos de estudiante editado
					//int num=Integer.parseInt(txtCedula.getText());
					listF.modificar(ob,ced);
				}
				frmPrincipal.lbMensaje.setText("Registro guardado exitosamente");
				listF.guardar(); //guarda en el archivo csv
				tabla.setModel(listF.getTabla());
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
            cFactura p=listF.buscar_Num_factura(txtDato.getText()); //buscar por número de factura
            if(p.Count()==0)p=listF.buscar_cedula(txtDato.getText()); //buscar por cedula
            if  (p.Count ()==0) p=listF.buscar_cedulav(txtDato.getText());
            if (p.Count()==0) p=listF.buscar_apellido(txtDato.getText());
            if (p.Count()==0) p=listF.buscar_codigop(txtDato.getText());
            if (p.Count ()==0) p=listF.buscar_nombre(txtDato.getText());
            if (p.Count ()==0) p=listF.buscar_nombrep(txtDato.getText());
            tabla.setModel(p.getTabla());
        }catch(Exception ex){frmPrincipal.lbMensaje.setText(ex.getMessage());}
    }
    /*
     * Eliminar datos de un estudiante
     */
    public void eliminar(){
    	try{
			listF.eliminar(txtCedula.getText());
			//listDf.eliminar(Integer.parseInt(txtCodigo_P.getText()));
			listF.guardar();
			//listDf.guardar();
			tabla.setModel(listF.getTabla());
			//tabla.setModel(listDf.getTabla());
			frmPrincipal.lbMensaje.setText("Registro eliminado");
        }catch(Exception ex){frmPrincipal.lbMensaje.setText(ex.getMessage());}
    }


    /*
     * Habilitar o desabilitar textos
     */
    public void habilitar_textos(Boolean ok)
    {
    	txtCedula.setEditable(ok);
        txtApellidos.setEditable(ok);
        txtNombres.setEditable(ok);
        //txtNum_factura.setEditable(ok);
        //txtNombre_fac.setEditable(ok);
        txtFecha_emision.setEditable(ok);
        txtTotal_fac.setEditable(ok);
        txtCedulaV.setEditable(ok);
        txtCodigo_P.setEditable(ok);
        txtNombre_P.setEditable(ok);
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
	public panelFacturar() throws IOException {
		super();
		initialize();
		listF.leerF();
		listC.leer();
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
		lbTitulo.setBounds(new Rectangle(14, 15, 866, 35));
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 14));
		lbTitulo.setForeground(Color.blue);
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
		lbTitulo.setText("Gestión de Facturas");
		lbTitulo.setBackground(Color.white);
		this.setSize(895, 689);
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setOpaque(true);
		this.add(getPanelfactura(), null);
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
			btGuardar.setLocation(new Point(35, 151));
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
	 * This method initializes panelfactura
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelfactura() {
		if (panelfactura == null) {			lbMostrarnF = new JLabel();
lbMostrarnF.setText("Factura de Compra");
lbMostrarnF.setLocation(new Point(333, 20));
lbMostrarnF.setSize(new Dimension(112, 25));
lbMostrarF = new JLabel();
lbMostrarF.setText("N-0000");
lbMostrarF.setLocation(new Point(126, 20));
lbMostrarF.setSize(new Dimension(112, 25));
lbNombre_P = new JLabel();
			lbNombre_P.setText("Nombre de Producto:");
			lbNombre_P.setSize(new Dimension(122, 25));
			lbNombre_P.setLocation(new Point(333, 121));
			lbCodigo_P = new JLabel();
			lbCodigo_P.setText("Codigo Producto:");
			lbCodigo_P.setSize(new Dimension(122, 25));
			lbCodigo_P.setLocation(new Point(333, 90));
			jbCedulaV = new JLabel();
			jbCedulaV.setText("Cedula del Vendedor:");
			jbCedulaV.setSize(new Dimension(122, 25));
			jbCedulaV.setLocation(new Point(333, 59));
			jbForma_pago = new JLabel();
			jbForma_pago.setFont(new Font("Dialog", Font.PLAIN, 12));
			jbForma_pago.setLocation(new Point(333, 189));
			jbForma_pago.setSize(new Dimension(122, 25));
			jbForma_pago.setText("Forma de pago:");
			jbintruccion = new JLabel();
			jbintruccion.setBounds(new Rectangle(6, 172, 112, 16));
			jbintruccion.setFont(new Font("Dialog", Font.ITALIC, 12));
			jbintruccion.setText("(yyyy-mm-dd)");
			jbTotal_factura = new JLabel();
			jbTotal_factura.setFont(new Font("Dialog", Font.PLAIN, 12));
			jbTotal_factura.setLocation(new Point(333, 153));
			jbTotal_factura.setSize(new Dimension(122, 25));
			jbTotal_factura.setText("Total a pagar:         $");
			jbFecha_emision = new JLabel();
			jbFecha_emision.setFont(new Font("Dialog", Font.PLAIN, 12));
			jbFecha_emision.setLocation(new Point(6, 153));
			jbFecha_emision.setSize(new Dimension(112, 25));
			jbFecha_emision.setText("Fecha de Emision:");
			jbNum_fac = new JLabel();
			jbNum_fac.setFont(new Font("Dialog", Font.PLAIN, 12));
			jbNum_fac.setLocation(new Point(7, 20));
			jbNum_fac.setSize(new Dimension(112, 25));
			jbNum_fac.setText("Número de Factura:");
			lbbombre = new JLabel();
			lbbombre.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbbombre.setLocation(new Point(6, 121));
			lbbombre.setSize(new Dimension(112, 25));
			lbbombre.setText("Nombres:");
			lbapellido = new JLabel();
			lbapellido.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbapellido.setLocation(new Point(6, 90));
			lbapellido.setSize(new Dimension(112, 25));
			lbapellido.setText("Apellidos:");
			lbcedula = new JLabel();
			lbcedula.setText("Cédula del Cliente:");
			lbcedula.setLocation(new Point(6, 59));
			lbcedula.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbcedula.setSize(new Dimension(112, 25));
			panelfactura = new JPanel();
			panelfactura.setLayout(null);
			panelfactura.setBorder(BorderFactory.createTitledBorder(null, "Datos de Factura", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			panelfactura.setSize(new Dimension(673, 292));
			panelfactura.setLocation(new Point(14, 53));
			panelfactura.add(lbcedula, null);
			panelfactura.add(lbapellido, null);
			panelfactura.add(lbbombre, null);
			panelfactura.add(getTxtCedula(), null);
			panelfactura.add(getTxtApellidos(), null);
			panelfactura.add(getTxtNombres(), null);
			panelfactura.add(jbNum_fac, null);
			panelfactura.add(jbFecha_emision, null);

			panelfactura.add(getRbDeposito(), null);
			panelfactura.add(getRbEfectivo(), null);
			ButtonGroup grupoGenero = new ButtonGroup();
			grupoGenero.add(rbDeposito);
			grupoGenero.add(rbEfectivo);


			panelfactura.add(jbTotal_factura, null);
			panelfactura.add(getTxtFecha_emision(), null);
			panelfactura.add(getBtBuscar(), null);
			panelfactura.add(jbintruccion, null);
			panelfactura.add(jbForma_pago, null);
			panelfactura.add(getTxtTotal_fac(), null);
			panelfactura.add(jbCedulaV, null);
			panelfactura.add(lbCodigo_P, null);
			panelfactura.add(lbNombre_P, null);
			panelfactura.add(getTxtCedulaV(), null);
			panelfactura.add(getTxtCodigo_P(), null);
			panelfactura.add(getTxtNombre_P(), null);
			panelfactura.add(lbMostrarF, null);
			panelfactura.add(lbMostrarnF, null);
		}
		return panelfactura;
	}

	/**
	 * This method initializes txtCedula
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCedula() {
		if (txtCedula == null) {
			txtCedula = new JTextField();
			txtCedula.setLocation(new Point(122, 59));
			txtCedula.setPreferredSize(new Dimension(5, 25));
			txtCedula.setFont(new Font("Dialog", Font.PLAIN, 12));
			txtCedula.setSize(new Dimension(189, 25));
			txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esCedula(txtCedula)) Validaciones.pinta_text(txtCedula);
				}
			});
		}
		return txtCedula;
	}

	/**
	 * This method initializes txtApellidos
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtApellidos() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setSize(new Dimension(189, 25));
			txtApellidos.setLocation(new Point(122, 90));
			txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esLetras(txtApellidos)) Validaciones.pinta_text(txtApellidos);
				}
			});
		}
		return txtApellidos;
	}

	/**
	 * This method initializes txtNombres
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNombres() {
		if (txtNombres == null) {
			txtNombres = new JTextField();
			txtNombres.setLocation(new Point(122, 121));
			txtNombres.setSize(new Dimension(189, 25));
			txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esLetras(txtNombres)) Validaciones.pinta_text(txtNombres);
				}
			});
		}
		return txtNombres;
	}

	/**
	 * This method initializes rbDeposito
	 *
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getRbDeposito() {
		if (rbDeposito == null) {
			rbDeposito = new JRadioButton();
			rbDeposito.setText("Efectivo");
			rbDeposito.setLocation(new Point(551, 190));
			rbDeposito.setSize(new Dimension(80, 24));
		}
		return rbDeposito;
	}

	/**
	 * This method initializes rbEfectivo
	 *
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getRbEfectivo() {
		if (rbEfectivo == null) {
			rbEfectivo = new JRadioButton();
			rbEfectivo.setText("Deposito");
			rbEfectivo.setSize(new Dimension(80, 24));
			rbEfectivo.setLocation(new Point(464, 190));
			rbEfectivo.setSelected(true);
		}
		return rbEfectivo;
	}

	/**
	 * This method initializes txtFecha_emision
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtFecha_emision() {
		if (txtFecha_emision == null) {
			txtFecha_emision = new JTextField();
			txtFecha_emision.setLocation(new Point(122, 153));
			txtFecha_emision.setSize(new Dimension(189, 25));
			txtFecha_emision.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esFecha(txtFecha_emision)) Validaciones.pinta_text(txtFecha_emision);
				}
			});
		}
		return txtFecha_emision;
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
			btNuevo.setLocation(new Point(35, 10));
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
			btBuscar.setText("Buscar Cliente");
			btBuscar.setBounds(new Rectangle(5, 205, 171, 39));
			btBuscar.setIcon(new ImageIcon(getClass().getResource("/Recursos/buscar.png")));
			btBuscar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//buscar();
					buscarC();
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
			ScrollPane.setLocation(new Point(13, 399));
			ScrollPane.setViewportView(getTabla());
			ScrollPane.setSize(new Dimension(866, 267));
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
				    if(pos>=0 && pos <listF.Count())
				       ver_registroF(pos);

				}
			});
			tabla.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					int pos= tabla.getSelectedRow();
				    if(pos>=0 && pos <listF.Count())
				       ver_registroF(pos);

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
			lbBuscar.setBounds(new Rectangle(12, 7, 124, 27));
			lbBuscar.setText("Dato a Buscar:");
			panelBuscar = new JPanel();
			panelBuscar.setLayout(null);
			panelBuscar.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			panelBuscar.setBackground(SystemColor.control);
			panelBuscar.setSize(new Dimension(866, 43));
			panelBuscar.setLocation(new Point(14, 349));
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
			txtDato.setBounds(new Rectangle(145, 7, 361, 26));
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
			btListar.setLocation(new Point(35, 245));
			btListar.setSize(new Dimension(120, 40));
			btListar.setIcon(new ImageIcon(getClass().getResource("/Recursos/tabla.png")));
			btListar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					tabla.setModel(panelFacturar.this.listF.getTabla());
					//tabla.setModel(panelFacturar.this.listDf.getTabla());
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
			panelBotones.setSize(new Dimension(185, 292));
			panelBotones.setLocation(new Point(694, 53));
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
			btEliminar.setLocation(new Point(35, 198));
			btEliminar.setIcon(new ImageIcon(getClass().getResource("/Recursos/eliminar.png")));
			btEliminar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!txtCedula.getText().trim().equals("")){
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
			btEditar.setLocation(new Point(35, 57));
			btEditar.setSize(new Dimension(120, 40));
			btEditar.setHorizontalAlignment(SwingConstants.LEFT);
			btEditar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (!txtCedula.getText().trim().equals("")){
						ced=txtCedula.getText().trim(); //captura la cédula antes de modificar
						txtCedula.requestFocus();  //envia curso o enfoque a la caja de texto cedula
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
			btCancelar.setLocation(new Point(35, 104));
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

			        int pos=listF.localizar(ced);
			        if(pos>=0)ver_registroF(pos);
			        else limpiar_textos();
				}
			});
		}
		return btCancelar;
	}

	/**
	 * This method initializes txtTotal_fac
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtTotal_fac() {
		if (txtTotal_fac == null) {
			txtTotal_fac = new JTextField();
			txtTotal_fac.setSize(new Dimension(189, 25));
			txtTotal_fac.setLocation(new Point(458, 153));
			txtTotal_fac.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esPrecio(txtTotal_fac)) Validaciones.pinta_text(txtTotal_fac);
				}
			});
		}
		return txtTotal_fac;
	}

	/**
	 * This method initializes txtCedulaV
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCedulaV() {
		if (txtCedulaV == null) {
			txtCedulaV = new JTextField();
			txtCedulaV.setLocation(new Point(458, 59));
			txtCedulaV.setSize(new Dimension(189, 25));
			txtCedulaV.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esCedula(txtCedulaV)) Validaciones.pinta_text(txtCedulaV);				}
			});
		}
		return txtCedulaV;
	}

	/**
	 * This method initializes txtCodigo_P
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCodigo_P() {
		if (txtCodigo_P == null) {
			txtCodigo_P = new JTextField();
			txtCodigo_P.setLocation(new Point(458, 90));
			txtCodigo_P.setSize(new Dimension(189, 25));
			txtCodigo_P.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esCedula(txtCodigo_P)) Validaciones.pinta_text(txtCodigo_P);				}
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
			txtNombre_P.setLocation(new Point(458, 121));
			txtNombre_P.setSize(new Dimension(189, 25));
			txtNombre_P.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esLetras(txtNombre_P)) Validaciones.pinta_text(txtNombre_P);				}
			});
		}
		return txtNombre_P;
	}

	/**
	 * This method initializes panelDetalle_Factura
	 *
	 * @return javax.swing.JPanel
	 */



}  //  @jve:decl-index=0:visual-constraint="10,14"
