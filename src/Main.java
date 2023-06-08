import java.util.*;
public class Main {
    boolean debug = true;
    Ring ring;

    public Main(){
        this.ring = new Ring();
        ring.setDebug(this.debug);
    }

    public static void main(String[] args) {

        System.out.println("Hello world!");

        Main main = new Main();

        String[] A = {"ADD"  ,"ASSIGN","ASSIGN","ASSIGN","ADD"   ,"ASSIGN","ASSIGN","ASSIGN","ADD"  ,"ASSIGN","ASSIGN","ASSIGN","ADD"    ,"ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN"};
        String[] B = {"INDIA","GYQF","SSAH","DVTQ","RUSSIA","ZIVQ","VBWW","ACDW","CHINA","YNXC","MWUN","NECZ","GERMANY","OOHQ","RSTZ","WRJJ","INDIA","YLDR","XDFH","SCCV","RUSSIA","QECH","WPCA","ZLVQ","CHINA","RQPJ","PFWJ"};
        int[] C = {947,613,821,701,193,683,19,467,503,347,433,887,971,587,509,283,727,359,443,883,499,487,853,223,137,13,739};
        int[] expectedAns = {0, 307, 107, 3, 1, 310, 160, 267, 0, 188, 359, 34, 0, 303, 263, 157, 8, 359, 66, 209, 2, 303, 175, 179, 3, 73, 343};
        int[] ans = main.solve(A, B, C);
        System.out.println("Expected: "+ Arrays.toString(expectedAns));
        System.out.println("Actual: "+ Arrays.toString(ans));
        if(Arrays.equals(expectedAns, ans))
            System.out.println("Test Passed!");
        else
            System.out.println("Test Failed!");
    }

    public int ADD(String serverName, int hashKey){
        int degree = userHash(serverName, hashKey);
        return ring.addServer(serverName, degree);
    }

    public int REMOVE(String serverName){
        return ring.removeServer(serverName);
    }

    public int ASSIGN(String requestName, int hashKey){
        int degree = userHash(requestName, hashKey);
        Request request = new Request(requestName, degree);
        Server server = ring.findServerForRequest(request);
        request.setAssignedServer(server);
        ring.addRequest(request);

        if(debug)
            System.out.println("ASSIGN "+ requestName+" "+ degree +" to "+ server);

        return degree;
    }

    public int[] solve(String[] A, String[] B, int[] C) {
        int size = A.length;
        int[] ans = new int[size];

        for(int i = 0; i < size; i++){
            if(A[i].equals("ADD")){
                ans[i] = ADD(B[i], C[i]);
            } else if(A[i].equals("REMOVE")){
                ans[i] = REMOVE(B[i]);
            } else if(A[i].equals("ASSIGN")) {
                if(ring.isEmpty())
                    continue;
                ans[i] = ASSIGN(B[i], C[i]);
            }
        }
        return ans;
    }

    public int userHash(String username, int hashKey){
        int p = hashKey;
        int n = 360;
        long hashCode = 0;
        long p_pow = 1;
        for (int i = 0; i < username.length(); i++) {
            char character = username.charAt(i);
            hashCode = (hashCode + (character - 'A' + 1) * p_pow) % n;
            p_pow = (p_pow * p) % n;
        }
        return (int)hashCode;
    }
}

/*
Your submission failed for the following input
Arg 1: A String Array, For e.g ['hello','world']
["ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN"]
Arg 2: A String Array, For e.g ['hello','world']
["INDIA","FVAN","TQRF","DVNP","RUSSIA","HQMW","VSDU","AEYP","CHINA","YSJO","QCWT","KLTQ","GERMANY","RYON","YHCW","OPOS","INDIA","JKIV","XZNK","HPGG","RUSSIA","IEHO","BRUZ","QQSL","CHINA","QMET","HAKQ"]
Arg 3: An Integer Array, For e.g [1,2,3]
[733,11,37,643,499,197,827,599,797,293,947,719,983,367,47,401,577,409,443,457,347,5,827,971,733,79,263]
Test As Custom Input
The expected return value:
0 283 169 188 3 53 274 5 4 157 5 2 5 330 237 145 1 76 85 254 4 309 195 115 4 229 289
Your function returned the following:
0 283 169 188 3 53 274 5 4 157 5 2 4 330 237 145 1 76 85 254 4 309 195 115 4 229 289

["ADD"  ,"ASSIGN","ASSIGN","ASSIGN","ADD"   ,"ASSIGN","ASSIGN","ASSIGN","ADD"  ,"ASSIGN","ASSIGN","ASSIGN","ADD"    ,"ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN"]
["INDIA","FVAN"  ,"TQRF"  ,"DVNP"  ,"RUSSIA","HQMW"  ,"VSDU"  ,"AEYP"  ,"CHINA","YSJO"  ,"QCWT"  ,"KLTQ"  ,"GERMANY","RYON","YHCW","OPOS","INDIA","JKIV","XZNK","HPGG","RUSSIA","IEHO","BRUZ","QQSL","CHINA","QMET","HAKQ"]
[733    ,11      ,37      ,643     ,499     ,197     ,827     ,599     ,797    ,293     ,947     ,719     ,983      ,367,47,401,577,409,443,457,347,5,827,971,733,79,263]
[0      ,283     ,169     ,188     ,3       ,53      ,274     ,5       ,4      ,157     ,5       ,2       ,5        ,330 237 145 1 76 85 254 4 309 195 115 4 229 289


*/

