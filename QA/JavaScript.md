# JavaScript - Namaste JavaScript

## 1. How JavaScript works? 

Is Synchronous or Asynchronous?

Is JS Single Threaded or Multi-threaded?

Everything in JS happens inside an Execution Context. Execution Context can be consider as a big box or a container in which whole js code is executed.

How execution context looks like?

| Memory Component                                             | Code  Component                                    |
| ------------------------------------------------------------ | -------------------------------------------------- |
| Where all the variables and functions are stored in key:value pair.Ex - a : 10, func : { ... } | This is where code is executed one line at a time. |

  Memory component is also known as variable environment. 

Code Component is also known as `thread of execution` because it is a like a thread in which whole code is executed one line at a time.

JS is a synchronous single-threaded language. It means JS will only execute one command at a time in a specific order. That means it will only go to the next line once it finishes executing the current line.

## 2. How JavaScript Code is executed? ❤️& Call Stack

**What happens when you run JavaScript Code?**

An Execution context is created.

**Code Example for Execution Context Creation**

```js
var n = 2;
function square(num){ // here num is the parameter of the function
   var ans = num * num;
   return ans;
}

var square2 = square(n); // here num is the argument of the function
var square4 = sqaure(4);
```

When the above code is ran a global execution context is created. This execution context is created in two phases. 

1. The first phases is also known as memory creation phase. For the above code JS scans through the whole code line by line will allocate memory to all the variables and functions. undefined 
2. The second phases is also known as code execution phase.

| Memory Component                             | Code  Component                                              |
| -------------------------------------------- | ------------------------------------------------------------ |
| n : undefined                                | n:2                                                          |
| sqaure :  { ... whole code of the function } | moves to line 6 after line 1 as no action is required to take. |
| sqaure2: undefined                           | square2: 4 function invocation happens.  from below it becomes 4. |
| square4: undefined                           | Square4: 16 similar things happens for this with value 4 as argument. |

* Functions are the heart of javascript. They behave very diferently from other programming languages. 
* Functions here in js are like mini program. Whenever a function is invoked altogether a new execution context is created, where it has a new memory component and code component. 
* Function execution context. Here for execution context we are only concerned for this below code. Memory is allocated to variables and functions inside the function. By function we mean the parameter (num) and for variables `var ans = num * num;`

```javascript
square(num){
   var ans = num * num;
   return ans;
}
```



| Memory Component | Code  Component                                              |
| ---------------- | ------------------------------------------------------------ |
| num: undefined   | num:2. 2 is allocated to the memory                          |
| ans : undefined  | ans : 4. 4 is allocated to the memory                        |
|                  | return keyword tells to return the whole control back to the execution where function was invocked. And it finds the value of the ans in the local memory. |

After the whole execution context of the function will be deleted.

**After the whole program execution is completed the whole global execution context will be deleted.**

Suppose inside a function another function is created so an execution context will be created inside an execution context and so on. So they manage this creation, deletion & control using stack. Also known as call stack.

|      |                      |
| ---- | -------------------- |
|      |                      |
|      |                      |
|      |                      |
|      |                      |
| E2   | Function Call Code 2 |
| E1   | Function Call Code 1 |
| GEC  | Main Code            |

Whenever any JS program is ran this call stack is populated with the whole GEC. After whole code runs complete the GEC is also removed.

`Call stack maintains the order of execution of execution contexts.`

Global Execution Context Creation 

Memory Allocation Phase & Code Execution Phase 

Function Invocation and Execution Context Creation 

What happens while executing return statement

Recap of Code Execution synchronously 

Call Stack in JavaScript 

Other names of the Call Stack in JS

Call is also known as various names

1. Call Stack
2. Execution Context Stack
3. Program Stack
4. Control stack
5. Run time stack
6. Machine Stack



## Hoisting in JavaScript (variables & functions) | Namaste JavaScript Ep. 3

```js
var x = 7;

function getName(){
  console.log("Namaste JavaScript");
}

getName(); // Namaste JavaScript
console.log(x); // 7
```

```javascript
getName(); // Namaste JavaScript
console.log(x); // undefined

var x = 7;

function getName(){
  console.log("Namaste JavaScript");
}
```

```js
getName(); // Namaste JavaScript
console.log(x); // Reference Error : x is not defined. x is not present in the memory.

function getName(){
  console.log("Namaste JavaScript");
}
```

Hoisting is a phenomena in JS by which we can access these variables and functions even before you have initialised.

```javascript
var x = 7;

function getName(){
    console.log("Namaste JavaScript");
}

console.log(getName); 
// f getName(){
//    console.log("Namaste JavaScript");
// }
```

```js
console.log(getName); 
// f getName(){
//    console.log("Namaste JavaScript");
// }

var x = 7;

function getName(){
    console.log("Namaste JavaScript");
}
```

