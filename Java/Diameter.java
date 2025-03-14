import java.util.*;
public class Diameter
{
    public static class Node
    {
        int data;
        Node left;
        Node right;
        Node(int data, Node left, Node right)
        {
            this.data=data;
            this.left=left;
            this.right= right;
        }
    }
    public static class Pair
    {
        Node node;
        int state;
        Pair(Node node, int state)
        {
            this.node = node;
            this.state = state;
        }
    }
    public static Node construct(int[] Ar)
    {
        Node root = new Node(Ar[0], null,null);
        Pair rtp = new Pair(root,1);
        Stack<Pair> st = new Stack<>();
        st.push(rtp);
        int ind=0;
        while(st.size()>0)
        {
            Pair top = st.peek();
            if(top.state==1)
            {
                ind++;
                if(Ar[ind]!=-1)
                {
                    Node ln = new Node(Ar[ind],null,null);
                    top.node.left = ln;
                    Pair lp = new Pair(ln,1);
                    st.push(lp);
                }
                else
                {
                    top.node.left=null;
                }
                top.state++;
            }
            else if(top.state==2)
            {
                ind++;
                if(Ar[ind]!=-1)
                {
                    Node rn = new Node(Ar[ind],null,null);
                    top.node.right = rn;
                    Pair rp = new Pair(rn,1);
                    st.push(rp);
                }
                else
                {
                    top.node.right=null;
                }
                top.state++;
            }
            else
            {
                st.pop();
            }
        }
        return root;
    }
    public static class DiaPair
    {
        int dia;
        int ht;
    }
    public static DiaPair diameter(Node node)
    {
        if(node==null)
        {
            DiaPair bp = new DiaPair();
            bp.dia = 0;
            bp.ht=-1;
            return bp;
        }
        DiaPair lp = diameter(node.left);
        DiaPair rp = diameter(node.right);
        
        DiaPair mp = new DiaPair();
        mp.ht = Math.max(lp.ht, rp.ht)+1;
        
        int fes = lp.ht+rp.ht+2;
        
        mp.dia = Math.max(fes,Math.max(lp.dia, rp.dia));
        return mp;
        
    }

    public static void main(String Args[])
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] Ar = new int[n];
        for(int i=0;i<n;i++)
        {
            Ar[i] =sc.nextInt();
        }
        Node root = construct(Ar);
        DiaPair p = diameter(root);
        System.out.println(p.dia);
    }
}