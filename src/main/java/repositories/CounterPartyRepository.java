package repositories;

import exceptions.CounterPartyNotFoundException;
import models.counterparty.CounterPartyEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;

public class CounterPartyRepository {

    private SessionFactory sessionFactory;

    public CounterPartyRepository() {
        sessionFactory = new Configuration().configure()
                .buildSessionFactory();
    }

    public CounterPartyEntity persist(CounterPartyEntity counterPartyEntity) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        CounterPartyEntity saved = (CounterPartyEntity) session.merge(counterPartyEntity);

        session.getTransaction().commit();

        session.close();

        return saved;
    }

    public CounterPartyEntity findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            CounterPartyEntity counterPartyEntity = (CounterPartyEntity) session.createQuery(
                    "FROM CounterParty WHERE id = " + id).getSingleResult();

            session.close();

            return counterPartyEntity;
        } catch (NoResultException noResultException) {
            throw new CounterPartyNotFoundException(id);
        }
    }
}
