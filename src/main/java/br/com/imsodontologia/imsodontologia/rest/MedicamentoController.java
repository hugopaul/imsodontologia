package br.com.imsodontologia.imsodontologia.rest;

import br.com.imsodontologia.imsodontologia.domain.Medicamento;
import br.com.imsodontologia.imsodontologia.domain.Paciente;
import br.com.imsodontologia.imsodontologia.domain.Receituario;
import br.com.imsodontologia.imsodontologia.repository.MedicamentoRepository;
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
@RequestMapping("/medicamentos")
public class MedicamentoController {

    private final MedicamentoRepository repository;

    @Autowired
    public  MedicamentoController( MedicamentoRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Medicamento salvar (@RequestBody @Valid Medicamento medicamento){
        return repository.save(medicamento);
    }

    @PostMapping("/saveall")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Medicamento> saveAll (@RequestBody List<Medicamento> medicamentos){
        System.out.println();
        return repository.saveAll(medicamentos);
    }


    @GetMapping("{id}")
    public Medicamento findById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Medicamento não encontrado"
                )
        );
    }
    @GetMapping
    public List<Medicamento> findAll(){
        return repository.findAll();
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository.findById(id).map(
                medicamento -> {
                    repository.delete(medicamento);
                    return Void.TYPE;
                }
        ).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicamento não encontrado"));
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Medicamento medicamentoAtualizado){
        repository.findById(id).map(receituarioDesatualizada ->{
            medicamentoAtualizado.setId(receituarioDesatualizada.getId());
            return repository.save( medicamentoAtualizado);
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receituário não Encontrado"));
    }
}
