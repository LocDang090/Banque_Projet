/**
 * gestionClient.java
 */
package tp2;

/**
 * @author dquan
 * Classe : gestionClient
 * Date : 18 oct. 2021
 */
public interface gestionClient {
	void ouvrir(Compte c1) throws Exception;
	void fermer(String num) throws Exception;
	void depot(String num,double d1) throws Exception;
	void retrait(String num,double d1) throws Exception;
	Compte obtenir(String num) throws Exception;
}
