package persistence;

import models.AccountEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AccountRepository {

    private SessionFactory sessionFactory;

    public AccountRepository() {
        sessionFactory = new Configuration().configure()
                .buildSessionFactory();
    }

    public AccountEntity persist(AccountEntity accountEntity) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        AccountEntity saved = (AccountEntity) session.merge(accountEntity);

        session.getTransaction().commit();

        session.close();

        return saved;
    }
}
