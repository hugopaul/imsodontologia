package br.com.imsodontologia.imsodontologia.repository;

import br.com.imsodontologia.imsodontologia.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    @Query( value = "select * from ims.tb_paciente where paciente ilike %:x%" ,nativeQuery = true)
    public List<Paciente> buscar(String x);
}
