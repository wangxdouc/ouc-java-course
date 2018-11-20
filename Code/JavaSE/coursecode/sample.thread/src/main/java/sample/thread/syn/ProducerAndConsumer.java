package sample.thread.syn;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 生产者 - 消费者 模式示例
 * 
 * @author xiaodong
 * @see https://blog.csdn.net/xindoo/article/details/80004003
 */
public class ProducerAndConsumer {
	private final int MAX_LEN = 10;
	// 队列数据结构
	private Queue<Integer> queue = new LinkedList<Integer>();

	/**
	 * 生产者线程 
	 */
	class Producer extends Thread {
		@Override
		public void run() {
			producer();
		}

		private void producer() {
			while (true) {
				synchronized (queue) {
					while (queue.size() == MAX_LEN) {
						queue.notify();
						System.out.println("当前队列满");
						try {
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					queue.add(1);
					queue.notify();
					System.out.println("生产者生产一条任务，当前队列长度为" + queue.size());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 消费者线程
	 */
	class Consumer extends Thread {
		@Override
		public void run() {
			consumer();
		}

		private void consumer() {
			while (true) {
				synchronized (queue) {
					while (queue.size() == 0) {
						queue.notify();
						System.out.println("当前队列为空");
						try {
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					queue.poll();
					queue.notify();
					System.out.println("消费者消费一条任务，当前队列长度为" + queue.size());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		ProducerAndConsumer pc = new ProducerAndConsumer();
		Producer producer = pc.new Producer(); // 创建一个生产者
		Consumer consumer = pc.new Consumer(); // 创建一个消费者
		producer.start();
		consumer.start();
	}
}