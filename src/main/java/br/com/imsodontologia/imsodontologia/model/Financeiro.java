package br.com.imsodontologia.imsodontologia.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(schema = "ims", name = "tb_financeiro",uniqueConstraints = @UniqueConstraint(columnNames = {"id_financeiro", "id_prontuario"}))
public class Financeiro {

        @Id
        @Column(name = "id_financeiro")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @OneToOne
        @JoinColumn(name = "id_prontuario")
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JsonFormat
        private Prontuario prontuario;

        @Column
        @NotNull(message = "O campo VALOR é Obrigatório" )
        private String valor;

        @Column
        @NotNull(message = "O campo SERVIÇO REALIZADO é obrigatório" )
        private String descricao;

        @Column
        private String observacao;

        @Column
        private Integer forma;

        @Column
        private Integer dividido;

        @Column
        private String status;

        @JsonFormat(pattern = "yyyy-MM-dd")
        @Column(name = "data_de_cadastro", updatable = false)
        private LocalDate dataLancamento;

        @PrePersist
        public void prePercist() {
                setDataLancamento(LocalDate.now());
                setStatus("Pendente");
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public Prontuario getProntuario() {
                return prontuario;
        }

        public void setProntuario(Prontuario prontuario) {
                this.prontuario = prontuario;
        }

        public String getValor() {
                return valor;
        }

        public void setValor(String valor) {
                this.valor = valor;
        }

        public String getDescricao() {
                return descricao;
        }

        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        public String getObservacao() {
                return observacao;
        }

        public void setObservacao(String observacao) {
                this.observacao = observacao;
        }

        public Integer getForma() {
                return forma;
        }

        public void setForma(Integer forma) {
                this.forma = forma;
        }

        public Integer getDividido() {
                return dividido;
        }

        public void setDividido(Integer dividido) {
                this.dividido = dividido;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public LocalDate getDataLancamento() {
                return dataLancamento;
        }

        public void setDataLancamento(LocalDate dataLancamento) {
                this.dataLancamento = dataLancamento;
        }
}
