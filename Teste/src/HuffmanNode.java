
public class HuffmanNode
{
	protected char simbolo;
	protected int frequencia;
	protected HuffmanNode esq, dir;
	

	public HuffmanNode(char simbolo, int frequencia)
	{
		this.simbolo = simbolo;
		this.frequencia = frequencia;

	}

	public void showTree()
	{
		System.out.print(" ( "+ frequencia);
		if(esq == null && dir == null)
			System.out.print(" "+ simbolo);

		if(esq != null){
			this.esq.showTree();
		}
		if(dir != null){
			this.dir.showTree();
		}
		System.out.print(" ) ");

	}
	


	public void printCode(String str)
	{
		if(this.esq == null && this.dir == null){
			System.out.println("Simbolo: "+ simbolo + " Codificacao: " + str);
			return;
		}
		if(esq != null)
			esq.printCode(str + "0");
		if(dir != null)
			dir.printCode(str + "1");
	}
}