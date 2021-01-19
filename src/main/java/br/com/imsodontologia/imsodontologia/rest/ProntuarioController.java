package br.com.imsodontologia.imsodontologia.rest;

import br.com.imsodontologia.imsodontologia.domain.Paciente;
import br.com.imsodontologia.imsodontologia.domain.Prontuario;
import br.com.imsodontologia.imsodontologia.repository.PacienteRepository;
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
        return repository.save(prontuario);
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

