package customPMDRule;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTStatement;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class AssertStatementFirstInPublicFunctionRule extends AbstractJavaRule {
    @Override
    public Object visit(ASTMethodDeclaration node, Object data) {
        if (!node.isPublic()) {
            return data;
        }
        // here > 0
        if (node.jjtGetNumChildren() == 1) {
            Node firstStatement = node.jjtGetChild(0);
            if (firstStatement instanceof ASTStatement) {
                // TODO: can we check, if this is an assert statement?
                if (firstStatement.getImage().startsWith("assert")) {
                    addViolation(data, firstStatement);
                }
            }
        }
        return super.visit(node, data);
    }
}
