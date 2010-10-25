package br.ufc.mdcc.cana;

import java.util.ArrayList;

/**
 * 
 * @author Fabiano Tavares
 *
 */
public class PrettyNeatly {

	private SlackTable slackTable;
	private int[] custo;
	private int[] inicioLinha;
	private ArrayList<Integer>  F; 

	static int INFINITO = Integer.MAX_VALUE;

	public PrettyNeatly(int[] W, int L)
	{
		int n = W.length;
		slackTable = new SlackTable(W, L);
		int[][] S = slackTable.getTabela();

		custo = new int[n];
		inicioLinha = new int[n];

		// Determinar o ultimo custo para arranjar a ultima linha
		custo[1] = 0;
		int k;
		int T;

		for (int i = 0; i < n; i++)
		{
			custo[i + 1] = INFINITO;
			k = i;
			T = custo[k] + S[k][i];
			if (T < custo[i + 1])
			{
				custo[i+1] = T;
				inicioLinha[i] = k;
			}
			while(k>=2 && T<INFINITO)
			{
				k--;
				T = custo[k] + S[k][i];
				if(T < custo[i+1])
				{
					custo[i+1] = T;
					inicioLinha[i] = k;
				}
			}
		}
		//Determinar a primeira palavara de cada linha
		F = new ArrayList<Integer>();
		F.add(inicioLinha[n]);
		int i = inicioLinha[n]-1;
		while(i>0)
		{
			F.add(inicioLinha[n]);
			i=inicioLinha[i]-i;
		}
	}
	
	public void printing()
	{
		for (Integer idxPrimeiraPalavra : F) {
			System.out.print(idxPrimeiraPalavra+", ");
		}
	}

}
