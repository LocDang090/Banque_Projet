/**
 * CompteBancaire.java
 */
package tp2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author dquan
 * Classe : CompteBancaire
 * Date : 18 oct. 2021
 */
/**
 * @author dquan
 * Classe : Compte
 * Date : 2 nov. 2021
 */
public abstract class Compte implements gestionCompte{
	protected static int total = 0;
	protected int num ;
	protected double  solde;
	protected LocalDate date;
	protected double limiteretrait;
	protected double limitedepot;
	protected double decouvertmax;
	protected String numcompte;
	protected double decouvert;
	protected ArrayList<Versement> historique1 = new ArrayList<>();

	public Compte(String num,double solde, LocalDate date, double limiteretrait,double limitedepot,double decouvertmax) {
		Compte.total++;

		if(solde >= 0) {
			this.solde = solde;
		}else {
			this.solde = 0;
		}
		this.date =date;
		if(limiteretrait>=0) {
			this.limiteretrait=limiteretrait;}else {
				setLimiteretrait(1000);
			}

		if(limitedepot>=0) {
			this.limitedepot=limitedepot;}else {
				setLimitedepot(10000);
			}
		if(decouvertmax <= 0 ) {
			this.decouvertmax=decouvertmax;
		}else {
			setDecouvert(-500);
		}
		setNumcompte(num);
		historique1.add(new Versement(this.solde,date,"Depot",this.solde));
	}

	public Compte(double solde, LocalDate date, double limiteretrait,double limitedepot,double decouvertmax) {
		Compte.total++;
		num = Compte.total;
		if(solde >= 0) {
			this.solde = solde;}else {
				this.solde = 0;
			}
		this.date =date;
		if(limiteretrait>=0) {
			this.limiteretrait=limiteretrait;}else {
				setLimiteretrait(1000);
			}

		if(limitedepot>=0) {
			this.limitedepot=limitedepot;}else {
				setLimitedepot(10000);
			}
		if(decouvertmax <= 0 ) {
			this.decouvertmax=decouvertmax;
		}else {
			setDecouvert(-500);
		}
		setNumcompte(generernum());
		historique1.add(new Versement(this.solde,date,"Depot",this.solde));
	}

	public Compte(double solde,LocalDate date) {

		this(solde, date, 1000, 10000, -500);



	}

	public Compte() {
		this(0,LocalDate.now(),1000,10000,-500);
	}


	//Genere le numeros du client
	/**
	 * @return String
	 */
	public String generernum() {
		Random rand = new Random();
		int rand1 = rand.nextInt(1000);
		String seq = String.format("%0" + 3 + "d", rand1)+"-"+String.format("%0" + 4 + "d", getNum())+"-"+String.format("%0" + 2 + "d", Banque.numero);
		return seq;
	}

