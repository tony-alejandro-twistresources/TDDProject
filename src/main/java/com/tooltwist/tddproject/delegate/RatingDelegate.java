package com.tooltwist.tddproject.delegate;

import java.util.ArrayList;
import java.util.List;

import com.tooltwist.tddproject.bean.Event;
import com.tooltwist.tddproject.manager.RatingManager;

public class RatingDelegate {

	private RatingManager manager;
	
	public void setManager(RatingManager manager) {
		this.manager = manager;
	}

	public List<Event> getEvents() {
		manager.retrieveEvents();
		Event event = new Event();
		List<Event> events = new ArrayList<Event>();
		events.add(event);
		return events;
		
	}

}
