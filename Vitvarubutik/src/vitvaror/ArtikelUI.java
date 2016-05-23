package vitvaror;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ArtikelUI extends JFrame {
	private static final long serialVersionUID = 6055583905065348375L;

	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("ArtikelID");
	private final JTextField tfArtikelID = new JTextField();

	private final JLabel lblNamn = new JLabel("Namn");
	private final JLabel lblTillverkare = new JLabel("Tillverkare");
	private final JLabel lblModell = new JLabel("Modell");
	private final JLabel lblEnergiKlass = new JLabel("Energiklass");
	private final JLabel lblPris = new JLabel("Pris");
	private final JLabel lblInkopspris = new JLabel("Inköppris");
	private final JLabel lblAntal = new JLabel("Antal");
	private final JLabel lblBeskrivning = new JLabel("Beskrivning");

	private final JTextField tfNamn = new JTextField();
	private final JTextField tfTillverkare = new JTextField();
	private final JTextField tfModell = new JTextField();
	private final JTextField tfEnergiKlass = new JTextField();
	private final JTextField tfPris = new JTextField();
	private final JTextField tfInkopspris = new JTextField();
	private final JTextField tfAntal = new JTextField();
	private final JTextField tfBeskrivning = new JTextField();

	private final JList listArtikel = new JList();
	private final JButton btnSkapaArtikel = new JButton("Skapa Artikel");
	private final JButton btnUppdateraArtikel = new JButton("Uppdatera Artikel");
	private final JButton btnRaderaArtikel = new JButton("Radera Artikel");

	private Controller controller;
	private DefaultListModel<String> model;
	private ArrayList<Artikel> artikelList = new ArrayList<Artikel>();
	private final JButton btnTomFalt = new JButton("T\u00F6m F\u00E4lt");

	private GridBagConstraints gbc_tfArtikelID;
	private GridBagConstraints gbc_listArtikel;

	private GridBagConstraints gbc_lblTillverkare;
	private GridBagConstraints gbc_tfTillverkare;
	private GridBagConstraints gbc_lblModell;
	private GridBagConstraints gbc_tfModell;

	private GridBagConstraints gbc_lblEnergiklass;
	private GridBagConstraints gbc_Energiklass;

	private GridBagConstraints gbc_btnSkapaArtikel;

	private GridBagConstraints gbc_lblPris;
	private GridBagConstraints gbc_tfPris;

	private GridBagConstraints gbc_lblInkopspris;
	private GridBagConstraints gbc_tfInkopspris;

	private GridBagConstraints gbc_lblAntal;
	private GridBagConstraints gbc_tfAntal;

	private GridBagConstraints gbc_lblBeskrivning;
	private GridBagConstraints gbc_tfBeskrivning;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public ArtikelUI(Controller controller) throws SQLException {
		super("Kundhanterare");
		this.controller = controller;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		tfArtikelID.setColumns(10);

		gbc_tfArtikelID = new GridBagConstraints();
		gbc_tfArtikelID.insets = new Insets(0, 0, 5, 5);
		gbc_tfArtikelID.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfArtikelID.gridx = 1;
		gbc_tfArtikelID.gridy = 0;
		tfArtikelID.setEnabled(false);
		contentPane.add(tfArtikelID, gbc_tfArtikelID);

		gbc_listArtikel = new GridBagConstraints();
		gbc_listArtikel.gridheight = 5;
		gbc_listArtikel.insets = new Insets(0, 0, 5, 0);
		gbc_listArtikel.fill = GridBagConstraints.BOTH;
		gbc_listArtikel.gridx = 2;
		gbc_listArtikel.gridy = 0;
		listArtikel.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					int artikelId = listArtikel.getSelectedIndex();
					if (artikelId != -1) {
						Artikel artikel = artikelList.get(artikelId);
						tfArtikelID.setText(Integer.toString(artikel.getArtikelId()));
						tfNamn.setText(artikel.getNamn());
						tfTillverkare.setText(artikel.getTillverkare());
						tfModell.setText(artikel.getModell());
						tfEnergiKlass.setText(artikel.getEnergiklass());

						tfPris.setText(Integer.toString(artikel.getPris()));
						tfInkopspris.setText(Integer.toString(artikel.getInkopspris()));
						tfAntal.setText(Integer.toString(artikel.getAntal()));

					}
				}
			}
		});

		listArtikel.setBorder(new LineBorder(new Color(0, 0, 0)));
		listArtikel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listArtikel.setPrototypeCellValue("ArtikelID (0): Alexander Johansson : 10000");
		contentPane.add(new JScrollPane(listArtikel), gbc_listArtikel);

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

		gbc_lblBeskrivning = new GridBagConstraints();
		gbc_lblBeskrivning.anchor = GridBagConstraints.EAST;
		gbc_lblBeskrivning.insets = new Insets(0, 0, 5, 5);
		gbc_lblBeskrivning.gridx = 0;
		gbc_lblBeskrivning.gridy = 3;
		contentPane.add(lblBeskrivning, gbc_lblBeskrivning);

		gbc_tfBeskrivning = new GridBagConstraints();
		gbc_tfBeskrivning.insets = new Insets(0, 0, 5, 5);
		gbc_tfBeskrivning.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfBeskrivning.gridx = 1;
		gbc_tfBeskrivning.gridy = 3;
		tfBeskrivning.setColumns(10);
		contentPane.add(tfBeskrivning, gbc_tfBeskrivning);

		gbc_lblAntal = new GridBagConstraints();
		gbc_lblAntal.anchor = GridBagConstraints.EAST;
		gbc_lblAntal.insets = new Insets(0, 0, 5, 5);
		gbc_lblAntal.gridx = 0;
		gbc_lblAntal.gridy = 4;
		contentPane.add(lblAntal, gbc_lblAntal);

		gbc_tfAntal = new GridBagConstraints();
		gbc_tfAntal.insets = new Insets(0, 0, 5, 5);
		gbc_tfAntal.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAntal.gridx = 1;
		gbc_tfAntal.gridy = 4;
		tfAntal.setColumns(10);
		contentPane.add(tfAntal, gbc_tfAntal);

		gbc_lblInkopspris = new GridBagConstraints();
		gbc_lblInkopspris.anchor = GridBagConstraints.EAST;
		gbc_lblInkopspris.insets = new Insets(0, 0, 5, 5);
		gbc_lblInkopspris.gridx = 0;
		gbc_lblInkopspris.gridy = 5;
		contentPane.add(lblInkopspris, gbc_lblInkopspris);

		gbc_tfInkopspris = new GridBagConstraints();
		gbc_tfInkopspris.insets = new Insets(0, 0, 5, 5);
		gbc_tfInkopspris.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfInkopspris.gridx = 1;
		gbc_tfInkopspris.gridy = 5;
		tfInkopspris.setColumns(10);
		contentPane.add(tfInkopspris, gbc_tfInkopspris);

		gbc_lblPris = new GridBagConstraints();
		gbc_lblPris.anchor = GridBagConstraints.EAST;
		gbc_lblPris.insets = new Insets(0, 0, 5, 5);
		gbc_lblPris.gridx = 0;
		gbc_lblPris.gridy = 6;
		contentPane.add(lblPris, gbc_lblPris);

		gbc_tfPris = new GridBagConstraints();
		gbc_tfPris.insets = new Insets(0, 0, 5, 5);
		gbc_tfPris.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPris.gridx = 1;
		gbc_tfPris.gridy = 6;
		tfPris.setColumns(10);
		contentPane.add(tfPris, gbc_tfPris);

		gbc_lblTillverkare = new GridBagConstraints();
		gbc_lblTillverkare.anchor = GridBagConstraints.EAST;
		gbc_lblTillverkare.insets = new Insets(0, 0, 5, 5);
		gbc_lblTillverkare.gridx = 0;
		gbc_lblTillverkare.gridy = 7;
		contentPane.add(lblTillverkare, gbc_lblTillverkare);

		gbc_tfTillverkare = new GridBagConstraints();
		gbc_tfTillverkare.insets = new Insets(0, 0, 5, 5);
		gbc_tfTillverkare.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTillverkare.gridx = 1;
		gbc_tfTillverkare.gridy = 7;
		tfTillverkare.setColumns(10);
		contentPane.add(tfTillverkare, gbc_tfTillverkare);

		gbc_lblModell = new GridBagConstraints();
		gbc_lblModell.anchor = GridBagConstraints.EAST;
		gbc_lblModell.insets = new Insets(0, 0, 5, 5);
		gbc_lblModell.gridx = 0;
		gbc_lblModell.gridy = 8;
		contentPane.add(lblModell, gbc_lblModell);

		gbc_tfModell = new GridBagConstraints();
		gbc_tfModell.insets = new Insets(0, 0, 5, 5);
		gbc_tfModell.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfModell.gridx = 1;
		gbc_tfModell.gridy = 8;
		tfModell.setColumns(10);
		contentPane.add(tfModell, gbc_tfModell);

		gbc_lblEnergiklass = new GridBagConstraints();
		gbc_lblEnergiklass.anchor = GridBagConstraints.EAST;
		gbc_lblEnergiklass.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnergiklass.gridx = 0;
		gbc_lblEnergiklass.gridy = 9;
		contentPane.add(lblEnergiKlass, gbc_lblEnergiklass);

		gbc_Energiklass = new GridBagConstraints();
		gbc_Energiklass.insets = new Insets(0, 0, 5, 5);
		gbc_Energiklass.fill = GridBagConstraints.HORIZONTAL;
		gbc_Energiklass.gridx = 1;
		gbc_Energiklass.gridy = 9;
		tfEnergiKlass.setColumns(10);
		contentPane.add(tfEnergiKlass, gbc_Energiklass);

		gbc_btnSkapaArtikel = new GridBagConstraints();
		gbc_btnSkapaArtikel.insets = new Insets(0, 0, 5, 5);
		gbc_btnSkapaArtikel.gridx = 1;
		gbc_btnSkapaArtikel.gridy = 10;

		btnSkapaArtikel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.addArticle(tfNamn.getText(), tfTillverkare.getText(), tfModell.getText(),
							tfEnergiKlass.getText(), tfBeskrivning.getText(), (Integer.parseInt(tfPris.getText())),
							(Integer.parseInt(tfInkopspris.getText())), (Integer.parseInt(tfAntal.getText())));

					updateJList();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnSkapaArtikel, gbc_btnSkapaArtikel);

		GridBagConstraints gbc_btnRaderaKund = new GridBagConstraints();
		gbc_btnRaderaKund.gridx = 2;
		gbc_btnRaderaKund.gridy = 6;

		btnRaderaArtikel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Artikel artikel = artikelList.get(listArtikel.getSelectedIndex());
				try {
					controller.deleteArticle(artikel);
					updateJList();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnRaderaArtikel, gbc_btnRaderaKund);

		GridBagConstraints gbc_btnTomFalt = new GridBagConstraints();
		gbc_btnTomFalt.insets = new Insets(0, 0, 0, 5);
		gbc_btnTomFalt.gridx = 1;
		gbc_btnTomFalt.gridy = 11;
		btnTomFalt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfArtikelID.setText(null);
				tfNamn.setText("");
				tfTillverkare.setText("");
				tfModell.setText("");
				tfEnergiKlass.setText("");
			}
		});
		contentPane.add(btnTomFalt, gbc_btnTomFalt);

		GridBagConstraints gbc_btnUppdateraKund = new GridBagConstraints();
		gbc_btnUppdateraKund.insets = new Insets(0, 0, 5, 0);
		gbc_btnUppdateraKund.gridx = 2;
		gbc_btnUppdateraKund.gridy = 5;
		btnUppdateraArtikel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Artikel artikel = artikelList.get(listArtikel.getSelectedIndex());
				try {
					controller.updateArticle(artikel.getArtikelId(), tfNamn.getText(), tfTillverkare.getText(),
							tfModell.getText(), tfEnergiKlass.getText(), tfBeskrivning.getText(),
							(Integer.parseInt(tfPris.getText())), (Integer.parseInt(tfInkopspris.getText())),
							(Integer.parseInt(tfAntal.getText())));
					updateJList();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnUppdateraArtikel, gbc_btnUppdateraKund);

		updateJList();
	}

	private void updateJList() throws SQLException {
		model = new DefaultListModel<String>();
		artikelList = controller.getArticles();
		for (Artikel a : artikelList) {
			model.addElement(a.toString());
		}

		listArtikel.setModel(model);
		listArtikel.setSelectedIndex(0);
	}
}
