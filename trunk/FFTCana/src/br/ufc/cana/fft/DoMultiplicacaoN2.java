package br.ufc.cana.fft;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Multiplicação de polinômios O(n^2)
 * 
 * @author Paulo
 * @author Fabiano
 */
public class DoMultiplicacaoN2 {

	/**
	 * Multiplica o polinômio A pelo polinômio B
	 * C = A*B
	 * 
	 * @param A : vetor com os coeficientes do primeiro polinômio
	 * @param B : vetor com os coeficientes do segundo polinômio
	 * @return C : vetor com os coeficientes do polinômio C = A*B
	 */
	public static int[] multiplicaN2(int[] A, int[] B) {
		int[] C = new int[A.length + B.length - 1];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B.length; j++) {
				C[i + j] = C[i + j] + A[i] * B[j];
			}
		}
		return C;
	}

	public static void main(String[] args) {
		
		 int[] eqcA = DoFFT.geradorNumerosAleatorios(100,(int) Math.pow(2,20));
	        int[] eqcB = DoFFT.geradorNumerosAleatorios(100,(int) Math.pow(2,20));
	        int[] eqcC = null;
	        
	        DateFormat df = new SimpleDateFormat("mm 'min,' ss 'sec,' ms 'ms'");
	        df.setTimeZone(TimeZone.getTimeZone("GMT-3"));

	        long tempo = System.currentTimeMillis();
	        eqcC = DoFFT.executeFFT(eqcA, eqcB);
	        tempo = System.currentTimeMillis() - tempo;
	        System.out.println();

	        tempo = System.currentTimeMillis();
	        eqcC = multiplicaN2(eqcA, eqcB);
	        tempo = System.currentTimeMillis() - tempo;
	        System.out.println(df.format(new Date(tempo)));
	        
	}
}
