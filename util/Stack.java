package util;

import java.util.ArrayList;

public class Stack {                                // class to use a Stack data structure
ArrayList<Cell> St;                                 // list holding the stack
public Stack(){                                     // constructor of the class Stack
	St=new ArrayList<>();
}
public boolean isEmpty() {                          // checking if the stack is empty  (true if empty false otherwise)
    if (St.isEmpty()){
         return true;
    } else {
          return false;
    }
}

public void push(Cell t)                            // function to push an element into the stack
{
	St.add(t);
}
public Cell pop()                                   // function for poping an element from the stack
{
	if(!isEmpty())                                  // if it is not empty just get the cell at top in the stack into a new object 
	{                                               // and remove the top element from the stack
		Cell popValue = St.get(St.size() - 1);
        St.remove(St.size() - 1);           
        return popValue;
	}
	else {                                           // print the message if it is already empty 
        System.out.print("The stack is already empty  ");
        return null;
   }
}

}
