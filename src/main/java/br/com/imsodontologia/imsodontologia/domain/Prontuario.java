package br.com.imsodontologia.imsodontologia.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.org.apache.xpath.internal.operations.Bool;
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

    @NotNull(message = "Marque sim ou não no campo DIABÉTICO")
    @Column
    private Boolean diabetico ;

    @NotNull(message = "Marque sim ou não no campo HIPERTENSO")
    @Column
    private Boolean hipertenso;

    @NotNull(message = "Marque sim ou não no campo CARDÍACO")
    @Column
    private Boolean cardiaco ;

    @NotNull(message = "Marque sim ou não no campo DOENÇAS TRANSMISSÍVEIS")
    @Column
    private Boolean dst ;

    @NotNull(message = "Marque sim ou não no campo GESTANTE")
    @Column
    private Boolean gestante;

    @NotNull(message = "Marque sim ou não no campo Epilético")
    @Column
    private Boolean epiletico;

    @Column
    private String observacao ;

    @Column
    private Integer serviço;

    @Column
    private String valor;

    @Column
    private String onze;
    @Column
    private String doze;
    @Column
    private String treze;
    @Column
    private String quatorze;
    @Column
    private String  quinze;
    @Column
    private String   dezesseis;
    @Column
    private String  dezessete;
    @Column
    private String   dezoito;

    @Column
    private String   vinteum;
    @Column
    private String   vintedois;
    @Column
    private String  vintetres;
    @Column
    private String  vintequatro;
    @Column
    private String  vintecinco;
    @Column
    private String   vinteseis;
    @Column
    private String   vintesete;
    @Column
    private String  vinteoito;

    @Column
    private String  trintaeum;
    @Column
    private String   trintaedois;
    @Column
    private String  trintaetres;
    @Column
    private String  trintaequatro;
    @Column
    private String   trintaecinco;
    @Column
    private String   trintaeseis;
    @Column
    private String   trintaesete;
    @Column
    private String   trintaeoito;

    @Column
    private String   quarentaeum;
    @Column
    private String   quarentaedois;
    @Column
    private String  quarentaetres;
    @Column
    private String  quarentaequatro;
    @Column
    private String  quarentaecinco;
    @Column
    private String   quarentaeseis;
    @Column
    private String   quarentaesete;
    @Column
    private String  quarentaeoito;


    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_de_cadastro", updatable = false)
    private LocalDate dataCadastro;

    @PrePersist
    public void prePercist() {
        setDataCadastro(LocalDate.now());
    }
}
