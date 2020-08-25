package com.Trees.BinaryTrees;
import com.Stack.Stack;

import java.util.*;
import java.util.function.*;

public class ExpressionTree extends BinaryTree<Character> {
    private Map<Character,Integer> operators;
    private Map<Character,BiFunction<Double,Double,Double>> operatorFunction;

    public ExpressionTree(String expression){
        super();
        initializeMappings();
        expression=infixToPostfix(expression);
        root=createTree(expression);
    }

    private void initializeMappings() {
        operators =new HashMap<>();
        operatorFunction=new HashMap<>();
        operators.put('+',1);
        operators.put('-',1);
        operators.put('*',2);
        operators.put('/',2);
        operatorFunction.put('+',this::add);
        operatorFunction.put('-',this::subtract);
        operatorFunction.put('*',this::multiply);
        operatorFunction.put('/',this::divide);

    }

    public double evaluate(){
        return evaluateExpression(root,0);

    }

    private double evaluateExpression(TreeNode<Character> current,double result){
        if(current==null)return 0;
        if(current.isLeaf()) return Double.parseDouble(current.data+"");

        double left=evaluateExpression(current.leftChild,result);
        double right=evaluateExpression(current.rightChild,result);

        if (isOperator(current.data, operators)){
             result+=operatorFunction.get(current.data).apply(left,right);
        }
        return result;
    }

    private TreeNode<Character> createTree(String postfix){
        ArrayList<TreeNode<Character>> stack=new ArrayList<>();
        TreeNode<Character> right=null;
        TreeNode<Character> left=null;
        TreeNode<Character> parent;

        for(int i=0;i<postfix.length();i++){

            char character=postfix.charAt(i);
            if(!isOperator(character, operators)){
                stack.add(new TreeNode<>(character));
            }else{

                if(!stack.isEmpty()) {
                    right=stack.get(stack.size()-1);
                    stack.remove(stack.size()-1);
                }
                parent=new TreeNode<>(character);

                if(!stack.isEmpty()) {
                    left=stack.get(stack.size()-1);
                    stack.remove(stack.size()-1);
                }

                parent.rightChild=right;
                parent.leftChild=left;
                stack.add(parent);
            }
        }

        TreeNode<Character> root=stack.get(stack.size()-1);
        return root;

    }

    private String infixToPostfix(String expression){
        String postfix="";

        Stack<Character> stack=new Stack<>();

        int i=0;
        while (i<expression.length()){
            char character=expression.charAt(i);
            if(!isOperator(character, operators)) {
                postfix += character;
                i++;
            }else {
                if(stack.isEmpty()) {
                    stack.push(character);
                    i++;
                }
                else{
                    char operandInStack=stack.getTop();
                    if(operators.get(operandInStack) < operators.get(character)) {
                        stack.push(character);
                        i++;
                    }else {
                        postfix+=stack.pop();
                    }
                }
            }
        }

        while (!stack.isEmpty())
            postfix+=stack.pop();
        return postfix;
    }
    private boolean isOperator(char character, Map<Character,Integer> map){
        return map.containsKey(character);
    }


    private double add(double a,double b){
        return a+b;
    }

    private double subtract(double a ,double b){
        return a-b;
    }

    private double multiply(double a,double b){
        return a*b;
    }

    private double divide(double a,double b){
        return a/b;
    }
}