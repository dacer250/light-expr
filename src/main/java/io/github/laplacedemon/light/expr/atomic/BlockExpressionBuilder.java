package io.github.laplacedemon.light.expr.atomic;

import io.github.laplacedemon.light.expr.BaseExpression;
import io.github.laplacedemon.light.expr.ExpressionBuilder;
import io.github.laplacedemon.light.expr.parse.ParseExpressionException;
import io.github.laplacedemon.light.expr.parse.Parser;
import java.util.ArrayList;
import java.util.List;

public class BlockExpressionBuilder extends ExpressionBuilder {
    private boolean closed = false;
    private int num = 0;

    public BlockExpressionBuilder(char ch) {
        super();
    }

    @Override
    public boolean append(char ch) {
        
        if(closed) {
            return false;
        } 
        
        if(num == 0) {
            if(ch == '}') {
                this.closed = true;
                return true;
            }
        }
        
        if(ch == '{') {
            num++;
        } else if (ch == '}') {
            num--;
        }
        
        
        super.append(ch);
        return true;
    }
    
    @Override
    public BlockExpression build() throws ParseExpressionException {
        String value = super.value();
        String[] arr =value.split(";");
        List<BaseExpression> list =new ArrayList<>();
        for(String str:arr){
            Parser parser = new Parser(str);
            BaseExpression baseExpression = parser.parse();
            list.add(baseExpression);
        }

        BlockExpression priorityExpression = new BlockExpression(list);
        return priorityExpression;
    }

}
