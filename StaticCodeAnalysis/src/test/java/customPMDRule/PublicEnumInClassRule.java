package customPMDRule;

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
        return super.visit(node, data);
    }

    // todo: use visit over all enums, look if they are public and if they are in a class.
}