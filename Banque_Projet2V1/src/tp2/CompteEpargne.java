/**
 * CompteEpargne.java
 */
package tp2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


/**
 * @author dquan
 * Classe : CompteEpargne
 * Date : 18 oct. 2021
 */
public class CompteEpargne extends Compte {
	protected double taux;

	/**
	 * @param solde
	 * @param date
	 * @param limiteretrait
	 * @param limitedepot
	 * @param decouvertmax
	 */
	public CompteEpargne(double solde, LocalDate date, double limitedepot, double decouvertmax,double taux) {
		super(solde, date,0, limitedepot, decouvertmax);
		// TODO Auto-generated constructor stub
		this.taux = taux;
		setLimiteretrait(0);
	}

	/**
	 * @param solde
	 * @param date
	 */
	public CompteEpargne(double solde, LocalDate date,double taux) {
		super(solde, date);
		// TODO Auto-generated constructor stub
		setLimiteretrait(0);
		this.taux= taux;

	}

	public CompteEpargne(String num, double solde, LocalDate date, double limiteretrait, double limitedepot, double decouvertmax,double taux) {
		super(num,solde,date,limiteretrait,limitedepot,decouvertmax);
		this.taux = taux;
	}

	public CompteEpargne() {
		super();
		setTaux(0.05);
	}

	/**
	 * @return the taux
	 */
	public double getTaux() {
		return taux;
	}

	/**
	 * @param taux the taux to set
	 */
	public void setTaux(double taux) {
		this.taux = taux;
	}

	/** Ne peut pas faire de retrait Throw une erreur
	 * @throws Exception @param double,Compte
	 *
	 */

	@Override
	public void virer(double d1, Compte c1) throws Exception {
		throw new Exception("Compte Epargne Aucun Virement Possible");
	}
	/** Ne peut pas faire de retrait Throw une erreur
	 * @throws Exception @param double
	 *
	 */
	@Override
	public void retrait(double d1) throws Exception {
		throw new Exception("Aucun Retrait Possible");
	}


	//Consultation et Calcule de L'interet
	/**
	 * @return String
	 */
	public String consultation() {

		String msg="";

		msg+=toString()+"\n";
		for (Versement s1 : historique1) {
			msg+=s1.toString() +"\n";
		}
		msg+="Interet Total: "+interet();
		return msg;
	}

	/**Calcule l interet du compte courant
	 * @return double
	 */
	public double interet() {
		double totalinteret=0;
		String msg="";
		double daybetween = ChronoUnit.DAYS.between(getDate(),LocalDate.now());
		double interet = 0;
		Versement past = historique1.get(0);
		for(Versement v1:historique1) {
			daybetween =  ChronoUnit.DAYS.between(past.getDate(),v1.getDate());
			interet = (past.getSolde()*daybetween*taux)/100/365;
			totalinteret+=interet;
			past=v1;

		}
		daybetween =  ChronoUnit.DAYS.between(past.getDate(),LocalDate.now());
		interet = (past.getSolde()*daybetween*taux)/100/365;
		totalinteret+=interet;
		return totalinteret;
	}

	@Override
	public String toString() {
		String msg = "";





		msg+="Compte Epargne: "+getNumcompte() +" Ouvert le "+getDate()+" Solde:"+getSolde()+"$ Taux: "+getTaux()+"% Interet:" + interet();
		return msg;
	}



}
