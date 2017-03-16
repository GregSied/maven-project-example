package pl.com.sages.tests.assertJ;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.Assertions.withinPercentage;
import static pl.com.sages.tests.tolkien.Race.*;

import java.math.BigDecimal;
import java.util.Comparator;

import org.junit.Test;

import pl.com.sages.tests.tolkien.TolkienCharacter;

/**
 * Number assertions examples.
 */
public class NumberAssertJExamples {

	
	protected final TolkienCharacter sam = new TolkienCharacter("Sam", 38, HOBBIT);
	  protected final TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
	  protected final TolkienCharacter gimli = new TolkienCharacter("Gimli", 139, DWARF);
	  protected final TolkienCharacter sauron = new TolkienCharacter("Sauron", 50000, MAIA);
	  protected final TolkienCharacter gandalf = new TolkienCharacter("Gandalf", 2020, MAIA);

	
  @Test
  public void number_assertions_examples() throws Exception {

    // equals / no equals assertions
    assertThat(sam.age).isEqualTo(38)
                       .isCloseTo(40, within(10));
    assertThat(frodo.age).isEqualTo(33).isNotEqualTo(sam.age);

    // <= < > >= assertions
    assertThat(sam.age).isGreaterThan(frodo.age).isGreaterThanOrEqualTo(38);
    assertThat(frodo.age).isLessThan(sam.age).isLessThanOrEqualTo(33);
    assertThat(sam.age).isBetween(frodo.age, gimli.age);

    // shortcuts for assertions : > 0, < 0 and == 0
    assertThat(frodo.age - frodo.age).isZero();
    assertThat(frodo.age - sauron.age).isNegative();
    assertThat(gandalf.age - frodo.age).isPositive();

    assertThat(frodo.age - frodo.age).isNotNegative();
    assertThat(frodo.age - frodo.age).isNotPositive();
    assertThat(gandalf.age - frodo.age).isNotNegative();
    assertThat(frodo.age - sauron.age).isNotPositive();
  }


  @Test
  public void assertion_error_with_message_differentiating_double_and_float() {

    // Assertion error message is built with a String description of involved objects.
    // Sometimes, the descriptions are the same, if you were to compare a double and a float with same values, the error
    // message would be confusing, ex :
    // "expected:<'42.0'> but was:<'42.0'> ... How bad !
    // In that case, AssertJ is smart enough and differentiates the number types in the error message.

    // we declare numbers instead of Double and Float to be able to compare them with isEqualTo.
    final Number expected = 42d;
    final Number actual = 42f;
    try {
      assertThat(actual).isEqualTo(expected);
    } catch (AssertionError e) {
      // this message is formatted by JUnit to show what is different (looks nice in IDE but not so in the error
      // message)
      assertThat(e).hasMessage("expected:<42.0[]> but was:<42.0[f]>");
      return;
    }
  }

  @Test
  public void big_decimals_assertions_examples() {

    // You can use String directly and we will create the corresponding BigDecimal for you, thus ...
    assertThat(new BigDecimal("8.0")).isEqualTo("8.0");
    // ... is equivalent to :
    assertThat(new BigDecimal("8.0")).isEqualTo(new BigDecimal("8.0"));

    // With BigDecimal, 8.0 is not equals to 8.00 but it is if you use compareTo()
    assertThat(new BigDecimal("8.0")).isEqualByComparingTo(new BigDecimal("8.00"));
    assertThat(new BigDecimal("8.0")).isEqualByComparingTo("8.00");
    assertThat(new BigDecimal("8.0")).isNotEqualByComparingTo("8.01");

    // isGreaterThanOrEqualTo uses compareTo semantics
    assertThat(new BigDecimal("8.0")).isGreaterThanOrEqualTo(new BigDecimal("8.00"));
    assertThat(new BigDecimal("8.1")).isGreaterThanOrEqualTo(new BigDecimal("8.10"));
  }

  @Test
  public void number_assertions_with_offset_examples() {
    assertThat(8.1).isEqualTo(8.0, offset(0.1));
    assertThat(8.1f).isEqualTo(8.2f, offset(0.1f));
    try {
      assertThat(8.1f).isEqualTo(8.0f, offset(0.1f));
    } catch (AssertionError e) {
    }

    // same stuff using within instead of offset
    assertThat(8.1).isCloseTo(8.0, within(0.1));
    assertThat(5.0).isCloseTo(6.0, withinPercentage(20.0));
    assertThat(5.0).isCloseTo(6.0, withinPercentage(20));
    assertThat(5).isCloseTo(6, withinPercentage(20));

    assertThat(8.2f).isCloseTo(8.0f, within(0.2f));
    assertThat(new BigDecimal("8.1")).isCloseTo(new BigDecimal("8.0"), within(new BigDecimal("0.1")));
    // just to see that the BigDecimal format does not have impact on the assertion
    assertThat(new BigDecimal("8.1")).isCloseTo(new BigDecimal("8.00"), within(new BigDecimal("0.100")));
    try {
      assertThat(8.1f).isCloseTo(8.0f, within(0.1f));
    } catch (AssertionError e) {
    }

    try {
      assertThat(new BigDecimal("8.1")).isCloseTo(new BigDecimal("8.0"), within(new BigDecimal("0.01")));
    } catch (AssertionError e) {
    }

    assertThat(sam.age).isCloseTo(40, within(10));
    assertThat(10l).isCloseTo(8l, within(2l));
    assertThat((short) 5).isCloseTo((short) 7, within((short) 3));
    assertThat((byte) 5).isCloseTo((byte) 7, within((byte) 3));
    
    // double[] x = {1.0, 2.0};
    // assertThat(x).isCloseTo(x);
  }

  @Test
  public void number_assertions_with_binary_representation_examples() {
    assertThat(1).inBinary().isEqualTo(1);
    try {
      assertThat(1).inBinary().isEqualTo(2);
    } catch (AssertionError e) {
      // logAssertionErrorMessage("isEqualTo with binary representation_", e);
    }
  }

  @Test
  public void comparing_array_of_real_numbers() {
    Comparator<Double> closeToComparator = new Comparator<Double>() {
      @Override
      public int compare(Double o1, Double o2) {
        return Math.abs(o1.doubleValue() - o2.doubleValue()) < 0.001 ? 0 : -1;
      }
    };
    assertThat(new double[] { 7.2, 3.6, -12.0 }).usingElementComparator(closeToComparator)
                                                .containsExactly(7.2000001, 3.5999999, -12.000001);
  }

  @Test
  public void subsequence_of_real_numbers() {
    assertThat(new double[] { 1.0, 2.0, 3.0 }).containsSubsequence(1.0, 3.0);
    assertThat(new float[] { 1.0f, 2.0f, 3.0f }).containsSubsequence(1.0f, 3.0f);
  }
  
}
