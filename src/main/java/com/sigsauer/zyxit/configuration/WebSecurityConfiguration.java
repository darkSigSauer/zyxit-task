package com.sigsauer.zyxit.configuration;

import com.sigsauer.zyxit.domain.User;
import com.sigsauer.zyxit.repository.UserRepository;
import com.sigsauer.zyxit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .   and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                    .and()
                .logout()
                .permitAll();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //hard :)
        if(userRepository.findByUsername("admin") == null) {
            com.sigsauer.zyxit.domain.User userb = new User();
            userb.setUsername("admin");
            userb.setPassword("admin");
            userRepository.save(userb);
        }

        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
