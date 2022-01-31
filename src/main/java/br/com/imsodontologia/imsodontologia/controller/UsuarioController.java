package br.com.imsodontologia.imsodontologia.controller;

import br.com.imsodontologia.imsodontologia.Resource.UsuarioService;
import br.com.imsodontologia.imsodontologia.model.Usuario;
import br.com.imsodontologia.imsodontologia.exception.UsuarioFindByIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody @Valid Usuario usuario){
        Usuario teste = usuarioService.salvar(usuario);
        if( teste == null){
            return ResponseEntity.badRequest().body("Já existe registro com este Usuário");
        }else {
            return ResponseEntity.ok().body("Usuário cadastrado com sucesso!");
        }
    }

    @GetMapping
    public List<Usuario> getAll(){
        return this.usuarioService.findAll();
    }

    @GetMapping("/perfil/{usuario}")
    public String getOne(@PathVariable String usuario){
        if(this.usuarioService.findPerfilByUsusario(usuario) == null) {
            return null;
        }
        String perfil = this.usuarioService.findPerfilByUsusario(usuario);
        return this.usuarioService.findPerfilByUsusario(usuario);
    }

    @GetMapping("/{id}")
    public Optional<Usuario> getOne(@PathVariable Integer id){
        try {
             return this.usuarioService.findById(id);
        }catch (UsuarioFindByIdException e){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

}
