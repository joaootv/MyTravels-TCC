package br.com.mytravels.apimytravels.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mytravels.apimytravels.data.dto.VeiculoDTO;
import br.com.mytravels.apimytravels.data.dto.VeiculosNewDTO;
import br.com.mytravels.apimytravels.data.model.Cidade;
import br.com.mytravels.apimytravels.data.model.Motorista;
import br.com.mytravels.apimytravels.data.model.TipoVeiculo;
import br.com.mytravels.apimytravels.data.model.Transportadora;
import br.com.mytravels.apimytravels.data.model.Veiculos;
import br.com.mytravels.apimytravels.repository.VeiculosRepository;

@Service
public class VeiculoServices {

	@Autowired
	private VeiculosRepository veiculosRepository;
	
	@Autowired
	private MotoristaService motoristaService;
	
	@Autowired
	private TransportadoraServices transportadoraServices;

	public List<Veiculos> findAll() {
		return veiculosRepository.findAll();
	}
	
	public List<Veiculos> findByTransportadora(Long id) {
		Optional<Transportadora> transportadora = this.transportadoraServices.findById(id);
		return veiculosRepository.findByTransportadora(transportadora);
	}

	public Optional<Veiculos> findById(Long id) {
		return veiculosRepository.findById(id);
	}
	
	public Optional<Veiculos> findByMotorista(Long id) {
		Motorista motorista = motoristaService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado!"));
		Veiculos veiculo = veiculosRepository.findByMotorista(motorista);
		return this.findById(veiculo.getKey());
	}

	@Transactional
	public Veiculos insert(Veiculos obj) {
		obj.setKey(null);
		obj = veiculosRepository.save(obj);
		return obj;
	}

	public Veiculos fromDTO(VeiculosNewDTO objDto) {
		TipoVeiculo tipo = new TipoVeiculo(objDto.getIdTipo(), null);
		Cidade cid = new Cidade(objDto.getCidadeEmplacado(), null, null);
		Transportadora transportadora = new Transportadora(objDto.getTransportadora());
		if (objDto.getMotorista() != null) {
			Motorista motorista = new Motorista(objDto.getMotorista());
			Veiculos veiculo = new Veiculos(null, objDto.getPlaca(), objDto.getRenavam(), tipo, objDto.getMarca(), objDto.getModelo(), objDto.getAnoFabricacao(), objDto.getAnoModelo(), objDto.getExercicioDoc(), objDto.getCor(), objDto.getChassi(), cid, objDto.getKilometragem(), motorista, transportadora);
			if (objDto.getImplemento1() != null) {
				Veiculos imp1 = new Veiculos(objDto.getImplemento1());
				veiculo.getConjuntos().add(imp1);
			}
			if (objDto.getImplemento2() != null) {
				Veiculos imp2 = new Veiculos(objDto.getImplemento2());
				veiculo.getConjuntos().add(imp2);
			}
			if (objDto.getImplemento3() != null) {
				Veiculos imp3 = new Veiculos(objDto.getImplemento3());
				veiculo.getConjuntos().add(imp3);
			} 
			return veiculo;
		} else {
			Veiculos veiculo = new Veiculos(null, objDto.getPlaca(), objDto.getRenavam(), tipo, objDto.getMarca(), objDto.getModelo(), objDto.getAnoFabricacao(), objDto.getAnoModelo(), objDto.getExercicioDoc(), objDto.getCor(), objDto.getChassi(), cid, objDto.getKilometragem(), null, transportadora);
			if (objDto.getImplemento1() != null) {
				Veiculos imp1 = new Veiculos(objDto.getImplemento1());
				veiculo.getConjuntos().add(imp1);
			}
			if (objDto.getImplemento2() != null) {
				Veiculos imp2 = new Veiculos(objDto.getImplemento2());
				veiculo.getConjuntos().add(imp2);
			}
			if (objDto.getImplemento3() != null) {
				Veiculos imp3 = new Veiculos(objDto.getImplemento3());
				veiculo.getConjuntos().add(imp3);
			} 
			return veiculo;
		}
	}
	
	public Veiculos update(VeiculoDTO veiculo) {
		var entity = veiculosRepository.findById(veiculo.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		
		
		Cidade cidade = new Cidade(veiculo.getCidadeEmplacado(), null, null);
		entity.setCidadeEmplacado(cidade);
		TipoVeiculo tipo = new TipoVeiculo(veiculo.getTipo(), null);
		entity.setTipo(tipo);
		if (veiculo.getMotorista() != null) {
			Motorista motorista = new Motorista(veiculo.getMotorista());
			entity.setMotorista(motorista);
		} else {
			entity.setMotorista(null);
		}
		entity.setPlaca(veiculo.getPlaca());
		entity.setRenavam(veiculo.getRenavam());
		entity.setMarca(veiculo.getMarca());
		entity.setModelo(veiculo.getModelo());
		entity.setAnoFabricacao(veiculo.getAnoFabricacao());
		entity.setAnoModelo(veiculo.getAnoModelo());
		entity.setExercicioDoc(veiculo.getExercicioDoc());
		entity.setCor(veiculo.getCor());
		entity.setChassi(veiculo.getChassi());
		entity.setKilometragem(veiculo.getKilometragem());
		
		veiculosRepository.save(entity);
		return entity;
	}
	
	public void delete(Long id) {
		Veiculos entity = veiculosRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		veiculosRepository.delete(entity);
	}

}
