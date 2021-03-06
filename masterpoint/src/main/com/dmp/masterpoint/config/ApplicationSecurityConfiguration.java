package com.dmp.masterpoint.config;

import com.dmp.masterpoint.areas.users.services.UserServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserServiceImpl userDetailsService;

    public ApplicationSecurityConfiguration(UserServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/login", "/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .and()
                .exceptionHandling().accessDeniedPage("/unauthorized")
                .and()
                .rememberMe()
                .rememberMeParameter("remember")
                .rememberMeCookieName("rememberMeCookie")
                .key("48433e39-e610-4a2c-926c-f86d46f5360a")
                .tokenValiditySeconds(100)
                .userDetailsService(userDetailsService)
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }
}
