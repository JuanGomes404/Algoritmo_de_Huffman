import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        MinBinaryHeap heap;
        System.out.println("Modo experimento:\n1 - SIM\nQUALQUER TECLA  - NAO");
        String op = scanner.next();
        do {
            System.out.println("Quantos simbolos?");
            n = scanner.nextInt();
            heap = new MinBinaryHeap(n);
        } while (n > 93);

        if(Objects.equals(op, "1")) {
            System.out.println("MODO EXPERIMENTO ATIVADO\n");
            int freq = 0;

            do{
                System.out.print("Digite a frequencia máxima desejada: ");
                freq = scanner.nextInt();
                if(freq < n)
                    System.out.println("Frequencia menor que a quantidade de símbolos distintos. Digite novamente");

            }while (freq < n );

            heap.geraMinBinaryHeap(freq);
            MinBinaryHeap.imprimeTeste();
        }
        else {
            heap.carregaDados();
            heap.printHeap();
            heap.aplicaHuffman();
            heap.mostraCodigos();
            scanner.close();
        }
        //Utilizado em casos de testes

    }
}
