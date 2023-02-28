package customPMDRule;

import net.sourceforge.pmd.lang.java.ast.ASTCompilationUnit;
import net.sourceforge.pmd.lang.java.ast.ASTLiteral;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class LineBreakRule extends AbstractJavaRule {
    @Override
    public Object visit(ASTCompilationUnit node, Object data) {
        for (ASTLiteral literal : node.findDescendantsOfType(ASTLiteral.class)) {
            if (literal.getImage() != null) {
                String[] lines = literal.getImage().split("\\r?\\n|\\r");
                for (String line: lines) {
                    if (line.contains("\\r") || line.contains("\\n")) {
                        addViolation(data, literal);
                    }
                }
            }
        }
        return super.visit(node, data);
    }
}
