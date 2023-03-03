/**
 * FenGestionClients.java
 */
package tp2;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author dquan
 * Classe : FenGestionClients
 * Date : 23 oct. 2021
 */
public class FenGestionClients extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_3;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JButton button;
	private JRadioButton radiocourant;
	private JLabel label;
	private JRadioButton radioepargne;
	private JComboBox<Double> comboBox;
	private JButton button_1;
	private JLabel label_1;
	private JTextField textField;
	private JButton button_2;
	private JButton button_3;
	private JButton btnconfirmer;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JButton btnNouveauClient;
	private JTextField textNom;
	private JTextField textPrenom;
	private JButton btnReleveClient;
	private JComboBox<Client> comboBox_1;
	private JList<Compte> list;
	private JScrollPane scrollPane_1;
	private JTextArea textArea;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JPanel panel_2;
	private DefaultComboBoxModel<Client> modelclient = new DefaultComboBoxModel<Client>();
	private DefaultComboBoxModel<Double> modeltaux = new DefaultComboBoxModel<Double>();
	private DefaultListModel<Compte>  modelcompte = new DefaultListModel<Compte>();
	private DefaultListModel<String>  modelcomptelist = new DefaultListModel<String>();

	private Banque b1 = new Banque("BMO",10);
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JList<String> list_1;
	private JScrollPane scrollPane_2;
	private JMenuBar menuBar;
	private JMenu mnFichier;
	private JMenuItem mntmSauvegarder;
	private JMenuItem mntmCharger;
	private JMenuItem mntmQuitter;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Authentification auth = new Authentification();
					auth.setModal(true);
					auth.setVisible(true);

					FenGestionClients frame = new FenGestionClients();

					frame.setVisible(true);
					frame.setTitle("Banque");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenGestionClients() {
		initialize();
		comboBox_1.setModel(modelclient);
		btnconfirmer.setEnabled(false);
		list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		list.setModel(modelcompte);
		list_1.setModel(modelcomptelist);
		list_1.setEnabled(false);
		comboBox.setModel(modeltaux);
		modeltaux.addElement(5.0);
		modeltaux.addElement(15.0);
		modeltaux.addElement(25.0);


	}
	private void initialize() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 474);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);

		mntmSauvegarder = new JMenuItem("Sauvegarder");
		mntmSauvegarder.addActionListener(new MntmSauvegarderActionListener());
		mnFichier.add(mntmSauvegarder);

		mntmCharger = new JMenuItem("Charger");
		mntmCharger.addActionListener(new MntmChargerActionListener());
		mnFichier.add(mntmCharger);

		mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new MntmQuitterActionListener());
		mnFichier.add(mntmQuitter);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new GridLayout(3, 2, 0, 0));

		lblPrenom = new JLabel("Prenom");
		panel_5.add(lblPrenom);

		textPrenom = new JTextField();
		panel_5.add(textPrenom);
		textPrenom.setColumns(10);

		lblNom = new JLabel("Nom");
		panel_5.add(lblNom);

		textNom = new JTextField();
		panel_5.add(textNom);
		textNom.setColumns(10);

		btnNouveauClient = new JButton("Nouveau Client");
		btnNouveauClient.addActionListener(new BtnNouveauClientActionListener());
		panel_5.add(btnNouveauClient);

		btnReleveClient = new JButton("Releve Client");
		btnReleveClient.addActionListener(new BtnReleveClientActionListener());
		panel_5.add(btnReleveClient);

		panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Clients", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));

		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ComboBox_1ItemListener());
		panel_6.add(comboBox_1);

		panel_7 = new JPanel();
		panel.add(panel_7);
		panel_7.setBorder(new TitledBorder(null, "Gestion Client", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));

		button = new JButton("Ouvrir Compte");
		button.addActionListener(new ButtonActionListener());
		panel_7.add(button);

		radiocourant = new JRadioButton("Courant");
		buttonGroup.add(radiocourant);
		panel_7.add(radiocourant);

		label = new JLabel("Taux De rendement");
		panel_7.add(label);

		radioepargne = new JRadioButton("Epargne");
		buttonGroup.add(radioepargne);
		panel_7.add(radioepargne);

		comboBox = new JComboBox();
		panel_7.add(comboBox);

		button_1 = new JButton("Fermer Compte");
		button_1.addActionListener(new Button_1ActionListener());
		panel_7.add(button_1);

		label_1 = new JLabel("Montant");
		panel_7.add(label_1);

		textField = new JTextField();
		textField.setColumns(10);
		panel_7.add(textField);

		button_2 = new JButton("Transaction");
		button_2.addActionListener(new Button_2ActionListener());
		panel_7.add(button_2);

		button_3 = new JButton("Virement");
		button_3.addActionListener(new Button_3ActionListener());
		panel_7.add(button_3);

		btnconfirmer = new JButton("Confirmer");
		btnconfirmer.addActionListener(new Button_4ActionListener());
		panel_7.add(btnconfirmer);

		panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		scrollPane_2 = new JScrollPane();
		panel_1.add(scrollPane_2);

		list = new JList();
		list.addListSelectionListener(new ListListSelectionListener());
		scrollPane_2.setViewportView(list);

		scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);

		panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		scrollPane = new JScrollPane();
		panel_2.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}

	private class BtnNouveauClientActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			try {

				String nom = textNom.getText();
				String prenom = textPrenom.getText();
				if(!nom.isBlank() & !prenom.isBlank()) {
					Client c1 = new Client(nom,prenom);
					b1.ajouter(c1);
					modelclient.addElement(c1);}else {
						throw new Exception("Zone de Texte Vide");
					}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				textArea.setText(e1.getMessage());
			}



		}
	}
	private class ButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {Client c1 = (Client) modelclient.getSelectedItem();
			if(radioepargne.isSelected()) {
				double taux = (double) comboBox.getSelectedItem();
				Compte com = new CompteEpargne(0,LocalDate.now(),taux);
				c1.ouvrir(com);
				modelcompte.addElement(com);
				modelcomptelist.addElement(com.getNumcompte());
				System.out.println(((CompteEpargne) com).getTaux());
			}else if(radiocourant.isSelected()) {
				Compte com = new CompteCourant(0,LocalDate.now());
				c1.ouvrir(com);
				modelcompte.addElement(com);
				modelcomptelist.addElement(com.getNumcompte());


			}


			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}



		}
	}
	private class ComboBox_1ItemListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			modelcompte.removeAllElements();
			if(comboBox_1.getItemCount() > 0) {
				Client c1 = (Client) comboBox_1.getSelectedItem();
				System.out.println(c1.ListerComptes());
				if(c1 != null) {
					for(Compte com: c1.getListCB()) {

						modelcompte.addElement(com);
					}}}

		}
	}
	private class Button_1ActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				Compte c1 = list.getSelectedValue();
				Client com = (Client) comboBox_1.getSelectedItem();
				com.fermer(c1.getNumcompte());


				modelcompte.removeElement(c1);
				modelcomptelist.removeElement(c1);


			} catch (Exception e1) {
				// TODO Auto-generated catch block
				textArea.setText("Selectionner un compte ");
			}

		}
	}
	private class Button_3ActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			btnconfirmer.setEnabled(true);
			list_1.setEnabled(true);

		}
	}
	private class ListListSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {





			try {	if(modelclient.getSize() > 0) {
				Client currclient = (Client) comboBox_1.getSelectedItem();

				if(modelcompte.getSize() > 0) {
					Compte c1 = list.getSelectedValue();

					textArea.setText("");
					textArea.setText(currclient.toString()+"\n");
					textArea.append(c1.toString());


				}
			}
			}catch(NullPointerException e1) {
				list.repaint();
				textArea.setText("");
			}}
	}
	private class BtnReleveClientActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Client c1 = (Client) comboBox_1.getSelectedItem();
			textArea.setText(c1.toString()+"\n");
			for (Compte  compte: c1.getListCB()) {
				textArea.append(compte.toString()+"\n");
			}
		}
	}
	private class Button_2ActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {	Compte c1 = list.getSelectedValue();
			int pos = comboBox.getSelectedIndex();
			FenTransactionCompte ft1 = new FenTransactionCompte(c1);
			ft1.setTitle(c1.toString());
			ft1.setModal(true);
			ft1.setVisible(true);

			c1 = ft1.getC1();
			System.out.println(c1.toString());
			b1.getListclient().get(comboBox.getSelectedIndex()).getListCB().set(pos, c1);
			list.repaint();
			list_1.repaint();
			}catch(Exception e1) {
				textArea.setText("Selectionner un Compte");
			}
		}
	}
	private class Button_4ActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				String montants = textField.getText();
				double montant = Double.parseDouble(montants);
				Compte donnant = list.getSelectedValue();
				String recevant = list_1.getSelectedValue();
				Compte crecevant = b1.ObtenirCompteParNum(recevant);
				donnant.virer(montant, crecevant);
				textArea.setText(donnant.historique1.get(donnant.historique1.size()-1).toString());
				list.repaint();
				list_1.repaint();

				btnconfirmer.setEnabled(false);
				list_1.setEnabled(false);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				textArea.setText(e1.getMessage());
			}
		}
	}
	private class MntmChargerActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				b1.ChargerTxt("src/Banque.txt");
				modelclient.removeAllElements();
				for (Client c1 : b1.getListclient()) {
					modelclient.addElement(c1);
					for (Compte compte : c1.getListCB()) {
						modelcomptelist.addElement(compte.getNumcompte());
					}
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				textArea.setText(e1.getMessage());
			}
		}
	}
	private class MntmSauvegarderActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			b1.EnregistrerJSON("Banque.json");
		}
	}
	private class MntmQuitterActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	public void refresh(Client c1) {

		modelcompte.removeAllElements();

		for (Compte point: c1.getListCB()) {
			modelcompte.addElement(point);
		}
	}
}

