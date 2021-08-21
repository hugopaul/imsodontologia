package br.com.imsodontologia.imsodontologia.rest;

import br.com.imsodontologia.imsodontologia.model.Paciente;
import br.com.imsodontologia.imsodontologia.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteRepository repository;

    @Autowired
    public PacienteController(PacienteRepository repository){
        this.repository = repository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente salvar (@RequestBody @Valid Paciente paciente){
        try {
            return repository.save(paciente);
        } catch (Exception ex){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , "Esta CPF já foi cadastrado!");
        }
    }



    @GetMapping("{id}")
    public Paciente findById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Paciente não Encontrado(a)"
                )
        );
    }
    @GetMapping
    public List<Paciente> findAll(){
        return repository.findAll();
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository.findById(id).map(
                paciente -> {
                    repository.delete(paciente);
                    return Void.TYPE;
                }
        ).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Paciente pacienteAtualizado){
        repository.findById(id).map(pacienteDesatualizada ->{
            pacienteAtualizado.setId(pacienteDesatualizada.getId());
            return repository.save(pacienteAtualizado);
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não Encontrado"));
    }
}
