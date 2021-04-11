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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "atestado",uniqueConstraints = @UniqueConstraint(columnNames = {"id", "id_prontuario"}))
public class Atestado {


    @Id
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_de_cadastro", updatable = false)
    private LocalDate dataCadastro;

    @PrePersist
    public void prePercist() {
        setDataCadastro(LocalDate.now());
    }


}
