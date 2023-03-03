/**
 * Versement.java
 */
package tp2;

import java.time.LocalDate;

/**
 * @author dquan
 * Classe : Versement
 * Date : 18 oct. 2021
 */
public class Versement {
	private LocalDate date;
	private double montant;
	private String typetransaction;
	private double solde;
	/**
	 *
	 */
	public Versement(double montant,LocalDate date,String typetransaction,double solde) {
		// TODO Auto-generated constructor stub
		this.date =date;
		this.montant=montant;
		this.solde = solde;
		this.typetransaction=typetransaction;
	}
	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	/**
	 * @return the montant
	 */
	public double getMontant() {
		return montant;
	}
	/**
	 * @param montant the montant to set
	 */
	public void setMontant(double montant) {
		this.montant = montant;
	}
	/**
	 * @return the typetransaction
	 */
	public String getTypetransaction() {
		return typetransaction;
	}
	/**
	 * @param typetransaction the typetransaction to set
	 */
	public void setTypetransaction(String typetransaction) {
		this.typetransaction = typetransaction;
	}


	/**
	 * @return the solde
	 */
	public double getSolde() {
		return solde;
	}
	/**
	 * @param solde the solde to set
	 */
	public void setSolde(double solde) {
		this.solde = solde;
	}
	@Override
	public String toString() {
		return getDate()+" "+getTypetransaction()+" "+getMontant()+"$ Solde:"+getSolde();
	}




}
