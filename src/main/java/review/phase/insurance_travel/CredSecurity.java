package review.phase.insurance_travel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CredSecurity {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails userDetails= User.withDefaultPasswordEncoder().username("pranay").password("pes2025").build();
        List<UserDetails> memoryUser = Stream.of(userDetails).collect(Collectors.toList());
        return new InMemoryUserDetailsManager(memoryUser);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(customizer->customizer.disable());
        httpSecurity.authorizeHttpRequests(request->request.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll());
        httpSecurity.authorizeHttpRequests(request->request.anyRequest().authenticated());
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
