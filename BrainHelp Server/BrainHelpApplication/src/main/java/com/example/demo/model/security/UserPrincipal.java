package com.example.demo.model.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.demo.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_ROLE = "ROLE_USER";

    private String email;
    @JsonIgnore
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UserPrincipal(String email, String senha, 
            Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.senha = senha;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Usuario usuario) {
        List<GrantedAuthority> authorities = Arrays.asList(
            new SimpleGrantedAuthority(usuario.getRole().orElse(DEFAULT_ROLE))
        );

        return new UserPrincipal(
        		usuario.getEmail(),
        		usuario.getSenha(),
            authorities
        );
    }

    
    @Override
    public String getUsername(){
        return email;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}