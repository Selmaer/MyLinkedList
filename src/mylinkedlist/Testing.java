
package mylinkedlist;

import java.util.Arrays;


public class   Testing {
    public static void main(String[] args) throws Exception {
        MyLinkedList list = new MyLinkedList();
        list.add(3);
        list.add("nobody");
        list.add(22);
//       
//        System.out.println(list.toString());
//        list.addFirst("oklahoma");
//        
//        System.out.println(list.toString());
//        
//        list.add("hello there", 1);
//        System.out.println("added 'hello there' on index 1");
//        
//        
//        System.out.println(list.toString());
//        System.out.println(list.removeFirst());
//        System.out.println(list.removeLast());
//        
//        System.out.println(list.toString());

        Object list2 = (Object)list;

        int[] a = {5, 4, 7};
        int[] b = {2, 7, 9};
        int[] c = {1, 3, 6};
        
        int[][] Z = {a, b, c};
        
        String s = Arrays.deepToString(Z);
        System.out.println(s);
        
        print(a);
        print(3, 6, 7);
        
    }
    public static void print(int... numbers) {
        for (int a : numbers) {
            System.out.print(a + " ");
        }
        
        byte[] a = new byte[1];
        a[0] = 3;
        System.out.println();
        System.out.println(a.getClass().getName());
        System.out.println(a.getClass());
    }
}
