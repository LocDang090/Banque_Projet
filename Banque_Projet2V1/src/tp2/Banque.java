/**
 * Banque.java
 */
package tp2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author dquan
 * Classe : Banque
 * Date : 18 oct. 2021
 */
public class Banque {
	public String nom;
	public static int numero;
	public int num;
	public ArrayList<Client> listclient = new ArrayList<>();

	public Banque(String nom,int num) {
		this.nom=nom;
		setNumero(num);
		setNum(num);
	}

	public Banque() {
		this("XXXXXX",9999);
	}
	/** Ajoute un client a la liste de client
	 * @param c1
	 * @throws Exception
	 */
	public void ajouter(Client c1) throws Exception {
		if(!listclient.contains(c1)) {
			listclient.add(c1);
		}else {
			throw new Exception("Deja dans la liste");
		}
	}
	//Obtiens un client par sa position
	public Client obtenirparpos(int pos) {
		return listclient.get(pos);
	}
	//Liste les clients de la banque
	/**
	 * @return String
	 */
	public String listerClient() {
		String msg = "";
		for (Client c1 : listclient) {
			msg+=c1.toString()+"\n";
		}
		return msg;
	}
	//Donne le nombre de Client
	public int nombreClient() {
		return listclient.size();
	}

	/**Enregistre Avec le Path donner
	 * @param path
	 */
	public void EnregistrerJSON(String path) {
		try  {
			Writer writer = new FileWriter(path);
			Gson gson = new GsonBuilder().create();
			gson.toJson(this, writer);
			writer.flush();
			writer.close();



		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}

	}




	/**Charge la liste de client sans le versement
	 * @param path
	 * @throws Exception
	 */
	public void ChargerTxt(String path) throws Exception {
		String ligne = null;
		String nom, prenom = null;

		try (FileReader fr = new FileReader(path);
				BufferedReader br = new BufferedReader(fr);)
		{
			ligne = br.readLine();
			while (ligne != null) {


				StringTokenizer str = new StringTokenizer(ligne, "[.:]");
				prenom = str.nextToken();
				nom = str.nextToken();
				String comptes = str.nextToken();

				Client client = new Client(nom, prenom);



				StringTokenizer str2 = new StringTokenizer(comptes, "[,:]");
				while (str2.hasMoreElements()) {
					String type = str2.nextToken();
					String numero = str2.nextToken();
					String month = str2.nextToken();
					String year  = str2.nextToken();
					String day = str2.nextToken();
					String solde =str2.nextToken();
					String limiteRetrait =str2.nextToken();
					String limitedepot =str2.nextToken();
					String limiteDecouvert =str2.nextToken();
					Compte c1;
					if(type.equals("C")) {
						c1 = new CompteCourant(numero,Double.parseDouble(solde), LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)),Double.parseDouble(limiteRetrait),Double.parseDouble(limitedepot),Double.parseDouble(limiteDecouvert));
						client.ouvrir(c1);

					} else if(type.equals("E")) {
						double taux = Double.parseDouble(str2.nextToken());
						c1 = new CompteEpargne(numero,Double.parseDouble(solde),  LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)),Double.parseDouble(limiteRetrait),Double.parseDouble(limitedepot),Double.parseDouble(limiteDecouvert),taux);
						client.ouvrir(c1);
					}


				}


				ajouter(client);


				ligne = br.readLine();
			}
		} catch (NumberFormatException | IOException | NoSuchElementException e) {
			//			System.out.println("Erreur : " + e.getMessage());
			e.printStackTrace();
		}








	}

	public Compte ObtenirCompteParNum(String num) throws Exception {
		for (Client client : listclient) {
			for (Compte compte : client.listCB) {
				if(compte.getNumcompte().compareTo(num)==0) {
					return compte;
				}
			}
		}
		throw new Exception("Compte Introuvable");
	}



	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the numero
	 */
	public static int getNumero() {
		return Banque.numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public static void setNumero(int numero) {
		Banque.numero = numero;
	}

	/**
	 * @return the listclient
	 */
	public ArrayList<Client> getListclient() {
		return listclient;
	}

	/**
	 * @param listclient the listclient to set
	 */
	public void setListclient(ArrayList<Client> listclient) {
		this.listclient = listclient;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((nom == null) ? 0 : nom.hashCode());
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
		Banque other = (Banque) obj;
		if (nom == null) {
			if (other.nom != null) {
				return false;
			}
		} else if (!nom.equals(other.nom)) {
			return false;
		}
		return true;
	}






}
