package pessoa;

import java.util.ArrayList;
import java.util.List;

public class Medico extends Pessoa{
    List<Especializacao> especializacoes = new ArrayList<>();

    public Medico() {
    }

    public Medico especializadoEm(Especializacao especializacao) {
        especializacoes.add(especializacao);
        return this;
    }

    public List<Especializacao> getEspecializacoes() {
        return especializacoes;
    }

    public void setEspecializacoes(List<Especializacao> especializacoes) {
        this.especializacoes = especializacoes;
    }
}
