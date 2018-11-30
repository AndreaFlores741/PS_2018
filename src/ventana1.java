import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Button;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

import Paq_Modificado.MDIApplication_Principal;


public class ventana1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField varNum;
	private JTextField restNum;
	private JTable tablaDatos;
	private JTable tablaSol;
	private ButtonGroup grupo;
	private JRadioButton opcionMax;
	private JRadioButton opcionMin;

	private JButton button;

	double Matriz[][] = null;
	double MatrizVista[][] = null;
	Object array[] = null;
	Object EtiquetaX[] = null, EtiquetaY[] = null;
	Object array2[] = null;
	Object EtiquetaX2[] = null, EtiquetaY2[] = null;
	int rest = 0, var = 0, iter = 0, iteracion = 0;
	/**
	 * Launch the application.
	 */
	public enum Ids {
		OPEN(100), CLOSE(200);

		private final int id;
		Ids(int id) { this.id = id; }
		public int getValue() { return id; }
	}


	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ventana1 v = new ventana1();
                v.setSize(800,600); 
                v.setVisible(true);
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public ventana1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 140, 276);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblProgramacinLineal = new JLabel("Programaci\u00F3n lineal");
		lblProgramacinLineal.setBounds(5, 5, 424, 14);
		contentPane.add(lblProgramacinLineal);

		JLabel lblNewLabel = new JLabel("Objetivo:");
		lblNewLabel.setBounds(15, 30, 58, 14);
		contentPane.add(lblNewLabel);

		opcionMax = new JRadioButton("Maximizar");
		opcionMax.setBounds(94, 26, 109, 23);
		contentPane.add(opcionMax);

		opcionMin = new JRadioButton("Minimizar");
		opcionMin.setBounds(236, 26, 109, 23);
		contentPane.add(opcionMin);


		grupo = new ButtonGroup();
		grupo.add(opcionMin);
		grupo.add(opcionMax);


		JLabel lblNumeroDeVariables = new JLabel("Numero de variables:");
		lblNumeroDeVariables.setBounds(15, 66, 165, 14);
		contentPane.add(lblNumeroDeVariables);

		JLabel lblNumeroDeRestricciones = new JLabel("Numero de Restricciones:");
		lblNumeroDeRestricciones.setBounds(15, 91, 165, 14);
		contentPane.add(lblNumeroDeRestricciones);

		varNum = new JTextField();
		varNum.setBounds(204, 63, 86, 20);
		contentPane.add(varNum);
		varNum.setColumns(10);

		restNum = new JTextField();
		restNum.setBounds(204, 88, 86, 20);
		contentPane.add(restNum);
		restNum.setColumns(10);

		button = new JButton("Refrescar");
		

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					iniciarTabla();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Debe Ingresar numeros validos", "no se puede inicializar tabla", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		button.setBounds(309, 91, 70, 22);
		contentPane.add(button);

		JPanel panel = new JPanel();
		panel.setBounds(27, 139, 507, 118);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 507, 118);
		panel.add(scrollPane_1);

		tablaDatos = new JTable();
		scrollPane_1.setViewportView(tablaDatos);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(27, 314, 507, 130);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 507, 130);
		panel_1.add(scrollPane_2);

		tablaSol = new JTable();
		scrollPane_2.setViewportView(tablaSol);

		JButton btnNewButton = new JButton("Solucion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(opcionMax.isSelected())
					maximizar();
				else if(opcionMin.isSelected())
					minimizar();
				else
					JOptionPane.showMessageDialog(null, "Seleccione Objetivo\nMaximizar o Minimizar");
			}
		});
		btnNewButton.setBounds(204, 268, 89, 23);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 284, 476, -141);
		contentPane.add(scrollPane);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ventana1.class.getResource("/fig/logo.png")));
		label.setBounds(362, 5, 190, 113);
		contentPane.add(label);
		
