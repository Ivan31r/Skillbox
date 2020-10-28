package unit_2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Query sqlQuery = session.createSQLQuery(" Select students.name as student_name, Courses.name as course_name from courses" +
                " join subscriptions on subscriptions.course_id = courses.id" +
                " join students on students.id = subscriptions.student_id;");
        List list =  sqlQuery.getResultList();
        for (Object o : list){
            System.out.println(o);
        }
        System.out.println(list.size());
        session.getTransaction().commit();

        


        sessionFactory.close();
    }
}
