package com.valentim.cursomc6.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.valentim.cursomc6.domain.Cliente;
import com.valentim.cursomc6.dto.ClienteDTO;
import com.valentim.cursomc6.repositories.ClienteRepository;
import com.valentim.cursomc6.resources.exceptions.FieldMessage;

public class ClienteUpdatetValidator implements ConstraintValidator<ClientUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	
	@Autowired
	private ClienteRepository repo;
	
	
	@Override
	public void initialize(ClientUpdate ann) {
	}	

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		@SuppressWarnings({ "unchecked", "unused" })
		Map<String,String> map =(Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();		
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux !=null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email","Email ja existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}