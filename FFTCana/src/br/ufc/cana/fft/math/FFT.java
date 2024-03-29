package br.ufc.cana.fft.math;

import java.util.ArrayList;

/**
 * Multiplicação de polinômios usando O(nlogn)
 * 
 * @author Paulo
 * @author Fabiano
 */
public class FFT {

	/**
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public ArrayList<Complex> multiplicacao(ArrayList<Complex> A,
			ArrayList<Complex> B) {

		ArrayList<Complex> C = new ArrayList<Complex>();

		for (int i = 0; i < A.size(); i++) {
			C.add(B.get(i).times(A.get(i)));
		}

		return C;
	}

	/**
	 * 
	 * @param V
	 * @return
	 */
	public static ArrayList<Complex> impar(ArrayList<Complex> V) {

		ArrayList<Complex> imparList = new ArrayList<Complex>();
		int tamImpar = 0;
		int n = V.size();

		tamImpar = ((n % 2) == 1) ? (n - 1) / 2 : n / 2;

		for (int i = 0; i < tamImpar; i++) {
			imparList.add(V.get(2 * i + 1));
		}
		return imparList;
	}

	/**
	 * 
	 * @param V
	 * @return
	 */
	public static ArrayList<Complex> par(ArrayList<Complex> V) {

		ArrayList<Complex> parList = new ArrayList<Complex>();
		int tamPar = 0;
		int n = V.size();

		tamPar = ((n % 2) == 1) ? (n + 1) / 2 : n / 2;

		for (int i = 0; i < tamPar; i++) {
			parList.add(V.get(2 * i));
		}
		return parList;
	}

	/**
	 * 
	 * @param V
	 * @return
	 */
	public int[] FFTinverso(ArrayList<Complex> V) {
		ArrayList<Complex> C = FFTrecursivo(V, true);
		int n = C.size();
		int[] res = new int[n];

		ArrayList<Complex> inversa = new ArrayList<Complex>();

		for (int i = 0; i < n; i++) {
			inversa.add(i, C.get(i).div(new Complex(n, 0)));
		}

		for (int i = 0; i < inversa.size(); i++) {
			res[i] = (int) Math.round(inversa.get(i).real());
		}

		return res;
	}

	/**
	 * 
	 * @param A
	 * @param inverso
	 * @return
	 */
	public ArrayList<Complex> FFTrecursivo(ArrayList<Complex> A, boolean inverso) {

		int m = A.size();
		double fator = (inverso) ? -1 : 1;

		Complex wn = new Complex(Math.cos(2 * Math.PI / m), fator
				* Math.sin(2 * (Math.PI / m)));
		Complex w = new Complex(1.0, 0);

		// Caso base: o vetor tem apenas um elemento
		ArrayList<Complex> V = new ArrayList<Complex>();
		if (m == 1) {
			V.add(0, A.get(0));
			return V;
		}

		// Divisão: separar o vetor em coeficientes pares e impares
		// A = Ap + x(Ai)
		ArrayList<Complex> Ap = FFT.par(A);
		ArrayList<Complex> Ai = FFT.impar(A);

		// Conquista: avaliar os polinomios de limite de grau n/2 para Ai e Ap
		ArrayList<Complex> Vp = FFTrecursivo(Ap, inverso);
		ArrayList<Complex> Vi = FFTrecursivo(Ai, inverso);

		for (int i = 0; i < m; i++) {
			V.add(i, new Complex(0, 0));
		}

		// Combinar os resultados 
		// V[i]       = Vp[] + (w^i)Vi[]
		// V[i + m/2] = Vp[] - (w^i)Vi[]
		for (int i = 0; i < m / 2; i++) {
			V.set(i, Vp.get(i).plus(Vi.get(i).times(w)));
			V.set(i + (m / 2), Vp.get(i).minus(Vi.get(i).times(w)));
			// manutenção do w contínuo 
			w = wn.times(w);
		}
		return V;
	}
	
}
