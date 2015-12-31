
import java.util.Iterator;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertNull;
import org.junit.Test;
import zadanie3pa.TripleList;



/**
 *
 * @author Krzysztof
 */
public class TestTripleList
{    
        @Test
	public void testEmptyListCreation()
        {
		final TripleList<Integer> tripleList = new TripleList<Integer>();
		assertEquals(0, tripleList.count());
		assertNull(tripleList.podajPoprzedni());
		assertNull(tripleList.podajSrodkowy());
		assertNull(tripleList.podajNastepny());
	}

	@Test
	public void testAddingSingleElement()
        {
		final TripleList<Integer> tripleList = new TripleList<Integer>();
		final Integer value = 4;
		tripleList.add(value);
		assertEquals(1, tripleList.count());
		assertEquals(value, tripleList.getValue());

		assertNull(tripleList.podajPoprzedni());
		assertNull(tripleList.podajSrodkowy());
		assertNull(tripleList.podajNastepny());
	}
	@Test
	public void testAddingTwoElements()
        {
		final TripleList<Integer> tripleList = new TripleList<Integer>();
		final Integer value1 = 4;
		final Integer value2 = -9;
		tripleList.add(value1);
		tripleList.add(value2);
		assertEquals(2, tripleList.count());
	
                
		assertEquals(value1, tripleList.getValue());
		assertEquals(value2, tripleList.podajSrodkowy().getValue());
		
                
		assertNull(tripleList.podajPoprzedni());
		assertNotNull(tripleList.podajSrodkowy());
		assertNull(tripleList.podajNastepny());
		
                
		assertNull(tripleList.podajSrodkowy().podajPoprzedni());
		assertNull(tripleList.podajSrodkowy().podajNastepny());
	}

	@Test
	public void testAddingTreeElements()
        {
		final TripleList<Integer> tripleList = new TripleList<Integer>();
		final Integer value1 = 4;
		final Integer value2 = -9;
		final Integer value3 = 47;
		tripleList.add(value1);
		tripleList.add(value2);
		tripleList.add(value3);
		assertEquals(3, tripleList.count());
		
                
		assertEquals(value1, tripleList.getValue());
		assertEquals(value2, tripleList.podajSrodkowy().getValue());
		assertEquals(value3, tripleList.podajNastepny().getValue());
		
                
		assertNull(tripleList.podajPoprzedni());
		assertNotNull(tripleList.podajSrodkowy());
		assertNotNull(tripleList.podajNastepny());
		
                
		assertNull(tripleList.podajSrodkowy().podajPoprzedni());
		assertNotNull(tripleList.podajSrodkowy().podajSrodkowy());
		assertNull(tripleList.podajSrodkowy().podajPoprzedni());
		
                
		assertNotNull(tripleList.podajNastepny().podajPoprzedni());
		assertNull(tripleList.podajNastepny().podajSrodkowy());
		assertNull(tripleList.podajNastepny().podajNastepny());
		
                
		assertEquals(value1, tripleList.getValue());
		assertEquals(value2, tripleList.podajSrodkowy().getValue());
		assertEquals(value3, tripleList.podajNastepny().getValue());
	}

	@Test
	public void testAddingFiveElements()
        {
		final TripleList<Integer> tripleList = new TripleList<Integer>();
		final Integer value1 = 1;
		final Integer value2 = 2;
		final Integer value3 = 3;
		final Integer value4 = 4;
		final Integer value5 = 5;
		tripleList.add(value1);
		tripleList.add(value2);
		tripleList.add(value3);
		tripleList.add(value4);
		tripleList.add(value5);
		assertEquals(5, tripleList.count());
		
                
		assertEquals(value1, tripleList.getValue());
		assertEquals(value2, tripleList.podajSrodkowy().getValue());
		assertEquals(value3, tripleList.podajNastepny().getValue());
		assertEquals(value4, tripleList.podajNastepny().podajSrodkowy().getValue());
		assertEquals(value5, tripleList.podajNastepny().podajNastepny().getValue());
	}

	@Test
	public void testListsEnumerator()
        {
		final Double[] values = { 1.1, 3.14, 6.13, 9.99999, 99.001 };
		final TripleList<Double> tripleList = new TripleList<>();
		int i;
		for (i = 0; i < values.length; ++i)
                {
			tripleList.add(values[i]);
		}
		i = 0;
		for (Double d : tripleList)
                {
			assertEquals(values[i++], d);
		}
	}

	@Test
	public void testListsEnumerator2()
        {
		final Double[] values = { 1.1, 3.14, 6.13, 9.99999, 99.001 };
		final TripleList<Double> tripleList = new TripleList<>();
		int i;
		for (i = 0; i < values.length; ++i)
                {
			tripleList.add(values[i]);
		}
		i = 0;
		final Iterator<Double> it = tripleList.iterator();
		while (it.hasNext())
                {
			assertEquals(values[i++], it.next());
		}
	}

	@Test
	public void IfNoCycle()
        {
		final int NUMBER_OF_ELEMENTS = 100;
		final TripleList<Integer> tripleList = new TripleList<Integer>();
		for (int i = 0; i < NUMBER_OF_ELEMENTS; ++i)
                {
			tripleList.add(i);
		}

		final TripleList<Integer> tripleListEverySingleNode = tripleList;
		TripleList<Integer> tripleListEveryTwoNodes = tripleList.podajNastepny();
		for (int i = 0; i < NUMBER_OF_ELEMENTS * NUMBER_OF_ELEMENTS; ++i)
                {
			assertNotSame(tripleListEverySingleNode, tripleListEveryTwoNodes);
			jumpToNextElement(tripleListEverySingleNode);
			if (null == tripleListEveryTwoNodes.podajNastepny())
                        {
				
				break;
			}
                        else
                        {
				tripleListEveryTwoNodes = tripleListEveryTwoNodes.podajNastepny();
			}
		}
	}

	private void jumpToNextElement(TripleList<Integer> element)
        {
		if (isNotLastElement(element))
                {
			if (isMiddleElement(element))
                        {
				if (null != element.podajSrodkowy().podajNastepny())
                                {
					element = element.podajSrodkowy().podajNastepny();
				}
			}
                        else
                        {
				if (null != element.podajNastepny())
                                {
					element = element.podajNastepny();
				}
			}
		}
	}

	private boolean isNotLastElement(TripleList<Integer> element)
        {
		return null != element.podajSrodkowy();
	}

	private boolean isMiddleElement(TripleList<Integer> element)
        {
		return null == element.podajNastepny()&& null == element.podajPoprzedni()
                && null != element.podajSrodkowy();
	}
}
