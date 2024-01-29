package net.javaguides.springboot.service.Event;

import net.javaguides.springboot.ExceptionHandling.NotFoundException;
import net.javaguides.springboot.model.Event;
import net.javaguides.springboot.model.EventDto;
import net.javaguides.springboot.repository.EventRepository;
import net.javaguides.springboot.service.User.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;
    private final UserService userService;
    static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("HH:mm");

    public EventServiceImpl(EventRepository eventRepository, UserService userService) {
        this.eventRepository = eventRepository;
        this.userService = userService;
    }

    //GET Methods
    @Override
    public Event getById(Long id){
        return this.eventRepository.findById(id).orElseThrow(()->new NotFoundException("Event not found. Id: "+ id));
    }
    @Override
    public List<Event> findAllById(List<Long> ids){
        return this.eventRepository.findAllById(ids);
    }
    @Override
    public List<Event> getAll(){
        return this.eventRepository.findAll();
    }
    @Override
    public List<Event> getMyEvents(){
        return this.eventRepository.findByCreator(userService.getLoggedInUser());
    }

    //POST methods
    @Override
    public Event create(EventDto eventDto){
        validateEvent(eventDto);
        return this.eventRepository.save(convertToEntity(eventDto));
    }

    //UPDATE Method
    @Override
    public Event update(Long id, EventDto eventDto){
        Event event= this.getById(id);
        validateEvent(eventDto);
        event=convertToEntity(eventDto);
        return this.eventRepository.save(event);
    }

    //DELETE method
    @Override
    public void delete(Long id){
        this.eventRepository.deleteById(id);
    }

    public Event convertToEntity(EventDto eventDto){
        return new Event(
                eventDto.getTitle(),
                eventDto.getDescription(),
                eventDto.getAllDay(),
                eventDto.getStartDate(),
                LocalTime.parse(eventDto.getStartTime(),formatter),
                LocalTime.parse(eventDto.getEndTime(),formatter),
                eventDto.getEndDate(),
                userService.findAllById(eventDto.getGuestIds()),
                userService.findById(eventDto.getCreatorId()),
                eventDto.getLink()
        );
    }

    public static void validateEvent(EventDto eventDto) {
        validateStartTimeNotNull(eventDto);
        validateEndTimeNotNull(eventDto);
        validateStartDateNotNull(eventDto);
        validateEndTimeAfterStartTime(eventDto);
        validateEndDateAfterStartDate(eventDto);
    }

    private static void validateStartTimeNotNull(EventDto eventDto) {
        if (!eventDto.getAllDay() && eventDto.getStartTime() == null) {
            throw new IllegalArgumentException("Event is not " +
                    "all day. Start time " +
                    "must not be null.");
        }
    }

    private static void validateEndTimeNotNull(EventDto eventDto) {
        if (!eventDto.getAllDay() && eventDto.getEndTime() == null) {
            throw new IllegalArgumentException("Event is not " +
                    "all day. End time must not be null.");
        }
    }

    private static void validateStartDateNotNull(EventDto eventDto) {
        if (eventDto.getStartDate() == null) {
            throw new IllegalArgumentException("Start date must not be null.");
        }
    }

    private static void validateEndTimeAfterStartTime(EventDto eventDto) {
        if (
                eventDto.getStartTime() != null &&
                eventDto.getEndTime() != null &&
                eventDto.getStartDate().isEqual(eventDto.getEndDate()) &&
                LocalTime.parse(eventDto.getEndTime(),formatter).isBefore(LocalTime.parse(eventDto.getStartTime(),
                        formatter))
        ) {
            throw new IllegalArgumentException("End time must be after the start time.");
        }
    }

    private static void validateEndDateAfterStartDate(EventDto eventDto) {
        if (
                eventDto.getStartDate() != null &&
                eventDto.getEndDate() != null &&
                eventDto.getEndDate().isBefore(eventDto.getStartDate())
        ) {
            throw new IllegalArgumentException("End date must be after the start date.");
        }
    }
}
