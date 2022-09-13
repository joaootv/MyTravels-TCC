package br.com.mytravels.apimytravels.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.mytravels.apimytravels.controller.exception.FieldMessage;
import br.com.mytravels.apimytravels.data.dto.TransportadoraNewDTO;
import br.com.mytravels.apimytravels.data.model.Transportadora;
import br.com.mytravels.apimytravels.data.model.enums.TipoPessoa;
import br.com.mytravels.apimytravels.repository.TransportadoraRepository;
import br.com.mytravels.apimytravels.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, TransportadoraNewDTO> {
	
	@Autowired
	private TransportadoraRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(TransportadoraNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getTipo().equals(TipoPessoa.PF.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		
		if (objDto.getTipo().equals(TipoPessoa.PJ.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Transportadora aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
