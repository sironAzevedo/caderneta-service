package br.com.caderneta.service.configuracao.seguranca;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.caderneta.service.common.enums.Perfil;
import br.com.caderneta.service.models.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private Long codigo;
	
	@Getter
	@Setter
	private String nome; 
	
	@Getter
	@Setter
	private String email;
	
	@Getter
	@Setter
	@JsonIgnore
	private String senha;
		
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public static User build(UsuarioEntity user) {
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
		        new SimpleGrantedAuthority(role.getName().name())
		).collect(Collectors.toList());
		
		return new User(
		        user.getCodigo(),
		        user.getNome(),
		        user.getEmail(),
		        user.getSenha(),
		        authorities
		);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 return authorities;
	}
	
	@Override
	public String getUsername() {
		return this.email;
	}
	
	@Override
	public String getPassword() {
		return this.senha;
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

	public boolean hasRole(Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.name()));
	}
}
