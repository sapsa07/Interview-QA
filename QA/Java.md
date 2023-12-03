# Java

## OOPs (Object-Oriented Programming System)

**Object-Oriented Programming** is a methodology or paradigm to design a program using classes and objects. It consists of

1. Object
   1. An Object can be defined as an instance of a class. An object contains an address and takes up some space in memory.
2. Class
   1. *Collection of objects* is called class. It is a logical entity. A class can also be defined as a blueprint from which you can create an individual object. Class doesn't consume any space.
3. Inheritance
   1. *When one object acquires all the properties and behaviors of a parent object*, it is known as inheritance. It provides code reusability. It is used to achieve runtime polymorphism.
4. Polymorphism
   1. If *one task is performed in different ways*, it is known as polymorphism. In Java, we use method overloading and method overriding to achieve polymorphism.
5. Abstraction
   1. Hiding internal details and showing functionality is known as abstraction. In Java, we use abstract class and interface to achieve abstraction.
6. Encapsulation
   1. *Binding (or wrapping) code and data together into a single unit are known as encapsulation*. For example, a capsule, it is wrapped with different medicines. A `java class` is the example of encapsulation. Java bean is the fully encapsulated class because all the data members are private here.

---

#### Abstract Class

##### <u>Difference between abstract class and interface</u>

Abstract class and interface both are used to achieve abstraction where we can declare the abstract methods. Abstract class and interface both can't be instantiated. But there are many differences between abstract class and interface that are given below. Simply, abstract class achieves partial abstraction (0 to 100%) whereas interface achieves fully abstraction (100%).

| Abstract class                                               | Interface                                                    |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| Abstract class can **have abstract and non-abstract** methods. | Interface can have **only abstract** methods. Since Java 8, it can have **default and static methods** also. |
| Abstract class **doesn't support multiple inheritance**. [Important] | Interface **supports multiple inheritance**. [Important]     |
| Abstract class **can have final, non-final, static and non-static variables**. | Interface has **only static and final variables**.           |
| Abstract class **can provide the implementation of interface**. | Interface **can't provide the implementation of abstract class**. |
| The **abstract keyword** is used to declare abstract class.  | The **interface keyword** is used to declare interface.      |
| An **abstract class** can extend another Java class and implement multiple Java interfaces. | An **interface** can extend another Java interface only.     |
| An **abstract class** can be extended using keyword "extends". | An **interface** can be implemented using keyword "implements". |
| A Java **abstract class** can have class members like private, protected, etc. | Members of a Java interface are public by default.           |
| **Example:** public abstract class Shape{ public abstract void draw(); } | **Example:** public interface Drawable{ void draw(); }       |

### Example of abstract class and interface in Java

Let's see a simple example where we are using interface and abstract class both.

```java
// Creating interface that has 4 methods  
interface A{  
  void a();// bydefault, public and abstract  
  void b();  
  void c();  
  void d();  
}  
  
// Creating abstract class that provides the implementation of one method of A interface  
abstract class B implements A{  
	public void c(){
    System.out.println("I am c");
	}  
}  
  
// Creating subclass of abstract class, now we need to provide the implementation of rest of the methods  
class M extends B{  
  public void a(){ System.out.println("I am a"); }  
  public void b(){ System.out.println("I am b"); }  
  public void d(){ System.out.println("I am d"); }  
}  
  
// Creating a test class that calls the methods of A interface  
class Test5 {  
public static void main(String args[]){  
  A a = new M();  
  a.a();  
  a.b();  
  a.c();  
  a.d();
}}

Output:
I am a
I am b
I am c
I am d
```

**Apart from these concepts, there are some other terms which are used in Object-Oriented design:**

1. <u>Coupling</u>

   1. Coupling refers to the knowledge or information or dependency of another class. It arises when classes are aware of each other. If a class has the details information of another class, there is strong coupling. In Java, we use private, protected, and public modifiers to display the visibility level of a class, method, and field. You can use interfaces for the weaker coupling because there is no concrete implementation.

2. <u>Cohesion</u>

   Cohesion refers to the level of a component which performs a single well-defined task. A single well-defined task is done by a highly cohesive method. The weakly cohesive method will split the task into separate parts. The java.io package is a highly cohesive package because it has I/O related classes and interface. However, the java.util package is a weakly cohesive package because it has unrelated classes and interfaces.

