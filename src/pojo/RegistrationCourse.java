package pojo;

import java.io.Serializable;
import java.util.Date;

public class RegistrationCourse implements Serializable {
    private IDRegistrationCourse id;
    private Date time_registration;

    public RegistrationCourse() {};
    public RegistrationCourse(IDRegistrationCourse id, Date time_registration) {
        this.id = id;
        this.time_registration = time_registration;
    }

    public Date getTime_registration() {
        return time_registration;
    }

    public void setTime_registration(Date time_registration) {
        this.time_registration = time_registration;
    }

    public IDRegistrationCourse getId() {
        return id;
    }

    public void setId(IDRegistrationCourse id) {
        this.id = id;
    }
}
