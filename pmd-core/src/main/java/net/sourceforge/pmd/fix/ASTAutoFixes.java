package net.sourceforge.pmd.fix;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public enum ASTAutoFixes {
    INSTANCE;

    private AutoFixesApplier applier = null;

    public void setAutoFixes(boolean isAutoFixes) {
        if (isAutoFixes) {
            applier = AutoFixesApplierImp.INSTANCE;
        } else {
            applier = NoopAutoFixesApplier.INSTANCE;
        }
    }

    public void addFix(final String fileName, final RuleViolationFix fix) {
        applier.addFix(fileName, fix);
    }

    public void applyFixesToAST(final String fileName) {
        applier.applyFixesToAST(fileName);
    }

    public boolean areFixesAppliedToAST(final String fileName) {
        return applier.areFixesAppliedToAST(fileName);
    }

    private interface AutoFixesApplier {
        void addFix(String fileName, RuleViolationFix fix);

        void applyFixesToAST(String fileName);

        boolean areFixesAppliedToAST(String fileName);
    }

    private enum NoopAutoFixesApplier implements AutoFixesApplier {
        INSTANCE;

        @Override
        public void addFix(final String fileName, final RuleViolationFix fix) {
            // Noop
        }

        @Override
        public void applyFixesToAST(final String fileName) {
            // Noop
        }

        @Override
        public boolean areFixesAppliedToAST(final String fileName) {
            return false;
        }
    }

    private enum AutoFixesApplierImp implements AutoFixesApplier {
        INSTANCE;

        private final ConcurrentMap<String, Queue<RuleViolationFix>> fileNameToFixes = new ConcurrentHashMap<>();

        @Override
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

        @Override
        public void applyFixesToAST(final String fileName) {
            final Queue<RuleViolationFix> fixesToApply = fileNameToFixes.get(fileName);
            if(fixesToApply != null) {
                while (!fixesToApply.isEmpty()) {
                    fixesToApply.poll().apply();
                }
            }
        }

        @Override
        public boolean areFixesAppliedToAST(final String fileName) {
            return fileNameToFixes.containsKey(fileName);
        }
    }
}
