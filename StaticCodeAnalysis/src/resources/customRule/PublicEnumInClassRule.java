package src.resources.customRule;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTEnumDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class PublicEnumInClassRule extends AbstractJavaRule {
    public Object visit(ASTClassOrInterfaceDeclaration node, Object data) {
        for (Node child : node.children()) {
            if (child instanceof ASTEnumDeclaration && ((ASTEnumDeclaration) child).isPublic()) {
                addViolation(data, child);
            }
        }
        return data;
    }
}