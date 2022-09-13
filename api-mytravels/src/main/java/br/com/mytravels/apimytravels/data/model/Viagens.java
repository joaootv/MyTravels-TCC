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

import br.com.mytravels.apimytravels.data.model.enums.EstadoViagem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="viagens")
public class Viagens extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonProperty("id")
	private Long key;
	
	@ManyToOne
	@JoinColumn(name="veiculo_id")
	private Veiculos veiculo;
	
	@ManyToOne
	@JoinColumn(name="motorista_id")
	private Motorista motorista;
	
	@ManyToOne
	@JoinColumn(name="transportadora_id")
	private Transportadora transportadora;
	
	@Column(name = "data_inicial")
	private LocalDateTime dataInical;
	
	@Column(name = "data_final")
	private LocalDateTime dataFinal;
	
	@Column(name = "km_inicial")
	private Double kmIncial;
	
	@Column(name = "km_final")
	private Double kmFinal;
	
	@ManyToOne
	@JoinColumn(name="origem")
	private Cidade origem;
	
	@ManyToOne
	@JoinColumn(name="destino")
	private Cidade destino;
	
	@Column(name = "peso_tonelada")
	private Double pesoTonelada;
	
	@Column(name = "valor_frete")
	private Double valorFrete;
	
	@Column(name = "saldo_inicial")
	private Double saldoInicial;
	
	@Column(name = "saldo_final")
	private Double saldoFinal;
	
	@Column(name = "estado_viagem", nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoViagem estadoViagem;
	
	public Viagens() {
	}

	public Viagens(Long key, Veiculos veiculoId, Motorista motoristaId, Transportadora transportadora, LocalDateTime dataInical,
			LocalDateTime dataFinal, Double kmIncial, Double kmFinal, Cidade origem, Cidade destino,
			Double pesoTonelada, Double valorFrete, Double saldoInicial, Double saldoFinal, EstadoViagem estadoViagem) {
		super();
		this.key = key;
		this.veiculo = veiculoId;
		this.motorista = motoristaId;
		this.transportadora = transportadora;
		this.dataInical = dataInical;
		this.dataFinal = dataFinal;
		this.kmIncial = kmIncial;
		this.kmFinal = kmFinal;
		this.origem = origem;
		this.destino = destino;
		this.pesoTonelada = pesoTonelada;
		this.valorFrete = valorFrete;
		this.saldoInicial = saldoInicial;
		this.saldoFinal = saldoFinal;
		this.estadoViagem = estadoViagem;
	}
	
	public Viagens(Long viagemId) {
		this.key = viagemId;
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
		Viagens other = (Viagens) obj;
		return Objects.equals(key, other.key);
	}
	
	
}
