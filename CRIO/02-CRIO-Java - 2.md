## SESSION 3

### Introduction

Understand Inheritance:-  One of the fundamental concepts in Object Oriented Programming.

Duration: 1 hour

Focus: OOP Concepts, Inheritance

Pre-requisites: None

#### Objective

Learn Inheritance by applying it in a practical scenario.

#### Background

YouChat is a messaging platform used by millions of users across the globe. People love to interact with each other to share their thoughts, opinions and be updated about the happenings around the world. The platform currently makes it possible to do these activities through a text message.

 A breakthrough in the world of the internet occurred when it became possible to upload content in the form of different media types like audio / images / video. This improved the way communication can be done in these media formats as well which in turn provides a better experience.

Top Messaging platforms have started to roll out features for people to be able to communicate in the form of image and audio media format. YouChat is one of the top messaging platforms who wants to implement these features as soon as possible for them to leave the competition behind .

You are given the opportunity to lead this initiative and help YouChat develop these features and make it available to the general public.

#### Primary goals

1. Learn why Inheritance is needed
2. Understand where and how to apply Inheritance.

#### Here comes our Saviour - Inheritance !!



Inheritance allows you to reuse data and behavior from the parent class.

From the previous Approach #2, we may have noticed that classes of each message type consist of common  fields and methods which can be pulled out from each of the classes.

These common entities can be then put into a parent class which in our case is a `Message` Class.  This phenomenon is called  **Generalization.**

Now most of the common entities are pulled out from each of these classes, but they want to do at least one thing differently ( behaviour ) and/or add more data. This phenomenon is called  **Specialization.**

That way each child class (representing one message type) remains independent of other child classes.



#### What does the Inheritance based design look like?



It can be easily presented with the help of following diagram



