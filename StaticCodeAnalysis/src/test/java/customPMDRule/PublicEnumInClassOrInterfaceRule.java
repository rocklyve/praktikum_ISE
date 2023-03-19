package customPMDRule;

import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTEnumDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * A PMD rule that detects public enum declarations inside a class or interface.
 * This rule checks if an ASTEnumDeclaration node is marked as public and its parent node
 * is an ASTClassOrInterfaceBodyDeclaration. If so, it adds a violation to the report.
 */
public class PublicEnumInClassOrInterfaceRule extends AbstractJavaRule {
    /**
    * Visits an ASTEnumDeclaration node and checks if it is marked as public and has a parent node of type
    * ASTClassOrInterfaceBodyDeclaration. If it does, a violation is added to the report.
    * @param node the ASTEnumDeclaration node to visit
    * @param data the context in which the node is being visited
    * @return the result of visiting the node
    */
    @Override
    public Object visit(ASTEnumDeclaration node, Object data) {
        if (node.isPublic() && node.getParent() instanceof ASTClassOrInterfaceBodyDeclaration) {
            addViolation(data, node);
        }
        return super.visit(node, data);
    }
}