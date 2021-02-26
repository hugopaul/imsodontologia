package br.com.imsodontologia.imsodontologia.rest;

import br.com.imsodontologia.imsodontologia.domain.Paciente;
import br.com.imsodontologia.imsodontologia.domain.Receituario;
import br.com.imsodontologia.imsodontologia.repository.PacienteRepository;
import br.com.imsodontologia.imsodontologia.repository.ReceituarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/receituarios")
public class ReceituarioController {

    private final ReceituarioRepository repository;

    @Autowired
    public  ReceituarioController( ReceituarioRepository repository){
        this.repository = repository;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Receituario salvar (@RequestBody @Valid Receituario receituario){
        return repository.save(receituario);
    }
    @GetMapping("{id}")
    public Receituario findById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Receituário não Encontrada"
                )
        );
    }
    @GetMapping
    public List<Receituario> findAll(){
        return repository.findAll();
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository.findById(id).map(
                receituario -> {
                    repository.delete(receituario);
                    return Void.TYPE;
                }
        ).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receituário não encontrado"));
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Receituario receituarioAtualizado){
        repository.findById(id).map(receituarioDesatualizada ->{
            receituarioAtualizado.setId(receituarioDesatualizada.getId());
            return repository.save(receituarioAtualizado);
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receituário não Encontrado"));
    }
}
