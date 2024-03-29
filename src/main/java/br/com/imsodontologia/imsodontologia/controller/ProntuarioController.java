package br.com.imsodontologia.imsodontologia.controller;

import br.com.imsodontologia.imsodontologia.model.Prontuario;
import br.com.imsodontologia.imsodontologia.repository.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    private final ProntuarioRepository repository;

    @Autowired
    public ProntuarioController(ProntuarioRepository repository){
        this.repository = repository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prontuario  salvar (@RequestBody @Valid Prontuario prontuario){
        try {
            return repository.save(prontuario);
        } catch (Exception ex){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , "Já existe Prontuário para este Paciente!");
        }
    }

    @GetMapping("prontbypac/{id}")
    public Prontuario findProntByPac(@PathVariable Integer id ){
        Prontuario pront = repository.findProntByPac(id);
        if(pront != null){
            return pront;
        }else{
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado");
        }

    }
    @PostMapping("/buscar-string")
    @ResponseStatus(HttpStatus.OK)
    public List<Prontuario> getByNome(@RequestBody String getByNome){
        return this.repository.buscar(getByNome);
    }

    @GetMapping("{id}")
    public Prontuario findById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Prontuário não Encontrado"
                )
        );
    }
    @GetMapping
    public List<Prontuario> findAll(){
        return repository.findAll();
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository.findById(id).map(
                prontuario -> {
                    repository.delete(prontuario);
                    return Void.TYPE;
                }
        ).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prontuário não encontrado"));
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Prontuario prontuarioAtualizado){
        repository.findById(id).map(prontuarioDesatualizada ->{
            prontuarioAtualizado.setId(prontuarioDesatualizada.getId());
            return repository.save(prontuarioAtualizado);
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prontuário não Encontrado"));
    }
}

