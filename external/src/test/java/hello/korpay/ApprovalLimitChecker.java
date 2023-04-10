package hello.korpay;

import org.junit.jupiter.api.Test;

import java.util.*;


public class ApprovalLimitChecker {

	@Test
	public static void main(String[] args) {
		List<Map<String, String>> transactionData = Arrays.asList(
				new HashMap<String, String>() {{
					put("idx", "1");
					put("app_dt", "20230117");
					put("amount", "10000");
				}},
				new HashMap<String, String>() {{
					put("idx", "2");
					put("app_dt", "20230117");
					put("amount", "2000");
				}},
				// ... 이하 생략
				new HashMap<String, String>() {{
					put("idx", "20");
					put("app_dt", "20230412");
					put("amount", "22000");
				}}
		);

		List<Map<String, String>> failedTransactions = findFailedTransactions(transactionData);
		System.out.println(failedTransactions);
	}

	public static List<Map<String, String>> findFailedTransactions(List<Map<String, String>> transactionData) {
		int singleApprovalLimit  = 30000;
		int dailyApprovalLimit   = 50000;
		int monthlyApprovalLimit = 100000;

		Map<String, Integer>      dailySum           = new HashMap<>();
		Map<String, Integer>      monthlySum         = new HashMap<>();
		List<Map<String, String>> failedTransactions = new ArrayList<>();

		for (Map<String, String> transaction : transactionData) {
			String idx    = transaction.get("idx");
			String app_dt = transaction.get("app_dt");
			int    amount = Integer.parseInt(transaction.get("amount"));

			String day   = app_dt.substring(0, 8);
			String month = app_dt.substring(0, 6);

			dailySum.put(day, dailySum.getOrDefault(day, 0) + amount);
			monthlySum.put(month, monthlySum.getOrDefault(month, 0) + amount);

			if (amount > singleApprovalLimit) {
				failedTransactions.add(new HashMap<String, String>() {{
					put(idx, "1회 승인한도 초과");
				}});
			} else if (dailySum.get(day) > dailyApprovalLimit) {
				failedTransactions.add(new HashMap<String, String>() {{
					put(idx, "1일 승인한도 초과");
				}});
			} else if (monthlySum.get(month) > monthlyApprovalLimit) {
				failedTransactions.add(new HashMap<String, String>() {{
					put(idx, "1개월 승인한도 초과");
				}});
			}
		}

		return failedTransactions;
	}
}