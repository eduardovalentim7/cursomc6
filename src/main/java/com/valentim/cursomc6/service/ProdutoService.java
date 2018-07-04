package com.valentim.cursomc6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.valentim.cursomc6.domain.Categoria;
import com.valentim.cursomc6.domain.Produto;
import com.valentim.cursomc6.repositories.CategoriaRepository;
import com.valentim.cursomc6.repositories.ProdutoRepository;
import com.valentim.cursomc6.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Produto obj = repo.findOne(id);
		if(obj==null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id 
					+ ",Tipo: " + Produto.class.getName());
		}
		
		return obj;
	}
	
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer pagina,Integer linhasPorPagina,String orderBy,String direction){
		PageRequest pageRequest = new PageRequest(pagina, linhasPorPagina, Direction.valueOf(direction), orderBy);
		List<Categoria>categorias = categoriaRepository.findAll(ids);
		return repo.search(nome,categorias,pageRequest);
		
		
	}

}
