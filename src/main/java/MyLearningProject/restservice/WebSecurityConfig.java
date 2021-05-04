package MyLearningProject.restservice;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*http.authorizeRequests()
                .antMatchers( "/newUser").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successForwardUrl("/chat")
                .defaultSuccessUrl("/chat")
                .and()
                .logout()
                .permitAll()
                .and().cors().and().csrf().disable();*/

        /*http.authorizeRequests()
                .antMatchers( "/newUser").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/loginPage").permitAll()
                .defaultSuccessUrl("/chat", true);*/

        http.authorizeRequests()
                .antMatchers( "/newUser", "/loginPage", "/saveCustomer").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login").permitAll();


/* loginPage() – the custom login page
loginProcessingUrl() – the URL to submit the username and password to
defaultSuccessUrl() – the landing page after a successful login
failureUrl() – the landing page after an unsuccessful login
logoutUrl() – the custom logout
*/


    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }






}