package br.com.mytravels.apimytravels.data.model.enums;

public enum TipoAbastecimento {
	DIESEL(1, "DIESEL"),
	ARLA(2, "ARLA"),
	OUTRO(3, "OUTRO");
	
	private int cod;
	private String descricao;
	
	private TipoAbastecimento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoAbastecimento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (TipoAbastecimento x : TipoAbastecimento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
