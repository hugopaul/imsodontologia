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
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "prontuario",uniqueConstraints = @UniqueConstraint(columnNames = {"id", "id_paciente"}))
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NaturalId
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_paciente")
    @NotNull(message = "Escolha o Paciente para continuar o atendimento")
    @JsonFormat
    private Paciente paciente;

    @Column
    private Boolean diabetico ;

    @Column
    private Boolean hipertenso;

    @Column
    private Boolean cardiaco ;

    @Column
    private Boolean dst ;

    @Column
    private Boolean gestante;

    @Column
    private Boolean epiletico;

    @Column
    private String observacao ;

    @Column
    private ArrayList dentes = new ArrayList<String>(32);


    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_de_cadastro", updatable = false)
    private LocalDate dataCadastro;

    @PrePersist
    public void prePercist() {
        setDataCadastro(LocalDate.now());
    }
}
