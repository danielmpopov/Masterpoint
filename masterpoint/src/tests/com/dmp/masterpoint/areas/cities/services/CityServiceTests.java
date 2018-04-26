package com.dmp.masterpoint.areas.cities.services;

import com.dmp.masterpoint.areas.cities.entities.City;
import com.dmp.masterpoint.areas.cities.models.view.CityViewModel;
import com.dmp.masterpoint.areas.cities.repositories.CityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CityServiceTests {
    private static final String FIRST_CITY_NAME = "First City";
    private static final String SECOND_CITY_NAME = "Second City";
    private static final int LIST_SIZE = 2;

    @Mock
    private CityRepository cityRepository;

    private CityServiceImpl cityService;

    @Before
    public void setUp() {

        cityService = new CityServiceImpl(this.cityRepository, new ModelMapper());

        City firstCity = new City();
        firstCity.setName(FIRST_CITY_NAME);
        firstCity.setId("1");

        City secondCity = new City();
        secondCity.setName(SECOND_CITY_NAME);
        secondCity.setId("2");

        ArrayList<City> cities = new ArrayList<>();
        cities.add(firstCity);
        cities.add(secondCity);

        when(this.cityRepository.findAll()).thenReturn(cities);


    }

    @Test
    public void findAll_shouldReturnCorrectNames() {

        List<CityViewModel> listCities = this.cityService.findAll();

        assertEquals("Not Correct First City Name",FIRST_CITY_NAME, listCities.get(0).getName());
        assertEquals("Not Correct Second City Name", SECOND_CITY_NAME, listCities.get(1).getName());
    }

    @Test
    public void findAll_shouldReturnCorrectListSize() {

        List<CityViewModel> listCities = this.cityService.findAll();

        assertEquals("Not Correct List Size", LIST_SIZE, listCities.size());
    }
}
