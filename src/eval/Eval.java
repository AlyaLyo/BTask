package eval;

import statement.StatementNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Eval {
    class NameAndInd {
        public String name;
        public ArrayList<Integer> inds = new ArrayList<>();

        public NameAndInd(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name + ' ' + inds.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != this.getClass()) {
                return false;
            }

            return name.equals(((NameAndInd) obj).name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    private final StatementNode statementNode;
    private final ArrayList<StatementNode> vars;

    public Eval(StatementNode statementNode, ArrayList<StatementNode> vars) {
        this.statementNode = statementNode;
        this.vars = vars;
    }

    private boolean[] variant;
    private List<boolean[]> allVariants = new ArrayList<>();

    private void generateVariants(int curr, int max) {
        if (curr == max) {
            boolean[] newV = new boolean[max];
            for (int i = 0; i < max; i++) {
                newV[i] = variant[i];
            }
            allVariants.add(newV);
            return;
        }

        variant[curr] = true;
        generateVariants(curr + 1, max);
        variant[curr] = false;
        generateVariants(curr + 1, max);
    }

    private List<boolean[]> getAllVariants(int valuesCount) {
        variant = new boolean[valuesCount];
        generateVariants(0, valuesCount);

        return allVariants;
    }

    public void eval() {
        List<NameAndInd> varNames = vars.stream().map(var -> new Eval.NameAndInd(var.name)).collect(Collectors.toSet()).stream().toList();

        varNames.forEach(varName -> {
            for (int i = 0; i < vars.size(); i++) {
                if (vars.get(i).name.equals(varName.name)) {
                    varName.inds.add(i);
                }
            }
        });

        System.out.println(varNames.toString());
        List<boolean[]> variants = getAllVariants(varNames.size());
        List<Boolean> results = new ArrayList<>();
        variants.forEach(variant -> {
            for (int i = 0; i < variant.length; i++) {
                int finalI = i;
                varNames.get(i).inds.forEach(ind -> vars.get(ind).value = variant[finalI]);
            }

            results.add(statementNode.exec());
        });

        System.out.println(results);

        int t = (int) results.stream().filter(res -> res).count();

        if (t == 0) {
            System.out.println("Unsatisfiable");
            return;
        }
        if (t == results.size()) {
            System.out.println("Valid");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Satisfiable and invalid, ").append(t).append(" true and ").append(results.size() - t).append(" false cases");
        System.out.println(sb.toString());
    }

}
