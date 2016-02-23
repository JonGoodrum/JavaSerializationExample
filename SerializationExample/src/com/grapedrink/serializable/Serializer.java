package com.grapedrink.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {

	private Serializer(){}
	
	
	private static class Beans {
		public static final String DIR = System.getProperty("java.io.tmpdir") + File.separator;
		public static final InputStream EMPLOYEE = Beans.class.getResourceAsStream("/com/grapedrink/beans/some-employee.bean");
	}
	
	private static String getBeanPath(String serializedBeanName) {
		return Beans.DIR + serializedBeanName;
	}
	
	/**
	 * loads this package's Employee resource
	 * 
	 * @param employee
	 * @return
	 */
	public static Employee getEmployeeResource() {
		try (ObjectInputStream objectSerializer = new ObjectInputStream(Beans.EMPLOYEE)) { // throws IOException (StreamCorruptedException)
	        Employee employee = (Employee) objectSerializer.readObject();
	        objectSerializer.close();
            return employee;
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
		
	
	public static void serialize(Employee bean, String serializedBeanName) {
		try {
			createFileIfNonExistent(serializedBeanName);
			FileOutputStream fos = new FileOutputStream(getBeanPath(serializedBeanName));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(bean);
	        oos.close();
	        fos.close();
	        System.out.println("Serialized data is saved in " + getBeanPath(serializedBeanName));
	    }
		catch (IOException e)
	    {
			System.out.println("Exception caught during serialization");
	        e.printStackTrace();
	    }
	}
	
	private static void createFileIfNonExistent(String bean) {
		try {
			File file = new File(getBeanPath(bean));
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		catch (IOException e) {
			System.out.println("Exception creating .ser file in " + Beans.DIR);
			e.printStackTrace();
		}
	}
	
	public static Object deserialize(String serializedBeanName) {
		Object bean = null;
		try (FileInputStream fis = new FileInputStream(getBeanPath(serializedBeanName))) {
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        bean = ois.readObject();
	        ois.close();
		} catch ( ClassNotFoundException | IOException e) {
			System.out.println("Exception caught during deserialization");
			e.printStackTrace();
		}
        return bean;
	}
	
	
}
