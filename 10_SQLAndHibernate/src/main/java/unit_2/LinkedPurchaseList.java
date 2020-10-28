package unit_2;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Linked_Purchase_List")
public class LinkedPurchaseList {
    @EmbeddedId
    private PurchaseListKey purchaseListKey;
    @Column(name = "student_name", insertable = false,updatable = false)
    private String studentName;
    @Column(name = "course_name" , insertable = false, updatable = false)
    private String courseName;

    public LinkedPurchaseList(){

    }

    public LinkedPurchaseList(PurchaseListKey purchaseListKey, String studentName, String courseName) {
        this.purchaseListKey = purchaseListKey;
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public class PurchaseListKey implements Serializable{
        @Column(name = "student_name")
        private String studentName;
        @Column(name = "course_name")
        private String courseName;

        public PurchaseListKey(){

        }
        PurchaseListKey(String studentName,String courseName){
            this.studentName=studentName;
            this.courseName=courseName;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }
    }


}
