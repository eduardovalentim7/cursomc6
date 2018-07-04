package com.valentim.cursomc6.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valentim.cursomc6.domain.Categoria;
import com.valentim.cursomc6.domain.Cidade;
import com.valentim.cursomc6.domain.Cliente;
import com.valentim.cursomc6.domain.Endereco;
import com.valentim.cursomc6.domain.Estado;
import com.valentim.cursomc6.domain.Pagamento;
import com.valentim.cursomc6.domain.PagamentoComBoleto;
import com.valentim.cursomc6.domain.PagamentoComCartao;
import com.valentim.cursomc6.domain.Pedido;
import com.valentim.cursomc6.domain.Produto;
import com.valentim.cursomc6.domain.enums.EstadoPagamento;
import com.valentim.cursomc6.domain.enums.TipoCliente;
import com.valentim.cursomc6.repositories.CategoriaRepository;
import com.valentim.cursomc6.repositories.CidadeRepository;
import com.valentim.cursomc6.repositories.ClienteRepository;
import com.valentim.cursomc6.repositories.EnderecoRepository;
import com.valentim.cursomc6.repositories.EstadoRepository;
import com.valentim.cursomc6.repositories.PagamentoRepository;
import com.valentim.cursomc6.repositories.PedidoRepository;
import com.valentim.cursomc6.repositories.ProdutoRepository;


@Service
public class DBService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	
	
	
	
	public void instantiateTestDatabase() throws ParseException {
		
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "cama,mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletronicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		Categoria cat8 = new Categoria(null, "Teste");
		
		

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);		
		Produto p3 = new Produto(null, "Mouse", 80.000);
		Produto p4 = new Produto(null, "Mesa de Escritorio", 300.000);
		Produto p5 = new Produto(null, "Toalha", 50.000);
		Produto p6 = new Produto(null, "Colcha", 200.000);
		Produto p7 = new Produto(null, "TV tue Collor", 1200.000);
		Produto p8 = new Produto(null, "Roçadeira", 800.000);
		Produto p9 = new Produto(null, "Abajour", 100.000);
		Produto p10 = new Produto(null, "Mouse", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 80.000);
		
			

		// Associações
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1. getCategorias().addAll(Arrays.asList(cat1,cat4));
		p2. getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3. getCategorias().addAll(Arrays.asList(cat1,cat4));
		p4. getCategorias().addAll(Arrays.asList(cat2));
		p5. getCategorias().addAll(Arrays.asList(cat3));
		p6. getCategorias().addAll(Arrays.asList(cat3));
		p7. getCategorias().addAll(Arrays.asList(cat4));
		p8. getCategorias().addAll(Arrays.asList(cat5));
		p9. getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		categoriaRepository.save(Arrays.asList(cat1, cat2,cat3,cat4,cat5,cat6,cat7,cat8));
		produtoRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "Sao Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", e1);
		Cidade c2 = new Cidade(null, "Sao Paulo", e2);
		Cidade c3 = new Cidade(null, "Campinas", e2);

		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.save(Arrays.asList(e1, e2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Carlos Eduardo", "eduardo@gmail.com", "12345678", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("988529717", "56545598"));

		Endereco end1 = new Endereco(null, "Rua Jose Valdir Barbosa", "159", "Prox Cemiterio", "Parque das Rosas",
				"22222222222", cli1, c1);
		Endereco end2 = new Endereco(null, "Rua Jose Valdir Barbosa", "159", "Prox Cemiterio", "Parque das Rosas",
				"22222222222", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(end1, end2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:30"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("15/05/2018 10:40"), cli1, end2);

		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);

		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pgto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pgto1, pgto2));
		

	}

}
