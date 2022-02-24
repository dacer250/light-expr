package io.github.laplacedemon.light.expr.atomic;

import io.github.laplacedemon.light.expr.BaseExpression;
import io.github.laplacedemon.light.expr.ItemExpression;
import io.github.laplacedemon.light.expr.util.IncomputableException;
import java.util.List;

public class BlockExpression extends ItemExpression {
    private List<BaseExpression> baseExpression;

    BlockExpression(List<BaseExpression> baseExpression) {
        this.baseExpression = baseExpression;
    }
    
    @Override
    public String toString() {
        return baseExpression.toString();
    }

    @Override
    public Object eval() throws IncomputableException {
        throw new UnsupportedOperationException();
    }

	public List<BaseExpression> getBaseExpression() {
		return baseExpression;
	}
    
}
