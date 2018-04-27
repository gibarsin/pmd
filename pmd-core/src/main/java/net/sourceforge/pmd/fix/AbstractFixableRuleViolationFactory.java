package net.sourceforge.pmd.fix;

import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.rule.AbstractRuleViolationFactory;

public abstract class AbstractFixableRuleViolationFactory extends AbstractRuleViolationFactory implements FixableRuleViolationFactory {

    @Override
    public void addViolationWithFix(final RuleContext ruleContext, final Rule rule, final Node node, final RuleViolationFix fix,
                                    String message, final Object[] args) {
        super.addViolation(ruleContext, rule, node, message, args);
        ASTFixes.INSTANCE.addFix(ruleContext.getSourceCodeFilename(), fix);
    }

    @Override
    public void addViolationWithFix(final RuleContext ruleContext, final Rule rule, final Node node,
                                    final RuleViolationFix fix, String message, final int beginLine, final int endLine,
                                    final Object[] args) {
        super.addViolation(ruleContext, rule, node, message, args);
        ASTFixes.INSTANCE.addFix(ruleContext.getSourceCodeFilename(), fix);
    }
}
