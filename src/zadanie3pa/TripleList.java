
package zadanie3pa;

import java.util.Iterator;

/**
 *
 * @author Krzysztof
 */
public class TripleList <T> implements Iterable<T>
{
    
    
    private T wartosc;
    private TripleList<T> poprzedni;
    private TripleList<T> nastepny;
    private TripleList<T> srodkowy;
    
    
    @Override
    public Iterator<T> iterator()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return new TripleIterator();
    }
    
        private class TripleIterator implements Iterator<T>
        {

        private int iterator = 0;

        public boolean hasNext() 
        {
            return licznik < this.iterator;
        }

        public T next() 
        {
            if(hasNext()) 
            {   
                int i = 0;
                TripleList<T> element = TripleList.this;
                while (i<iterator) i++;             
                {
                    if ((i%2==0))
                    {
                        element = element.podajSrodkowy();
                    }
                    else
                    {
                        element = element.podajSrodkowy().podajNastepny();
                    }
                }
                iterator++;
                return element.getValue();
            }
            //throw new NoSuchElementException();
            return null;
        }


    } 
    public void add(T value) 
    {
		if (this.wartosc == null)
                    this.wartosc = value;
                
                
		else if (srodkowy == null) 
                {
			srodkowy = new TripleList<T>();
			srodkowy.ustawSrodkowy(this);
			srodkowy.add(value);
		} 
                else 
                {
			if (nastepny == null) nastepny = new TripleList<T>();
			nastepny.ustawPoprzedni(this);
			nastepny.add(value);
		}
		licznik++;
    }
private int licznik;

 public int count() 
    {
        return licznik;
    }

public T getValue()
    {
        return wartosc;
    }

    public TripleList<T> podajPoprzedni()
    {
        return poprzedni;
    }

    public void ustawPoprzedni(TripleList<T> element)
    {
        poprzedni = element;
    }

    public TripleList<T> podajSrodkowy()
    {
        return srodkowy;
    }

    public void ustawSrodkowy(TripleList<T> element)
    {
        srodkowy = element;
    }

    public TripleList<T> podajNastepny()
    {
        return nastepny;
    }

    public void setNextElement(TripleList<T> element)
    {
        nastepny = element;
    }

    public TripleList()
    {
        this.poprzedni = null;
        this.nastepny = null;
        this.srodkowy = null;
        this.wartosc = null;
    }
    
    public TripleList(T value)
    {
        this.poprzedni = null;
        this.nastepny = null;
        this.srodkowy = null;
        this.wartosc = value;
    }
}