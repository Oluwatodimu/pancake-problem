## Java Developer Test: Question Two

This repo contains the solution to second question of the java
developer test, where I created a console java application for
implement the shop keeper and the pancake algorithm.

## Objectives Completed
The following objectives were completed in this question:
1. created a non-concurrent implementation of the algorithm problem.
2. created a concurrent implementation of the algorithm problem.
3. Comparing, contrasting both versions of the code and stating my observations.


### Setting up the run environment

- ensure you have `Java 17` installed on your local machine.
- you may also have an IDE, preferably Intellij installed on your machine.
- clone this repo and navigate to the project directory.

### Running the application

- navigate to the project directory, then run this command `javac PancakeShopApp.java` to compile the code.
- if you want to see the non-concurrent implementation of the algorithm solution, run this command `java PancakeShopApp.java 1`.
- if you want to see the concurrent implementation of the algorithm solution, run this command `java PancakeShopApp.java 2`.

The results will be displayed in the console.


### Comparisons and Contrasts

| Criteria       | Non-Concurrent impl  | Concurrent impl  |
|----------------|----------------------|------------------|
| Execution      | sequential execution | async execution  |
| Implementation | straightforward       | slightly complex |
| Parallelism    | Limited              | Improved         |

I made the table above to summarize the comparisons of the different implementations. 

##### Non-Concurrent Version

- Sequential Execution: The non-concurrent code runs sequentially, performing each task one after the other in a single thread.
Tasks are executed in a linear fashion. This may not be efficient, especially for scenarios with potentially long-running operations.


- Simple Implementation: The code is straightforward and easier to understand without the complexities of managing concurrency.


- Limited Parallelism: There is no explicit parallelism in the non-concurrent version, and the entire process is executed in a single thread.


##### Concurrent Version:

- Asynchronous Execution: The concurrent version performs asynchronous tasks, allowing for potential parallelism.
Tasks can run concurrently without blocking the main thread. This can lead to improved performance for certain scenarios.


- Improved Parallelism: The use of CompletableFuture enables a more concurrent approach, allowing tasks to run in parallel, which can be beneficial
for scenarios with independent tasks.


- Complexity: The code in the concurrent version is more complex due to the asynchronous nature.
Proper handling of asynchronous tasks, result retrieval, and combining multiple tasks introduce additional complexity.


- Potential Performance Gain: The concurrent version has the potential for better performance by executing tasks concurrently, especially in scenarios 
where tasks can run independently.

### Observations

The non-concurrent version is simpler and easier to understand, making it suitable for scenarios with straightforward sequential execution.

The concurrent version provides potential performance improvements by leveraging asynchronous execution for tasks that can run concurrently.
However, the concurrent version introduces complexity, requiring careful handling of asynchronous tasks, error conditions, and result retrieval.

In summary, the choice between the non-concurrent and concurrent versions depends on the specific requirements of the scenario, and
also the size of the project. For a simple algorithm like Question 2, the simpler implementation may be preferred.


