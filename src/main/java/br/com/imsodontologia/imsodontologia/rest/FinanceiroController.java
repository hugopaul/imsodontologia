package br.com.imsodontologia.imsodontologia.rest;

import br.com.imsodontologia.imsodontologia.domain.Atestado;
import br.com.imsodontologia.imsodontologia.domain.Financeiro;
import br.com.imsodontologia.imsodontologia.repository.FinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/financeiros")
public class FinanceiroController {

    private final FinanceiroRepository repository;

    @Autowired
    public FinanceiroController(FinanceiroRepository repository){
        this.repository = repository;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Financeiro salvar (@RequestBody @Valid Financeiro financeiro){
        return repository.save(financeiro);
    }
    @GetMapping("{id}")
    public Financeiro findById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Não Encontrado"
                )
        );
    }
    @GetMapping
    public List<Financeiro> findAll(){
        return repository.findAll();
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository.findById(id).map(
                financeiro -> {
                    repository.delete(financeiro);
                    return Void.TYPE;
                }
        ).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Atestado não encontrado"));
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Financeiro financeiroAtualizado){
        repository.findById(id).map(financeiroDesatualizada ->{
            financeiroAtualizado.setId(financeiroDesatualizada.getId());
            return repository.save(financeiroAtualizado);
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Financeiro não Encontrado"));
    }
}
