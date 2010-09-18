package br.ufc.cana.fft;

public class DoMultiplicacaoN2 {

	public static int[] multiplicaN2(int[] A, int[] B) {
		int[] V = new int[A.length + B.length - 1];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B.length; j++) {
				V[i + j] = V[i + j] + A[i] * B[j];
			}
		}
		return V;
	}

	public static void main(String[] args) {
		int[] eqcA = { 5, -2 }; // 1 + 2x
		int[] eqcB = { 5, 2 }; // 3 + 5x

		int[] eqcC = multiplicaN2(eqcA, eqcB);

		// Imprime resultado
		System.out.println("\nImprime resultado");
		String sinal;
		for (int i = 0; i < eqcC.length; i++) {
			sinal = (eqcC[i] >= 0) ? "+" : "";
			System.out.print(sinal + eqcC[i] + "x^" + i + " ");
		}
	}
}
