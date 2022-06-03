package sales.common.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sales.domain.service.implementation.UserServiceImpl;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserServiceImpl userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // Use a configuration to secure back-end and front-end communication. This api does not have a web client, thus will not be necessary.
                .authorizeRequests()
                .antMatchers("/api/clients/**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/products/**")
                    .hasRole("ADMIN")
                .antMatchers("/api/orders/**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/users/**")
                    .permitAll()
                .anyRequest().authenticated()
                .and()
                    .httpBasic();
    }
}
