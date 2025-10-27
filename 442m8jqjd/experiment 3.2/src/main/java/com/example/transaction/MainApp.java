package com.example.transaction;

import com.example.config.TransactionAppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TransactionAppConfig.class);

        AccountService accountService = context.getBean(AccountService.class);

        // Initial Setup (will clean and recreate accounts due to hbm2ddl.auto=create)
        accountService.setupAccounts(
                new Account("1001", 1000.00),
                new Account("1002", 500.00)
        );

        System.out.println("--- Part C: Transaction Management Demonstration ---");
        System.out.printf("Initial Balances: 1001=$%.2f, 1002=$%.2f\n",
                accountService.getBalance("1001"), accountService.getBalance("1002"));

        // --- Test 1: Successful Transfer ---
        try {
            accountService.transferFunds("1001", "1002", 100.00);
            System.out.println("Transaction 1 Status: SUCCESS.");
        } catch (Exception e) {
            System.err.println("Transaction 1 Status: FAILED. Error: " + e.getMessage());
        }

        // --- Test 2: Rollback Test (Simulate Insufficient Funds) ---
        // This transaction will fail, and the $100 deduction will be rolled back.
        try {
            accountService.transferFunds("1001", "1002", 2000.00);
            System.out.println("Transaction 2 Status: SUCCESS (UNEXPECTED).");
        } catch (Exception e) {
            System.err.println("Transaction 2 Status: FAILED (Expected Rollback). Error: " + e.getMessage());
        }

        // Check final balances
        System.out.printf("\nFinal Balances: 1001=$%.2f, 1002=$%.2f\n",
                accountService.getBalance("1001"), accountService.getBalance("1002"));

        context.close();
        System.out.println("----------------------------------------------------");
    }
}