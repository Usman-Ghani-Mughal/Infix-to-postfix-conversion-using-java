/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infixtopostfix;

/**
 *
 * @author Usman Ghani Mughal
 */
 class Stack
 {
     static int size=40;
     char []arr=new char[size];  
     int top;
     Stack()
     {
       this.top=-1;
     }
   public void push(char ch)
   {
       if(this.top==size)
       {
           System.out.println("Stack is full***push");
       }
       else
       {
        this.arr[++this.top]=ch;   
       }
   }
   public char pull()
   {
       if(this.top==-1)
       {
           System.out.println("Stack is Empty***pull");
           return '\u0000';
       }
       else
       {
         return this.arr[this.top--];  
       }
   }
   public char peek()
   {
       if(this.top==-1)
       {
           System.out.println("Stack is Empty***peek");
           return '\u0000';
       }
       else
       {
         return this.arr[this.top];  
       }
   }
   public void displayStack()
   {
       int move=this.top;
       while(move!=-1)
       {
           System.out.print(this.arr[move]+" ");
           move--;
       }
   }
 }


public class STackss {
    
}
