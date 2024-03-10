# 1. Top store for movie sales

**Instruction**

Write a query to return the name of the store and its manager, that generated the most sales.

**Table: sales_by_store** 

Movie sales by store

```bash
  col_name   | col_type
-------------+----------
 store       | text
 manager     | text
 total_sales | numeric
```

**Sample results**

```bash
   store   |   manager
-----------+--------------
 Woodridge | Jon Stephens
```

```sql
select store, manager from sales_by_store 
WHERE total_sales = (
    SELECT MAX(total_sales)
    FROM sales_by_store
);
```

```sql
SELECT store, manager, total_sales
FROM sales_by_store
ORDER BY total_sales DESC
LIMIT 1;
```

# 2. Top 3 movie categories by sales

**Instruction**

- Write a query to find the top 3 film categories that generated the most sales.
- The order of your results doesn't matter. 

**Table: sales_by_film_category** 

Total sales by movie categories.

```bash
  col_name   | col_type
-------------+----------
 category    | text
 total_sales | numeric
```

**Sample results**

```bash
category
-----------
Category 1
Category 2
Category 3
```

```sql
SELECT category
FROM sales_by_film_category
ORDER BY total_sales DESC
LIMIT 3;
```

# 3. Top 5 shortest movies

**Instruction**

- Write a query to return the titles of the 5 shortest movies by duration.
- The order of your results doesn't matter. 

**Table: film** 

```
       col_name       |  col_type
----------------------+--------------------------
 film_id              | integer
 title                | text
 description          | text
 release_year         | integer
 language_id          | smallint
 original_language_id | smallint
 rental_duration      | smallint
 rental_rate          | numeric
 length               | smallint
 replacement_cost     | numeric
 rating               | text
```

**Sample results**

```bash
        title
---------------------
 MOVIE 1
 MOVIE 2
 MOVIE 3
 MOVIE 4
 MOVIE 5
```

```sql
SELECT title
FROM film
ORDER BY length ASC
LIMIT 5;
```

# 5. Monthly revenue

**Instruction**

- Write a query to return the total movie rental revenue for each month.

- For Postgres: you can use `EXTRACT(MONTH FROM colname)` and `EXTRACT(YEAR FROM colname)` to extract month and year from a timestamp column.

- For Python/Pandas: you can use pandas DatetimeIndex() to extract Month and Year

  - ```python
    df['year'] = pd.DatetimeIndex(df['InsertedDate']).year
    ```

**Table: payment** 

Movie rental payment transactions table

```
   col_name   | col_type
--------------+--------------------------
 payment_id   | integer
 customer_id  | smallint
 staff_id     | smallint
 rental_id    | integer
 amount       | numeric
 payment_ts   | timestamp with time zone
```

**Sample results**

```bash
 year | mon |   rev
------+-----+----------
 2020 |   1 |  123.45
 2020 |   2 |  234.56
 2020 |   3 |  345.67
```

```postgresql
// In Postgresql
SELECT EXTRACT(YEAR FROM payment_ts) AS year, EXTRACT(MONTH FROM payment_ts) AS mon, SUM(amount) AS rev
FROM payment
GROUP BY year, mon
ORDER BY year, mon;
```

```mysql
# In MySQL
SELECT YEAR(payment_ts) AS year, MONTH(payment_ts) AS mon, SUM(amount) AS rev
FROM payment
GROUP BY year, mon
ORDER BY year, mon;
```

