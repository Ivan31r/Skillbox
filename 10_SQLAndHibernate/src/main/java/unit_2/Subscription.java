package unit_2;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Subscriptions")
public class Subscription {
    @EmbeddedId
    private Id id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id",insertable = false, updatable = false)
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;
    @Column(name = "subscription_date",insertable = false, updatable = false)
    private LocalDateTime subscriptionDate;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "studentId=" + student.getId() +
                ", courseId=" + course.getId() +
                ", subscriptionDate=" + subscriptionDate +
                '}';
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDateTime subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
