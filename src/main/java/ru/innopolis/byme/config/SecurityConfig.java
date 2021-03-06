package ru.innopolis.byme.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.innopolis.byme.service.MainService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    // TODO: 2019-02-21 реализовать данные запросы в DAO
    private static final String USER_BY_USER = "SELECT login, password, true as enabled FROM public.user WHERE login = ?";
    private static final String USER_BY_ROLE = "SELECT login, 'USER' FROM public.user WHERE login = ?";

    private final MainService service;

    public SecurityConfig(MainService service) {
        this.service = service;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        LOGGER.info("попытка идентифицировать пользователя");
        auth
                .jdbcAuthentication()
                .dataSource(service.getDataSource())
                .usersByUsernameQuery(USER_BY_USER)
                .authoritiesByUsernameQuery(USER_BY_ROLE)
                .passwordEncoder(service.getEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.info("начали проверку безопасности");
        http
                .csrf().disable()
            .authorizeRequests()
                .antMatchers("/", "/registration", "/about", "/ad/view/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("login").passwordParameter("password")
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
            .rememberMe()
                .key("ByMeKey");
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/static/**");
    }
}