// set name
		contentPane.setName("panel");
		button.setName("Refrescar");
		
		this.pack();
		this.setSize(800,600);
		this.setVisible(true);
	}
	public void iniciarTabla()
	{
		var = Integer.parseInt(varNum.getText());
		rest = Integer.parseInt(restNum.getText());

		DefaultTableModel modeloTabla = new DefaultTableModel();
		modeloTabla.setRowCount(rest + 1);
		//modeloTabla.setColumnCount(var + rest + 2);
		modeloTabla.setColumnCount(var + 3);

		array = new Object[var + 3];
		EtiquetaX = new Object[var];
		for (int i = 1; i < array.length - 2; i++) {
			array[i] = "X" + i;
			EtiquetaX[i - 1] = "X" + i;

		}
		//---------------------
		EtiquetaY = new Object[rest + 1];
		for (int i = 0; i < rest; i++) {
			modeloTabla.setValueAt("S" + (i + 1), i, 0);
			EtiquetaY[i] = "S" + (i + 1);
		}
		modeloTabla.setValueAt("Z", rest, 0);

		EtiquetaY[rest] = "Z";
		//----------------------
		array[array.length - 1] = "soluc";
		array[array.length - 2] = "select";
		modeloTabla.setColumnIdentifiers(array);
		tablaDatos.setModel(modeloTabla);

		String[] comparacion = {"<=", ">=", "=="};
		JComboBox opciones = new JComboBox(comparacion);
		TableCellEditor editor = new DefaultCellEditor(opciones);
		TableColumn columna = tablaDatos.getColumnModel().getColumn(var+1);
		columna.setCellEditor(editor);



		array2 = new Object[var + rest + 2];
		EtiquetaX2 = new Object[var + rest];
		for (int i = 1; i < array.length - 1; i++) {
			if (i < var + 1) {
				array2[i] = "X" + i;
				EtiquetaX2[i - 1] = "X" + i;
			} else {
				array2[i] = "S" + (i - var);
				EtiquetaX2[i - 1] = "S" + (i - var);
			}
		}
		//---------------------
		EtiquetaY2 = new Object[rest + 1];
		for (int i = 0; i < rest; i++) {

			EtiquetaY2[i] = "S" + (i + 1);
		}

		EtiquetaY2[rest] = "Z";

		array2[array2.length - 1] = "soluc";




	}
	public void minimizar()
	{
		try {

			DefaultTableModel modeloSolucion = new DefaultTableModel();

			Matriz = new double[rest + 1][rest + var + 1];


			int[] elec = new int[rest+1];
			TableColumn columna = tablaDatos.getColumnModel().getColumn(var+1);
			System.out.println("paso columna");

			for (int i = 0; i < (rest+1); i++) {
				String e = tablaDatos.getValueAt(i, var+1).toString();
				if(e == "<=")
					elec[i] = 1;
				else if(e == ">=")
					elec[i] = -1;
				else 
					elec[i] = 0;
			}



			int[][] edit = new int[rest+1][rest];
			for (int i = 0; i < edit.length; i++) {
				for (int j = 0; j < edit[i].length; j++) {
					edit[i][j] = 0;
					if(i == j)
						edit[i][j] = 1*elec[i];
				}
			}


			for (int i = 0; i < (rest + 1); i++) {
				for (int j = 0; j < (rest + var + 1); j++) {

					if(j < var)
						Matriz[i][j] = Double.parseDouble(tablaDatos.getValueAt(i, j + 1).toString());
					else if(j < rest+var)
						Matriz[i][j] = edit[i][j-var];
					else
						Matriz[i][j] = Double.parseDouble(tablaDatos.getValueAt(i, var + 2).toString());
				}
			}

			for (int i = 0; i < Matriz.length; i++) {
				System.out.println();
				for (int j = 0; j < Matriz[i].length; j++) {
					System.out.print(Matriz[i][j]+ " ");
				}
			}





			while (ComprobarResultado1() != true) {
				EtiquetaY2[FilaPivote1()] = EtiquetaX2[ColumnaPivote1()];
				NuevaTabla(FilaPivote1(), ColumnaPivote1());
				modeloSolucion.setColumnCount(rest + var + 2);
				modeloSolucion.setRowCount(rest + 1);
				//--------------------------
				modeloSolucion.setColumnIdentifiers(array2);
				//---------------------------
				for (int i = 0; i < (rest + 1); i++) {
					modeloSolucion.setValueAt(EtiquetaY2[i], i, 0);
					for (int j = 0; j < (rest + var + 1); j++) {
						modeloSolucion.setValueAt(Matriz[i][j], i, j + 1);
					}
				}
				tablaSol.setModel(modeloSolucion);
				iteracion++;
			}


			tablaSol.setAutoscrolls(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
	}
	public void maximizar()
	{
		try {

			DefaultTableModel modeloSolucion = new DefaultTableModel();

			Matriz = new double[rest + 1][rest + var + 1];


			int[] elec = new int[rest+1];
			TableColumn columna = tablaDatos.getColumnModel().getColumn(var+1);
			System.out.println("paso columna");

			for (int i = 0; i < (rest+1); i++) {
				String e = tablaDatos.getValueAt(i, var+1).toString();
				if(e == "<=")
					elec[i] = 1;
				else if(e == ">=")
					elec[i] = -1;
				else 
					elec[i] = 0;
			}



			int[][] edit = new int[rest+1][rest];
			for (int i = 0; i < edit.length; i++) {
				for (int j = 0; j < edit[i].length; j++) {
					edit[i][j] = 0;
					if(i == j)
						edit[i][j] = 1*elec[i];
				}
			}


			for (int i = 0; i < (rest + 1); i++) {
				for (int j = 0; j < (rest + var + 1); j++) {

					if(j < var)
						Matriz[i][j] = Double.parseDouble(tablaDatos.getValueAt(i, j + 1).toString());
					else if(j < rest+var)
						Matriz[i][j] = edit[i][j-var];
					else
						Matriz[i][j] = Double.parseDouble(tablaDatos.getValueAt(i, var + 2).toString());
				}
			}

			for (int i = 0; i < Matriz.length; i++) {
				System.out.println();
				for (int j = 0; j < Matriz[i].length; j++) {
					System.out.print(Matriz[i][j]+ " ");
				}
			}





			while (ComprobarResultado() != true) {
				EtiquetaY2[FilaPivote()] = EtiquetaX2[ColumnaPivote()];
				NuevaTabla(FilaPivote(), ColumnaPivote());
				modeloSolucion.setColumnCount(rest + var + 2);
				modeloSolucion.setRowCount(rest + 1);
				//--------------------------
				modeloSolucion.setColumnIdentifiers(array2);
				//---------------------------
				for (int i = 0; i < (rest + 1); i++) {
					modeloSolucion.setValueAt(EtiquetaY2[i], i, 0);
					for (int j = 0; j < (rest + var + 1); j++) {
						modeloSolucion.setValueAt(Matriz[i][j], i, j + 1);
					}
				}
				tablaSol.setModel(modeloSolucion);
				iteracion++;
			}


			tablaSol.setAutoscrolls(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
	}

	public int ColumnaPivote() {
		int pos = 0;
		double aux = Matriz[rest][0];
		for (int i = 1; i < rest + var; i++) {
			if (aux > Matriz[rest][i]) {
				aux = Matriz[rest][i];
				pos = i;
			}
		}
		return pos;
	}

	public int FilaPivote() {
		int columna = ColumnaPivote();
		double temp = 0, razon = Matriz[0][var + rest] / Matriz[0][columna];
		int pos = 0;
		for (int i = 1; i < rest; i++) {
			if (Matriz[i][columna] != 0) {
				temp = Matriz[i][var + rest] / Matriz[i][columna];
				if (razon > temp && temp >= 0) {
					razon = temp;
					pos = i;
				}
			}

		}
		return pos;
	}

	public void NuevaTabla(int Fila, int Columna) {
		double pivote = Matriz[Fila][Columna], temp = 0;//--
		for (int i = 0; i < rest + var + 1; i++) {
			Matriz[Fila][i] = Matriz[Fila][i] / pivote;
		}
		for (int i = 0; i < rest + 1; i++) {
			temp = Matriz[i][Columna];
			for (int j = 0; j < var + rest + 1; j++) {
				if (i != Fila) {
					Matriz[i][j] = Matriz[i][j] - temp * Matriz[Fila][j];
				} else {
					break;
				}
			}
		}
	}

	public boolean ComprobarResultado() {
		boolean result = true;
		for (int i = 0; i < rest + var; i++) {
			if (Matriz[rest][i] < 0) {
				result = false;
				break;
			}
		}
		return result;
	}



	//minimizar
	public int ColumnaPivote1() {
		int pos = 0;
		double aux = Matriz[rest][0];
		for (int i = 1; i < rest + var; i++) {
			if (aux > Matriz[rest][i]) {
				aux = Matriz[rest][i];
				pos = i;
			}
		}
		return pos;
	}

	public int FilaPivote1() {
		int columna = ColumnaPivote1();
		double temp = 0, razon = Matriz[0][var + rest] / Matriz[0][columna];
		int pos = 0;
		for (int i = 1; i < rest; i++) {
			if (Matriz[i][columna] != 0) {
				temp = Matriz[i][var + rest] / Matriz[i][columna];
				if (razon > temp && temp >= 0) {
					razon = temp;
					pos = i;
				}
			}

		}
		return pos;
	}

	public void NuevaTabla1(int Fila, int Columna) {
		double pivote = Matriz[Fila][Columna], temp = 0;//--
		for (int i = 0; i < rest + var + 1; i++) {
			Matriz[Fila][i] = Matriz[Fila][i] / pivote;
		}
		for (int i = 0; i < rest + 1; i++) {
			temp = Matriz[i][Columna];
			for (int j = 0; j < var + rest + 1; j++) {
				if (i != Fila) {
					Matriz[i][j] = Matriz[i][j] - temp * Matriz[Fila][j];
				} else {
					break;
				}
			}
		}
	}

	public boolean ComprobarResultado1() {
		boolean result = true;
		for (int i = 0; i < rest + var; i++) {
			if (Matriz[rest][i] < 0) {
				result = false;
				break;
			}
		}
		return result;
	}
}
