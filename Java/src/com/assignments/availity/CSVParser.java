package com.assignments.availity;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
/**
* Java class which parses the contents of a csv file and perform some operations on it.
*
* @author  Vidhya Vikraman Nair
*
*/
public class CSVParser {

	/**
     * Method that reads the CSV file and outputs the List of objects
     * @param  filePath CSV File Path 
     * @return Enrollment List 
     */

	public List<Enrollment> readCSVFile(String filePath) {
		List<Enrollment> enrollmentList = new ArrayList<>();
		Path pathToFile = Paths.get(filePath);
		try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
			String line = br.readLine();
			while (line != null) {
				String[] column = line.split(",");
				Enrollment enrollment = new Enrollment(); 
				enrollment.setFirstName(column[0]);
				enrollment.setLastName(column[1]);
				enrollment.setUserId(column[2]);
				enrollment.setVersion(Integer.parseInt(column[3]));
				enrollment.setInsuranceCompany(column[4]);
				enrollmentList.add(enrollment);
				line = br.readLine();
			}
		} catch (Exception exception) {
			System.out.println("Exception found" + exception);
		}
		return enrollmentList;
	}
	
	/**
     * Method that displays the contents of CSV file grouped by Insurance Company
     * param Enrollment List 
     */

	public static void companyWiseEnrollment(List<Enrollment> enrollmentDetails) {
		Map<String, List<Enrollment>> enrollmentGrouping = enrollmentDetails.stream()
				.collect(Collectors.groupingBy(Enrollment::getInsuranceCompany));
		System.out.println(Arrays.asList(enrollmentGrouping));
	}
	/**
     * Method that sort the contents by FirstName and LastName
     * param Enrollment List 
     */
	public static void sortEnrollment(List<Enrollment> enrollmentDetails) {
		Comparator<Enrollment> compareByName = Comparator.comparing(Enrollment::getFirstName)
				.thenComparing(Enrollment::getLastName);
		Collections.sort(enrollmentDetails, compareByName);
	}
	/**
     * Method to remove duplicates and retain the higher version
     * param Enrollment List 
     */
	public static void removeDuplicates(List<Enrollment> enrollmentDetails) {
		Comparator<Enrollment> compareByVersion = Comparator.comparing(Enrollment::getVersion).reversed();
		Collections.sort(enrollmentDetails, compareByVersion);
		Set<Enrollment> set = new TreeSet<>(new Comparator<Enrollment>() {
			@Override
			public int compare(Enrollment enrollment1, Enrollment enrollment2) {
				if (enrollment1.getInsuranceCompany().equalsIgnoreCase(enrollment2.getInsuranceCompany())
						&& enrollment1.getUserId().equalsIgnoreCase(enrollment2.getUserId())) {
					return 0;
				}
				return 1;

			}
		});
		set.addAll(enrollmentDetails);
		System.out.println(Arrays.asList(set));
	}

	public static void main(String[] args) {
		CSVParser parser = new CSVParser();
		List<Enrollment> enrollmentDetails = parser.readCSVFile("text");
		companyWiseEnrollment(enrollmentDetails);
		System.out.println("Before Sorting:" + Arrays.asList(enrollmentDetails));
		sortEnrollment(enrollmentDetails);
		System.out.println(Arrays.asList(enrollmentDetails));
		removeDuplicates(enrollmentDetails);
	}
}
