package com.robot;

public class Response<T> {
	private Esito result;
	private T content;

	public Esito getEsito() {
		return result;
	}

	public Response(Esito esito, T response) {
		this.result = esito;
		this.setContent(response);
	}

	public Response() {
	}

	public void setEsito(Esito esito) {
		this.result = esito;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}


}