/*
Your submission failed for the following input
Arg 1: A String Array, For e.g ['hello','world']
["ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN"]
Arg 2: A String Array, For e.g ['hello','world']
["INDIA","VLVL","OXXV","HHGN","RUSSIA","AWNF","SPHI","FXKT","CHINA","JXZU","BWPK","JYWN","GERMANY","ZKYK","HLQZ","BRMS","INDIA","FMVA","NPJO","GACA","RUSSIA","ZMWM","XVUA","IDUW","CHINA","EHWW","KROX"]
Arg 3: An Integer Array, For e.g [1,2,3]
[431,563,223,761,197,409,31,223,769,619,991,613,139,797,547,821,859,131,577,269,2,499,599,29,449,13,337]
Test As Custom Input
The expected return value:
0 260 337 317 3 116 202 157 0 51 232 180 0 281 123 152 6 62 271 188 4 63 262 213 11 167 284
0 260 337 317 3 116 202 157 0 51 232 180 0 281 123 152 6 62 271 188 4 63 262 213 11 167 284
Your function returned the following:
0 260 337 317 3 116 202 157 0 51 232 180 4 281 123 152 6 62 271 188 0 63 262 213 11 167 284


["ADD"  ,"ASSIGN","ASSIGN","ASSIGN","ADD"   ,"ASSIGN","ASSIGN","ASSIGN","ADD"  ,"ASSIGN","ASSIGN","ASSIGN","ADD"    ,"ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN"]
["INDIA","VLVL"  ,"OXXV"  ,"HHGN"  ,"RUSSIA","AWNF"  ,"SPHI"  ,"FXKT"  ,"CHINA","JXZU"  ,"BWPK"  ,"JYWN"  ,"GERMANY","ZKYK"  ,"HLQZ"  ,"BRMS"  ,"INDIA" ,"FMVA"  ,"NPJO"  ,"GACA"  ,"RUSSIA","ZMWM","XVUA","IDUW","CHINA","EHWW","KROX"]
[431    ,563     ,223     ,761     ,197     ,409     ,31      ,223     ,769    ,619     ,991     ,613     ,139      ,797     ,547     ,821     ,859     ,131     ,577     ,269     ,2       ,499,599,29,449,13,337]
[0      ,260     ,337     ,317     ,3       ,116     ,202     ,157     ,0      ,51      ,232     ,180     ,4        ,281     ,123     ,152     ,6       ,62      ,271     ,188     ,0       ,63 ,262 ,213 ,11 ,167 ,284]

*/

/*
your input:
["ADD","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","REMOVE","ASSIGN"]
["INDIA","GSZJ","ORWX","RUSSIA","IENS","TTXU","INDIA","CHEX"]
[211,181,919,383,571,127,977,97]
The expected return value:
0 242 116 1 47 139 2 256
Your function returned the following:
ADD INDIA 187
ASSIGN GSZJ 242 to INDIA 187
ASSIGN ORWX 116 to INDIA 187
ADD RUSSIA 77
ASSIGN IENS 47 to RUSSIA 77
ASSIGN TTXU 139 to INDIA 187
REMOVE INDIA 187
ASSIGN CHEX 256 to RUSSIA 77
0 242 116 1 47 139 2 256
*/

