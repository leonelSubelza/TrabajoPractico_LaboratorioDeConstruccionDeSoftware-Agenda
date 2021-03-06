package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import dto.TipoContactoDTO;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaTipoContacto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTable tablaTipoContacto;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnSalir;
	private JButton btnEditar;
	private DefaultTableModel modelTipoContacto;
	private String[] nombreColumnas = { "Nombre" };
	private JLabel lblNewLabel;
	private JTextField txtTipoContacto;

	public VentanaTipoContacto() {
		super();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 532, 271);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(252, 0, 271, 232);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spTipoContacto = new JScrollPane();
		spTipoContacto.setBounds(10, 11, 250, 182);
		panel.add(spTipoContacto);

		modelTipoContacto = new DefaultTableModel(null, nombreColumnas);
		tablaTipoContacto = new JTable(modelTipoContacto);
		tablaTipoContacto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tablaTipoContacto.rowAtPoint(e.getPoint());
				txtTipoContacto.setText(tablaTipoContacto.getValueAt(filaSeleccionada, 0).toString());
			}
		});

		tablaTipoContacto.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaTipoContacto.getColumnModel().getColumn(0).setResizable(false);
		
		spTipoContacto.setViewportView(tablaTipoContacto);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(89, 198, 89, 23);
		panel.add(btnSalir);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 254, 232);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 198, 71, 23);
		panel_1.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(91, 198, 71, 23);
		panel_1.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(172, 198, 71, 23);
		panel_1.add(btnBorrar);

		lblNewLabel = new JLabel("Modificar tipo de contacto");
		lblNewLabel.setBounds(34, 11, 187, 14);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		txtTipoContacto = new JTextField();
		txtTipoContacto.setBounds(66, 50, 118, 20);
		panel_1.add(txtTipoContacto);
		txtTipoContacto.setColumns(10);
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
			}
		});
		this.frame.setVisible(true);
	}

	public void cerrar() {
		this.txtTipoContacto.setText(null);
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public DefaultTableModel getModelTipoContacto() {
		return modelTipoContacto;
	}

	public JTable getTablaTipoContacto() {
		return tablaTipoContacto;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTextField getTxtTipoContacto() {
		return txtTipoContacto;
	}

	public void limpiarTxtTipoContacto() {
		getTxtTipoContacto().setText("");
	}

	public JTable getTable() {
		return tablaTipoContacto;
	}

	public void llenarTabla(List<TipoContactoDTO> tipoContactoEnTabla) {
		this.getModelTipoContacto().setRowCount(0);
		this.getModelTipoContacto().setColumnCount(0);
		this.getModelTipoContacto().setColumnIdentifiers(this.getNombreColumnas());

		for (TipoContactoDTO t : tipoContactoEnTabla) {
			String nombre = t.getNombreTipoContacto();
			Object[] fila = { nombre };
			this.getModelTipoContacto().addRow(fila);
		}
	}
}
