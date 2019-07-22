package com.app.navneet.java8;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MergeAvergaeHashMap {
	
	public static class StatisticsAggregatorImpl implements StatisticsAggregator {
		Map<String, Double> symblePrice = new ConcurrentHashMap<String, Double>();
		Map<String, Integer> symbleFreq = new ConcurrentHashMap<String, Integer>();

		@Override
		public synchronized void putNewPrice(String symbol, double price) {
			symblePrice.merge(symbol, price, Double::sum);
			symbleFreq.merge(symbol, 1, (a,b)->a+b);
			// YOUR CODE HERE
		}

		@Override
		public synchronized  double getAveragePrice(String symbol) {

			return symblePrice.get(symbol) / symbleFreq.get(symbol);
			// YOUR CODE HERE
		}

		@Override
		public synchronized int getTickCount(String symbol) {
			return symbleFreq.get(symbol);
			// YOUR CODE HERE
		}
	}

	////////////////// DO NOT MODIFY BELOW THIS LINE ///////////////////

	public interface StatisticsAggregator {

		// This is an input. Make note of this price.
		public void putNewPrice(String symbol, double price);

		// Get the average price
		public double getAveragePrice(String symbol);

		// Get the total number of prices recorded
		public int getTickCount(String symbol);
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner("1,IBM 22.22,AAPL 33.33,IBM 44.44,AAPL 55.55,IBM 66.66");
		while (scanner.hasNext()) {
			final StatisticsAggregator stats = new StatisticsAggregatorImpl();
			final Set<String> symbols = new TreeSet<>();

			String line = scanner.nextLine();
			String[] inputs = line.split(",");
			int threads = Integer.parseInt(inputs[0]);

			ExecutorService pool = Executors.newFixedThreadPool(threads);

			for (int i = 1; i < inputs.length; ++i) {
				String[] tokens = inputs[i].split(" ");
				final String symbol = tokens[0];
				symbols.add(symbol);
				final double price = Double.parseDouble(tokens[1]);
				pool.submit(new Runnable() {
					@Override
					public void run() {
						stats.putNewPrice(symbol, price);
					}
				});

			}
			pool.shutdown();
			try {
				pool.awaitTermination(5000, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (String symbol : symbols) {
				System.out.println(
						String.format("%s %.4f %d", symbol, stats.getAveragePrice(symbol), stats.getTickCount(symbol)));
			}
		}

		scanner.close();
	}
}