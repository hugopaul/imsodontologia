package br.com.imsodontologia.imsodontologia.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "ims", name = "tb_prontuario",uniqueConstraints = @UniqueConstraint(columnNames = {"id_prontuario", "id_paciente"}))
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prontuario")
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


    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_de_cadastro", updatable = false)
    private LocalDate dataCadastro;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Boolean getDiabetico() {
        return diabetico;
    }

    public void setDiabetico(Boolean diabetico) {
        this.diabetico = diabetico;
    }

    public Boolean getHipertenso() {
        return hipertenso;
    }

    public void setHipertenso(Boolean hipertenso) {
        this.hipertenso = hipertenso;
    }

    public Boolean getCardiaco() {
        return cardiaco;
    }

    public void setCardiaco(Boolean cardiaco) {
        this.cardiaco = cardiaco;
    }

    public Boolean getDst() {
        return dst;
    }

    public void setDst(Boolean dst) {
        this.dst = dst;
    }

    public Boolean getGestante() {
        return gestante;
    }

    public void setGestante(Boolean gestante) {
        this.gestante = gestante;
    }

    public Boolean getEpiletico() {
        return epiletico;
    }

    public void setEpiletico(Boolean epiletico) {
        this.epiletico = epiletico;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public ArrayList getDentes() {
        return dentes;
    }

    public void setDentes(ArrayList dentes) {
        this.dentes = dentes;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @PrePersist
    public void prePercist() {
        setDataCadastro(LocalDate.now());
    }
}
