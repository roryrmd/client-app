package mcc53.client.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()

                .antMatchers("/css/**","/js/**","/lib/**","/login_assets/**").permitAll()

                .antMatchers("/login*").permitAll()
//                .antMatchers("/dashboard").authenticated()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/dashboard")
                .failureForwardUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout");
    }
}
