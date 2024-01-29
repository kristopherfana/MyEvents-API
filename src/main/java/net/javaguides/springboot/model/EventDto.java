package net.javaguides.springboot.model;

import net.javaguides.springboot.service.User.UserService;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventDto {
    private String title;
    private String description;
    private Boolean allDay;
    private String startDate;
    private String startTime;
    private String endTime;
    private String endDate;
    private List<Long> guestIds;
    private Long creatorId;
    private String link;

    public EventDto(){}

    public EventDto(String title, String description,
                    Boolean allDay, String startDate,
                    String startTime, String endTime,
                    String endDate, List<Long> guestIds,
                    Long creatorId, String link) {
        this.title = title;
        this.description = description;
        this.allDay = allDay;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.endDate = endDate;
        this.guestIds = guestIds;
        this.creatorId = creatorId;
        this.link = link;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(startDate, formatter);
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public LocalDate getEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(endDate, formatter);
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }



    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Long> getGuestIds() {
        return guestIds;
    }

    public void setGuestIds(List<Long> guestIds) {
        this.guestIds = guestIds;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
