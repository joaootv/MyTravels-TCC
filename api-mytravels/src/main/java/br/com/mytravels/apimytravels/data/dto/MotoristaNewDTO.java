package br.com.mytravels.apimytravels.data.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotoristaNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	
	private Long pessoaId;
	
	private Integer cnhRegistro;
	
	private Integer cnhEspelho;
	
	private LocalDateTime validade;
	
	private Integer numSeguranca;
	
	private String renach;
	
	private String categoria;
	
	private Long cidadeEmissao;
	
	private Double taxaComissao;
	
	private Long transportadoraId;

}
