import java.util.ArrayList;

public class Elevador {

    public Balanca balanca;
    public int cargaMaxima;
    public int andarAtual;
    public String sentidoAtual;
    public boolean distribuicaoDeAndares = false;
    public ArrayList<Integer> andares = new ArrayList<Integer>();
    public ArrayList<Integer> proximosAndares = new ArrayList<Integer>();

    public Elevador() {
        this.balanca = new Balanca();
        this.cargaMaxima = 500;
    }

    public void enviarParaAndar(int andar) {
        System.out.println("Enviando elevador para o andar " + andar);
    }

    public void abrirPorta() {
        System.out.println("Abrindo porta do elevador");
    }

    public void fecharPorta() {
        System.out.println("Fechando porta do elevador");
    }

    public boolean verificarExcessoDePeso() {
        int peso = balanca.getPeso();
        return peso > cargaMaxima;
    }

    public int getAndarAtual() {
        return andarAtual;
    }

    public String getSentidoAtual() {
        return sentidoAtual;
    }

    public Balanca getBalanca() {
        return balanca;
    }

    public void exibirAvisoDeSobrecarga() {
        System.out.println("Aviso de sobrecarga!");
    }

    public void pararElevador() {
        System.out.println("Elevador parado!");
    }

    public void exibirAndarAtual() {
        System.out.println("Andar atual: " + andarAtual);
    }

    public void exibirSentidoAtual() {
        System.out.println("Sentido atual: " + sentidoAtual);
    }

    public void exibirProximasParadas() {
        System.out.println("Próximos andares: " + proximosAndares);
    }

}
