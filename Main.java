import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Central central = new Central();
        central.adicionar(new Chamado(1, "Computador sem internet"));
        central.adicionar(new ChamadoUrgente(2, "Servidor indisponivel"));
        central.adicionar(new Chamado(3, "Mouse com defeito"));
        central.adicionar(new ChamadoUrgente(4, "Sistema de matriculas fora do ar"));
        central.adicionar(new Chamado(5, "Projetor falhando"));
        central.adicionar(new ChamadoUrgente(6, "Computador do Adm nao estar conectando a internet."));

        try {
            Chamado test = new Chamado(7, " ");
        } catch (IllegalArgumentException e) {
            System.out.println("Falha ao realizar chamado: " + e.getMessage());
        }

        System.out.println("===\n LISTA DE CHAMADOS PARA A CENTRAL DE ATENDIMENTOS.");
        central.listar();

        Thread ana = new Thread(new Atendente("Ana", central));
        Thread leo = new Thread(new Atendente("Leo", central));
        System.out.println("\n=== ORDEM DE ATENDIMENTO ===");
        ana.start();
        leo.start();
        ana.join();
        leo.join();

        System.out.println("\nReinicie: sem banco, os chamados voltam ao estado inicial.");
    }
}

class Chamado {
    private final int numero;
    private final String problema;
    private boolean atendido;

    public Chamado(int numero, String problema) {
        if (numero <= 0 || problema == null || problema.trim().isEmpty()) {
            throw new IllegalArgumentException("O numero ou a descrição do chamado não podem estar vazio.");
        }
        this.numero = numero;
        this.problema = problema;
        this.atendido = false;
    }

    public boolean isAtendido() {
        return atendido;
    }

    public void atender() {
        atendido = true;
    }

    public String tipo() {
        return "COMUM";
    }

    public String resumo() {
        return "#" + numero + " [" + tipo() + "] " + problema
                + " - " + (atendido ? "atendido" : "pendente");
    }
}

class ChamadoUrgente extends Chamado {
    public ChamadoUrgente(int numero, String problema) {
        super(numero, problema);
    }

    @Override
    public String tipo() {
        return "URGENTE";
    }
}

class Central {
    private final List<Chamado> chamados = new ArrayList<>();

    public void adicionar(Chamado chamado) {
        chamados.add(chamado);
    }

    public synchronized Chamado proximo() {
        // DESAFIO 1: faca os URGENTES passarem na frente dos COMUNS.
        for (Chamado chamadoUrgent : chamados) {
            if (!chamadoUrgent.isAtendido() && chamadoUrgent.tipo().equals("URGENTE")) {
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return null;
                }
                chamadoUrgent.atender();
                return chamadoUrgent;
            }

        }

        for (Chamado chamadoComun : chamados) {
            if (!chamadoComun.isAtendido() && chamadoComun.tipo().equals("COMUM")) {
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return null;
                }

                chamadoComun.atender();
                return chamadoComun;
            }
        }
        return null;
    }

    public void listar() {
        for (Chamado chamado : chamados) {
            System.out.println(chamado.resumo());
        }
    }
}

class Atendente implements Runnable {
    private final String nome;
    private final Central central;

    public Atendente(String nome, Central central) {
        this.nome = nome;
        this.central = central;
    }

    @Override
    public void run() {
        while (true) {
            Chamado chamado = central.proximo();
            if (chamado == null) {
                break;
            }
            System.out.println(nome + " atendeu: " + chamado.resumo());
        }
    }
}
