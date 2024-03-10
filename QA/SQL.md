# SQL

1. DELETE

```sql
DELETE FROM Data WHERE ID=201;
```

2. INSERT 

```sql
INSERT INTO table_name (column1, column2, column3, ...)
VALUES (value1, value2, value3, ...);
```

3. Second Highest Salary

```sql
# 1
select * from employee where salary=(select Max(salary) from employee);

# 2
Select * FROM employee ORDER BY salary DESC OFFSET 1 LIMIT 1;
```

4. SQL query to find third highest salary in company **[Important]**

```sql
# 1. The most simple way that should work in any database is to do following:

SELECT * FROM `employee` ORDER BY `salary` DESC LIMIT 1 OFFSET 2;

# Which orders employees by salary and then tells db to return a single result (1 in LIMIT) counting from third row in result set (2 in OFFSET). It may be OFFSET 3 if your DB counts result rows from 1 and not from 0. This example should work in MySQL and PostgreSQL.


# 2. But there's a catch if you only want the 3rd highest DISTINCT salary. Than you should add the DISTINCT keyword. In case of salary list: 100, 90, 90, 80, 70. In the above query it will produce the 3rd highest salary which is 90. But if you mean the 3rd distinct which is 80 than you should use

SELECT DISTINCT `salary` FROM `employee` ORDER BY `salary` DESC LIMIT 1 OFFSET 2;

# 3. But there's a catch, this will return you only 1 column which is Salary, because in order to operate the distinction operation, DISTINCT can only operate on a specific set of columns. This means we should add another wrapping query to extract the employees (There can be multiple) that matches that result. Thus I added LIMIT 1 at the end.

SELECT * FROM `employee` WHERE `Salary` = (SELECT DISTINCT `Salary` FROM `employee` ORDER BY `salary` DESC LIMIT 1 OFFSET 2 ) LIMIT 1;
```

5. SQL code to give employees average salary

To calculate the average salary for employees in an SQL database, you can use the `AVG` aggregate function. Assuming you have a table named `employees` with a column named `salary`, you can write a SQL query like this:

```sql
SELECT AVG(salary) AS average_salary
FROM employees;
```

This query calculates the average salary from the `salary` column in the `employees` table and aliases the result as `average_salary`.

Make sure to replace `employees` and `salary` with your actual table and column names if they are different.

6. by each department

If you want to calculate the average salary for employees in each department separately, you can use the `GROUP BY` clause along with the `AVG` function. Assuming you have a column named `department` in your `employees` table, the SQL query would look like this:

```sql
SELECT department, AVG(salary) AS average_salary
FROM employees
GROUP BY department;
```

This query groups the data by the `department` column and calculates the average salary for each group. The result will show the average salary for each department along with the department name.

Make sure to replace `employees`, `salary`, and `department` with your actual table and column names if they are different.

7. give name of all duplicate employees

To find the names of all duplicate employees based on a specific criterion (e.g., duplicate names or duplicate employee IDs), you can use a SQL query with the `GROUP BY` and `HAVING` clauses. The following example assumes you have a table named `employees` with a column named `employee_name`:

```sql
SELECT employee_name, COUNT(*) AS occurrence_count
FROM employees
GROUP BY employee_name
HAVING COUNT(*) > 1;
```

This query retrieves the names of employees along with the count of occurrences for each name. The `HAVING COUNT(*) > 1` condition filters out the names that only occur once, leaving only the duplicates.

Make sure to replace `employees` and `employee_name` with your actual table and column names if they are different. Additionally, you may need to adjust the columns and conditions based on your specific criteria for considering duplicates.

8. sort in sql

To sort the result set of a SQL query, you can use the `ORDER BY` clause. This clause allows you to specify one or more columns by which the result set should be sorted. The basic syntax is as follows:

```sql
SELECT column1, column2, ...
FROM table_name
ORDER BY column1 [ASC|DESC], column2 [ASC|DESC], ...;
```

- `column1, column2, ...`: The columns you want to retrieve in the result set.
- `table_name`: The name of the table from which you want to retrieve data.
- `ORDER BY column1 [ASC|DESC], column2 [ASC|DESC], ...`: Specifies the columns and the sort order (ascending or descending).

