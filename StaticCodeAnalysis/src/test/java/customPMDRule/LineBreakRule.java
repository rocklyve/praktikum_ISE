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
                /**
                 * The split() method is called on the literal.getImage() string,
                 * which contains the actual content of the Java string literal.
                 * The regular expression "\\r?\\n|\\r" is used as the separator pattern for splitting the string.
                 * This pattern matches any combination of a carriage return (\r) followed by a newline (\n)
                 * or just a carriage return (\r). This allows the method to handle different line break conventions
                 * that might be used on different operating systems.
                 */
                String[] lines = literal.getImage().split("\\r?\\n|\\r");
                for (String line: lines) {
                    if (line.contains("\\r") || line.contains("\\n") || line.contains("\\s")) {
                        addViolation(data, literal);
                    }
                }
            }
        }
        return super.visit(node, data);
    }
}
