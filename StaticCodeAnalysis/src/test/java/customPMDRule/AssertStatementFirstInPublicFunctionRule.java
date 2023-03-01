package customPMDRule;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTAssertStatement;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * This rule checks if there is an assert statement as the first statement in a public function.
 * The ASTMethodDeclaration node has three children: ResultType, MethodDeclarator, and a Block, containing the MethodBody.
 * Inside the Block, every statement is packed into a BlockStatement. The rule checks if the method is public and
 * if there is a first statement. If the first statement is an instance of an ASTAssertStatement, a violation is added.
 */
public class AssertStatementFirstInPublicFunctionRule extends AbstractJavaRule {
    /**
     * Visits a method declaration node and checks if it violates the rule.
     *
     * If the method is public and has at least one statement, checks if the first statement
     * is an assert statement. If it is, a violation is added.
     *
     * @param node the ASTMethodDeclaration node to visit
     * @param data the data passed from the parent node
     * @return the result of visiting this node
     */
    @Override
    public Object visit(ASTMethodDeclaration node, Object data) {
        // Check if the method is public and check if there is a first statement
        // the node inside a ASTMethodDeclaration has 3 children,
        // the ResultType, the MethodDeclarator and a Block, containing the MethodBody
        // inside the Block, every statement is packed into a BlockStatement,
        // therefore we have to get the child of the BlockStatement
         if (node.isPublic()
                && node.getNumChildren() > 2
                && node.getChild(2).getNumChildren() > 0
                && node.getChild(2).getChild(0).getNumChildren() > 0
        ) {
            // Get the first statement of the method
            Node firstChild = node.getChild(2).getChild(0).getChild(0);
            if (firstChild instanceof ASTAssertStatement) {
                addViolation(data, node);
            }
        }
        return super.visit(node, data);
    }
}
