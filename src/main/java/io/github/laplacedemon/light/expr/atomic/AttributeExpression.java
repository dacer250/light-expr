package io.github.laplacedemon.light.expr.atomic;

import io.github.laplacedemon.light.expr.IEvalHandler;

import java.util.List;

public class AttributeExpression extends SymbolExpression {

    private List<String> symbolList;

    static IEvalHandler evalHandler;
    
//    private AttributeExpression(String value) {
//        super(value);
//    }


    public List<String> getSymbolList() {
        return symbolList;
    }

    public void setSymbolList(List<String> symbolList) {
        this.symbolList = symbolList;
    }

    public static IEvalHandler getEvalHandler() {
        return evalHandler;
    }

    public static void setEvalHandler(IEvalHandler ie) {
        evalHandler = ie;
    }

    AttributeExpression(List<String> symbolList) {
        super("");
        this.symbolList = symbolList;
    }
    
    @Override
    public String toString() {
        return this.symbolList.toString();
    }
    @Override
    public Object eval(){
        try {
            return evalHandler!=null? evalHandler.doEval(this):null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}

