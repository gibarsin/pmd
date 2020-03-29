/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.rule.fix;

import java.util.Collection;
import java.util.LinkedList;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 *
 */
/* default */ final class RuleViolationFixProcessorImp implements RuleViolationFixProcessor {
    public static final RuleViolationFixProcessor INSTANCE = new RuleViolationFixProcessorImp(new LinkedList<>());

    private final Collection<RuleViolationFix> ruleViolationFixes;

    private RuleViolationFixProcessorImp(final Collection<RuleViolationFix> ruleViolationFixes) {
        this.ruleViolationFixes = ruleViolationFixes;
    }

    @Override
    public void addRuleViolationFix(@NonNull final RuleViolationFix rvf) {
        ruleViolationFixes.add(rvf);
    }

    @Override
    public void process() {
        ruleViolationFixes.forEach(RuleViolationFix::fix);
    }
}
