package Vistas;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Color;


import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.io.IOException;
import java.awt.SystemColor;

public class frmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelMenu = null;
	private JPanel panelCentral = null;
	private JButton btCliente = null;
	private JButton btVendedor = null;
	private JLabel lbFondo = null;
	private JLabel lbimg_repre = null;
	private JLabel lbimg_estudiante = null;
	private JLabel lbimg_matri = null;
	private JButton btProducto = null;
	private JButton btHome = null;
	private JPanel panelPie = null;
	private JLabel lbPie = null;
	public static JLabel lbMensaje = null;
	private JButton btSalir = null;
	private JLabel lbimg_salir = null;
	private JLabel lbimg_curso = null;
	private JButton btFactura = null;
	private JLabel lbimgDf = null;
	private JButton btDetalle_factura = null;
	/**
	 * This is the default constructor
	 */
	public frmPrincipal() {
		super();
		initialize();
		lbFondo.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Recursos/desenfocao.jpeg")));
		//this.setLocationRelativeTo(null);
		this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH); //maximizar

	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(749, 578);
		this.setFont(new Font("Dialog", Font.BOLD, 14));
		this.setPreferredSize(new Dimension(1200, 900));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Recursos/icono app.png")));
		this.setResizable(true);
		this.setContentPane(getJContentPane());
		this.setTitle("Sistema Facturacion");
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelMenu(), BorderLayout.WEST);
			jContentPane.add(getPanelCentral(), BorderLayout.CENTER);
			jContentPane.add(getPanelPie(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes panelMenu
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelMenu() {
		if (panelMenu == null) {
			lbimgDf = new JLabel();
			lbimgDf.setBounds(new Rectangle(4, 368, 58, 41));
			lbimgDf.setText("");
			lbimgDf.setIcon(new ImageIcon(getClass().getResource("/Recursos/icons8-panel-de-detalles-48.png")));
			lbimg_curso = new JLabel();
			lbimg_curso.setBounds(new Rectangle(7, 297, 62, 65));
			lbimg_curso.setText("");
			lbimg_curso.setIcon(new ImageIcon(getClass().getResource("/Recursos/icons8-recibo-48.png")));
			lbimg_salir = new JLabel();
			lbimg_salir.setBounds(new Rectangle(4, 426, 64, 56));
			lbimg_salir.setText("");
			lbimg_salir.setIcon(new ImageIcon(getClass().getResource("/Recursos/close.png")));
			lbimg_matri = new JLabel();
			lbimg_matri.setText("");
			lbimg_matri.setLocation(new Point(6, 231));
			lbimg_matri.setSize(new Dimension(64, 65));
			lbimg_matri.setIcon(new ImageIcon(getClass().getResource("/Recursos/Producto.png")));
			lbimg_estudiante = new JLabel();
			lbimg_estudiante.setText("");
			lbimg_estudiante.setSize(new Dimension(66, 68));
			lbimg_estudiante.setLocation(new Point(6, 102));
			lbimg_estudiante.setIcon(new ImageIcon(getClass().getResource("/Recursos/Usuario.png")));
			lbimg_repre = new JLabel();
			lbimg_repre.setText("");
			lbimg_repre.setSize(new Dimension(64, 65));
			lbimg_repre.setLocation(new Point(5, 171));
			lbimg_repre.setIcon(new ImageIcon(getClass().getResource("/Recursos/Vendedor.png")));
			panelMenu = new JPanel();
			panelMenu.setLayout(null);
			panelMenu.setPreferredSize(new Dimension(225, 0));
			panelMenu.setBackground(new Color(227, 236, 255));
			panelMenu.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

			panelMenu.add(lbimg_estudiante, null);
			panelMenu.add(lbimg_repre, null);
			panelMenu.add(getBtCliente(), null);
			panelMenu.add(getBtVendedor(), null);

			panelMenu.add(lbimg_matri, null);
			panelMenu.add(getBtProducto(), null);
			panelMenu.add(getBtHome(), null);
			panelMenu.add(lbimg_salir, null);
			panelMenu.add(getBtSalir(), null);

			panelMenu.add(lbimg_curso, null);
			panelMenu.add(getBtFactura(), null);
			panelMenu.add(lbimgDf, null);
			panelMenu.add(getBtDetalle_factura(), null);
		}
		return panelMenu;
	}

	/**
	 * This method initializes panelCentral
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelCentral() {
		if (panelCentral == null) {
			lbFondo = new JLabel();
			lbFondo.setBounds(0, 0, 1600, 914);
			lbFondo.setText("");
			lbFondo.setBackground(new Color(204, 255, 255));
			lbFondo.setName("lbFondo");
			panelCentral = new JPanel();
			panelCentral.setBackground(Color.white);
			panelCentral.setLayout(null);
			panelCentral.add(lbFondo);
		}
		return panelCentral;
	}

	/**
	 * This method initializes btCliente
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtCliente() {
		if (btCliente == null) {
			btCliente = new JButton();
			btCliente.setIcon(new ImageIcon(getClass().getResource("/Recursos/boton-azul.png")));
			btCliente.setBackground(new Color(51, 102, 255));
			btCliente.setSize(new Dimension(214, 64));
			btCliente.setLocation(new Point(19, 110));
			btCliente.setPreferredSize(new Dimension(0, 0));
			btCliente.setForeground(Color.white);
			btCliente.setFont(new Font("Arial Black", Font.PLAIN, 14));
			btCliente.setText("Cliente");
			//quitar borde
			btCliente.setBorder(null);
			//quitar recuadro de boton seleccionado
			btCliente.setFocusable(false);
			//quitar recuadro al hacer clik
			btCliente.setContentAreaFilled(false);

			btCliente.setHorizontalTextPosition(SwingConstants.CENTER);
			btCliente.setRolloverIcon(new ImageIcon(getClass().getResource("/Recursos/boton-naranja.png")));

			btCliente.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					panelCentral.removeAll();
					try{
					panelCentral.add(new panelCliente(),"Cliente");
					}catch(Exception ex){}
					panelCentral.repaint();
				}
			});
		}
		return btCliente;
	}

	/**
	 * This method initializes btVendedor
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtVendedor() {
		if (btVendedor == null) {
			btVendedor = new JButton();
			btVendedor.setIcon(new ImageIcon(getClass().getResource("/Recursos/boton-azul.png")));
			btVendedor.setText("Vendedor");
			btVendedor.setSize(new Dimension(214, 64));
			btVendedor.setLocation(new Point(19, 172));
			btVendedor.setForeground(Color.white);
			btVendedor.setFont(new Font("Arial Black", Font.PLAIN, 14));
			btVendedor.setHorizontalTextPosition(SwingConstants.CENTER);
			btVendedor.setHorizontalAlignment(SwingConstants.CENTER);
			btVendedor.setRolloverEnabled(true);
			btVendedor.setRolloverIcon(new ImageIcon(getClass().getResource("/Recursos/boton-naranja.png")));
			btVendedor.setBackground(new Color(51, 102, 255));
			btVendedor.setBorder(null);
			btVendedor.setFocusable(false);
			//quitar recuadro al hacer clik
			btVendedor.setContentAreaFilled(false);
			btVendedor.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					panelCentral.removeAll();
					try {
						panelCentral.add(new panelVendedor(),"Vendedor");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					panelCentral.repaint();
				}
			});
		}
		return btVendedor;
	}

	/**
	 * This method initializes btProducto
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtProducto() {
		if (btProducto == null) {
			btProducto = new JButton();
			btProducto.setFont(new Font("Arial Black", Font.PLAIN, 14));
			btProducto.setForeground(Color.white);
			btProducto.setHorizontalAlignment(SwingConstants.CENTER);
			btProducto.setHorizontalTextPosition(SwingConstants.CENTER);
			btProducto.setIcon(new ImageIcon(getClass().getResource("/Recursos/boton-azul.png")));
			btProducto.setRolloverEnabled(true);
			btProducto.setRolloverIcon(new ImageIcon(getClass().getResource("/Recursos/boton-naranja.png")));
			btProducto.setText("Producto");
			btProducto.setLocation(new Point(19, 232));
			btProducto.setSize(new Dimension(214, 64));
			btProducto.setBackground(new Color(51, 102, 255));
			//quitar borde
			btProducto.setBorder(null);
			//quitar recuadro de boton seleccionado
			btProducto.setFocusable(false);
			//quitar recuadro al hacer clik
			btProducto.setContentAreaFilled(false);
			btProducto.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					panelCentral.removeAll();
					try {
						panelCentral.add(new panelProducto(),"Producto");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					panelCentral.repaint();
				}
			});
		}
		return btProducto;
	}

	/**
	 * This method initializes btHome
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtHome() {
		if (btHome == null) {
			btHome = new JButton();
			btHome.setFont(new Font("Arial Black", Font.PLAIN, 14));
			btHome.setForeground(Color.white);

			btHome.setPreferredSize(new Dimension(0, 0));
			btHome.setContentAreaFilled(false);
			btHome.setHorizontalTextPosition(SwingConstants.CENTER);
			btHome.setIcon(new ImageIcon(getClass().getResource("/Recursos/icons8-menú-principal-96.png")));
			btHome.setText("");
			btHome.setLocation(new Point(56, 1));
			btHome.setSize(new Dimension(100, 92));
			btHome.setBackground(new Color(0, 51, 153));
			//quitar borde
			btHome.setBorder(null);
			//quitar recuadro de boton seleccionado
			btHome.setFocusable(false);
			//quitar recuadro al hacer clik
			btHome.setContentAreaFilled(false);

			btHome.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					panelCentral.removeAll();
					lbFondo.setIcon(new ImageIcon(getClass().getResource("/Recursos/fondo4.jpg")));
					panelCentral.add(lbFondo, lbFondo.getName());
					panelCentral.repaint();

				}
			});
		}
		return btHome;
	}

	/**
	 * This method initializes panelPie
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelPie() {
		if (panelPie == null) {
			lbMensaje = new JLabel();
			lbMensaje.setForeground(Color.red);
			lbMensaje.setLocation(new Point(238, -1));
			lbMensaje.setSize(new Dimension(539, 41));
			lbMensaje.setText("");
			lbPie = new JLabel();
			lbPie.setText("2023 Utmach Derechos reservados");
			lbPie.setSize(new Dimension(205, 40));
			lbPie.setLocation(new Point(8, -1));
			panelPie = new JPanel();
			panelPie.setLayout(null);
			panelPie.setPreferredSize(new Dimension(0, 40));
			panelPie.add(lbPie, null);
			panelPie.add(lbMensaje, null);
		}
		return panelPie;
	}

	/**
	 * This method initializes btSalir
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtSalir() {
		if (btSalir == null) {
			btSalir = new JButton();
			btSalir.setFont(new Font("Arial Black", Font.PLAIN, 14));
			btSalir.setForeground(Color.white);
			btSalir.setBorder(null);
			btSalir.setContentAreaFilled(false);
			btSalir.setHorizontalAlignment(SwingConstants.CENTER);
			btSalir.setHorizontalTextPosition(SwingConstants.CENTER);
			btSalir.setIcon(new ImageIcon(getClass().getResource("/Recursos/boton-azul.png")));
			btSalir.setRolloverEnabled(true);
			btSalir.setRolloverIcon(new ImageIcon(getClass().getResource("/Recursos/boton-naranja.png")));
			btSalir.setText("Salir");
			btSalir.setSize(new Dimension(214, 64));
			btSalir.setLocation(new Point(19, 423));
			btSalir.setBackground(new Color(51, 102, 255));
			btSalir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de salir de la aplicación?","Sistema Facturacion", JOptionPane.YES_NO_OPTION);
				    if (respuesta == JOptionPane.YES_OPTION) {
				    	System.exit(0);
				    }
				}
			});
		}
		return btSalir;
	}

	/**
	 * This method initializes btFactura
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtFactura() {
		if (btFactura == null) {
			btFactura = new JButton();
			btFactura.setFont(new Font("Arial Black", Font.PLAIN, 14));
			btFactura.setForeground(Color.white);
			btFactura.setBorder(null);
			btFactura.setContentAreaFilled(false);
			btFactura.setHorizontalAlignment(SwingConstants.CENTER);
			btFactura.setHorizontalTextPosition(SwingConstants.CENTER);
			btFactura.setIcon(new ImageIcon(getClass().getResource("/Recursos/boton-azul.png")));
			btFactura.setRolloverEnabled(true);
			btFactura.setRolloverIcon(new ImageIcon(getClass().getResource("/Recursos/boton-naranja.png")));
			btFactura.setText("Factura");
			btFactura.setLocation(new Point(19, 291));
			btFactura.setSize(new Dimension(214, 64));
			btFactura.setBackground(new Color(51, 102, 255));
			btFactura.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					panelCentral.removeAll();
					try {
						panelCentral.add(new panelFacturar(),"Factura");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					panelCentral.repaint();
				}
			});
		}
		return btFactura;
	}

	/**
	 * This method initializes btDetalle_factura
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtDetalle_factura() {
		if (btDetalle_factura == null) {
			btDetalle_factura = new JButton();
			btDetalle_factura.setFont(new Font("Arial Black", Font.PLAIN, 14));
			btDetalle_factura.setForeground(Color.white);
			btDetalle_factura.setBorder(null);
			btDetalle_factura.setContentAreaFilled(false);
			btDetalle_factura.setHorizontalAlignment(SwingConstants.CENTER);
			btDetalle_factura.setHorizontalTextPosition(SwingConstants.CENTER);
			btDetalle_factura.setIcon(new ImageIcon(getClass().getResource("/Recursos/boton-azul.png")));
			btDetalle_factura.setRolloverEnabled(true);
			btDetalle_factura.setRolloverIcon(new ImageIcon(getClass().getResource("/Recursos/boton-naranja.png")));
			btDetalle_factura.setText("Detalle de Factura");
			btDetalle_factura.setLocation(new Point(19, 354));
			btDetalle_factura.setSize(new Dimension(214, 64));
			btDetalle_factura.setBackground(new Color(51, 102, 255));
			btDetalle_factura.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					panelCentral.removeAll();
					try {
						panelCentral.add(new panelDetalle_FacturaV2(),"Factura");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					panelCentral.repaint();
				}
			});
		}
		return btDetalle_factura;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
