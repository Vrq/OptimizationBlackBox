package main.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Varq on 2016-12-14.
 */
@RunWith(JUnit4.class)
public class DataLoaderTest {

    String resourcesDir = "src/main/resources/";
    String performanceTableFileName = "performanceTable.csv";
    String criteriaFileName = "criteria.csv";
    DataLoader dataLoader;

    @Before
    public void setUp() {
        dataLoader = new DataLoader(resourcesDir);
    }

    @Test
    public void testReadingListFromCSV() {
        List<String> criteriaListReadFromCSV = dataLoader.getDataListFromCSV(criteriaFileName);
        String expectedCriteria = "Buying";
        Assert.assertEquals(criteriaListReadFromCSV.get(0),expectedCriteria);
        Assert.assertEquals(criteriaListReadFromCSV.size(), 3);
    }

    @Test
    public void testReadingMatrixFromCSV() {
        List<String> performanceValueListReadFromCSV = dataLoader.getDataMatrixFromCSV(performanceTableFileName).get(0);
        List<String> expectedPerformanceValueList = new ArrayList<>();
        expectedPerformanceValueList.add("4");
        expectedPerformanceValueList.add("1");
        expectedPerformanceValueList.add("2");
        expectedPerformanceValueList.add("1");
        expectedPerformanceValueList.add("3");
        expectedPerformanceValueList.add("2");
        Assert.assertEquals(performanceValueListReadFromCSV,expectedPerformanceValueList);
    }



}
