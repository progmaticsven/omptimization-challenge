# Find duplicates
Goal is to find duplicates in input file and write found duplicates to output file.

Create command line tool that takes two filenames as attributes:
```
duplicates input.txt output.txt
```

## How to measure

```
/usr/bin/time -vp java

Command exited with non-zero status 1
        Command being timed: "java"
        User time (seconds): 0.07
        System time (seconds): 0.06
        Percent of CPU this job got: 98%
        Elapsed (wall clock) time (h:mm:ss or m:ss): 0:00.14
        Average shared text size (kbytes): 0
        Average unshared data size (kbytes): 0
        Average stack size (kbytes): 0
        Average total size (kbytes): 0
        Maximum resident set size (kbytes): 30216
        Average resident set size (kbytes): 0
        Major (requiring I/O) page faults: 0
        Minor (reclaiming a frame) page faults: 7977
        Voluntary context switches: 0
        Involuntary context switches: 0
        Swaps: 0
        File system inputs: 0
        File system outputs: 0
        Socket messages sent: 0
        Socket messages received: 0
        Signals delivered: 0
        Page size (bytes): 4096
        Exit status: 1
```

## Requirements
* Every duplicate is only once in output
* Must work with very large input files up to 5 GB in size
* String max length is 255 bytes, containing only ASCII characters
* 0.1% of false duplicates in output is acceptable! 
* Must run on Ubuntu with 16 GB of RAM and 4 CPUs

## What to optimize
* Minimum execution time
* Minimum peak memory usage

## Example 1 
### Input
```
foo
bar
baz
bar
```

### Output
```
bar
```

## Example 2 

### Input
```
foo
baz
bar
```

## Example 3
### Input
```
foo
baz
bar
foo
foo
baz
``` 
### Output
```
foo
baz
``` 
