package algorithm.divide_and_conquer;

public class 분할정복_02_이진검색 {
    static int[] arr = { 2, 6, 19, 20, 55, 77, 100 };
    static int key = 2;

    public static void main(String[] args) {
        System.out.println(binarySearch2(0, arr.length - 1));
    }

    // 재귀
    static boolean binarySearch2(int left, int right) {
        if (left > right)
            return false;

        int mid = (left + right) / 2;

        if (arr[mid] == key)
            return true;
        else if (arr[mid] > key) {
            return binarySearch2(left, mid - 1);
        } else {
            return binarySearch2(mid + 1, right);
        }
    }
}
