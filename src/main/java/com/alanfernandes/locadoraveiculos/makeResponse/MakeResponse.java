package com.alanfernandes.locadoraveiculos.makeResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MakeResponse<T> {

	private T payload;

	private String message;

	public MakeResponse(T payload, String message) {
		super();
		this.payload = payload;
		this.message = message;
	}

}
