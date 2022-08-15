import java.util.Random;
import java.util.Scanner;

public class MinBinaryHeap
{
    private int n;
    private int tamMaximo;
    private HuffmanNode[] vetor;
    private HuffmanNode raiz = null;

    public MinBinaryHeap(int tamanho)
    {
        n = 0;
        tamMaximo = tamanho;
        vetor = new HuffmanNode[tamanho+1];

    }

    public MinBinaryHeap(int tamanho, HuffmanNode[] v)
    {
        tamMaximo = tamanho;
        vetor = new HuffmanNode[tamanho+1];
        n = tamanho;

        for( int i = 0; i < tamanho; i++ )
            vetor[ i + 1 ] = v[ i ];

        buildHeap();
    }

    public boolean empty()
    {
        return n == 0;
    }


    public void turnEmpty()
    {
        n = 0;
    }


    public void printHeap()
    {
        System.out.println("Heap Mínima Binária: ");

        for(int i = 1; i <= n; i++)
            System.out.println("v["+ i +"] " + vetor[i].simbolo + " " + vetor[i].frequencia);

        System.out.println();
    }


    public HuffmanNode min()
    {
        if (this.empty())
        {
            System.out.println("Fila de prioridades vazia!");
            return null;
        }

        return vetor[1];
    }


    public HuffmanNode removeMin()
    {
        HuffmanNode itemMin;

        if(this.empty())
        {
            System.out.println("Fila de prioridades vazia!");
            return null;
        }

        itemMin = vetor[1];
        vetor[1] = vetor[n];
        n--;
        heapify(1);

        return itemMin;
    }


    private void buildHeap()
    {
        for( int i = n / 2; i > 0; i-- )
            heapify(i);
    }


    private void heapify(int i)
    {
        HuffmanNode x = vetor[ i ];

        while(2*i <= n)
        {
            int filhoEsq, filhoDir, menorFilho;

            filhoEsq = 2*i;
            filhoDir = 2*i + 1;
            menorFilho = filhoEsq;

            if(filhoDir <= n)
            {
                if(vetor[ filhoDir ].frequencia < vetor[ filhoEsq ].frequencia)
                    menorFilho = filhoDir;
            }
            if(vetor[ menorFilho ].frequencia < x.frequencia)
                vetor [ i ] = vetor[ menorFilho ];
            else
                break;

            i = menorFilho;
        }

        vetor[ i ] = x;
    }

    public void insere(HuffmanNode x)
    {
        if (tamMaximo == n)
        {
            System.out.println("Fila de prioridades cheia!");
            return;
        }
        n++;
        int pos = n;

        vetor[0] = x;

        while(x.frequencia < vetor[pos/2].frequencia)
        {
            vetor[pos] = vetor[ pos/2 ];
            pos /= 2;
        }
        vetor[pos] = x;

    }

    public void mostraCodigos() {
        raiz.printCode(" ");
    }

    public void carregaDados() {
        Scanner scan = new Scanner(System.in);

        for(int i = 0, j = 1; i < tamMaximo; i++, j++){
            System.out.print("Digite o símbolo: ");
            char simbolo = scan.next().charAt(0);
            System.out.print("Digite a frequência: ");
            int frequencia = scan.nextInt();
            HuffmanNode node = new HuffmanNode(simbolo, frequencia);
            vetor[j] = node;
            n++;
        }
        buildHeap();
    }

    public long aplicaHuffman() {
        long somatorio = 0;
        int i = 1;
        while (n > 1){
            long inicio = System.currentTimeMillis();
            HuffmanNode esquerdaElem = removeMin();
            HuffmanNode direitaElemen = removeMin();

            HuffmanNode novoNo = new HuffmanNode(' ', esquerdaElem.frequencia + direitaElemen.frequencia);

            novoNo.esq = esquerdaElem;
            novoNo.dir = direitaElemen;
            vetor[i] = novoNo;
            raiz = novoNo;
            long termino = System.currentTimeMillis();
            somatorio += (termino - inicio);
            i++;
            printHeap();
        }
        buildHeap();
        raiz.showTree();
        System.out.println();
        return somatorio;
    }

