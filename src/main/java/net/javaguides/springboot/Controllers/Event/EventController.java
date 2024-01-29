package net.javaguides.springboot.Controllers.Event;

import io.swagger.v3.oas.annotations.Operation;
import net.javaguides.springboot.model.Event;
import net.javaguides.springboot.model.EventDto;
import net.javaguides.springboot.service.Event.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "Create Event.")
    @PostMapping
    public ResponseEntity<Event> create(@RequestBody EventDto eventDto){
        return ResponseEntity.ok().body(eventService.create(eventDto));
    }

    @Operation(summary = "Update Event By Id.")
    @PutMapping("/{id}")
    public ResponseEntity<Event> update(@PathVariable("id") Long id
            , EventDto eventDto){
        return ResponseEntity.ok().body(eventService.update(id,
                eventDto));
    }

    @Operation(summary = "Delete Event By Id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        eventService.delete(id);
        return ResponseEntity.ok().body("Event deleted successfully");
    }

    @Operation(summary = "Get All Events.")
    @GetMapping
    public ResponseEntity<List<Event>> getAll(){
        return ResponseEntity.ok().body(eventService.getAll());
    }

    @Operation(summary = "Get Events by Id.")
    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(eventService.getById(id));
    }

    @Operation(summary = "Get Events of currently logged in user.")
    @GetMapping("/my-events")
    public ResponseEntity<List<Event>> getMyEvents(){
        return ResponseEntity.ok(eventService.getMyEvents());
    }
}
