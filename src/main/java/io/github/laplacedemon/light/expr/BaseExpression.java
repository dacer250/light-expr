package io.github.laplacedemon.light.expr;

import io.github.laplacedemon.light.expr.parse.ParseExpressionException;
import io.github.laplacedemon.light.expr.util.IncomputableException;

public abstract class BaseExpression implements IEvalHandler {
   public IEvalHandler handler;

    public abstract BaseExpression join(BaseExpression baseExpression) throws ParseExpressionException;

    //3+5 ,3*5 3+2+2; x=3,x+3
    public abstract Object eval() throws IncomputableException;

    @Override
    public Object doEval(BaseExpression be) throws Exception{
       return handler==null?be.eval(): handler.doEval(be);
    }

}
