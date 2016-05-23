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
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class KundUI extends JFrame {
	private static final long serialVersionUID = 6055583905065348375L;
	
	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("KundID");
	private final JTextField tfKundID = new JTextField();
	private final JLabel lblNamn = new JLabel("Namn");
	private final JLabel lblEmail = new JLabel("Email");
	private final JLabel lblTelefonnr = new JLabel("Telefonnr");
	private final JLabel lblAdress = new JLabel("Adress");
	private final JTextField tfNamn = new JTextField();
	private final JTextField tfEmail = new JTextField();
	private final JTextField tfTelefonnr = new JTextField();
	private final JTextField tfAdress = new JTextField();
	private final JList listKund = new JList();
	private final JButton btnSkapaKund = new JButton("Skapa Kund");
	private final JButton btnUppdateraKund = new JButton("Uppdatera Kund");
	private final JButton btnRaderaKund = new JButton("Radera Kund");
	
	private Controller controller;
	private DefaultListModel<String> model;
	private ArrayList<Kund> kundList = new ArrayList<Kund>();
	private final JButton btnTomFalt = new JButton("T\u00F6m F\u00E4lt");

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public KundUI(Controller controller) throws SQLException {
		super("Kundhanterare");
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
		tfKundID.setColumns(10);
		
		GridBagConstraints gbc_tfKundID = new GridBagConstraints();
		gbc_tfKundID.insets = new Insets(0, 0, 5, 5);
		gbc_tfKundID.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfKundID.gridx = 1;
		gbc_tfKundID.gridy = 0;
		tfKundID.setEnabled(false);
		contentPane.add(tfKundID, gbc_tfKundID);
		
		GridBagConstraints gbc_listKund = new GridBagConstraints();
		gbc_listKund.gridheight = 5;
		gbc_listKund.insets = new Insets(0, 0, 5, 0);
		gbc_listKund.fill = GridBagConstraints.BOTH;
		gbc_listKund.gridx = 2;
		gbc_listKund.gridy = 0;
		listKund.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					int kundId = listKund.getSelectedIndex();
					if(kundId != -1) {
						Kund kund = kundList.get(kundId);
						tfKundID.setText(Integer.toString(kund.getKundId()));
						tfNamn.setText(kund.getNamn());
						tfEmail.setText(kund.getEmail());
						tfTelefonnr.setText(kund.getTelefonnr());
						tfAdress.setText(kund.getAdress());
					}
			    }
			}
		});
		listKund.setBorder(new LineBorder(new Color(0, 0, 0)));
		listKund.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listKund.setPrototypeCellValue("KundID (0): Alexander Johansson");
		contentPane.add(new JScrollPane(listKund), gbc_listKund);
		
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
		contentPane.add(lblEmail, gbc_lblEmail);
		
		GridBagConstraints gbc_tfEmail = new GridBagConstraints();
		gbc_tfEmail.insets = new Insets(0, 0, 5, 5);
		gbc_tfEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfEmail.gridx = 1;
		gbc_tfEmail.gridy = 2;
		tfEmail.setColumns(10);
		contentPane.add(tfEmail, gbc_tfEmail);
		
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
		
		GridBagConstraints gbc_btnSkapaKund = new GridBagConstraints();
		gbc_btnSkapaKund.insets = new Insets(0, 0, 5, 5);
		gbc_btnSkapaKund.gridx = 1;
		gbc_btnSkapaKund.gridy = 5;
		btnSkapaKund.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.addCustomer(tfNamn.getText(), tfEmail.getText(), tfTelefonnr.getText(), tfAdress.getText());
					updateJList();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnSkapaKund, gbc_btnSkapaKund);
		
		GridBagConstraints gbc_btnRaderaKund = new GridBagConstraints();
		gbc_btnRaderaKund.gridx = 2;
		gbc_btnRaderaKund.gridy = 6;
		btnRaderaKund.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Kund kund = kundList.get(listKund.getSelectedIndex());
				try {
					controller.deleteCustomer(kund);
					updateJList();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnRaderaKund, gbc_btnRaderaKund);
		
		GridBagConstraints gbc_btnTomFalt = new GridBagConstraints();
		gbc_btnTomFalt.insets = new Insets(0, 0, 0, 5);
		gbc_btnTomFalt.gridx = 1;
		gbc_btnTomFalt.gridy = 6;
		btnTomFalt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfKundID.setText(null);
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
				Kund kund = kundList.get(listKund.getSelectedIndex());
				try {
					controller.updateCustomer(kund.getKundId(), tfNamn.getText(), tfEmail.getText(), tfTelefonnr.getText(), tfAdress.getText());
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
	    kundList = controller.getCustomers();
	    for(Kund k : kundList){
	         model.addElement(k.toString());
	    }
	    
	    listKund.setModel(model);
	    listKund.setSelectedIndex(0);
	}
}
