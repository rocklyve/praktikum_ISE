package pmdModel;

import java.util.Objects;

public class PMDTestViolation {
    public int beginline;
    public int begincolumn;
    public int endline;
    public int endcolumn;
    public String description;
    public String rule;
    public String ruleset;
    public int priority;
    public String externalInfoUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PMDTestViolation that = (PMDTestViolation) o;
        return beginline == that.beginline &&
                begincolumn == that.begincolumn &&
                endline == that.endline &&
                endcolumn == that.endcolumn &&
                priority == that.priority &&
                Objects.equals(description, that.description) &&
                Objects.equals(rule, that.rule) &&
                Objects.equals(ruleset, that.ruleset) &&
                Objects.equals(externalInfoUrl, that.externalInfoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beginline, begincolumn, endline, endcolumn, description, rule, ruleset, priority, externalInfoUrl);
    }
}