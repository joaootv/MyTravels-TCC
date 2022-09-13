package br.com.mytravels.apimytravels.data.dto;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoDTO extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	
	private String placa;
	
	private Integer renavam;
	
	private Long tipo;
	
	private String marca;

	private	String modelo;
	
	private Integer anoFabricacao;
	
	private Integer anoModelo;
	
	private Integer exercicioDoc;
	
	private String cor;
	
	private String chassi;
	
	private Long cidadeEmplacado;
	
	private Float kilometragem;
	
	private Long motorista;

}
