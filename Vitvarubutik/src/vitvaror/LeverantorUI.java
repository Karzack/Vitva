package vitvaror;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class LeverantorUI extends JFrame{

	private static final long serialVersionUID = 6055583905065348375L;

	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("LeverantörID");
	private final JTextField tfLeverantorID = new JTextField();
	private final JLabel lblNamn = new JLabel("Namn");
	private final JLabel lblTelefonnr = new JLabel("Telefonnr");
	private final JLabel lblAdress = new JLabel("Adress");
	private final JTextField tfNamn = new JTextField();
	private final JTextField tfEmail = new JTextField();
	private final JTextField tfTelefonnr = new JTextField();
	private final JTextField tfAdress = new JTextField();
	private final JList listLeverantor = new JList();
	private final JButton btnSkapaLeverantor = new JButton("Skapa Leverantör");
	private final JButton btnUppdateraKund = new JButton("Uppdatera Leveranör");
	private final JButton btnRaderaKund = new JButton("Radera Leverantör");

	private Controller controller;
	private DefaultListModel<String> model;
	private ArrayList<Leverantor> leverantorList = new ArrayList<Leverantor>();
	private final JButton btnTomFalt = new JButton("T\u00F6m F\u00E4lt");

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public LeverantorUI(Controller controller) throws SQLException {
		super("Leverantörhanterare");
		this.controller = controller;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		tfLeverantorID.setColumns(10);

		GridBagConstraints gbc_tfKundID = new GridBagConstraints();
		gbc_tfKundID.insets = new Insets(0, 0, 5, 5);
		gbc_tfKundID.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfKundID.gridx = 1;
		gbc_tfKundID.gridy = 0;
		tfLeverantorID.setEnabled(false);
		contentPane.add(tfLeverantorID, gbc_tfKundID);

		GridBagConstraints gbc_listKund = new GridBagConstraints();
		gbc_listKund.gridheight = 5;
		gbc_listKund.insets = new Insets(0, 0, 5, 0);
		gbc_listKund.fill = GridBagConstraints.BOTH;
		gbc_listKund.gridx = 2;
		gbc_listKund.gridy = 0;
		listLeverantor.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					int levid = listLeverantor.getSelectedIndex();
					if(levid != -1) {
						Leverantor leverantor = leverantorList.get(levid);
						tfLeverantorID.setText(Integer.toString(leverantor.getLevid()));
						tfNamn.setText(leverantor.getNamn());
						tfTelefonnr.setText(leverantor.getTelefonr());
						tfAdress.setText(leverantor.getAdress());
					}
				}
			}
		});
		listLeverantor.setBorder(new LineBorder(new Color(0, 0, 0)));
		listLeverantor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listLeverantor.setPrototypeCellValue("LeverantörID (0): Freja");
		contentPane.add(new JScrollPane(listLeverantor), gbc_listKund);

		GridBagConstraints gbc_lblNamn = new GridBagConstraints();
		gbc_lblNamn.anchor = GridBagConstraints.EAST;
		gbc_lblNamn.insets = new Insets(0, 0, 5, 5);
		gbc_lblNamn.gridx = 0;
		gbc_lblNamn.gridy = 1;
		contentPane.add(lblNamn, gbc_lblNamn);

		GridBagConstraints gbc_tfNamn = new GridBagConstraints();
		gbc_tfNamn.insets = new Insets(0, 0, 5, 5);
		gbc_tfNamn.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNamn.gridx = 1;
		gbc_tfNamn.gridy = 1;
		tfNamn.setColumns(10);
		contentPane.add(tfNamn, gbc_tfNamn);

		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 2;


		GridBagConstraints gbc_lblTelefonnr = new GridBagConstraints();
		gbc_lblTelefonnr.anchor = GridBagConstraints.EAST;
		gbc_lblTelefonnr.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefonnr.gridx = 0;
		gbc_lblTelefonnr.gridy = 3;
		contentPane.add(lblTelefonnr, gbc_lblTelefonnr);

		GridBagConstraints gbc_tfTelefonnr = new GridBagConstraints();
		gbc_tfTelefonnr.insets = new Insets(0, 0, 5, 5);
		gbc_tfTelefonnr.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTelefonnr.gridx = 1;
		gbc_tfTelefonnr.gridy = 3;
		tfTelefonnr.setColumns(10);
		contentPane.add(tfTelefonnr, gbc_tfTelefonnr);

		GridBagConstraints gbc_lblAdress = new GridBagConstraints();
		gbc_lblAdress.anchor = GridBagConstraints.EAST;
		gbc_lblAdress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdress.gridx = 0;
		gbc_lblAdress.gridy = 4;
		contentPane.add(lblAdress, gbc_lblAdress);

		GridBagConstraints gbc_tfAdress = new GridBagConstraints();
		gbc_tfAdress.insets = new Insets(0, 0, 5, 5);
		gbc_tfAdress.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAdress.gridx = 1;
		gbc_tfAdress.gridy = 4;
		tfAdress.setColumns(10);
		contentPane.add(tfAdress, gbc_tfAdress);

		GridBagConstraints gbc_btnSkapaLeverantor = new GridBagConstraints();
		gbc_btnSkapaLeverantor.insets = new Insets(0, 0, 5, 5);
		gbc_btnSkapaLeverantor.gridx = 1;
		gbc_btnSkapaLeverantor.gridy = 5;
		btnSkapaLeverantor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.addCustomer(tfNamn.getText(), tfEmail.getText(), tfTelefonnr.getText(), tfAdress.getText());
					updateJList();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnSkapaLeverantor, gbc_btnSkapaLeverantor);

		GridBagConstraints gbc_btnRaderaLeverantor = new GridBagConstraints();
		gbc_btnRaderaLeverantor.gridx = 2;
		gbc_btnRaderaLeverantor.gridy = 6;
		btnRaderaKund.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Leverantor leverantor = leverantorList.get(listLeverantor.getSelectedIndex());
				try {
					controller.deleteDeliverer(leverantor);
					updateJList();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnRaderaKund, gbc_btnRaderaLeverantor);

		GridBagConstraints gbc_btnTomFalt = new GridBagConstraints();
		gbc_btnTomFalt.insets = new Insets(0, 0, 0, 5);
		gbc_btnTomFalt.gridx = 1;
		gbc_btnTomFalt.gridy = 6;
		btnTomFalt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfLeverantorID.setText(null);
				tfNamn.setText("");
				tfEmail.setText("");
				tfTelefonnr.setText("");
				tfAdress.setText("");
			}
		});
		contentPane.add(btnTomFalt, gbc_btnTomFalt);

		GridBagConstraints gbc_btnUppdateraKund = new GridBagConstraints();
		gbc_btnUppdateraKund.insets = new Insets(0, 0, 5, 0);
		gbc_btnUppdateraKund.gridx = 2;
		gbc_btnUppdateraKund.gridy = 5;
		btnUppdateraKund.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Leverantor leverantor = leverantorList.get(listLeverantor.getSelectedIndex());
				try {
					controller.updateCustomer(leverantor.getLevid(), tfNamn.getText(), tfEmail.getText(), tfTelefonnr.getText(), tfAdress.getText());
					updateJList();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnUppdateraKund, gbc_btnUppdateraKund);

		updateJList();
	}

	private void updateJList() throws SQLException{
		model = new DefaultListModel<String>();
		leverantorList = controller.getDeliverers();
		for(Leverantor l : leverantorList){
			model.addElement(l.toString());
		}    

		listLeverantor.setModel(model);
		listLeverantor.setSelectedIndex(0);
	}
}

