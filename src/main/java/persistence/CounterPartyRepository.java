package persistence;

import models.CounterPartyEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
}
