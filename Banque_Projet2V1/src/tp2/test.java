/**
 * test.java
 */
package tp2;

import java.time.LocalDate;

/**
 * @author dquan
 * Classe : test
 * Date : 18 oct. 2021
 */
public class test {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub



		Banque b1 = new Banque("0",0);
		Compte c1 = new CompteEpargne(100, LocalDate.of(2015,11,02), 5);
		Compte c2 = new CompteEpargne(1, LocalDate.of(1, 1, 1), 5);






		b1.ChargerTxt("src/Banque.txt");

		for (Client string : b1.getListclient()) {
			System.out.println(string.ListerComptesEpargnes());
		}

		System.out.println(((CompteEpargne)b1.getListclient().get(0).getListCB().get(1)).toString());

		System.out.println(b1.getListclient().toString());


		b1.EnregistrerJSON("src/Banque.json");

	}

}
