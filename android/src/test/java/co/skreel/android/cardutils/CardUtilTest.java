package co.skreel.android.cardutils;

import org.junit.After;
import org.junit.Before;

import co.skreel.android.models.cards.Card;

import static org.junit.Assert.*;

public class CardUtilTest {

    private Card SUT;

    @Before
    public void setUp() throws Exception {
        SUT = new Card();
    }

    @After
    public void tearDown() throws Exception {
    }
}