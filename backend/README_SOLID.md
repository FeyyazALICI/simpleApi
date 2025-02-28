# IMPLEMENTING SOLID
1. Single Responsibility Principle (SRP)
Each class in the project has a single responsibility and only one reason to change.
2. Open/Closed Principle (OCP)
New functionality is added by extending existing components (e.g., implementing interfaces) rather than modifying existing code.
3. Liskov Substitution Principle (LSP)
Subtypes can replace their base types without altering system behavior. Interfaces are designed to ensure substitutability.
Example: A List implementation (e.g., ArrayList) can replace List without breaking expectations.
boolean deleteCat( Long id ); -> String deleteCat( Long id );
4. Interface Segregation Principle (ISP)
nterfaces are designed to be specific, ensuring that no class is forced to implement methods it does not need.
5. Dependency Inversion Principle (DIP)
High-level modules depend on abstractions (interfaces) rather than concrete implementations. Dependencies are injected via constructors or dependency injection.

In summary, by specialization of classes and using interfaces, I have tried to obey SOLID principles as much as possible.



# SOLID
1. Single Responsibility Principle (SRP)
    Definition: A class should have one and only one reason to change.
                This means that a class should only have one responsibility or job.
    Why SRP?
                Makes classes easier to understand.
                Avoids coupling unrelated functionalities.
                Reduces the risk of introducing bugs when making changes.

2. Open/Closed Principle (OCP)
    Definition: Software entities (classes, modules, functions) should be open for extension but closed for modification.
                This means you should be able to add new functionality without altering existing code.
    Why OCP?
                Encourages adding new features without breaking existing code.
                Reduces regression bugs when extending behavior.

3. Liskov Substitution Principle (LSP)
    Definition: Objects of a superclass should be replaceable with objects of a subclass without altering the correctness of the program.
                In other words, a subclass should behave in a way that it can stand in for its superclass without breaking functionality.
    Why LSP?
                Prevents unexpected behavior when using polymorphism.
                Ensures that subclasses adhere to the contract of the parent class.

4. Interface Segregation Principle (ISP)
    Definition: A class should not be forced to implement interfaces it does not use.
                Instead of having one large interface, break it into smaller, more specific ones.
    Why ISP?
                Reduces the burden of implementing unnecessary methods.
                Makes code more modular and easier to maintain.

5. Dependency Inversion Principle (DIP)
    Definition: High-level modules should not depend on low-level modules. Both should depend on abstractions.
                Abstractions should not depend on details. Details should depend on abstractions.
    Why DIP?
                Decouples high-level and low-level code.
                Makes the code more flexible and testable.
