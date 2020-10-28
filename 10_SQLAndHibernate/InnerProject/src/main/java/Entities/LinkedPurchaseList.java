package Entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Linked_Purchase_List")
public class LinkedPurchaseList {
    @EmbeddedId
    private PurchaseListKey purchaseListKey;


    public LinkedPurchaseList(){

    }

    @Override
    public String toString() {
        return "LinkedPurchaseList{" + "studentId "+
                 purchaseListKey.getStudentId() + "\tcourseId " +
                purchaseListKey.getCourseId() +
                '}';
    }




    public LinkedPurchaseList(PurchaseListKey purchaseListKey) {
        this.purchaseListKey = purchaseListKey;
    }

    public PurchaseListKey getPurchaseListKey() {
        return purchaseListKey;
    }

    public void setPurchaseListKey(PurchaseListKey purchaseListKey) {
        this.purchaseListKey = purchaseListKey;
    }

    public static class PurchaseListKey implements Serializable{
        @Column(name = "student_id")
        private int studentId;
        @Column(name = "course_id")
        private int courseId;

        public PurchaseListKey(){

        }

        public int getStudentId() {
            return studentId;
        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

    }


}
