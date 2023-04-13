package edu.mipt.accounts.applock;

import edu.mipt.accounts.AccountRepository;
import edu.mipt.accounts.Accounts;
import edu.mipt.accounts.command.TransferCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AppSynchronizedAccounts implements Accounts {
    private final AccountRepository accountRepository;
    private final Lock lock = new DynamicLock();

    @Override
    public void transfer(long fromAccountId, long toAccountId, long amount) {
        var fromAccount = accountRepository.findById(fromAccountId);
        var toAccount = accountRepository.findById(toAccountId);

        var transferCommand = new TransferCommand(fromAccount, toAccount, amount);
        lock.exec(transferCommand, fromAccount, toAccount);
    }
}