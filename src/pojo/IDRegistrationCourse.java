package pojo;

import java.util.Objects;

public class IDRegistrationCourse implements java.io.Serializable {
    private int id_course;
    private int id_student;
    public IDRegistrationCourse() {};
    public IDRegistrationCourse(int id_course, int id_student) {
        this.id_course = id_course;
        this.id_student = id_student;
    }

    public int getId_course() {
        return id_course;
    }

    public void setId_course(int id_course) {
        this.id_course = id_course;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IDRegistrationCourse)) return false;
        IDRegistrationCourse that = (IDRegistrationCourse) o;
        return getId_course() == that.getId_course() && getId_student() == that.getId_student();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_course(), getId_student());
    }
}