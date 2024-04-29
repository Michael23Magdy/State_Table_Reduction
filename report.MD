# Project Report: Sequential Logic Circuit State Table Reducer

## Introduction
The Sequential Logic Circuit State Table Reducer project aims to provide a tool for reducing the state table of a sequential logic circuit, thereby minimizing the number of flip-flops required and reducing the overall cost of the circuit. The project employs Java programming language and implements various classes to achieve its objectives.

## Classes Overview

### 1. State
The `State` class encapsulates the properties and functionalities of a state in the state table. It includes attributes such as index, next states, outputs, and methods for comparing and printing states.

### 2. StateTable
The `StateTable` class manages an array of indexed states and provides helper functions for state table operations. It facilitates the manipulation and organization of states within the state table.

### 3. ImplicationTable
The `ImplicationTable` class represents the implication table using a 2D array data structure. It implements algorithms to reduce the state table by identifying and eliminating redundant states. Additionally, it maintains an array to map old states to new, possibly fewer states.

### 4. NumberSystemConvertor
The `NumberSystemConvertor` class is responsible for converting decimal numbering to alphabet Excel-like numbering and Boolean values to bits. It assists in adjusting input and output formats to suit the requirements of the state table reduction process.

### 5. ConsoleColors
The `ConsoleColors` class provides methods for coloring the output tables, enhancing the readability and user experience of the console-based application.

### 6. Main
The `Main` class serves as the entry point of the application and orchestrates the user interface for interacting with the state table reduction process.

#### User Interface Flow
1. The user interface begins by prompting the user to enter the number of states, the number of inputs, and the type of circuit (Mealy or Moore).
2. An empty state table is then printed, and the user is prompted to fill in the table by entering state transitions and outputs. The user can rewrite rows if needed.
3. Once the user has finished entering data, they can input `-1` to signify completion.
4. The application processes the provided state table, generates the implication table, and performs state reduction.
5. Finally, the implication table, along with the grouped states and their new names, and the reduced state table are printed for analysis.

## Implementation Details
The project leverages the functionalities provided by each class to achieve the state table reduction process. Here's a brief overview of the implementation:

- The `State` class encapsulates the properties of each state in the state table, facilitating comparison and printing functionalities.
- `StateTable` manages the organization of states and provides methods for state table manipulation.
- `ImplicationTable` analyzes the state table to identify redundant states using the implication table technique and reduces the table accordingly.
- `NumberSystemConvertor` assists in converting numbering systems and Boolean values to meet the requirements of the state table reduction process.
- `ConsoleColors` enhances the visual presentation of the output tables by providing colored formatting options.
- `Main` orchestrates the execution flow of the application, guiding users through input, processing, and output stages.

## Conclusion
The Sequential Logic Circuit State Table Reducer project presents a robust solution for reducing the state table of sequential logic circuits. By leveraging Java programming language and implementing specialized classes, the project offers a user-friendly tool for minimizing flip-flop usage and reducing circuit costs. With further enhancements and optimizations, the project holds the potential to contribute significantly to the field of digital circuit design and optimization.



