package br.com.imsodontologia.imsodontologia.rest;

import br.com.imsodontologia.imsodontologia.model.Medicamento;
import br.com.imsodontologia.imsodontologia.model.Paciente;
import br.com.imsodontologia.imsodontologia.model.Receituario;
import br.com.imsodontologia.imsodontologia.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


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
    public List<Medicamento> salvar (@RequestBody @Valid List<Medicamento> medicamento){
         List<Medicamento>  salvados = new ArrayList<>();
         for(int i = 0 ; i < medicamento.size(); i++){
             if(medicamento.get(i).getMedicamento() != null ){
                 repository.save(medicamento.get(i));
                 salvados.add(medicamento.get(i));
             }
         }
        return salvados;
    }

    @GetMapping("{id}")
    public Medicamento findById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Medicamento não encontrado"
                )
        );
    }

    @PostMapping("/buscar-string")
    @ResponseStatus(HttpStatus.OK)
    public List<Medicamento> getByNome(@RequestBody String getByNome){
        return this.repository.buscar(getByNome);
    }

    @GetMapping("recbyid/{id}")
    public List<Medicamento> findAllByReceituario(@PathVariable  Integer id){
        return repository.findAllByReceituario(id);
    }


    @GetMapping
    public List<Medicamento> findAll(){
        return repository.findAll();
    }

   // @GetMapping("/verificaesalva")
    //@ResponseStatus(HttpStatus.OK)
    //public Medicamento verifica(@RequestBody Medicamento  verifica){
    //        return this.repository.getOne(verifica.getId());
   // }

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

}
