package pojo;

import java.util.Date;

public class RegistrationSession implements java.io.Serializable {
    private IDSession id;
    private Date start;
    private Date finish;

    public RegistrationSession() {}
    public RegistrationSession(IDSession id, Date start, Date finish) {
        this.id = id;
        this.start = start;
        this.finish = finish;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public IDSession getId() {
        return id;
    }

    public void setId(IDSession id) {
        this.id = id;
    }
}

