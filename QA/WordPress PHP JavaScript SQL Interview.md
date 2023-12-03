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
