package edu.cmu.sphinx.linguist.language.grammar;

import static com.google.common.io.Resources.getResource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.io.IOException;
import java.net.URL;

import org.testng.annotations.Test;

import edu.cmu.sphinx.linguist.acoustic.UnitManager;
import edu.cmu.sphinx.linguist.dictionary.Dictionary;
import edu.cmu.sphinx.linguist.dictionary.FastDictionary;
import edu.cmu.sphinx.models.Sphinx4Model;


public class FSTGrammarTest {

    @Test
    public void testForcedAlignerGrammar() throws IOException {
        URL dictionaryUrl = getResource(getClass(), "FSTGrammarTest.dic");
        URL noisedictUrl = getResource(Sphinx4Model.class,
                                       "acoustic/wsj/noisedict");

        Dictionary dictionary = new FastDictionary(dictionaryUrl,
                                                   noisedictUrl,
                                                   null,
                                                   false,
                                                   null,
                                                   false,
                                                   false,
                                                   new UnitManager());

        URL url = getResource(getClass(), "FSTGrammarTest.gram");
        FSTGrammar grammar = new FSTGrammar(url.getPath(),
                                            true,
                                            true,
                                            true,
                                            true,
                                            dictionary);
        grammar.allocate();
        assertThat(grammar.getGrammarNodes(), hasSize(14));
    }
}