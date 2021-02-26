package br.com.imsodontologia.imsodontologia.rest;

import br.com.imsodontologia.imsodontologia.domain.Atendimento;
import br.com.imsodontologia.imsodontologia.repository.AtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {


        private final AtendimentoRepository repository;

        @Autowired
        public AtendimentoController(AtendimentoRepository repository){
            this.repository = repository;
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Atendimento salvar (@RequestBody @Valid Atendimento atendimento){
            return repository.save(atendimento);
        }

        @GetMapping("{id}")
        public Atendimento findById(@PathVariable Integer id){
            return repository.findById(id).orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Não Encontrado"
                    )
            );
        }
        @GetMapping
        public List<Atendimento> findAll(){
            return repository.findAll();
        }
        @DeleteMapping("{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deletar(@PathVariable Integer id){
            repository.findById(id).map(
                    atendimento -> {
                        repository.delete(atendimento);
                        return Void.TYPE;
                    }
            ).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lançamento não encontrado"));
        }
        @PutMapping("{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void update(@PathVariable Integer id, @RequestBody @Valid Atendimento atendimentoAtualizado){
            repository.findById(id).map(atendimentoDesatualizada ->{
                atendimentoAtualizado.setId(atendimentoDesatualizada.getId());
                return repository.save(atendimentoAtualizado);
            }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Financeiro não Encontrado"));
        }
    }