/*
Your submission failed for the following input
Arg 1: A String Array, For e.g ['hello','world']
["ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN"]
Arg 2: A String Array, For e.g ['hello','world']
["INDIA","GYQF","SSAH","DVTQ","RUSSIA","ZIVQ","VBWW","ACDW","CHINA","YNXC","MWUN","NECZ","GERMANY","OOHQ","RSTZ","WRJJ","INDIA","YLDR","XDFH","SCCV","RUSSIA","QECH","WPCA","ZLVQ","CHINA","RQPJ","PFWJ"]
Arg 3: An Integer Array, For e.g [1,2,3]
[947,613,821,701,193,683,19,467,503,347,433,887,971,587,509,283,727,359,443,883,499,487,853,223,137,13,739]
Test As Custom Input
The expected return value:
0 307 107 3 1 310 160 267 0 188 359 34 0 303 263 157 8 359 66 209 2 303 175 179 3 73 343
Your function returned the following:
0 307 107 3 1 310 160 267 0 188 359 34 2 303 263 157 8 359 66 209 0 303 175 179 3 73 343
["ADD"  ,"ASSIGN","ASSIGN","ASSIGN","ADD"   ,"ASSIGN","ASSIGN","ASSIGN","ADD"  ,"ASSIGN","ASSIGN","ASSIGN","ADD"    ,"ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN"]
["INDIA","GYQF"  ,"SSAH"  ,"DVTQ"  ,"RUSSIA","ZIVQ"  ,"VBWW"  ,"ACDW"  ,"CHINA","YNXC"  ,"MWUN"  ,"NECZ"  ,"GERMANY","OOHQ","RSTZ","WRJJ","INDIA","YLDR","XDFH","SCCV","RUSSIA","QECH","WPCA","ZLVQ","CHINA","RQPJ","PFWJ"]
[947   ,613      ,821     ,701     ,193     ,683     ,19      ,467     ,503    ,347     ,433     ,887     ,971      ,587,509,283,727,359,443,883,499,487,853,223,137,13,739]
[0     ,307      ,107     ,3       ,1       ,310     ,160     ,267     ,0      ,188     ,359     ,34      ,2        ,303, 263 157 8 359 66 209 0 303 175 179 3 73 343


*/

/*
Your submission failed for the following input
Arg 1: A String Array, For e.g ['hello','world']
["ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN"]
Arg 2: A String Array, For e.g ['hello','world']
["INDIA","GYQF","SSAH","DVTQ","RUSSIA","ZIVQ","VBWW","ACDW","CHINA","YNXC","MWUN","NECZ","GERMANY","OOHQ","RSTZ","WRJJ","INDIA","YLDR","XDFH","SCCV","RUSSIA","QECH","WPCA","ZLVQ","CHINA","RQPJ","PFWJ"]
Arg 3: An Integer Array, For e.g [1,2,3]
[947,613,821,701,193,683,19,467,503,347,433,887,971,587,509,283,727,359,443,883,499,487,853,223,137,13,739]
Test As Custom Input
The expected return value:
0 307 107 3 1 310 160 267 0 188 359 34 0 303 263 157 8 359 66 209 2 303 175 179 3 73 343
Your function returned the following:
0 307 107 3 1 310 160 267 0 188 359 34 2 303 263 157 8 359 66 209 0 303 175 179 3 73 343

["ADD"  ,"ASSIGN","ASSIGN","ASSIGN","ADD"   ,"ASSIGN","ASSIGN","ASSIGN","ADD"  ,"ASSIGN","ASSIGN","ASSIGN","ADD"    ,"ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN"]
["INDIA","GYQF"  ,"SSAH"  ,"DVTQ"  ,"RUSSIA","ZIVQ"  ,"VBWW"  ,"ACDW"  ,"CHINA","YNXC"  ,"MWUN"  ,"NECZ"  ,"GERMANY","OOHQ","RSTZ","WRJJ","INDIA","YLDR","XDFH","SCCV","RUSSIA","QECH","WPCA","ZLVQ","CHINA","RQPJ","PFWJ"]
[0      ,307     ,107     ,3       ,1       ,310     ,160     ,267     ,0      ,188     ,359     ,34      ,2        ,303 263 157 8 359 66 209 0 303 175 179 3 73 343
ADD INDIA 51
ASSIGN GYQF 307 to INDIA 51
ASSIGN SSAH 107 to INDIA 51
ASSIGN DVTQ 3 to INDIA 51
ADD RUSSIA 207
SSAH 107 Reassigned from INDIA 51 to RUSSIA 207
ASSIGN ZIVQ 310 to INDIA 51
ASSIGN VBWW 160 to RUSSIA 207
ASSIGN ACDW 267 to INDIA 51
ADD CHINA 207
ASSIGN YNXC 188 to CHINA 207
ASSIGN MWUN 359 to INDIA 51
ASSIGN NECZ 34 to INDIA 51
ADD GERMANY 163
SSAH 107 Reassigned from RUSSIA 207 to GERMANY 163
VBWW 160 Reassigned from RUSSIA 207 to GERMANY 163
ASSIGN OOHQ 303 to INDIA 51
ASSIGN RSTZ 263 to INDIA 51
ASSIGN WRJJ 157 to GERMANY 163
REMOVE INDIA 51
GYQF 307 Reassigned from INDIA 51 to GERMANY 163
DVTQ 3 Reassigned from INDIA 51 to GERMANY 163
ZIVQ 310 Reassigned from INDIA 51 to GERMANY 163
ACDW 267 Reassigned from INDIA 51 to GERMANY 163
MWUN 359 Reassigned from INDIA 51 to GERMANY 163
NECZ 34 Reassigned from INDIA 51 to GERMANY 163
OOHQ 303 Reassigned from INDIA 51 to GERMANY 163
RSTZ 263 Reassigned from INDIA 51 to GERMANY 163
ASSIGN YLDR 359 to GERMANY 163
*/