    public long aplicaHuffman(String teste) {
        long somatorio = 0, inicio, termino;
        int i = 1;

        while (n > 1){
            inicio = System.currentTimeMillis();

            HuffmanNode esquerdaElem = removeMin();
            HuffmanNode direitaElemen = removeMin();

            int somaFrequencia = esquerdaElem.frequencia + direitaElemen.frequencia;
            HuffmanNode novoNo = new HuffmanNode(' ', somaFrequencia);
            novoNo.esq = esquerdaElem;
            novoNo.dir = direitaElemen;
            vetor[i] = novoNo;
            raiz = novoNo;

            termino = System.currentTimeMillis();
            somatorio += (termino - inicio);
        }
        return somatorio;
    }


    public void geraMinBinaryHeap (int frequenciaTotal){
        Random random = new Random();
        int frequencia, somaFrequencia = 0;

        char simbolo;
        boolean repetido = false;
        int aux = tamMaximo;
        int limite = frequenciaTotal - aux;
        for (int i = 0, index = 1; i < tamMaximo; i++, index++){
            do{
                if (aux > 1) {
                    simbolo = (char) (random.nextInt(93) + '!');
                    frequencia = random.nextInt((limite) + 1) + 1;
                }
                else {
                    simbolo = (char) (random.nextInt(93) + '!');
                    frequencia = frequenciaTotal - somaFrequencia;
                }
                for (int k = 1; k <= tamMaximo; k++){
                    if (vetor[k] != null) {
                        if (Character.valueOf(vetor[k].simbolo).compareTo(simbolo) == 0) {
                            repetido = true;
                            break;
                        }

                        else
                            repetido = false;
                    }
                    else
                        break;
                }
            } while (repetido == true);
            somaFrequencia += frequencia;
            aux --;
            limite = frequenciaTotal - aux - somaFrequencia;
            HuffmanNode hfNode = new HuffmanNode(simbolo, frequencia);
            vetor[index] = hfNode;
            n++;
        }
    }
    private long repeteGeracao( int quantidade, int tamanho){
        long somatorio = 0;

        for (int i = 0 ; i < quantidade; i++) {
            geraMinBinaryHeap(tamanho);
            long tempoHuffman = aplicaHuffman("teste");
            this.n = 0;
            this.vetor = new HuffmanNode[this.tamMaximo + 1];
            somatorio += tempoHuffman;
        }
        return somatorio;
    }
    static void imprimeTeste(){
        MinBinaryHeap heap5 = new MinBinaryHeap(5);
        MinBinaryHeap heap10 = new MinBinaryHeap(10);
        MinBinaryHeap heap12 = new MinBinaryHeap(12);
        MinBinaryHeap heap15 = new MinBinaryHeap(15);
        MinBinaryHeap heap20 = new MinBinaryHeap(20);
        MinBinaryHeap heap30 = new MinBinaryHeap(30);
        MinBinaryHeap heap50 = new MinBinaryHeap(50);
        MinBinaryHeap heap80 = new MinBinaryHeap(80);
        MinBinaryHeap heap93 = new MinBinaryHeap(93);

        long tempo5 = heap5.repeteGeracao(100000, 100);
        long tempo10 = heap10.repeteGeracao(100000, 100);
        long tempo12 = heap12.repeteGeracao(100000, 100);
        long tempo15 = heap15.repeteGeracao(100000, 100);
        long tempo20 = heap20.repeteGeracao(100000, 100);
        long tempo30 = heap30.repeteGeracao(100000, 100);
        long tempo50 = heap50.repeteGeracao(100000, 100);
        long tempo80 = heap80.repeteGeracao(100000, 100);
        long tempo93 = heap93.repeteGeracao(100000, 100);
        System.out.println("O tempo total de 100 mil ciclos para 5 simbolos diferentes e de: "+tempo5+"ms");
        System.out.println("O tempo total de 100 mil ciclos para 10 simbolos diferentes e de: "+tempo10+"ms");
        System.out.println("O tempo total de 100 mil ciclos para 12 simbolos diferentes e de: "+tempo12+"ms");
        System.out.println("O tempo total de 100 mil ciclos para 15 simbolos diferentes e de: "+tempo15+"ms");
        System.out.println("O tempo total de 100 mil ciclos para 20 simbolos diferentes e de: "+tempo20+"ms");
        System.out.println("O tempo total de 100 mil ciclos para 30 simbolos diferentes e de: "+tempo30+"ms");
        System.out.println("O tempo total de 100 mil ciclos para 50 simbolos diferentes e de: "+tempo50+"ms");
        System.out.println("O tempo total de 100 mil ciclos para 80 simbolos diferentes e de: "+tempo80+"ms");
        System.out.println("O tempo total de 100 mil ciclos para 93 simbolos diferentes e de: "+tempo93+"ms");
    }
}
