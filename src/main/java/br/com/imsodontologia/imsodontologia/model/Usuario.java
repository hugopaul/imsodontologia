package br.com.imsodontologia.imsodontologia.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(schema = "ims", name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "o campo Nome é obrigatório")
    private String nome;

    @Column(unique = true)
    @NotEmpty(message = "O campo Usuário é obirgatório")
    private String username;

    @Column
    @NotEmpty(message = "O campo Senha é obirgatório")
    private String password;

    @Column(nullable = false)
    @NotEmpty(message = "O campo Perfil é obrigatório!")
    private String perfil;

    @Column
    private String registro;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerfil() {
        return perfil;
    }

    public String getRegistro() {
        return registro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Usuario(Integer id, String nome, String username, String password, String perfil, String registro) {
        this.id = id;
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.perfil = perfil;
        this.registro = registro;
    }

    public Usuario() {
    }
}
