package vitvaror;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;

public class OrderUI extends JFrame {

	private JPanel contentPane;
	private final JList listKund = new JList();
	private final JList listArtiklar = new JList();
	private final JButton btnLaggOrder = new JButton("L\u00E4gg Order");

	private Controller controller;
	private Artikel answer;
	private Kund kund;
	private int id;
	private DefaultListModel<String> modelKund;
	private DefaultListModel<String> modelArtiklar;

	private ArrayList<Kund> kundList;
	private ArrayList<Artikel> artikelList;
	private final JLabel lblAntal = new JLabel("Antal:");
	private final JTextField tfAntal = new JTextField();

	private final JLabel lblSokning = new JLabel("Sokning:");
	private final JTextField tfSokning = new JTextField();
	private final JButton btnSokning = new JButton("L\u00E4gg Sokning");
	private final JButton btngetAll = new JButton("Hamta alla Artiklar");
	private int user;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public OrderUI(Controller controller, int user, int id) throws SQLException {
		super("Lägg order");
		this.user = user;
		this.controller = controller;
		this.id = id;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][][grow][]", "[grow][]"));

		listKund.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listKund.setBorder(new TitledBorder(null, "Kund", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(new JScrollPane(listKund), "cell 1 0,grow");
		listArtiklar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listArtiklar.setBorder(new TitledBorder(null, "Artiklar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(new JScrollPane(listArtiklar), "cell 4 0,grow");

		btnLaggOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int kundId = listKund.getSelectedIndex();
				int artikelId = listArtiklar.getSelectedIndex();
				int antal = 1;
				try {
					antal = Integer.parseInt(tfAntal.getText());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}

				if (kundId != -1 && artikelId != -1) {
					Kund kund = kundList.get(kundId);
					Artikel artikel = artikelList.get(artikelId);
					try {
						int orderId = controller.addOrder(kund.getKundId(), System.currentTimeMillis() / 1000L);
						int orderradId = controller.addOrderrad(orderId);

						controller.addArticleToOrderrad(orderradId, artikel, antal);
						updateJList();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		contentPane.add(btnLaggOrder, "cell 1 1,alignx center,aligny center");

		contentPane.add(lblAntal, "cell 2 1,alignx trailing");

		tfAntal.setColumns(10);
		contentPane.add(tfAntal, "cell 3 1 2 1,growx");

		contentPane.add(btngetAll, "cell 1 1,alignx center,aligny center");

		btnSokning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sokning = 0;

				try {
					sokning = Integer.parseInt(tfSokning.getText());

				} catch (NumberFormatException e) {
					System.out.println("Not found");
				}

				try {
					
					modelArtiklar.clear();
					answer = controller.getArticle(sokning);

				} catch (SQLException e) {
					System.out.println("Not found internal error");
					e.printStackTrace();
				}

				modelArtiklar.addElement(answer.toString());
				listArtiklar.setModel(modelArtiklar);
				listArtiklar.setSelectedIndex(0);
			}
		});

		btngetAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					updateJList();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		});

		contentPane.add(btnSokning, "cell 1 1,alignx center,aligny center");
		contentPane.add(lblSokning, "cell 3 1 2 1,growx");
		tfSokning.setColumns(10);
		contentPane.add(tfSokning, "cell 3 1 2 1,growx");
		 updateJList();
	}

	private void updateJList() throws SQLException {
		switch (user) {
		case 1:
			modelKund = new DefaultListModel<String>();
			kundList = controller.getCustomers();
			for (Kund k : kundList) {
				modelKund.addElement(k.toString());
			}
			listKund.setModel(modelKund);
			listKund.setSelectedIndex(0);
			break;

		case 2:
			modelKund = new DefaultListModel<String>();
			kundList=controller.getCustomers();
			kund = controller.getCustomer(id);
			
			modelKund.addElement(kund.toString());
			listKund.setModel(modelKund);
			listKund.setSelectedIndex(0);
			break;
		}

		modelArtiklar = new DefaultListModel<String>();
		artikelList = controller.getArticles();
		for (Artikel a : artikelList) {
			modelArtiklar.addElement(a.toString());
		}

		listArtiklar.setModel(modelArtiklar);
		listArtiklar.setSelectedIndex(0);
	}

}
