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
@Table(name = "atestado",uniqueConstraints = @UniqueConstraint(columnNames = {"id", "id_paciente"}))
public class Atestado {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O horário de chegada é obrigatório!")
    @Column
    private Time chegada;

    @NotNull(message = "O horário de saida é obrigatório!")
    @Column
    private Time saida;

    @NotNull(message = "O campo Dias de repouso é obrigatório!")
    @Column
    private Integer repouso;

    @NotNull(message = "O campo CID é obrigatório!")
    @Column
    private Integer cid;

    @NaturalId
    @OneToOne
    @JoinColumn(name = "id_paciente")
    @NotNull(message = "Escolha o Paciente para continuar registrar o Atestado Médico!")
    @JsonFormat
    private Paciente paciente;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_de_cadastro", updatable = false)
    private LocalDate dataCadastro;

    @PrePersist
    public void prePercist() {
        setDataCadastro(LocalDate.now());
    }


}
