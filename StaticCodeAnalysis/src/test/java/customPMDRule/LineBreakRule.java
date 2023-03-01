package customPMDRule;

import net.sourceforge.pmd.lang.java.ast.ASTCompilationUnit;
import net.sourceforge.pmd.lang.java.ast.ASTLiteral;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * A PMD rule that detects line breaks within a single Java string literal.
 * This rule visits an ASTCompilationUnit node and looks for ASTLiteral nodes that contain
 * a Java string literal. If the literal contains line breaks, it adds a violation to the report.
 */
public class LineBreakRule extends AbstractJavaRule {
    /**
     * Visits an ASTCompilationUnit node and looks for ASTLiteral nodes that contain a Java string literal.
     * If the literal contains line breaks, a violation is added to the report.
     * @param node the ASTCompilationUnit node to visit
     * @param data the context in which the node is being visited
     * @return the result of visiting the node
     */
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
