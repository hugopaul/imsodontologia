package br.com.imsodontologia.imsodontologia.controller;

import br.com.imsodontologia.imsodontologia.model.Medicamento;
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
    @GetMapping("recbyid/{id}")
    public List<Medicamento> findAllByReceituario(@PathVariable  Integer id){
        return repository.findAllByReceituario(id);
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

}
