package br.ufc.cana.fft.math;

import java.util.ArrayList;

public class FFT {

	public ArrayList<Complex> multiplicacao(ArrayList<Complex> A, ArrayList<Complex> B) {
		
		ArrayList<Complex> C = new ArrayList<Complex>();
		
		for (int i = 0; i < A.size(); i++) {
			C.add(B.get(i).times(A.get(i)));
		}
		
		return C;
	}
	
	private ArrayList<Complex> impar(ArrayList<Complex> V){
		
		ArrayList<Complex> imparList = new ArrayList<Complex>();
		int tamImpar = 0 ;
		int n =  V.size();
		
		tamImpar = ((n % 2) == 1)?(n-1)/2 : n/2;
		
		for (int i = 0; i < tamImpar; i++) {
			imparList.add(V.get(2*i+1));
		}
		return imparList;
	}
	
	private ArrayList<Complex> par(ArrayList<Complex> V){
		
		ArrayList<Complex> parList = new ArrayList<Complex>();
		int tamPar = 0 ;
		int n =  V.size();
		
		tamPar = ((n % 2) == 1)?(n+1)/2 : n/2;
		
		for (int i = 0; i < tamPar; i++) {
			parList.add(V.get(2*i));
		}
		return parList;
	}
}
