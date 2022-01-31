package br.com.imsodontologia.imsodontologia.controller;

import br.com.imsodontologia.imsodontologia.model.Marcacao;
import br.com.imsodontologia.imsodontologia.repository.MarcacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("marcacoes")
public class MarcacaoController {

    private final MarcacaoRepository repository;

    @Autowired
    public MarcacaoController(MarcacaoRepository repository){
        this.repository = repository;
    }
}
