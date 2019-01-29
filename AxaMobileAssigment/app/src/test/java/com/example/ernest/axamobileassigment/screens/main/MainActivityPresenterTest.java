package com.example.ernest.axamobileassigment.screens.main;

import android.content.Context;
import android.content.Intent;

import com.example.ernest.axamobileassigment.constants.Constants;
import com.example.ernest.axamobileassigment.model.City;
import com.example.ernest.axamobileassigment.model.Gnome;
import com.example.ernest.axamobileassigment.networking.GnomesApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {

    // region constants ----------------------------------------------------------------------------



    // endregion constants -------------------------------------------------------------------------


    // region helper fields ------------------------------------------------------------------------

    @Mock Context mContextMock;
    @Mock Intent mIntent;

    MockWebServer mockWebServer;

    GnomesApi mGnomesApiMocked;

    private Gnome gnomeMock = setGnome();

    @Mock MainActivityMVP.View mViewMocked;

    // end region helper fields --------------------------------------------------------------------

    MainActivityPresenter SUT;

    @Before
    public void setup() throws Exception {

        mockWebServer = new MockWebServer();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mockWebServer.enqueue(new MockResponse().setBody(Constants.JSON_RESPONSE));

        mGnomesApiMocked = retrofit.create(GnomesApi.class);

        SUT = new MainActivityPresenter(mContextMock, mGnomesApiMocked);

        SUT.setView(mViewMocked);

        SUT.mGnomesApi = mGnomesApiMocked;


    }

    @Test
    public void setView_correctViewAssigned() throws Exception {
        // Arrange

        // Act

        // Assert
        assertThat(SUT.mView,is(mViewMocked));
    }

    @Test
    public void setView_hideProgressbarCalled() throws Exception {
        // Arrange
        // Act
        // Assert
        verify(mViewMocked,times(1)).hideProgressbar();
    }

    @Test
    public void refreshButton_mViewShowProgresBarIsCalled() throws Exception {
        // Arrange
        // Act
        SUT.refreshButtonClicked();
        // Assert
        verify(SUT.mView,times(1)).showProgressbar();
    }

    //refresh button on response show data called with correct response

    //todo seguir er aqui
    @Test
    public void refreshButton_responseOk_correctGnomeReturned() throws Exception {
        // Arrange

        // Act
        SUT.refreshButtonClicked();
        // Assert
        assertEquals(SUT.gnomes.size(),SUT.gnomes.size());
        assertThat(SUT.gnomes.size(),is(0));
    }

    @Test
    public void refreshButton_responseOk_viewHidesProgressBar() throws Exception {
        // Arrange

        // Act
        SUT.refreshButtonClicked();
        // Assert
        verify(mViewMocked,times(1)).hideProgressbar();
    }

    //refresh button on error view.showError called


    @Test
    public void refresshButton_networkError_viewShowErrorCalled() throws Exception {
        // Arrange
        SUT.mGnomesApi.getGnomes().isCanceled();
        // Act

        // Assert
    }

    @Test
    public void onCellClicked_correctGnomePassed_correctIntentCreated() throws Exception {
        // Arrange

        // Act
        SUT.onGnomeCellClicked(gnomeMock);
        // Assert

    }

    // region helper methods -----------------------------------------------------------------------

    public Gnome setGnome(){
        List<String> gnomeFriends  = new ArrayList();
        gnomeFriends.add("Cogwitz Chillwidget");
        gnomeFriends.add("Tinadette Chillbuster");

        List<String> gnomeProfessions = new ArrayList<>();
        gnomeProfessions.add("Metalworker");
        gnomeProfessions.add("Woodcarver");
        gnomeProfessions.add("Stonecarver");
        gnomeProfessions.add("Tinker");
        gnomeProfessions.add("Tailor");
        gnomeProfessions.add("Potter");

        Gnome gnome = new Gnome();
        gnome.setAge(306);
        gnome.setHairColor("Pink");
        gnome.setHeight(107.75835);
        gnome.setName("Tobus Quickwhistle");
        gnome.setId(0);
        gnome.setThumbnail("http://www.publicdomainpictures.net/pictures/10000/nahled/thinking-monkey-11282237747K8xB.jpg");
        gnome.setWeight(39.065952);
        gnome.setFriends(gnomeFriends);
        gnome.setProfessions(gnomeProfessions);

        return gnome;
    }

    // endregion helper methods --------------------------------------------------------------------

    // region helper classes -----------------------------------------------------------------------

//    public class MockGnomesApi implements GnomesApi {
//        private final BehaviorDelegate<GnomesApi> delegate;
//        private final Map<String, Map<String, List<City>>> ownerRepoContributors;
//
//        public MockGnomesApi(BehaviorDelegate<GnomesApi> delegate) {
//            this.delegate = delegate;
//            ownerRepoContributors = new LinkedHashMap<>();
//
//            // Seed some mock data.
//            addContributor("square", "retrofit", "John Doe", 12);
//            addContributor("square", "retrofit", "Bob Smith", 2);
//            addContributor("square", "retrofit", "Big Bird", 40);
//            addContributor("square", "picasso", "Proposition Joe", 39);
//            addContributor("square", "picasso", "Keiser Soze", 152);
//        }
//
//        @Override public Call<List<City>> contributors(String owner, String repo) {
//            List<City> response = Collections.emptyList();
//            Map<String, List<City>> repoContributors = ownerRepoContributors.get(owner);
//            if (repoContributors != null) {
//                List<City> contributors = repoContributors.get(repo);
//                if (contributors != null) {
//                    response = contributors;
//                }
//            }
//            return delegate.returningResponse(response).contributors(owner, repo);
//        }
//
//        @Override
//        public Call<City> getGnomes() {
//            return null;
//        }
//    }






    // end region helper classes -------------------------------------------------------------------


}