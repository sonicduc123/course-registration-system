package pojo;

import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistrationSession)) return false;
        RegistrationSession that = (RegistrationSession) o;
        return getId().equals(that.getId()) && Objects.equals(getStart(), that.getStart()) && Objects.equals(getFinish(), that.getFinish());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStart(), getFinish());
    }
}

