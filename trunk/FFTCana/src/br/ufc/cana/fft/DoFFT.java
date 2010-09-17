package br.ufc.cana.fft;

import br.ufc.cana.fft.math.Complex;

public class DoFFT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] A = {3,2,-1,4,0,0,0};
		int[] B = {-2,-1,2,7,0,0,0};
		
		
//		1. Pick m points x_0, x_1, ..., x_{m-1} according to a secret formula.
		//w =  + i*sin(2*pi/m)

		int m=8;
		Complex w =  new Complex(Math.cos(2*Math.PI/m), Math.sin(2*Math.PI/m));
		
		
//		2. Evaluate A at each of the points: A(x_0),..., A(x_{m-1}).
		
		
//		3. Same for B.
		
		
//		4. Now compute C(x_0),..., C(x_{m-1}), where C is A(x)*B(x)
		
		
//		5. Interpolate to get the coefficients of C.
 
		
		
		
	}

}
