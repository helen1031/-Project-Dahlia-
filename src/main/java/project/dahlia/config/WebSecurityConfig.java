package project.dahlia.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.web.filter.CorsFilter;
import project.dahlia.security.JwtAuthenticationFilter;
import project.dahlia.security.OAuthSuccessHandler;
import project.dahlia.security.OAuthUserServiceImpl;
import project.dahlia.security.RedirectUrlCookieFilter;

@EnableWebSecurity
@Slf4j
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private OAuthUserServiceImpl oAuthUserService;

    @Autowired
    private OAuthSuccessHandler oAuthSuccessHandler;

    @Autowired
    private RedirectUrlCookieFilter redirectUrlFilter;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                    .disable()
                .httpBasic()
                    .disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .antMatchers("/", "/auth/**", "/oauth2/**").permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                .oauth2Login()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/**")
                .and()
                .authorizationEndpoint()
                .baseUri("/auth/authorize")
                .and()
                .userInfoEndpoint()
                .userService(oAuthUserService)
                .and()
                .successHandler(oAuthSuccessHandler)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint());

        //CorsFilter 실행 후 jwtAuthenticationFilter 매번 실행
        http.addFilterAfter(
                jwtAuthenticationFilter,
                CorsFilter.class
        );

        http.addFilterBefore(
                redirectUrlFilter,
                OAuth2AuthorizationRequestRedirectFilter.class
        );

        return http.build();
    }

}
