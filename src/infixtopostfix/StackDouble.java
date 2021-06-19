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

class StackD
 {
     static int size=40;
     double []arr=new double[size];  
     int top;
     StackD()
     {
       this.top=-1;
     }
   public void push(double d)
   {
       if(this.top==size)
       {
           System.out.println("Stack is full***push");
       }
       else
       {
        this.arr[++this.top]=d;   
       }
   }
   public double pull()
   {
       if(this.top==-1)
       {
           System.out.println("Stack is Empty***pull");
           return 0.00;
       }
       else
       {
         return this.arr[this.top--];  
       }
   }
   public double peek()
   {
       if(this.top==-1)
       {
           System.out.println("Stack is Empty***peek");
           return 0.00;
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

public class StackDouble {
    
}
