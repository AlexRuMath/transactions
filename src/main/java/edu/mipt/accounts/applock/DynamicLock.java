package edu.mipt.accounts.applock;

import edu.mipt.accounts.command.Command;

public class DynamicLock implements Lock {
    private static final Object freeLock = new Object();

    @Override
    public void exec(Command command, Object firstObj, Object secondObj) {
        int firstHash = firstObj.hashCode();
        int secondHash = secondObj.hashCode();

        if (firstHash < secondHash) {
            lock(firstObj, secondObj, command);
            return;
        }

        if (secondHash < firstHash) {
            lock(secondObj, firstObj, command);
            return;
        }

        synchronized (freeLock) {
            lock(firstObj, secondObj, command);
        }
    }

    private void lock(Object externalLock, Object internalLock, Command command) {
        synchronized (externalLock) {
            synchronized (internalLock) {
                command.exec();
            }
        }
    }
}
