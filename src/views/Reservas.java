package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;

import com.alura.hotel.controller.ReservaController;
import com.alura.hotel.modelo.Reserva;
import com.mchange.v2.cfg.PropertiesConfigSource.Parse;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;



public class Reservas extends JFrame {

	private JPanel contentPane;
	protected Reserva reserva;
	
	private JDateChooser txtFechaE;
	private JDateChooser txtFechaS;
	private JTextField txtValor;
	private JTextField txtid = new JTextField();

	private JComboBox<String> txtFormaPago;
	
	private JButton btnReservar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservas frame = new Reservas();
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
	
	public void configurarDatosReservaEnFormulario(Reserva reserva) {
		txtFechaE.setDate(reserva.getFechaEntrada());
		txtFechaS.setDate(reserva.getFechaSalida());
		txtValor.setText(Float.toString(reserva.getValorReserva()));
		txtFormaPago.setSelectedItem(reserva.getMetodoPago());
		txtid.setText(String.valueOf(reserva.getId()));

		btnReservar.setText("Actualizar");
	}
	
	public Reservas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("/imagenes/calendario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 540);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245,245,245));
		panel.setBounds(0, 0, 900, 502);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtFechaE = new JDateChooser();
		txtFechaE.setBounds(88, 166, 235, 33);
		txtFechaE.setMinSelectableDate(new Date());
		panel.add(txtFechaE);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha de Check In");
		lblNewLabel_1.setBounds(88, 142, 133, 14);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Fecha de Check Out");
		lblNewLabel_1_1.setBounds(88, 210, 133, 14);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1);
		
		txtFechaS = new JDateChooser();
		txtFechaS.setBounds(88, 234, 235, 33);
		txtFechaS.getCalendarButton().setBackground(Color.WHITE);
		txtFechaS.setMinSelectableDate(new Date());
		panel.add(txtFechaS);		
		
		txtValor = new JTextField();
		txtValor.setBounds(88, 303, 235, 33);
		txtValor.setEnabled(false);
		panel.add(txtValor);
		txtValor.setColumns(10);

		
		JLabel lblNewLabel_1_1_1 = new JLabel("Valor de la Reserva");
		lblNewLabel_1_1_1.setBounds(88, 278, 133, 14);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1);
		
		txtFormaPago = new JComboBox();
		txtFormaPago.setBounds(88, 373, 235, 33);
		txtFormaPago.setFont(new Font("Arial", Font.PLAIN, 14));
		txtFormaPago.setModel(new DefaultComboBoxModel(new String[] {"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo"}));
		panel.add(txtFormaPago);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Forma de pago");
		lblNewLabel_1_1_1_1.setBounds(88, 347, 133, 24);
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("Sistema de Reservas");
		lblNewLabel_4.setBounds(108, 93, 199, 42);
		lblNewLabel_4.setForeground(new Color(65, 105, 225));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblNewLabel_4);
		
		txtFechaE.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	        	if(txtFechaE.getDate() != null && txtFechaS.getDate() != null) {
	        		reserva = new Reserva(txtFechaE.getDate(), txtFechaS.getDate(), (String)txtFormaPago.getSelectedItem());
	        		txtValor.setText(String.valueOf(reserva.getValorReserva()));	        
	        	}
	        }
		});
		
		txtFechaS.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	        	if(txtFechaE.getDate() != null && txtFechaS.getDate() != null) {
	        		reserva = new Reserva(txtFechaE.getDate(), txtFechaS.getDate(), (String)txtFormaPago.getSelectedItem());
	        		txtValor.setText(String.valueOf(reserva.getValorReserva()));	        
	        	}
	        }
		});
		
		txtFormaPago.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if(txtFechaE.getDate() != null && txtFechaS.getDate() != null) {
	        		reserva = new Reserva(txtFechaE.getDate(), txtFechaS.getDate(), (String)txtFormaPago.getSelectedItem());
	        		txtValor.setText(String.valueOf(reserva.getValorReserva()));	        
	        	}
		    }
		});
		
		btnReservar = new JButton("Continuar");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reservar();				
			}			
		});
		btnReservar.setForeground(Color.WHITE);
		btnReservar.setBounds(183, 436, 140, 33);
		btnReservar.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/calendario.png")));
		btnReservar.setBackground(new Color(65,105,225));
		btnReservar.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(btnReservar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(399, 0, 491, 502);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, -16, 500, 539);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/reservas-img-2.png")));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(15, 6, 104, 107);
		panel.add(lblNewLabel_2);
	}

	
	private void actualizar(int id) {
		
		ReservaController reservaController = new ReservaController();
		var reserva = new Reserva(id, txtFechaE.getDate(), txtFechaS.getDate(),
				Float.parseFloat(txtValor.getText()), txtFormaPago.getSelectedItem().toString());
		reservaController.actualizar(reserva);

		JOptionPane.showMessageDialog(this, "Actualizado con Exito.");

		Busqueda busqueda = new Busqueda();
		busqueda.setVisible(true);
		dispose();

	}
	
	private void reservar() {
		if (!txtid.getText().isEmpty()) {
			actualizar(Integer.parseInt(txtid.getText()));
		}else {
			ArrayList<String> errores = validarCampos();
			if(errores.isEmpty()) {
				reserva = new Reserva(txtFechaE.getDate(), txtFechaS.getDate(), (String)txtFormaPago.getSelectedItem());
				RegistroHuesped huesped = new RegistroHuesped(reserva);
				huesped.setVisible(true);
				dispose();
			}else {
				MostrarMensajeError(errores);
			}
		}
	}
	
	private ArrayList<String> validarCampos() {
		ArrayList<String> errores = new ArrayList<>();	
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
	    Date date = calendar.getTime();
	    
	    
		if(txtFechaE.getDate() != null && txtFechaS.getDate() != null)	{
			if(txtFechaE.getDate().compareTo(date) < 0) {
				System.out.println(txtFechaE.getDate());
				System.out.println(date);
				errores.add("-La fecha de check in no puede ser anterior al dia de hoy.");
			}
			if(txtFechaS.getDate().compareTo(date) < 0) {
				errores.add("-La fecha de check out no puede ser anterior al dia de hoy.");
			}
			if(txtFechaS.getDate().compareTo(txtFechaE.getDate()) < 0) {
				errores.add("-La fecha de entrada no puede ocurrir despues que la de salida.");
			}
			if(txtFechaE.getDate() == null) {
				errores.add("-Falta rellenar la fecha de check in.");
			}else if(txtFechaS.getDate() == null) {
				errores.add("-Falta rellenar la fecha de check out.");
			} 
		}else {
			errores.add("Falta rellenar las fechas");
		}
		
		return errores;
	}
	
	private void MostrarMensajeError(ArrayList<String> mensajes) {
	    JOptionPane.showMessageDialog(null, mensajes.toArray(), "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
	}

	
}
