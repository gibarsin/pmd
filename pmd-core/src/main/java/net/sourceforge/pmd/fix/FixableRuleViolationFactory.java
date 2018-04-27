package net.sourceforge.pmd.fix;

import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.ast.Node;

public interface FixableRuleViolationFactory {
    void addViolationWithFix(RuleContext ruleContext, Rule rule, Node node, RuleViolationFix fix, String message, Object[] args);

    void addViolationWithFix(RuleContext ruleContext, Rule rule, Node node, RuleViolationFix fix, String message, int beginLine, int endLine, Object[] args);
}
