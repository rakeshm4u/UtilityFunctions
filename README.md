# UtilityFunctions
Fillo is an Excel API Library for Java
This library helps treat the excel tabs as database tables and the sheet content as table rows and columns. This helps to treat the excel as a database and using sql query-like syntax we can retrieve the data from excel sheets. We can use this in the DataProvider method of TestNG framework to fetch data from local excel to fed to the test scripts
Now, it supports SELECT, UPDATE & INSERT queries with or without WHERE clause.

```
Dependancy to be added,
<dependency>
    <groupId>com.codoid.products</groupId>
    <artifactId>fillo</artifactId>
    <version>1.21</version>
</dependency>
```
To use as DataProvider in TestNG
Refer the 'FilloExample.java' in this repo to get the data as 2-D array
The sheet name and the test case name to be fed from the calling @DataProvider script to this file
Sample test script to use this DataProvider method,
