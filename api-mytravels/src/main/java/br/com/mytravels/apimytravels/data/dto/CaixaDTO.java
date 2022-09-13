package br.com.mytravels.apimytravels.data.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaixaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	
	private Long veiculo;
	
	private Double saldo;

}
