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
@NoArgsConstructor
@Table(schema = "ims", name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotEmpty(message = "O campo Usuário é obirgatório")
    private String username;

    @Column
    @NotEmpty(message = "O campo Senha é obirgatório")
    private String password;

    @Column(nullable = false)
    @NotEmpty(message = "O campo Perfil é obrigatório!")
    private String perfil;

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

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Usuario(Integer id, String username, String password, String perfil) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.perfil = perfil;
    }

    public Usuario() {
    }
}
