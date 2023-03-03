/**
 * FenTransactionCompte.java
 */
package tp2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * @author dquan
 * Classe : FenTransactionCompte
 * Date : 29 oct. 2021
 */
public class FenTransactionCompte extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	JRadioButton rdbtnDepot = new JRadioButton("Depot");
	JTextArea textArea = new JTextArea();

	JRadioButton rdbtnRetrait = new JRadioButton("Retrait");
	JRadioButton rdbtnMiseAJour = new JRadioButton("Mise a jour");
	JButton btnNewButton = new JButton("Confirmer");
	JLabel lblSolde = new JLabel("Solde");
	JButton btnNewButton_2 = new JButton("Annuler");
	JPanel panel = new JPanel();
	Compte c1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final JLabel lblMessage = new JLabel("Message");

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public FenTransactionCompte(Compte c1) {
		initialize();
		this.c1 = c1;
		textField_1.setEnabled(false);
		textField_1.setText(c1.getSolde()+"");

		{
			contentPanel.add(lblMessage);
		}

	}
	private void initialize() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new GridLayout(4, 2, 0, 0));
		{
			JLabel lblMontant = new JLabel("Montant");
			contentPanel.add(lblMontant);
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{

			contentPanel.add(lblSolde);
		}
		{
			textField = new JTextField();
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{

			buttonGroup.add(rdbtnDepot);
			rdbtnDepot.addActionListener(new RdbtnDepotActionListener());
			contentPanel.add(rdbtnDepot);
		}
		{
			buttonGroup.add(rdbtnRetrait);
			rdbtnRetrait.addActionListener(new RdbtnRetraitActionListener());

			contentPanel.add(rdbtnRetrait);
		}
		{

			buttonGroup.add(rdbtnMiseAJour);
			rdbtnMiseAJour.addActionListener(new RdbtnMiseAJourActionListener());
			contentPanel.add(rdbtnMiseAJour);
		}
		{

			getContentPane().add(panel);
			panel.setLayout(new GridLayout(0, 3, 0, 0));
			{
				btnNewButton.addActionListener(new BtnNewButtonActionListener());

				panel.add(btnNewButton);
			}
			{
				JButton btnNewButton_1 = new JButton("Etat de Compte");
				btnNewButton_1.addActionListener(new BtnNewButton_1ActionListener());
				panel.add(btnNewButton_1);
			}
			{
				btnNewButton_2.addActionListener(new BtnNewButton_2ActionListener());
				panel.add(btnNewButton_2);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Affichage", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			getContentPane().add(panel);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane);
				{

					scrollPane.setViewportView(textArea);
				}
			}
		}
	}

	private class RdbtnDepotActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {lblSolde.setText("Limite");

			textField_1.setText(c1.getSolde()+"");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	private class RdbtnRetraitActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lblSolde.setText("Montant");


		}
	}
	private class RdbtnMiseAJourActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lblSolde.setText("Montant");

		}
	}
	private class BtnNewButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				double d1 = Double.parseDouble(textField.getText());
				if(rdbtnDepot.isSelected()) {

					c1.depot(d1);
					textField_1.setText(c1.getSolde()+"");
					System.out.println(c1.getSolde());
				}else if(rdbtnMiseAJour.isSelected()) {

					c1.setLimiteretrait(d1);

				}else if(rdbtnRetrait.isSelected()) {

					c1.retrait(d1);
					textField_1.setText(c1.getSolde()+"");
				} }catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					textArea.setText("Mettre des Chiffres");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					textArea.setText(e1.getMessage());
				}


		}
	}
	private class BtnNewButton_1ActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(c1 instanceof CompteCourant) {
				textArea.setText(((CompteCourant) c1).consultation());
			}

			if(c1 instanceof CompteEpargne) {
				textArea.setText(((CompteEpargne) c1).consultation());
			}

		}
	}
	private class BtnNewButton_2ActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	/**
	 * @return the c1
	 */
	public Compte getC1() {
		return c1;
	}
	/**
	 * @param c1 the c1 to set
	 */
	public void setC1(Compte c1) {
		this.c1 = c1;
	}

}
