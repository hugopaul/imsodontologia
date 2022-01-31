package br.com.imsodontologia.imsodontologia.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Builder
@Table(schema = "ims", name = "tb_marcacao",uniqueConstraints = @UniqueConstraint(columnNames = {"id_marcacao", "id_usuario","id_paciente"}))
public class Marcacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dt_inicio")
    private String dtInicio;

    @Column(name = "dt_fim")
    private String dtFim;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(String dtInicio) {
        this.dtInicio = dtInicio;
    }

    public String getDtFim() {
        return dtFim;
    }

    public void setDtFim(String dtFim) {
        this.dtFim = dtFim;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Marcacao() {
    }

    public Marcacao(Integer id, String dtInicio, String dtFim, Paciente paciente, Usuario usuario) {
        this.id = id;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
        this.paciente = paciente;
        this.usuario = usuario;
    }

}
