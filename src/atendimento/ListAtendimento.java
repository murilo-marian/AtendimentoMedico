package atendimento;

import java.util.ArrayList;
import java.util.List;

public class ListAtendimento {
    List<Atendimento> atendimentos = new ArrayList<>();

    public void adicionarAtendimento(Atendimento atendimento) {
        atendimentos.add(atendimento);
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }
}
