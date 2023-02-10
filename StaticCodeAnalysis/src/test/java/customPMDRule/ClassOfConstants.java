package customPMDRule;

import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTFieldDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class ClassOfConstants extends AbstractJavaRule {
    @Override
    public Object visit(ASTClassOrInterfaceDeclaration node, Object data) {
        int fieldCount = 0;
        int constantCount = 0;
        int methodCount = 0;

        for (ASTFieldDeclaration field : node.findDescendantsOfType(ASTFieldDeclaration.class)) {
            fieldCount++;
            if (field.isFinal() && field.isStatic()) {
                constantCount++;
            }
        }

        for(ASTMethodDeclaration method : node.findDescendantsOfType(ASTMethodDeclaration.class)) {
            methodCount++;
        }

        if (fieldCount != 0 && methodCount == 0 && fieldCount == constantCount) {
            addViolation(data, node);
        }

        return super.visit(node, data);
    }
}