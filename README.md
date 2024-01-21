# Encode Decode Step by Step
[![en](https://img.shields.io/badge/lang-en-red.svg)](/README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](/README.pt-br.md)

This project was developed with the purpose of performing bitwise file encodings.
Covering six encoding algorithms: delta, unary, Elias-Gamma, Fibonacci, Golomb, and static Huffman.
In addition, we created a graphical interface to facilitate visualization.

## How to use the application? 🤔
![How to use explanation](ReadMeImgs/como_usar.gif "How to use explanation")

## Requirements ⚠
1. [Java 8+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
2. [Node.js 12.18+](https://nodejs.org/en/)


## Installation 💾
### Method 1 Using the [releases](https://github.com/EncodeDecodeStepByStep/EncodeDecodeStepByStep/releases) available on this Github
#### Windows

![Download Release](ReadMeImgs/download_release.gif "Download Release")

#### Linux
1. Download the .zip file for Linux
2. Unzip the content and enter the extracted folder
3. Run the EncodeDecode.AppImage file, or in the terminal, execute the command ./encode-decode-step-by-step

> Note: In the Linux world, certain inconveniences may occur during the step-by-step display process. Avoid encoding or decoding a certain file, in which its path is in a folder with spaces or accents. In certain distributions, this generates errors, which are still being resolved.

### Method 2 Cloning this repository
1. Clone the repository
```
git clone https://github.com/EncodeDecodeStepByStep/EncodeDecodeStepByStep.git
```
2. Navigate to the frontend folder
```
cd EncodeDecodeStepByStep/frontend
```
3. Install dependencies
```
npm install
```
4. Build the application
```
npm run build
```
5. Install the distribution generated in EncodeDecodeStepByStep/frontend/dist
   ![ExtraContent](ReadMeImgs/instalador_encode_decode.png)

6. Oh, and don't forget to star 🌟 the repository, then it will work on the first try 😁


# $${\color{lightgreen} About\ the\ Project\ and\ Its\ Explanations}$$
This application aims to facilitate the understanding of fundamental encoders, covered in the Information Theory course. Among them, 6 encoders have been implemented: Elias-Gamma, Golomb, Unary, Delta, Huffman, and Fibonacci. Each of them has a unique way to visualize the data, just install the program and send a file with ASCII text to perform the encoding.


## $${\color{lightgreen} How\ to\ Use}$$
After starting the application, you can use the left menu to select the encoding process you wish to use, then select a file (It must be composed of ASCII characters).

![homepage.png](/ReadMeImgs/homepage.png)

Each encoding process has a different layout, especially designed to facilitate your learning.

![Huffman Encoder](/ReadMeImgs/Huffman-Encoder.png)

To perform the decoding, you must select the file that was generated from the encoding (same file, but with the .cod extension at the end). From this, you do not need to inform the algorithm used in the encoding to perform the decoding. This information is already saved in the header of the .cod file.

![Fibonacci Encoder](/ReadMeImgs/Fibonacci-Encoder.png)


## $${\color{lightgreen} Explanations\ of\ the\ Encoders}$$

⚠️ ***<ins style='color: orange'> Some images still being translated </ins>*** ⚠️

### $${\color{lightgreen} Unary\ Encoding}$$

Unary coding is extremely simple, we just need to extract the ASCII of the first symbol, with this value, we create a stream with that amount of 0's. Then, we do the same for the second symbol, but this time creating a stream of 1's, this process is repeated by alternating sequences of 0's or 1's.

Symbol | ASCII | Codeword
--- | --- | ---
C | 67 | 0000000000000000000000000000000000000000000000000000000000000000000
D | 68 | 1111111111111111111111111111111111111111111111111111111111111111111
E | 69 | 000000000000000000000000000000000000000000000000000000000000000000000


### $${\color{lightgreen} Delta\ Encoding}$$

#### $${\color{lightgreen} 1\degree\ Writing\ the\ value\ of\ the\ first\ symbol}$$

Delta encoding works through increments and decrements in the encoding queue, taking into consideration the previously encoded symbol. In this case, let's assume we have the following queue to be encoded:

| Queue | B  | C  | C  | B  | G  |
|-------|----|----|----|----|----|
| Ascii | 66 | 67 | 67 | 66 | 71 |

In this case, the value of the first codeword will be **01000010**, which is 66 in binary.

#### $${\color{lightgreen} 2\degree\ Obtaining\ the\ largest\ possible\ Delta}$$

Next, we need to find out what the largest increment or decrement in this sequence of symbols is, in this case, there is only 1 difference between 66 and 67, none between 67 and 67, but there is an increase of 5 between 66 and 71. Therefore, 5 is the largest delta of this encoding.

#### $${\color{lightgreen} 3\degree\ Assembling\ the\ codewords}$$

Starting from the initial symbol and the largest delta, we already have the capacity to start assembling the codewords. From here, the encoding follows the following algorithm:

![Delta Codeworks Step By Step](/ReadMeImgs/Delta-Codeworks.png)

### $${\color{lightgreen} Elias-Gamma\ Encoding}$$

#### $${\color{lightgreen} 1\degree\ Calculating\ the\ integer\ part\ and\ the\ remainder}$$

Elias-Gamma follows the same principle as the Golomb encoding, there is a prefix represented by a number N of 0's, a suffix represented by a remainder in binary of the same size N, and a stopbit 1 separating the two parts. The value N is calculated from the highest power of 2 within the number to be encoded. That is, if we want to encode the number 9, N will be equal to 3, because 2 raised to the power of 3 is 8, which is the highest power within 9. Knowing this, the remainder of this encoding will be just 1, because it is the distance that is lacking up to 9. Below is a more visual example:

![Elias Gamma Step by Step](/ReadMeImgs/Elias-Gamma-Step-by-Step.png)

#### $${\color{lightgreen} 2\degree\ Incrementing\ 1\ to\ ASCII}$$

This encoding causes a problem when we encode the first character of the ASCII table (code 0). This is because there is no exponent that will result in 0, even 2 raised to the power of 0 is 1. Therefore, we must add 1 at the beginning of the symbol's encoding and subtract 1 at the end of decoding.

### $${\color{lightgreen} Golomb\ Coding}$$

#### $${\color{lightgreen} 1\degree\ Defining\ the\ value\ of\ K}$$

Initially, we need to define a divisor, which will be the key for encoding and decoding. Usually, a power of 2 is used because it generates better results. Therefore, in the next example, we will use k=4.

#### $${\color{lightgreen} 2\degree\ Calculating\ the\ integer\ part\ and\ the\ remainder}$$

For each symbol, we obtain its ASCII code and divide by K, the value of the whole quotient will represent the number of 0's that will appear in the prefix of the codeword, and the remainder will be placed in binary in the suffix of the codeword, in the middle of these two parts we will place a digit 1. This will be the stopbit, and it will serve as a control to identify the position of the codewords in the decoding.

![Golomb Step By Step](/ReadMeImgs/Golomb-Step-By-Step.png)


### $${\color{lightgreen} Huffman\ Encoding}$$

#### $${\color{lightgreen} 1\degree\ Symbol\ Counting}$$

Consider the following sequence: 

#### $$\textcolor{lightgreen}{\fbox{a b c c d b b b}}$$

Now, let's count each of the elements:

a -> 1 <br>
b -> 4 <br>
c -> 2 <br>
d -> 1 <br>

#### $${\color{lightgreen} 2\degree\ Table\ Sorting}$$

In this phase, we only need to organize the table, sorting it according to the frequency of each symbol.

b -> 4 <br>
c -> 2 <br>
a -> 1 <br>
d -> 1 <br>

#### $${\color{lightgreen} 3\degree\ Building\ the\ Huffman\ Tree}$$

From now on, we must build the tree starting from the symbols with the lowest frequency.
Each leaf receives a weight, which is the counting of each of the symbols, and as we join these nodes, we create intermediate nodes, whose weights will be the sum of the weights of their children.

The objective here is to join the nodes with the smallest weights.

![Huffman Step By Step](/ReadMeImgs/Huffman-Step-By-Step.png)

**<span style='color: #49d180;'> Note: </span>** In this example, the generated tree was a nested structure [0,10,110,111], but this does not mean that all trees will have this format. The **'ad'** node generated a weight of 2, we joined it to **'c'**, because the sum of their weights (4) would be less than **'bc'** (6). However, in larger trees, it is common for this structure to gain a more balanced appearance.

#### $${\color{lightgreen} 4\degree\ Creating\ the\ Codewords}$$

From the associations assigned to each arc, we can create the codewords for each of the symbols.

$$\textcolor{lightgreen}{\texttt{b = 0}} \quad \textcolor{lightgreen}{\texttt{c = 10}} \quad \textcolor{lightgreen}{\texttt{a = 110}} \quad \textcolor{lightgreen}{\texttt{d = 111}}$$

Now it is enough to map the input, replacing the symbol with the corresponding codeword.


### $${\color{lightgreen} Fibonacci\ Encoding}$$

#### $${\color{lightgreen} 1\degree\ Obtaining\ the\ Fibonacci\ sum}$$

Fibonacci encoding is obviously carried out through the Fibonacci sequence, however, excluding the zero and the first one. Initially, we obtain the ASCII value of the symbol, with it, we traverse the Fibonacci sequence from right to left, starting from the largest number in the sequence that is less than the obtained ASCII, from there, we must discover which sequence of numbers, and its sum, is equal to the found ASCII. With the examples below, this dynamic becomes easier.

![Fibonacci Step By Step](/ReadMeImgs/Fibonacci-Step-By-Step.png)

#### $${\color{lightgreen} 2\degree\ Queuing\ the\ Codewords}$$

Next, we queue the codewords, and we separate each of them with the stop bit 1, so when decoding, when we find a sequence of two consecutive 1's, we will know that it is the limit of a codeword.

![Fibonacci Step By Step 2](/ReadMeImgs/Fibonacci-Step-By-Step-2.png)

#### $${\color{lightgreen} 3\degree\ Incrementing\ 1\ to\ the\ ASCII}$$

This encoding has an impediment when we encode the first character of the ASCII table (code 0), because its value is zero, there will be no sum extracted from the Fibonacci sequence, thus, in the encoding, we add 1 to the actual ASCII code of the symbol, and at the end of the decoding, we subtract that 1, in this way, we manage to encompass all the symbols of the table.

# $${\color{lightgreen} Contributors\ 🤝}$$

This project is open to contributions, if you want to contribute, just fork the repository, make your changes, and send a pull request. We will be happy to analyze your contribution and add it to the project 😁.

# $${\color{lightgreen} Founders}$$
1. <a href="https://www.linkedin.com/in/bruno-camboim3b6/" target="_blank">Bruno Camboim</a>
2. <a href="https://www.linkedin.com/in/bruno-pozzebon44/" target="_blank">Bruno Pozzebon</a>
3. <a href="https://www.linkedin.com/in/stzgustavo/" target="_blank">Gustavo Steinmetz</a>

