package atendimento;

import pessoa.Medico;
import pessoa.Paciente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Atendimento implements Comparable<Atendimento> {
    List<Medico> medicos = new ArrayList<>();
    Paciente paciente;
    LocalDate chegadaPaciente;
    LocalDate saidaPaciente;
    LocalDate comecoAtendimento;
    LocalDate fimAtendimento;
    boolean ativo = true;
    int prioridade;

    public Atendimento(Paciente paciente, LocalDate chegadaPaciente, int prioridade) {
        this.paciente = paciente;
        this.chegadaPaciente = chegadaPaciente;
        this.prioridade = prioridade;
    }

    public void finalizarAtendimento() {
        if (ativo) {
            fimAtendimento = LocalDate.now();
            saidaPaciente = LocalDate.now();
            ativo = false;
        }
    }

    public String emitirAtestado() {
        if (saidaPaciente == null) {
            return "Atestado emitido para " + "NOME_PACIENTE " + "por Dr. " + "NOME_DOUTOR";
        } else {
            return "Impossível emitir atestado";
        }
    }

    public LocalDate getChegadaPaciente() {
        return chegadaPaciente;
    }

    public void setChegadaPaciente(LocalDate chegadaPaciente) {
        this.chegadaPaciente = chegadaPaciente;
    }

    public LocalDate getSaidaPaciente() {
        return saidaPaciente;
    }

    public void setSaidaPaciente(LocalDate saidaPaciente) {
        this.saidaPaciente = saidaPaciente;
    }

    public LocalDate getComecoAtendimento() {
        return comecoAtendimento;
    }

    public void setComecoAtendimento(LocalDate comecoAtendimento) {
        this.comecoAtendimento = comecoAtendimento;
    }

    public LocalDate getFimAtendimento() {
        return fimAtendimento;
    }

    public void setFimAtendimento(LocalDate fimAtendimento) {
        this.fimAtendimento = fimAtendimento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public int compareTo(Atendimento o) {
        int comparePriority = (o).getPrioridade();
        return comparePriority - this.prioridade;
    }
}
