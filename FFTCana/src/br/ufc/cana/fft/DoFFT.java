package br.ufc.cana.fft;

import java.util.ArrayList;

import br.ufc.cana.fft.math.Complex;
import br.ufc.cana.fft.math.FFT;

public class DoFFT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//Entradas
		int[] eqcA = { 1, 2 }; // 1 + 2x
		int[] eqcB = { 3, 5 }; // 3 + 5x
		int[] eqcC;

		//Inicializado variáveis
		ArrayList<Complex> A = new ArrayList<Complex>();
		ArrayList<Complex> B = new ArrayList<Complex>();
		ArrayList<Complex> cmplxA = new ArrayList<Complex>();
		ArrayList<Complex> cmplxB = new ArrayList<Complex>();
		ArrayList<Complex> cmplxC;

		//Duplicando o limite de grau
		//Gerando numeros complexos
		A = Complex.doubleToComplex(eqcA, eqcA.length * 2 - 1);
		B = Complex.doubleToComplex(eqcB, eqcB.length * 2 - 1);
		
		//Fast Fourier Transform
		FFT fft = new FFT();
		
		//Avaliar
		cmplxA = fft.FFTrecursivo(A, false);
		cmplxB = fft.FFTrecursivo(B, false);
		
		//Multiplicar
		cmplxC = fft.multiplicacao(cmplxA, cmplxB);

		//Interpolar
		eqcC = fft.FFTinverso(cmplxC);

		//Imprime resultado
		for (int i = 0; i < eqcC.length; i++) {
			System.out.print(eqcC[i] + ",");
		}

	}

}
