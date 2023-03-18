package customPMDRule;

import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTConstructorDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTFieldDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * A PMD rule that detects classes that contain only constant fields and no methods.
 * This rule visits an ASTClassOrInterfaceDeclaration node and counts the number of fields and constant
 * fields in the class. If the class has no methods and all fields are constant, a violation is added to the report.
 */
public class ClassOfConstants extends AbstractJavaRule {
    /**
     * Visits an ASTClassOrInterfaceDeclaration node and counts the number of fields and constant fields
     * in the class. If the class has no methods and all fields are constant, a violation is added to the report.
     * @param node the ASTClassOrInterfaceDeclaration node to visit
     * @param data the context in which the node is being visited
     * @return the result of visiting the node
     */
    @Override
    public Object visit(ASTClassOrInterfaceDeclaration node, Object data) {
        int fieldCount = 0;
        int constantCount = 0;

        for (ASTFieldDeclaration field : node.findDescendantsOfType(ASTFieldDeclaration.class)) {
            fieldCount++;
            if (field.isFinal() && field.isStatic()) {
                constantCount++;
            }
        }
        
        boolean containsConstructor = !node.findDescendantsOfType(ASTConstructorDeclaration.class).isEmpty();
        boolean containsMethods = !node.findDescendantsOfType(ASTMethodDeclaration.class).isEmpty();
        if (!containsConstructor
                && !containsMethods
                && fieldCount != 0
                && fieldCount == constantCount) {
            addViolation(data, node);
        }

        return super.visit(node, data);
    }
}