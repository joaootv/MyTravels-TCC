package br.com.mytravels.apimytravels.data.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

import br.com.mytravels.apimytravels.data.model.Transportadora;
import br.com.mytravels.apimytravels.services.validation.ClienteUpdate;

@ClienteUpdate
public class TransportadoraDTO extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	private Set<String> telefones = new HashSet<>();
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String rntrc;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private LocalDateTime vencimentoRNTRC;
	
	public TransportadoraDTO() {
	}

	public TransportadoraDTO(Transportadora obj) {
		key = obj.getKey();
		nome = obj.getNome();
		email = obj.getEmail();
		rntrc = obj.getRNTRC();
		vencimentoRNTRC = obj.getVencimentoRNTRC();
		telefones = obj.getTelefones();
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long id) {
		this.key = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRntrc() {
		return rntrc;
	}

	public void setRntrc(String rntrc) {
		this.rntrc = rntrc;
	}

	public LocalDateTime getVencimentoRNTRC() {
		return vencimentoRNTRC;
	}

	public void setVencimentoRNTRC(LocalDateTime vencimentoRNTRC) {
		this.vencimentoRNTRC = vencimentoRNTRC;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	
}
