package br.com.mytravels.apimytravels.data.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="motorista")
public class Motorista extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonProperty("id")
	private Long key;
	
	@ManyToOne
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;
	
	@Column(name = "cnh_registro")
	private Integer cnhRegistro;
	
	@Column(name = "cnh_espelho")
	private Integer cnhEspelho;
	
	@Column(name = "validade")
	private LocalDateTime validade;
	
	@Column(name = "num_seguranca")
	private Integer numSeguranca;
	
	@Column(name = "renach")
	private String renach;
	
	@Column(name = "categoria",length = 2)
	private String categoria;
	
	@ManyToOne
	@JoinColumn(name="cidade_emissao")
	private Cidade cidadeEmissao;
	
	@Column(name = "taxa_comissao")
	private Double taxaComissao;
	
	@ManyToOne
	@JoinColumn(name="transportadora_id")
	private Transportadora transportadora;
	
	public Motorista() {
	}
	
	public Motorista(Long key, Pessoa pessoa, Integer cnhRegistro, Integer cnhEspelho, LocalDateTime validade,
			Integer numSeguranca, String renach, String categoria, Cidade cidadeEmissao, Double taxaComissao,
			Transportadora transportadora) {
		super();
		this.key = key;
		this.pessoa = pessoa;
		this.cnhRegistro = cnhRegistro;
		this.cnhEspelho = cnhEspelho;
		this.validade = validade;
		this.numSeguranca = numSeguranca;
		this.renach = renach;
		this.categoria = categoria;
		this.cidadeEmissao = cidadeEmissao;
		this.taxaComissao = taxaComissao;
		this.transportadora = transportadora;
	}
	
	public Motorista(Long motorista) {
		this.key = motorista;
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
		Motorista other = (Motorista) obj;
		return Objects.equals(key, other.key);
	}

}
