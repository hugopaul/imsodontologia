package br.com.imsodontologia.imsodontologia.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "receituario",uniqueConstraints = @UniqueConstraint(columnNames = {"id", "id_prontuario"}))
public class Receituario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(targetEntity = Medicamento.class, cascade=CascadeType.PERSIST)
    @JoinColumn(name = "id_receituario")
    private List<Medicamento> medicamento ;

    @OneToOne
    @JoinColumn(name = "id_prontuario")
    @NotNull(message = "Escolha o Paciente para registrar o Receituário!")
    @JsonFormat
    private Prontuario prontuario;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_de_cadastro", updatable = false)
    private LocalDate dataCadastro;

    @PrePersist
    public void prePercist() {
        setDataCadastro(LocalDate.now());
    }


}