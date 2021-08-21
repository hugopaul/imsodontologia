package br.com.imsodontologia.imsodontologia.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private String horas;

    @Column
    private Integer dias;
}
