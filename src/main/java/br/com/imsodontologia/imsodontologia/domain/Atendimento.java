package br.com.imsodontologia.imsodontologia.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "prontuario",uniqueConstraints = @UniqueConstraint(columnNames = {"id", "id_paciente"}))
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String atendimento;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_de_cadastro", updatable = false)
    private LocalDate dataAtendimento;

    @OneToOne
    @JoinColumn(name = "id_prontuario")
    @JsonFormat
    private Prontuario prontuario;

    @PrePersist
    public void prePercist() {
        setDataAtendimento(LocalDate.now());
    }
}
