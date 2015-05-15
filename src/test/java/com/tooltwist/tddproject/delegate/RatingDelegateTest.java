package com.tooltwist.tddproject.delegate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tooltwist.tddproject.bean.Comment;
import com.tooltwist.tddproject.bean.Event;
import com.tooltwist.tddproject.manager.RatingManager;
import com.tooltwist.xdata.XD;
import com.tooltwist.xdata.XDException;

public class RatingDelegateTest {

	private RatingDelegate setupDelegate(List<Event> expectedEvents) throws XDException {
		RatingManager manager = mock(RatingManager.class);
		RatingDelegate delegate = new RatingDelegate();
		
		JsonObject eventObject = new JsonObject();
		eventObject.add("events", new Gson().toJsonTree(expectedEvents, List.class));
		
		delegate.setManager(manager);

		when(manager.retrieveEvents()).thenReturn(new XD(eventObject.toString()));
		return delegate;
	}

	@Test
	public void getEvents_ShouldRetrieveEventsFromRatingManager()
			throws Exception {
		RatingManager manager = mock(RatingManager.class);
		RatingDelegate delegate = new RatingDelegate();

		delegate.setManager(manager);

		when(manager.retrieveEvents()).thenReturn(new XD(""));

		delegate.getEvents();

		verify(manager).retrieveEvents();
	}

	@Test
	public void getEvents_ShouldReturnListOfEvents() throws Exception {
		
		RatingDelegate delegate = setupDelegate(null);

		List<Event> events = delegate.getEvents();

		assertThat(events, not(emptyCollectionOf(Event.class)));
	}
	
	@Test
	public void getEvents_ShouldReturnReceivedEventNotNull() throws Exception{
		List<Event> expectedEvents = new ArrayList<Event>();
		expectedEvents.add(new Event());
		RatingDelegate delegate = setupDelegate(expectedEvents);

		Event event = delegate.getEvents().get(0);
		
		assertThat(event, notNullValue());
	}
	
	@Test
	public void getEvents_ShouldReturnReceivedEventName() throws Exception{
		
		Event expectedEvent = new Event();
		expectedEvent.setEvent("Event1");
		List<Event> expectedEvents = new ArrayList<Event>();
		expectedEvents.add(expectedEvent);
		RatingDelegate delegate = setupDelegate(expectedEvents);

		Event event = delegate.getEvents().get(0);
		
		assertThat(event.getEvent(), equalTo("Event1"));
	}
	
	@Test
	public void getEvents_ShouldReturnAnotherReceivedEventName() throws Exception{

		Event expectedEvent = new Event();
		expectedEvent.setEvent("Event2");
		List<Event> expectedEvents = new ArrayList<Event>();
		expectedEvents.add(expectedEvent);
		RatingDelegate delegate = setupDelegate(expectedEvents);

		Event event = delegate.getEvents().get(0);
		
		assertThat(event.getEvent(), equalTo("Event2"));
	}
	
	@Test
	public void getEvents_ShouldReturnOneComment() throws Exception{
		
		Event expectedEvent = new Event();
		expectedEvent.setComments(new ArrayList<Comment>());
		expectedEvent.getComments().add(new Comment(1.0, "Bad Rating."));
		List<Event> expectedEvents = new ArrayList<Event>();
		expectedEvents.add(expectedEvent);
		RatingDelegate delegate = setupDelegate(expectedEvents);

		Event event = delegate.getEvents().get(0);
		
		assertThat(event.getComments().size(), equalTo(1));
		assertThat(event.getComments().get(0).getRating(), equalTo(1.0));
		assertThat(event.getComments().get(0).getComment(), equalTo("Bad Rating."));
		
	}
	
	@Test
	public void getEvents_ShouldReturnTwoComments() throws Exception{
		
		Event expectedEvent = new Event();
		expectedEvent.setComments(new ArrayList<Comment>());
		expectedEvent.getComments().add(new Comment(1, "Bad Rating."));
		expectedEvent.getComments().add(new Comment(5, "Good Rating."));
		List<Event> expectedEvents = new ArrayList<Event>();
		expectedEvents.add(expectedEvent);
		RatingDelegate delegate = setupDelegate(expectedEvents);
		
		Event event = delegate.getEvents().get(0);

		assertThat(event.getComments().size(), equalTo(2));

		assertThat(event.getComments().get(0).getRating(), equalTo(1.0));
		assertThat(event.getComments().get(0).getComment(), equalTo("Bad Rating."));
		
		assertThat(event.getComments().get(1).getRating(), equalTo(5.0));
		assertThat(event.getComments().get(1).getComment(), equalTo("Good Rating."));
	}
	
	
}
