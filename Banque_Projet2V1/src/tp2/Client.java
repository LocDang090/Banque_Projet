/**
 * Client.java
 */
package tp2;

import java.util.ArrayList;

/**
 * @author dquan
 * Classe : Client
 * Date : 18 oct. 2021
 */
public class Client implements gestionClient{
	public String nomfamille;
	public String prenom;
	public ArrayList<Compte> listCB = new ArrayList<>();


	public Client(String nomfamille,String prenom) {
		this.nomfamille=nomfamille;
		this.prenom=prenom;
	}

	public Client() {
		this("XXXXX","XXXXX");
	}
	/** Ouvre un Compte en Banque pour le client courant
	 * @param Compte
	 */
	@Override
	public void ouvrir(Compte c1) throws Exception {
		if(!listCB.contains(c1)) {
			if(listCB.size()<10) {
				listCB.add(c1);
			}else {
				throw new Exception("Plus de 10 compte");
			}}else {
				throw new Exception("Compte Existe Deja");
			}
	}

	/** ferme un Compte par son numero de compte
	 *	@param String
	 */
	@Override
	public void fermer(String num) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Compte> c2 = new ArrayList<Compte>();
		for (Compte c1 : listCB) {
			if(c1.getNumcompte().compareTo(num) != 0) {
				c2.add(c1);
			}
		}
		setListCB(c2);

	}
	/**	Depose un montant d'argent a un compte avec son numero de compte
	 *	@param String, double
	 */
	@Override
	public void depot(String num,double d1) throws Exception {
		// TODO Auto-generated method stub
		for (Compte c1 : listCB) {
			if(c1.getNumcompte().compareTo(num) == 0) {
				c1.depot(d1);
			}
		}
	}
	/**	Enleve  un montant d'argent a un compte avec son numero de compte
	 *	@param String, double
	 */
	@Override
	public void retrait(String num,double d1) throws Exception {
		// TODO Auto-generated method stub
		for (Compte c1 : listCB) {
			if(c1.getNumcompte().compareTo(num) == 0) {
				c1.retrait(d1);
			}
		}
	}
	/**	Obtiens le compte par son numero de compte
	 *	@param String
	 */
	@Override
	public Compte obtenir(String num) throws Exception {
		// TODO Auto-generated method stub
		for (Compte c1 : listCB) {
			if(c1.getNumcompte().compareTo(num) == 0) {
				return c1;
			}
		}
		return null;
	}
	//Liste les comptes courant du client courant
	/**
	 * @return String
	 */
	public String ListerComptes() {
		String msg = "";
		for (Compte c1 : listCB) {
			if(c1 instanceof CompteCourant) {
				msg+= ((CompteCourant) c1).consultation()+"\n";
			}}
		return msg;
	}


	// Liste les comptes epargnes du client courant
	/**
	 * @return
	 */
	public String ListerComptesEpargnes() {
		String msg = "";
		for (Compte c1 : listCB) {
			if(c1 instanceof CompteEpargne) {
				msg+= ((CompteEpargne) c1).consultation()+"\n";
			}}
		return msg;
	}
	//Donne le solde total des comptes du client courant
	/**
	 * @return double
	 */
	public double totalsoldeComptes() {
		double total = 0;
		for (Compte c1 : listCB) {
			total+=c1.getSolde();
		}
		return total;
	}
	//Liste les nombres de tous les comptes du client courant
	public String totalNombreComptes() {
		String msg="";
		for (Compte c1 : listCB) {
			msg+=c1.getNumcompte()+"\n";
		}
		return msg;
	}

	@Override
	public String toString() {
		return "Client: "+getNomfamille()+", "+getPrenom();
	}
	/**
	 * @return the nomfamille
	 */
	public String getNomfamille() {
		return nomfamille;
	}
	/**
	 * @param nomfamille the nomfamille to set
	 */
	public void setNomfamille(String nomfamille) {
		this.nomfamille = nomfamille;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the listCB
	 */
	public ArrayList<Compte> getListCB() {
		return listCB;
	}
	/**
	 * @param listCB the listCB to set
	 */
	public void setListCB(ArrayList<Compte> listCB) {
		this.listCB = listCB;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((nomfamille == null) ? 0 : nomfamille.hashCode());
		result = (prime * result) + ((prenom == null) ? 0 : prenom.hashCode());
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
		Client other = (Client) obj;
		if (nomfamille == null) {
			if (other.nomfamille != null) {
				return false;
			}
		} else if (!nomfamille.equals(other.nomfamille)) {
			return false;
		}
		if (prenom == null) {
			if (other.prenom != null) {
				return false;
			}
		} else if (!prenom.equals(other.prenom)) {
			return false;
		}
		return true;
	}






}
