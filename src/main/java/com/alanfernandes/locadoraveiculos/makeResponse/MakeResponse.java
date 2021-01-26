package com.alanfernandes.locadoraveiculos.makeResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MakeResponse<T> {

	private T entity;

	private String message;

	public MakeResponse(T entity, String message) {
		super();
		this.entity = entity;
		this.message = message;
	}

}
