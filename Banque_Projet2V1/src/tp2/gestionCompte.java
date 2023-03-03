/**
 * gestionCompte.java
 */
package tp2;

/**
 * @author dquan
 * Classe : gestionCompte
 * Date : 18 oct. 2021
 */
public interface gestionCompte {
	void retrait(double d1) throws Exception;
	void depot(double d1) throws Exception;
	void virer(double d1,Compte c1) throws Exception;


}
