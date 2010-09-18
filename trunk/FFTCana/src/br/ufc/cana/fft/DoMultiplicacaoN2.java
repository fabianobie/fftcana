package br.ufc.cana.fft;
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
		int[] eqcA = { 3, 1, 3, 1, 2, 3, 4,-1};
		int[] eqcB = { 1, 4, -2, 1, -2, -1, -3, 4};
		int[] eqcC = multiplicaN2(eqcA, eqcB);

		// Imprime resultado
		System.out.println("\nImprime resultado");
		String sinal;
		for (int i = 0; i < eqcC.length; i++) {
			sinal = (eqcC[i] >= 0) ? "+" : "";
			System.out.print(sinal + eqcC[i] + "x^" + i + " ");
		}
		
		eqcC = DoFFT.executeFFT(eqcA, eqcB);
		for (int i = 0; i < eqcC.length; i++) {
			sinal = (eqcC[i] >= 0) ? "+" : "";
			System.out.print(sinal + eqcC[i] + "x^" + i + " ");
		}
	}
}
