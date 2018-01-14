package data_structure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyList<T> implements Iterable<T> {
	
	/**
	 * MISSION: 	Questa classe è l'implementazione dell'analoga classe Java: Vector.
	 * 					MyList è mutable e senza limite (unbounded)
	 * 
	 * FUNZIONE DI ASTRAZIONE:  MyList viene implementata con list un array di Object e size un intero che conta gli elementi inseriti.
	 * 												list ha una dimensione predefinita di 32 elementi.
	 * 												Ogni volta che si supera la capacità di list essa viene riallocata con una capacità aggiuntiva del 50 per cento.
	 */	
	
	private Object[] list;
	private int size;
	
	/**
	 * Costruttore
	 */
	public MyList (){
		this.list = new Object[32];
		this.size = 0;
	}
	
	/**
	 * inserisce un nuovo elemento 
	 * @param value elemento da inserire
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
	
	/**
	 * Sostituisce l’elemento alla posizione index con el
	 * @param el nuovo elemento
	 * @param index posizione 
	 */
	public void setElementAt(T el, int index) {
		if (index>=this.size || index < 0) {
			throw new IllegalArgumentException();
		}
		this.list[index] = el;
	}
	
	/**
	 * @return numero di elementi inseriti
	 */
	public int size () {
		return this.size;
	}
	
	/**
	 * @param value elemento 
	 * @return indice di value in this
	 */
	@SuppressWarnings("unchecked")
	public int getIndex (T value) {
		for (int i=0; i< this.size; i++) {
			if ( (T) this.list[i] == value) {
				return i;
			}
		}
		// end of the MyList
		throw new NoSuchElementException();
	}
	
	/**
	 * @return l'ultimo elemento inserito in coda
	 */
	public T lastElement() {
		if (this.size == 0) {
			throw new NoSuchElementException();
		}
		return this.elementAt(this.size-1);
	}
	
	/**
	 * @param index
	 * @return elemento alla posizione index
	 */
	@SuppressWarnings("unchecked")
	public T elementAt(int index) {
		if ( index >= this.list.length || index < 0) {
			throw new NoSuchElementException();
		}
		return (T) this.list[index];
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