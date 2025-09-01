package main1;
import java.util.ArrayList;
import java.util.Scanner;

class Series {
    private ArrayList<SeriesModel> seriesList;
    private Scanner scanner;

    public Series() {
        seriesList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("LATEST SERIES - 2025");
        System.out.println("*****************************************************");
        System.out.println("Enter (1) to launch menu or any other key to exit");
        
        String input = scanner.nextLine();
        if (input.equals("1")) {
            showMainMenu();
        } else {
            exitSeriesApplication();
        }
    }

    private void showMainMenu() {
        while (true) {
            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Capture a new series.");
            System.out.println("(2) Search for a series.");
            System.out.println("(3) Update series age restriction");
            System.out.println("(4) Delete a series.");
            System.out.println("(5) Print series report - 2025");
            System.out.println("(6) Exit Application.");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    captureSeries();
                    break;
                case "2":
                    searchSeries();
                    break;
                case "3":
                    updateSeries();
                    break;
                case "4":
                    deleteSeries();
                    break;
                case "5":
                    seriesReport();
                    break;
                case "6":
                    exitSeriesApplication();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            
            System.out.println("Enter (1) to launch menu or any other key to exit");
            String input = scanner.nextLine();
            if (!input.equals("1")) {
                exitSeriesApplication();
                break;
            }
        }
    }

    public void captureSeries() {
        System.out.println("CAPTURE A NEW SERIES");
        System.out.println("**********************************************");
        
        System.out.print("Enter the series id: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter the series name: ");
        String name = scanner.nextLine();
        
        String age = "";
        boolean validAge = false;
        while (!validAge) {
            System.out.print("Enter the series age restriction: ");
            age = scanner.nextLine();
            validAge = validateAgeRestriction(age);
            if (!validAge) {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
            }
        }
        
        System.out.print("Enter the number of episodes for " + name + ": ");
        String episodes = scanner.nextLine();
        
        SeriesModel newSeries = new SeriesModel(id, name, age, episodes);
        seriesList.add(newSeries);
        
        System.out.println("Series processed successfully!!!");
    }

    public boolean validateAgeRestriction(String age) {
        try {
            int ageValue = Integer.parseInt(age);
            return ageValue >= 2 && ageValue <= 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void searchSeries() {
        System.out.print("Enter the series id to search: ");
        String id = scanner.nextLine();
        
        SeriesModel foundSeries = null;
        for (SeriesModel series : seriesList) {
            if (series.seriesId.equals(id)) {
                foundSeries = series;
                break;
            }
        }
        
        System.out.println("---");
        if (foundSeries != null) {
            System.out.println("SERIES ID: " + foundSeries.seriesId);
            System.out.println("SERIES NAME: " + foundSeries.seriesName);
            System.out.println("SERIES AGE RESTRICTION: " + foundSeries.seriesAge);
            System.out.println("SERIES NUMBER OF EPISODES: " + foundSeries.seriesNumberOfEpisodes);
        } else {
            System.out.println("Series with Series Id: " + id + " was not found!");
        }
        System.out.println("---");
    }

    public void updateSeries() {
        System.out.print("Enter the series id to update: ");
        String id = scanner.nextLine();
        
        SeriesModel foundSeries = null;
        int index = -1;
        for (int i = 0; i < seriesList.size(); i++) {
            if (seriesList.get(i).seriesId.equals(id)) {
                foundSeries = seriesList.get(i);
                index = i;
                break;
            }
        }
        
        if (foundSeries == null) {
            System.out.println("Series with Series Id: " + id + " was not found!");
            return;
        }
        
        System.out.print("Enter the series name: ");
        String name = scanner.nextLine();
        
        String age = "";
        boolean validAge = false;
        while (!validAge) {
            System.out.print("Enter the age restriction: ");
            age = scanner.nextLine();
            validAge = validateAgeRestriction(age);
            if (!validAge) {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
            }
        }
        
        System.out.print("Enter the number of episodes: ");
        String episodes = scanner.nextLine();
        
        SeriesModel updatedSeries = new SeriesModel(id, name, age, episodes);
        seriesList.set(index, updatedSeries);
        
        System.out.println("Series updated successfully!!!");
    }

    public void deleteSeries() {
        System.out.print("Enter the series id to delete: ");
        String id = scanner.nextLine();
        
        SeriesModel foundSeries = null;
        int index = -1;
        for (int i = 0; i < seriesList.size(); i++) {
            if (seriesList.get(i).seriesId.equals(id)) {
                foundSeries = seriesList.get(i);
                index = i;
                break;
            }
        }
        
        if (foundSeries == null) {
            System.out.println("Series with Series Id: " + id + " was not found!");
            return;
        }
        
        System.out.print("Are you sure you want to delete series " + id + " from the system? Yes (y) to delete: ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("y")) {
            seriesList.remove(index);
            System.out.println("---Series with Series Id: " + id + " WAS deleted!---");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    public void seriesReport() {
        if (seriesList.isEmpty()) {
            System.out.println("No series data available.");
            return;
        }
        
        for (int i = 0; i < seriesList.size(); i++) {
            SeriesModel series = seriesList.get(i);
            System.out.println("Series " + (i + 1));
            System.out.println("---");
            System.out.println("SERIES ID: " + series.seriesId);
            System.out.println("SERIES NAME: " + series.seriesName);
            System.out.println("SERIES AGE RESTRICTION: " + series.seriesAge);
            System.out.println("NUMBER OF EPISODES: " + series.seriesNumberOfEpisodes);
            System.out.println("---");
        }
    }

    public void exitSeriesApplication() {
        System.out.println("Exiting application. Goodbye!");
        scanner.close();
        System.exit(0);
    }

    // Test helper methods
    public void addTestSeries(SeriesModel series) {
        seriesList.add(series);
    }

    public SeriesModel searchSeriesById(String id) {
        for (SeriesModel series : seriesList) {
            if (series.seriesId.equals(id)) {
                return series;
            }
        }
        return null;
    }

    public boolean updateTestSeries(String id, String name, String age, String episodes) {
        for (int i = 0; i < seriesList.size(); i++) {
            if (seriesList.get(i).seriesId.equals(id)) {
                SeriesModel updatedSeries = new SeriesModel(id, name, age, episodes);
                seriesList.set(i, updatedSeries);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTestSeries(String id) {
        for (int i = 0; i < seriesList.size(); i++) {
            if (seriesList.get(i).seriesId.equals(id)) {
                seriesList.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<SeriesModel> getSeriesList() {
        return seriesList;
    }

    public void clearSeriesList() {
        seriesList.clear();
    }
}