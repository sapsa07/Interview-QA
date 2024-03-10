# WordPress PHP JavaScript SQL Interview

**HTML:**

1. The significance of specifying `!DOCTYPE` in HTML.

   All HTML documents must start with a `<!DOCTYPE>` declaration. The declaration is not an HTML tag. It is an "information" to the browser about what document type to expect. Doctype HTML is a declaration that **tells the browser what version of HTML the document is written in**. 

2. Understanding attributes and semantics.

​	Elements such as `<header>`, `<footer>` and `<article>` are all considered semantic because they accurately describe the 	purpose of the element and the type of content that is inside them.

3. What does <div></div> do?

   As a pure container, the <div> element does not inherently represent anything. Instead, it's **used to group content so it can be easily styled using the class or id attributes, marking a section of a document as being written in a different language (using the lang attribute), and so on**.

4. The `<article>` tag specifies independent, self-contained content.

5. HTML attributes provide additional information about HTML elements. The `<a>` tag defines a hyperlink. The `href` attribute specifies the URL of the page the link goes to. The `<img>` tag is used to embed an image in an HTML page. The `src` attribute specifies the path to the image to be displayed.

6. ```php
   add_action( 'wp_ajax_my_action', 'my_action' ); // Fires authenticated Ajax actions for logged-in users.
   add_action( 'wp_ajax_nopriv_my_action', 'my_action' ); // executes for users that are not logged in.
   ```

7. Explaining promises in JavaScript.

The **`Promise`** object represents the eventual completion (or failure) of an asynchronous operation and its resulting value. It allows you to associate handlers with an asynchronous action's eventual success value or failure reason. This lets asynchronous methods return values like synchronous methods: instead of immediately returning the final value, the asynchronous method returns a *promise* to supply the value at some point in the future.

4. Tackling callback hell.

Callback hell is a common issue that can make your JavaScript code difficult to read, maintain, and debug. **By using techniques such as Promises and async / await , and following best practices for organizing your code**, you can overcome callback hell and write clean, efficient, and maintainable JavaScript code.

