package br.ufc.cana.fft;

import java.util.ArrayList;
import java.util.Random;

import br.ufc.cana.fft.math.Complex;
import br.ufc.cana.fft.math.FFT;

public class DoFFT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Entradas
		int[] A = geradorNumerosAleatorios(100,1000); //{ 1, 2 }; // 1 + 2x
		int[] B = geradorNumerosAleatorios(100,1000);//{ 3, 5 }; // 3 + 5x
		
		int[] C  = executeFFT(A, B);
		
		geradorNumerosAleatorios(10, 100);

		//Imprime resultado
		System.out.println("\nImprime resultado");
		for (int i = 0; i < C.length; i++) {
			System.out.print(C[i] + ",");
		}

	}
	
	
    public static int[] geradorNumerosAleatorios(int range, int tamanho) {
		if (tamanho <= 0) {
			return null;
		} else {
			int numeroSorteado;
			int sinal;
			Random random = new Random();
			int[] numeros = new int[tamanho];

			for (int i = 0; i < tamanho; i++) {
				numeroSorteado = random.nextInt(range + 1);
				sinal = (random.nextInt(range + 1) > numeroSorteado)?-1:1;
				numeros[i] = sinal*numeroSorteado;
			}

			return numeros;
		}
	}

    
    public static int[] executeFFT(int[] eqcA , int eqcB[]){
		//Inicializado variáveis
		ArrayList<Complex> A = new ArrayList<Complex>();
		ArrayList<Complex> B = new ArrayList<Complex>();
		ArrayList<Complex> cmplxA = new ArrayList<Complex>();
		ArrayList<Complex> cmplxB = new ArrayList<Complex>();
		ArrayList<Complex> cmplxC;
		int[] resultado;

		//Duplicando o limite de grau
		//Gerando numeros complexos
		A = Complex.doubleToComplex(eqcA, eqcA.length * 2);
		B = Complex.doubleToComplex(eqcB, eqcB.length * 2);
		
		System.out.println("\nArrays de Entrada");
		//Complex.printComplex(A);
		//Complex.printComplex(B);
		
		//Fast Fourier Transform
		FFT fft = new FFT();
		
		//Avaliar
		System.out.println("\nAvaliar");
		cmplxA = fft.FFTrecursivo(A, false);
		cmplxB = fft.FFTrecursivo(B, false);
		
		//Complex.printComplex(cmplxA);
		//Complex.printComplex(cmplxB);
		
		
		//Multiplicar
		System.out.println("\nMultiplicar");
		cmplxC = fft.multiplicacao(cmplxA, cmplxB);
		
		//Complex.printComplex(cmplxC);
		
		//Interpolar
		System.out.println("\nInterpolar");
		resultado = fft.FFTinverso(cmplxC);
		
		return resultado;
    }
    
	
	
	

}
