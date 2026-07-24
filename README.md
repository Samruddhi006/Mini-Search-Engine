# Mini Search Engine

A Java console-based search engine that indexes local text documents and supports fast keyword search using a custom-built inverted index. The project demonstrates the use of fundamental data structures such as HashMap, Trie, ArrayList, and HashSet to implement core search engine functionalities from scratch.

## Features

- Reads and indexes multiple text documents.
- Tokenizes text using regular expressions.
- Performs case normalization.
- Removes punctuation and common stop words.
- Builds a custom inverted index for efficient keyword lookup.
- Ranks search results using **Term Frequency (TF)**.
- Supports **Boolean AND** search for multi-word queries.
- Provides **autocomplete suggestions** using a Trie (Prefix Tree).
- Frees raw document content after indexing to reduce memory usage.

## Project Structure

```
MiniSearchEngine/
│
├── Docs/
│   ├── doc1.txt
│   ├── doc2.txt
│   ├── doc3.txt
│   └── doc4.txt
│
├── src/
│   └── searchengine/
│       ├── Documents.java
│       ├── InvertedIndex.java
│       ├── Main.java
│       ├── SearchEngine.java
│       └── Trie.java
|
└── README.md
```

## Data Structures Used

| Data Structure | Purpose |
|---------------|---------|
| HashMap | Stores word frequencies and inverted index |
| ArrayList | Stores document IDs for each indexed word |
| HashSet | Efficient stop-word lookup |
| Trie | Prefix-based autocomplete suggestions |

## Search Workflow

```
Read Documents
      │
      ▼
Tokenization
      │
      ▼
Case Normalization
      │
      ▼
Remove Stop Words
      │
      ▼
Create Frequency Map
      │
      ▼
Build Inverted Index
      │
      ▼
Build Trie
      │
      ▼
Search / Boolean AND / Autocomplete
```

## Supported Operations

### 1. Keyword Search

Searches for a word and returns all matching documents ranked by **Term Frequency (TF)**.

Example:

```
Input:
search

Output:
document: doc1   Frequency: 7    TermFreq: 0.14583333333333334
document: doc3   Frequency: 3    TermFreq: 0.09375
document: doc2   Frequency: 3    TermFreq: 0.08333333333333333
document: doc4   Frequency: 1    TermFreq: 0.03571428571428571
```

---

### 2. Boolean AND Search

Returns only the documents that contain **all** query terms.

Example:

```
Input:
machine learning

Output:
doc3
```

---

### 3. Autocomplete

Suggests indexed words matching the entered prefix.

Example:

```
Input:
se

Output:
[search, searching]
```

## Time Complexity

| Operation | Complexity |
|-----------|------------|
| Document Processing | O(N) |
| Build Inverted Index | O(U) |
| Keyword Search | O(1) average + O(k) output |
| Boolean AND Search | O(sum of posting list sizes) |
| Trie Insert | O(L) |
| Prefix Search | O(L) |

Where:

- **N** = total number of processed words
- **U** = number of unique words
- **k** = number of matching documents
- **L** = length of the word/prefix

## Future Improvements

- TF-IDF ranking
- Phrase search
- Boolean OR and NOT queries
- File discovery using directory traversal
- GUI/Web interface

## Technologies Used

- Java
- Java Collections Framework
- File Handling (`java.nio.file`)
- Regular Expressions
- Object-Oriented Programming

## Learning Outcomes

This project helped reinforce concepts including:

- HashMap
- Trie (Prefix Tree)
- Inverted Index
- Boolean Search
- File Handling
- String Processing
- Time and Space Complexity Analysis
