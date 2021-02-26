package br.com.imsodontologia.imsodontologia.repository;

import br.com.imsodontologia.imsodontologia.domain.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Integer> {
}
