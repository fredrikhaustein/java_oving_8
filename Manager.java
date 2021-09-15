package patterns.delegation.office;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BinaryOperator;

public class Manager implements Employee{

	private int count;
	Printer printer = new Printer();
	Clerk clerk = new Clerk(printer);
	private int resourceCount = 1;
	private Collection<Employee> workEmployees = new ArrayList<>();

	Manager(Collection<Employee> employees){
		if (employees.isEmpty()) {
			throw new IllegalArgumentException("Kan ikke være tom");
		}
		else {
			this.workEmployees = employees;
		}
	}

	
	private Employee getEmployee() {
		return workEmployees.stream().min((e1,e2) -> e1.getTaskCount()-e1.getTaskCount())
				.get();
	}

	@Override
	public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
		count++;
		Employee employee = getEmployee();
		return employee.doCalculations(operation, value1, value2);
	}

	@Override
	public void printDocument(String document) {
		Employee employee = getEmployee();
		count++;
		employee.printDocument(document);
	}

	@Override
	public int getTaskCount() {
		return this.count;
	}

	@Override
	public int getResourceCount() {
		for (Employee employee : workEmployees) {
			resourceCount += employee.getResourceCount();
		}
		return resourceCount;
	}


	public static void main(String[] args) {
		final BinaryOperator<Double> function = new BinaryOperator<Double>() {
			public Double apply(final Double x, final Double y) {
				return Double.valueOf((x + y));
			}
		};
		final BinaryOperator<Double> function1 = new BinaryOperator<Double>() {
			public Double apply(final Double x, final Double y) {
				return Double.valueOf((x * y));
			}
		};
		final BinaryOperator<Double> function2 = new BinaryOperator<Double>() {
			public Double apply(final Double x, final Double y) {
				return Double.valueOf((x / y));
			}
		};

		Printer printer = new Printer();
		
		Clerk clerk = new Clerk(printer);
		Clerk clerk1 = new Clerk(printer); 
		Clerk clerk2= new Clerk(printer);
		Clerk clerk3 = new Clerk(printer);
		
		Collection<Employee> workers = new ArrayList<>();
		
		
		workers.add(clerk);
		workers.add(clerk1);
		workers.add(clerk2);
		workers.add(clerk3);
		
		
		Manager manager = new Manager(workers);
		
		manager.printDocument("a");
		manager.printDocument("b");
		manager.printDocument("c");
		manager.printDocument("d");
		manager.printDocument("e");
		manager.printDocument("f");
		manager.printDocument("g");
		manager.printDocument("h");
		manager.printDocument("i");
		
		
		
		manager.doCalculations(function, 3, 5);
		manager.doCalculations(function1, 3, 5);
		manager.doCalculations(function2, 10,5);
		
		System.out.println("Vanlig manager "+manager.getResourceCount());
		System.out.println("Vanlig manager "+manager.getTaskCount());
		
		System.out.println("Uten mellomledere: " + Double.valueOf(manager.getTaskCount() / Double.valueOf(manager.getResourceCount())));
		
		Printer printer1 = new Printer();
		
		Clerk clerk4 = new Clerk(printer1);
		Clerk clerk5 = new Clerk(printer1);
		Clerk clerk6 = new Clerk(printer1);
		Clerk clerk7 = new Clerk(printer1);
		
		Collection<Employee> workers1 = new ArrayList<>();
		
		workers1.add(clerk4);
		workers1.add(clerk5);
		workers1.add(clerk6);
		workers1.add(clerk7);
		
		
		Collection<Employee> managers = new ArrayList<>();
		
		Manager manager1 = new Manager(workers);
		
		managers.add(manager);
		managers.add(manager1);
		
		
		Manager superManager = new Manager(managers);
		
		superManager.printDocument("f");
		superManager.printDocument("g");
		superManager.printDocument("h");
		superManager.printDocument("i");
		superManager.printDocument("j");
		superManager.printDocument("k");
		superManager.printDocument("l");
		superManager.printDocument("m");
		
		
		superManager.doCalculations(function, 3, 5);
		superManager.doCalculations(function1, 3, 5);
		superManager.doCalculations(function2, 10,5);
		
		System.out.println("Supermanager "+ superManager.getTaskCount());
		System.out.println("Supermanager "+ superManager.getResourceCount());
		
		System.out.println("Med mellomledere: " + Double.valueOf(superManager.getTaskCount() / Double.valueOf(superManager.getResourceCount())));
	}
}
