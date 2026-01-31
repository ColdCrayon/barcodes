# Barcodes

## About

_(TODO: insert a description of the project here!)_

### Authors

*   _(TODO: insert your names here!)_
*  ...

### Resources

*   _(TODO: list your resources here!)_
*   ...

## Write-up

### Part 2.1: Old Hat, New Hat

Part 1

1  Getting a Chracter by Index c : str[i] 
   String length: c : strlen(s) 
   Comparing Strings: c : strcmp(s1, s2) == 0 
   Substring: c : char *strncpy(char *dest, const char *src, size_t n);

2  Chracter by Index: Java : public char charAt(int index)
   String Length: Java : public int length()
   Comparing Strings: Java: public boolean equals(Object anObject)
   Substring: Java: public String substring(int beginIndex, int endIndex)

### Part 2.2: Iceberg, Right Head!

1 A returns True but B and C return false

2 When you replace == with s1.equals(s2) etc, all three results become true. We don't think it matters becuase for example s1.equals(s3) is the same as s3.equals(s1). 
To compare two strings for equality, we must use the .equals() method rather than the == operator.

3 In Java, == compares the memory addresses, not the contents of the objects. In examples B and C, we created new String objects. Even though they contain the same characters, they are stored in different locations in memory.

4 Since both variables represent the same fixed value, Java points both s1 and s2 point to the same memory reference in the pool.