package main1;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SeriesTest {
    private Series series;
    
    @Before
    public void setUp() {
        series = new Series();
    }
    
    @Test
    public void TestSearchSeries() {
       
        SeriesModel testSeries = new SeriesModel("101", "Extreme Sports", "12", "10");
        series.addTestSeries(testSeries);

        SeriesModel foundSeries = series.searchSeriesById("101");
        
        assertNotNull("Series should be found", foundSeries);
        assertEquals("Series ID should match", "101", foundSeries.seriesId);
        assertEquals("Series name should match", "Extreme Sports", foundSeries.seriesName);
        assertEquals("Series age should match", "12", foundSeries.seriesAge);
        assertEquals("Series episodes should match", "10", foundSeries.seriesNumberOfEpisodes);
    }
    
    @Test
    public void TestSearchSeries_SeriesNotFound() {
  
        SeriesModel testSeries = new SeriesModel("101", "Extreme Sports", "12", "10");
        series.addTestSeries(testSeries);
        

        SeriesModel foundSeries = series.searchSeriesById("999");
        
        assertNull("Series should not be found for incorrect ID", foundSeries);
    }
    
    @Test
    public void TestUpdateSeries() {
        SeriesModel testSeries = new SeriesModel("101", "Extreme Sports", "12", "10");
        series.addTestSeries(testSeries);
        
     
        boolean updateResult = series.updateTestSeries("101", "Extreme Sports 2025", "10", "12");
        
        assertTrue("Series should be updated successfully", updateResult);
  
        SeriesModel updatedSeries = series.searchSeriesById("101");
        assertNotNull("Updated series should be found", updatedSeries);
        assertEquals("Series name should be updated", "Extreme Sports 2025", updatedSeries.seriesName);
        assertEquals("Series age should be updated", "10", updatedSeries.seriesAge);
        assertEquals("Series episodes should be updated", "12", updatedSeries.seriesNumberOfEpisodes);
    }
    
    @Test
    public void TestDeleteSeries() {
        SeriesModel testSeries = new SeriesModel("101", "Extreme Sports", "12", "10");
        series.addTestSeries(testSeries);
        
        assertNotNull("Series should exist before deletion", series.searchSeriesById("101"));
  
        boolean deleteResult = series.deleteTestSeries("101");
        
        assertTrue("Series should be deleted successfully", deleteResult);
        
        assertNull("Series should not exist after deletion", series.searchSeriesById("101"));
    }
    
    @Test
    public void TestDeleteSeries_SeriesNotFound() {
 
        SeriesModel testSeries = new SeriesModel("101", "Extreme Sports", "12", "10");
        series.addTestSeries(testSeries);
  
        boolean deleteResult = series.deleteTestSeries("999");
        
        assertFalse("Deletion should fail for non-existent series", deleteResult);
        
  
        assertNotNull("Original series should still exist", series.searchSeriesById("101"));
    }
    
    @Test
    public void TestSeriesAgeRestriction_AgeValid() {
        assertTrue("Age 2 should be valid", series.validateAgeRestriction("2"));
        assertTrue("Age 12 should be valid", series.validateAgeRestriction("12"));
        assertTrue("Age 18 should be valid", series.validateAgeRestriction("18"));
    }
    
    @Test
    public void TestSeriesAgeRestriction_SeriesAgeInValid() {
        assertFalse("Age 1 should be invalid", series.validateAgeRestriction("1"));
        assertFalse("Age 19 should be invalid", series.validateAgeRestriction("19"));
        assertFalse("Age 0 should be invalid", series.validateAgeRestriction("0"));
        assertFalse("Age 100 should be invalid", series.validateAgeRestriction("100"));
        assertFalse("Non-numeric age should be invalid", series.validateAgeRestriction("abc"));
        assertFalse("Empty age should be invalid", series.validateAgeRestriction(""));
    }
    
    @Test
    public void TestSeriesReport() {
        SeriesModel testSeries1 = new SeriesModel("101", "Extreme Sports", "12", "10");
        SeriesModel testSeries2 = new SeriesModel("102", "Bargain Hunters", "10", "10");
        series.addTestSeries(testSeries1);
        series.addTestSeries(testSeries2);

        assertEquals("Series list should contain 2 series", 2, series.getSeriesList().size());
        assertEquals("First series name should match", "Extreme Sports", series.getSeriesList().get(0).seriesName);
        assertEquals("Second series name should match", "Bargain Hunters", series.getSeriesList().get(1).seriesName);
    }
    
    @Test
    public void TestClearSeriesList() {
        SeriesModel testSeries1 = new SeriesModel("101", "Extreme Sports", "12", "10");
        SeriesModel testSeries2 = new SeriesModel("102", "Bargain Hunters", "10", "10");
        series.addTestSeries(testSeries1);
        series.addTestSeries(testSeries2);
    
        assertEquals("Series list should contain 2 series", 2, series.getSeriesList().size());
        
  
        series.clearSeriesList();
   
        assertEquals("Series list should be empty after clearing", 0, series.getSeriesList().size());
    }
}