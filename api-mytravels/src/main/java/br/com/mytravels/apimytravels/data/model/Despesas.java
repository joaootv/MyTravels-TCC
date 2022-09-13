package br.com.mytravels.apimytravels.data.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.mytravels.apimytravels.data.model.enums.EstadoPagamento;
import br.com.mytravels.apimytravels.data.model.enums.TipoDespesa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="despesas")
public class Despesas extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonProperty("id")
	private Long key;
	
	@ManyToOne
	@JoinColumn(name="viagem_id")
	private Viagens viagem;

	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "local")
	private String local;
	
	@Column(name = "preco")
	private Double preco;
	
	@Column(name = "data")
	private LocalDateTime data;
	
	@Column(name = "tipo_despesa", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoDespesa tipoDespesa;
	
	@Column(name = "estado_pagamento", nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoPagamento estadoPagamento;
	
	public Despesas() {
	}
	
	public Despesas(Long key, Viagens viagemId, String descricao, String local, Double preco, LocalDateTime data,
			TipoDespesa tipoDespesa, EstadoPagamento estadoPagamento) {
		super();
		this.key = key;
		this.viagem = viagemId;
		this.descricao = descricao;
		this.local = local;
		this.preco = preco;
		this.data = data;
		this.tipoDespesa = tipoDespesa;
		this.estadoPagamento = estadoPagamento;
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
		Despesas other = (Despesas) obj;
		return Objects.equals(key, other.key);
	}
	
}
