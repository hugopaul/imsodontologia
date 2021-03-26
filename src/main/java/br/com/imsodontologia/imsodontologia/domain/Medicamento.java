package br.com.imsodontologia.imsodontologia.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O Medicamento deve ser descrito!")
    @Column
    private String medicamento;

    @NotEmpty(message = "Campo horas é obrigatório!")
    @Column
    private String horas;

    @NotNull(message = "O campo Dias é Obrigatório!")
    @Column
    private Integer dias;
}
