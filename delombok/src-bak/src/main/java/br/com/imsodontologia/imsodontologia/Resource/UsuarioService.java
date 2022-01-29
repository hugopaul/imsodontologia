package br.com.imsodontologia.imsodontologia.Resource;

import br.com.imsodontologia.imsodontologia.model.Usuario;
import br.com.imsodontologia.imsodontologia.repository.UsuarioRepository;
import br.com.imsodontologia.imsodontologia.exception.UsuarioCadastradoException;
import br.com.imsodontologia.imsodontologia.exception.UsuarioFindByIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Login não encontrado.")
        );
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getPerfil())
                .build();
    }

    public Usuario salvar (Usuario usuario){
        boolean exists = repository.existsByUsername(usuario.getUsername());
        if (exists == true){
                return null;
        }else {
            return repository.save(usuario);
        }
    }

    public List<Usuario> findAll() {
        return this.repository.findAll();
    }


    public Optional<Usuario> findById(Integer id) {
        if(this.repository.findById(id) == null){
            new UsuarioFindByIdException();
        }
        return this.repository.findById(id);
    }
}
