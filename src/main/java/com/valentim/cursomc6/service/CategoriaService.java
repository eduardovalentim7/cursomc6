package com.valentim.cursomc6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.valentim.cursomc6.domain.Categoria;
import com.valentim.cursomc6.dto.CategoriaDTO;
import com.valentim.cursomc6.repositories.CategoriaRepository;
import com.valentim.cursomc6.service.exceptions.DataIntgrityException;
import com.valentim.cursomc6.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);
		if(obj==null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id 
					+ ",Tipo: " + Categoria.class.getName());
		}
		
		return obj;
	}

	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		Categoria newObj=find(obj.getId());
		updateData(newObj,obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);		
	try {
		repo.delete(id);
		} catch (DataIntegrityViolationException e) {
		throw new DataIntgrityException("Não é possivel excluir uma categoria que contem produtos");	
		}
		
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer pagina,Integer linhasPorPagina,String orderBy,String direction){
		PageRequest pageRequest = new PageRequest(pagina, linhasPorPagina, Direction.valueOf(direction), orderBy);
	   return repo.findAll(pageRequest);
	
	}
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(),objDto.getNome());
	}
	
	private void updateData(Categoria newObj,Categoria obj) {
		newObj.setNome(obj.getNome());
		
	}
}
