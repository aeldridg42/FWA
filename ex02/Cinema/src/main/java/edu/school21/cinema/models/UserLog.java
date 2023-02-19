package edu.school21.cinema.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
public class UserLog {
    private long id;
    private long user_id;
    private Date date;
    private Time time;
    private String ip;

    public UserLog(long user_id, Date date, Time time, String ip) {
        this.user_id = user_id;
        this.date = date;
        this.time = time;
        this.ip = ip;
    }

    public UserLog() {}
}
