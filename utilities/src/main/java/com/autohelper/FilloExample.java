package com.autohelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class FilloExample {

    String filePath = System.getProperty("user.dir") + "data.xlsx"; //Commit your own file
    String driverPath = System.getProperty("user.dir") + "chromedriver.exe"; //commit your own chromedriver

    @Test(dataProvider = "getDataUsingFillo",priority = 5)
    public void testUsingSelenium(String username) {
        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver driver = new ChromeDriver();
        driver.get("https://github.com/");
        driver.findElement(By.id("user[login]")).sendKeys(username);
    }

    @Test(description = "This is a create sheet and data example",priority = 1)
    public void testCreate() {
        Connection connection = null;
        try {
            Fillo fillo = new Fillo();
            connection = fillo.getConnection(filePath);
            connection.createTable("Sample1", new String[]{"RollNo", "First Name", "Last Name",
                "Language", "Marks"});

        } catch (FilloException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    @Test(description = "This is a Delete sheet and data example",priority = 6)
    public void testDelete() {
        Connection connection = null;
        try {
            Fillo fillo = new Fillo();
            connection = fillo.getConnection(filePath);
            connection.deleteTable("Sample1");

        } catch (FilloException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    @Test(description = "This is a Insert data example",priority = 2)
    public void testInsert() {
        String query = "INSERT INTO \"Sample1\"(RollNo,\"First Name\",\"Last Name\",Language,\"Marks\") VALUES(43,'John','Ryan','Python',71)";
        Connection connection = null;
        try {
            Fillo fillo = new Fillo();
            connection = fillo.getConnection(filePath);
            connection.executeUpdate(query);
        } catch (FilloException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

    }

    @Test(description = "This is a Update data example",priority = 3)
    public void testUpdate() {
        String query = "UPDATE \"Sample1\" SET Language='Go' WHERE (StudentID=43 and \"First Name\"='John' AND \"Last Name\"='Ryan')";
        Connection connection = null;
        try {
            Fillo fillo = new Fillo();
            connection = fillo.getConnection(filePath);
            connection.executeUpdate(query);

        } catch (FilloException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    @Test(description = "This is a Select data example",priority = 4)
    public void testSelect() {
        String query = "SELECT * FROM Sample1 WHERE Language='Go'";
        Connection connection = null;
        try {
            Fillo fillo = new Fillo();
            connection = fillo.getConnection(filePath);
            Recordset recordset = connection.executeQuery(query);
            while (recordset.next()) {
                System.out.println(recordset.getField("RollNo") + " " + recordset.getField("First Name") + " "
                    + recordset.getField("Last Name") + " " + recordset.getField("Language") + " "
                    + recordset.getField("Marks"));
            }
            recordset.close();
            connection.close();
        } catch (FilloException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    @DataProvider
    public Object[][] getDataUsingFillo() {
        Object[][] object = null;
        Connection connection = null;
        Recordset recordset = null;
        try {
            Fillo fillo = new Fillo();
            connection = fillo.getConnection(filePath); //Pass the file path
            recordset = connection.executeQuery("SELECT * FROM Sample1"); //Datasheet is the sheet name
            int numberOfRows = recordset.getCount();
            int i = 0;
            object = new Object[numberOfRows][];
            while (recordset.next()) {
                object[i][0] = recordset.getField("First Name");
                i++;
            }

        } catch (FilloException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            recordset.close();
            connection.close();
        }
        return object;
    }
}
