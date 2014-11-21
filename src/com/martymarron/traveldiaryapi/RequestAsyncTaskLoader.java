package com.martymarron.traveldiaryapi;

import java.io.Serializable;
import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
 * @param <D extends Serializable>
 */
public class RequestAsyncTaskLoader<D extends Serializable> {
	
	private static final String TAG = "RequestAsyncTaskLoader";
	
	private final String apiBase;
	
	private final Request<D> request;
	
	private final Response<D> response = new Response<D>();

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
				Class<D> responseType = getRequest().getClazz();
				D request = getRequest().getPostData();
				HttpMethod method = getRequest().getHttpMethod();
				
				HttpEntity<D> requestEntity = new HttpEntity<D>(request);
				
				D data = null;
				try {
					ResponseEntity<D> responseEntity = null;
					
					switch (method) {
					case POST:
						responseEntity = template.postForEntity(url, request, responseType);
						break;

					case GET:
						responseEntity = template.getForEntity(url, responseType);
						break;

					case PUT:
					case DELETE:
						responseEntity = template.exchange(url, method, requestEntity, responseType);
						break;
					
					default:
						throw new RestClientException(getRequest().getHttpMethod().name() + " is not supported.");
					}
					
					if (responseEntity != null) {
						data = responseEntity.getBody();
						
					    response.setStatusCode(responseEntity.getStatusCode());
					    response.setHeaders(responseEntity.getHeaders());
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
				response.setLoader(loader);
				response.setBody(data);
				getRequest().getCallback().onLoadFinished(response);
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
