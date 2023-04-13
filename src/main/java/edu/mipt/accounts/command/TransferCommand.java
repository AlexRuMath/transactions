package edu.mipt.accounts.command;

import edu.mipt.accounts.Account;

public class TransferCommand implements Command {
    private final Account from;
    private final Account to;
    private final long value;

    public TransferCommand(Account from, Account to, long value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

    @Override
    public void exec() {
        from.withdraw(value);
        to.deposit(value);
    }
}