Here's an example assuming you have a table named `employees` with columns `employee_name` and `salary`, and you want to retrieve the names and salaries of employees sorted by salary in descending order:

```sql
SELECT employee_name, salary
FROM employees
ORDER BY salary DESC;
```

In this example, `ORDER BY salary DESC` sorts the result set by the `salary` column in descending order.

Remember to replace `employees`, `employee_name`, and `salary` with your actual table and column names if they are different.

### SQL Exercises for Basic to Advanced Queries

**#1** Create a query that displays EMPFNAME, EMPLNAME, DEPTCODE, DEPTNAME, LOCATION from EMPLOYEE, and DEPARTMENT tables. Make sure the results are in ascending order based on the EMPFNAME and LOCATION of the department.

```sql
SELECT E.EMPFNAME, E.EMPLNAME, E.DEPTCODE,
       D.DEPTNAME, D.LOCATION
       FROM EMPLOYEE E, DEPARTMENT D
       WHERE E.DEPTCODE = D.DEPTCODE
       ORDER BY E.EMPFNAME, D.LOCATION;
```

**#2** Display EMPFNAME and “TOTAL SALARY” for each employee

```sql
SELECT EMPFNAME, SUM(COMMISSION+SALARY) AS "TOTAL SALARY" FROM EMPLOYEE GROUP BY EMPCODE;
```

**#3** Display MAX and 2nd MAX SALARY from the EMPLOYEE table.

```
SELECT
(SELECT MAX(SALARY) FROM EMPLOYEE) MAXSALARY,
(SELECT MAX(SALARY) FROM EMPLOYEE
WHERE SALARY NOT IN (SELECT MAX(SALARY) FROM EMPLOYEE )) as 2ND_MAX_SALARY;
```

**#4** Display the TOTAL SALARY drawn by an analyst working in dept no 20

```
SELECT SUM(SALARY+COMMISSION) AS TOTALSALARY FROM EMPLOYEE
WHERE JOB = 'ANALYST' AND DEPTCODE = 20;
```

**#5** Compute the average, minimum, and maximum salaries of the group of employees having the job of ANALYST.

```sql
ELECT AVG(Salary) AS AVG_SALARY, MIN(Salary) AS MINSALARY, MAX(Salary) AS MAXSALARY
FROM EMPLOYEE WHERE Job = 'ANALYST';
```

**a)** Query to find all departments that are located in Edinburgh:

```
SELECT * FROM DEPARTMENT WHERE LOCATION = 'EDINBURGH';
```

This query uses the WHERE clause to filter the results to only include departments where the CITY column is equal to EDINBURGH.

**b)** Query to find all employees who work in the FINANCE department:

```sql
SELECT * FROM EMPLOYEE JOIN DEPARTMENT ON EMPLOYEE.DEPTCODE = DEPARTMENT.DEPTCODE
WHERE DEPARTMENT.DeptName = 'FINANCE';
```

**c)** Query to find the average salary of employees in each department:



```
SELECT DEPARTMENT.DeptName, AVG(EMPLOYEE.Salary) AS AVERAGE_SALARY
FROM EMPLOYEE JOIN DEPARTMENT ON EMPLOYEE.DEPTCODE = DEPARTMENT.DEPTCODE
GROUP BY DEPARTMENT.DeptName
ORDER BY AVERAGE_SALARY DESC;
```

This query uses a GROUP BY clause to group the results by department and an AVG() function to calculate the average salary for each department. The ORDER BY clause sorts the results in descending order by average salary.

**d)** Query to find the top 10 highest-paid employees:

```
SELECT * FROM EMPLOYEE ORDER BY Salary DESC LIMIT 10;
```

This query uses the ORDER BY clause to sort the results in descending order by salary and the LIMIT clause to limit the results to the top 10 highest-paid employees.



**e)** Query to find all employees who did not get a promotion in the last year:

```
SELECT * FROM EMPLOYEE
WHERE HireDate < CURRENT_DATE - INTERVAL 1 YEAR AND Commission IS NULL;
```

This query selects all employees who have been with the company for at least one year and have not received a commission.

