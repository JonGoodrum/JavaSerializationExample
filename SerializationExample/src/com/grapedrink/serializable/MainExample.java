package com.grapedrink.serializable;

public class MainExample {

	public static void main(String[] args) {

		printExampleResource();
	
	}
	
	
	private static void printExampleResource() {
		System.out.println( Serializer.getEmployeeResource().getInfo() );
	}
	
	private static void serializeThenDeserializeEmployee() {
		Employee employee = getEmployee("Dusty Flanders", "13 Magnolia Parkway San Diego CA 98226", 69, 336829);
		String examplebean = "example-bean.ser";
		Serializer.serialize(employee, examplebean);
		
		Employee deserializedEmployee = (Employee) Serializer.deserialize(examplebean);
		System.out.println("\n*** deserialized instance of Employee: ***\n");
		System.out.println( deserializedEmployee.getInfo() );
	}
	
	private static Employee getEmployee(String name, String address, int age, int ssn) {
		Employee e = new Employee();
		e.setName(name);
		e.setAddress(address);
		e.setAge(age);
		e.setSsn(ssn);
		return e;
	}
}
