package Main;


import Entities.LinkedPurchaseList;
import Entities.Subscription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        TypedQuery <Subscription> subscriptionTypedQuery =
                session.createQuery("From Subscription", Subscription.class);
        List<Subscription> list = subscriptionTypedQuery.getResultList();
        for (Subscription s : list){
            int studentId = s.getStudent().getId();
            int courseId = s.getCourse().getId();
            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
            LinkedPurchaseList.PurchaseListKey purchaseListKey = new LinkedPurchaseList.PurchaseListKey();
            purchaseListKey.setStudentId(studentId);
            purchaseListKey.setCourseId(courseId);
            linkedPurchaseList.setPurchaseListKey(purchaseListKey);
            session.save(linkedPurchaseList);
        }

        session.getTransaction().commit();



        sessionFactory.close();
    }
}
