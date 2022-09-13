package br.com.mytravels.apimytravels.data.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.mytravels.apimytravels.data.model.enums.TipoPessoa;

@Entity
@Table(name="transportadora")
@JsonPropertyOrder({"id", "nome", "tipoPessoa", "cpfOuCnpj", "email", "telefones", "enderecos"})
public class Transportadora extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonProperty("id")
	private Long key;
	
	@Column(name = "nome", nullable = false, length = 255)
	private String nome;
	
	@Column(name = "tipo", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa;
	
	@Column(name = "cpf_cnpj", nullable = false, length = 19)
	private String cpfOuCnpj;
	
	@Column(unique=true)
	private String email;
	
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	@Column(unique=true)
	private String RNTRC;
	
	@Column(name = "vencimento_rntrc")
	private LocalDateTime vencimentoRNTRC;
	
	@OneToMany(mappedBy="transportadora", cascade=CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();
	
	public Transportadora() {
	}
	
	public Transportadora(Long key, String nome, TipoPessoa tipoPessoa, String cpfOuCnpj, String email, String rNTRC, LocalDateTime vencimentoRNTRC) {
		super();
		this.key = key;
		this.nome = nome;
		this.tipoPessoa = tipoPessoa;
		this.cpfOuCnpj = cpfOuCnpj;
		this.email = email;
		RNTRC = rNTRC;
		this.vencimentoRNTRC = vencimentoRNTRC;
	}

	public Transportadora(Long transportadora) {
		this.key = transportadora;
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

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
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

	public String getRNTRC() {
		return RNTRC;
	}

	public void setRNTRC(String rNTRC) {
		RNTRC = rNTRC;
	}

	public LocalDateTime getVencimentoRNTRC() {
		return vencimentoRNTRC;
	}

	public void setVencimentoRNTRC(LocalDateTime vencimentoRNTRC) {
		this.vencimentoRNTRC = vencimentoRNTRC;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
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
		Transportadora other = (Transportadora) obj;
		return Objects.equals(key, other.key);
	}
}
