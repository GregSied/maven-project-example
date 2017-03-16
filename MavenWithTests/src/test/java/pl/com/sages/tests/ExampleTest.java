package pl.com.sages.tests;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import pl.com.sages.tests.App;

//@BeforeClass setUpClass
//@Before setUp
//@Test test2()
//@After tearDown
//@Before setUp
//@Test test1()
//@After tearDown
//@AfterClass tearDownClass

public class ExampleTest {

	App app;

	@Before
	public void setUp() {
		System.out.println("@BeforeClass setUpClass");
		app = new App();
	}
	
	@Test
	public void testGetName() {
		assertEquals("AAA", app.getName());
	}

	@Test(timeout = 1000)
	public void testAssertArrayEquals() {
		byte[] expected = "trial".getBytes();
		byte[] actual = "trial".getBytes();
		assertArrayEquals("failure - byte arrays not same", expected, actual);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void empty() {
		new ArrayList<Object>().get(0);
	}

	@Ignore("Test is ignored as a demonstration")
	@Test
	public void testExceptionMessage() {
		try {
			new ArrayList<Object>().get(0);
			fail("Expected an IndexOutOfBoundsException to be thrown");
		} catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
			assertThat(anIndexOutOfBoundsException.getMessage(), is("Index: 0, Size: 0"));
		}
	}

	@Test
	public void assertsTest() {
		int x = 3;
		String responseString = "colour";
		List<String> myList = new ArrayList<>();
		myList.add("3");

		assertThat(x, is(3));
		assertThat(x, is(not(4)));
		assertThat(responseString, either(containsString("color")).or(containsString("colour")));
		assertThat(myList, hasItem("3"));
	}

	@Test
	public void testAssertTrue() {
		assertTrue("failure - should be true", true);
	}

	@Test
	public void testAssertNotSame() {
		assertNotSame("should not be same Object", new Object(), new Object());
	}

	@Test
	public void testAssertNotNull() {
		assertNotNull("should not be null", new Object());
	}

	@Test
	public void testAssertThatHasItems() {
		assertThat(Arrays.asList("one", "two", "three"), hasItems("one", "three"));
	}

	@Test
	public void testAssertEquals() {
		assertEquals("failure - strings are not equal", "text", "text");
	}

}
