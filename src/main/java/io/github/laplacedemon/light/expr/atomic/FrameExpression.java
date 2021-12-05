package io.github.laplacedemon.light.expr.atomic;

import io.github.laplacedemon.light.expr.BaseExpression;
import io.github.laplacedemon.light.expr.ItemExpression;
import io.github.laplacedemon.light.expr.util.IncomputableException;

//[]
public class FrameExpression extends ItemExpression {
    private String symbol;
    private BaseExpression baseExpression;

    FrameExpression(String sym,BaseExpression baseExpression) {
        this.baseExpression = baseExpression;
        this.symbol=sym;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return baseExpression.toString();
    }

    @Override
    public Object eval() throws IncomputableException {
        return baseExpression.eval();
    }

	public BaseExpression getBaseExpression() {
		return baseExpression;
	}

    @Override
    public Object doEval(BaseExpression be) throws Exception {
        return handler.doEval(be);
    }
}
