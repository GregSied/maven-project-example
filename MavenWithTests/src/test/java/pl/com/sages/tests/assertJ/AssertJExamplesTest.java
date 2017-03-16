package pl.com.sages.tests.assertJ;

//One static import to rule them all ...
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class AssertJExamplesTest {

	
	//AssertJ:
	@Test
	public void anyTest(){
		assertThat("AAA").contains("AA");
		// lub: 
		assertThat("AAA").isEqualTo("AAA");
	}
	
	// to samo w TestNG
	@Test
	public void anyTest2(){
		assertEquals("AAA", "AAA");
	}
	
	@Test
	public void stringExamplesTest(){
		assertThat("Frodo").startsWith("Fro").endsWith("do").hasSize(5);
	    assertThat("Frodo").contains("rod").doesNotContain("fro");
	    assertThat("Frodo").containsOnlyOnce("do");
	    assertThat("Frodo").isSubstringOf("Frodon");
	    try {
	      assertThat("Frodo").containsOnlyOnce("o");
	    } catch (AssertionError e) {
	    }
	    // contains accepts multiple String to avoid chaining contains several times.
	    assertThat("Gandalf the grey").contains("alf", "grey");
	    try {
	      assertThat("Gandalf the grey").contains("alf", "grey", "wizard", "ring");
	    } catch (AssertionError e) {
	    }

	    String bookDescription = "{ 'title':'Games of Thrones', 'author':'George Martin'}";
	    assertThat(bookDescription).containsSequence("{", "title", "Games of Thrones", "}");

	    try {
	      assertThat(bookDescription).containsSequence("{", "title", "author", "Games of Thrones", "}");
	    } catch (AssertionError e) {
	    }

	    // you can ignore case for equals check
	    assertThat("Frodo").isEqualToIgnoringCase("FROdO").hasSameSizeAs("12345");
	    assertThat("Frodo".length()).isGreaterThan("Sam".length());

	    // using regex
	    assertThat("Frodo").matches("..o.o").doesNotMatch(".*d");

	    // check for empty string, or not.
	    assertThat("").isEmpty();
	    assertThat("").isNullOrEmpty();
	    assertThat("not empty").isNotEmpty();

	    // check size.
	    assertThat("C-3PO").hasSameSizeAs("R2-D2").hasSize(5);

	    assertThat("3210").containsOnlyDigits();

	    //
	    assertThat("Frodo").doesNotStartWith("fro")
	                       .doesNotEndWith("don");
	}
	
	
}
