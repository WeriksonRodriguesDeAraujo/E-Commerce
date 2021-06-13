package com.serratec.trabalhofinal.security;

import java.util.Date;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.serratec.trabalhofinal.model.Cliente;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTService {

	private static final String chavePrivadaJWT = "ZnVsbHRocm90dGxl";
	public String gerarToken(Authentication authentication) {
		
		int tempoExpiracao = 86400000;
		Date dataExpiracao = new Date(new Date().getTime() + tempoExpiracao);
		
		Cliente user = (Cliente) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject(user.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS512, chavePrivadaJWT)
				.compact();
	}
	
	public Optional<Integer> obterIdDoUsuario(String token) {
		try {
			Claims claims = parse(token).getBody();
			return Optional.ofNullable(Integer.parseInt(claims.getSubject()));
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	
	private Jws<Claims> parse (String token){
		return Jwts.parser().setSigningKey(chavePrivadaJWT).parseClaimsJws(token);
	}
	
}
