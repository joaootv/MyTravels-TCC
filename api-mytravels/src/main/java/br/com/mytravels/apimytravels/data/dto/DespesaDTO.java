package br.com.mytravels.apimytravels.data.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DespesaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	
	private Long viagemId;
	
	private String descricao;

	private String local;
	
	private Double preco;
	
	private LocalDateTime data;
	
	private Integer tipoDespesa;
	
	private Integer estadoPagamento;

}
