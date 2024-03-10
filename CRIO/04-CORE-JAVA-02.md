# CORE-JAVA-02

#### Objective

Learn and understand Low Level Design Architecture

#### Background

There is a lot of research and development effort that goes into building an enterprise software, which serves the customers in achieving their business goals. There are a series of ideas and techniques executed to build the software iteratively and adapt to the changing requirements of the end users. This is where following good low level design architecture helps.

There has been a long evolution in the software development process right from writing a program in a single file to developing software modules independently. 

As the code base for a software began to grow, the number of developers working on them also grew, to make changes to support new requirements. 

It became harder to make room for new changes when there was collaboration required between several developers. A single wrong code change would affect the software quality and push the deadlines, making it difficult for the company. 

Various processes and architectures were developed to streamline the development process to make things easier and enable as many software developers as possible to work simultaneously. 

In this module. We will discuss one of the approaches which can be followed to make it easier to solve a bigger problem. This can be applied successfully to every problem, small or large.

#### Primary Goals

In this module, you will focus on the following:

1. Get introduced to the components of Low Level Design Architecture
2. Understand entities and identify them with the help of real-world examples
3. Gather requirements and translate them into use cases
4. Understand the need for a datastore



#### Components of Low Level Design Architecture



![image](https://storage.googleapis.com/crio-content-container-assets/ME_OBJECT_MODELING_V2_MODULE_ME_OBJECT_MODELING_V2_MODULE_ARCHITECTURE_1640323200_image_0.png)



### Entities

These contain different objects sharing a resemblance with real objects. They collaborate with the Use Case components for execution of business logic whenever required.

### Use Cases

These components contain the logic to solve a particular task independently, in collaboration with the Entity components. They also interact with the Data Repository component to exchange information wherever required.

### Data Repository

This component is responsible for storing the data which may be required for the Use Case components to execute their business logic.

### Controller

These components are responsible for executing different kinds of use cases based on the input from the external system or actor.

For example, a web browser client interacts with a backend server for different requests. The controller in the backend application redirects the request to the relevant logic to serve different requests.

### Main Application

This component acts as an aggregator where all the other components are assembled together to make the program function smoothly. This is the entry point for the application.

#### Process flow across the components

Let’s consider an example scenario where a user adds an item to a shopping cart.

This is how the data flow would take place across the components.

1. The Main Application is initiated and can accept requests from the client.
2. A request to Add Item will be received by the Controller component.
3. Controller will invoke the relevant Use Case component (Add Item to Cart) to execute the logic for adding the Item to Shopping Cart.
4. The Use Case component will utilize the matching Entity component representing the real world object (Item) for execution of the business logic.
5. The Use Case component will interact with the Data Repository component and ask it to store the Item in the User’s Shopping Cart.
6. On successful completion of the task, the Use Case component will send back a successful response to the Controller component.
7. The Controller component will then notify the requesting client about the response.

#### What is an entity?

Entity is the representation of a real-world object which may be of physical existence like Car, Smartphone, TV, House, etc. or conceptual existence like a company, a website or even a Youtube channel.

Entities are also known as domain objects as they are tied to a concept (real world objects like Manager, Teacher, Song Album, etc.). Entities are used when it is required for execution of any business logic. It holds vital information about the object for which it is modelled.

#### Sample Scenario 1

Universities keep track of the students who have enrolled, what subjects they are taking and which teachers handle those subjects. What are the entities you would identify in this case?

Think through it before checking the given answer.

Answer:

1. University
2. Student
3. Subject
4. Teacher



#### Entity Identification Checklist

Let’s use the below checklist to go about identifying the entities:

- Real-World Objects (Entities) are identified as a **noun**. So, we should search for them in the description. Note that nouns can belong to a wide category. Reference: [Noun Definition and Classification](https://edifyenglish.com/noun-definition-and-classification/).
- The entity must be a singular word to remain consistent (if we ever end up having a plural form).
- Ignore some of the nouns if we feel they won’t hold any vital information related to the problem description.

Note: Entity Identification is an iterative process. There are chances that we might miss some of them. As we go to the next steps while building the software, we might come up with the missing entities, which might not have crossed our minds in the initial phase.



#### Sample Scenario 2

Let’s consider this scenario of a Mobile Payment Application. A User can make Credit Card payments using an Application linked to their bank Account.

What entities may be involved in this problem description?

This is the list of entities identified when walking through the above mentioned checklists.

- User
- Credit Card
- Payment
- Application (removed)
- Bank Account

**Application** is removed because it doesn’t provide any information useful enough to solve the above problem.

**Payment** is identified as a singular term to remain consistent with our rules.

#### Activity

Read this problem statement in detail - [Movie Booking](https://docs.google.com/document/d/1I88MfqspjSu1jJxEQjIORMS1hoS8GXijLinqlNV-tzA/edit?usp=sharing) - and list down all the entities that hold information required to solve the problem.

Spend about 10 min to understand the problem statement thoroughly. We’ll continue to use this problem throughout this Micro Experience.

Note: Make sure you try this before looking at the Debrief below.

#### Activity Debrief

List of entities we can identify:

1. Cinema
2. Screen
3. Show
4. Ticket
5. Movie
6. Customer ( Improvised - as User is too generic)
7. Seat - **Seat** is identified as a singular term to remain consistent with the naming conventions.

---

#### What is a Requirement?

Requirement is something that your system has to do, for it to meet expectations. Requirements are gathered when we spend our time with the users and try to understand what they expect from the system.

Requirements of a software system could be quite comprehensive and the extra details make it harder to visualize the functionality required from the system. This problem can be overcome by a technique called Use Case Identification.

#### What are Use Cases?

A use case is a well defined task or activity that has to be performed by the system, in line with the functionality it supports. Requirements could translate to one or more use cases.

#### Use Case Identification Checklist

Let’s use the below checklist to go about identifying the Use Cases:

- Identify the actors or stakeholders of the system which interact with the system from the outside. List down what they want to interact with the system for. These would be use cases. For example
  - Student learning from an online course
  - Bank managing the accounts of the customers
  - Chrome browser requesting a resource from an external server
- Browse through the list of given requirements and try to understand what are the goals and expectations of the user from the system. Here, we primarily focus on "What" the user wants instead of “How” things will be done for that particular goal. These would be use cases. For example
  - Travelers book a train ticket
  - Customer buys a product
  - Borrower asks for a loan
- Apply CRUD (Created, Read, Update, Delete) technique on the data entities by identifying those that need to be handled by the system. These would be use cases. The below table is a good example of the CRUD technique.

![image](https://storage.googleapis.com/crio-content-container-assets/ME_OBJECT_MODELING_V2_MODULE_ME_OBJECT_MODELING_V2_MODULE_ARCHITECTURE_1640323200_image_1.png)

Eliminate the extra identified use cases if they are not mentioned in the requirements of the problem description.

#### Activity

Let’s use the problem statement for [Movie Booking Application](https://docs.google.com/document/d/1I88MfqspjSu1jJxEQjIORMS1hoS8GXijLinqlNV-tzA/edit?usp=sharing). You will see a list of requirements defined. Try to identify the use cases for that application.

Note: Make sure you try this before looking at the Debrief below.

#### Activity Debrief



![image](https://storage.googleapis.com/crio-content-container-assets/ME_OBJECT_MODELING_V2_MODULE_ME_OBJECT_MODELING_V2_MODULE_ARCHITECTURE_1640323201_image_2.jpg)

From the above use case diagram, it is evident that actor user wants to accomplish the following goals:

1. Browse the available movies.
2. Browse the shows for different movies.
3. Browse the available show seats to check for favourite spots to get a better experience.
4. Book the Show ticket.
5. Cancel the Show ticket.

Another actor System also plays an important role in doing the following roles:

1. Add Movies in the database.
2. Add upcoming shows for the movies.

Keep in mind that the use case diagram only deals with WHAT the actor wants to accomplish and it doesn’t care about the steps taken to accomplish the goal (HOW).

The use cases pretty much define the services we might want to implement in our system.

#### Why do we need a Data Store?

Systems need to store and access data when needed. This data could be anything, can be stored anywhere and can be available in many formats. Data could be about bank statements, inventory items in a hardware store, e-commerce product list, etc.

The software system relies on the data store to store or retrieve this data and use it to execute the requirements.

Data stores are useful in many different scenarios. Typically, the store multiple types of data that can be linked together, such as:

- Students in a school and their grades
- Customer records and sales information
- Patients’ records and doctors’ information
- Transactions between bank accounts
- Taxpayer information and income tax payments



#### Activity

In the [Movie Booking Application](https://docs.google.com/document/d/1I88MfqspjSu1jJxEQjIORMS1hoS8GXijLinqlNV-tzA/edit?usp=sharing), what are the relevant entities which you feel should be stored in a Data Store. Think about it well, before looking at the debrief :)

#### Activity Debrief

Considering the real-world scenario, here are some pointers to strengthen the claims for certain data to be stored:

1. Users continue to use the Booking Application so it is prudent to store their data.
2. Ticket booking data can be used to check tickets, available seats and calculate revenue.
3. Shows need to be added for future dates as Users might prefer advance booking.
4. Movie Data needs to be stored so that they can be added to the Catalog.
5. List of Cinemas and its number of screens across the city.

Let us know if you think of more scenarios.

---

#### Identify relationships between entities

#### Objective

Learn about class relationships between objects and its role in software development.

#### Background

A good software design following OOP principles is successful in splitting a large application into smaller pieces called objects. Each object represents different parts of the application. These objects aren’t randomly made up from nowhere. They are outcomes of our way of thinking while trying to solve the problem. Each of these objects have their own data, responsibilities and collaboration goals. Collaboration is a must between these objects with different responsibilities, so that they come together to solve the problem.
In this module, we are going to explore different ways in which objects can collaborate with each other.

#### Primary Goals

In this module, you will focus on the following:

1. Explore different types of class relationships and their purpose.
2. Discover relationships for different scenarios and draw an object diagram.