3. Association

   1. Association represents the relationship between the objects. Here, one object can be associated with one object or many objects. There can be four types of association between the objects:

      - One to One
      - One to Many
      - Many to One, and
      - Many to Many

      Let's understand the relationship with real-time examples. For example, One country can have one prime minister (one to one), and a prime minister can have many ministers (one to many). Also, many MP's can have one prime minister (many to one), and many ministers can have many departments (many to many).

      Association can be undirectional or bidirectional.

4. Aggregation

   1. Aggregation is a way to achieve Association. Aggregation represents the relationship where one object contains other objects as a part of its state. It represents the weak relationship between objects. It is also termed as a *has-a* relationship in Java. Like, inheritance represents the *is-a* relationship. It is another way to reuse objects.

5. Composition

   1. The composition is also a way to achieve Association. The composition represents the relationship where one object contains other objects as a part of its state. There is a strong relationship between the containing object and the dependent object. It is the state where containing objects do not have an independent existence. If you delete the parent object, all the child objects will be deleted automatically.

---

## Getters and Setters in Java

*In Java, getters and setters are methods used to access and modify the private fields of a class.* They are used to ensure that the private fields of a class are accessed and modified in a controlled way. 

A getter method is used to retrieve the value of a private field, while a setter method is used to modify the value of a private field.

The syntax for a getter method is as follows:

```java
public dataType getFieldName() {
    return fieldName;
}
```

The syntax for a setter method is as follows:

```java
public void setFieldName(dataType fieldName) {
    this.fieldName = fieldName;
}
```

In these examples, `dataType` represents the data type of the private field and `fieldName` represents the name of the private field.

The `this` keyword is used to refer to the current object, and is necessary when the parameter name of the setter method is the same as the name of the private field.

Getters and setters can also be used to enforce constraints on the values of private fields. For example, a setter method can check if the input value is valid and throw an exception if it is not.

In conclusion, getters and setters are an important part of Java classes, as they provide a way to access and modify the private fields of a class in a controlled way. They can also be used to enforce constraints on the values of private fields.

----

#### Java Wrapper Class

*A Java Wrapper Class is a class that allows the use of primitive data types as objects. It wraps around a primitive data type and gives it an object appearance.*

Wrapper classes are used to represent primitive values when an object is required. They also provide utility methods to convert primitive types to objects (boxing) and objects to primitive types (unboxing).

Java provides eight wrapper classes for the primitive data types. These are:

- Byte
- Short
- Integer
- Long
- Float
- Double
- Character
- Boolean

*Wrapper classes are immutable, which means that once an object is created, you cannot change its value. Wrapper classes are useful in situations where we need to treat primitive types as objects, such as in collections and generic classes.*

## Primitive Type to Wrapper Class Conversion

We can convert a primitive type to its corresponding wrapper class using the following syntax:

```java
// for example, converting an int to an Integer
int i = 10;
Integer integer = Integer.valueOf(i);

```

Java also provides an autoboxing feature that automatically converts a primitive type to its corresponding wrapper class. For example:

```
// autoboxing example
int i = 10;
Integer integer = i;

```

## Wrapper Class to Primitive Type Conversion

We can convert a wrapper class to its corresponding primitive type using the following syntax:

```
// for example, converting an Integer to an int
Integer integer = Integer.valueOf(10);
int i = integer.intValue();

```

Java also provides an unboxing feature that automatically converts a wrapper class to its corresponding primitive type. For example:

```java
// autounboxing example
Integer integer = Integer.valueOf(10);
int i = integer;

```

In conclusion, wrapper classes provide a way to use primitive data types as objects and are useful in situations where we need to treat primitive types as objects. They also provide utility methods to convert primitive types to objects and vice versa.

### Java Annotations

Java **Annotation** is a tag that represents the *metadata*  i.e. attached with class, interface, methods or fields to indicate some  additional information which can be used by java compiler and JVM.

Annotations in Java are used to provide additional information, so it is an alternative option for XML and Java marker interfaces.    

**@Override**

@Override annotation assures that the subclass method is overriding the parent class method. If it is not so, compile time error occurs.

Sometimes, we does the silly mistake such as spelling mistakes etc.  So, it is better to mark @Override annotation that provides assurance that method is overridden.

