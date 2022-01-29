package br.com.imsodontologia.imsodontologia.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;



@Entity
@Builder
@Table(schema = "ims", name = "tb_atendimento",uniqueConstraints = @UniqueConstraint(columnNames = {"id_atendimento", "id_prontuario"}))
public class Atendimento {

    @Id
    @Column(name = "id_atendimento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull(message = "O campo VALOR é Obrigatório" )
    private String valor;

    @Column
    @NotNull(message = "O campo SERVIÇO REALIZADO é obrigatório" )
    private String atendimento;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_de_cadastro", updatable = false)
    private LocalDate dataAtendimento;

    @ManyToOne
    @JoinColumn(name = "id_prontuario", referencedColumnName = "id_prontuario")
    private Prontuario prontuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(String atendimento) {
        this.atendimento = atendimento;
    }

    public LocalDate getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDate dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

    public Atendimento(Integer id, String valor, String atendimento, LocalDate dataAtendimento, Prontuario prontuario) {
        this.id = id;
        this.valor = valor;
        this.atendimento = atendimento;
        this.dataAtendimento = dataAtendimento;
        this.prontuario = prontuario;
    }

    public Atendimento() {
    }

    @PrePersist
    public void prePercist() {
        setDataAtendimento(LocalDate.now());
    }

}
