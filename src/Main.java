import atendimento.Atendimento;
import atendimento.ListAtendimento;
import pessoa.Especializacao;
import pessoa.Medico;
import pessoa.Paciente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    static Scanner entrada = new Scanner(System.in);
    static ListAtendimento listAtendimento = new ListAtendimento();

    public static void main(String[] args) {

        Medico medico1 = new Medico();
        Medico medico2 = new Medico();

        Especializacao especializacao1 = new Especializacao();
        especializacao1.setTitulo("Cardiologista");
        especializacao1.setDescricao("Especialidade em Corações");
        Especializacao especializacao2 = new Especializacao();
        especializacao1.setTitulo("Pediatra");
        especializacao1.setDescricao("Especialidade em Crianças");

        medico1.especializadoEm(especializacao1);
        medico2.especializadoEm(especializacao1).especializadoEm(especializacao2);

        System.out.println("Sistema de Atendimento");
        boolean atendendo = true;

        while (atendendo) {
            System.out.println("------------------");
            System.out.println("Opções");
            System.out.println("------------------");
            System.out.println("Novo Atendimento (1)");
            System.out.println("Finalizar Atendimento em Aberto (2)");
            System.out.println("Registrar Saída de Paciente (3)");
            System.out.println("Fechar Atendimentos (4)");

            switch (entrada.nextInt()) {
                case 1 -> novoAtendimento();
                case 2 -> finalizarAtendimento();
                case 3 -> saidaPaciente();
                case 4 -> atendendo = false;
            }
        }

    }

    public static void novoAtendimento() {
        Paciente paciente = new Paciente();

        entrada.nextLine();

        System.out.print("Nome do paciente: ");
        String nome = entrada.nextLine();
        paciente.setNome(nome);

        System.out.println("Data de Nascimento do Paciente: ");
        String str = entrada.nextLine();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        paciente.setNascimento(LocalDate.parse(str, dtf));

        System.out.println("CPF do paciente");
        long cpf = entrada.nextLong();
        paciente.setCpf(cpf);
        entrada.nextLine();

        int prioridade = 0;

        List<String> perguntas = new ArrayList<>();
        perguntas.add("Paciente apresenta hemorragia? (S/N)");
        perguntas.add("Paciente apresenta desorientação? (S/N)");
        perguntas.add("Paciente apresenta queimaduras? (S/N)");
        perguntas.add("Paciente se enquadra para atendimento prioritário? (S/N)");
        perguntas.add("Paciente corre risco de vida? (S/N)");

        for (String pergunta : perguntas) {
            System.out.print(pergunta);
            if (entrada.nextLine().equalsIgnoreCase("s")) {
                prioridade++;
            }
            System.out.println();
        }

        Atendimento atendimento = new Atendimento(paciente, LocalDate.now(), prioridade);
        listAtendimento.adicionarAtendimento(atendimento);
        Collections.sort(listAtendimento.getAtendimentos());
        System.out.println("Paciente registrado com sucesso");
    }

    public static void finalizarAtendimento() {
        entrada.nextLine();

        System.out.println("Finalização de Atendimento");
        System.out.println("Selecionando Atendimento com a maior prioridade:");
        Atendimento atendimento = null;
        for (Atendimento atendimentoIterado : listAtendimento.getAtendimentos()) {
            if (atendimentoIterado.isAtivo()) {
                System.out.println("Atendimento de Paciente " + atendimentoIterado.getPaciente().getNome());
                atendimento = atendimentoIterado;
                break;
            }
        }

        atendimento.finalizarAtendimento();
        System.out.println("Atendimento Finalizado");

        System.out.println("Deseja emitir atestado? (S/N)");
        if (entrada.nextLine().equalsIgnoreCase("s")) {
            System.out.println(atendimento.emitirAtestado());
        }
    }

    public static void saidaPaciente() {
        entrada.nextLine();

        System.out.println("Registro de Saida do Paciente");

        List<Atendimento> list = listAtendimento.getAtendimentos();

        for (int i = 0; i < list.size(); i++) {
            System.out.println("Paciente " + (i+1) + ": " + list.get(i).getPaciente().getNome());
        }
        System.out.println("Escolha o número do paciente a sair");
        int num = entrada.nextInt();

        Atendimento atendimento = list.get(num - 1);

        atendimento.setSaidaPaciente(LocalDate.now());
        listAtendimento.getAtendimentos().remove(atendimento);
        System.out.println("Horario de saída atualizado");
    }
}
