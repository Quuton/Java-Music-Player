package com.dotgroup;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.dotgroup.classes.Employee;

public class JPATest {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.exit(-1);
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test_schema");
        EntityManager em = emf.createEntityManager();

        try {
            // Persist an Employee
            persistEmployee(em);

            // Retrieve the Employee by ID
            long employeeId = 1L;
            Employee employee = retrieveEmployee(em, employeeId);

            if (employee != null) {
                System.out.println("Retrieved Employee: " + employee.getName());
            } else {
                System.out.println("Employee with ID " + employeeId + " not found.");
            }
        } finally {
            em.close();
            emf.close();
        }
    }
    
    private static void persistEmployee(EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            Employee employee = new Employee();
            employee.setName("bITCH");

            em.persist(employee);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    private static Employee retrieveEmployee(EntityManager em, long employeeId) {
        return em.find(Employee.class, employeeId);
    }
}
