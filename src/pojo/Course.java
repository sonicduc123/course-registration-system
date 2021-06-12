package pojo;

import java.io.Serializable;

public class Course implements Serializable {
    private int id;
    private String title;
    private int credits;
    private Teacher teacher;
    private Classroom classroom;
    private String dayweek;
    private String shift;
    private int slot;
    private RegistrationSession registrationSession;

    public Course() {};
    public Course(int id, String title, int credits, Teacher teacher, Classroom classroom, String dayweek, String shift, int slot, RegistrationSession registrationSession) {
        this.id = id;
        this.title = title;
        this.credits = credits;
        this.teacher = teacher;
        this.classroom = classroom;
        this.dayweek = dayweek;
        this.shift = shift;
        this.slot = slot;
        this.registrationSession = registrationSession;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public String getDayweek() {
        return dayweek;
    }

    public void setDayweek(String dayweek) {
        this.dayweek = dayweek;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public RegistrationSession getRegistrationSession() {
        return registrationSession;
    }

    public void setRegistrationSession(RegistrationSession registrationSession) {
        this.registrationSession = registrationSession;
    }
}
