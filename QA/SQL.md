# Database, RDMS & SQL

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

SELECT * 
FROM `employee` 
WHERE `Salary` = (
  SELECT DISTINCT `Salary` 
  FROM `employee` 
  ORDER BY `salary` DESC 
  LIMIT 1 
  OFFSET 2 
) LIMIT 1;
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

## Normalization in RDMS

Normalization is the process of dividing the larger table into smaller and links them using relationships. The normal form is used to reduce redundancy from the database table. ***\*Normalization\**** is the process of minimizing ***\*redundancy\**** from a relation or set of relations. Redundancy in relation may cause insertion, deletion, and update anomalies. So, it helps to minimize the redundancy in relations. ***\*Normal forms\**** are used to eliminate or reduce redundancy in database tables.

##### Types of Normal Forms:

![DBMS Normalization](https://static.javatpoint.com/dbms/images/dbms-normalization.png)

| Normal Form                                               | Description                                                  |
| :-------------------------------------------------------- | :----------------------------------------------------------- |
| [1NF](https://www.javatpoint.com/dbms-first-normal-form)  | A relation is in 1NF if it contains an atomic value.         |
| [2NF](https://www.javatpoint.com/dbms-second-normal-form) | A relation will be in 2NF if it is in 1NF and all non-key attributes are fully functional dependent on the primary key. |
| [3NF](https://www.javatpoint.com/dbms-third-normal-form)  | A relation will be in 3NF if it is in 2NF and no transition dependency exists. |
| BCNF                                                      | A stronger definition of 3NF is known as Boyce Codd's normal form. |
| [4NF](https://www.javatpoint.com/dbms-forth-normal-form)  | A relation will be in 4NF if it is in Boyce Codd's normal form and has no multi-valued dependency. |
| [5NF](https://www.javatpoint.com/dbms-fifth-normal-form)  | A relation is in 5NF. If it is in 4NF and does not contain any join dependency, joining should be lossless. |

### Example of Normalization



## First Normal Form

If a relation contain composite or multi-valued attribute, it violates first normal form or a relation is in first normal form if it does not contain any composite or multi-valued attribute. A relation is in first normal form if every attribute in that relation is ***\*singled valued attribute\****.

> First Normal Form == Single Valued Attribute

Relation STUDENT in table 1 is not in 1NF because of multi-valued attribute STUD_PHONE. Its decomposition into 1NF has been shown in table 2. 

![Example](https://media.geeksforgeeks.org/wp-content/uploads/20231101151006/1239.png)

Example 2

```
ID   Name   Courses
------------------
1    A      c1, c2
2    E      c3
3    M      C2, c3
```

In the above table Course is a multi-valued attribute so it is not in 1NF. Below Table is in 1NF as there is no multi-valued attribute

```
ID   Name   Course
------------------
1    A       c1
1    A       c2
2    E       c3
3    M       c2
3    M       c3
```







# Transaction in DBMS

Transaction in Database Management Systems (DBMS) can be defined as a set of operations to maintain the consistency and integrity of the data present in a database.

Transactions follow 4 properties, namely, Atomicity, Consistency, Isolation, and Durability. Generally, these are referred to as ACID properties of transactions in DBMS. ACID is the acronym used for transaction properties.

### i) Atomicity

This property ensures that either all operations of a transaction are executed or it is aborted. Each transaction is treated as a single unit (like an atom). Atomicity is achieved through 

​	commit and rollback operations, 

i.e. changes are made to the database only if all operations related to a transaction are completed, and if it gets interrupted, any changes made are rolled back using rollback operation to bring the database to its last saved state.

### ii) Consistency

This property of a transaction keeps the database consistent before and after a transaction is completed. There is no ambiguity.

### iii) Isolation

This property states that two transactions must not interfere with each other, i.e. if some data is used by a transaction for its execution, then any other transaction can not concurrently access that data until the first transaction has completed. It ensures that the integrity of the database is maintained and we don’t get any ambiguous values. Thus, any two transactions are isolated from each other. 

This property is enforced by the concurrency control subsystem of DBMS.

### iv) Durability

This property ensures that the changes made to the database after a transaction is completely executed, are durable. It indicates that permanent changes are made by the successful execution of a transaction. In the event of any system failures or crashes, the consistent state achieved after the completion of a transaction remains intact. 

The recovery subsystem of DBMS is responsible for enforcing this property.

https://www.geeksforgeeks.org/sql-transactions/

### Types of Keys in Relational Model (Candidate, Super, Primary, Alternate and Foreign)

### 1. Primary key

It is the first key used to identify one and only one instance of an entity uniquely.



### 2. Candidate key

- A candidate key is an attribute or set of attributes that can uniquely identify a tuple.
- Except for the primary key, the remaining attributes are considered a candidate key. The candidate keys are as strong as the primary key.

**For example:** In the EMPLOYEE table, id is best suited for the primary key. The rest of the attributes, like SSN, Passport_Number, License_Number, etc., are considered a candidate key.

### 3. Super Key

Super key is an attribute set that can uniquely identify a tuple. A super key is a superset of a candidate key.

![DBMS Keys](https://static.javatpoint.com/dbms/images/dbms-keys5.png)

**For example:** In the above EMPLOYEE table, for(EMPLOEE_ID, EMPLOYEE_NAME), the name of two employees can be the same, but their EMPLYEE_ID can't be the same. Hence, this combination can also be a key. The super key would be EMPLOYEE-ID (EMPLOYEE_ID, EMPLOYEE-NAME), etc.

### 4. Foreign key

- Foreign keys are the column of the table used to point to the primary key of another table.

![DBMS Keys](https://static.javatpoint.com/dbms/images/dbms-keys6.png)

### 5. Alternate key

There may be one or more attributes or a combination of attributes that uniquely identify each tuple in a relation. These attributes or combinations of the attributes are called the candidate keys. One key is chosen as the primary key from these candidate keys, and the remaining candidate key, if it exists, is termed the alternate key. **In other words,** the total number of the alternate keys is the total number of candidate keys minus the primary key. The alternate key may or may not exist. If there is only one candidate key in a relation, it does not have an alternate key.

**For example,** employee relation has two attributes, Employee_Id and PAN_No, that act as candidate keys. In this relation, Employee_Id is chosen as the primary key, so the other candidate key, PAN_No, acts as the Alternate key.

![DBMS Keys](https://static.javatpoint.com/dbms/images/dbms-keys7.png)

### 6. Composite key

Whenever a primary key consists of more than one attribute, it is known as a composite key. 



## SQL Exercises for Basic to Advanced Queries

**#1** Create a query that displays EMPFNAME, EMPLNAME, DEPTCODE, DEPTNAME, LOCATION from EMPLOYEE, and DEPARTMENT tables. Make sure the results are in ascending order based on the EMPFNAME and LOCATION of the department.

```sql
SELECT E.EMPFNAME, E.EMPLNAME, E.DEPTCODE,D.DEPTNAME, D.LOCATION
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
