package com.robot;

import java.util.List;

public class Response<T> {
	private Esito result;
	private List<T> content;

	public Esito getEsito() {
		return result;
	}

	public Response(Esito esito, List<T> response) {
		this.result = esito;
		this.setContent(response);
	}

	public Response() {
	}

	public void setEsito(Esito esito) {
		this.result = esito;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}


}
