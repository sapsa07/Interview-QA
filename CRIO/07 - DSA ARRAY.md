# 01 - DSA ARRAY

## 01. Stock Buy Sell to Maximize Profit

The cost of a stock on each day is given in an array. Find the maximum profit that you can make by buying and selling on those days. If the given array of prices is sorted in decreasing order, then profit cannot be earned at all.

**Examples:**

> **Input:** arr[] = {100, 180, 260, 310, 40, 535, 695}
> **Output:** 865
> **Explanation:** Buy the stock on day 0 and sell it on day 3 => 310 – 100 = 210
>             Buy the stock on day 4 and sell it on day 6 => 695 – 40 = 655
>             Maximum Profit  = 210 + 655 = 865
>
> **Input:** arr[] = {4, 2, 2, 2, 4}
> **Output:** 2
> **Explanation:** Buy the stock on day 1 and sell it on day 4 => 4 – 2 = 2
>             Maximum Profit  = 2

We just need to find the next greater element and subtract it from the current element so that the difference keeps increasing until we reach a minimum. If the sequence is a decreasing sequence, so the maximum profit possible is 0.

Follow the steps below to solve the problem:

- maxProfit = 0
- if `price[i] > price[i – 1]` then `maxProfit = maxProfit + price[i] – price[i – 1]`

```java
import java.io.*;
import java.util.*;

public class BestTimeToBuyAndSellStocks {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] prices 		= new int[scanner.nextInt()];

        for (int i = 0; i < prices.length; i++)
            prices[i] = scanner.nextInt();
            
        scanner.close();

        int result = new BestTimeToBuyAndSellStocks().maxProfit(prices);
        System.out.print(Integer.toString(result));
    }
}
```

> **Time Complexity**: O(N) - Traversing over the array of size N.
> **Auxiliary** **Space:** O(1)

