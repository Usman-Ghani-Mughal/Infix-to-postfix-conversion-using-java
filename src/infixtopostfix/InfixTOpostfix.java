/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infixtopostfix;

import java.util.Scanner;

/**
 *
 * @author Usman Ghani Mughal
 */


class Notation
{
    String postfix;
    String prefix;
    Stack stack=new Stack();
    Notation()
    {
        this.postfix="";
        this.prefix="";
    }
    public boolean isoperator(char ch)
    {
        switch(ch)
        {
            case '(':
            case ')':
            case '+':
            case '-':
            case '*':
            case '/':
            case '%':
            case '^':
                return true;
            default:
                return false;
        }
    }
    public int checkOpPres(char ch)
    {
        switch(ch)
        {
            case '(':
            {
                return 1;
            }
            case '+':
            {
                return 2;
            }
            case '-':
            {
                return 2;
            }
            case '/':
            {
                return 3;
            }
            case '*':
            {
                return 3;
            }
            case '%':
            {
                return 3;
            }
                
            case '^':
            {
                return 4;
            }
            case ')':
            {
                return 5;
            }
            default:
            {
                return 0;
            }
        }
    }
    public void inFixToPostFix(String infix)
    {
        int i,presidance,presidanceLast;
        stack.push('(');
        //stack.displayStack();
        infix+=")";
        //System.out.println("\n"+infix+"\n"+infix.length());
        for(i=0;i<infix.length();i++)
        {
            if(this.isoperator(infix.charAt(i)))
            {
                //System.out.println("\n at i"+i);
                presidance=this.checkOpPres(infix.charAt(i));
                switch(presidance)
                {
                    case 1:
                    {
                        stack.push(infix.charAt(i));
                        break;
                    }
                    case 2:
                    {
                        presidanceLast=this.checkOpPres(stack.peek());
                        //System.out.println("last pre is "+presidanceLast);
                        while(presidanceLast>=presidance)
                        {
                            //System.out.println("***in case 2***\n");
                            this.postfix+=stack.pull();
                            presidanceLast=this.checkOpPres(stack.peek());
                        }
                        stack.push(infix.charAt(i));
                        break;
                    }
                    case 3:
                    {
                        
                        presidanceLast=this.checkOpPres(stack.peek());
                        while(presidanceLast>=presidance)
                        {
                            //System.out.println("***in case 3***\n");
                            this.postfix+=stack.pull();
                            presidanceLast=this.checkOpPres(stack.peek());
                        }
                        stack.push(infix.charAt(i));
                        break;
                    }
                    case 4:
                    {
                        presidanceLast=this.checkOpPres(stack.peek());
                        while(presidanceLast>=presidance)
                        {
                            //System.out.println("***in case 4***\n");
                            this.postfix+=stack.pull();
                            presidanceLast=this.checkOpPres(stack.peek());
                        }
                        stack.push(infix.charAt(i));
                        break;
                    }
                    case 5:
                    {
                        char oprator;
                        //System.out.println("**************\n\n");
                        //stack.displayStack();
                        //System.out.println("**************\n\n");
                        oprator=stack.pull();
                        while(oprator!='(')
                        {
                           // stack.displayStack();
                           //System.out.println("***in case 5***\n");
                            this.postfix+=oprator;
                            oprator=stack.pull();
                            //this.displayPostFix();
                        }
                        break;
                    }
                    default:
                    {
                        break;
                    }
                        
                }
            }
            else
            {
                this.postfix+=infix.charAt(i);
                //System.out.print("\n\n\n****\n"+this.postfix+" \n\n\n******post");
            }
        }
    }
    public void displayPostFix()
    {
        System.out.println(this.postfix);
    }
    public void displayPreFix()
    {
        System.out.println(this.prefix);
    }
    public void clearPostfix()
    {
        this.postfix="";
    }
    
