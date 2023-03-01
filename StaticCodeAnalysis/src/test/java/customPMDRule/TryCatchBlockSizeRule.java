package customPMDRule;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBlock;
import net.sourceforge.pmd.lang.java.ast.ASTStatement;
import net.sourceforge.pmd.lang.java.ast.ASTTryStatement;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

import java.util.Arrays;
import java.util.List;

public class TryCatchBlockSizeRule extends AbstractJavaRule {
    private static final int MAX_LENGTH = 10;
    private static final List<String> IRRELEVANT_STATEMENTS = Arrays.asList("System.out.println", "logger.info");

    @Override
    public Object visit(ASTTryStatement node, Object data) {
        int length = node.jjtGetNumChildren();
        if (length > MAX_LENGTH) {
            int relevantStatements = 0;
            for (int i = 0; i < length; i++) {
                Node child = node.jjtGetChild(i);
                if (!(child instanceof ASTBlock)) {
                    relevantStatements++;
                } else {
                    for (int j = 0; j < child.jjtGetNumChildren(); j++) {
                        Node grandchild = child.jjtGetChild(j);
                        if (!(grandchild instanceof ASTStatement)) {
                            continue;
                        }
                        ASTStatement statement = (ASTStatement) grandchild;
                        if (isRelevant(statement)) {
                            relevantStatements++;
                        }
                    }
                }
            }
            if (relevantStatements <= MAX_LENGTH) {
                addViolation(data, node);
            }
        }
        return super.visit(node, data);
    }

    private boolean isRelevant(ASTStatement statement) {
        String code = statement.toString();
        for (String irrelevantStatement : IRRELEVANT_STATEMENTS) {
            if (code.contains(irrelevantStatement)) {
                return false;
            }
        }
        return true;
    }
}
