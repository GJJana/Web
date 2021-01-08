package mk.ukim.finki.wp.lab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public  WebSecurityConfig(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       //third party
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/assets","/register","/courses","/listStudents","/studentsInCourse").permitAll()
                .antMatchers("/addStudent","/add-course","/courses/add").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/listCourses",true)
                .and()
                .logout()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");


    }
    //koj provider kje go koristime
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("jana.gelevska")
                    .password(passwordEncoder.encode("pass"))
                    .authorities("ROLE_USER")
                    .and()
                    .withUser("admin")
                    .password(passwordEncoder.encode("pass"))
                    .authorities("ROLE_ADMIN");

    }
}
