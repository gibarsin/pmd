package net.sourceforge.pmd.fix;

import net.sourceforge.pmd.lang.ast.AbstractNode;

import java.util.Objects;

public abstract class RuleViolationFix {
    private final AbstractNode node;

    public RuleViolationFix(final AbstractNode node) {
        this.node = Objects.requireNonNull(node);
    }

    public abstract void apply();
}
