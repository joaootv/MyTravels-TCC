package br.com.mytravels.apimytravels.data.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculosNewDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String placa;
	
	private Integer renavam;
	
	private Long idTipo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String marca;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private	String modelo;
	
	private Integer anoFabricacao;

	private Integer anoModelo;
	
	private Integer exercicioDoc;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String cor;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String chassi;
	
	private Long cidadeEmplacado;
	
	private Float kilometragem;
	
	private Long motorista;
	
	private Long implemento1;
	
	private Long implemento2;
	
	private Long implemento3;
	
	private Long transportadora;
}
