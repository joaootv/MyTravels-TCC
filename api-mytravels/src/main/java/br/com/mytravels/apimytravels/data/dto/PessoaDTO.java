package br.com.mytravels.apimytravels.data.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

import br.com.mytravels.apimytravels.data.model.Cidade;
import br.com.mytravels.apimytravels.data.model.Estado;
import br.com.mytravels.apimytravels.data.model.Pessoa;

public class PessoaDTO  extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String cpf;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private LocalDateTime dtNascimento;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private Cidade naturalidade;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String rg;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String orgaoExpedidor;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private Estado uf;
	
	private String pai;
	
	private String mae;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	private Set<String> telefones = new HashSet<>();
	
	public PessoaDTO() {
	}

	public PessoaDTO(Pessoa obj) {
		key = obj.getKey();
		nome = obj.getNome();
		cpf = obj.getCpf();
		dtNascimento = obj.getDtNascimento();
		naturalidade = obj.getNaturalidade();
		rg = obj.getRg();
		orgaoExpedidor = obj.getOrgaoExpedidor();
		uf = obj.getUf();
		pai = obj.getPai();
		mae = obj.getMae();
		email = obj.getEmail();
		telefones = obj.getTelefones();
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDateTime getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDateTime dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Cidade getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(Cidade naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}

	public Estado getUf() {
		return uf;
	}

	public void setUf(Estado uf) {
		this.uf = uf;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(key);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaDTO other = (PessoaDTO) obj;
		return Objects.equals(key, other.key);
	}

}
