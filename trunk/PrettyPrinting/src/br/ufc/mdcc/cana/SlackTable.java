package br.ufc.mdcc.cana;

/**
 * 
 * @author Fabiano Tavares
 *
 */
public class SlackTable {
	
	private int[][] tabela;
	private int[] fimLinha;
	
	static int INFINITO = Integer.MAX_VALUE;
	
	public SlackTable(int[] W,int L) {
		int n = W.length;
		tabela = new int[n][n];
		fimLinha = new int[n];
		
		//inicializando tabela e linha
		tabela[1][1] = W[1];
		fimLinha[1] = n;
		
		for (int i = 2; i < n; i++)
		{
			tabela[1][i]=tabela[1][i-1]+W[i];
		}
		
		for (int i = 2; i < n; i++)
		{
			int k=i;
			tabela[1][k]=tabela[1][k]-tabela[1][i-1];
			while(k<=n-1 && L-tabela[i][k]-(k-i)>0)
			{
				k++;
				tabela[1][k]=tabela[1][k]-tabela[1][i-1];
			}
			fimLinha[i]=k;
		}
		//Avaliar a soma do quadadrados das folgas
		for (int i = 1; i < n; i++)
		{
			for (int k = i; i < fimLinha[i]; k++)
			{
				tabela[i][k] = L-tabela[i][k]-(k-i);
				if(tabela[i][k]<0)
				{
					tabela[i][k] = INFINITO;
				}else
				{
					tabela[i][k] = (int) Math.pow(tabela[i][k], 2.0);
				}
			}
		}
	}
	
	public int[][] getTabela() {
		return tabela;
	}


	public int[] getFimLinha() {
		return fimLinha;
	}	
	
}
