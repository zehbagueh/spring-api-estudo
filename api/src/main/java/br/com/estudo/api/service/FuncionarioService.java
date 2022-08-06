package br.com.estudo.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.estudo.api.dto.FuncionarioDTO;
import br.com.estudo.api.entity.Funcionario;
import br.com.estudo.api.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	private FuncionarioRepository repository;

	@Autowired
	ModelMapper mapper;

	public Page<FuncionarioDTO> buscarTodosFuncionarios(Pageable pageable) {
		Page<Funcionario> funcionariosPaginados = repository.findAll(pageable);
		List<FuncionarioDTO> funcionarios = funcionariosPaginados.getContent().stream()
				.map(element -> mapper.map(element, FuncionarioDTO.class)).collect(Collectors.toList());
		return new PageImpl<FuncionarioDTO>(funcionarios, funcionariosPaginados.getPageable(),
				funcionariosPaginados.getTotalElements());
	}

}
