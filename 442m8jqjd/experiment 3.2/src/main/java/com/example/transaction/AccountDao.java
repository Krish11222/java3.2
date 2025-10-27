package com.example.transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        // Session is managed by Spring's HibernateTransactionManager
        return sessionFactory.getCurrentSession();
    }

    public Account getAccountByNumber(String accountNumber) {
        return getCurrentSession().createQuery("FROM Account WHERE accountNumber = :accNum", Account.class)
                .setParameter("accNum", accountNumber)
                .uniqueResult();
    }

    public void updateAccount(Account account) {
        getCurrentSession().update(account);
    }

    public void saveAccount(Account account) {
        getCurrentSession().save(account);
    }
}