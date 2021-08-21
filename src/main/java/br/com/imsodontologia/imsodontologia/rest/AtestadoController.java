package br.com.imsodontologia.imsodontologia.rest;

import br.com.imsodontologia.imsodontologia.model.Atestado;
import br.com.imsodontologia.imsodontologia.repository.AtestadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/atestados")
public class AtestadoController {

    private final AtestadoRepository repository;

    @Autowired
    public AtestadoController(AtestadoRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Atestado salvar (@RequestBody @Valid Atestado atestado){
        return repository.save(atestado);
    }

    @GetMapping("{id}")
    public Atestado findById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Atestado não Encontrada"
                )
        );
    }
    @GetMapping
    public List<Atestado> findAll(){
        return repository.findAll();
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository.findById(id).map(
                atestado -> {
                    repository.delete(atestado);
                    return Void.TYPE;
                }
        ).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Atestado não encontrado"));
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Atestado atestadoAtualizado){
        repository.findById(id).map(atestadoDesatualizada ->{
            atestadoAtualizado.setId(atestadoDesatualizada.getId());
            return repository.save(atestadoAtualizado);
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Atestado não Encontrado"));
    }
}
