package pojo;

import java.util.Objects;

public class IDSession implements java.io.Serializable {
    private String session;
    private String year;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IDSession)) return false;
        IDSession idSession = (IDSession) o;
        return Objects.equals(getSession(), idSession.getSession()) && Objects.equals(getYear(), idSession.getYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSession(), getYear());
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}