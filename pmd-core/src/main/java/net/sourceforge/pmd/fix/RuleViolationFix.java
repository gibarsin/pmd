package net.sourceforge.pmd.fix;

import net.sourceforge.pmd.lang.ast.AbstractNode;

import java.util.Objects;

/**
 * This IS GOING TO change, this is just to be used in {@link ASTAutoFixes}.
 */
public abstract class RuleViolationFix {
    private final AbstractNode node;

    public RuleViolationFix(final AbstractNode node) {
        this.node = Objects.requireNonNull(node);
    }

    public abstract void apply();
}
