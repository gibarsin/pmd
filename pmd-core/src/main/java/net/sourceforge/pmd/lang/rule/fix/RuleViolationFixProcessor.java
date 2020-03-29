/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.rule.fix;

/**
 *
 */
public interface RuleViolationFixProcessor {
    static RuleViolationFixProcessor getInstance() {
        return RuleViolationFixProcessorImp.INSTANCE;
    }


    void addRuleViolationFix(RuleViolationFix rvf);

    void process();
}
