package customPMDRule;

import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTEnumDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class PublicEnumInClassOrInterfaceRule extends AbstractJavaRule {
    @Override
    public Object visit(ASTEnumDeclaration node, Object data) {
        if (node.isPublic() && node.getParent() instanceof ASTClassOrInterfaceBodyDeclaration) {
            addViolation(data, node);
        }
        return super.visit(node, data);
    }
}