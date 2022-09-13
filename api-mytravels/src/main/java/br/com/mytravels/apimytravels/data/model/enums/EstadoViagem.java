package br.com.mytravels.apimytravels.data.model.enums;

public enum EstadoViagem {
	
	ABERTA(1, "ABERTA"),
	FINALIZADA(2, "FINALIZADA"),
	CANCELADA(3, "CANCELADA");
	
	private int cod;
	private String descricao;
	
	private EstadoViagem(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoViagem toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (EstadoViagem x : EstadoViagem.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
