package calculator;

import java.util.List;

public class SimpleOperation implements Operation {
    protected List<Integer> operands;
    protected int result;

    @Override
    public void addOperand(int operand) {

    }

    @Override
    public boolean isCompleted() {
        return this.operands.size() == 2;
    }

    @Override
    public int getResult() {
        return this.result;
    }
}
