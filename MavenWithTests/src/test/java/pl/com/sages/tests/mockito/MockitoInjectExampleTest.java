package pl.com.sages.tests.mockito;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.*;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import pl.com.sages.tests.game.GameManager;
import pl.com.sages.tests.game.Player;

@RunWith(MockitoJUnitRunner.class)
public class MockitoInjectExampleTest {

	@Mock
    private Player player;
	
	@InjectMocks
    private final GameManager gameManager = new GameManager();
	
//  zamiast @InjectMocks można użyć:
//	@Before
//	public void init() {
//	    MockitoAnnotations.initMocks(this);
//	}
	
	
	@Test
    public void testGameManager() throws Exception {
        when(player.play()).thenReturn(true);
 
        final boolean actual = gameManager.playGame();
 
        assertThat(actual).isTrue();
    }
	
	
	@Mock
	List<String> mockedList;
	 
	@Test
	public void whenUseMockAnnotation_thenMockIsInjected() {
	    mockedList.add("one");
	    verify(mockedList).add("one");
	    assertThat(mockedList.size()).isEqualTo(0);
	 
	    when(mockedList.size()).thenReturn(100);
	    assertThat(mockedList.size()).isEqualTo(100);
	}
	
	@Test
	public void whenNotUseSpyAnnotation_thenCorrect() {
	    List<String> spyList = spy(new ArrayList<String>());
	     
	    spyList.add("one");
	    spyList.add("two");
	 
	    verify(spyList).add("one");
	    verify(spyList).add("two");
	 
	    assertThat(spyList.size()).isEqualTo(2);
	 
	    when(spyList.size()).thenReturn(100);
	    // albo:
	    // doReturn(100).when(spyList).size();
	    assertThat(spyList.size()).isEqualTo(100);
	}
	
	
	@Spy
	List<String> spiedList = new ArrayList<String>();
	 
	@Test
	public void whenUseSpyAnnotation_thenSpyIsInjected() {
	    spiedList.add("one");
	    spiedList.add("two");
	 
	    verify(spiedList).add("one");
	    verify(spiedList).add("two");
	 
	    assertThat(spiedList.size()).isEqualTo(2);
	 
	    doReturn(100).when(spiedList).size();
	    assertThat(spiedList.size()).isEqualTo(100);
	}
	
	
}
