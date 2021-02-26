package br.com.imsodontologia.imsodontologia.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;

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

    @NotNull(message = "O Medicamento deve ser descrito!")
    @Column
    private String medicamento;

    @NotNull(message = "Campo horas é obrigatório!")
    @Column
    private Time horas;

    @NotNull(message = "O campo Dias é Obrigatório!")
    @Column
    private Integer dias;

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