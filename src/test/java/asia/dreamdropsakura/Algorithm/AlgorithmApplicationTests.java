package asia.dreamdropsakura.Algorithm;

import asia.dreamdropsakura.Algorithm.entity.ListNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

@SpringBootTest
public class AlgorithmApplicationTests {


    @Test
    void contextLoads() {
        // 剑指offer 05 替换空格
        String charStr = "We are win.";
        String s = replaceSpace(charStr);
        System.out.println(s);

        // 剑指offer 06 从尾到头打印链表 todo 23-09-12 有问题，暂未完成
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(2);
        listNode.setNext(listNode1);
        listNode1.setNext(listNode2);
        System.out.println("GetLatestNodeVal: ");
        getNextNode(listNode);
        System.out.println("  ");

        for (int i : reversePrint(listNode)) {
            System.out.print("" + i + ", ");

        }
    }

    public static int[] getNextNode(ListNode listNode) {
        if (listNode == null) return null;

        ArrayList<Integer> integers = new ArrayList<>();
        int[] newInts = null;
        ListNode listNode1 = null;
        if (listNode.getNext() != null) {
            int[] nextNode = getNextNode(listNode.getNext());
            // 末尾元素，会被第一个打印.
            newInts = new int[nextNode.length + 1];
            for (int i = 0; i < nextNode.length; i++) {
                newInts[i] = nextNode[i];
            }
            newInts[nextNode.length] = listNode.getVal();
        } else {
            listNode1 = listNode;
            integers.add(listNode1.getVal());
        }
        if (newInts != null) integers.addAll(new ArrayList<Integer>(Arrays.asList(newInts)));

        int[] returnReadyInts = integers.stream().mapToInt(new ToIntFunction<Integer>() {
            @Override
            public int applyAsInt(Integer value) {
                return value;
            }
        }).toArray();
        return returnReadyInts;
    }

    String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if (c == ' ') {
                sb.append("%20");
                continue;
            }
            sb.append(c);
        }

        return sb.toString();
    }

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return null;
        }

        // 值
        int val = head.getVal();
        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(val);

//        ArrayList<Integer> integers = new ArrayList<Integer>();
        if (head.getNext() != null) {
            // 此时调用后返回的是当前作用域下，下一个节点的值
            int[] ints = reversePrint(head.getNext());

            for (int i = 0; i < ints.length; i++) {
                intList.add(ints[i]);
            }
//            for (int i = ints.length; i > 0; i--) {
//                Collections.addAll(intList, ints[i - 1]);
//            }
        }

        int[] reversedInts = new int[intList.size()];
        int j = 0;
        for (int i = intList.size(); i > 0; i--) {
            reversedInts[j] = intList.get(i - 1);
            j++;
        }

        return reversedInts;
    }
}
