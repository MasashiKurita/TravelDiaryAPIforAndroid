package com.martymarron.traveldiaryapi;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;

public class RequestAsyncTaskLoader<D> {
	
	private static final String TAG = "RequestAsyncTaskLoader";
	
	private final String apiBase;
	
	private final Request<D> request;
	
	private AsyncTaskLoader<D> asyncTaskLoader;
	
	private LoaderCallbacks<D> loaderCallbacks;
	
	private LoaderManager loaderManager;
		
	public RequestAsyncTaskLoader(Request<D> request) {
		
		this.request = request;
		this.apiBase = request.getContext().getString(com.martymarron.traveldiaryapi.R.string.api_base);

		asyncTaskLoader = new AsyncTaskLoader<D>(request.getContext()) {
			
			private static final String TAG = "AsyncTaskLoader";
						
			@Override
			public D loadInBackground() {
				RestTemplate template = new RestTemplate();
				template.getMessageConverters().add(new GsonHttpMessageConverter());
				
				String url = getApiBase() + getRequest().getPath();
				Log.d(TAG, url);
				
				D data = null;
				try {
//				    Class<?> clazz = (Class<?>)this.getClass();
//				    Type type = clazz.getGenericSuperclass();
//				    Log.d(TAG, type.toString());
//				
//				    ParameterizedType pt = (ParameterizedType)type;
//				    Log.d(TAG, pt.toString());
//				
//				    Type[] actualTypeArguments = pt.getActualTypeArguments();
//				    Log.d(TAG, actualTypeArguments[0].toString());
//				
//				    Class<?> entityClass = (Class<?>)actualTypeArguments[0];
//				    Log.d(TAG, entityClass.getSimpleName());
//				    
//				    data = (D)template.getForObject(url, entityClass);
					
					data = (D)template.getForObject(url, getRequest().getClazz());
				} catch (Exception e) {
					Log.e(TAG, e.getMessage());
				}
				
				return data;
			}
			
		};
		
		loaderCallbacks = new LoaderCallbacks<D>() {

			@Override
			public Loader<D> onCreateLoader(int id, Bundle args) {
				asyncTaskLoader.forceLoad();
				return asyncTaskLoader;
			}

			@Override
			public void onLoadFinished(Loader<D> loader, D data) {
				getRequest().getCallback().onLoadFinished(loader, data);
			}

			@Override
			public void onLoaderReset(Loader<D> loader) {
				getRequest().getCallback().onLoaderReset(loader);
			}
		};
		
		this.loaderManager = request.getLoaderManager();
	}
	
	public void execute() {
		Log.d(TAG, "execute task");
		this.loaderManager.initLoader(0, null, loaderCallbacks);				
	}
	
	public String getApiBase() {
		return apiBase;
	}
	
	public Request<D> getRequest() {
		return request;
	}
		
}
