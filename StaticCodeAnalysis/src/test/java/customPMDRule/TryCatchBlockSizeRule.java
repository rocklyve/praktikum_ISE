package customPMDRule;

import net.sourceforge.pmd.lang.java.ast.ASTTryStatement;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * A PMD rule that detects try-catch blocks that exceed a specified maximum length of the try block.
 *
 * The maximum length is set to {@value TryCatchBlockSizeRule#MAX_LENGTH}.
 */
public class TryCatchBlockSizeRule extends AbstractJavaRule {
    /**
    * The maximum length for try-catch blocks. If you modify this value, please also adjust
    * the value in the custom-pmd-ruleset.xml file in the description of the rule.
    */
    private static final int MAX_LENGTH = 20;

    /**
     Visits an ASTTryStatement node and checks if the length of the try block exceeds the maximum length.
     If it does, a violation is added to the report.
     @param node the ASTTryStatement node to visit
     @param data the context in which the node is being visited
     @return the result of visiting the node
     */
    @Override
    public Object visit(ASTTryStatement node, Object data) {
        int length = node.getChild(0).getNumChildren();
        if (length > MAX_LENGTH) {
            addViolation(data, node);
        }
        return super.visit(node, data);
    }
}
