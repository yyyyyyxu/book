package com.yanxu.book.config;

import com.yanxu.book.handler.LoginSucessHandler;
import com.yanxu.book.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserLoginService userLoginService;


    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userLoginService).passwordEncoder(password());
    }

    @Bean
    PasswordEncoder password(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/book/getBookList").successHandler(new LoginSucessHandler()).permitAll()
                .and().logout()
                .logoutUrl("/book/logout")
                .logoutSuccessUrl("/user/login").permitAll()
                .and().authorizeRequests()
                .antMatchers("/book/getBookList").hasAnyRole("manager","user")
                .antMatchers("/book/insertBook","book/upload").hasAnyRole("manager")
                .anyRequest().permitAll()
                .and().rememberMe().tokenRepository(presistentTokenRepository())
                .tokenValiditySeconds(60 * 60)
                .userDetailsService(userLoginService)
                .and().csrf().disable();
    }
//
    @Bean
    public PersistentTokenRepository presistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }
}

