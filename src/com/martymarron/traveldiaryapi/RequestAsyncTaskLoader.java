package com.martymarron.traveldiaryapi;

import java.net.URI;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;

/**
 * Refs {@link AsyncTaskLoader }
 * 
 * @author x-masashik
 *
 * @param <D>
 */
public class RequestAsyncTaskLoader<D> {
	
	private static final String TAG = "RequestAsyncTaskLoader";
	
	private final String apiBase;
	
	private final Request<D> request;
	
	private AsyncTaskLoader<D> asyncTaskLoader;
	
	private LoaderCallbacks<D> loaderCallbacks;
		
	/**
	 * 
	 * @param request
	 */
	public RequestAsyncTaskLoader(Request<D> request) {
		
		this.request = request;
		this.apiBase = request.getContext().getString(R.string.api_base);

		asyncTaskLoader = new AsyncTaskLoader<D>(request.getContext()) {
			
			private static final String TAG = "AsyncTaskLoader";
						
			@Override
			public D loadInBackground() {
				RestTemplate template = new RestTemplate();
				template.getMessageConverters().add(new GsonHttpMessageConverter());
				
				URI url = 
						UriComponentsBuilder.fromUriString(getApiBase())
						.path(getRequest().getPath())
						.queryParam("format", "json")
						.build()
						.toUri();
				Log.d(TAG, url.toString());
				
				D data = null;
				try {
					switch (getRequest().getHttpMethod()) {
					case DELETE:
						template.delete(url);
						break;
					
					case POST:
						data = template.postForObject(url, getRequest().getPostData(), getRequest().getClazz());
						break;

					case PUT:
						template.put(url, getRequest().getPostData());
						break;
						
					case GET:
						data = template.getForObject(url, getRequest().getClazz());
						break;
						
					default:
						throw new RestClientException(getRequest().getHttpMethod().name() + " is not supported.");
					}		
				} catch (RestClientException e) {
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
		
	}
	
	public void execute(LoaderManager loaderManager) {
		Log.d(TAG, "execute task");
		loaderManager.initLoader(0, null, loaderCallbacks);				
	}
	
	public String getApiBase() {
		return apiBase;
	}
	
	public Request<D> getRequest() {
		return request;
	}

}
