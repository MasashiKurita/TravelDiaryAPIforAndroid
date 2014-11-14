package com.martymarron.traveldiaryapi;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

/**
 * @author x-masashik
 *
 */
public class Request<D> {
	
	private Context context;
	
	private LoaderManager loaderManager;
	
	private String path;
	
	private Bundle params;
	
	private Callback<D> callback;
	
	private Class<D> clazz;
	
	/**
	 * @param context
	 */
	public Request(Context context, LoaderManager loaderManager, String path, Bundle params, Callback<D> callback, Class<D> clazz) {
		this.context = context;
		this.setLoaderManager(loaderManager);
		this.path = path;
		this.params = params;
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
	 * @return the loaderManager
	 */
	public LoaderManager getLoaderManager() {
		return loaderManager;
	}

	/**
	 * @param loaderManager the loaderManager to set
	 */
	public void setLoaderManager(LoaderManager loaderManager) {
		this.loaderManager = loaderManager;
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


	public interface Callback<D> {
		
		void onLoadFinished(Loader<D> loader, D data);

		void onLoaderReset(Loader<D> loader);
		
	}
}
