package io.github.laplacedemon.light.expr.pair;

import io.github.laplacedemon.light.expr.ExpressionBuilder;

public class ExpoExpressionBuilder extends ExpressionBuilder {
    public ExpoExpressionBuilder(char ch) {
        super(ch);
    }
    
    @Override
    public boolean append(char ch) {
        return false;
    }

    @Override
    public ExpoExpression build() {
        ExpoExpression ee= new ExpoExpression();
        return ee;
    }
}
