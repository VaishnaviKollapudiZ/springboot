package com.zemoso.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {


        @Autowired
        @Qualifier("securityDataSource")
        private DataSource securityDataSource;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth.jdbcAuthentication().dataSource(securityDataSource);

        }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/students/showFormForAdd").hasRole("ADMIN")
                .antMatchers("/courses/showFormForAdd").hasRole("ADMIN")
                .antMatchers("/students/showFormForUpdate").hasAnyRole("FACULTY","ADMIN")
                .antMatchers("/students/save*").hasAnyRole("FACULTY", "ADMIN")
                .antMatchers("/students/delete").hasRole("ADMIN")
                .antMatchers("/students/**").hasAnyRole("ADMIN","FACULTY")
                .antMatchers("/resources/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");

    }

}