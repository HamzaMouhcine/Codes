import java.util.*;
import java.io.*;
import java.math.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(System.out);
        //Scanner sc = new Scanner();
        Reader in = new Reader();
        Main solver = new Main();
        solver.solve(out, in);
        out.flush();
        out.close();
 
    }
 
    static int INF = (int)1e9;
    static int maxn = (int)1e7+3000;
    static int mod= 998244353 ;
    static int n,m,k,t,q,d;
    static int[] b,x,y;
    static long[] dp,a;
    static Deque<line> dq;
    
    void solve(PrintWriter out, Reader in) throws IOException{
        n = in.nextInt();
        
        x = new int[n];
        y = new int[n];
        a = new long[n];
        Integer[] indices = new Integer[n];
		for (int i = 0; i < n; i++) {
			indices[i] = i;
			x[i] = in.nextInt();
			y[i] = in.nextInt();
			a[i] = in.nextLong();
		}
		Arrays.sort(indices, Comparator.comparingInt(i -> x[i]));
        
        dq = new LinkedList<line>();
        dp = new long[n];
        
        dq.addFirst(new line(0,0));
        
        for(int idx:indices){
            dp[idx] = query(y[idx],idx);
            insert(-x[idx],dp[idx]);
        }
        
        long ans=0;
        for(int i=0;i<n;i++) ans= Math.max(dp[i],ans);
        out.println(ans); 
        
    }
    
    //<>
    
    static void insert(long mx,long cx){
        line ne = new line(mx,cx);
        line li = new line(-1,-1);
        while(dq.size()>=2){
            li = dq.pollFirst();
            if(li.inter(ne)<li.inter(dq.peekFirst())){
                dq.addFirst(li);
                break;
            }
        }
        dq.addFirst(ne);
        return;
    }
    
    static long query(int s,int i){
        line li = new line(-1,-1);
        while(dq.size()>=2 ){
            li = dq.pollLast();
            if(li.eval(s)>dq.peekLast().eval(s)){
                dq.addLast(li);
                break;
            }
        }
        
        li = dq.peekLast();
        return li.eval(s)+(long)x[i]*(long)y[i]-a[i];
    }
    
    static class line{
        long m,c;
        
        line(long m,long c){
            this.m = m;
            this.c = c;
        }
        
        double inter(line l){
            return (double)(l.c-c)/(double)(m-l.m);
        }
        
        long eval(int x){
            return m*x+c;
        }
    }
    
    static class Reader {

    private InputStream mIs;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public Reader() {
        this(System.in);
    }

    public Reader(InputStream is) {
        mIs = is;
    }

    public int read() {
        if (numChars == -1) {
            throw new InputMismatchException();

    }
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = mIs.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0) {
                return -1;
            }
        }
        return buf[curChar++];
    }

    public String nextLine() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isEndOfLine(c));
        return res.toString();
    }

    public String next() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    double nextDouble()
    {
        return Double.parseDouble(next());
    }

    public long nextLong() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public int nextInt() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public boolean isEndOfLine(int c) {
        return c == '\n' || c == '\r' || c == -1;
    }

    }
}