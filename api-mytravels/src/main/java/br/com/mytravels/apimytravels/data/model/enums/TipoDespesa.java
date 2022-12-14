package br.com.mytravels.apimytravels.data.model.enums;

public enum TipoDespesa {
	DIVERSA(1, "DIVERSA"),
	MANUTENCAO(2, "MANUTENCAO");

	private int cod;
	private String descricao;
	
	private TipoDespesa(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoDespesa toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (TipoDespesa x : TipoDespesa.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
