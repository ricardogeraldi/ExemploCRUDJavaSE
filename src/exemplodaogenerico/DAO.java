package exemplodaogenerico;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import org.hibernate.HibernateException;

public class DAO<E> {

    private Class<E> classeEntidade;
    private Query consulta;
    private EntityManager em;

    public DAO(Class<E> c) {
        try {
            this.classeEntidade = c;
            em = Banco.getInstancia().getEm();
            consulta = em.createQuery("from " + classeEntidade.getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(E obj) {
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(E obj) {
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<E> list() {
        List<E> l = null;
        try {
            em.getTransaction().begin();
            l = consulta.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return l;
    }

    public void insert(E obj) {
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public E find(Serializable id) {
        E c = null;
        try {
            em.getTransaction().begin();
            c = (E) em.find(classeEntidade, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return c;
    }

    @SuppressWarnings("unchecked")
    public List<E> findByCriteria(org.hibernate.criterion.Criterion... criterion) {
        List<E> lista = null;
        em.getTransaction().begin();
        org.hibernate.Session session = ((org.hibernate.ejb.HibernateEntityManager) em).getSession();
        org.hibernate.Criteria crit = session.createCriteria(classeEntidade);
        for (org.hibernate.criterion.Criterion c : criterion) {
            crit.add(c);
        }
        lista = crit.list();
        em.getTransaction().commit();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<E> findByExample(Object exampleInstance, String... excludeProperty) {
        List<E> lista = null;
        try {
            em.getTransaction().begin();
            org.hibernate.Criteria crit = ((org.hibernate.ejb.HibernateEntityManager) em).getSession().createCriteria(classeEntidade);
            org.hibernate.criterion.Example example = org.hibernate.criterion.Example.create(exampleInstance);
            for (String exclude : excludeProperty) {
                example.excludeProperty(exclude);
            }
            crit.add(example);
            lista = crit.list();
            em.getTransaction().commit();
        } catch (HibernateException ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<E> findByExamplePartial(Object exampleInstance, String... excludeProperty) {
        List<E> lista = null;
        try {
            em.getTransaction().begin();
            org.hibernate.Criteria crit = ((org.hibernate.ejb.HibernateEntityManager) em).getSession().createCriteria(classeEntidade);
            org.hibernate.criterion.Example example = org.hibernate.criterion.Example.create(exampleInstance);
            example.enableLike(org.hibernate.criterion.MatchMode.ANYWHERE);
            example.ignoreCase();
            example.excludeZeroes();
            for (String exclude : excludeProperty) {
                example.excludeProperty(exclude);
            }
            crit.add(example);
            lista = crit.list();
            em.getTransaction().commit();
        } catch (HibernateException ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
        }
        return lista;
    }
}