    public void EvaluatePostFix()
    {
        char temp;
        double a,b;
        Stack equ=new Stack();
        StackD res=new StackD();
        equ.push('(');
        for(int i=this.postfix.length()-1;i>=0;i--)
        {
            equ.push(this.postfix.charAt(i));
        }
        //equ.displayStack();
        temp=equ.pull();
        while(temp!='(')
        {
            if(this.isoperator(temp))
            {
                
                b=res.pull();
                a=res.pull();
                res.push(this.operate(a,b,temp));
               // System.out.println("*!*Result is : "+res.peek());
            }
            else
            {
                res.push(this.checkvariable(temp));
            }
            temp=equ.pull();
        }
        System.out.println("Result is : "+res.pull());
    }
    public double checkvariable(char ch)
    {
        switch(ch)
        {
            case 'a':
            {
                return 4;
            }
            case 'b':
            {
                return 4;
            }
            case 'c':
            {
                return 2;
            }
            case 'd':
            {
                return 2;
            }
            default:
            {
                return 1;
            }
                
        }
    }
    public double operate(double a,double b,char op)
    {
        switch(op)
        {
            case '+':
            {
                return a+b;
            }
            case '-':
            {
                return a-b;
            }
            case '*':
            {
                return a*b;
            }
            case '/':
            {
                return a/b;
            }
            case '%':
            {
                return a%b;
            }
            default:
            {
                return 0;
            }
            
        }
    }
    public String invertBrackets(String input)
    {
        String temp="";
        char ch;
        for(int i=0;i<input.length();i++)
        {
            ch=input.charAt(i);
            switch(ch)
            {
                case '(':
                {
                    temp+=')';
                    break;
                }
                case ')':
                {
                    temp+='(';
                    break;
                }
                default:
                {
                    temp+=ch;
                    break;
                }
            }
        }
    //    System.out.println("After inverted : "+temp);
        return temp;
    }
    protected String reverse(String input)
    {
        String temp="";
        for(int i=0;i<input.length();i++)
        {
            temp+=input.charAt(input.length()-1-i);
        }
       // System.out.println("After reverse : "+temp);
        return temp;
    }
    protected String applyBrac(String input)
    {
        String temp="(";
        for(int i=0;i<input.length();i++)
        {
            temp+=input.charAt(i);
        }
        temp+=")";
        //System.out.println("after brackerts apply :"+temp);
        return temp;
    }
    public void infixtoPrefix(String infix)
    {
        int i,presidance,presidanceLast;
        //stack.push('(');
        //stack.displayStack();
        //infix+=")";
        infix=this.applyBrac(infix);
        infix=this.invertBrackets(infix);
        infix=this.reverse(infix);
        //System.out.println("\n"+infix+"\n"+infix.length());
        for(i=0;i<infix.length();i++)
        {
            if(this.isoperator(infix.charAt(i)))
            {
                //System.out.println("\n at i"+i);
                presidance=this.checkOpPres(infix.charAt(i));
                switch(presidance)
                {
                    case 1:
                    {
                        stack.push(infix.charAt(i));
                        break;
                    }
                    case 2:
                    {
                        presidanceLast=this.checkOpPres(stack.peek());
                        //System.out.println("last pre is "+presidanceLast);
                        while(presidanceLast>presidance)
                        {
                            //System.out.println("***in case 2***\n");
                            this.prefix+=stack.pull();
                            presidanceLast=this.checkOpPres(stack.peek());
                        }
                        stack.push(infix.charAt(i));
                        break;
                    }
                    case 3:
                    {
                        
                        presidanceLast=this.checkOpPres(stack.peek());
                        while(presidanceLast>presidance)
                        {
                            //System.out.println("***in case 3***\n");
                            this.prefix+=stack.pull();
                            presidanceLast=this.checkOpPres(stack.peek());
                        }
                        stack.push(infix.charAt(i));
                        break;
                    }
                    case 4:
                    {
                        presidanceLast=this.checkOpPres(stack.peek());
                        while(presidanceLast>presidance)
                        {
                            //System.out.println("***in case 4***\n");
                            this.prefix+=stack.pull();
                            presidanceLast=this.checkOpPres(stack.peek());
                        }
                        stack.push(infix.charAt(i));
                        break;
                    }
                    case 5:
                    {
                        char oprator;
                        //System.out.println("**************\n\n");
                        //stack.displayStack();
                        //System.out.println("**************\n\n");
                        oprator=stack.pull();
                        while(oprator!='(')
                        {
                           // stack.displayStack();
                           //System.out.println("***in case 5***\n");
                            this.prefix+=oprator;
                            oprator=stack.pull();
                            //this.displayPostFix();
                        }
                        break;
                    }
                    default:
                    {
                        break;
                    }
                        
                }
            }
            else
            {
                this.prefix+=infix.charAt(i);
                //System.out.print("\n\n\n****\n"+this.postfix+" \n\n\n******post");
            }
        }
        this.prefix=this.reverse(this.prefix);
    }
    
}


public class InfixTOpostfix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc=new Scanner(System.in);
        Notation obj=new Notation();
        while(true)
        {
          System.out.print("\nEnter Your infix : ");
          String input=sc.next();
          obj.infixtoPrefix(input);
          obj.inFixToPostFix(input);
          obj.displayPostFix();
          obj.displayPreFix();
          //obj.EvaluatePostFix();
          obj.clearPostfix();
        }
        
    }
    
}
