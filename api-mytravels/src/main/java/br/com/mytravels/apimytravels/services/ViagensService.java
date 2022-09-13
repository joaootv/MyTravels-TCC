package br.com.mytravels.apimytravels.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mytravels.apimytravels.data.dto.ViagemDTO;
import br.com.mytravels.apimytravels.data.dto.ViagensNewDTO;
import br.com.mytravels.apimytravels.data.model.CaixaVeiculo;
import br.com.mytravels.apimytravels.data.model.Cidade;
import br.com.mytravels.apimytravels.data.model.Motorista;
import br.com.mytravels.apimytravels.data.model.Transportadora;
import br.com.mytravels.apimytravels.data.model.Veiculos;
import br.com.mytravels.apimytravels.data.model.Viagens;
import br.com.mytravels.apimytravels.data.model.enums.EstadoViagem;
import br.com.mytravels.apimytravels.repository.ViagensRepository;

@Service
public class ViagensService {
	
	@Autowired
	private ViagensRepository viagensRepository;
	
	@Autowired
	private CaixaService caixaService;
	
	@Autowired
	private TransportadoraServices transportadoraServices;
	
	@Autowired
	private MotoristaService motoristaService;

	public List<Viagens> findAll() {
		return viagensRepository.findAll();
	}
	
	public List<Viagens> findByTransportadora(Long id) {
		Optional<Transportadora> transportadora = this.transportadoraServices.findById(id);
		return viagensRepository.findByTransportadora(transportadora);
	}
	
	public List<Viagens> findByMotorista(Long id) {
		Optional<Motorista> motorista = this.motoristaService.findById(id);
		return viagensRepository.findByMotorista(motorista);
	}

	public Optional<Viagens> findById(Long id) {
		return viagensRepository.findById(id);
	}
	
	@Transactional
	public Viagens insert(ViagensNewDTO obj) {
		Viagens viagem = new Viagens();
		Veiculos veiculo = new Veiculos(obj.getVeiculoId());
		Motorista motorista = new Motorista(obj.getMotoristaId());
		Transportadora transportadora = new Transportadora(obj.getTransportadoraId());
		Cidade origem = new Cidade(obj.getOrigem());
		Cidade destino = new Cidade(obj.getDestino());
		CaixaVeiculo caixa = caixaService.findByVeiculo(obj.getVeiculoId());
		viagem.setKey(null);
		viagem.setVeiculo(veiculo);
		viagem.setMotorista(motorista);
		viagem.setTransportadora(transportadora);
		viagem.setDataInical(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
		viagem.setDataFinal(null);
		viagem.setKmIncial(obj.getKmIncial());
		viagem.setKmFinal(obj.getKmFinal());
		viagem.setOrigem(origem);
		viagem.setDestino(destino);
		viagem.setPesoTonelada(obj.getPesoTonelada());
		viagem.setValorFrete(obj.getValorFrete());
		var caixaInicial = caixa.getSaldo();
		viagem.setSaldoInicial(caixaInicial);
		var saldo = caixa.getSaldo() + obj.getValorFrete();
		viagem.setSaldoFinal(saldo);
		viagem.setEstadoViagem(EstadoViagem.toEnum(1));
		viagensRepository.save(viagem);
		return viagem;
	}
	
	public Viagens update(ViagemDTO obj) {
		var viagem = viagensRepository.findById(obj.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		
		Veiculos veiculo = new Veiculos(obj.getVeiculoId());
		Motorista motorista = new Motorista(obj.getMotoristaId());
		Transportadora transportadora = new Transportadora(obj.getTransportadoraId());
		Cidade origem = new Cidade(obj.getOrigem());
		Cidade destino = new Cidade(obj.getDestino());
		CaixaVeiculo caixa = caixaService.findByVeiculo(veiculo.getKey());
		viagem.setVeiculo(veiculo);
		viagem.setMotorista(motorista);
		viagem.setTransportadora(transportadora);
		viagem.setDataInical(obj.getDataInical());
		viagem.setDataFinal(obj.getDataFinal());
		viagem.setKmIncial(obj.getKmIncial());
		viagem.setKmFinal(obj.getKmFinal());
		viagem.setOrigem(origem);
		viagem.setDestino(destino);
		viagem.setPesoTonelada(obj.getPesoTonelada());
		viagem.setValorFrete(obj.getValorFrete());
		viagem.setSaldoInicial(caixa.getSaldo());
		viagem.setSaldoFinal(null);
		viagem.setEstadoViagem(EstadoViagem.toEnum(obj.getEstadoViagem()));
		viagem = viagensRepository.save(viagem);
		return viagem;
	}
	
	public void delete(Long id) {
		Viagens entity = viagensRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		viagensRepository.delete(entity);
	}

}
