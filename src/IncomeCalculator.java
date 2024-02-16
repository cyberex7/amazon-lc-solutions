/*
* We want to implement an income calculator that,
given a compensation package and start date,
returns the income per year until the present.

The input is:

Start date (SimpleDate)
Base salary per year(int)
RSU per year (int)
Sign on bonus, only once (int)
SimpleDate is my own implementation of Date. It has these fields:

SimpleDate:
  month: int
  year: int
You can also use SimpleDate.now() to get the current date.

Example: Given these values

Base salary: 120,000

RSU: 60,000

Sign on: 25,000

Start date: 02/2018

Current date 02/2020

The calculator should return:

2018: 190,000 (11 months income, including February plus sign on)

2019: 180,000 (12 months income)

2020: 15,000 (only January, excluding February)
*
* */

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class IncomeCalculator {

    public static class SimpleDate {
        public int month;
        public int year;

        public SimpleDate(int month, int year) {
            this.month = month;
            this.year = year;
        }

        public static SimpleDate now() {
            Calendar today = Calendar.getInstance();
            return new SimpleDate(today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR));
        }
    }

    public static Map<Integer, Integer> incomeCalculator(SimpleDate start, int baseSalary, int rsu, int signOn) {
        SimpleDate current = SimpleDate.now();
        int startYear = start.year;
        Map<Integer, Integer> incomePerYear = new HashMap<Integer, Integer>();

        int salaryPerMonth = baseSalary / 12;
        int rsuPerMonth = rsu / 12;

        if (startYear == current.year) {
            int months = current.month - start.month;
            int firstYearIncome = salaryPerMonth * months + rsuPerMonth * months + signOn;
            incomePerYear.put(startYear, firstYearIncome);
        } else {
            int months = 12 - start.month + 1;
            int firstYearIncome = salaryPerMonth * months + rsuPerMonth * months + signOn;
            incomePerYear.put(startYear, firstYearIncome);

            for (int year = startYear + 1; year < current.year; year++) {
                int yearlyIncome = 12 * (salaryPerMonth + rsuPerMonth);
                incomePerYear.put(year, yearlyIncome);
            }

            if (current.year > startYear) {
                int lastYearIncome = (current.month - 1) * (salaryPerMonth + rsuPerMonth);
                incomePerYear.put(current.year, lastYearIncome);
            }
        }

        return incomePerYear;
    }

    public static void main(String[] args) {
        SimpleDate startDate = new SimpleDate(2, 2018);
        Map<Integer, Integer> income = incomeCalculator(startDate, 120000, 60000, 25000);
        for (Map.Entry<Integer, Integer> entry : income.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

