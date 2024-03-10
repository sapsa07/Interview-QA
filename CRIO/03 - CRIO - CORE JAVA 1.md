# CRIO - CORE JAVA 1

<u>Session 1</u>

1. 32:41 - Recap Java Collection
2. 39:57 - What happens to data stored in Java Collections when program execution is completed?
3. 57:09 - De Serialization and Serialization
4. 01:03:00 - Data file format
5. 01: 07:00 - De Serialization
6. Activity 1
7. 01:55 - Annotations

<u>Session 2</u>



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

- Complete the (De)Serialization Logic in parseJSONJacksomattically method.

```java
public void parseJsonJacksomatically(File inputFile, File outputFile) throws JsonParseException, JsonMappingException, IOException {
    // Initialize ObjectMapper
    ObjectMapper om = new ObjectMapper();

    // De-Serialize and print the POJOs
    Trade[] t = om.readValue(inputFile, Trade[].class);

    // Trade t = om.readValue(inputFile, Trade.class);
    // it will throw runtime error as it is expecting jso format data but getting an array on json data

    for (Trade trade : t) {
      System.out.println(trade);
    }

    // Serialize the POJOs to outputFile
    om.writeValue(outputFile, t);
  }
```



- How does Jackson know which variable to map a JSON key to?

```java
Trade[] t = om.readValue(inputFile, Trade[].class);
// Since we are passing Trade.class very time as a reference to map with the json key
```

- What happens if change the name of symbol to symbl in POJO? Edit and Run the Program.

​	Error. Unrecognized property exception.

- What happens if change data type of quantity to String in POJO? Edit and Run the program.

​	It will work without any error because it will be consumed as a string only. Ans will be printed in the output file as a string.

- What will Jackson if there are duplicate keys in JSON? Try it out!

  **<u>Overwritten</u>** Instead of MSFT, A is the new output

  ```bash
  Trade [purchaseDate=2019-01-02, quantity=100, symbol=AAPL]
  Trade [purchaseDate=2019-01-02, quantity=10, symbol=A]
  Running completed
  ```

  

#### Jackson & Getters/Setters

All variables in the `Trade` class were `public`. We might need to restrict access to these in some cases. You are given another `TradePrivate` class with `private` variables. Have a glance at it and run the `parseJsonJacksomaticallyPrivate()` method with the `trades` file. Getting any errors?

Jackson by default doesn’t see variables with non-public access modifiers. We’ll have to provide *getter* & *setter* methods in the `TradePrivate` class for that. This is how we add getter & setter for the `symbol` variable.

```java
public String getSymbol() {
    return symbol;
}

public void setSymbol(String symbol) {
    this.symbol = symbol;
}
```

Add getters & setters for other variables similarly. The `parseJsonJacksomaticallyPrivate()` method will run now.

#### How would I know Jackson is using these methods to serialize & deserialize?

We can use the debugger to check the program flow. Put a breakpoint inside any of the setter methods and run the `Main` class on debug mode. Verify the`main()` method is calling

```java
main.parseJsonJacksomaticallyPrivate(tradesFancier, outputFile);
```

And the `parseJsonJacksomaticallyPrivate()` method has only the deserialization call (`objectMapper.readValue()`)

Did it stop? What do you think happened?