```java
class Animal{
	void eatSomething(){
		System.out.println("eating something");}
}

class Dog extends Animal{
	@Override
	void eatsomething(){
		System.out.println("eating foods"); //should be eatSomething
	}
}

class TestAnnotation1{
	public static void main(String args[]){
		Animal a=new Dog();
		a.eatSomething(); // Compiler Error
	}
}
```

`Output:Comple Time Error`

**Java Bean**

A Java Bean is a Java class that should follow the following conventions:

- It should have a no-arg constructor.
- It should be Serializable.
- It should provide methods to set and get the values of the properties, known as getter and setter methods.

## Why use Java Bean?

According to Java white paper, it is a reusable software component. A bean encapsulates many objects into one object so that we can access this object from multiple places. Moreover, it provides easy  maintenance.

**Simple example of Java Bean class**

```java
//Employee.java  

package mypack;  

public class Employee implements java.io.Serializable{  
    private int id;  
    private String name;

    public Employee(){} // constructor

    public void setId(int id){this.id=id;}  
    public int getId(){return id;}  

    public void setName(String name){this.name=name;}  
    public String getName(){return name;}  
}
```

**How to access the Java Bean class?**

To access the Java Bean class, we should use getter and setter methods.

Note: There are two ways to provide values to the object. One way is by constructor and second is by setter method.

#### POJO - **Important**

> POJO stands for **Plain Old Java Object**. It is an ordinary Java object, not bound by any special restriction other than those forced by the Java Language Specification and not requiring any classpath. POJOs are used for increasing the readability and re-usability of a program.

## Java Bean Properties

A JavaBean property is a named feature that can be accessed by the user of the object. The feature can be of any Java data type, containing the classes that you define.

A Java Bean property may be read, write, read-only, or write-only.  Java Bean features are accessed through two methods in the Java Bean's 
implementation class:

**1. getPropertyName ()**

For example, if the property name is firstName, the method name  would be getFirstName() to read that property. This method is called the accessor.

**2. setPropertyName ()**

For example, if the property name is first Name, the method name would be setFirstName() to write that property. This method is called the  mutator.

### Advantages of Java Bean

The following are the advantages of Java Bean:

- The Java Bean properties and methods can be exposed to another application.
- It provides an easiness to reuse the software components.

### Disadvantages of Java Bean

The following are the disadvantages of Java Bean:

- Java Beans are mutable. So, it can't take advantages of immutable objects.
- Creating the setter and getter method for each property separately may lead to the boilerplate code.

### Spring Bean

Spring Bean is nothing special, any object in the Spring framework that we initialize through `Spring container` is called Spring Bean. 

Any normal Java POJO class can be a Spring Bean if it’s configured to be initialized via container by providing configuration metadata information.

**Annotation Based Spring Bean Configuration Example**

```java
package com.journaldev.spring.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class MyAnnotatedBean {

	private int empId;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

}
```

### **Spring IOC Container - [Java Guides]**

**What is the Spring Container?**

The Spring container is responsible for instantiating, configuring, and assembling the `Spring beans`. 

The container gets its instructions on what objects to instantiate, configure, and assemble by reading configuration metadata. 

The configuration metadata is represented in XML, Java annotations, or Java code. 

It lets you express the objects that compose your application and the rich inter-dependencies between those objects.

**The responsibilities of IOC container are:**

- Instantiating the bean
- Wiring the beans together
- Configuring the beans
- Managing the bean’s entire life-cycle

The *`org.springframework.beans`* and *`org.springframework.context`* packages are the basis for Spring Framework’s IoC container. 

Spring framework provides two distinct types of containers.

1. *BeanFactory* container
2. *ApplicationContext* container

***BeanFactory*** is the root interface of Spring IOC container. 

***ApplicationContext*** is the child interface of *BeanFactory* interface that provides Spring AOP features, i18n etc.

One main difference between *BeanFactory* and *ApplicationContext* is that *BeanFactory* only instantiates bean when we call *getBean()* method while *ApplicationContext* instantiates singleton bean when the container is started, It doesn't wait for *getBean()* method to be called.

The following diagram shows a high-level view of how Spring works. Your application classes are combined with configuration metadata so that, after the ApplicationContext is created and initialized, you have a fully configured and executable system or application.

