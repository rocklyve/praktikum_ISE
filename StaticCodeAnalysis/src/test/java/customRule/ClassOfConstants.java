package customRule;

import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTFieldDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class ClassOfConstants extends AbstractJavaRule {
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

        if (fieldCount == constantCount) {
            addViolation(data, node);
        }

        return super.visit(node, data);
    }
}