package br.com.mytravels.apimytravels.data.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViagensNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
