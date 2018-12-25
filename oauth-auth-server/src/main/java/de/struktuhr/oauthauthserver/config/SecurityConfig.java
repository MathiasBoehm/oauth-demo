package de.struktuhr.oauthauthserver.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

import java.util.Collection;
import java.util.HashSet;

@Configurable
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${ldap.userSearchFilter}")
    private String userSearchFilter;

    @Value("${ldap.groupSearchFilter}")
    private String groupSearchFilter;

    @Value("${ldap.url}")
    private String url;

    @Value("${ldap.managerDN}")
    private String managerDN;

    @Value("${ldap.managerPassword}")
    private String managerPassword;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().disable() // disable form authentication
                .anonymous().disable() // disable anonymous user
                .authorizeRequests().anyRequest().denyAll(); // denying all access
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() // creating user in memory
                    .withUser("user")
                    .password("password")
                    .roles("USER")
                .and()
                    .withUser("admin")
                    .password("password")
                    .authorities("ROLE_ADMIN");
    }


//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .ldapAuthentication()
//                .userSearchFilter(userSearchFilter)
//                .groupSearchFilter(groupSearchFilter)
//                .ldapAuthoritiesPopulator(ldapAuthoritiesPopulator())
//                .contextSource()
//                .url(url)
//                .managerDn(managerDN)
//                .managerPassword(managerPassword);
//
//    }
//
//    @Bean
//    public LdapAuthoritiesPopulator ldapAuthoritiesPopulator() {
//        return new LdapAuthoritiesPopulator() {
//            @Override
//            public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations dirContextOperations, String s) {
//                final Collection<GrantedAuthority> authorities = new HashSet<>();
//
//                // every authenticated user has Role USER
//                authorities.add(new SimpleGrantedAuthority("USER"));
//
//                // jfryer is admin
//                final String uid = dirContextOperations.getStringAttribute("uid");
//                if ("jfryer".equalsIgnoreCase(uid)) {
//                    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//                }
//
//                System.out.println(s + " has roles " + authorities);
//
//                return authorities;
//            }
//        };
//    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // provides the default AuthenticationManager as a Bean
        return super.authenticationManagerBean();
    }
}
