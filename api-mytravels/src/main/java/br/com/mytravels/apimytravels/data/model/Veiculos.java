package br.com.mytravels.apimytravels.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="veiculos")
public class Veiculos extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonProperty("id")
	private Long key;
	
	private String placa;
	
	private Integer renavam;
	
	@ManyToOne
	@JoinColumn(name="tipo")
	private TipoVeiculo tipo;
	
	private String marca;

	private	String modelo;
	
	@Column(name = "ano_fabricaco")
	private Integer anoFabricacao;
	
	@Column(name = "ano_modelo")
	private Integer anoModelo;
	
	@Column(name = "exercicio_doc")
	private Integer exercicioDoc;
	
	private String cor;
	
	private String chassi;
	
	@ManyToOne
	@JoinColumn(name="cidade_emplacado")
	private Cidade cidadeEmplacado;
	
	private Float kilometragem;
	
	@OneToOne
	@JoinColumn(name="motorista")
	private Motorista motorista;
	
	@ManyToOne
	@JoinColumn(name="transportadora_id")
	private Transportadora transportadora;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "VEICULOS_CONJUNTOS", 
			joinColumns = @JoinColumn(name = "veiculo_id"), 
			inverseJoinColumns = @JoinColumn(name = "conjunto_id")
	)
	private List<Veiculos> conjuntos = new ArrayList<>();
	
	public Veiculos() {
	}

	public Veiculos(Long id, String placa, Integer renavam, TipoVeiculo tipo, String marca, String modelo,
			Integer anoFabricacao, Integer anoModelo, Integer exercicioDoc, String cor, String chassi,
			Cidade cidadeEmplacado, Float kilometragem, Motorista motorista, Transportadora transportadora) {
		super();
		this.key = id;
		this.placa = placa;
		this.renavam = renavam;
		this.tipo = tipo;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
		this.anoModelo = anoModelo;
		this.exercicioDoc = exercicioDoc;
		this.cor = cor;
		this.chassi = chassi;
		this.cidadeEmplacado = cidadeEmplacado;
		this.kilometragem = kilometragem;
		this.motorista = motorista;
		this.transportadora = transportadora;
	}
	
	public Veiculos(Long implemento) {
		this.key = implemento;
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
		Veiculos other = (Veiculos) obj;
		return Objects.equals(key, other.key);
	}

}
