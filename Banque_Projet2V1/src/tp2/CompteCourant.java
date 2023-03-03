/**
 * CompteCourant.java
 */
package tp2;

import java.time.LocalDate;

/**
 * @author dquan
 * Classe : CompteCourant
 * Date : 18 oct. 2021
 */
public class CompteCourant extends Compte {

	/**
	 * @param solde
	 * @param date
	 * @param limiteretrait
	 * @param limitedepot
	 * @param decouvertmax
	 */
	public CompteCourant(double solde, LocalDate date, double limiteretrait, double limitedepot, double decouvertmax) {
		super(solde, date, limiteretrait, limitedepot, decouvertmax);
		// TODO Auto-generated constructor stub
	}
	public CompteCourant(String num, double solde, LocalDate date, double limiteretrait, double limitedepot, double decouvertmax) {
		super(num,solde,date,limiteretrait,limitedepot,decouvertmax);
	}


	/**
	 * @param solde
	 * @param date
	 */
	public CompteCourant(double solde, LocalDate date) {
		super(solde, date);
		// TODO Auto-generated constructor stub
	}


	public CompteCourant() {
		super();
	}








	//Donne l'historique de versement
	/**
	 * @return String
	 */
	public String consultation() {
		String msg = "";
		msg+="Compte: "+getNumcompte() +" Ouvert le "+getDate()+" Solde:"+getSolde()+" $\n";
		for (Versement s1 : historique1) {
			msg+=s1.toString() + "\n";
		}
		return msg;
	}










}
