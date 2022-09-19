package com.act.cooperativism.exception;

import java.io.Serializable;

import lombok.Data;

@Data
public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long timestamp;
	private Integer status;
	private String message;

	public StandardError() {
		super();
	}
	
	public StandardError(Long timestamp, Integer status, String message) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
	}

}
