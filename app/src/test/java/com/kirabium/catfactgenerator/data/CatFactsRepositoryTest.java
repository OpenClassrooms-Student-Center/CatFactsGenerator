package com.kirabium.catfactgenerator.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import com.kirabium.catfactgenerator.model.CatFact;
import com.kirabium.catfactgenerator.model.CatFactsResponse;
import com.kirabium.catfactgenerator.ui.LiveDataTestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RunWith(MockitoJUnitRunner.class)
public class CatFactsRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private CatFactsApi catFactsApi;

    @InjectMocks
    private CatFactsRepository catFactsRepository;

    @Before
    public void setUp() {
        given(catFactsApi.getListOfCats(0)).willReturn(mockedCall);

        given(mockedResponse.body()).willReturn(mockedCatFactsResponse);
        given(mockedCatFactsResponse.getCatFacts()).willReturn(mockedCatFacts);
    }

    @Test
    public void nominal_case() {
        // Given
        // Let's call the repository method
        LiveData<List<CatFact>> result = catFactsRepository.getCatFactsLiveData(0);

        // Capture the callback waiting for data
        verify(catFactsApi.getListOfCats(0)).enqueue(callbackArgumentCaptor.capture());

        // When
        // Trigger the response ourselves
        callbackArgumentCaptor.getValue().onResponse(mockedCall, mockedResponse);

        // Then
        // Assert the result is posted to the LiveData
        LiveDataTestUtils.observeForTesting(result, liveData -> {
            assertEquals(mockedCatFacts, liveData.getValue());
        });
    }

    @Test
    public void GIVEN_the_api_call_fails_THEN_livedata_exposes_null() {
        // Given
        // Let's call the repository method
        LiveData<List<CatFact>> result = catFactsRepository.getCatFactsLiveData(0);

        // Capture the callback waiting for data
        verify(catFactsApi.getListOfCats(0)).enqueue(callbackArgumentCaptor.capture());

        // When
        // Trigger the response ourselves
        callbackArgumentCaptor.getValue().onFailure(mockedCall, mock(Throwable.class));

        // Then
        // Assert the result is posted to the LiveData
        LiveDataTestUtils.observeForTesting(result, liveData -> {
            assertNull(liveData.getValue());
        });
    }

    // region IN
    @Captor
    private ArgumentCaptor<Callback<CatFactsResponse>> callbackArgumentCaptor;

    @Mock
    private Call<CatFactsResponse> mockedCall;
    // endregion IN

    // region OUT
    @Mock
    private Response<CatFactsResponse> mockedResponse;

    @Mock
    private CatFactsResponse mockedCatFactsResponse;

    @Mock
    private List<CatFact> mockedCatFacts;
    // endregion OUT
}