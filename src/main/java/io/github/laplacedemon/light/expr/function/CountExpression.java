package io.github.laplacedemon.light.expr.function;

import io.github.laplacedemon.light.expr.ItemExpression;
import io.github.laplacedemon.light.expr.atomic.PriorityExpression;
import io.github.laplacedemon.light.expr.util.IncomputableException;

@Deprecated
public class CountExpression extends ItemExpression {
    private PriorityExpression priorityExpression;
    
    public CountExpression(PriorityExpression priorityExpression) {
        this.priorityExpression = priorityExpression;
    }
    
    @Override
    public String toString() {
        return "count < " + priorityExpression.toString() + " >";
    }

    @Override
    public Object eval() throws IncomputableException {
        throw new IncomputableException();
    }
}
