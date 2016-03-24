package exemplodaogenerico;

import java.sql.Connection;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Banco {
    private static Banco instancia;

    private EntityManager em;

    public synchronized static Banco getInstancia(){
        if (instancia == null){
            instancia = new Banco();
        }
        return instancia;
    }
    
    private Banco() {
        em = Persistence.createEntityManagerFactory("ExemploHibernatePU").createEntityManager();
    }

    public EntityManager getEm() {
        return em;
    }
    
    public Connection getConnection(){
        org.hibernate.Session session = ((org.hibernate.ejb.HibernateEntityManager) em).getSession();
        return session.connection();
    }
}