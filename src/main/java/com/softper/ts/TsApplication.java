package com.softper.ts;

import com.softper.ts.config.JwtAuthEntryPoint;
import com.softper.ts.security.JWTAuthorizationFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableJpaAuditing
public class TsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TsApplication.class, args);
    }

    /*
    * Desplegar Springboot en heroku
    * 1.- Abrir heroku login
    * 2.- heroku create <APP NAME>
    * 3.- inicializar git
    * 4.- git remote add heroku <HEROKU APP REPOSITORY>
    * 5.- git add .
    * 6.- git commit -m "n verison"
    * 7.- git push heroku master
    * 8.- heroku config:set DATABASE_URL=' <ADDON URI> '
    *
    *
    * Se utilizo servicio de clevercloud para Base de datos MySql
    * */

    @Bean
    public ModelMapper modelMapper() {return new ModelMapper();}

    @Autowired

    private JwtAuthEntryPoint authEntryPoint;

    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Bean
        public CorsFilter corsFilter() {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true);
            config.addAllowedOrigin("*");
            config.addAllowedHeader("*");
            config.addAllowedMethod("OPTIONS");
            config.addAllowedMethod("GET");
            config.addAllowedMethod("POST");
            config.addAllowedMethod("PUT");
            config.addAllowedMethod("DELETE");
            source.registerCorsConfiguration("/**", config);
            return new CorsFilter(source);
        }




        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/index.html","/ts-api-docs","/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui**", "/webjars/**");
        }


        /*
        * Ya esta configurado en httsecurity http
        * solo falta la configuracion web de swagger
        * */

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            /*http
                    .csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/api/authentication/**").permitAll()
                    .anyRequest().authenticated();
             */
            http
                    .csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests().
                    anyRequest().permitAll();
        }
    }


}
