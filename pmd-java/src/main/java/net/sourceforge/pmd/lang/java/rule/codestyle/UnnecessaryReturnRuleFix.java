/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.rule.codestyle;

import net.sourceforge.pmd.lang.java.ast.ASTReturnStatement;
import net.sourceforge.pmd.lang.rule.fix.RuleViolationFix;

public class UnnecessaryReturnRuleFix implements RuleViolationFix {
    private final ASTReturnStatement returnStatement;

    public UnnecessaryReturnRuleFix(final ASTReturnStatement returnStatement) {
        this.returnStatement = returnStatement;
    }

    @Override
    public void fix() {
        returnStatement.jjtGetParent().remove();
    }
}
