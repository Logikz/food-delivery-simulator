# CS665 Summer 2018 Assignment 2

This is assignment 2 for CS665 Summer 2018.  The following readme can be used to guide the user 
to install and run the application using Java.  

-Nick Cuneo


# Program Assumptions

I felt the assignment was poorly described, and involved several points that contradicted itself.  
As such I had to make many assumptions so that we could approach a reasonable solution involving a 
few design patterns as described in the lecture for the module.  Here is a list of assumptions used:
- Each store could only provide 1 type of good.
- There was only 1 type of each store.
- Orders were considered 'birthday' orders based on random selection.
- Rush hour appears as a window that occurs once throughout the life of the program
- The map is designed as a grid system, with each cell representing a location.  Since the cells 
mimic the real world with streets, they can contain many vehicles and shops in one cell.
- Vehicles take 1 time tick to 'pick up' or 'deliver'.
- The state is created randomly on each invocation, as such sometimes it's possible to get many frozen orders
and not many vehicles capable of hosting those orders.
- Drivers aren't smart enough to figure out the best route, and instead that information is determined when
the order is assigned to the driver.
- New orders aren't received during execution of the program

# Implementation Description
The main focus of the assignment was deriving the right patters around notifying order updates and 
assigning orders in various conditions for a food delivery app.  I generated random data for each
execution cycle in the main, I considered making it fed in by console input or modifiable via a configuration
file.  As this wasn't really the focus of the assignment to create different scenarios, I left it as is.

There are many software design patterns that can be used to implement a system designed around
notifications and asynchronous events.  In this assignment we use the observer pattern to monitor orders
and push status updates.  We also use the strategy pattern to allow vehicles the ability to change their
driving behavior whether they have an order or not.  Finally the abstract factory pattern was uses so
that we could have stores provide the right kind of goods, since the package tree are the same for those
two class hierarchies. There were many considerations taken into picking these design patterns as well 
as implementing them to provide the most flexibility and maintainability.

The assignment offers flexibility in the ability to add new stores and goods, as they are hierarchical
and adding new stores and goods should be as simple as implementing the base classes provided.  If a new
type of product were to be implemented, such as clothing, all one would need to do was add a Clothing
base class extending Good, as well as a clothing store to extend the Shop base class.  New kinds of 
vehicles can also be added simply by extending the vehicle class.  Since the project involves printing
information to the console, helper interfaces are provided for those new types of stores and vehicles
to help them easily get displayed onto the map grid.

I attempted to organize the classes in a logical fashion, to make it easier to add new classes in the future.
Since Shops can provide one kind of good, those class trees are similar, which makes sense.  I tried to keep
the code path straightforward and synchronous which makes it easy to follow the flow of the program.  I 
attempted to make each actor true to the knowledge that actor would have, such as the vehicle should have
a GPS, which gives them driving directions as well as provides them the destinations to go to.  Their positions
are updated back in the DeliveryManager using the observer pattern so the DeliveryManager can observe
all the vehicles in the system.  One area that possibly could be enhanced further would be to allow
for new orders to come in during the course of the program execution lifecycle.  Rather than processing
orders each tick of the application, we would want to use the observer pattern to add new orders to the list,
and as vehicles finish their delivery we could allocate new orders to them immediately.

Since we are performing many type of mapping operations, such as calculating distance and finding
the closest objects to navigate to, a MapUtils was created to allow for static utility methods to give
different areas of the code the ability to look for close objects or calculate the distance to one or
more objects.  Also, base abstract classes were used to try to allow for some common functionality to
be consolidated in one method rather than duplicated among the base classes.


# Setup
 
You need to install Apache Maven (https://maven.apache.org/)  on your system.

# Compilation 

Compilation and packaging is done with maven, and is easily ran from windows or linux with one of
the following scripts.

## Windows
```bash
compile.bat
```

## Linux

```bash
./compile.sh
```


# Running

The application can be ran by executing one of the following scripts on windows or linux.

## Windows

```bash
run.bat
```

## Linux

```bash
./run.sh 
```

# Using Findbugs 

To see bug detail using the Findbugs GUI, use the following command "mvn findbugs:gui"

Or you can create a XML report by using  


```bash
mvn findbugs:gui 
```

or 


```bash
mvn findbugs:findbugs
```

# Run Checkstyle 

To analyse this example using CheckStyle run 

```bash
mvn checkstyle:check
```


CheckStyle code styling configuration files are in config/ directory. Maven checkstyle plugin is set to use google code style. You can change it to other styles like sun checkstyle. 

# Unit Tests

A sampling of unit tests are included to show functionality and to illustrate the design patterns
ability to make testing easier.  The tests themselves are by no means complete nor offer full coverage
of the assignment as it was not requested.




