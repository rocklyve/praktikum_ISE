package customPMDRule;

import net.sourceforge.pmd.lang.java.ast.ASTTryStatement;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class TryCatchBlockSizeRule extends AbstractJavaRule {
    // NOTE: If you modify the {@value MAX_LENGTH} value,
    // please make sure to also adjust the value in the custom-pmd-ruleset.xml file
    // in the description of the rule
    private static final int MAX_LENGTH = 20;
    @Override
    public Object visit(ASTTryStatement node, Object data) {
        // inside the ASTTryStatement node, there is a child node that contains
        // all the content of the try block in the first child,
        // so we have to count the number of elements inside the try block
        int length = node.getChild(0).getNumChildren();
        if (length > MAX_LENGTH) {
            addViolation(data, node);
        }
        return super.visit(node, data);
    }
}
