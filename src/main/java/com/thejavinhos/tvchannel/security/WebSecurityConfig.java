package com.thejavinhos.tvchannel.security;


import com.thejavinhos.tvchannel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenServicee tokenService;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private UserRepository userRepository;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    //configuraçoes de autorizacao
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user").permitAll()//limitar os acessos por aqui
                .antMatchers(HttpMethod.POST, "/auth").permitAll()//limitar os acessos por aqui
                .antMatchers(HttpMethod.POST, "/actors").permitAll()//limitar os acessos por aqui
                .antMatchers(HttpMethod.POST, "/producer").permitAll()//limitar os acessos por aqui
                .antMatchers("/h2/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new  AutenticacaoViaTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
//                .and().logout().logoutRequestM    atcher(new AntPathRequestMatcher("/logout"));

        http.headers().frameOptions().disable();
    }


    //Configurações de autenticao ->  controle de acesso
    @Override
//    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
//                .passwordEncoder(new BCryptPasswordEncoder());
    }

    //requisicoes de recursos estaticos(js, css, imagens)
    @Override
    public void configure(WebSecurity web) throws Exception{
//        web.ignoring().antMatchers("/materialize/**", "/style/*");
    }
}
