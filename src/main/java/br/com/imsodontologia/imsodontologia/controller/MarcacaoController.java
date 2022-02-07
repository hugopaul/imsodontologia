package br.com.imsodontologia.imsodontologia.controller;

import br.com.imsodontologia.imsodontologia.model.Marcacao;
import br.com.imsodontologia.imsodontologia.repository.MarcacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/marcacoes")
public class MarcacaoController {

    private final MarcacaoRepository repository;

    @Autowired
    public MarcacaoController(MarcacaoRepository repository){
        this.repository = repository;
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Marcacao salvar (@RequestBody @Valid Marcacao marcacao){
        try {
            return repository.save(marcacao);
        } catch (Exception ex){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , "Esta marcação já existe!");
        }
    }


    @GetMapping("{id}")
    public Marcacao findById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Marcação não Encontrado(a)"
                )
        );
    }
    @GetMapping("/buscar")
    public List<Marcacao> buscar(@RequestParam String dtinicio, @RequestParam String dtfim){
        String dt1 = dtinicio.replace("T", " ");
        String dt2 = dtfim.replace("T", " ");
        return repository.getByDate(dt1,dt2);
    }
    @GetMapping
    public List<Marcacao> findAll(){
        return repository.findAll();
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository.findById(id).map(
                marcacao -> {
                    repository.delete(marcacao);
                    return Void.TYPE;
                }
        ).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Marcação não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Marcacao marcacaoAtualizado){
        repository.findById(id).map(marcacaoDesatualizada ->{
            marcacaoAtualizado.setId(marcacaoDesatualizada.getId());
            return repository.save(marcacaoAtualizado);
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Marcação não Encontrado"));
    }


}
