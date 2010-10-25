package br.ufc.mdcc.cana;

import java.util.Scanner;

/**
 * 
 * @author Fabiano Tavares
 *
 */
public class QuebraLinha {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String texto = "Eu sou fabiano Tavares da Silva";
		
		Scanner sc = new Scanner(texto);
		
		int i=0;
		while(sc.hasNext())
		{
			System.out.println(sc.next().length());
			i++;
		}
		
		
		int[] W = new int[i];
		int j=1;
		while(sc.hasNext()) {
			W[j] = sc.next().length();
		}

		PrettyNeatly pn = new PrettyNeatly(W, 10);
		pn.printing();

	}

}

