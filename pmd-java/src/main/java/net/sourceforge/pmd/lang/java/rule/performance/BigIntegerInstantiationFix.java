package net.sourceforge.pmd.lang.java.rule.performance;

import net.sourceforge.pmd.lang.ast.DatumToken;
import net.sourceforge.pmd.lang.java.ast.ASTAllocationExpression;
import net.sourceforge.pmd.lang.java.ast.ASTLiteral;
import net.sourceforge.pmd.lang.java.ast.ASTName;

/* default */ final class BigIntegerInstantiationFix {
    private final ASTAllocationExpression node;

    /* default */ BigIntegerInstantiationFix(final ASTAllocationExpression node) {
        this.node = node;
    }

    /* default */ void fixZeroOrOne() {
        String number = getNumber();
        ASTName astName = new ASTName(-1);
        astName.jjtSetFirstToken(new DatumToken.Builder().withImage("BigInteger" + "." + number).build());
        astName.jjtSetLastToken(new DatumToken.Builder().build());
        node.beReplacedBy(astName);
    }

    private String getNumber() {
        final String number = node.getFirstDescendantOfType(ASTLiteral.class).getImage();
        switch (number) {
            case "0": return "ZERO";
            case "1": return "ONE";
            default: throw new IllegalArgumentException("Should have never been brought to this fix");
        }
    }

    /* default */ void fixTen() {
        ASTName astName = new ASTName(-1);
        astName.jjtSetFirstToken(new DatumToken.Builder().withImage("BigInteger" + "." + "TEN").build());
        astName.jjtSetLastToken(new DatumToken.Builder().build());
        node.beReplacedBy(astName);
    }
}
