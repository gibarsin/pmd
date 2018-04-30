package net.sourceforge.pmd.fix;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public enum ASTFixes {
    INSTANCE;

    private final ConcurrentMap<String, Queue<RuleViolationFix>> fileNameToFixes = new ConcurrentHashMap<>();

    public void addFix(final String fileName, final RuleViolationFix fix) {
        Objects.requireNonNull(fix); // Avoid potentially creating a new queue if the fix is null
        final Queue<RuleViolationFix> potentialQueue = new ArrayDeque<>();
        final Queue<RuleViolationFix> existingQueue = fileNameToFixes.putIfAbsent(fileName, potentialQueue);
        if (existingQueue != null) {
            existingQueue.offer(fix);
        } else {
            potentialQueue.offer(fix);
        }
    }

    public void applyFixesToAST(final String fileName) {
        final Queue<RuleViolationFix> fixesToApply = fileNameToFixes.get(fileName);
        while (fixesToApply != null && !fixesToApply.isEmpty()) {
            fixesToApply.poll().apply();
        }
    }

    public boolean areFixesAppliedToAST(final String fileName) {
        return fileNameToFixes.containsKey(fileName);
    }
}