![https://3.bp.blogspot.com/-CsI_TxPJk_Y/W7cJZnb8z0I/AAAAAAAAEGw/oxiVbiAVSj8hrk06ynWoLqfcSolTTf3GgCLcBGAs/s1600/spring-ioc-container.png](https://3.bp.blogspot.com/-CsI_TxPJk_Y/W7cJZnb8z0I/AAAAAAAAEGw/oxiVbiAVSj8hrk06ynWoLqfcSolTTf3GgCLcBGAs/s1600/spring-ioc-container.png)

**What is Configuration Metadata?**

From the above diagram, the Spring IoC container consumes a form of configuration metadata. This configuration metadata represents how you, as an application developer, tell the Spring container to instantiate, configure, and assemble the objects in your application.

Three ways we can supply Configuration Metadata to Spring IoC container

1. **XML-based configuration**
2. **Annotation-based configuration**
3. **Java-based configuration**

## How to Create a Spring Container?

Spring provides many ApplicationContext interface implementations that we use are;

1. ***AnnotationConfigApplicationContext*** : If we are using Spring in standalone Java applications and using annotations for Configuration, then we can use this to initialize the container and get the bean objects.
2. *ClassPathXmlApplicationContext*: If we have spring bean configuration XML file in a standalone application, then we can use this class to load the file and get the container object.
3. *FileSystemXmlApplicationContext*: This is similar to *ClassPathXmlApplicationContext* except that the XML configuration file can be loaded from anywhere in the file system.

*`AnnotationConfigWebApplicationContext`* and *`XmlWebApplicationContext`* for web applications.

Let's write a code to create Spring container:

```java
// ClassPathXmlApplicationContext
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");`
```

> Note that we are supplying configuration metadata via applicationContext.xml file(XML-based configuration).
> 

```java
AnnotationConfigApplicationContext  context = new AnnotationConfigApplicationContext(AppConfig.class);
```

> Note that we are supplying configuration metadata via AppConfig.class file.
> 

## **In 28 Minutes**

V 330 - Use of Spring Framework

1. Tight Coupling
2. Loose Coupling
3. Dependency Injection
4. Spring Beans
5. Application Context
6. IOC Container
7. Auto Wiring
8. Component Scan

Approach : Build a Loose Coupled Hello World Gaming App with Modern Spring Approach

 

V 331

Design Game Runner to run games (Mario, SuperContra, Pacman etc) in an iterative approach:

1. Iteration 1: Tightly Coupled Java Code
    1. Game Runner Class
    2. Game classes: Mario, SuperContra, Pacman etc.
2. Iteration 2: Loose Coupling - Interfaces
    1. Game Runner Class
    2. GamingConsole Interface
        1. Game classes : Mario, Super Contra, Pacman etc.
3. Iteration 3: Loose Coupling - Spring Level 1
    1. Spring Beans
    2. Spring framework will manage objects and wiring
4. Iteration 3: Loose Coupling  - Spring Level 2
    1. Spring Annotations
    2. Spring framework will create, manage & auto-wire objects

V 333

V 334

Why is Coupling important?

Coupling : How much work is involved in changing something?

Coupling is important everywhere:

An engine is tightly coupled to a car. It takes a lot of effort to remove a engine from the car.

A wheel is loosely coupled to a Car.

You can take a laptop anywhere you go.

A computer, on the other hand, is a little bit more difficult to move.

Coupling is even more important in building great software

Only thing constant in technology is change

Business requirements change

Frameworks change

Code Changes

We want Loose Coupling as much as possible

We want to make functional changes with an less code chanegs as possible.

Let’s explore how Java Interfaces and Spring Framework help with Loose Coupling!

GameRunner → Mario → SuperContra → PacMan

GameRunner → GamingConsole (Interface) → Mario → SuperContra

Game runner class is no longer coupled to a specific game class after introducing interface

V336 [IMPORTANT]

**<u>V 338</u>** 

`record` - Java 16 new feature to make class → no need to make getter and setter they are automatically created.

**<u>V 440</u>**

Spring Questions You might be thinking about

Q1 : Spring Container vs Spring Context vs IOC container vs Application Context

Q2: Java Bean vs Spring Bean

Q3: How can I list all beans managed by Spring Framework?

Q4: What if multiple matching beans are available?

Q5: Spring is managing objects and perform auto-wiring.

1. But aren’t we writing the code to create objects?
2. How do we get Spring to create objects for us?

**<u>V 341</u>**

**What is Spring Container?**

Spring Container: Manages Spring beans & their lifecycle.

1. Bean Factory: Basic Spring Container
2. Application Context: Advanced Spring Container with entreprise-specific features
    1. Easy to use in web applications
    2. Easy internationalization
    3. Easy integration with Spring AOP

*<u>Which one to use?</u>*

Most enterprise applications use Application Context. It is recommended for web applications, web services, REST APIs and microservices.

**<u>V 342</u>**

Exploring Java Bean Vs POJO vs Spring Bean

POJO in Java stands for Plain Old Java Object. It is an ordinary object, which is not bound by any special restriction. It is not tied to any Java Framework.

```java
package com.in28minutes.learnspringframework.test;

class Pojo{
	private String text;
	private int number;

	public String toString(){
		return text + ":" + number;
	}
}

class JavaBean implements Serializable{ // EJB - Enterprise Java Bean
	// 1. Public no-arg constructor
	public JavaBean() {
		
	}

	private String text;
	private int number;
	
	// 2. getters and setters
	
	// 3. Serializable

}

public class SpringBeanVsJavaBean {
	public static void main(String[] args){
		Pojo pojo = new Pojo();

		System.out.println(pojo); // null:0
	}
}
```

Exploring Java Bean vs POJO vs Spring Bean

1. **Java Bean:** Classes adhering to 3 constraints: 
    1. Have public default (no argument) constructors
    2. Allow access to their properties using getter and setter methods
    3. Implement java.io.Serializable
2. POJO: Plain Old Java Object
    1. No constraints
    2. Any Java Object is a POJO!
3. Spring Bean: Any Java Object that is managed by Spring
    1. Spring uses IOC Container (Bean Factory or Application Context) to manage these objects.

V 343

Q3: How can I list all beans managed by Spring Framework?

```java
// Functional Programming
//System.out.println
Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
// System.out::println is called method reference

// All Beans
```

**Q4: What if multiple matching beans are available?**

```java
// Use @Primary and @Qualifier
```

V 344

Q5: Spring is managing objects and perform auto-wiring.

1. But aren’t we writing the code to create objects?
2. How do we get Spring to create objects for us?

Q6: Is Spring really making things easy?

V 350

```java
@Component
@Primary
class QuickSort implements SortingAlgorithm {}

@Component
class BubbleSort implements SortingAlgorithm {}

@Component @Qualifier("RadixSortQualifier")
class RadixSort implements SortingAlgorithm {}

@Component
class ComplexAlgorithm
	@Autowired
	private SortingAlgorithm algorithm;

@Component
class AnotherComplexAlgorithm
	@Autowired @Qualifier("RadixSortQualifier")
	private SortingAlgorithm iWantToUseRadixSortOnly;
```

```java
@Primary - A bean should be given preference when multiple candidates are qualified

@Qualifier - A specific bean should be auto-wired(name of the bean can be as qualifier)

Always think from the perspective of the class using the SortingAlgorithm:
1. Just @Autowired: Give me (preferred) Sorting Algorithm
2. @Autowired + @Qualifier: I only want to use specifc SortingAlgorithm - RadixSort
(Remember) @Qualifier has higher priority than @Primary
```

V 351

Exploring Spring - Dependency Injection Types

1. Constructor-based: Dependencies are set by creating the Bean using its constructor
2. Setter-based : Dependencies are set by calling setter methods on your beans.
3. Field: No setter or constructor. Dependency is injected using reflection.

V355

Exercise - BusinessCalculationService

```java
public interface DataService {
	int[] retrieveData();
}

public class MongoDBDataService implements DataService {
	public int[] retrieveData(){
		return new int[] { 11, 22, 33, 44, 55 };
	}
}

public class MySQLDataService implements DataService {
	public int[] retrieveData(){
		return new int[] { 1, 2, 3, 4, 5 };
	}
}

public class BusinessCalculationService{
	public int findMax(){
		return Arrays.stream(DataService.retrieveData()).max().orElse(0);
	}
}
```

Create classes and interfaces as needed

1. Use constructor injection to inject dependencies
2. Make MySQLDataService as primary
3. Create a spring context
    1. Prefer annotations
    2. Retrieve BusinessCalculationService bean and run `findMax` method.