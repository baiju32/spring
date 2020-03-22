package com.cy.java.thread;

/**
 * 线程安全问题
 * @author lll
 *
 */
public class TestThread02 {

	static class TicketTask implements Runnable{
		
		volatile int ticket=10;
		@Override
		public void run() {
			doTicket();
		}
		private void doTicket() {
			while (true) {
				synchronized(this) {
					if (ticket<=0) break;
					System.out.println(ticket--);
				}
			}
		}
	}

	public static void main(String[] args) {
		TicketTask ticketTask = new TicketTask();
		Thread t1 = new Thread(ticketTask);
		Thread t2 = new Thread(ticketTask);
		Thread t3 = new Thread(ticketTask);
		Thread t4 = new Thread(ticketTask);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}	
