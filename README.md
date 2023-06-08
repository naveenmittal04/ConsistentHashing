# Consistent Hashing

## Problem Description
We have a hash ring where we have locations available from 0 degrees to 359 degrees. Implement a consistent hashing in the following way : You can add a server to the hash ring. We just need to pass the serverID and the hash functions automatically assign them one location on the hash ring. The serverID can be strings.

To add any server you give input as ADD servername . This will add that server to your hash ring. You also have the option to remove an added server which can be done by giving the input as REMOVE servername . This will remove the server from the hash ring. Lastly, to the servers added on the ring, you can assign incoming requests based on keys.

So, when you do ASSIGN keyname, you basically want to assign this request to one of the servers nearest to the name hash location in clockwise direction ( If no server found in clockwise direction, pick the nearest server from 0 degrees in clockwise direction or if there is more than one nearest server available then assign this request to the latest server added to that location). It has been guaranteed that all the key names and server names would be unique and at least one server exists for ASSIGN type requests.

You are given two string vectors A and B and an integer array C. For all valid i, A[i] tells you the type of operation of i-th query and B[i] tells you the key/server name depending on the type of query and C[i] tells you the hashKey for the i-th operation. A[i] can only take 3 values - "ADD", "REMOVE", "ASSIGN".

For "ADD" queries, B[i] is an uppercase string with 5 or more letters. They are all unique among add queries.
For "REMOVE" queries, B[i] is an uppercase string with 5 or more letters. They are all unique among remove queries.
For "ASSIGN" queries, B[i] is an uppercase string with exactly 4 letters. They are all unique among all queries.

You need to return an integer array. Let's call it ans. The value ans[i] should correspond to the output for the i-th query. The rules for the output are:
For "ADD" queries, ans[i] should be equal to the number of keys that got reassigned to the server added in the i-th query.
For "REMOVE" queries, ans[i] should be equal to the number of keys that assigned to the server getting removed (before removal).
For "ASSIGN" queries, ans[i] should be equal to the hash location of this request.

It is guaranteed that there will not be any removals of servers that are not already there. It is also guaranteed that number of queries of types "ADD" / "REMOVE" do not exceed 11 each.

### Note:
While removing a server, all the keys assigned to that particular server will get reassigned according to the "ASSIGN" functionality.
If there are more than one server at a specific location, then consider the latest added server to that location to serve the upcoming requests that needs to be assigned to a server at that location.
You are required to use the following hash function to assign degrees to servers/keys:

<code>
int userHash(string username, int hashKey){<br>
const int p = hashKey;<br>
const int n = 360;<br>
int hashCode = 0;<br>
long long p_pow = 1;<br>
for (int i = 0; i &amp;amp;lt; username.length(); i++) {<br>
char character = username[i];<br>
hashCode = (hashCode + (character - 'A' + 1) * p_pow) % n;<br>
p_pow = (p_pow * p) % n;<br>
}<br>
return hashCode;<br>
}<br>
</code>


### Problem Constraints
1 <= A.size() <= 104 + 30<br>
1 <= B.size() <= 104 + 30<br>
1 <= C.size() <= 104 + 30<br>
A.size() = B.size() = C.size()<br>



### Input Format
First argument is the string array A. Second argument is string array B. Third argument is integer array C.


### Output Format
Return an integer array with the corresponding values for each query according to the problem statement.


### Example Input
### Input 1:
A = [ADD, ASSIGN, ADD, ASSIGN, REMOVE, ASSIGN] <br>
B = [INDIA, NWFJ, RUSSIA, OYVL, INDIA, IGAX]<br>
C = [7, 3, 5, 13, 23, 17 ]<br>
### Input 2:

A = [ADD, ASSIGN, ASSIGN, ADD, ASSIGN, ASSIGN, REMOVE, ASSIGN]<br>
B = [INDIA, IRYA, RGJK, RUSSIA, BGVH, SUKJ, INDIA, RBRF]<br>
C = [11, 31, 7, 3, 5, 13, 23, 17]<br>


### Example Output
### Output 1:
0 47 1 182 0 249
### Output 2:
0 23 10 0 147 1 4 172


### Example Explanation
### Explanation 1:
"INDIA" gets mapped to hash value 31<br>
"RUSSIA" gets mapped to hash value 203<br>
For the 1st query, there are no keys at all so 0 keys get reassigned.<br>
For the 2nd query, string gets hash value of 47 and gets assigned to "INDIA"<br>
For the 3rd query, "RUSSIA" gets added and 1 key get reassigned to "RUSSIA"<br>
For the 4th query, string gets hash value of 182 and gets assigned to "RUSSIA"<br>
For the 5th query, "INDIA" is removed and 0 keys need to be reassigned.<br>
For the 6th query, string gets hash value of 249 and gets assigned to "RUSSIA"<br>

### Explanation 1:
"INDIA" gets mapped to hash value 267<br>
"RUSSIA" gets mapped to hash value 297<br>
For the 1st query, there are no keys at all so 0 keys get reassigned.<br>
For the 2nd query, string gets hash value of 23 and gets assigned to "INDIA"<br>
For the 3rd query, string gets hash value of 10 and gets assigned to "INDIA"<br>
For the 4th query, "RUSSIA" gets added and 0 key get reassigned to "RUSSIA"<br>
For the 5th query, string gets hash value of 147 and gets assigned to "INDIA"<br>
For the 6th query, string gets hash value of 1 and gets assigned to "INDIA"<br>
For the 7th query, "INDIA" is removed and 4 keys need to be reassigned.<br>
For the 8th query, string gets hash value of 172 and gets assigned to "RUSSIA"<br>