![image](https://storage.googleapis.com/crio-content-container-assets/ME_ME_JACKSON_MODULE_ME_JACKSON_MODULE_BYTE1_image_2.png)

So, Jackson uses the setter methods to deserialize JSON for non-public variables.

Now you know why defining setters makes the non-public variables deserializable.

---

#### Curious Cats

- You noticed the setter being called when deserializing the JSON string into a Java object. What do you think will get called when serialization happens? Can you prove that by setting an appropriate breakpoint in the `TradePrivate` class?
- Can you find any annotation to make non-public fields serializable without adding getters?
- Add only a getter for a private variable and it should only be serializable, right? Can you try out if it can be deserialized for me? Is there any anomaly? Do feel free to see if the same happens with only a setter
- See if there’s any difference in the serialized output if we set the getter as *getSymbol* & *getSYmbol*? Try out different variations as well

​	*No difference it doesnot matter. Result will be same*

- Is there any annotation to make non-public field serializable without Getters? Google it.

​	I would like to use the fields as the canonical source of serialization config and thus don't want jackson to look at properties at all. I can do this on an individual class basis with the annotation:

```java
 @JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
```

But I don't want to have to put this on every single class. Is it possible to configure this globally? Like add some to the Object Mapper?

```java
ObjectMapper mapper  = new ObjectMapper();
mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
```

[How does the Jackson mapper know what field in each Json object to assign to a class object?](https://stackoverflow.com/questions/22162916/how-does-the-jackson-mapper-know-what-field-in-each-json-object-to-assign-to-a-c)

[Ask Question](https://stackoverflow.com/questions/ask)

Let's say I have a Json object like this:

```java
{
    "name": "Bob Dole",
    "company": "Bob Dole Industries",
    "phone": {
        "work": "123-456-7890",
        "home": "234-567-8901",
        "mobile": "345-678-9012"
    }
}
```

And to help me read it, I use Jackson's Object Mapper with the following class:

```java
public class Contact {
        public static class Phone {
        private String work;
        private String home;
        private String mobile;

        public String getWork() { return work; }
        public String getHome() { return home; }
        public String getMobile() { return mobile; }

        public void setWork(String s) { work = s; }
        public void setHome(String s) { home = s; }
        public void setMobile(String s) { mobile = s; }
    }

    private String name;
    private String company;
    private Phone phone;

    public String getName() { return name; }
    public String getCompany() { return company; }
    public Phone getPhone() { return phone; }

    public void setName(String s) { name = s; }
    public void setCompany(String s) { company = s; }
    public void setPhone(Phone p) { phone = p; }
}
```

My question is, how (using the simplest explanation possible), does the Object mapper "deserialize" the Json object? I thought it was matching variable names, but changing them by a few letters didn't affect the output. Then, I tried switching the order of the set() functions, but that didn't do anything. I also tried both, but that was also useless. I'm guessing there's something more sophisticated at work here, but what?

## Without Annotations:

Without any annotations, it does what is called `POJO` mapping, it just uses [reflection](http://docs.oracle.com/javase/tutorial/reflect/member/index.html) on the instance members and uses some rules about how to map the keys in the json to the names of the instance members. **note: it works on `private` members as well as `public` or `package protected` as well*

If it doesn't match the names of the instance members, then it starts trying to match the `getXXX` and `setXXX` methods, if it doesn't match anything then it gives up.

## With Annotations:

It uses the metadata supplied by the annotations to do the mapping and conversions.

It is always better to explicitly use the annotations when you have the source to add them to, then there is no guess work on what gets mapped to what.

*Remember explicit is always better than implicit!*

## This is all well documented on the WIKI:

[Mapping](http://wiki.fasterxml.com/JacksonInFiveMinutes) and [Annotations](http://wiki.fasterxml.com/JacksonAnnotations)

## JSON Schema:

I am creating JSON Schema definitions for all my new projects now to document what is and isn't valid JSON according to the schema rules engine. It is a great way to document your data structures and eliminate parsing errors.

----

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

```java
@JsonIgnoreProperties(ignoreUnknown=true)
```



# QMoney

#### <u>QMoney Pvt. Ltd</u>

QMoney is a financial service company that offers world-class solutions in Portfolio Management, Investment Advisory, and Wealth Management. Founded in 2010 and headquartered in Bangalore, the company has gained the trust of 1 million+ clients to manage their finances and investment needs.

In recent years, the company has grown to become one of the pioneers of online trading by building the first-of-its-kind platform to make trading efficient and effortless for its customers.

#### <u>Note from the Product Manager</u>

Welcome to QMoney! Our customer base is expanding with over 100 accounts opening every day. Our portfolio managers assist clients by analyzing their stock portfolios and making buy/sell recommendations. Portfolio analysis can be very time-consuming. With the number of clients growing at a rapid rate, portfolio managers need tools to help analyze client portfolios efficiently. To do that, our designers have come up with the following user experience for portfolio managers:



![image](https://storage.googleapis.com/crio-content-container-assets/ME_QMONEY_V2_MODULE_ME_QMONEY_V2_MODULE_JSON_PARSING_1682599951_image_0.png)

The QMoney frontend team has started working on visualizing user portfolios. You will be joining the QMoney backend team which is responsible for generating all data required by the frontend team.

Your key responsibilities in this project will be defined by your Tech Lead who will also guide you with the necessary information to complete your milestones in this project.

Your Tech Lead has provided a structured execution plan to build, maintain, and support the portfolio manager library of QMoney. Start with the setup tasks and proceed with the milestone tasks to complete building a stock analyzer tool for our portfolio managers.

#### QMoney Architecture

Start with the overall architecture of QMoney by studying this diagram.



![image](https://storage.googleapis.com/crio-content-container-assets/ME_QMONEY_V2_MODULE_ME_QMONEY_V2_MODULE_JSON_PARSING_1682599952_image_1.png)

##### <u>QMoney Application Architecture</u>

#### Objective

Read data from a user transactions file to extract a list of stocks held by the client.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_QMONEY_V2_MODULE_ME_QMONEY_V2_MODULE_JSON_PARSING_1682599952_image_2.png)

##### <u>Data flow diagram for this module</u>

![image](https://img.youtube.com/vi/IKclid-nbCc/hqdefault.jpg)

#### Background

The Spring framework is used to build the Java backend for QMoney. The QMoney frontend, on the other hand, is written in React - a JavaScript framework. It is not uncommon in web-based applications to have the frontend and backend written in different languages. In order to communicate seamlessly between the frontend and backend, a standard data interchange format is hence required.

<u>JSON is a popular text-based format used by machines to achieve this. Working with JSON is made simpler in Java by using the Jackson library. It helps translate data between different formats. Jackson’s `ObjectMapper` class can be used to map Java objects to JSON (this process is called **serialization**) and back (**deserialization**).</u>

#### Primary goals

In this module, you will focus on the following:

1. Read a JSON-formatted file containing historical trades made by a user.
2. Deserialize the data.
3. Extract the list of stocks held by the user.

### Note

1. The process of converting a Java object to its JSON representation is called **serialization**.
2. The reverse process of converting a JSON representation of a data model to a Java object is called **deserialization**.



![img](https://lh7-us.googleusercontent.com/VEJ8LIT0OMczF3Ry1DYrxXxTD2jk81eS6jZJJur2KTay2MLlDctcWSzYaUILb7ytZfSxYeRcvJ-npscH-IgPmr8s30y3ew-YSB5MCZpJxSas-S_t2O3ukClr1fUkdgNXeAHGYRVlboAMGVbrt3JY2Q)



# QMoney

#### Structure of Java Program

Let’s begin the debrief by talking about our observations on how a simple Java program is structured.

**main()** method

The first thing we observe is the presence of a `main()` method with some arguments. Those of you who have programmed in C/C++ will surely recognize this construct. In Java, as in the C family languages, the main method is the entry point to the program. We can pass arguments from the command line to the main method - since main is the entry point to the application, it can also serve as arguments to the application itself. In our case, we are passing the string `trades.json` to the program which we interpret as a filename and proceed with our logic.

```
public static void main(String[] args) {}
```

*What is interesting though is that the `main()` method has a public access specifier and a static modifier applied to it. What this means is that Java does it’s main application file to be an object to call the main method - this is intentional as the main method is being called from an area external to the program.*

**Note**: Though we spoke of the main method being the entry point to the program, we can technically execute code even before it. This is done through static blocks. We will leave you with a question: how is it running before the `main()` method?

**Public, private and protected**

These terms are called access specifiers in Java. They control visibility of member fields and methods to entities outside the class that defines them.

- `public`: Class members are accessible everywhere.
- `private`: Class members are not visible outside the class.
- `protected`: Class members are visible only to it’s child classes.

Food for thought: Why do you even need access specifiers? What’s the point?

**Constructor**

A constructor is a special member function defined inside a class. It has the same name as the class, and is called when the object is constructed with the "new" keyword. It generally serves the purpose of defining values for the declared member fields, and is normally defined with the public access specifier. Refer to `PortfolioTrade.java`, you should be able to see the public constructor for that class.

Food for thought: Can you have a private access specifier on a constructor?

**Class and variable names, and other conventions**

Java uses camel case as it’s naming convention. Can you start class names with smaller case? Yes, but you shouldn’t. While this is probably the most easily noticeable convention, there are others that are officially recommended. Refer to this link to know more: [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)



#### Serialization and Deserialization

Let us begin with the with the definition:

*Serialization is the process of translating data structures or object state into a format that can be stored or transmitted and reconstructed later.* [[src](https://en.wikipedia.org/wiki/Serialization)]

Computers (programs, in general) need a common way to talk to each other. For example: How would a Java program talk to some functionality written in C++? How would an app on your mobile phone communicate what it needs to a server in a different country? If you've used databases, how does a Java program read something from a database (where data might be stored in a completely different format)? Serialization is one of the ways to achieve this. The `format that can be stored or transmitted` referred to in the definition above - that format is JSON in our use case.

Deserialization is the reverse process. In our use-case, we accept an incoming JSON and convert it to a Java object.



#### Role of Jackson

Jackson is a library that is popularly used for serialization/deserialization in Java.

**Jackson Annotations**

We have a fair understanding of the relationship between JSON and Java serialization / deserialization. What if:

1. the JSON being sent is missing a field?
2. What if it has an extra field?
3. What if we want to map a key with a different name to a particular field?

We can do these things through Jackson annotations. Think of them as modifying the rules with which Java does serialization/deserialization.

For example, let’s say we have a new member variable in `PortfolioTrade.java` which tracks the time of trade along with the date. How do we exclude this variable from the serialized data?

The `@JsonIgnore` annotation comes in handy. There are several annotations that can tell Jackson to customize the serialization/deserialization process. Refer to [Jackson Annotation Examples](https://www.baeldung.com/jackson-annotations) for more information.

**ObjectMapper**

1. In which part of the code is serialization/deserialization actually done?
2. How does `PortfolioManagerApplicationTest.java` actually verify that the conversion to/from JSON was done correctly?

We can gain an understanding by peeking into the code of `PortfolioManagerApplicationTest.java`.

Digging into the code and following the methods it calls in the application, we come to see it uses something called as ObjectMapper. It uses two very important classes of methods.

1. `ObjectMapper().readX(..)`, and
2. `ObjectMapper().writeX(..)`

Let us focus on the read class of methods. The actual method `ObjectMapper` uses is:

```
readValue(String content, Class<T> valueType)
```

It accepts a `String` and a `Class` as parameters. The method looks at the content JSON and then deserializes into an object of type specified by the Java generic `Class<T>`.

The `write` methods perform the similar process in reverse.

#### Setters and Getters

Okay, so we have fields to hold the data that is needed. But if we notice, all the fields have an access modifier of `private`, so how exactly is this set?

We generate `getters` and `setters` for every field. We can do this in two ways:

1. One is by getting your IDE([VSCode](https://code.visualstudio.com/docs/java/java-editing)) to do it for us through a shortcut.
2. The other popular way is to use a library called `Lombok`. `Lombok` is very helpful as it assists in generating all the boilerplate code that is necessary. For example, by marking a class with `@Data` annotation, it will automatically generate all the required `getters` and `setters`.

In doing so, while serializing and deserializing, Java will `get` and `set` the appropriate values from the class. We don’t have to call the `getters` or `setters` explicitly as it is already taken care of.

Looking at our `PortfolioTrade` class, we have:

```java
public class PortfolioTrade {
 public PortfolioTrade() {}
  
 public static enum TradeType {
   BUY,
   SELL
 }

 private String symbol;
 private int quantity;
 private TradeType tradeType;
 private LocalDate purchaseDate;

 public PortfolioTrade(String symbol, int quantity, LocalDate purchaseDate) {
   this.symbol 				= symbol;
   this.quantity 			= quantity;
   this.purchaseDate 	= purchaseDate;
   this.tradeType 		= TradeType.BUY;
 }

 public void setSymbol(String symbol) {
   this.symbol = symbol;
 }

 public void setQuantity(int quantity) {
   this.quantity = quantity;
 }

 public void setTradeType(TradeType tradeType) {
   this.tradeType = tradeType;
 }

 public void setPurchaseDate(LocalDate purchaseDate) {
   this.purchaseDate = purchaseDate;
 }

 public String getSymbol() {
   return symbol;
 }

 public int getQuantity() {
   return quantity;
 }

 public LocalDate getPurchaseDate() {
   return purchaseDate;
 }

 public TradeType getTradeType() {
   return tradeType;
 }
}
```

#### Session 2

<u>Independent Developer Series - Java debuging - 1 - Assessment</u>

### Assignment - JUnit

#### Benefits of Unit Testing

- Allows to make changes to code easily as the tests will spot any bugs that new changes might introduce
- Improves code quality as tests are written to check edge cases and writing tests also forces you to think about better implementation
- Since unit testing is performed in the development stage itself, bugs can be found and resolved very early
- Reduces development cost due to finding bugs before they reach production
- Reduces time required to ship new code because previously written tests can be re-used

We'll look at some of these advantages practically in the next couple of sections.

**Find out how assertEquals and assertSame differ. In addition, find a scenario where assertSame can be used.**

`assertEquals` and `assertSame` are both assertion methods commonly used in unit testing frameworks like JUnit to verify expected behavior in tests, but they serve different purposes.

### `assertEquals`:

- **Purpose**: Compares whether two objects are equal in terms of their contents.
- **Comparison**: Compares the values of the objects using their `equals()` method or equivalent.
- **Usage**: Typically used to compare objects for equality based on their content or state.
- **Example**: `assertEquals(expected, actual)`

### `assertSame`:

- **Purpose**: Checks if two object references refer to the same object in memory.
- **Comparison**: Compares the references of the objects (i.e., memory addresses), not their content.
- **Usage**: Used to verify that two references point to the exact same object instance.
- **Example**: `assertSame(expected, actual)`

### Scenario for `assertSame`:

A scenario where `assertSame` can be used is when testing singleton objects or ensuring that a method returns the exact same object reference.

```java
javaCopy code
import static org.junit.jupiter.api.Assertions.*;

class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

class SingletonTest {
    @Test
    public void testSingletonInstance() {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        assertSame(instance1, instance2);
    }
}
```

In this scenario, the `testSingletonInstance` method ensures that the `getInstance` method of the `Singleton` class returns the same instance of the `Singleton` object every time it is called. `assertSame` is used to verify that `instance1` and `instance2` are indeed references to the same object in memory. If they are not the same instance, the test will fail, indicating a problem with the singleton implementation.

**How would you assert if some method completes execution within some predefined time?**

To assert that a method completes execution within a predefined time, you can use timeouts in your testing framework. In JUnit, you can utilize the `Timeout` rule or the `assertTimeout` method to achieve this. Here's how you can do it:

### Using `Timeout` Rule:

```java
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class MyTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2); // Define a global timeout of 2 seconds

    @Test
    public void testMethodCompletesWithinTime() {
        // Call the method you want to test
        // Ensure it completes within the predefined time
    }
}
```

In this example, the `globalTimeout` rule specifies a timeout of 2 seconds for each test method within the test class. If the method being tested takes longer than 2 seconds to complete, the test will fail with a timeout error.

### Using `assertTimeout` Method:

```java
import static org.junit.jupiter.api.Assertions.assertTimeout;
import java.time.Duration;
import org.junit.jupiter.api.Test;

public class MyTest {
    @Test
    public void testMethodCompletesWithinTime() {
        // Call the method you want to test and specify the timeout
        assertTimeout(Duration.ofSeconds(2), () -> {
            // Method execution code
        });
    }
}
```

In this example, `assertTimeout` ensures that the method being tested completes within 2 seconds. If the method takes longer than the specified duration, the test will fail with a timeout error.

Both approaches allow you to assert that a method completes execution within a predefined time limit, providing control over the maximum allowed duration for the method to finish its execution.

---

*When we have a large number of tests, sometimes, we need to skip some unit tests that take a longer time. How would you do this using JUnit annotations?*

*You wrote multiple variants of the <u>test5PMIsValidMeetingTime()</u> test earlier. If you check, the difference among these tests is just a single value, the input time. How would you use JUnit annotations to reuse the same test with different parameter values?*

In JUnit, you can skip certain unit tests using the `@Ignore` annotation. This annotation allows you to temporarily disable a test method, which can be useful when you want to skip tests that take longer to execute or are not currently relevant.

Here's an example of how you can use `@Ignore` annotation to skip a unit test:

```java
import org.junit.Test;
import org.junit.Ignore;

public class MyTest {

    @Test
    public void test1() {
        // Test implementation
    }

    @Test
    @Ignore("Skipping this test for now")
    public void test2() {
        // Test implementation
    }
}
```

In the above example, `test2()` will be skipped when the tests are executed.

Regarding reusing the same test with different parameter values, you can use parameterized tests in JUnit. One way to achieve this is by using the `@ParameterizedTest` annotation along with `@MethodSource` or `@CsvSource` annotations.

Here's an example of how you can reuse the same test with different parameter values using parameterized tests:

```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyParameterizedTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void test5PMIsValidMeetingTime(int hour) {
        assertTrue(hour >= 1 && hour <= 12, "Hour should be between 1 and 12");
    }
}
```

In this example, the `test5PMIsValidMeetingTime()` method is annotated with `@ParameterizedTest`, and the `@ValueSource` annotation provides different integer values (1, 2, 3, 4, 5) as parameters to the test method. The test method will be executed multiple times, once for each value provided by the `@ValueSource`.

#### Best practices for Unit Testing

##### Don’t use System dependent code in the methods

If the method to be tested contains system-dependent parameters like the current system time,

- **Tests can randomly pass or fail** - If you check how the `addNewAd()` method creates a new `AdData` object, it uses the `LocalDateTime.now()` method within it to fetch the current system date and time. Assume there’s some unresolved bug in the `AdData` constructor. An exception is thrown due to this bug when the provided time is between 12 AM - 11:59 AM. Due to this, the tests will pass if run between 12 PM-11:59 PM but fails if the time the developer ran the test falls between 12 AM-11:59 AM.
- **Not all scenarios can be tested** - To check the `addNewAd()` method with some date and time of 3 AM, either the developer shouldn’t sleep or the system time needs to be changed back and forth when this test is to be run. This makes it very difficult to test, either manually or automatically.

```java
public void addNewAd(String description, String type,  int sellerID) throws FileNotFoundException {
	// code
	// more code…
	AdData adData = new AdData(description, type, seller, LocalDateTime.now());
	this.allAdsList.add(adData);
}
```

A better approach is to pass the date-time as parameter to the method. See the `addNewAdInputLocalDateTime()` method which is commented out in the `AdHandler` class.

```java
public void addNewAdInputLocalDateTime(String description, String type, int sellerID, LocalDateTime localDateTime) throws FileNotFoundException {
	// code
	// more code ...
	AdData adData = new AdData(description, type, seller, localDateTime);
	this.allAdsList.add(adData);
}
```

Find another example code [here](https://gist.github.com/anandaanv/99689fd609e1342fe1022ed5bc270697)

### Writing Testable code

When a test for the current `addNewAd()` fails, what could be the reasons?

Test could fail due to

- Description length check
- Type check
- Or the rest of the logic

This is because the description validation and type validation code isn’t tested separately. Due to how the code is written, this isn’t possible. If the method is refactored to include these validation checks as separate methods, both of them can be tested.



![image](https://storage.googleapis.com/crio-content-container-assets/ME_ME_JUNIT_MODULE_ME_JUNIT_MODULE_BYTE1_image_15.png)

##### Current method (left), Refactored method (right)

### References

1. [Modern Best Practices for Testing in Java](https://phauer.com/2019/modern-best-practices-testing-java/)
2. [JUnit Best Practices Guide](https://howtodoinjava.com/best-practices/unit-testing-best-practices-junit-reference-guide/)
3. [7 Tips for Writing Better Unit Tests in Java](https://dzone.com/articles/7-tips-for-writing-better-unit-tests-in-java)



#### Curious Cats

- Is there some reason why the application logic is under the src/main directory and tests are under src/test directory? (Try moving the test file somewhere else)
- In the upcoming version, we’ll be using the Maps API (costly service) to find the location of a user. How would you test the methods that depend on the API response?

#### Summary

- Unit testing helps with the software development cycle by spotting bugs earlier as well as reducing testing time.
- It is recommended to have one Test class with unit tests for every Java class that needs testing.
- JUnit is a popular library for Unit Testing in Java
  - The test methods are annotated with the `@Test` annotation
  - JUnit provides different assert statements like `assertEquals`, `assertTrue`, `assertNull` to check if correct values are returned by methods
  - Annotations like `BeforeAll`, `BeforeEach`, `AfterAll`, `AfterEach` can be used to run setup code once per class or once per each test
- Passing system specific code as parameters and breaking down code to smaller components helps improve the quality of unit tests.
- Find the
  - Solution code [here](https://gitlab.crio.do/crio_bytes/me_junit/-/tree/solution)
  - Pointers to the Curious Cats questions [here](https://docs.google.com/document/d/1N7Mrw3o1OwjDzD05E1KeUqi6GOTB4MBBR5j-kUk1WDI/edit?usp=sharing)
- **Further Reading**
  - [TDD Changed My Life](https://medium.com/javascript-scene/tdd-changed-my-life-5af0ce099f80)
  - [Code Coverage and the Pitfalls of Automated Unit Test Generation](https://dzone.com/articles/code-coverage-and-the-pitfalls-of-automated-unit-t)
  - [JUnit Tutorial (comprehensive)](https://www.vogella.com/tutorials/JUnit/article.html)
  - [Unit testing vs Integration testing](https://dzone.com/articles/unit-testing-vs-integration-testing-whats-the-diff)
  - [CI/CD Pipeline](https://semaphoreci.com/blog/cicd-pipeline)

#### Newfound Superpowers

- You understand the need for Unit Testing
- Practical know-how of the Java JUnit library

#### Now you can

- Write Unit Tests for your projects
- Use the JUnit library with ease
- Use JUnit annotations to improve tests
- Utilise unit testing best practices to write better quality of code

### UnTestableDate

```java
public String getTimeOfDay() {
  	// new Date() will return current time. So the value of it will change in every hour.
  	Date time = new Date(); 
  
    if (time.getHours() >= 0 && time.getHours() < 6) {
      return "Night";
    }
  
    if (time.getHours() >= 6 && time.getHours() < 12) {
      return "Morning";
    }
  
    if (time.getHours() >= 12 && time.getHours() < 18) {
      return "Afternoon";
    }
  
    return "Evening";
  }
```

### Micro Experience - HTTP

#### Objective

Learn about HTTP protocol and how it’s used

#### Background

HTTP is the most popular application protocol on the internet, which makes actions like internet browsing happen.

- It starts with a client machine sending requests in the HTTP format.
- The server machine receives the request, understands it and takes appropriate action.
- The response again has to be formatted in a specific manner adhering to the HTTP protocol for the client to make sense of it.

#### Primary goals

1. Get a clear understanding of how HTTP works.
2. Use tools like cURL & Postman to perform HTTP requests and analyse responses

#### What are HTTP Request Methods?

You’d have seen how HTTP can be used to fetch resources eg: for loading a website. How would we use HTTP to

- Upload data to the server eg: Add profile picture to facebook
- Update some data present in the server eg: Change your facebook display name
- Delete some data present in the server eg: Remove contact information from facebook


As listed above there are a variety of use cases and HTTP provides different request methods for each. Let’s look at the frequently used methods.

#### GET

**GET** requests are used to “get” resources from a server. By definition, **GET** requests **should** only fetch data from the server and shouldn’t change the data stored on the server.

Check the requests made when you visit https://gitlab.crio.do/users/sign_in (copy-paste enter URL as such) in Incognito. If we check the first request made, it’s for a resource of type **document** which is the HTML file. Use the **Preview** tab to see the HTML rendered. Is it missing something?

Goto the **Response** tab & you’ll be able to see the raw HTML data. You’ll be able to see `<img>` tags. Why aren't the images showing up in the **Preview** tab then?

We need a separate HTTP request for fetching any related files (image, css, javascript) the HTML needs.

Find out an image included in the HTML (look for `<img>` tags). Can you see a HTTP request for that resource?

![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_0.png)

### References

1. [Network analysis with Devtools](https://developer.chrome.com/docs/devtools/network/reference/)



#### <u>POST</u>

**POST** requests are used to send some data to the server. Some use cases are to submit data from a web form or to upload a file to the server.

Assuming you’re still at https://gitlab.crio.do/users/sign_in, try to Sign in using some *invalid credentials*. Inspect the request sent now. How does it differ from what we had when we visited the web page?

Scroll down in the **Headers** tab to find the form data you filled which was sent along with the HTTP request.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_1.png)

#### <u>PUT</u>

**PUT** requests are used to update data on the server side. This could be for actions like changing your Facebook relationship status, updating a student’s marks on the college server after improvement exams etc.

Visit https://gitlab.crio.do/ and login using Google sign-in if you haven’t already. Open the DevTools Networks tab and ensure you’ve the **Preserve log** option checked.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_2.png)

Try updating your account status (Yep, even Gitlab allows you to set status :)) and monitor the network requests. Are there any **PUT** requests made?



![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_3.png)







![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_4.png)





*Try the same process again with the **Preserve log** option unchecked. Are there any differences? Why so?*

We looked at some of the commonly used HTTP request methods. There are a few more that we can use to perform tasks like deleting data, finding the request methods supported for a particular endpoint etc.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_5.png)

#### Curious Cats

- Is it possible to send form data using a GET request? Why or why not?

Yes, it’s possible though not recommended. Usually, form data contains fields that are sensitive like passwords and using GET requests for submitting these means your password will be out in the open along with the request URL. See [here](https://developer.mozilla.org/en-US/docs/Learn/Forms/Sending_and_retrieving_form_data#The_GET_method) on how to send data using GET.

- Are there any limitations in using a GET request to send data to the server?

Data in a GET request is sent as part of the URL and this has a limit of 2048 characters.

[What is the URL character limit for get requests?](https://helpx.adobe.com/in/experience-manager/scene7/kb/base/is_protocol-_-forming_is/url-character-limit-get-requests.html)

[Can HTTP POST be limitless?](https://stackoverflow.com/questions/2880722/can-http-post-be-limitless)

(Answers to these Curious Cats questions will be available in the Takeaways milestone at the end)

#### What are HTTP Status Codes?

HTTP Status codes are part of the HTTP Response. It helps the client understand what happened to the request.

- Status codes are 3 digit numbers (201, 304 etc) and are categorised to 5 different families based on their starting digit.
- Along with the status code, a *Reason-Phrase* is also present (OK, Moved Permanently etc) which gives a short description of the status code.
- The *Status Code* is intended for machines whereas *Reason-Phrase* is for humans.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_6.png)

#### Status codes - 2xx

The 2xx family of status codes or status codes 200-299 signifies the HTTP request was successfully received & understood by the server. We’ve been seeing the 200 status codes all the way until now. That’s what we get when the server returns some resource for our request.



#### Status codes - 3xx

3xx family of status codes denotes that further action must be taken to complete the HTTP request made.

1. Try navigating to http://www.flipkart.com instead of https://www.flipkart.com (`http` instead of `https`).
2. Observe the headers of the first HTTP request again this time.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_7.png)

1. Note the response status code: `301 Moved Permanently`. This is Flipkart asking the browser to redirect the request from the unsecure URL (`http://`) to the secure URL (`https://`) instead. The browser will oblige and send the request accordingly.
2. Inspect the Remote Address again. Is the IP address the same as before? What about the port number?
3. What is the difference between port `80` and port `443`?
4. How does the browser know to redirect to https://www.flipkart.com? (Hint: Response Headers)
5. In summary, requests to http://www.flipkart.com get redirected to https://www.flipkart.com.

#### Status codes - 4xx

Getting a 4xx status code tells us that there was an error in the HTTP request sent by the client - that would be the browser if we are visiting web pages.

Check the status code on trying to fetch some random resource from a website eg: https://www.flipkart.com/crio-do

There are a couple more HTTP status code families - 1xx & 5xx. 1xx is for information purposes while 5xx signifies there was a server error.

Here’s a fun illustration for you to remember the status codes easily. If some of them don’t make sense, check out this reference (https://developer.mozilla.org/en-US/docs/Web/HTTP/Status) to find out what they mean.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_8.png)

#### Curious Cats

- When you try to access a resource that requires logging in, like LinkedIn feed, https://www.linkedin.com/feed, you get redirected to the login screen. That should be a 301, right? Can you verify?
- One day or another, you’d have come across the below pop-up when trying to reload a web page containing a form. Why does this happen? Is there any way to avoid this happening?

![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_9.png)



- Find out example situations that result in a 4xx or 5xx response code.

Answers to these Curious Cats questions will be available in the Takeaways milestone at the end.

#### The cURL utility

cURL is like a web-browser, but for the command line.You can make HTTP requests using cURL just like in a web-browser. The Response can be seen on the command line or redirected to a file.

Open a new terminal window and enter the following commands. Take your time and observe the output of each step closely.

1. Type the following `curl` command:
   `curl -X GET https://www.flipkart.com -o ~/flipkart.html`
   The output of the curl command goes to a file called flipkart.html. You can type  `more ~/flipkart.html` or `cat ~/flipkart.html` to see the contents of flipkart.html file.
2. In the above `curl` request, `-X` allows you to specify the HTTP method to be  used.
3. You don’t have to use the `-X GET` switch => it is the default behaviour. 
4. Try the following command:

```
curl https://www.flipkart.com
```

1. `curl` can give you the same details that you were looking at, in the Chrome Developer Tools.

Try the below exercises and inspect the content of the **flipkart.html** file

1. `curl -X GET http://www.flipkart.com -o flipkart.html`
   You should see the `301 Moved Permanently` reponse; just like in Chrome Developer Tools
2. We can also print the HTTP Response Headers using this:
   `curl -i -X GET http://www.flipkart.com -o flipkart.html`
   Now, you should see the full Response Headers as well.
3. If you also want the full details, try the following command:
   `curl -v -X GET http://www.flipkart.com -o flipkart.html`
   A verbose log is printed. You can get the IP Address and the port number from the console output this time.
4. You can also instruct `curl` to follow the Redirect uRL automatically using the `-L` switch

```
curl -v -L -X GET http://www.flipkart.com -o flipkart.html    # still using http and not https
```

Can you find out other capabilities of cURL?

#### Postman (Optional)

Postman is a powerful GUI based application that lets us make HTTP requests easily. To start with, create a new Request in Postman.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_14.png)

You’ll be able to see this view

![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_15.png)





Using Postman to perform GET requests is straightforward. Just add the request URL and hit **Send**.Try performing a GET request for [https://www.flipkart.com](https://www.flipkart.com/).

![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_16.png)



The returned data is shown in the **Body** tab and the response headers in the **Headers** tab. Check the headers out.

### References

1. [Intro to Postman: Sending Requests](https://www.youtube.com/watch?v=7E60ZttwIpY)

#### Curious Cats

- Postman has a cool feature presenting us with commands to perform requests using cURL, Java, Python & multiple other languages. Find out how to do that.

Answers to these Curious Cats questions will be available in the Takeaways milestone at the end.

#### Construct a simple HTTP request on TCP protocol

Let’s now see the components of an HTTP request & response by actually creating one. The `telnet` client helps us connect to other computers on the internet. The format is `telnet hostname port`. (Use terminal in Crio workspace)

![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_17.png)

##### Opening a TCP connection to server via telnet

The default port for HTTP is 80 and the telnet command has us connected to the HTTP port on the **data.pr4e.org** server. We can start sending HTTP Requests to the server now.

How do we create an HTTP request? Let’s check the HTTP protocol definition doc [here](https://tools.ietf.org/html/rfc2616#section-5) to get an idea of how to frame an HTTP request.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_18.png)

##### HTTP Request specification

![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_19.png)

##### HTTP Request-Response components

(Note: You’ll have to type “Enter” two times if you’re trying out Step 1 in the above image)

From the above figure, different parts of the HTTP communication are:

- Request Line (HTTP Request)
- Status Line & Response Header (HTTP Response)
- Response Body (HTTP Response)

Try to figure out what some of these response headers mean & what their uses are - for starters, see **Last-Modified**, **Content-Length**, **Content-Type** 

If we analyse the network packets transferred to/from our computer during the above communication, we’ll be able to understand some things (*192.168.43.197* is the client computer & *192.241.136.170*, the server)

1. Client initiates a TCP connection request to the server (Line 1) - this is performed when we execute the `telnet` command
2. HTTP communication happens using this established TCP connection (see the bottom part that lists outs the protocols used for the resource transfer)
3. Client sends a HTTP Request line to the server (Line 6) to which the server responds with a HTTP Status code & data as we saw earlier in the telnet output



![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_20.png)

##### Network packets analysed using Wireshark

#### Summary

![image](https://storage.googleapis.com/crio-content-container-assets/ME_HTTP_V2_MODULE_BYTE1_image_21.gif)





- See pointers on *Curious Cats* questions [here](https://docs.google.com/document/d/17t3xFlMLsyxpHlu2ltFwT_JKz4KcYE6vqCw0-LUrG3Y/edit?usp=sharing)
- Further reading (Optional)

- [HTTP: PUT vs POST](https://stackoverflow.com/a/630475/9734484)
- [Redirections in HTTP](https://developer.mozilla.org/en-US/docs/Web/HTTP/Redirections)
- [HTTP Status Codes Explained](https://dynomapper.com/blog/254-the-6-types-of-http-status-codes-explained)
- [Caching using HTTP](https://developer.mozilla.org/en-US/docs/Web/HTTP/Caching)
- [How to Use Wireshark to Capture, Filter and Inspect Packets](https://www.howtogeek.com/104278/how-to-use-wireshark-to-capture-filter-and-inspect-packets/)

### REST API

#### Objective

Get started with REST APIs

#### Background

Ever wondered how

- Flipkart or similar mobile apps make payments using Paytm. Does Flipkart have Paytm’s code to do that?
- Google Sign-in works on websites like medium.com. Does Google share their user data with *Medium*?
- Web applications for facebook, twitter or gmail fetch data from their backend servers

APIs (Application Program Interface) are how different software programs communicate with each other. Application developers specify the rules of “How to communicate” with their application, these are APIs.

For example, Google Maps API specifies how other applications can use it to provide maps services to their users. Usually, when we talk about API, we mean REST API. We’ll take a closer look at REST APIs in this Byte.

![image](https://img.youtube.com/vi/w9b7ygGVCHY/hqdefault.jpg)

#### Primary goals

1. Understand what REST APIs are
2. Understand how to make REST API calls

#### Getting started with REST APIs

REST stands for **RE**presentational **S**tate **T**ransfer. Don’t worry - no one’s gonna ask you the meaning of each word :)

API stands for **A**pplication **P**rogramming **I**nterface, and unfortunately, people will ask you the meaning of each word here. **An API is like a waiter in a restaurant**.

- You don’t go into a cafe and walk straight into the kitchen to tell the chef what you wanna eat.
- The waiter does that for you, and that’s exactly what an API is - with the client being you, the customer and any resource that can send data, being the chef.

Now, these APIs have different styles, or in more formal terms - conventions and architectures about how they are used.

**REST APIs** are those APIs which follow the guidelines set by the REST architecture.

- They follow a client-server model where one software program sends a request and the other responds with some data.
- REST APIs commonly use the HTTP protocol to send requests & receive responses.

Excited about trying this out on your own?

#### Activity

- Visit https://content-xflix-backend.azurewebsites.net/v1/videos/60331f421f1d093ab5424480 on your browser to send a `GET` request to that API endpoint
- Do you find anything peculiar about the response?
- Check out the Networks Tab of Chrome Developer Tools to see if an HTTP request was made



![image](https://www.crio.do/learn/_next/static/images/ActivitySection-04dfadaeb65b7a46fa92d3ab9115c133.png)

## What did you observe?Reveal Now![image](https://www.crio.do/learn/_next/static/images/RevealHead-221179b918a9690e34425c54c7f76589.png)

#### Activity



Look at the HTML content rendered on the web page and say which is Flipkart’s address?

Check it for all the images 1, 2 and 3



![image](https://storage.googleapis.com/crio-content-container-assets/ME_REST_MODULE_BYTE1_image_1.png)







![image](https://storage.googleapis.com/crio-content-container-assets/ME_REST_MODULE_BYTE1_image_2.png)







![image](https://storage.googleapis.com/crio-content-container-assets/ME_REST_MODULE_BYTE1_image_3.png)







![image](https://www.crio.do/learn/_next/static/images/ActivitySection-04dfadaeb65b7a46fa92d3ab9115c133.png)

## What did you observe?Reveal Now![image](https://www.crio.do/learn/_next/static/images/RevealHead-221179b918a9690e34425c54c7f76589.png)

#### Activity



Now, Look at the Json data response and say which is Flipkart’s address?



![image](https://storage.googleapis.com/crio-content-container-assets/ME_REST_MODULE_BYTE1_image_4.png)







![image](https://www.crio.do/learn/_next/static/images/ActivitySection-04dfadaeb65b7a46fa92d3ab9115c133.png)

## What did you observe?Reveal Now![image](https://www.crio.do/learn/_next/static/images/RevealHead-221179b918a9690e34425c54c7f76589.png)

#### JSON

JSON (JavaScript Object Notation - how cryptic :| ) is a standard data format that is easily “understandable” by applications

- It can be handled well in most languages
- So the data format in REST is usually JSON
- For example, an Android app can effortlessly utilize data sent by a Node.js server

### Tip

XML is another popular format for data transfer between applications.

#### Curious Cats

- Why is it that you are able to make a REST API call via the browser?

Answers to these Curious Cats questions will be available in the Takeaways milestone at the end.



#### Components of a REST API endpoint

Let’s try to deconstruct an API endpoint

#### Activity

- Install [JSONView](https://chrome.google.com/webstore/detail/jsonview/chklaanhfefbnpoihckbnefhakgolnmc) chrome extension or a similar tool to automatically format the JSON response in your browser window

![image](https://storage.googleapis.com/crio-content-container-assets/ME_REST_MODULE_BYTE1_image_5.png)



Check out the data returned from each of these

1. https://content-xflix-backend.azurewebsites.net/v1/videos

2. https://content-xflix-backend.azurewebsites.net/v1/videos/60331f421f1d093ab5424490

3. https://content-xflix-backend.azurewebsites.net/v1/videos?genres=Movies

4. https://content-xflix-backend.azurewebsites.net/v1/videos?genres=Education

Do you find any relation between data returned from these endpoints?

The data returned from

- https://content-xflix-backend.azurewebsites.net/v1/videos - list of all videos
- https://content-xflix-backend.azurewebsites.net/v1/videos/60331f421f1d093ab5424490 - video with `id` value of 60331f421f1d093ab5424490
- https://content-xflix-backend.azurewebsites.net/v1/videos?genres=Movies - list of “Movies” (see the “genre” field for each of the video returned)
- https://content-xflix-backend.azurewebsites.net/v1/videos?genres=Education - list of “Education” videos

Here, there are 3 API endpoints at play

1. `GET https://content-xflix-backend.azurewebsites.net/v1/videos` - Lists all videos
2. `GET https://content-xflix-backend.azurewebsites.net/v1/videos/:id` - Gets video with a particular id value
   1. The `:id` notation denotes that the value is to be replaced with a valid `id` value
   2. Here, `:id` is also called as **URL Path Parameter**
   3. `GET https://content-xflix-backend.azurewebsites.net/v1/videos?genres=<value>` - Lists posts filtered by their `genres` field value

3. The `genres` query value can be any valid genre the API supports (eg: Education, Sports, Movies)
4. Here, `genres` field is also called a **Query Parameter** and specifies the search or filter criteria for the resource
5. Part of an API endpoint after the “?” symbol comprises of one or more Query parameters

Here’s a visual on different parts of an API endpoint.

- The root endpoint will be the same for all API endpoints supported by a provider
- The API path specifies the resource to be fetched (eg: videos, users)
- Path parameter is used to identify and return a specific resource
- Query parameter is used to filter or search for resources

![image](https://storage.googleapis.com/crio-content-container-assets/ME_REST_MODULE_BYTE1_image_6.png)



#### Activity

- Get data from the `GET https://content-xflix-backend.azurewebsites.net/v1/videos` API endpoint
- From this data, find the `id` value for the video with the title “The Great Silence" (Hint: `Ctrl + f` can open up the search option)
- Now, use the `GET https://content-xflix-backend.azurewebsites.net/v1/videos/:id` API endpoint to fetch the data for this video only
- Verify the data returned is correct by checking the “title” field value

### How to approach it?

As we know, the browser can be used to make a GET API request. 

Visiting https://content-xflix-backend.azurewebsites.net/v1/videos returns the list of all videos. 

Searching for the required video title here shows us the data related to the video



![image](https://storage.googleapis.com/crio-content-container-assets/ME_REST_MODULE_BYTE1_image_7.png)





Now, we can copy the “id” value from here (60331f421f1d093ab5424488) and use it to create the required endpoint - https://content-xflix-backend.azurewebsites.net/v1/videos/60331f421f1d093ab5424488 . 

As we learnt earlier, the `:id` path parameter in the endpoint is replaced with a valid “id” value here

#### Activity

- What is the first video you see on Youtube?
- Do you see the same videos as your friends, or say your parents?
- If not, how is this personalization done

- Is youtube sending different html files to 2B+ users?



![image](https://storage.googleapis.com/crio-content-container-assets/ME_REST_MODULE_BYTE1_image_8.png)





Modern web pages has page template (which can vary based on web, desktop app or mobile app) and data (which will be the same for a user across devices). This data is sent in  structured (JSON/XML) format. It works in the following sequence:



![image](https://storage.googleapis.com/crio-content-container-assets/ME_REST_MODULE_BYTE1_image_9.png)

#### Curious Cats

- Let’s say we need to use another query parameter, `contentRating` along with `genres` to filter by the video’’s content rating. How would you redesign the request URL, https://content-xflix-backend.azurewebsites.net/v1/videos?genres=Movies for this purpose?

Answers to these Curious Cats questions will be available in the Takeaways milestone at the end.

#### REST and HTTP

As we discussed earlier, REST API calls are made on top of the HTTP protocol. We can analyse the network packets during the API calls to confirm this using Wireshark.

**Wireshark** is a popular network analysis tool to capture network packets and display them at a granular level. Once these packets are broken down, you can **use** them for real-time or offline analysis.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_REST_MODULE_BYTE1_image_10.png)

##### Visualizing API request-response packets using Wireshark

From analysing the Wireshark data given above, we can understand that

- The client sends a HTTP **GET** request (Entry No. 66) to the server for the resource at `/v1/videos/60331f421f1d093ab5424489` (API request was made to https://content-xflix-backend.azurewebsites.net/v1/videos/60331f421f1d093ab5424489)
- The server responds with a **200** status code and JSON data on line 77.
- You’ll find the Hypertext Transfer Protocol (HTTP) being used for communication
- The JSON data returned is formatted and displayed in the lower pane

### Note

You don’t have to use Wireshark to capture data at this point. The reference image was given to demonstrate the internal workings of an API request made.

### Tip

REST is one architectural pattern used, NOT the only one. There are some has-beens, like SOAP and some new kids on the block, like GraphQL. These patterns differ in the ways in which they ask for data, send data, and how they enforce these practices.

#### REST API calls using Programs

---

#### Background

REST API calls are basically structured HTTP requests. You can read [a beginner’s guide to REST APIs](https://www.smashingmagazine.com/2018/01/understanding-using-rest-api/) to get a good introduction to REST APIs. Spring Boot provides a convenient library called `RestTemplate` to effortlessly make REST API requests. `RestTemplate` generates the required HTTP request and consumes the returned response in the same template with minimal lines of code.

#### Primary goals

In this module, you will make use of `RestTemplate` to interact with Tiingo’s APIs to retrieve stock quotes.

1. Call Tiingo’s APIs to obtain closing prices on a given date of stocks present in a user’s portfolio.
2. Sort the stock symbols in ascending order of closing price.

File file = resolveFileFromResources(args[0]);

​    ObjectMapper objectMapper = getObjectMapper();

​    PortfolioTrade[] trades = objectMapper.readValue(file, PortfolioTrade[].class);

​    List<String> symbols = new ArrayList<String>();

​    

​    for (PortfolioTrade t : trades) {

​      *// System.out.println(t.toString());*

​      symbols.add(t.getSymbol());

​    }

​    return symbols;

---

List<PortfolioTrade> trades = readTradesFromJson("trades.json");

   RestTemplate restTemplate = new RestTemplate();

   ObjectMapper objectMapper = new ObjectMapper();

   objectMapper.registerModule(new JavaTimeModule());

   List<String> symbols = new ArrayList<>();

   *// Process each trade*

   for (PortfolioTrade trade : trades) {

​      String url = prepareUrl(trade.getSymbol(), endDate, apiToken); *// Use getSymbol() method*

​      Candle[] candles  = restTemplate.getForObject(url, Candle[].class);

​      

​      if (candles != null && candles.length > 0) {

​         Arrays.sort(candles, Comparator.comparing(Candle::getClose));

​         *// symbols.add(candles[candles.length - 1].symbol);*

​         System.out.println("A");

​         System.out.println(candles);

​      }

   }

   Collections.sort(symbols);

   return symbols;

---

#### REST



REST stands for Representational State Transfer. But what does this mean? In simple terms, we are simply trying to query the state of a machine. In the real world, REST is the dominant form of communication across the web. HTTP protocol fits in nicely with the specification guidelines laid out by REST. In a sense, you can think of REST-HTTP being identical in concept to a function call.

URL is analogous to the function and its various paths, parameters, are function arguments. That function call is understood by a server situated across a network and it returns a response. This is pretty much what happens in our use-case: we are calling the Tiingo API through which we are requesting information defined by the parameters we pass to the servers at Tiingo. This information is then returned to you as a JSON.

Real world examples

1. Open https://crio.do/
2. Open the Developer Tools. In Chrome, go to View -> Developer -> Developer Tools.
3. Click on the "Network" tab at the top.
4. Click on "XHR" subtab.

You can inspect the requests and responses by clicking on them. Observe the response in the `Response` tab in the right panel. It should look very familiar to you.

You can try it out for other websites as well. Many Google products use this in similar ways. Try out Google Docs or Google Maps.



#### RestTemplate



So we’ve established that REST together with HTTP can be used to communicate with servers. How would we do this in Java? And more specifically, Spring Boot?

Internally, Java uses low level objects such as `HttpURLConnection` and `ClientHttpRequest` to make network calls over HTTP. But doing so requires the developer taking care of things like streams, buffers, etc which must be carefully coded and can be error prone if done incorrectly.

Spring Boot offers a simple wrapper which takes care of handling the low-level details, and this is the `RestTemplate` that we are familiar with now. RestTemplate has many methods that deal with its corresponding HTTP method. The one that is most convenient to use for our case is `getForEntity` or `getForObject`.

Food for thought:

1. What are the differences between the above methods?
2. Can you think of specific cases when you would select one method over the other?

#### Where does this module fit in the big picture?



At this moment, you are able to get the stock values from Tiingo. So all the required data for calculating the annualized returns is now available. It is basically the following:

1. `stock_symbol`
2. `purchase_date`
3. `purchase_value`
4. `quantity`
5. `current_value`

Based on this, you will calculate annualized returns using a formula in the next module.

#### Alternative Solutions

1. You can use different variations of `RestTemplate` methods. Try using the following in your code:
   1. `getForObject`(pojo class)
   2. `getForEntity`(pojo class)
   3. `exchange`
   4. `getForObject(String.class)` and then use `ObjectMapper` to parse
2. Alternatively, if needed, you can make `POST` calls using `RestTemplate` as well.
3. You can also use [Retrofit](https://www.vogella.com/tutorials/Retrofit/article.html) to make API calls.

---

#### Comparator

We had an array/arraylist of our own class type: `AnnualizedReturns` containing fields like:

1. `annualizedreturns`,
2. `symbol`,
3. `totalReturns`

How do we efficiently sort the array based on `annualizedreturns`?

There are different ways to do this:

1. One obvious approach is to write our own `sort()` function using one of the standard algorithms.
   - This solution requires rewriting the whole sorting code for different criteria like `annualizedReturns`.
   - Obviously we will not focus on it, as it is not the Java way.
2. Using comparator interface
   - Comparator interface is used to order the objects of the user-defined class.
   - You can implement the `compareTo` function and later use `Collections.sort` in order to sort the collection.
   - [Java Comparator interface example](https://howtodoinjava.com/java/collections/java-comparator/)
3. Use `Comparator.comparing()`
   - This approach is even simpler.
   - You can use [Comparator.comparing()](https://www.baeldung.com/java-8-comparator-comparing) function where you don't even have to write a single line of comparison logic.
   - Your class need not be comparable and you don’t have to write any comparator.
   - You are leveraging some predefined Java APIs to do the magic.
   - [How to Use the Comparator.comparing Method in Java 8](https://www.webucator.com/how-to/how-use-the-comparatorcomparing-method-java-8.cfm)



#### JUnit tests

1. Whenever you are writing any new code, it's absolutely necessary that the code you wrote earlier is working absolutely fine.
2. Also there are several cases where your code is not yet production ready, but you still want to test whether its working fine.
3. Junit is a library that lets you do that. You can write your logic and test it in isolation with other components.
4. Did we use it here?
   - Before implementing the full calculation, we implemented a small piece of logic, and tested it from isolation with another program using a gradlew command. That helped us give confidence upon the calculation logic we had built.
   - Software is nothing but a combination of multiple such tiny working components combined together as a system.
5. In the upcoming modules, you will use more such unit tests to test your code in isolation, just to gain the confidence on the small components, before they fit into the big system. Keep an eye on it!

---

#### Objective - Abstraction

Learn abstraction by applying it in a practical scenario.



#### Background

Abstraction is the concept of exposing only the required information to users and hiding the rest. A class would only expose some of its methods and hide the data fields and other methods. For the exposed methods, the users only need to worry about the method name, the input parameters that need to be passed and the output format to expect. The external users/objects need not worry about the complexity within these methods.

Abstraction is a natural extension of encapsulation.

This is very useful in large projects which have many different kinds of objects interacting with each other. It keeps the complexity manageable. Each class is free to make changes under the hood without impacting other classes as long as the exposed interface doesn't change.

An abstract class or interface is a good way to implement Abstraction. 

The methods and their signature (inputs and outputs) are made public by the abstract class or interface and multiple other classes can implement these methods. That way, we can switch between the implementing classes without much impact to external entities that use these methods.

In this Byte, we’ll experience where Abstraction is useful and how it contributes to better software design.



#### Primary goals

1. Learn where Abstraction can be used
2. Understand why it is needed and to how to apply it

#### Debrief

- Practical scenarios
  - The Java Virtual Machine is an abstraction. Your code can use the exact same java methods irrespective of whether your code will run on Linux or Windows or any other OS. Underneath, a lot of things will change depending on the OS, which the user doesn’t need to know.
  - You can use a simple API call to fetch weather data or stock prices. These API abstract out the internal complexities of how it is implemented.
  - Software libraries are good examples of abstraction. They tell you the methods you can use without telling you how they are implemented.
- Summary of Abstraction
  - It separates the method signature from its implementation.
  - Closely related classes can separate out common fields/methods into a different class and reuse that class.
  - **Why** is it useful? - To the implementers of the class, it is useful since it provides them with the flexibility to change things as long as the method signatures don’t change. For the users, it keeps them from getting exposed to anything other than the method signatures they need to know, reducing complexity.
  - **How** to apply? - Use a smaller set of public methods
  - What is the drawback if we don’t use abstraction? - Low maintainability because responsibilities are not clearly differentiated. Higher Complexity with larger code bases because many objects interact with others and it becomes difficult to add functionality without impacting others.
- Language specific notes
  - In Java, `interface` classes provides total abstraction and `abstract` classes provide partial abstraction
  - In C++, you can use either header files to achieve abstraction or private methods to hide implementation details.
  - Python interface can be applied using the the Abstract Base Class library
- General Abstraction notes
  - Hides underlying complexity of data from users
  - Helps avoid repetitive code
  - Presents only the signature of internal functionality
  - Gives flexibility to programmers to change the implementation of the abstract behaviour
- Difference between Encapsulation and Abstraction
  - Abstraction is a natural extension of Encapsulation. Encapsulation recommends that you create entities/objects which contain closely related fields and functionality/methods. Abstraction further recommends that the entities expose only their method signatures to external users who shouldn’t need to worry about the underlying implementation.
  - Encapsulation - hide data. Abstraction - hide complexity.

### References

1. [Interface vs. Abstract Class in Java](https://howtodoinjava.com/java/oops/exploring-interfaces-and-abstract-classes-in-java/)
2. [Abstraction vs Encapsulation](https://www.quora.com/What-is-the-difference-between-abstraction-and-encapsulation-2)
3. [OOPS Concepts Java - Abstraction](https://raygun.com/blog/oop-concepts-java/#abstraction)
4. [Implementing an Interface in Python](https://realpython.com/python-interface/)
5. [Abstract Base Classes in Python](https://dbader.org/blog/abstract-base-classes-in-python)

#### Curious Cats

- What is the difference between a regular class, an abstract class and an interface class in Java?
- How can you spot if a code base has used Abstraction in its design?

#### Newfound Superpowers

- You are now cruising along the *Abstraction Understood* highway, ready to design larger pieces of software more efficiently.

#### Now you can

- Think about the bigger picture with situations where abstraction adds value.
- Crack that interview question with a real example
- Answer these interview questions
  - What is Abstraction?
  - What is the difference between Encapsulation and Abstraction?

---

### Mockito

#### Background/Recap

Software testing is essential to ensure correct working of applications. It also helps speed up the development process as the tests would let us know if changes to the program breaks something. Unit testing is a type of software testing where each smaller part of the application is tested in isolation from other components. These tests are usually run multiple times daily & that too not on the server but on the developer’s computer. Hence, unit tests have to be quick. Apart from increasing the tests time, dependencies also increase chances of a test failing even with correct logic because the test could fail due to a bug in any of the dependencies.

To not care about dependencies, we replace the original implementation of the dependency using test doubles like Mocks, Stubs, Spies. 

We can stub methods to return predefined values, for example, when calling methods that are not implemented yet. 

Mocking allows capturing interactions to the mocked objects & returns null for any calls that’s not stubbed. Spying provides similar functionality as of mocking. 

The difference is that for non-stubbed scenarios, the spied objects work normally.

Mockito is a Java library popularly used in testing to create test doubles.

#### Primary goals

1. Understand the need for using Mockito
2. Understand using Mockito to test predefined behaviour
3. Understand using Mockito to simulate behaviour based on input
4. Understand using Mockito to verify interactions with dependencies
5. Understand common issues and tips working with Mockito

#### The need for Mockito

Testing code concerned with Uber API in Google Maps without mocking leads to:

- Slows the unit tests as it involves network calls to Uber servers
- Running the unit tests can become expensive if any of these APIs are rate-limited or charged by Uber
- Unit tests cannot be run if the Uber API servers are temporarily down

Let’s check on some scenarios where Mockito helps in more detail

#### Unit testing even when dependencies aren’t available

Quite often, different components of a project will be worked on by developers across multiple teams. There will be a contract pre-defined in these scenarios, mostly in the form of interfaces specifying the behaviour of different methods. Your code will be using the components others are working on.

Our `getPriceEstimate()` method in the `ExternalUberService` class uses the `isValidLocations()` method of `GoogleMapsHelper`. `GoogleMapsHelper` is an interface containing method contracts being worked on by a different team yet to be implemented. Should you wait for the other team to complete their code to start unit testing your implementation?

```java
public interface GoogleMapsHelper {

  /**
   * Validates the correctness of the starting and ending location coordinates
   * @param startLatitude of type Double.
   * @param startLongitude of type Double.

   * @param endLatitude of type Double.

   * @param endLongitude of type Double.

   * @return true if coordinates given are all valid,

   * false if any of the coordinate values are invalid

   * Valid Latitude range -> -90 to +90

   * Valid Longitude range -> -180 to +180

   */

  public boolean isValidLocations(Double startLatitude, Double startLongitude,

      Double endLatitude, Double endLongitude);


  /**

   * Makes payment for the Uber cab booked via Google Maps

   *

   * @param tripID of type String

   * @param otp of type String

   * @return true if the payment was success,

   * false if the payment failed

   */

  public boolean makeUberPayment(String tripID, String otp);

}
```

#### Isolate your unit tests from dependencies

Unit testing by definition should only be testing for a specific unit or functionality. Let’s say we are unit testing the `getPriceEstimates()` method. Now, what happens if the `buildPriceEstimateBaseUrl()` method has a bug?

Yeah, the unit test for `getPriceEstimates()` will also fail. This is not a preferable behaviour. In large projects with multiple dependencies, it’ll be hard to pinpoint the cause of unit test failure if the unit tests aren’t isolated from the dependencies. Also, as `buildPriceEstimateBaseUrl()` will be unit tested separately, it’s safe to assume that it works as expected when testing `getPriceEstimates()`.

How would you isolate the dependencies when unit testing?



![image](https://storage.googleapis.com/crio-content-container-assets/ME_MOCKITO_MODULE_ME_MOCKITO_MODULE_BYTE1_1684221466_image_3.png)







#### Testing for behavior that doesn’t exist/cannot be created today

It’s critical that our code be tested to ensure its correctness as well as to check how it handles irregularities. What would happen if Uber API response structure got changed in a future release or a bug introduced returns an empty response occasionally?

![image](https://storage.googleapis.com/crio-content-container-assets/ME_MOCKITO_MODULE_ME_MOCKITO_MODULE_BYTE1_1684221467_image_4.png)

To check how our code behaves in these cases, we’ll have to get these exact responses back from the Uber API server. But, how would you do that? These are scenarios we are anticipating and don't happen today.

Mockito can be used to return preset responses to deal with all the 3 scenarios we discussed without making any changes to the implementation.

### References

1. [The Test Double Rule of Thumb](https://engineering.pivotal.io/post/the-test-double-rule-of-thumb/)
2. [The Concept of Mocking](https://dzone.com/articles/the-concept-mocking)
3. [Mock vs Stub](https://stackoverflow.com/a/48875619/9734484)

---- Not Completed ---



----

### GRADLE V2

Get hands on with Gradle Build Automation Tool



#### Background



Mr. X works for a startup company which provides a free online service to store and access memes from across the internet.The company recently received investor funding and is now frantically working toward its first official launch. However, the manual and error-prone build and delivery process slows down the productivity. As a result, the team has to live with the continuous compilation issues, inconsistent software packaging and failed deployments. As the product continues to get complex with each new feature to be supported, Mr.X is horrified at the manual and repetitive work that lies ahead. To bring an end to this torture, Mr. X plans to automate each step of the compilation and delivery process to reduce the risk of failed builds, late integration and painful deployments.



![image](https://storage.googleapis.com/crio-content-container-assets/ME_GRADLE_V2_MODULE_BYTE_image_0.png)





**Build tools** are programs that automate the creation of executable applications from source code (e.g., apk for an Android app, jar file for java app). Building includes compiling, linking and packaging the code into a usable or executable form.

Basically **build automation** is the act of scripting or automating a wide variety of tasks that software developers do in their day-to-day activities like:

- Downloading dependencies.
- Compiling source code into binary code.
- Packaging that binary code.
- Running tests.
- Deployment to production systems.

**Gradle** is one of the more popular build tools.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_GRADLE_V2_MODULE_BYTE_image_1.png)

#### Primary goals

1. Understand the importance of build tools.
2. Answer the basic question of why they are required in modern day applications
3. Get a better understanding of Gradle as the build tool of choice for the projects.
4. To automate the different stages in the Software Development lifecycle using Gradle.

#### Phases of Java Project Development

A Software project under development goes through a series of phases after which it is made available to the end consumer.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_GRADLE_V2_MODULE_BYTE_image_3.png)





Let’s go through some of the important terms mentioned in the above diagram

- Build
  - Process of converting the source code to an executable code that can be run on a computer. Build also refers to the executable created.

- Publish
  - It refers to Build getting published to the location where they are stored.

- Test
  - It refers to a process which ensures software quality so that it’s bug free.

- Deploy
  - It refers to performing the process of application deployment to servers in different environments of the project so that it becomes available to the end user.



#### What is Java ClassPath?

Java Class Path is one way to tell applications **where to look for classes** and other files. This is specified as an Environment variable or configuration on the system.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_GRADLE_V2_MODULE_BYTE_image_4.png)









- JVM uses the Java Classpath to find classes during runtime.

- Java commands and tools also use the classpath to locate classes.

- When building and running a Java project there are two classpaths involved:

  - Compile classpath
    - List of dependencies that are required for the JDK to be able to compile Java code into .class files

  - Runtime classpath
    - List of dependencies that are required to actually run the compiled Java code

Let’s execute the project we cloned and see how classpath comes into play. Follow the instructions below.

### Curious Cats

- What is the difference between PATH and CLASSPATH?

#### Build Weather Application through commands

Let’s walk through the traditional approach of building a Java Project from terminal using different tools and commands. Make sure you are present in the root directory of your cloned “me_gradle/milestone-1” repository through the terminal.

#### Compilation of the Java Project

- Command line Execution

  - javac -d bin -cp <Class Path indicating files/libraries to include> <list of Java Classes to be compiled>

  - javac Command Syntax breakdown

    - -d specifies the directory to generate the compiled classes in (.class files)

    - -cp specifies a list of directories, JAR archives and ZIP archives to search for class files which might be required for other classes to compile.

- Compile WeatherApplication (you can open the files specified below to understand what they contain `classpath.txt` and `main_sources.txt`)

```
javac -d bin -cp @classpath.txt  @main_sources.txt
```

Run the Weather Application

```
java -cp @classpath.txt com.crio.session5.activity1.WeatherAdvisorApplication 12.9762 77.6033 "2020-05-29 08:15" "2020-05-29 09:15" v1
```

- Here is the breakdown of Command line arguments for the Weather Application for your understanding.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_GRADLE_V2_MODULE_BYTE_image_5.png)

- Why do you think -cp was required in both commands?
- We need java to know where to find the compiled classes which will be needed by some other classes which are dependent on it for execution (runtime).

- You should see the below output when you run the application

![image](https://storage.googleapis.com/crio-content-container-assets/ME_GRADLE_V2_MODULE_BYTE_image_6.png)

Now, we have successfully finished the **Compilation** and Execution of the Java project from the command line. Let’s move to the next thing that is required - **Testing** the project.

#### Testing of the Java Project using JUnit

- Command line Execution to run all test cases across the files

- java -jar <path of junit-platform-console-standalone.jar>  -cp <Class Path indicating files/classes/libraries to include> --scan-class-path

- Java Command Syntax breakdown

- -jar specifies the executable jar to be executed, this is the standard junit jar which can execute the test cases
- -cp specifies the classpath which is a list of directories, JAR archives and ZIP archives to search for class files.
- --scan-class-path scans all the directories of the classpath to find compiled classes that have JUnit test and runs the unit tests

- Run all the Unit Tests in the Weather Application

```
java -jar ./lib/junit-platform-console-standalone-1.8.1.jar -cp @classpath.txt --scan-class-path
```

You should see this output:



![image](https://storage.googleapis.com/crio-content-container-assets/ME_GRADLE_V2_MODULE_BYTE_image_7.png)

Now, we have successfully finished the **Compilation** and **Testing** of the project. Let’s move to the next thing that is required - **Packaging** the project.

#### Packaging the Java Project

- A [JAR (Java Archive)](https://www.geeksforgeeks.org/jar-files-java/) is the standard package file format in Java

- Used to **aggregate many Java class files** and associated metadata and resources (text, images, etc.) **into one jar file**. This jar file can be used to **distribute application software or libraries** on the Java platform. Others can include this jar when they compile their program that depends on these class files.

- Command line execution to create jar file for our application

- jar cvfm <Jar filename to be created> <Path to Manifest file> -C <Path To directory containing classes that will be included in the jar>.
- **Manifest** is a special file that can contain information about the files packaged in a JAR file. By tailoring this "meta" information that the manifest contains, you enable the JAR file to serve a variety of purposes.
- For. eg, a Jar contains a bunch of compiled classes. We can specify which class can act as an entry point when a jar is executed through the command line.

- To learn more about the jar related command syntax, you can refer to this

- [Create an Executable Fat JAR With Your Command Line - DZone Java](https://dzone.com/articles/java-8-how-to-create-executable-fatjar-without-ide)

- Create Jar Executable (WeatherAdvisorApplication.jar) using the following command.

```
jar cvfm WeatherAdvisorApplication.jar ./jar_manifest.txt -C ./bin/ .
```

You will be able to see `WeatherAdvisorApplication.jar` generated inside the `me_gradle/milestone-1` directory.Take a quick look at the manifest file to understand what it contains.
Run the jar file.

```
java  -jar ./WeatherAdvisorApplication.jar 12.9762 77.6033 "2020-05-29 08:15" "2020-05-29 09:15" v1
```

Compare this to the non jar way of execution you did earlier.

#### Can we somehow automate all of this?

We have seen how to do Compilation, Testing and Packaging of a Java project from the command line using standard java commands.

Do you see any **disadvantages** with the way we’ve done these?

- These are a set of repeatable tasks when **executed manually** take a lot of **effort and time**.
- Any mistake done while executing these commands or a small change in code may lead to starting the process again from scratch.
- **Commands might need to be modified** by multiple people (and it's difficult to co-ordinate every time) if there is an addition/deletion of some of the source code files.
- Is there a way to get past these disadvantages?

- Use Shell Scripts
- Use MakeFile
- Use a **BuildTool**

#### Where do build tools come into the picture?

Build tools come to the rescue by **addressing these disadvantages**. They manage to **automate the entire build lifecycle** for us.

Not only does it automate the entire process, but also makes it a very easy process and ultimately increases developer productivity. It has been adopted world wide for the plethora of features that it provides.

In the subsequent milestones, we will discuss one such build tool: **Gradle**. Here’s a quick comparison of how Gradle is very elegant to use in comparison to the traditional command line approach. We’ll execute some of these commands soon.



![image](https://storage.googleapis.com/crio-content-container-assets/ME_GRADLE_V2_MODULE_BYTE_image_8.png)

### References

- [Introduction To The Tools Involved In DevOps Process - DevOps Diggers](https://www.devopsdiggers.com/introduction-to-the-tools-involved-in-devops-process/)
- [Create an Executable Fat JAR With Your Command Line - DZone Java](https://dzone.com/articles/java-8-how-to-create-executable-fatjar-without-ide)
- [Runtime Classpath vs Compile-Time Classpath - DZone Java](https://dzone.com/articles/runtime-classpath-vs-compile)

#### What is Gradle?

Gradle is a powerful **build tool**, and when set up properly, offers one of the most efficient ways to build a Java project. The below diagram shows some of Gradle’s features though we’ll not go into all of them.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_GRADLE_V2_MODULE_BYTE_image_9.png)

Gradle is a build tool which meets all the requirements of building Java applications. Once it’s set up, building your application is as simple as running a single command on the command line.

```
./gradlew build
```

That **includes compiling, testing, and packaging your application**, all with one command. We’ll cover exactly what this command does later.

#### But how does Gradle know how to build your application?

At a high level, you have to tell Gradle:

- what type of application you’re trying to build, for example, Java
- any libraries your application depends on, or in other words, its dependencies (E.g. TestNG, JUnit, Spring, Jackson etc.)
- any other configuration specific to your application, such as special compile or testing options

In Gradle, you provide this configuration in a file called a **build script** (`build.gradle` - we'll learn more about this file soon). You will add it to your application’s code repository, which means, anyone can clone your repository and immediately build the application the exact way you want them to. This will avoid any inconsistencies across different team members.

#### What is a library and why are they used?

A **library** provides a set of helper functions/objects/modules which your application code calls for specific functionality. Libraries typically focus on a narrow scope (e.g., strings, IO, sockets), so their methods also tend to be smaller and require fewer dependencies. It is just a collection of class definitions which you can include and use in your code.

#### Why do we need libraries?

The reason being very simple i.e. **code reuse**, use the code which has already been written by other developers. There is no point in reinventing the wheel. These libraries generally are widely used, widely accepted and a very easy alternative to otherwise, writing the entire thing again.

#### What is dependency management with libraries?

To use any library in our application, we would require to have the set of class files, jars(executables) of the library, inside our application.

Every library also has multiple versions, depending on the different functionalities they offer. Apart from this, every library can also depend on multiple other libraries for its functionality! These **libraries on which the application is dependent are called the dependencies** of the application. The entire system of managing dependencies is known as dependency management.



![image](https://storage.googleapis.com/crio-content-container-assets/ME_GRADLE_V2_MODULE_BYTE_image_10.png)

**Dependency management** allows teams to manage dependencies for multi-module projects and applications. These can consist of hundreds or even thousands of modules. Using a build tool like Gradle can help teams define, create, and maintain reproducible builds.

Dependency management is important because in larger product, there may be a lot of components, produced by several teams working on different schedules. You need to make sure you have the right versions of all of these components in your workspace in order to develop, build, test, and release your product.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_GRADLE_V2_MODULE_BYTE_image_11.png)

### Tip

A detailed view of the **dependency tree** can be seen by executing the `./gradlew -q dependencies` command inside the `me_gradle/milestone-2` directory. Try it out in your workspace.

#### How did dependency management happen before Gradle?

Earlier, we used to do it **manually**. We would have to figure out all the dependencies & transitive dependencies our application had. Then, manually download the correct versions and copy them into appropriate folder locations. These would then be picked up by our application accordingly.

As you can imagine, this is a tiresome process involving a lot of manual effort.

#### How do we declare dependencies in Gradle?

We specify dependencies in our `build.gradle` by enclosing the details within the `dependencies` block. Within that, we can specify the details of each dependency in two ways. Either using the format specified on line 48 in the snapshot below or the format on line 53. Either way, we need to specify the dependency’s **group**, **name**, and **version**.
The snapshot below shows 2 dependencies specified for an application - `spring-boot-starter-web` and `spring-boot-starter-test`.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_GRADLE_V2_MODULE_BYTE_image_12.png)

#### Curious Cats

- What do you think will happen if you don't specify the version of the dependency?

#### Where are the gradle downloaded dependencies stored?

Let’s figure out where the dependencies are stored on the machine when they’re downloaded.

Inside the `me_gradle/milestone-2` folder, run `./gradlew build` so that all the required dependencies are downloaded accordingly and the project is built successfully.

You can compare the `milestone-1` and `milestone-2` folders to see the difference between a non build tool project and a build tool (Gradle) based project.

Let’s run this command - `find ~/.gradle -name spring-boot-starter-web`.

You will see that the dependencies are stored in the cache at a default location. You can explore the dependency folder on the third line and you will be able to find the jar dependencies with the versions defined in the build.gradle.



![image](https://storage.googleapis.com/crio-content-container-assets/ME_GRADLE_V2_MODULE_BYTE_image_13.png)







#### Know-how required to add a dependency



What is a repository - It's a directory where all the **standard project jars, library jar,** plugins or any other project specific artifacts are stored and can be used by build tools like Gradle easily. Usually on the internet where it is accessible for everyone.

**Maven central repository** (https://mvnrepository.com/) is the standard go-to repository provided by the Maven community for Java developers. It contains a large number of commonly used libraries. Go to the maven repository [website](https://mvnrepository.com/) and follow the below visuals to choose a version of the dependency  `spring-boot-starter-web`. Search for it, copy any version of the dependency from the website and replace the current version in the `build.gradle` file under the `me_gradle/milestone-2` folder. Run `./gradlew build` so that the new version of the dependency is downloaded accordingly and the project is built successfully.



![image](https://storage.googleapis.com/crio-content-container-assets/ME_GRADLE_V2_MODULE_BYTE_image_14.gif)





Get into the directory where the dependencies are downloaded and you will notice that dependencies of different versions are downloaded.



![image](https://storage.googleapis.com/crio-content-container-assets/ME_GRADLE_V2_MODULE_BYTE_image_15.png)





Try changing the version of any dependency mentioned in the buildscript and verify if they are successfully downloaded.

For any dependency that your project might have, you can go to the maven repository website, choose the version and include it in your `build.gradle`. Gradle will take care of downloading it before compilation.



#### Curious Cats



- What happens to the downloaded dependency if we remove the declaration of the dependency from build.gradle? Comment out any line starting with `implementation` under dependencies block and follow the above steps to verify if dependencies are present or not in the cache.





#### Project Folder Structure required for Gradle



Gradle requires the files to be organized in a specific folder structure for them to operate. If the code doesn’t follow this structure, it will not be able to function. Sample folder structure can be seen in the diagram below.



![image](https://storage.googleapis.com/crio-content-container-assets/ME_GRADLE_V2_MODULE_BYTE_image_16.png)





- src/main/java

- Contains the source code of our project.

- src/main/resources

- Contains the resources (such as properties files) of our project.

- src/test/java

- Contains the test classes.

- src/test/resources

- Contains the test resources.

- bin

- Contains the compiled .class files.

- lib

- Contains the third party jar files required by the project.

Having a standard folder structure is a good approach because you can work on several projects with the same structure. You will be able to switch between projects and don't have to expend time to understand the project organisation.

### References

- Maven is the other popular build tool - [Maven Tutorial - Maven Directory Structure (java2s.com)](http://www.java2s.com/Tutorials/Java/Maven_Tutorial/1030__Maven_Directory_Structure.htm)