![image](https://storage.googleapis.com/crio-content-container-assets/ME_INHERITANCE_JAVA_MODULE_BYTE_image_14.png)





Implementation of this solution can be found in this [oop_solution](https://gitlab.crio.do/crio_bytes/me_inheritance_java/-/tree/oop_solution/messaging/src/main/java/com/crio/messaging) branch of code repository.

We use a `Message` parent class to define fields & their methods which are common to all types of messages. E.g. message id, sender, receiver etc.

For each of the message types, namely, Text, Image & Voice, we create new children classes `TextMessage`, `ImageMessage` & `VoiceMessage`. These children classes inherit the `Message` class and hence need only add any fields and methods specific to them.



![image](https://storage.googleapis.com/crio-content-container-assets/ME_INHERITANCE_JAVA_MODULE_BYTE_image_15.png)







![image](https://storage.googleapis.com/crio-content-container-assets/ME_INHERITANCE_JAVA_MODULE_BYTE_image_16.png)







![image](https://storage.googleapis.com/crio-content-container-assets/ME_INHERITANCE_JAVA_MODULE_BYTE_image_17.png)







![image](https://storage.googleapis.com/crio-content-container-assets/ME_INHERITANCE_JAVA_MODULE_BYTE_image_18.png)





Try executing the new OOP based approach of the solution

```

```



```
#Change the current branch to “oop_solution” branch.
$ git checkout oop_solution
```

The new solution will be reflected in the IDE. Run the Main.java and check the new output.



#### 



### Advantages

- Code reuse for common fields and methods (by inheriting parent class) as opposed to repeated code in the earlier Approach #2.
- Clean class structure for the child classes that are easily maintainable. Each message type can be modified without impacting other classes since it holds fields/methods specific to itself.
- The Clients get only the fields and methods for the message type they are interested in by creating an object of the child class. This is in contrast to getting all fields by creating an object of a large single Message class, as was the case with  Approach #2 earlier.



#### Close Enough!



Pay close attention to these lines of code.

<br>

[single_class_proposal/AndroidHandler.java](https://gitlab.crio.do/crio_bytes/me_inheritance/-/blob/master/single_class_proposal/android_client_handler.py)

```

```



```
private static void deliverText(Message message);
private static void deliverImage(Message message);
```

<br>

[oop_solution/AndroidHandler.java](https://gitlab.crio.do/crio_bytes/me_inheritance_java/-/blob/oop_solution/messaging/src/main/java/com/crio/messaging/AndroidHandler.java)

```

```



```
private static void deliverText(TextMessage textMessage);
private static void deliverImage(ImageMessage imageMessage);
```

Note that with the Inheritance based design, the invocation of the methods need to know which exact message type to use. This is not very good.

Inheritance has helped clean up the structure from the service provider side/implementation side (Message.java ).

We’ll see how this design can be further improved to help the caller/client side (AndroidHandler.java) when we visit Polymorphism.



#### New Trend! Another Hustle!

A new requirement has come in to add support for

- Video messages

How easy or difficult is it to implement this with the Inheritance based design?

#### Summary of Inheritance



- **Why** is it useful? - It helps separate out common functionality and fields so that they can be reused across many other classes cleanly. Avoids code redundancy.
- **Where** to apply? - In the code base when different objects need to share common fields and functionality.
- **How** to apply? - Define a parent class with all shared functionalities & child classes that inherit these from the parent class.
- **What** is the drawback if we don’t use inheritance? - Code duplication, Higher maintenance cost.



#### Types of Inheritance



- [Types of Inheritance](https://www.studytonight.com/cpp/types-of-inheritance.php)

- Single inheritance
- Multiple inheritance
- Hierarchical inheritance
- Multilevel inheritance
- Hybrid inheritance



#### Practical Scenarios



- Java Collections is a good example of application of inheritance.



![image](https://storage.googleapis.com/crio-content-container-assets/ME_INHERITANCE_JAVA_MODULE_BYTE_image_20.png)





- Java Exceptions is among one of the notable Class hierarchies displaying the effective use of inheritance.



![image](https://storage.googleapis.com/crio-content-container-assets/ME_INHERITANCE_JAVA_MODULE_BYTE_image_21.png)







#### General rules of Inheritance



- A class (child class) can extend another class (parent class) by inheriting its features.
- Implements the DRY (Don’t Repeat Yourself) programming principle.
- Improves code reusability.



#### Inheritance vs Composition



- Composition is another way to reuse code from another class. It is achieved by creating a class containing instances of other classes that implement the desired functionality, rather than inheritance from a base or parent class.
- Further Reading:- [Inheritance Vs Composition (veerpalbrar.github.io)](https://veerpalbrar.github.io/blog/2021/06/30/Inheritance-vs-Composition)



#### Encapsulation and Abstraction vs Inheritance



- Encapsulation and Abstraction are helpful to develop and maintain a big codebase.
- When there are similar objects in this big codebase that share common functionality, the common functionality and fields can be separated out into a separate base class which is then inherited by child classes.

#### What are Linux operating systems?



An **operating system** is a set of tools which makes our life easier to interact with computers eg: Go through files and folders, configure settings, connect to network etc. Windows is a commonly operating system.

**Linux is a kernel (i.e, a template)** based on which operating systems can be created. The linux kernel takes care of important operations like processing files, taking care of memory, cpu etc.

Here are a few Linux based operating systems (also called Linux Distributions)

1. Ubuntu
2. Debian
3. Fedora

### Note

Windows is not based on the Linux kernel

Execute the `cd` command without any directory argument and observe where it changes the directory to (Hint: Use `pwd` to check where you were taken to)

If you don’t provide any argument to the `cd` command, it defaults to the user’s home directory

`ls /` lists all the root level directories

1. `ls -l` is used to list file and directories in long listing format, along with some other information. (Note: You don’t need to worry about the details here)
2. `ls -l` is used to list file and directories in long listing format, along with some other information. (Note: You don’t need to worry about the details here)

3. Let’s try out giving multiple options at a time. As we can see `ls -l -a` or `ls -la` command lists out the the hidden files as well in the long listing format.
4. `ls -lt` sorts the contents listed based on the last modification time, newest first. As this is the default sort order, you won’t notice any differences

#### Absolute path

In the Linux terminal, there is always more than one way to do the same thing. Below are some options to display the contents of `/var`.

1. `cd` to the `/var` directory and do `ls`
2. Do `ls /var` instead



![image](https://storage.googleapis.com/crio-content-container-assets/ME_LINUX1_MODULE_BYTE1_image_33.png)

Irrespective of the current working directory, you can run `cd /var` to take you to `/var`. Similarly, `ls /var` can be executed from any working directory and it will always display the contents of `/var`.

This is called using the **absolute path** where we provided the full path to a directory right from the root of the filesystem: `/`.

### Tip

To make it explicit a path is a directory, it’s common to add an additional `/` at the end of the paths.

- Eg: `/var/` is same as the `/var` directory
- Both `ls /var` and `ls /var/` will list the contents of the `/var` directory
- You’ll get a below error if you append `/` to a non-directory

![image](https://storage.googleapis.com/crio-content-container-assets/ME_LINUX1_MODULE_BYTE1_image_34.png)

#### Relative Path

Let’s look back into this example where we are already inside the `/home/crio-user/workspace/` directory and want to `cd` to the `/home/crio-user/workspace/linux-commands` directory. This is how we know to do that currently.



![image](https://storage.googleapis.com/crio-content-container-assets/ME_LINUX1_MODULE_BYTE1_image_35.png)





Given that we are already in `/home/crio-user/workspace` and that this directory contains the `linux-commands` directory, can we do this better?

(Side note: We say `/home/crio-user/workspace` is the **parent directory** of `linux-commands` directory here)



![image](https://storage.googleapis.com/crio-content-container-assets/ME_LINUX1_MODULE_BYTE1_image_36.png)

You’d notice the `cd linux-commands` command not starting with the `/` character. This is called a Relative path.

**Relative paths** are *relative* to the present working directory

### Note

Let’s look at some common terminologies

1. **Current or Present working directory** : As the name indicates, it represents the current directory in which we are currently working.
2. **Parent directory:** This represents the directory which **contains** the present directory.

1. Suppose the folder structure is like, we have a directory named “children/” inside which we have directories named “child1/” and “child2/”
2. Let’s say we are currently working in the “child2/” directory. In this case, parent directory is the “children/” directory.

1. **Previous working directory**: Suppose if we take example of previous file structure. If we start working with the “child1/” directory and then we `cd` to “child2/” directory, then previous working directory will be “child1”.



#### Additional examples on Relative Path

A list of special relative paths are listed in the table below and additional examples are in the code block that follow

![image](https://storage.googleapis.com/crio-content-container-assets/ME_LINUX1_MODULE_BYTE1_image_37.png)

1. Using relative path to go back to the parent directory

```bash
crio-user@ajay-criodo:~$ pwd
/home/crio-user
crio-user@ajay-criodo:~$ ls .    ## list contents of the present working dir
workspace
crio-user@ajay-criodo:~$ cd ./workspace  ## path is relative to the present working dir
crio-user@ajay-criodo:~/workspace$ pwd
/home/crio-user/workspace
crio-user@ajay-criodo:~/workspace$ cd ..  ## one level up to the parent dir
crio-user@ajay-criodo:~$ pwd
/home/crio-user
```

1. Easily go back to the previous working directory

```bash
crio-user@ajay-criodo:~$ pwd
/home/crio-user
crio-user@ajay-criodo:~$ cd workspace/ajay-criodo-ME_QMONEY/
crio-user@ajay-criodo:~/workspace/ajay-criodo-ME_QMONEY$ cd ../..  ## two levels up
crio-user@ajay-criodo:~$ pwd
/home/crio-user
crio-user@ajay-criodo:~$ cd -  ## go back to the previous dir you came from
/home/crio-user/workspace/ajay-criodo-ME_QMONEY
crio-user@ajay-criodo:~/workspace/ajay-criodo-ME_QMONEY$ cd -  ## and back again
/home/crio-user
```

1. Using path relative to the home directory

```bash
crio-user@ajay-criodo:~$ cd ~/workspace  ## path relative to the home dir
crio-user@ajay-criodo:~/workspace$ pwd
/home/crio-user/workspace
crio-user@ajay-criodo:~/workspace$ cd ../workspace/../workspace/../workspace   ## fun :)
crio-user@ajay-criodo:~/workspace$ 
```

#### Activity

Using the Crio workspace UI, create directories `linux1/` and `linux2` inside `~/workspace/linux-commands`. Within these directories create two txt files each as well.

The directory structure will be similar as shown



![image](https://storage.googleapis.com/crio-content-container-assets/ME_LINUX1_MODULE_BYTE1_image_38.png)

Now, use the terminal to try out the following steps utilising relative paths

1. Move to “linux1/”
2. Check the contents of “linux2/”
3. Check the contents of current working directory
4. Move to “linux2/”
5. Check the contents of “linux2/”
6. Move to previous working directory

### Tip

1. The `tree` command prints the directory structure as shown

![image](https://storage.googleapis.com/crio-content-container-assets/ME_LINUX1_MODULE_BYTE1_image_40.png)

1. The command prompt given below tells us the current working directory as well (`~/workspace/linux-commands`) - saves us from using `pwd` every now and then!

![image](https://storage.googleapis.com/crio-content-container-assets/ME_LINUX1_MODULE_BYTE1_image_41.png)

### References

1. [Absolute and relative paths](https://www.youtube.com/watch?v=ephId3mYu9o)

## Polymorphism

### Introduction

Understand Polymorphism - One of the fundamental concepts in Object Oriented Programming.

Duration: 1 hour

Focus: OOP Concepts, Polymorphism

Pre-requisites: None

#### Objective

Learn polymorphism by applying it in a practical scenario.

#### Prerequisite

Taking up the [OOP Concepts: Inheritance](https://www.crio.do/learn/home/me/ME_INHERITANCE_JAVA) Byte is a prerequisite as the current Byte builds on top of the codebase used there.

#### Background

Polymorphism is about being able to change the functionality offered by a method, depending on the situation.

This is achieved in two ways:

1. **Method Overriding** - Child class implements a method that is already present in the parent class, resulting in the child method being invoked - *Dynamic polymorphism.*

1. **Method Overloading** - Writing multiple methods with the same name but with different sets of parameters (or order of parameters) so that the appropriate method gets invoked based on the parameters passed - *Static polymorphism.*

In this Byte, we will learn method overriding and touch upon method overloading. We’ll see how they simplify code.

#### Primary goals

1. Understand where and how to apply method overriding
2. Understand where and how to apply method overloading