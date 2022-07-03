import entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    private static EntityManager em;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("qbit_jpa");
        em = emf.createEntityManager();

        createEmployee(4, "Qui", "Winnipeg", "Hospitality");
        createEmployee(5, "Quo", "Rulopoide", "IT");
        createEmployee(6, "Qua", "Grassopper", "Marketing");
    }

    private static void createEmployee(int id, String firstName, String lastName, String dept) {
        em.getTransaction().begin();
        Employee emp = new Employee(id, firstName, lastName, dept);
        em.persist(emp);
        em.getTransaction().commit();
    }
}
