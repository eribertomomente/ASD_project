package data_structure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyList<T> implements Iterable<T> {
	
	/**
	 * This class is a personal implementation of the Java Vector class
	 * MyList is mutable, unbounded.
	 * 
	 * ABSTRACTION FUNCTION:  Each "node" of the list has a field called <data> containing the real data and a field <next> pointing the next node. 
	 *                                                 The last node has null <data>.
	 */
	
	// ATTRIBUTES
	//private T data;
	//private MyList<T> next;
	private Object[] list;
	private int size;
	
	// CONSTRUCTOR
	public MyList (){
		this.list = new Object[32];
		this.size = 0;
	}
	
	/* GETTERS
	public T getData() {
		return this.data;
	}
	
	public MyList<T> getNext() {
		return this.next;
	}
	*/
	

	public void insert (T value) {
		if ( this.size == this.list.length ) {
			Object[] bigger = new Object[size/2*3]; 
			for (int i = 0; i < this.size; i++) {
				bigger[i] = this.list[i];
			}
			this.list = bigger;
		}
		this.list[size] = value;
		this.size ++;
	}
	
	public void insert(String elementAt, int i) {
		// TODO Auto-generated method stub
		
	}
	
	
	public int size () {
		return this.size;
	}
	
	
	@SuppressWarnings("unchecked")
	public int getIndex (T value) {
		for (int i=0; i< this.list.length; i++) {
			if ( (T) this.list[i] == value) {
				return i;
			}
		}
		// end of the MyList
		throw new NoSuchElementException();
	}
	
	
	@SuppressWarnings("unchecked")
	public T elementAt(int index) {
		if ( index >= this.list.length ) {
			throw new NoSuchElementException();
		}
		return (T) this.list[index];
	}
	
	/*
	 * TODO cerchiamo di eliminarlo questo
	 * 
	public boolean contains(T elt) {
		MyList<T> current = this;
		while(current.hasNext()) {
			if (current.data.equals(elt)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	*/
	
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < this.size-1; i++) {
			sb.append(this.list[i]);
			sb.append(", ");
		}
		sb.append(this.list[this.size-1]);
		sb.append("]");
		
		return sb.toString();
			
	}


	@Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    // Inner class 
    private class MyIterator implements Iterator<T> {
    	
        private int cursor;

        public MyIterator() {
            this.cursor = 0;
        }

        public boolean hasNext() {
            return this.cursor < MyList.this.size();
        }

        public T next() {
            if(this.hasNext()) {
                int current = cursor;
                cursor ++;
                return MyList.this.elementAt(current);
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
public static void main(String[] args) {
		
		MyList<Character> list = new MyList<Character>();
		
		list.insert('e');
		list.insert('r');
		list.insert('i');
		list.insert('b');
		list.insert('e');
		list.insert('r');
		list.insert('t');
		list.insert('o');

		System.out.println( "inizio");
		System.out.println( list.toString() );

		System.out.println( "fine");
		
		
	}


	
}