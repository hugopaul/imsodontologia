package br.com.imsodontologia.imsodontologia.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@Builder
@Table(schema = "ims", name = "tb_receituario",uniqueConstraints = @UniqueConstraint(columnNames = {"id_receituario", "id_prontuario"}))
public class Receituario {

    @Id
    @Column(name = "id_receituario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Medicamento.class, cascade=CascadeType.PERSIST)
    private List<Medicamento> medicamento ;

    @OneToOne
    @JoinColumn(name = "id_prontuario")
    @NotNull(message = "Escolha o Paciente para registrar o Receituário!")
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

    public List<Medicamento> getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(List<Medicamento> medicamento) {
        this.medicamento = medicamento;
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

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Receituario(Integer id, List<Medicamento> medicamento, Prontuario prontuario, LocalDate dataCadastro) {
        this.id = id;
        this.medicamento = medicamento;
        this.prontuario = prontuario;
        this.dataCadastro = dataCadastro;
    }

    public Receituario() {
    }

}