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

    /*Setting SQLServer database
    * https://www.youtube.com/watch?v=LVlxBEB1EF4 
    *  Commands:
    *   mvn install:install-file -Dfile=C:/sqljdbc_6.0/enu/jre7/sqljdbc41.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0-
            Dpackaging=packaging.jar
    *
    *   mvn install:install-file -Dfile=C:/sqljdbc_6.0/enu/jre7/sqljdbc41.jar -DpomFile=C:/Users/51931/IdeaProjects/ts-opensource-be/pom.xml
    *
    * */

    /*
    * Desplegar Springboot en heroku
    * 1.- Abrir heroku login
    * 2.- heroku create <APP NAME>
    * 3.- inicializar git
    * 4.- git remote add heroku <HEROKU APP REPOSITORY>
    * 5.- heroku config:set DATABASE_URL='mysql://ueu35yi8gtvogkeu:LNOJKsZvSOqNhAcfJA4t@bqhh75ibqyxemcgdz60f-mysql.services.clever-cloud.com:3306/bqhh75ibqyxemcgdz60f'
    * 6.- git add .
    * 7.- git commit -m "n version"
    * 8.- git push heroku master
    *
    *a
    * Se utilizo servicio de clevercloud para Base de datos MySql
    * */

    /**
     * run springboot: ./mvnw spring-boot:run
     */
    


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
