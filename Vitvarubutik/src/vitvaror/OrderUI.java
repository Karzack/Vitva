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
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.ListSelectionModel;

public class OrderUI extends JFrame {

	private JPanel contentPane;
	private final JList listKund = new JList();
	private final JList listArtiklar = new JList();
	private final JButton btnLaggOrder = new JButton("L\u00E4gg Order");
	
	private Controller controller;
	
	private DefaultListModel<String> modelKund;
	private DefaultListModel<String> modelArtiklar;
	
	private ArrayList<Kund> kundList;
	private ArrayList<Artikel> artikelList;

	/**
	 * Create the frame.
	 */
	public OrderUI(Controller controller) {
		this.controller = controller;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow]", "[grow][]"));
		listKund.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listKund.setBorder(new TitledBorder(null, "Kund", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(new JScrollPane(listKund), "cell 0 0,grow");
		listArtiklar.setBorder(new TitledBorder(null, "Artiklar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(new JScrollPane(listArtiklar), "cell 1 0,grow");
		
		btnLaggOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int kundId = listKund.getSelectedIndex();
				int[] artikelIds = listArtiklar.getSelectedIndices();
				if(kundId != -1 && artikelIds.length > 0) {
					
				}
			}
		});
		contentPane.add(btnLaggOrder, "cell 1 1,alignx center,aligny center");
		
		
	}
	
	private void updateJList() throws SQLException{
	    modelKund = new DefaultListModel<String>();
	    kundList = controller.getCustomers();
	    for(Kund k : kundList){
	         modelKund.addElement(k.toString());
	    }
	    
	    listKund.setModel(modelKund);
	    listKund.setSelectedIndex(0);
	    
	    modelArtiklar = new DefaultListModel<String>();
	    artikelList = controller.getArticles();
	    for(Artikel a : artikelList){
	         modelArtiklar.addElement(a.toString());
	    }
	    
	   	listArtiklar.setModel(modelArtiklar);
	    listArtiklar.setSelectedIndex(0);
	}

}
