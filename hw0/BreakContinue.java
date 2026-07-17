public class BreakContinue {
    public static void windowPosSum(int[] a, int n) {
        /** your code here */
        for(int i=0;i<a.length;i++){
            if(a[i]<0){
                continue;
            }
            else if(i!=a.length-1){
                int sum = 0;
                if(i+n> a.length-1){
                    for(int j=i;j<=a.length-1;j++){
                        sum += a[j];
                    }
                    a[i] = sum;
                }
                else{
                    for (int j = i; j <= i + n; j++) {
                        sum += a[j];
                    }
                    a[i] = sum;
                }
            }
            else{
                break;
            }
        }
        for(int i=0;i<a.length;i++){
            if(i!=a.length-1){
                System.out.print(a[i]+",");
            }
            else{
                System.out.println(a[i]);
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);

        // Should print 4, 8, -3, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));
    }
}
