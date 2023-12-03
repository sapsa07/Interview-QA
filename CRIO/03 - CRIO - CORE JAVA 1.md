# CRIO - CORE JAVA 1

Session 1

1. 32:41 - Recap Java Collection
2. 39:57 - What happens to data stored in Java Collections when program execution is completed?
3. 57:09 - De Serialization and Serialization
4. 01:03:00 - Data file format
5. 01: 07:00 - De Serialization

QnA Answers --

- What happens to data stored in Java Collections when program execution is completed?  **(Important)**
  - Data is garbage collected and lost
- How can we persist the data even after completion of program?
  - Use Database, file storage, cloud storage to persist the data.
- Can persistent data be utilized by program written in any language? Why?
  - For eg. SQL Connectors have implementation in various language. Data in mysql is not dependent on any programming language to access it.
- In which format do you think data is transferred across internet? **(Important)**
  - Data Packets. Data is converted to binary or JSON or XML format and broken down into multiple data packets and is transferred to the receiver.
- What are different video formats supported by Youtube?
  - .MOV.
  - .MPEG-1.
  - .MPEG-2.
  - .MPEG4.
  - .MP4.
  - .MPG.
  - .AVI.
  - .WMV.



----

**What happens to the value of the data structure after program execution gets completed?**

In `Java & all other languages`, when a program completes its execution, all the resources associated with the program, including the memory used by data structures, are released. The Java Virtual Machine (JVM) has a garbage collector that automatically identifies and removes objects that are no longer reachable or referenced by the program. This process is known as garbage collection.

Here's what typically happens:

1. **Garbage Collection:** The JVM periodically runs the garbage collector to identify and reclaim memory occupied by objects that are no longer reachable. It frees up the memory occupied by objects that are no longer referenced by any part of the program.
2. **Memory Release:** The memory used by variables, objects, and data structures that were part of the program is released. This memory is returned to the system and can be used by other programs.
3. **Program Termination:** After the garbage collection process and other necessary cleanup tasks, the JVM terminates, and the program's execution is completed.
4. **Operating System Cleanup:** The operating system, in turn, reclaims any system resources allocated to the Java process, such as file handles or network connections.

In summary, once a Java program completes its execution, the memory used by data structures is released through the garbage collection process, and any other system resources are cleaned up. This ensures that the program does not leave behind memory leaks or resource leaks.

##### Getting started with Jackson

#### Objective

Get a quick overview of serialization & deserialization of JSON using Jackson

**What is JSON?**

How will a Python application communicate with a SpringBoot server? They need a common language to convey information, right? For this purpose there are standard formats like JSON, XML etc, made use of for transferring data across systems.

JSON is a readable format with key-value pairs

```json
{
    "symbol": "AAPL",
    "quantity": 100,
    "purchaseDate": "2019-01-02"
}
```

The keys have to be a String whereas values comes from a much wider pool of objects like `String, Integer, Boolean, Array, or even another JSON nesting`

Converting JSON to a Java object for processing is **deserialization** whereas **serialization** involves transforming the Java object to JSON.

-----

#### How would you approach reading data from a JSON file?

Your friend has a list of stocks she’d purchased, in the JSON format.

```json
[
  {
    "symbol": "AAPL",
    "purchaseDate": "2019-01-02"
    "quantity": 100
  },
  {
   "quantity": 10,
   "purchaseDate": "2019-01-02",
   "symbol": "MSFT"
  }
]
```

You being someone who deals with computer stuff is asked to parse it, store each stock data as an object and print out the contents in a specific format. Try to jot down the steps you’ll have to follow to do this. If you’re curious enough, jump in to write the code itself, inside the `parseJsonManually()` method. You can store the data as objects of the `Trade` class provided. The output has to be like this (the ordering is to be followed)

```
purchaseDate=2019-01-02, quantity=100, symbol=AAPL
purchaseDate=2019-01-02, quantity=10, symbol=MSFT
```

How did it go? Takes time, but doable? How about [this](https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=demo) one?

