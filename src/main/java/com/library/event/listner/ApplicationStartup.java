package com.library.event.listner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.library.util.DataService;

/**
 * 
 * Class is responsible for loading the data as soon as the container loaded.
 *
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private DataService dataService;



	@Override
	public void onApplicationEvent(ApplicationReadyEvent arg0) {
		dataService.loadData();
	}

	
}
