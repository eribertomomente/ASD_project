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
	private T data;
	private MyList<T> next;
	
	// CONSTRUCTOR
	public MyList (){
		this.data = null;
		next = null;
	}
	
	// GETTERS
	public T getData() {
		return this.data;
	}
	
	public MyList<T> getNext() {
		return this.next;
	}
	

	public void insert (T value) {
		MyList<T> newElt = new MyList<T>();
		
		MyList<T> current = this;
		while ( current.data != null) {
			current = current.next;
		}
		current.data = value;
		current.next = newElt;
	}
	
	
	public int size () {
		int length = 0;
		MyList<T> current = this;
		while ( current.data != null) {
			length ++;
			current = current.next;
		}
		return length;
	}
	
	
	public int getIndex (T value) {
		int index = 0;
		MyList<T> current = this;
		while ( current.data != value) {
			if (current.data == null) {
				// end of the MyList
				throw new NoSuchElementException();
			}
			current = current.next;
			index ++;
		}
		return index;
	}
	
	
	public T elementAt(int index) {
		MyList<T> current = this;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}
	
	
	public boolean hasNext() {
		if ( this.data == null) {
			return false;
		}
		return true;
	}
	
	
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
	
	public String toString() {
		MyList<T> current = this;
		String str = "";
		while(current.hasNext()) {
			str = str +  " " + current.data.toString();
			current = current.next;
		}
		return str;
			
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
	
}