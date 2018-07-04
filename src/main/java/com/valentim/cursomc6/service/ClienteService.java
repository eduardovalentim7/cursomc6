package com.valentim.cursomc6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.valentim.cursomc6.domain.Cidade;
import com.valentim.cursomc6.domain.Cliente;
import com.valentim.cursomc6.domain.Endereco;
import com.valentim.cursomc6.domain.enums.TipoCliente;
import com.valentim.cursomc6.dto.ClienteDTO;
import com.valentim.cursomc6.dto.ClienteNewDTO;
import com.valentim.cursomc6.repositories.CidadeRepository;
import com.valentim.cursomc6.repositories.ClienteRepository;
import com.valentim.cursomc6.repositories.EnderecoRepository;
import com.valentim.cursomc6.service.exceptions.DataIntgrityException;
import com.valentim.cursomc6.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if(obj==null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id 
					+ ",Tipo: " + Cliente.class.getName());
		}
		
		return obj;
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj=repo.save(obj);
		enderecoRepository.save(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj=find(obj.getId());
		updateData(newObj,obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);		
	try {
		repo.delete(id);
		} catch (DataIntegrityViolationException e) {
		throw new DataIntgrityException("Não é possivel excluir porque ha entidads relacionadas ");	
		}
		
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer pagina,Integer linhasPorPagina,String orderBy,String direction){
		PageRequest pageRequest = new PageRequest(pagina, linhasPorPagina, Direction.valueOf(direction), orderBy);
	   return repo.findAll(pageRequest);
	
	}
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),null,null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null,objDto.getNome(),objDto.getEmail(),objDto.getCpfOuCnpj(),TipoCliente.toEmum(objDto.getTipo()));
		Cidade cid = cidadeRepository.findOne(objDto.getCidadeId());
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(),cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}

	private void updateData(Cliente newObj,Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
