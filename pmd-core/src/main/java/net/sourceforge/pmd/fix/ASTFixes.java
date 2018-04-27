package net.sourceforge.pmd.fix;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public enum ASTFixes {
    INSTANCE;

    private final Map<String, Queue<RuleViolationFix>> fileNameToFixes = new HashMap<>();

    public void addFileName(final String fileName) {
        if (fileNameToFixes.containsKey(Objects.requireNonNull(fileName))) {
            throw new IllegalArgumentException("File name already exists in FilesRuleViolationsWithFixes: " + fileName);
        }
        fileNameToFixes.put(fileName, new LinkedList<RuleViolationFix>());
    }

    public void addViolation(final String fileName, final RuleViolationFix fix) {
        final Queue<RuleViolationFix> tuples = getApplicableFixes(fileName);
        tuples.offer(fix);
    }

    private Queue<RuleViolationFix> getApplicableFixes(final String fileName) {
        final Queue<RuleViolationFix> tuples = fileNameToFixes.get(fileName);
        if (tuples == null) {
            throw new IllegalArgumentException("File name does not exist in FilesRuleViolationsWithFixes: " + fileName);
        }
        return tuples;
    }

    public void applyFixesToFileAST(final String fileName) {
        final Queue<RuleViolationFix> fixesToApply = getApplicableFixes(fileName);
        while (!fixesToApply.isEmpty()) {
            fixesToApply.poll().apply();
        }
    }
}
