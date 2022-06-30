package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.alura.hotel.controller.HuespedController;
import com.alura.hotel.controller.ReservaController;
import com.alura.hotel.modelo.Huesped;
import com.alura.hotel.modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;

public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	
	private JTabbedPane panel;
	
	private HuespedController huespedController = new HuespedController();
	private ReservaController reservaController = new ReservaController();
	
	private DefaultTableModel modeloHuespedes;
	private DefaultTableModel modeloReservas;
	
	private JButton btnBuscar;
	private JButton btnEditar;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JButton btnSalir;

	
	private List<Huesped> huespedes;
	private List<Reserva> reservas;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		huespedController.ListarHuespedes();
		reservaController.ListarReservas();
		
		configurarContenido();
		configurarAccionesDelUsuario();
	}
	
	private void configurarAccionesDelUsuario() {

		txtBuscar.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				buscar();
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer index = panel.getSelectedIndex();

				switch (index) {
				case 0:
					editarHuesped();
					break;

				case 1:
					editarReserva();
					break;

				default:
					break;
				}
			}
		});

		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Integer index = panel.getSelectedIndex();

				switch (index) {
				case 0:
					eliminarHuespedes();
					limpiarTablaHuespedes();
					obtenerHuespedes();
					break;

				case 1:
					eliminarReservas();
					limpiarTablaReservas();
					obtenerReservas();
					break;

				default:
					break;
				}

			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});

		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});

	}
	
	protected void buscar() {

		Integer index = panel.getSelectedIndex();
		String busqueda;
		busqueda = txtBuscar.getText();

		switch (index) {
		case 0:
			List<Huesped> huespedesEncotrados = filtarHuespedPorId(busqueda);

			limpiarTablaHuespedes();
			cargarTablaHuespedes(huespedesEncotrados);
			tbHuespedes.revalidate();
			break;

		case 1:
			List<Reserva> reservasEncotradas = filtarReservaPorId(busqueda);

			limpiarTablaReservas();
			cargarTablaReservas(reservasEncotradas);
			tbReservas.revalidate();
			break;

		default:
			busqueda = "";
			break;
		}

	}

	private List<Huesped> filtarHuespedPorId(String busqueda) {

		List<Huesped> collect = huespedes.stream()

				.filter(huesped -> Integer.toString(huesped.getId()).contains(busqueda))

				.collect(Collectors.toList());

		return collect;

	}

	private List<Reserva> filtarReservaPorId(String busqueda) {

		List<Reserva> collect = reservas.stream()

				.filter(huesped -> Integer.toString(huesped.getId()).contains(busqueda))

				.collect(Collectors.toList());

		return collect;

	}
	
	private void configurarContenido() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 516);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(647, 85, 158, 31);
		txtBuscar.setColumns(10);
		contentPane.add(txtBuscar);
		

		btnBuscar = new JButton("");
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/lupa2.png")));
		btnBuscar.setBounds(815, 75, 54, 41);
		contentPane.add(btnBuscar);

		btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/editar-texto.png")));
		btnEditar.setBackground(SystemColor.menu);
		btnEditar.setBounds(587, 416, 54, 41);
		contentPane.add(btnEditar);

		JLabel lblTitulo = new JLabel("Sistema de Búsqueda");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
		lblTitulo.setBounds(155, 42, 301, 42);
		contentPane.add(lblTitulo);

		btnSalir = new JButton("");
		btnSalir.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(815, 416, 54, 41);
		contentPane.add(btnSalir);

		panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBounds(10, 127, 874, 265);
		contentPane.add(panel);

		tbHuespedes = new JTable();
		JScrollPane scrollPane1 = new JScrollPane(tbHuespedes);

		modeloHuespedes = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuespedes.addColumn("Id");
		modeloHuespedes.addColumn("Nombre");
		modeloHuespedes.addColumn("Apellido");
		modeloHuespedes.addColumn("Fecha de Nacimiento");
		modeloHuespedes.addColumn("Nacionalidad");
		modeloHuespedes.addColumn("Teléfono");
		modeloHuespedes.addColumn("Id Reserva");

		scrollPane1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/persona.png")), scrollPane1,
				null);

		obtenerHuespedes();

		tbReservas = new JTable();
		JScrollPane scrollPane2 = new JScrollPane(tbReservas);

		modeloReservas = (DefaultTableModel) tbReservas.getModel();
		modeloReservas.addColumn("Id");
		modeloReservas.addColumn("Fecha Entrada");
		modeloReservas.addColumn("Fecha Salida");
		modeloReservas.addColumn("Valor");
		modeloReservas.addColumn("Forma de Pago");

		scrollPane2.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/calendario.png")), scrollPane2,
				null);

		obtenerReservas();

		btnEliminar = new JButton("");
		btnEliminar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/deletar.png")));
		btnEliminar.setBackground(SystemColor.menu);
		btnEliminar.setBounds(651, 416, 54, 41);
		contentPane.add(btnEliminar);

		btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.setBackground(SystemColor.menu);
		btnCancelar.setBounds(713, 416, 54, 41);
		contentPane.add(btnCancelar);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblLogo.setBounds(25, 10, 104, 107);
		contentPane.add(lblLogo);
		setResizable(false);
		
		
	}
	
	
	
	
	private void obtenerHuespedes() {
		huespedes = this.huespedController.ListarHuespedes();
		cargarTablaHuespedes(huespedes);
	}
	
	private void obtenerReservas() {
		reservas = this.reservaController.ListarReservas();		
		cargarTablaReservas(reservas);
	}
	
	private void cargarTablaHuespedes(List<Huesped> collect) {
		collect.forEach(huesped -> modeloHuespedes.addRow(new Object[] { huesped.getId(), huesped.getNombre(),
				huesped.getApellido(), huesped.getFechaNacimientoFormatoDB(), huesped.getNacionalidad(), huesped.getTelefono(),
				huesped.getIdReserva() }));

	}
	
	private void cargarTablaReservas(List<Reserva> collect) {
		collect.forEach(reserva -> modeloReservas.addRow(new Object[] { reserva.getId(), reserva.getFechaEntradaFormatoDB(),
				reserva.getFechaSalidaFormatoDB(), reserva.getValorReserva(), reserva.getMetodoPago() }));

	}
	
	private void limpiarTablaHuespedes() {
		modeloHuespedes.getDataVector().clear();
	}

	private void limpiarTablaReservas() {
		modeloReservas.getDataVector().clear();
	}
	
	private void eliminarHuespedes() {

		if (!FilaSeleccionada(tbHuespedes)) {

			JOptionPane.showMessageDialog(this, "Por favor, elije un Huesped.");
			return;

		}

		Optional.ofNullable(modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))

				.ifPresentOrElse(fila -> {

					Integer id = Integer
							.parseInt(modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());

					int cantidadEliminada;

					cantidadEliminada = this.huespedController.eliminar(id);

					modeloHuespedes.removeRow(tbHuespedes.getSelectedRow());

					JOptionPane.showMessageDialog(this, cantidadEliminada + " item eliminado con éxito!");

				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un Huesped."));

	}

	private void eliminarReservas() {

		System.out.println("Reservas");

		if (!FilaSeleccionada(tbReservas)) {

			JOptionPane.showMessageDialog(this, "Por favor, elije una Reserva.");
			return;

		}

		Optional.ofNullable(modeloReservas.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))

				.ifPresentOrElse(fila -> {

					Integer id = Integer
							.parseInt(modeloReservas.getValueAt(tbReservas.getSelectedRow(), 0).toString());

					int cantidadEliminada;

					cantidadEliminada = this.reservaController.eliminar(id);

					modeloReservas.removeRow(tbReservas.getSelectedRow());

					JOptionPane.showMessageDialog(this, cantidadEliminada + " item eliminado con éxito!");

				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije una Reserva."));

	}
	
	private void editarHuesped() { // Carga los datos en la view RegistroHUesped

		if (!FilaSeleccionada(tbHuespedes)) {

			JOptionPane.showMessageDialog(this, "Por favor, elije un item.");
			return;

		}

		Optional.ofNullable(modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))

				.ifPresentOrElse(fila -> {

					
					
					DateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
					try {
						Integer id = (Integer) modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 0);
						String nombre = (String) modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 1);
						String apellido = (String) modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 2);
						Date date = (Date) simpleDateFormat.parse((String) modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 3));
						Date fechaNacimiento = date;
						String nacionalidad = (String) modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 4);
						String telefono = (String) modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 5);
						Integer idReserva = (Integer) modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 6);

						Huesped huesped = new Huesped(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono,
								idReserva);

						RegistroHuesped registroHuesped = new RegistroHuesped(null);
						registroHuesped.configurarDatosHuespedEnFormulario(huesped);
						registroHuesped.setVisible(true);
						dispose();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					

				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item."));

	}
	

	private void editarReserva() { // Carga los datos en la view RegistroHUesped

		if (!FilaSeleccionada(tbReservas)) {

			JOptionPane.showMessageDialog(this, "Por favor, elije un item.");
			return;

		}

		Optional.ofNullable(modeloReservas.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))

				.ifPresentOrElse(fila -> {
					
					DateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

					try {
						Integer id = (Integer) modeloReservas.getValueAt(tbReservas.getSelectedRow(), 0);
						Date fechaEntrada = (Date) simpleDateFormat.parse((String) modeloReservas.getValueAt(tbReservas.getSelectedRow(), 1));
						Date fechaSalida = (Date) simpleDateFormat.parse((String) modeloReservas.getValueAt(tbReservas.getSelectedRow(), 2));
						Float valor = (float) modeloReservas.getValueAt(tbReservas.getSelectedRow(), 3);
						String formaPago = (String) modeloReservas.getValueAt(tbReservas.getSelectedRow(), 4);

						Reserva reserva = new Reserva(id, fechaEntrada, fechaSalida, valor, formaPago);
						Reservas reservas = new Reservas();
						reservas.configurarDatosReservaEnFormulario(reserva);
						reservas.setVisible(true);
						dispose();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					

				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item."));

	}	
	
	protected void cancelar() {

		MenuUsuario menuUsuario = new MenuUsuario();
		menuUsuario.setVisible(true);
		dispose();

	}
	
	private boolean FilaSeleccionada(JTable tbSeleccionada) {
		return tbSeleccionada.getSelectedRowCount() != 0 || tbSeleccionada.getSelectedColumnCount() != 0;
	}
	
	
}
