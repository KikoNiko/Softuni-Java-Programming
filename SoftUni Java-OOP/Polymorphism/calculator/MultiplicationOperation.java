package calculator;

import java.util.ArrayList;

public class MultiplicationOperation extends SimpleOperation {

    public MultiplicationOperation(){
        this.operands = new ArrayList<>();
    }
    @Override
    public void addOperand(int operand) {
        this.operands.add(operand);

        if (this.isCompleted()) {
            this.result = this.operands.get(0) * this.operands.get(1);
        }
    }

}
