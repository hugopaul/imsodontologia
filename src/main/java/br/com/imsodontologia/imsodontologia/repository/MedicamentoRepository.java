package br.com.imsodontologia.imsodontologia.repository;

import br.com.imsodontologia.imsodontologia.domain.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {
}
