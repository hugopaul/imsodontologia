package br.com.imsodontologia.imsodontologia.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "atendimento",uniqueConstraints = @UniqueConstraint(columnNames = {"id", "id_prontuario"}))
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull(message = "O campo VALOR é Obrigatório" )
    private String valor;

    @Column
    @NotNull(message = "O campo SERVIÇO REALIZADO é obrigatório" )
    private String atendimento;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_de_cadastro", updatable = false)
    private LocalDate dataAtendimento;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_prontuario")
    @JsonFormat
    private Prontuario prontuario;

    @PrePersist
    public void prePercist() {
        setDataAtendimento(LocalDate.now());
    }
}