Whether you wrote down the logic or coded it, you would’ve noticed that the key names have to be hardcoded, spaces have to be ignored, not everything is inside quotes so you can’t go on vaguely finding *quotes* and getting stuff inside it.

#### Reading JSON Jacksomatically

We’ll now try to do the same utilizing **Jackson**. This does the work for us

```java
ObjectMapper objectMapper = new ObjectMapper();
Trade[] trades = objectMapper.readValue(file, Trade[].class);

for (Trade trade : trades) {
      System.out.println(trade);
}
```

Ok, where’s the rest of the code? Feel free to put extra spaces but this is all we need to read contents of the JSON file, store them as `Trade` objects and print out the values as required. The `readValue` method takes in the `File` object for our JSON file as well as the class to which we need to *Object Map* the JSON contents to. We’ll read each stock data as a `Trade` object to an `Array`, which is why the second parameter is `Trade[].class`

Run the `Main` class, you’ll be able to see the deserialized output in the terminal



![image](https://storage.googleapis.com/crio-content-container-assets/ME_ME_JACKSON_MODULE_ME_JACKSON_MODULE_BYTE1_image_0.png)





People always want more. With being able to see the stock data printed prettily, your friend now wants a list of stocks with more than 50 shares, back into a JSON file. Jackson `ObjectMapper`provides a `writeValue()` method to write Java objects back to JSON (serialization). The arguments are the deserialized data & the `File` object to write it to. Fill the arguments for `writeValue()` to complete the `writeImportantPurchasesToFile()` method. Verify the content of the JSON file created in `impTrades.json`

```json
[
  {
  	"symbol":"AAPL",
    "quantity":100,
    "purchaseDate":"2019-01-02"
  }
]
```

----

#### <u>Mapping JSON keywords</u>

#### How does Jackson know which variable to map a JSON key to?

If you noticed how we were filtering the stock data earlier, the `quantity` variable was called to fetch the number of shares. How did Jackson know which variable in `Trade` class to map a particular key’s value to? Does something stand out on comparing the `Trade` class & the JSON data?

```java
public class Trade {
  private String symbol;
  private int quantity;
  private String purchaseDate;
  // ....
}
```

```
[
  {
    "symbol": "AAPL",
    "quantity": 100,
    "purchaseDate": "2019-01-02"
  },
  {
    "symbol": "MSFT",
    "quantity": 10,
    "purchaseDate": "2019-01-02"
  }
]
```

*Hmm, looks like the variable names are the same as the key names. Not convinced? Change the variable names & see what Jackson tells you then (via the stack trace in error)*

#### Curious cats

- Isn’t the empty constructor in `Trade` class simply taking up space, do we need that? Or is it the other way around :) (Hint: Put a breakpoint on one of the statements in the constructor with arguments. For empty constructor, add breakpoint on a dummy statement like `"”.isEmpty()`)
- What will Jackson do if there are duplicate keys? When would you not want the default behaviour to happen? How would you go on to override the behaviour? Technically speaking, are duplicate keys in JSON to be dealt by the JSON creator or the user? (Hint: RFC)

<u>**JSON (De)Serialization**</u>

Java stores information as Objects. How to (De) Serialize Java POJO to JSON vice-versa?

```java
public class Member {
    public String name;
    public Integer age;
    public String secretIdentity;
    ...
}
```

Serialization (to json)

Deserialization (to java objects)

```json
{
    "name": "J Jonah Jameson",
    "age": 60,
    "secretIdentity": "Omni Man"
}
```



```java
ObjectMapper om = new ObjectMapper();
Member m = new Member("J Jonah Jameson",29,"Omni Man");
String s = om.writeValue(outputFile, m);
```

How to (De) Serialize Java POJO to JSON vice-versa?

- Serialize the Object data and send. Receiver will deserialize to convert it back to Object. 
- Needed since the network transferring the data doesn’t understand Objects and doesn’t understand Java. The other endpoint need not even be in Java.
- Libraries like **Jackson** simplify this **serialization/deserialization**.

Reference:- [Java ObjectMapper | What it is | How it works (stackchief.com)](https://www.stackchief.com/blog/Java ObjectMapper | What it is | How it works)