package net.javaguides.springboot.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name= "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String title;
    public String description;
    @Column(nullable = false, name="all_day")
    public Boolean allDay;
    @Column(nullable = false, name="start_date")
    public LocalDate startDate;
    @Column(nullable = false, name="start_time")
    public LocalTime startTime;
    @Column(nullable = false, name="end_time")
    public LocalTime endTime;
    @Column(nullable = false, name="end_date")
    public LocalDate endDate;
    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "event_guest",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "guest_id"))
    public List<User> guests;
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    public User creator;
    public String link;

    public Event() {
    }

    public Event(String title, String description, Boolean allDay, LocalDate startDate, LocalTime startTime, LocalTime endTime, LocalDate endDate, List<User> guests, User creator, String link) {
        this.title = title;
        this.description = description;
        this.allDay = allDay;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.endDate = endDate;
        this.guests = guests;
        this.creator = creator;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @JsonIgnore
    public List<User> getGuests() {
        return guests;
    }


    public List<String> getGuest() {
        return guests.stream()
                .map(User::getEmail)
                .collect(Collectors.toList());
    }

    public void setGuests(List<User> guests) {
        this.guests = guests;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

}
