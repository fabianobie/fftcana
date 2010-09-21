package br.ufc.cana.fft;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimeZone;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufc.cana.fft.math.Complex;
import br.ufc.cana.fft.math.FFT;
import br.ufc.cana.fft.math.TimeFormatter;

/**
 * Executa FFT e FB
 * 
 * @author Paulo
 * @author Fabiano
 */
public class DoFFT {
	
	private static Logger logador = Logger.getLogger(DoFFT.class.getName());

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//initLog();
		
		 DateFormat df = new SimpleDateFormat("mm 'min,' ss 'sec,' ms 'ms'");
	     df.setTimeZone(TimeZone.getTimeZone("GMT-3"));
	     long tInit, tEnd , tAll;
	     int i = 2;
	     
	    	
	    	int[] A = geradorNumerosAleatorios(10,(int) Math.pow(2,i)); //{ 1, 2 }; // 1 + 2x
	 		int[] B = geradorNumerosAleatorios(10,(int) Math.pow(2,i));//{ 3, 5 }; // 3 + 5x	 		
	 		int[] C; 
	 		System.out.print("A=");print(A);
	 		System.out.print("B=");print(B);
	 		
	 		System.out.print("\nI = " + i +  "\n");
	 		
	 		tInit = System.currentTimeMillis();
	 			
	 			C = DoMultiplicacaoN2.multiplicaN2(A, B);
	 			
	 		tEnd = System.currentTimeMillis();
	 		tAll=tEnd - tInit;
	 		print(C);
	 		System.out.println("N2:"+tAll+" : "+ df.format(new Date(tAll))+"\n");
	 		
	 		
	 		tInit = System.currentTimeMillis();
	 		
	 			C = executeFFT(A, B);
	 			
	 		tEnd = System.currentTimeMillis();
	 		tAll=tEnd - tInit;
	 		print(C);
	 		System.out.println("NlogN:"+tAll+": "+ df.format(new Date(tAll))+"\n");
	}
	
	private static void print(int[] A){
		String sinal;
		for (int i = 0; i < A.length; i++) {
			if(A[i]!=0){
				sinal = (A[i] > 0) ? "+" : "";
				System.out.print(sinal + A[i] + "x^" + i + " ");
			}
		}
		System.out.println("");
	}
	
	
    private static void initLog() {
    	FileHandler fh;
		try {
			fh = new FileHandler("e:\\fft.log", true);
			logador.addHandler(fh);
			logador.setLevel(Level.INFO);
			TimeFormatter formatter = new TimeFormatter();
			fh.setFormatter(formatter);
		} catch (Exception e) {
			e.printStackTrace();
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
		
		//System.out.println("\nArrays de Entrada");
		//Complex.printComplex(A);
		//Complex.printComplex(B);
		
		//Fast Fourier Transform
		FFT fft = new FFT();
		
		//Avaliar
		//System.out.println("\nAvaliar");
		cmplxA = fft.FFTrecursivo(A, false);
		cmplxB = fft.FFTrecursivo(B, false);
		
		//Complex.printComplex(cmplxA);
		//Complex.printComplex(cmplxB);
		
		
		//Multiplicar
		//System.out.println("\nMultiplicar");
		cmplxC = fft.multiplicacao(cmplxA, cmplxB);
		
		//Complex.printComplex(cmplxC);
		
		//Interpolar
		//System.out.println("\nInterpolar");
		resultado = fft.FFTinverso(cmplxC);
		
		return resultado;
    }
    
	
	
	

}