4. [unlink()](https://www.php.net/manual/en/function.unlink.php) - Deletes a file in php

5. [unset()](https://www.php.net/manual/en/function.unset.php) - Unset a given variable in php

6. ```mysql
   DELETE FROM Data WHERE ID=201
   ```

7. ```mysql
   INSERT INTO table_name (column1, column2, column3, ...)
   VALUES (value1, value2, value3, ...);
   ```

8. what are different types of scopes

- Global Scope.
- Local Scope.
- Block Scope.
- Function Scope.

4. ```java
   function my_awesome_func( $data ) {
     $posts = get_posts( array(
       'author' => $data['id'],
     ));
   
     if ( empty( $posts ) ) {
       return null;
     }
     return $posts[0]->post_title;
   }
   
   add_action( 'rest_api_init', function () {
     register_rest_route( 'myplugin/v1', '/author/(?P<id>\d+)', array(
       'methods' 	=> 'GET',
       'callback' 	=> 'my_awesome_func',
     ));
   });
   ```

5. ```js
   for (var i = 0; i < 3; i++){
     setTimeout(() => console.log(i) , 100); // 3 3 3
   }
   
   for (let i = 0; i < 3; i++) {
     setTimeout(() => console.log(i) , 100); // 0 1 2
   }
   ```

6. Solve using closure

In JavaScript, the issue you're encountering with the setTimeout function inside the loop is related to how closures capture variables. When you use var to declare a variable, it has a function scope, and the value of the variable is captured at the time the setTimeout callback is executed, not when it's defined. This can lead to unexpected behavior in your code.

Using closures, you can solve this problem by creating a new function that captures the current value of i at each iteration. Here's how you can modify your code using closures:

```java
// Using var
for (var i = 0; i < 3; i++) {
    setTimeout((function (index) {
        return function () {
            console.log(index);
        };
    })(i), 100);
}

// Using let
for (let i = 0; i < 3; i++) {
    setTimeout(() => console.log(i), 100);
}
```

In the first loop, a closure is created by immediately invoking a function that returns another function. This inner function captures the current value of i and prints it when executed inside the setTimeout callback.

In the second loop, using let creates a block-scoped variable i, which works as expected without the need for additional closure.

Both of these approaches will output the values 0, 1, and 2 as expected, but they address the issue of closure and variable capturing in different ways.

10. What is the difference between DOM and BOM ?

DOM: Objects that have to do with the currently loaded page (the page is also called the document). It mainly focuses on the structure of the displayed document.

```javascript
document.getElementById("btnID").style.display = "none";
```

BOM: Objects that deal with everything outside the page (the browser window and the desktop screen).It mainly focuses on browser-specific functionality. 

```javascript
Window.close();
```

11. Second Highest Salary

```sql
# 1
select * from employee where salary=(select Max(salary) from employee);

# 2
Select * FROM employee ORDER BY salary DESC OFFSET 1 LIMIT 1;
```

12.

```php
wp_enqueue_script(
  string $handle, 
  string $src = '', 
  string[] $deps = array(), 
  string|bool|null $ver = false, 
  array|bool $args = array()
)
```

13. What is async in programming?

Asynchronous programming is a technique that enables your program to start a potentially long-running task and still be able to be responsive to other events while that task runs, rather than having to wait until that task has finished.

14. ## What is the manage_options permission in WordPress?

    The manage_options permission allows you to access all of the links under “Settings” in the WordPress admin. The manage_options permission gives you access to these seven links:

    - Settings > General
    - Settings > Writing
    - Settings > Reading
    - Settings > Discussion
    - Settings > Permalinks
    - Settings > Miscellaneous
    - Settings > Privacy

16. SQL query to find third highest salary in company **[Important]**

```sql
# 1. The most simple way that should work in any database is to do following:

SELECT * FROM `employee` ORDER BY `salary` DESC LIMIT 1 OFFSET 2;

# Which orders employees by salary and then tells db to return a single result (1 in LIMIT) counting from third row in result set (2 in OFFSET). It may be OFFSET 3 if your DB counts result rows from 1 and not from 0. This example should work in MySQL and PostgreSQL.


# 2. But there's a catch if you only want the 3rd highest DISTINCT salary. Than you should add the DISTINCT keyword. In case of salary list: 100, 90, 90, 80, 70. In the above query it will produce the 3rd highest salary which is 90. But if you mean the 3rd distinct which is 80 than you should use

SELECT DISTINCT `salary` FROM `employee` ORDER BY `salary` DESC LIMIT 1 OFFSET 2;

# 3. But there's a catch, this will return you only 1 column which is Salary, because in order to operate the distinction operation, DISTINCT can only operate on a specific set of columns. This means we should add another wrapping query to extract the employees (There can be multiple) that matches that result. Thus I added LIMIT 1 at the end.

SELECT * FROM `employee` WHERE `Salary` = (SELECT DISTINCT `Salary` FROM `employee` ORDER BY `salary` DESC LIMIT 1 OFFSET 2 ) LIMIT 1;
```

16. Top 12 Features of HTML5

Here’s a list of Top 12 HTML5 Features, which are explained in detail below:

1. Semantic Elements
2. Audio and Video Support
3. Canvas Elements
4. Geolocation API
5. Local Storage
6. Responsive Images
7. Web Workers
8. Drag and Drop API
9. Form Enhancements
10. Web Sockets
11. Micro Data
12. Cross Document Messaging

17. ##### Laravel Request Lifecycle

    ## Introduction

    When using any tool in the "real world", you feel more confident if you understand how that tool works. Application development is no different. When you understand how your development tools function, you feel more comfortable and confident using them.

    The goal of this document is to give you a good, high-level overview of how the Laravel framework works. By getting to know the overall framework better, everything feels less "magical" and you will be more confident building your applications. If you don't understand all of the terms right away, don't lose heart! Just try to get a basic grasp of what is going on, and your knowledge will grow as you explore other sections of the documentation.

    ## Lifecycle Overview

    ##### First Steps

    The entry point for all requests to a Laravel application is the `public/index.php` file. All requests are directed to this file by your web server (Apache / Nginx) configuration. The `index.php` file doesn't contain much code. Rather, it is a starting point for loading the rest of the framework.

    The `index.php` file loads the Composer generated autoloader definition, and then retrieves an instance of the Laravel application from `bootstrap/app.php`. The first action taken by Laravel itself is to create an instance of the application / [service container](https://laravel.com/docs/10.x/container).

    ##### HTTP / Console Kernels

    Next, the incoming request is sent to either the HTTP kernel or the console kernel, depending on the type of request that is entering the application. These two kernels serve as the central location that all requests flow through. For now, let's just focus on the HTTP kernel, which is located in `app/Http/Kernel.php`.

    The HTTP kernel extends the `Illuminate\Foundation\Http\Kernel` class, which defines an array of `bootstrappers` that will be run before the request is executed. These bootstrappers configure error handling, configure logging, [detect the application environment](https://laravel.com/docs/10.x/configuration#environment-configuration), and perform other tasks that need to be done before the request is actually handled. Typically, these classes handle internal Laravel configuration that you do not need to worry about.

    The HTTP kernel also defines a list of HTTP [middleware](https://laravel.com/docs/10.x/middleware) that all requests must pass through before being handled by the application. These middleware handle reading and writing the [HTTP session](https://laravel.com/docs/10.x/session), determining if the application is in maintenance mode, [verifying the CSRF token](https://laravel.com/docs/10.x/csrf), and more. We'll talk more about these soon.

    The method signature for the HTTP kernel's `handle` method is quite simple: it receives a `Request` and returns a `Response`. Think of the kernel as being a big black box that represents your entire application. Feed it HTTP requests and it will return HTTP responses.

    ##### Service Providers

    One of the most important kernel bootstrapping actions is loading the [service providers](https://laravel.com/docs/10.x/providers) for your application. Service providers are responsible for bootstrapping all of the framework's various components, such as the database, queue, validation, and routing components. All of the service providers for the application are configured in the `config/app.php` configuration file's `providers` array.

    Laravel will iterate through this list of providers and instantiate each of them. After instantiating the providers, the `register` method will be called on all of the providers. Then, once all of the providers have been registered, the `boot` method will be called on each provider. This is so service providers may depend on every container binding being registered and available by the time their `boot` method is executed.

    Essentially every major feature offered by Laravel is bootstrapped and configured by a service provider. Since they bootstrap and configure so many features offered by the framework, service providers are the most important aspect of the entire Laravel bootstrap process.

    ##### Routing

    One of the most important service providers in your application is the `App\Providers\RouteServiceProvider`. This service provider loads the route files contained within your application's `routes` directory. Go ahead, crack open the `RouteServiceProvider` code and take a look at how it works!

    Once the application has been bootstrapped and all service providers have been registered, the `Request` will be handed off to the router for dispatching. The router will dispatch the request to a route or controller, as well as run any route specific middleware.

    Middleware provide a convenient mechanism for filtering or examining HTTP requests entering your application. For example, Laravel includes a middleware that verifies if the user of your application is authenticated. If the user is not authenticated, the middleware will redirect the user to the login screen. However, if the user is authenticated, the middleware will allow the request to proceed further into the application. Some middleware are assigned to all routes within the application, like those defined in the `$middleware` property of your HTTP kernel, while some are only assigned to specific routes or route groups. You can learn more about middleware by reading the complete [middleware documentation](https://laravel.com/docs/10.x/middleware).

    If the request passes through all of the matched route's assigned middleware, the route or controller method will be executed and the response returned by the route or controller method will be sent back through the route's chain of middleware.

    ##### Finishing Up

    Once the route or controller method returns a response, the response will travel back outward through the route's middleware, giving the application a chance to modify or examine the outgoing response.

    Finally, once the response travels back through the middleware, the HTTP kernel's `handle` method returns the response object and the `index.php` file calls the `send`method on the returned response. The `send` method sends the response content to the user's web browser. We've finished our journey through the entire Laravel request lifecycle!

    ##### Focus on Service Providers

    Service providers are truly the key to bootstrapping a Laravel application. The application instance is created, the service providers are registered, and the request is handed to the bootstrapped application. It's really that simple!

    Having a firm grasp of how a Laravel application is built and bootstrapped via service providers is very valuable. Your application's default service providers are stored in the `app/Providers` directory.

    By default, the `AppServiceProvider` is fairly empty. This provider is a great place to add your application's own bootstrapping and service container bindings. For large applications, you may wish to create several service providers, each with more granular bootstrapping for specific services used by your application.

    In Short

    1. The entry point for all requests to a Laravel application is the `public/index.php` file. 

    2. Then retrieves an instance of the Laravel application from `bootstrap/app.php`. 

    3. The HTTP kernel extends the `Illuminate\Foundation\Http\Kernel` class, which defines an array of `bootstrappers` that will be run before the request is executed. These classes handle internal Laravel configuration that you do not need to worry about.

    4. The HTTP kernel also defines a list of HTTP [middleware](https://laravel.com/docs/10.x/middleware) that all requests must pass through before being handled by the application.

       The method signature for the HTTP kernel's `handle` method is quite simple: it receives a `Request` and returns a `Response`. 

    5. One of the most important kernel bootstrapping actions is loading the [service providers](https://laravel.com/docs/10.x/providers) for your application ie. database, queue, validation, and routing components. All of the service providers for the application are configured in the `config/app.php`configuration file's `providers` array.

       Laravel will iterate through this list of providers and instantiate each of them. After instantiating the providers, the `register` method will be called on all of the providers. Then, once all of the providers have been registered, the `boot`method will be called on each provider.

    6. One of the most important service providers in your application is the `App\Providers\RouteServiceProvider`. This service provider loads the route files contained within your application's `routes` directory. Go ahead, crack open the `RouteServiceProvider` code and take a look at how it works!

       Once the application has been bootstrapped and all service providers have been registered, the `Request` will be handed off to the router for dispatching. The router will dispatch the request to a route or controller, as well as run any route specific middleware.

       If the request passes through all of the matched route's assigned middleware, the route or controller method will be executed and the response returned by the route or controller method will be sent back through the route's chain of middleware.

       ##### Finishing Up

       Once the route or controller method returns a response, the response will travel back outward through the route's middleware, giving the application a chance to modify or examine the outgoing response.

       Finally, once the response travels back through the middleware, the HTTP kernel's `handle` method returns the response object and the `index.php` file calls the `send` method on the returned response. The `send` method sends the response content to the user's web browser. We've finished our journey through the entire Laravel request lifecycle!

18. SQL Query

```sql
Create Table Statement : 
========================

CREATE TABLE products (
 product_id INT PRIMARY KEY,
 product_name VARCHAR(50),
 category VARCHAR(50)
);

INSERT INTO products (product_id, product_name, category) VALUES
 (1, 'Laptops', 'Electronics'),
 (2, 'Jeans', 'Clothing'),
 (3, 'Chairs', 'Home Appliances');


CREATE TABLE sales (
 product_id INT,
 year INT,
 total_sales_revenue DECIMAL(10, 2),
 PRIMARY KEY (product_id, year),
 FOREIGN KEY (product_id) REFERENCES products(product_id)
);

INSERT INTO sales (product_id, year, total_sales_revenue) VALUES
 (1, 2019, 1000.00),
 (1, 2020, 1200.00),
 (1, 2021, 1100.00),
 (2, 2019, 500.00),
 (2, 2020, 600.00),
 (2, 2021, 900.00),
 (3, 2019, 300.00),
 (3, 2020, 450.00),
 (3, 2021, 400.00);


Task : 
write a sql query to find the products whose total sales revenue has increased every year. Include the product_id , product_name and category in the result.

SELECT 
    p.product_id,
    p.product_name,
    p.category
FROM 
    products p
JOIN 
    sales s ON p.product_id = s.product_id
WHERE 
	NOT EXISTS (
    SELECT year FROM  sales s2 WHERE  s.product_id = s2.product_id AND s.year = s2.year + 1 AND s.total_sales_revenue <= s2.total_sales_revenue);

+------------+--------------+------------+
| product_id | product_name |  category  |
+------------+--------------+------------+
|     1      |   Laptops    | Electronics|
|     2      |    Jeans     |  Clothing  |
+------------+--------------+------------+

# Solution 2
SELECT 
    p.product_id,
    p.product_name,
    p.category
FROM 
    products p
JOIN 
    sales s ON p.product_id = s.product_id
WHERE 
    NOT EXISTS (
        SELECT 
            year
        FROM 
            sales s2
        WHERE 
            s.product_id = s2.product_id
            AND s.year = s2.year + 1
            AND s.total_sales_revenue <= s2.total_sales_revenue
    )
    AND s.year = (SELECT MAX(year) FROM sales WHERE product_id = p.product_id);

+------------+--------------+------------+
| product_id | product_name |  category  |
+------------+--------------+------------+
|     2      |    Jeans     |  Clothing  |
+------------+--------------+------------+

# Solution 3
with cte as (
  select *, lag(total_sales_revenue) over(partition by product_id order by year asc) as previous_year_revenue from sales_deloitte), ctel as (select *, total_sales_revenue - previous_year_revenue as revenue_diff from cte), cte2 as (select product_id from cte1 group by product_id having min(revenue_diff) > 0) select t2.* from cte2 as t1 inner join inner join products_deloitte as t2 on t1.product_id = t2.product_id
```



17. 
