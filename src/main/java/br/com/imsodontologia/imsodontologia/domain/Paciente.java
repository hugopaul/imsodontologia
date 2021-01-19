package br.com.imsodontologia.imsodontologia.domain;

import com.danielfariati.annotation.CPF;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.org.apache.xpath.internal.objects.XString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "O NOME COMPLETO não pode ser vazio.")
    @Column
    private String paciente;

    @CPF(message = "CPF inválido! Por favor, verifique.")
    @NotEmpty(message = "O campo CPF não pode ser vazio.")
    private String cpf;

    @Column
    private String endereco;

    @Column
    private String telefone;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_de_nascimento")
    private Date dataNascimento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_de_cadastro", updatable = false)
    private LocalDate dataCadastro;

    @PrePersist
    public void prePercist() {
        setDataCadastro(LocalDate.now());
    }

}

