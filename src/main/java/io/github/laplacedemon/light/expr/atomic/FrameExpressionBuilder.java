package io.github.laplacedemon.light.expr.atomic;

import io.github.laplacedemon.light.expr.BaseExpression;
import io.github.laplacedemon.light.expr.ExpressionBuilder;
import io.github.laplacedemon.light.expr.parse.ParseExpressionException;
import io.github.laplacedemon.light.expr.parse.Parser;

public class FrameExpressionBuilder extends ExpressionBuilder {
    private boolean closed = false;
    private int num = 0;

    public FrameExpressionBuilder(char ch) {
        super();
    }

    @Override
    public boolean append(char ch) {
        
        if(closed) {
            return false;
        } 
        
        if(num == 0) {
            if(ch == ']') {
                this.closed = true;
                return true;
            }
        }
        
        if(ch == '[') {
            num++;
        } else if (ch == ']') {
            num--;
        }
        
        
        super.append(ch);
        return true;
    }
    
    public FrameExpression build() throws ParseExpressionException {
        String value = super.value();
        String[]arr =value.split(":");
        if(arr==null || arr.length!=2){
            throw new ParseExpressionException("过滤操作符失败");
        }

        Parser parser = new Parser(arr[1]);
        BaseExpression baseExpression = parser.parse();
        FrameExpression frameExpression = new FrameExpression(arr[0].trim(), baseExpression);
        return frameExpression;
    }

}