	/** Depose le montant donner dans le compte courant
	 *	@param double
	 */
	@Override
	public void depot(double d1) throws Exception {
		if(d1 >= 0 ) {
			if(d1 <=getLimitedepot()) {
				if((getSolde()+d1) > 0) {
					setDecouvert(0);
					setSolde(getSolde()+d1);

					historique1.add(new Versement(d1,LocalDate.now(),"Depot",getSolde()));}else {
						setDecouvert(Math.abs(getSolde()+d1));
						setSolde(getSolde()+d1);
						historique1.add(new Versement(d1,LocalDate.now(),"Depot",getSolde()));
					}
			}else {
				throw new Exception("Limite de Depot depasser");
			}}else {
				throw new Exception("Montant Negatif");
			}


	}
	/** Retire le montant donner dans le compte courant
	 *	@param double
	 */
	@Override
	public void retrait(double d1) throws Exception {
		if(d1 >= 0 ) {
			if(d1 <= getLimiteretrait()) {
				if((getSolde()-d1) >= 0 ){
					setDecouvert(0);
					setSolde(getSolde()-d1);

					historique1.add(new Versement(d1,LocalDate.now(),"Retrait",getSolde()));
				}else
					if(((getSolde()-d1) < 0) && ((getSolde()-d1) > decouvertmax)) {
						setDecouvert(Math.abs(getSolde()-d1));
						setSolde(getSolde()-d1);

						historique1.add(new Versement(d1,LocalDate.now(),"Retrait",getSolde()));
					}else {
						throw new Exception("Limite de Decouvert depasser");
					}


			}else {
				throw new Exception("Limite de retrait depasser");
			}}else {
				throw new Exception("Montant Negatif");
			}


	}
	/** Vire le montant donner dans le compte donner
	 *	@param double
	 */
	@Override
	public void virer(double d1,Compte c1)  throws Exception{
		if(d1 >= 0) {
			if(d1 <= getLimiteretrait()) {
				if((getSolde()-d1) >= 0 ){
					setDecouvert(0);
					setSolde(getSolde()-d1);
					historique1.add(new Versement(d1,LocalDate.now(),"Virer",getSolde()));
					c1.depot(d1);
					c1.historique1.add(new Versement(d1,LocalDate.now(),"Depot par Virement",c1.getSolde()));
				}else
					if(((getSolde()-d1) < 0) && ((getSolde()-d1) >= decouvertmax)) {
						setDecouvert(Math.abs(getSolde()-d1));
						setSolde(getSolde()-d1);
						historique1.add(new Versement(d1,LocalDate.now(),"Virer",getSolde()));
						c1.depot(d1);
						c1.historique1.add(new Versement(d1,LocalDate.now(),"Depot par Virement",c1.getSolde()));
					}else {
						throw new Exception("Limite de Decouvert depasser");
					}


			}else {
				throw new Exception("Limite de retrait depasser");
			}}else {
				throw new Exception("Montant Negatif");
			}

	}

	/**
	 * @return the total
	 */
	public static int getTotal() {
		return Compte.total;
	}

	/**
	 * @param total the total to set
	 */
	public static void setTotal(int total) {
		Compte.total = total;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
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
	 * @return the limiteretrait
	 */
	public double getLimiteretrait() {
		return limiteretrait;
	}

	/**
	 * @param limiteretrait the limiteretrait to set
	 */
	public void setLimiteretrait(double limiteretrait) {
		this.limiteretrait = limiteretrait;
	}

	/**
	 * @return the limitedepot
	 */
	public double getLimitedepot() {
		return limitedepot;
	}

	/**
	 * @param limitedepot the limitedepot to set
	 */
	public void setLimitedepot(double limitedepot) {
		this.limitedepot = limitedepot;
	}

	/**
	 * @return the decouvertmax
	 */
	public double getDecouvertmax() {
		return decouvertmax;
	}

	/**
	 * @param decouvertmax the decouvertmax to set
	 */
	public void setDecouvertmax(double decouvertmax) {
		this.decouvertmax = decouvertmax;
	}

	/**
	 * @return the numcompte
	 */
	public String getNumcompte() {
		return numcompte;
	}

	/**
	 * @param numcompte the numcompte to set
	 */
	public void setNumcompte(String numcompte) {
		this.numcompte = numcompte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((numcompte == null) ? 0 : numcompte.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Compte other = (Compte) obj;
		if (numcompte == null) {
			if (other.numcompte != null) {
				return false;
			}
		} else if (!numcompte.equals(other.numcompte)) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return "Compte: "+getNumcompte()+" Solde:"+getSolde()+" Ouvert le :" +getDate();
	}

	/**
	 * @return the historique1
	 */
	public ArrayList<Versement> getHistorique1() {
		return historique1;
	}

	/**
	 * @param historique1 the historique1 to set
	 */
	public void setHistorique1(ArrayList<Versement> historique1) {
		this.historique1 = historique1;
	}

	/**
	 * @return the decouvert
	 */
	public double getDecouvert() {
		return decouvert;
	}

	/**
	 * @param decouvert the decouvert to set
	 */
	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}




}
