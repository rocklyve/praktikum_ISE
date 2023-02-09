package src.resources.customRule;

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
        if (node.jjtGetNumChildren() == 1) {
            Node firstStatement = node.jjtGetChild(0);
            if (firstStatement instanceof ASTStatement) {
                if (firstStatement.getImage().startsWith("assert")) {
                    addViolation(data, firstStatement);
                }
            }
        }
        return super.visit(node, data);
    }
}
