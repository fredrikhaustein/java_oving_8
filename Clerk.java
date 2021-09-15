package patterns.delegation.office;

import java.util.function.BinaryOperator;

public class Clerk implements Employee{

	Printer printer = new Printer();
	int countTask;
	
	Clerk(Printer printer){
		this.printer = printer;
	}
	
	@Override
	public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
		double value = operation.apply(value1, value2);
		this.countTask += 1;
		System.out.println(value);
		return value;
	}

	@Override
	public void printDocument(String document) {
		printer.printDocument(document, this);
		this.countTask += 1;
		
		
	}

	@Override
	public int getTaskCount() {
		return this.countTask;
	}

	@Override
	public int getResourceCount() {
		return 1;
	}
	
	
	public static void main(String[] args) {
		Printer printer1 = new Printer();
		Clerk clerk = new Clerk(printer1);
		Clerk clerk1 = new Clerk(printer1);
		clerk.printDocument("Hallo hvordan går det");
		clerk1.printDocument("Halllllllo");
		clerk1.printDocument("Heiiiiii");
		System.out.println(printer1.getPrintHistory(clerk1));
		System.out.println(printer1.getPrintHistory(clerk));
	}

}
