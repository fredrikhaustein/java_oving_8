package patterns.delegation.office;

import java.util.ArrayList;
import java.util.List;

public class Printer {
	
	private List<String> employeeHistoryList = new ArrayList<String>();
	private List<Employee> employeesList = new ArrayList<Employee>();
	

	public void printDocument(String document, Employee employee) {
		employeeHistoryList.add(document);
		employeesList.add(employee);
		System.out.println(document);
	}
	
	public List<String> getPrintHistory(Employee employee){
		List<String> employeeList = new ArrayList<String>();
		for (int i = 0;i<employeesList.size();i++) {
			if (employeesList.get(i).equals(employee)) {
				employeeList.add(employeeHistoryList.get(i));
			}
		}
		return employeeList;
	}
}