It is happening because of execution context.

```javascript
getName();
console.log(getName); // TypeError: getName is not a function
var x = 7;

var getName = () => {
    console.log("Namaste JavaScript");
}
```

Here getName act as variable. 

# How functions work in JS ❤️ & Variable Environment | Namaste JavaScript Ep. 4

```js
var x = 1;
a();
b();

console.log(x);

function a(){
  var x = 10;
  console.log(x);
}

function b(){
  var x = 100;
  console.log(x);
}

// Output
// 10
// 100
// 1
```



| Memory Component                        | Code  Component                                              |
| --------------------------------------- | ------------------------------------------------------------ |
| X : undefined ---> 1                    | Val x = 1                                                    |
| A :  { ... whole code of the function } | a function is invoked a execution context is created which very much limited to this function. It will create two phases memory and code. the new variable will have a **kind of** new copy in the memory inside the execution context. This execution context (exec) is independent of everything else. now line no 8 it will log the value of x which is 10 as js will try to check the value of x in the local memory space and print the value. and after he completion of the function the whole exec. will be deleted. After that it will also be removed from the call stack and control goes back GEC. |
| B :  { ... whole code of the function } | b function is invoked a execution context is created which very much limited to this function. It will create two phases memory and code. the new variable will have a **kind of** new copy in the memory inside the execution context. This execution context (exec) is independent of everything else. now line no 12 it will log the value of x which is 100 as js will try to check the value of x in the local memory space and print the value. and after he completion of the function the whole exec. will be deleted. After that it will also be removed from the call stack and control goes back GEC. |
| Console.log(x)                          | it will print x value as 1 as it will find value 1 in local memory. |

Call Stack

|                   |                                |
| ----------------- | ------------------------------ |
|                   |                                |
|                   |                                |
|                   |                                |
|                   |                                |
| E2 -- line 11     | Function Call Code 2           |
| E1 --  line no. 7 | Function Call for function a() |
| GEC --- line no 2 | Main Code                      |



# SHORTEST JS Program 🔥window & this keyword | Namaste JavaScript Ep. 5

What is the shortest js program? 
Ans - It is the blank js file. JS setup a global execution context and sets up some memory space even for a blank file and does its job. JS engine creates a global execution context or function execution context with window object and `this` keyword in the global space and can be accessed from anywhere in the code.

So Window is a global object.

```js
this === window
```

> Global Space is a code we write which is not inside a function. 

```js
var a = 10;

function b(){
  var x = 10; // Not is global space
}

console.log(window.a);
console.log(this.a);
console.log(a);
```

# undefined vs not defined in JS 🤔 | Namaste JavaScript Ep. 6

```js
console.log(a); // undefiend before executing this line
var a = 7; 
console.log(x); // not defined

// ----
var a; // undefiend
console.log(a); 
```

Before executing this line js will allocate some memory. undefined is like allocating memory. It is not like null or not having any spaces.

> JavaScript is a loosely typed language or weakly typed language as it doesn't attach its variable to specific data type.

```js
var a;
console.log(a);

a = 10;
console.log(a);

a = "Hello World";
console.log(a);

a = undefined; // not recommend at all as it is not a good practice 
```

# The Scope Chain, 🔥Scope & Lexical Environment | Namaste JavaScript Ep. 7

```js
function a(){
  
}

var b = 10;
a(); // output - 10

// 2
function a(){
  c();
  
  function c(){
    console.log(b); // output - 10
  }
}

var b = 10;
a();
```

```js
function a(){
  var b = 10;
  c();
  function c(){}
}

a();
console.log(b); // output - b is not defined
```



Scope means where you can access a specific variable or function. Scope is directly dependent on lexical environment. 

>  Visual representation on #CM001

whenever a execution context is created a lexical enviornment is also created.



# Closures in JS 🔥 | Namaste JavaScript Episode 10

```js
function x(){
  var a = 7;
  function y(){
    console.log(a);
  }
  a = 100;
  return y;
}

var z = x();
console.log(z); // output - 100, value 7 doesn't persist the reference persists
// ....
z();
```

```js
function z(){
  var b = 900;
  
  function x(){
    var a = 7;
    
    function y(){
      console.log(a,b);
    }
    y();
  }
  x();
}
z(); // 
```

Uses of Closures:

1. Module Design Pattern
2. Currying
3. Functions like once
4. memoize
5. maintaining state in async world
6. setTimeouts
7. Iterators
8. and many more ...



# setTimeout + Closures Interview Question 🔥 | Namaste 🙏 JavaScript Ep. 11

```js
function x(){
  var i = 1;
  setTimeout(function(){
    console.log(i);
  }, 1000);
  console.log("N JS");
}

x();

// N JS
// 1
```

