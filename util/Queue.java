package util;
//import java.util.ArrayList;
public class Queue {													// class to manipulate a queue data structure
	 private static int front, rear, capacity;							// capacity is the length of queue and front, rear are normal queue terms
	    private static Cell queue[];
	 
	    public Queue(int c)												// constructor of class Queue
	    {
	        front = rear = 0;											// intially front and rear will be 0 (here)
	        capacity = c;												// using length to create an array of Cells 
	        queue = new Cell[capacity];
	    }
	 
	    // function to insert an element
	    // at the rear of the queue
	    public void queueEnqueue(Cell data)								// adding an element to the queue from rear
	    {
	        // check queue is full or not
	        if (capacity == rear) {										// checking of the queue is full or not and print the message 
	            System.out.printf("\nQueue is full\n");					// accordingly
	            return;
	        }
	 
	        // insert element at the rear
	        else {														// if not full then add the data (input argument) to the queue
	            queue[rear] = data;										// from the rear end and increment reaer for next addition 
	            rear++;
	        }
	        return;
	    }
	    // function to delete an element
	    // from the front of the queue
	    public Cell queueDequeue()										
	    {
	        // if queue is empty print it is empty and come out of the function
	        if (front == rear) {
	            System.out.printf("\nQueue is empty\n");
	            return null;
	        }
              
	        // if it is not full then get it's object at front position and return it 	
	        // then increment the value of front by 1
	        else {
	        	Cell temp=queue[front];
	        	front++;
	        	return temp;
	        }
	        
	    }
	 
}
