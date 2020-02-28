package br.com.maiscontrole.simulador.util;

import org.springframework.http.HttpStatus;

/**
 * Utilizado para padronizar as respostas da API
 * 
 * @author Henrique Santiago
 * 
 */
public class ResponseData {

	private Object data;
	private int status;
	private String message;
	private boolean success;

	public ResponseData(int status, String message) {
		super();
		this.status = status;
		this.message = message;
		this.success = HttpStatus.OK.value() == status || HttpStatus.CREATED.value() == status;
	}

	public ResponseData(Object data) {
		super();
		this.data = data;
		this.status = 202;
		this.success = true;
	}

	public ResponseData(Object data, String message) {
		super();
		this.data = data;
		this.status = 202;
		this.message = message;
		this.success = true;
	}

	public ResponseData(Object data, String message, HttpStatus http) {
		super();
		this.data = data;
		this.status = 202;
		this.message = message;
		this.success = true;
	}

	public ResponseData(Object data, HttpStatus http) {
		super();
		this.data = data;
		this.status = http.value();
		this.success = true;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}