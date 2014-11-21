package com.martymarron.traveldiaryapi;

import java.io.Serializable;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import android.content.Loader;

/**
 * 
 * @author x-masashik
 *
 * @param <D extends Serializable>
 */
public class Response<D extends Serializable> {

	Loader<D> loader;
	
	private HttpStatus statusCode;
	
	private D body;
	
	private HttpHeaders headers;
	
	/**
	 * 
	 */
	public Response() {
	}
	
	/**
	 * 
	 * @param loader
	 * @param statusCode
	 * @param body
	 * @param headers
	 */
	public Response(Loader<D> loader, HttpStatus statusCode, D body, HttpHeaders headers) {
		this.loader = loader;
		this.statusCode = statusCode;
		this.body = body;
		this.headers = headers;
	}

	/**
	 * @return the loader
	 */
	public Loader<D> getLoader() {
		return loader;
	}

	/**
	 * @param loader the loader to set
	 */
	public void setLoader(Loader<D> loader) {
		this.loader = loader;
	}

	/**
	 * @return the statusCode
	 */
	public HttpStatus getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the body
	 */
	public D getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(D body) {
		this.body = body;
	}

	/**
	 * @return the headers
	 */
	public HttpHeaders getHeaders() {
		return headers;
	}

	/**
	 * @param headers the headers to set
	 */
	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}
}
