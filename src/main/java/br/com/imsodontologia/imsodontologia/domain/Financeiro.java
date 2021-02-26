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
@Table(name = "financeiro",uniqueConstraints = @UniqueConstraint(columnNames = {"id", "id_prontuario"}))
public class Financeiro {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @OneToOne
        @JoinColumn(name = "id_prontuario")
        @JsonFormat
        private Prontuario prontuario;

        @Column
        private String valor;

        @Column
        private String descricao;

        @JsonFormat(pattern = "yyyy-MM-dd")
        @Column(name = "data_de_cadastro", updatable = false)
        private LocalDate dataLancamento;

        @PrePersist
        public void prePercist() {
                setDataLancamento(LocalDate.now());
        }
}
