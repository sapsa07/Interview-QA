
# SELECT D.DeptName, COUNT(DEPTCODE) 
# FROM EMPLOYEE E 
# JOIN DEPARTMENT D ON e.DEPTCODE = D.DEPTCODE
# GROUP BY DEPTCODE;

SELECT d.DeptName, COUNT(e.DEPTCODE) AS num_employees
FROM EMPLOYEE e
JOIN department d ON e.DEPTCODE = d.DEPTCODE
GROUP BY d.DEPTCODE;

SELECT Job, COUNT(Job) as no_of_employees from EMPLOYEE GROUP by Job;

# SELECT EmpCode from EMPLOYEE GROUP BY Salary ORDER BY Salary DESC LIMIT 1 OFFSET 2;

SELECT Salary
FROM EMPLOYEE
GROUP BY Salary
HAVING Salary >= 3000
ORDER BY Salary DESC;
# LIMIT 1 OFFSET 2;

SELECT column_name
FROM information_schema.columns
WHERE table_schema = 'employees' AND table_name = 'employee';
