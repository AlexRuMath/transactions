package edu.mipt.accounts.applock;

import edu.mipt.accounts.command.Command;

public interface Lock {
    void exec(Command command, Object firstObj, Object secondObj);
}
