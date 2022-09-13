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
@Table(name="pedagio")
public class Pedagio extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonProperty("id")
	private Long key;
	
	@ManyToOne
	@JoinColumn(name="viagem_id")
	private Viagens viagem;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="num_eixos")
	private Integer numEixos;
	
	@Column(name="valor")
	private Double valor;
	
	@Column(name="data_hora")
	private LocalDateTime dataHora;
	
	public Pedagio() {
	}

	public Pedagio(Long key, Viagens viagemId, String descricao, Integer numEixos, Double valor,
			LocalDateTime dataHora) {
		super();
		this.key = key;
		this.viagem = viagemId;
		this.descricao = descricao;
		this.numEixos = numEixos;
		this.valor = valor;
		this.dataHora = dataHora;
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
		Pedagio other = (Pedagio) obj;
		return Objects.equals(key, other.key);
	}

}
