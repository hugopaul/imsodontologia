package br.com.imsodontologia.imsodontologia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll();
                /*.authorizeRequests()
                .antMatchers("/usuarios/**", "/marcacoes/**").hasRole("ADMINISTRADOR")
                .antMatchers("/atendimentos/**",
                             "/atestados/**",
                             "/medicamentos/**",
                             "/prontuarios/**"
                             ,"/receituarios/**", "/marcacoes/**" ).hasAnyRole("DENTISTA", "ADMINISTADOR")
                .antMatchers("/financeiros/**", "/marcacoes/**").hasAnyRole("SECRETARIA", "ADMINISTADOR")
                .antMatchers("/atendimentos/**","/atestados/**","/financeiros/**","/medicamentos/**","/pacientes/**",
                        "/prontuarios/**","/receituarios/**", "/usuarios/**").authenticated()
                .anyRequest().denyAll();*/

    }
}