package pl.com.sages.tests.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class MockitoExamplesTest {

	@Test
	public void iterator_will_return_hello_world() {
		// arrange
		Iterator i = mock(Iterator.class);
		when(i.next()).thenReturn("Hello").thenReturn("World");
		// act
		String result = i.next() + " " + i.next();
		// assert
		assertEquals("Hello World", result);
	}

	@Test
	public void with_arguments() {
		Comparable c = mock(Comparable.class);
		when(c.compareTo("Test")).thenReturn(1);
		assertEquals(1, c.compareTo("Test"));
	}

	@Test
	public void with_unspecified_arguments() {
		Comparable c = mock(Comparable.class);
		when(c.compareTo(anyInt())).thenReturn(-1);
		assertEquals(-1, c.compareTo(5));
	}

	@Test(expected = IOException.class)
	public void OutputStreamWriter_rethrows_an_exception_from_OutputStream() throws IOException {
		OutputStream mock = mock(OutputStream.class);
		OutputStreamWriter osw = new OutputStreamWriter(mock);
		doThrow(new IOException()).when(mock).close();
		osw.close();
	}

	@Test
	public void OutputStreamWriter_Closes_OutputStream_on_Close() throws IOException {
		OutputStream mock = mock(OutputStream.class);
		OutputStreamWriter osw = new OutputStreamWriter(mock);
		osw.close();
		verify(mock).close();
	}
	
	
	// Mock vs Spy
	@Test
	public void differenceBetweenMockingSpyingAndStubbing(){
	    List list = new ArrayList();
	    list.add("abc");
	    assertEquals(1,list.size());
	    List mockedList = spy(list);
	    when(mockedList.size()).thenReturn(10);
	    assertEquals(10,mockedList.size());
	}
	
	@Test
	public void whenNotUseMockAnnotation_thenCorrect() {
	    List mockList = mock(ArrayList.class);
	     
	    mockList.add("one");
	    verify(mockList).add("one");
	    assertEquals(0, mockList.size());
	 
	    when(mockList.size()).thenReturn(100);
	    assertEquals(100, mockList.size());
	}
	

}
