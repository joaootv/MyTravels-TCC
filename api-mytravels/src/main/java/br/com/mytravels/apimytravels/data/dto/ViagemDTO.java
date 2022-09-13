package br.com.mytravels.apimytravels.data.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViagemDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	
	private Long veiculoId;
	
	private Long motoristaId;
	
	private Long transportadoraId;
	
	private LocalDateTime dataInical;
	
	private LocalDateTime dataFinal;
	
	private Double kmIncial;
	
	private Double kmFinal;
	
	private Long origem;
	
	private Long destino;
	
	private Double pesoTonelada;
	
	private Double valorFrete;
	
	private Double saldoInicial;
	
	private Double saldoFinal;

	private Integer estadoViagem;

}
