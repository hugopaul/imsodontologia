package br.com.imsodontologia.imsodontologia.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Builder
@Table(schema = "ims", name = "tb_medicamento")
public class Medicamento {

    @Id
    @Column(name = "id_medicamento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String medicamento;

    @Column
    private LocalTime horas;

    @Column
    private Integer dias;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public LocalTime getHoras() {
        return horas;
    }

    public void setHoras(LocalTime horas) {
        this.horas = horas;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Medicamento(Integer id, String medicamento, LocalTime horas, Integer dias) {
        this.id = id;
        this.medicamento = medicamento;
        this.horas = horas;
        this.dias = dias;
    }

    public Medicamento() {
    }
}
