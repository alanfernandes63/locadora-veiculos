package com.alanfernandes.locadoraveiculos.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javassist.NotFoundException;

public abstract class GenericService<T, R extends JpaRepository<T, Long>> {

	@Autowired
	R genericRepository;

	final Class<T> typeParameterClass;

	Logger logger = LoggerFactory.getLogger(GenericService.class);

	@SuppressWarnings("unchecked")
	public GenericService() {
		super();
		this.typeParameterClass = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0]);
	}

	@Transactional
	public T save(T entity) {
		return this.genericRepository.save(entity);
	}

	@Transactional
	public void deleteById(Long id) throws NotFoundException {
		T entity = this.findById(id);
		this.genericRepository.delete(entity);
	}

	public T findById(Long id) throws NotFoundException {
		T entity = genericRepository.findById(id).orElseThrow(() -> new NotFoundException(
				typeParameterClass.getSimpleName() + " " + "com id " + id + " " + "Não encontrado"));
		return entity;
	}

	public List<T> findAll() {
		return this.genericRepository.findAll();
	}

}
