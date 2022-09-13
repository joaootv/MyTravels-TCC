package br.com.mytravels.apimytravels.data.model;

import java.io.Serializable;
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
import br.com.mytravels.apimytravels.data.model.enums.TipoAbastecimento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="abastecimento")
public class Abastecimento extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonProperty("id")
	private Long key;
	
	@ManyToOne
	@JoinColumn(name="viagem_id")
	private Viagens viagem;
	
	@Column(name="local")
	private String local;
	
	@Column(name = "tipo_abastecimento", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoAbastecimento tipoAbastecimento;
	
	@Column(name="litros")
	private Double litros;
	
	@Column(name="preco")
	private Double preco;
	
	@Column(name="km_veiculo")
	private Double kmVeiculo;
	
	@Column(name = "estado_pagamento", nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoPagamento estadoPagamento;
	
	public Abastecimento() {
	}

	public Abastecimento(Long key, Viagens viagemId, String local, TipoAbastecimento tipoAbastecimento, Double litros,
			Double preco, Double kmVeiculo, EstadoPagamento estadoPagamento) {
		super();
		this.key = key;
		this.viagem = viagemId;
		this.local = local;
		this.tipoAbastecimento = tipoAbastecimento;
		this.litros = litros;
		this.preco = preco;
		this.kmVeiculo = kmVeiculo;
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
		Abastecimento other = (Abastecimento) obj;
		return Objects.equals(key, other.key);
	}

}
