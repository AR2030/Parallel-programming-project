# Multithreaded File Search in Java
This project demonstrates how to use multithreading to efficiently search for a specific keyword in large files. By splitting the file into chunks and processing them in parallel using threads, the program achieves faster search performance compared to sequential processing.

---

## Features
- Efficient processing of large files using multithreading.
- Searches for a specific keyword in the file.
- Aggregates results from all threads to display matching lines.
- Easily configurable for file path, number of threads, and search keyword.

---

## Steps to Use

### 1. Prerequisites
- Java Development Kit (JDK) 8 or higher.
- A large file to test the program (e.g., `largefile.txt`).

### 2. How It Works
- **Divide the File**:
  - The program splits the file into chunks based on its size and the specified number of threads.
- **Multithreading**:
  - Each thread processes one chunk of the file in parallel.
- **Keyword Search**:
  - Threads search for the keyword line by line in their assigned chunk.
- **Aggregate Results**:
  - Results from all threads are combined to display lines containing the keyword.


