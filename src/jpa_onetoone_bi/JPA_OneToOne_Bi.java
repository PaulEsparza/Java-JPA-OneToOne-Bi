/*

*/
package jpa_onetoone_bi;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import model.Table1;
import model.Table2;

public class JPA_OneToOne_Bi {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU"); //Gestor de entidades
        EntityManager em = emf.createEntityManager(); //Instancia para trabajar con las entidades
        EntityTransaction tx = em.getTransaction(); // Permite hacer transacciones
        
        Table1 t1 = new Table1(14, "Example1 T1 C1", "Example T1 C2");
        //Table2 t2 = new Table2(14, "Example1 T2 C1", "Example T2 C2");
        t1.setTable2(new Table2(14, "Example1 T2 C1", "Example T2 C2"));
        
        //tx.begin(); //Abrir la transaccion
        try {
            em.getTransaction().begin(); //Abrir la transaccion
            
            //t1.setTable2(t2);
            
            //em.persist(t2); // Grabe registro en la base de datos
            em.persist(t1); // Grabe registro en la base de datos
            em.getTransaction().commit(); // Termine la transaccion
            
            System.out.println(t1.toString());
            
            JOptionPane.showMessageDialog(null, "El registro fue"
                    + " satisfactorio");
            
            em.close();
            //em.flush(); // Para guardar en la base de datos(Va a la base de datos forzando que se haga la indicacion en la BD)
            //em.refresh(t1); // Regresar al estado anterior que se encontaba la entidad
            //tx.commit(); // Termine la transaccion
        } catch (Exception e) {
            //tx.rollback();
            em.getTransaction().rollback();
            em.close();
            System.out.println("ERROR: " + e.toString());
        }
    }
    
}
