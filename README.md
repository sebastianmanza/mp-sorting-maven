# mp-sorting-maven

An exploration of sorting in Java.

Authors

* Sebastian Manza
* Samuel A. Rebelsky (starter code)

Acknowledgements

* _Forthcoming_.

This code may be found at <https://github.com/sebastianmanza/mp-sorting-maven>. The original code may be found at <https://github.com/Grinnell-CSC207/mp-sorting-maven>.

Description of custom sorting algorithm
---------------------------------------
The sorting algorithm uses a combination of merge sort and insertion sort. It grabs 10 pairs of adjacent index's, and if at least 9 of them are in order, runs insertion sort. If the array is less than 50 elements, it also runs insertion sort. Otherwise, it splits it in half and calls itself on both halves.

Notes on using Copilot (or other AI)
------------------------------------
I used ChatGPT for ManzaSebSorter and I thought it was pretty interesting. I had to be very specific with what I prompted it, and even then I had to correct it on a lot of different things. This was an algorithm (the picking adjacent index's) that I thought of myself, and i asked chatGPT to optimize it. It was very fast with its answer, but I am not sure that it came up with the perfectly fastest answer. Some of the numbers seemed arbitrary, and I asked it to modify them (for instance it wanted 7 index's at least correct, but to me that seemed like a bad idea if the array was very large.) It also couldn't make up its mind whether it wanted to use merge sort or quick sort for larger algorithms, and tried to offer both. I think it could improve speed, but unless you are clear with what you want it to do, could get you in strange situations.
