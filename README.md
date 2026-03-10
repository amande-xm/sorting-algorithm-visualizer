# Sorting Algorithm Visualizer 📊

A desktop application built with **Java and JavaFX** to visualize how different sorting algorithms work in real time. This project was developed as part of a Software Engineering course on Object-Oriented Design.

## Features
* **Visual Sorting:** Watch arrays being sorted in real-time with dynamic bar charts and color interpolation (gradient mapping from light to dark blue).
* **Multiple Algorithms:** Currently supports **Merge Sort** and **Quick Sort**.
* **Custom Inputs:** Input your own custom array of integers via the UI to see them sorted.
* **Adjustable Speed:** Control the animation playback speed (Fast, Medium, Slow).

## Architecture & Design Patterns
This project focuses heavily on clean Object-Oriented principles and strict separation of concerns (Model-View separation):

* **Template Method Pattern:** The core sorting algorithm skeleton is defined in an abstract `SortAlgorithm` class. Subclasses (`MergeSort`, `QuickSort`) only implement specific hook methods (like `divide`, `merge`, and recursive bounds) without altering the overarching algorithm structure.
* **Snapshot / History Approach:** To safely animate the sorting process without freezing the JavaFX Application Thread, the algorithms do not manipulate the UI directly. Instead, they record "snapshots" of the array at every swap or merge step. The UI then plays back this history using a JavaFX `Timeline`.

## Getting Started

### Prerequisites
* Java JDK 8 or higher
* JavaFX SDK (if not bundled with your JDK environment)

### How to Run
1. Clone this repository:
   ```bash
   git clone [https://github.com/YourUsername/your-repo-name.git](https://github.com/YourUsername/your-repo-name.git)
