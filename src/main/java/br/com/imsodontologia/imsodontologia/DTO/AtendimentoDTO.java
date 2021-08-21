package br.com.imsodontologia.imsodontologia.DTO;

import br.com.imsodontologia.imsodontologia.model.Prontuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AtendimentoDTO {
        @Id
        @Column(name = "id_atendimento")
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


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }

        public String getAtendimento() {
            return atendimento;
        }

        public void setAtendimento(String atendimento) {
            this.atendimento = atendimento;
        }

        public LocalDate getDataAtendimento() {
            return dataAtendimento;
        }

        public void setDataAtendimento(LocalDate dataAtendimento) {
            this.dataAtendimento = dataAtendimento;
        }

        public AtendimentoDTO(Integer id, String valor, String atendimento, LocalDate dataAtendimento, Prontuario prontuario) {
            this.id = id;
            this.valor = valor;
            this.atendimento = atendimento;
            this.dataAtendimento = dataAtendimento;
        }

        @PrePersist
        public void prePercist() {
            setDataAtendimento(LocalDate.now());
        }

    }