function inside the settimeout forms a closure and remebers the valu of i.  JS waits for none.

setimeout after 3000 ms agains load the function in the call stack and perform its action.

```js
function x(){
  for(var i=1; i<=5; i++){
    setTimeout(function(){
      console.log(i);
    }, i*1000); // due to closure it remebers its reference to i 
    // and 5 copy of function refer  to same i and the loop was running constantly and increments the value 
  }
  
  console.log("N JS");
}

x(); // 5 times it will print 6
```

```js
function x(){
  for(let i=1; i<=5; i++){
    setTimeout(function(){
      console.log(i);
    }, i*1000);
  }
  
  console.log("N JS");
}

x(); // 1 2 3 4 5
```

```js
function x(){
  for(var i=1; i<=5; i++){
    function close(i){
      setTimeout(function(){
        console.log(i);
      }, i*1000);
    }
    close(i);
  }
  
  console.log("N JS");
}

x(); // 1 2 3 4 5
```



































# Important Interview Questions

#### Promises.all vs promises.all settled

`Promise.all` and `Promise.allSettled` are both methods provided by JavaScript for working with multiple promises. Here's a brief comparison:

1. Promise.all:
   - It takes an array (or any iterable) of promises as input.
   - It returns a single promise that resolves when all of the input promises have resolved, or rejects as soon as one of the promises rejects.
   - If any of the input promises rejects, the entire `Promise.all` rejects immediately with the reason of the first rejected promise.
   - Use `Promise.all` when you need to wait for all promises to either fulfill or reject together.

Example:

```javascript
const promises = [promise1, promise2, promise3];

Promise.all(promises).then(results => {
  // Handle successful results
})
.catch(error => {
  // Handle the first error that occurred
});

// example 2

// Simulated asynchronous functions
function fetchDataFromEndpoint(endpoint) {
  return new Promise((resolve, reject) => {
    // Simulating async operation
    setTimeout(() => {
      if (endpoint === 'success') {
        resolve(`Data from ${endpoint}`);
      } else {
        reject(`Error fetching data from ${endpoint}`);
      }
    }, Math.random() * 2000); // Random delay up to 2 seconds
  });
}

// Array of promises representing different async operations
const promises = [
  fetchDataFromEndpoint('success'),
  fetchDataFromEndpoint('success'),
  fetchDataFromEndpoint('error')
];

Promise.all(promises).then(results => {
    console.log("All promises resolved successfully!");
    console.log("Results:", results);
})
.catch(error => {
  console.log("One of the promises rejected with an error!");
  console.error("Error:", error);
});

// example 3
const promise1 = fetch('https://api.example.com/endpoint1');
const promise2 = fetch('https://api.example.com/endpoint2');
const promise3 = fetch('https://api.example.com/endpoint3');

const promises = [promise1, promise2, promise3];

Promise.all(promises).then(results => {
    // Assuming each result is a response object
    return Promise.all(results.map(response => response.json())); // Parse JSON for each response
})
.then(jsonData => {
  // Handle successful results after parsing JSON
  console.log('Data from endpoint 1:', jsonData[0]);
  console.log('Data from endpoint 2:', jsonData[1]);
  console.log('Data from endpoint 3:', jsonData[2]);
})
.catch(error => {
  // Handle the first error that occurred
  console.error('Error:', error);
});
```

1. Promise.allSettled:
   - It takes an array (or any iterable) of promises as input.
   - It returns a single promise that resolves when all of the input promises have settled (fulfilled or rejected).
   - The resolved value is an array of objects representing the fulfillment status of each promise. Each object has a `status` property indicating either `"fulfilled"` or `"rejected"`, and a `value` or `reason` property containing the fulfillment value or rejection reason respectively.
   - Use `Promise.allSettled` when you need to handle the outcome of all promises, regardless of whether they fulfill or reject.

Example:

```javascript
const promises = [promise1, promise2, promise3];

Promise.allSettled(promises)
  .then(results => {
  	results.forEach(result => {
      if (result.status === 'fulfilled') {
        // Handle successful result
        console.log('Fulfilled:', result.value);
      } else {
        // Handle error
        console.log('Rejected:', result.reason);
      }
    });
  });
```

In summary, use `Promise.all` when you need all promises to succeed and want to bail out early if any fail, and use `Promise.allSettled` when you need to handle the outcome of all promises regardless of their individual success or failure.

### String to Object Creation

```js
let str 	= 'a.b.c';
let arr 	= str.split('.');
let temp 	= {};

arr.forEach(v => {
    temp[v] = v;
});

console.log(temp);
```

```js
var chars = "overpopulation".split('');

// If you just want to access a string in an array-like fashion, you can do that without split:
var s = "overpopulation";
for (var i = 0; i < s.length; i++) {
    console.log(s.charAt(i));
}
```

