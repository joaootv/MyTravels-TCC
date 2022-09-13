package br.com.mytravels.apimytravels.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mytravels.apimytravels.data.dto.CaixaDTO;
import br.com.mytravels.apimytravels.data.model.CaixaVeiculo;
import br.com.mytravels.apimytravels.data.model.Veiculos;
import br.com.mytravels.apimytravels.repository.CaixaVeiculoRepository;
import br.com.mytravels.apimytravels.repository.VeiculosRepository;

@Service
public class CaixaService {
	
	@Autowired
	private CaixaVeiculoRepository caixaRepository;
	
	@Autowired
	private VeiculosRepository veiculosRepository;

	public CaixaVeiculo findByVeiculo(Long id) {
		Optional<Veiculos> veiculo = this.veiculosRepository.findById(id);
		return caixaRepository.findByVeiculo(veiculo);
	}
	
	public CaixaVeiculo create(CaixaDTO caixaVeiculo) {
		CaixaVeiculo caixa = new CaixaVeiculo();
		Veiculos veiculo = new Veiculos(caixaVeiculo.getVeiculo());
		caixa.setVeiculo(veiculo);
		caixa.setSaldo(caixaVeiculo.getSaldo());
		return caixaRepository.save(caixa);
	}
	
	public CaixaVeiculo updateSaldo(CaixaDTO caixa) {
		CaixaVeiculo caixaVeiculo = caixaRepository.findById(caixa.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		caixaVeiculo.setSaldo(caixa.getSaldo());
		caixaRepository.save(caixaVeiculo);
		return caixaVeiculo;
	}

}
