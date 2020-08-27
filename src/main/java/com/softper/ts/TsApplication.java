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
            web.ignoring().mvcMatchers(HttpMethod.OPTIONS, "/**");
            // ignore swagger
            web.ignoring().mvcMatchers( "/**","/favicon.icon**","/index.html/**", "/configuration/**", "/swagger-resources/**", "/ts-api-docs/**");
        }


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers("/**").permitAll()
                    .antMatchers("/favicon.icon**").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/authentication/**").permitAll()
                    .anyRequest().authenticated();
        }
    }


}
