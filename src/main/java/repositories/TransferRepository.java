package repositories;

import models.transfer.TransferEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TransferRepository {

    private SessionFactory sessionFactory;

    public TransferRepository() {
        sessionFactory = new Configuration().configure()
                .buildSessionFactory();
    }

    public TransferEntity persist(TransferEntity transferEntity) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        TransferEntity saved = (TransferEntity) session.merge(transferEntity);

        session.getTransaction().commit();

        session.close();

        return saved;
    }
}
