package br.com.imsodontologia.imsodontologia.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@Builder
@Table(schema = "ims", name = "tb_atestado",uniqueConstraints = @UniqueConstraint(columnNames = {"id_atestado", "id_prontuario"}))
public class Atestado {


    @Id
    @Column(name = "id_atestado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "O horário de chegada é obrigatório!")
    @Column
    private String chegada;

    @NotEmpty(message = "O horário de saida é obrigatório!")
    @Column
    private String saida;

    @NotNull(message = "O campo Dias de repouso é obrigatório!")
    @Column
    private Integer repouso;

    @Column
    private String cid;

    @ManyToOne
    @JoinColumn(name = "id_prontuario")
    @NotNull(message = "Escolha o Paciente para registrar!")
    @JsonFormat
    private Prontuario prontuario;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_de_cadastro", updatable = false)
    private LocalDate dataCadastro;

    @PrePersist
    public void prePercist() {
        setDataCadastro(LocalDate.now());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChegada() {
        return chegada;
    }

    public void setChegada(String chegada) {
        this.chegada = chegada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public Integer getRepouso() {
        return repouso;
    }

    public void setRepouso(Integer repouso) {
        this.repouso = repouso;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public Atestado() {
    }

    public Atestado(Integer id, String chegada, String saida, Integer repouso, String cid, Prontuario prontuario, LocalDate dataCadastro) {
        this.id = id;
        this.chegada = chegada;
        this.saida = saida;
        this.repouso = repouso;
        this.cid = cid;
        this.prontuario = prontuario;
        this.dataCadastro = dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