**See also** SQL Table Creation: The Missing Manual

### 4 Queries useful for a DevOps engineer

Here are 4 complex SQL exercises that are important for the DevOps engineer:



**a)** Query to find all database tables which was not part of the backup during last week:

```
SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'dbo' AND LAST_BACKUP_DATE IS NULL OR LAST_BACKUP_DATE < CURRENT_DATE - INTERVAL 1 WEEK;
```

This query uses the INFORMATION_SCHEMA.TABLES view to get a list of all database tables. The WHERE clause is filtering the results to only include tables that were not in the backup plan for the last week.

**b)** Query to find all database indexes that have some level of fragmentation:



```
SELECT INDEX_NAME, FRAGMENTATION_PERCENT FROM sys.dm_db_index_physical_stats
WHERE FRAGMENTATION_PERCENT > 5;
```

This query uses the sys.dm_db_index_physical_stats dynamic management view to get a list of all database indexes and their fragmentation percentage. The WHERE clause filters the results to only include indexes that are more than 5% fragmented.

**c)** Query to find all database queries that are running for longer than 10 seconds:

```
SELECT QUERY_TEXT, ELAPSED_TIME FROM sys.dm_exec_query_stats
WHERE ELAPSED_TIME > 10000;
```

This query uses the sys.dm_exec_query_stats dynamic management view to get a list of all database queries that are currently running and their elapsed time. The WHERE clause filters the results to only include queries that are running for longer than 10 seconds.



**d)** Query to find all database locks with retention time for longer than 1 minute:

```
SELECT RESOURCE_TYPE, RESOURCE_DESCRIPTION, WAIT_TIME FROM sys.dm_exec_locks
WHERE WAIT_TIME > 60000;
```

This query uses the sys.dm_exec_locks dynamic management view to get a list of all database locks that are currently holding for longer than 1 minute.

**a)** Return a list of all employees who are paid above the average salary.

```
SELECT EmpFName, EmpLName, Salary
FROM EMPLOYEE
WHERE Salary > (SELECT AVG(Salary) FROM EMPLOYEE);
```

**b)** Return a list of all employees who have been with the company for more than 5 years.

```
SELECT EmpFName, EmpLName, DateDiff(Now(), HireDate) AS YearsOfService
FROM EMPLOYEE
WHERE YearsOfService > 5;
```

**c)** Return a list of all departments, ordered by the number of employees in each department.

```
SELECT DeptName, COUNT(*) AS NumEmployees
FROM EMPLOYEE
GROUP BY DeptName
ORDER BY NumEmployees DESC;
```

**d)** Return a list of all job titles, ordered by the number of employees in each job title.

```
SELECT Job, COUNT(*) AS NumEmployees
FROM EMPLOYEE
GROUP BY Job
ORDER BY NumEmployees DESC;
```

**e)** Return a list of all managers, ordered by the number of employees managed by each manager.

```sql
SELECT Manager, COUNT(*) AS NumEmployees
FROM EMPLOYEE
GROUP BY Manager
ORDER BY NumEmployees DESC;
```





**What is the difference between UPSERT and insert in MySQL?**

In MySQL, while working with tables, it is important to understand the difference between UPSERT and INSERT statements. The INSERT statement simply adds entirely new records to a table, whereas UPSERT combines the functionalities of both INSERT and UPDATE. UPSERT is employed when you want to insert new records into a table but also need to handle potential conflicts, updating existing records if conflicts arise. This is particularly useful in scenarios where maintaining data integrity is crucial.

Differences Between UPSERT and INSERT

| Criteria                | INSERT                                                       | UPSERT                                                       |
| ----------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Primary Purpose**     | Adds entirely new records to a table.                        | Inserts new records or updates existing ones to maintain data integrity. |
| **Handling Duplicates** | Does not inherently handle duplicate entries; results in an error if conflicts occur. | Specifically designed to handle duplicate entries by updating existing records. |
| **Syntax**              | Follows a straightforward syntax, specifying values for each column in the new record. | Uses the `INSERT ... ON DUPLICATE KEY UPDATE` syntax for conditional updates based on key conflicts. |
