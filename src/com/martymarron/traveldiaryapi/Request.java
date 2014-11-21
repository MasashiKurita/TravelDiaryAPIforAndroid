package com.martymarron.traveldiaryapi;

import java.io.Serializable;

import org.springframework.http.HttpMethod;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

/**
 * @author x-masashik
 *
 * @param <D extends Serializable>
 */
public class Request<D extends Serializable> {
	
	private Context context;
	
	private HttpMethod httpMethod;
	
	private String path;
	
	private Bundle params;
	
	private D postData;
	
	private Callback<D> callback;
	
	private Class<D> clazz;
	
	/**
	 * 
	 * @param context
	 * @param path
	 * @param params
	 * @param httpMethod
	 * @param postData
	 * @param callback
	 * @param clazz
	 */
	public Request(Context context, 
			String path, Bundle params, 
			HttpMethod httpMethod, 
			D postData,
			Callback<D> callback, 
			Class<D> clazz) {
		this.context = context;
		this.path = path;
		this.params = params;
		this.httpMethod = httpMethod;
		this.postData = postData;
		this.callback = callback;
		this.clazz = clazz;
	}
	
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the params
	 */
	public Bundle getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(Bundle params) {
		this.params = params;
	}

	/**
	 * @return the context
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * @return the callback
	 */
	public Callback<D> getCallback() {
		return callback;
	}

	/**
	 * @param callback the callback to set
	 */
	public void setCallback(Callback<D> callback) {
		this.callback = callback;
	}


	/**
	 * @return the httpMethod
	 */
	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	/**
	 * @param httpMethod the httpMethod to set
	 */
	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	/**
	 * @return the postData
	 */
	public D getPostData() {
		return postData;
	}

	/**
	 * @param postData the postData to set
	 */
	public void setPostData(D postData) {
		this.postData = postData;
	}

	/**
	 * @return the clazz
	 */
	public Class<D> getClazz() {
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(Class<D> clazz) {
		this.clazz = clazz;
	}


	/**
	 * Refs {@link LoaderCallbacks }
	 * 
	 * @author x-masashik
	 *
	 * @param <D extends Serializable>
	 */
	public interface Callback<D extends Serializable> {
		
		/**
		 * Refs {@link LoaderCallbacks#onLoadFinished(Loader, Object)  }
		 * 
		 * @param loader
		 * @param data
		 */
		void onLoadFinished(Response<D> response);

		/**
		 * Refs {@link LoaderCallbacks#onLoaderReset(Loader)  }
		 * 
		 * @param loader
		 */
		void onLoaderReset(Loader<D> loader);
		
	}
}
