package com.example.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    // *** @Transactional guarantees that both DB operations succeed or both fail (Atomicity) ***
    @Transactional
    public void transferFunds(String fromAcc, String toAcc, double amount) {
        System.out.println("\nStarting transfer: $" + amount + " from " + fromAcc + " to " + toAcc);

        // 1. Deduct money
        Account fromAccount = accountDao.getAccountByNumber(fromAcc);
        if (fromAccount.getBalance() < amount) {
            // Throwing a RuntimeException triggers an automatic rollback of the transaction
            throw new RuntimeException("Insufficient funds in account: " + fromAcc);
        }
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        accountDao.updateAccount(fromAccount);

        // 2. SIMULATE FAILURE (Uncomment to test explicit rollback)
        // if (fromAcc.equals("1001")) throw new RuntimeException("Simulated Network Failure!");

        // 3. Add money
        Account toAccount = accountDao.getAccountByNumber(toAcc);
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountDao.updateAccount(toAccount);

        System.out.println("Transfer successful!");
    }

    @Transactional
    public void setupAccounts(Account acc1, Account acc2) {
        accountDao.saveAccount(acc1);
        accountDao.saveAccount(acc2);
        System.out.println("Accounts setup complete.");
    }

    @Transactional(readOnly = true)
    public double getBalance(String accountNumber) {
        return accountDao.getAccountByNumber(accountNumber).getBalance();
    }
}