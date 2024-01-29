package net.javaguides.springboot.service.Event;

import net.javaguides.springboot.model.Event;
import net.javaguides.springboot.model.EventDto;

import java.util.List;

public interface EventService {
    Event getById(Long id);

    List<Event> findAllById(List<Long> ids);

    List<Event> getAll();

    List<Event> getMyEvents();

    //POST methods
    Event create(EventDto eventDto);

    //UPDATE Method
    Event update(Long id, EventDto eventDto);

    //DELETE method
    void delete(Long id);
}
