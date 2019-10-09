package domain;

import java.util.Date;

public class GuestBookBean {

    private Date date;

    private String name;

    private String message;

    public GuestBookBean(Date date, String name, String message){
        this.date = date;
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "<tr><td>" + date + "</td><td> " + name + "</td><td> " + message + "<td></tr>";
    }
}
