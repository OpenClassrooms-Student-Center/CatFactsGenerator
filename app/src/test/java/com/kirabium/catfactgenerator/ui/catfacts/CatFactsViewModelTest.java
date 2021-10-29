package com.kirabium.catfactgenerator.ui.catfacts;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.kirabium.catfactgenerator.data.CatFactsRepository;
import com.kirabium.catfactgenerator.model.CatFact;
import com.kirabium.catfactgenerator.ui.LiveDataTestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CatFactsViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private CatFactsRepository catFactsRepository;

    private CatFactsViewModel viewModel;

    @Before
    public void setUp() {
        // Reinitialize LiveData every test
        MutableLiveData<List<CatFact>> catFactsMutableLiveData = new MutableLiveData<>();

        // Mock the method from Repository returning the LiveData
        given(catFactsRepository.getCatFactsLiveData(0)).willReturn(catFactsMutableLiveData);

        // Set default value to LiveData
        List<CatFact> catFacts = getDefaultCatFacts(0);
        catFactsMutableLiveData.setValue(catFacts);

        viewModel = new CatFactsViewModel(catFactsRepository);
    }

    @Test
    public void nominal_case() {
        // Given
        // Nothing should be needed to test the nominal case : everything is done in the setup !

        // When
        LiveDataTestUtils.observeForTesting(viewModel.getViewStateLiveData(), liveData -> {
            // Then
            // Step 1 for Then : assertions...
            assertEquals(
                new CatFactsViewState(
                    getExpectedCatFacts(0),
                    false // On default page (the page 0), the "previous fact button" is disabled
                ),
                liveData.getValue()
            );

            // ... Step 2 for Then : verify !
            // Very important : we check that the program queried the correct page number from the repository !
            Mockito.verify(catFactsRepository).getCatFactsLiveData(0);
            Mockito.verifyNoMoreInteractions(catFactsRepository);
        });
    }

    @Test
    public void GIVEN_the_next_page_button_is_pressed_THEN_more_facts_are_displayed() {
        // Given
        MutableLiveData<List<CatFact>> catFactsPage1MutableLiveData = new MutableLiveData<>();
        catFactsPage1MutableLiveData.setValue(getDefaultCatFacts(1));
        given(catFactsRepository.getCatFactsLiveData(1)).willReturn(catFactsPage1MutableLiveData);

        // When
        LiveDataTestUtils.observeForTesting(viewModel.getViewStateLiveData(), liveData -> {
            viewModel.onNextPageButtonClicked();

            // Then
            // Step 1 for Then : assertions...
            assertEquals(
                new CatFactsViewState(
                    getExpectedCatFacts(1),
                    true // On page 1, the "previous fact button" must be enabled !
                ),
                liveData.getValue()
            );

            // At the beginning, we query the first page...
            Mockito.verify(catFactsRepository).getCatFactsLiveData(0);
            // ... and then, since we 'simulate' a click on the 'next' button (with viewModel.onNextPageButtonClicked()),
            // we must check that we queried the next correct page (page 1)
            Mockito.verify(catFactsRepository).getCatFactsLiveData(1);
            Mockito.verifyNoMoreInteractions(catFactsRepository);
        });
    }

    @Test
    public void GIVEN_the_next_page_button_is_pressed_twice_THEN_more_facts_are_displayed() {
        // Given
        // We mock the call on repository with param page 1
        MutableLiveData<List<CatFact>> catFactsPage1MutableLiveData = new MutableLiveData<>();
        catFactsPage1MutableLiveData.setValue(getDefaultCatFacts(1));
        given(catFactsRepository.getCatFactsLiveData(1)).willReturn(catFactsPage1MutableLiveData);

        // We mock the call on repository with param page 2
        MutableLiveData<List<CatFact>> catFactsPage2MutableLiveData = new MutableLiveData<>();
        catFactsPage2MutableLiveData.setValue(getDefaultCatFacts(2));
        given(catFactsRepository.getCatFactsLiveData(2)).willReturn(catFactsPage2MutableLiveData);

        // When
        LiveDataTestUtils.observeForTesting(viewModel.getViewStateLiveData(), liveData -> {
            viewModel.onNextPageButtonClicked();
            viewModel.onNextPageButtonClicked();

            // Then
            assertEquals(
                new CatFactsViewState(
                    getExpectedCatFacts(2),
                    true // On page 2, the "previous fact button" must be enabled !
                ),
                liveData.getValue()
            );

            Mockito.verify(catFactsRepository).getCatFactsLiveData(0);
            // Since we 'simulate' a click on the 'next' button (with viewModel.onNextPageButtonClicked()) 2 times in a row,
            // we must check that we queried the correct pages
            Mockito.verify(catFactsRepository).getCatFactsLiveData(1);
            Mockito.verify(catFactsRepository).getCatFactsLiveData(2);
            Mockito.verifyNoMoreInteractions(catFactsRepository);
        });
    }

    @Test
    public void GIVEN_the_next_page_button_is_pressed_then_the_previous_page_button_is_press_THEN_more_facts_are_displayed() {
        // Given
        MutableLiveData<List<CatFact>> catFactsPage1MutableLiveData = new MutableLiveData<>();
        catFactsPage1MutableLiveData.setValue(getDefaultCatFacts(1));
        given(catFactsRepository.getCatFactsLiveData(1)).willReturn(catFactsPage1MutableLiveData);

        // When
        LiveDataTestUtils.observeForTesting(viewModel.getViewStateLiveData(), liveData -> {
            viewModel.onNextPageButtonClicked();
            viewModel.onPreviousPageButtonClicked();

            // Then
            assertEquals(
                new CatFactsViewState(
                    getExpectedCatFacts(0),
                    false // We are back on page 0, the 'previous cat fact' button must be disabled
                ),
                liveData.getValue()
            );

            // 2 times the ViewModel should query the repository with "page 0" :
            // at the beginning and at the end (when we come back from 1) after viewModel.onPreviousPageButtonClicked()
            Mockito.verify(catFactsRepository, times(2)).getCatFactsLiveData(0);
            Mockito.verify(catFactsRepository).getCatFactsLiveData(1);
            Mockito.verifyNoMoreInteractions(catFactsRepository);
        });
    }

    @Test
    public void GIVEN_the_list_is_null_THEN_nothing_is_displayed() {
        // Given
        MutableLiveData<List<CatFact>> nullCatFactsMutableLiveData = new MutableLiveData<>();
        // This LiveData has a value of null
        nullCatFactsMutableLiveData.setValue(null);
        // We override the previous mocking (that we did during the 'setUp()' method)
        given(catFactsRepository.getCatFactsLiveData(0)).willReturn(nullCatFactsMutableLiveData);

        // When
        LiveDataTestUtils.observeForTesting(viewModel.getViewStateLiveData(), liveData -> {

            // Then
            assertEquals(
                new CatFactsViewState(
                    Collections.emptyList(), // No facts ! :(
                    false
                ),
                liveData.getValue()
            );

            Mockito.verify(catFactsRepository).getCatFactsLiveData(0);
            Mockito.verifyNoMoreInteractions(catFactsRepository);
        });
    }

    // region IN
    private static final int DEFAULT_CAT_FACT_COUNT = 10;
    private static final String DEFAULT_CAT_FACT = "DEFAULT_CAT_FACT, PAGE ";
    private static final String NUMBER = ", NUMBER ";

    private List<CatFact> getDefaultCatFacts(int page) {
        List<CatFact> catFacts = new ArrayList<>();

        for (int i = 0; i < DEFAULT_CAT_FACT_COUNT; i++) {
            catFacts.add(
                new CatFact(
                    getDefaultCatFact(page, i), // For page = 0, this will be "DEFAULT_CAT_FACT, PAGE 0, NUMBER 0", for example
                    i // This is not true (this is not the real length of the String), but we don't care in unit tests !)
                )
            );
        }

        return catFacts;
    }

    @NonNull
    private String getDefaultCatFact(int page, int index) {
        return DEFAULT_CAT_FACT + page + NUMBER + index;
    }
    // endregion IN

    // region OUT
    private List<String> getExpectedCatFacts(int page) {
        List<String> catFacts = new ArrayList<>();

        for (int i = 0; i < DEFAULT_CAT_FACT_COUNT; i++) {
            catFacts.add(getDefaultCatFact(page, i));
        }

        return catFacts;
    }
    // endregion OUT
}