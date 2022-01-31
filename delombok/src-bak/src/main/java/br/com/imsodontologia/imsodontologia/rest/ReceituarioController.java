package br.com.imsodontologia.imsodontologia.controller;

import br.com.imsodontologia.imsodontologia.model.Medicamento;
import br.com.imsodontologia.imsodontologia.model.Receituario;
import br.com.imsodontologia.imsodontologia.repository.MedicamentoRepository;
import br.com.imsodontologia.imsodontologia.repository.ReceituarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/receituarios")
public class ReceituarioController {

    private final ReceituarioRepository repository;
    private final MedicamentoRepository medicamentoRepository;

    @Autowired
    public  ReceituarioController(ReceituarioRepository repository, MedicamentoRepository medicamentoRepository){
        this.repository = repository;
        this.medicamentoRepository = medicamentoRepository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Receituario salvar (@RequestBody @Valid Receituario receituario){
        List<Medicamento> comDados = new ArrayList<>();
        for(int i = 0 ; i < receituario.getMedicamento().size(); i++){
            if(receituario.getMedicamento().get(i).getMedicamento() != null ){
                comDados.add(receituario.getMedicamento().get(i));
            }
        }
        receituario.setMedicamento(comDados);

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
