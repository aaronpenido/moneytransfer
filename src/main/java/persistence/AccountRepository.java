package persistence;

import exceptions.AccountNotFoundException;
import models.account.AccountEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;

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

    public AccountEntity findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            AccountEntity accountEntity = (AccountEntity) session.createQuery(
                    "FROM Account WHERE id = " + id).getSingleResult();

            session.close();

            return accountEntity;
        } catch (NoResultException noResultException) {
            throw new AccountNotFoundException(id);
        }
    }
}
