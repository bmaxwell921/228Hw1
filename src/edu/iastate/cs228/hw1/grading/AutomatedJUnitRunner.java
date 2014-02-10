package edu.iastate.cs228.hw1.grading;

import edu.iastate.cs228.hw1.grading.TestClasses.CounterGeotaskTest;
import edu.iastate.cs228.hw1.grading.TestClasses.GroundTest;
import edu.iastate.cs228.hw1.grading.TestClasses.MobileObjectTest;
import edu.iastate.cs228.hw1.grading.TestClasses.PopulationMonitoringGeotaskTest;
import edu.iastate.cs228.hw1.grading.TestClasses.WarningGeotaskTest;

public class AutomatedJUnitRunner {
	public static void main(String[] args) {
		// Total points for Automated Tests
		double topTotal = 80;

		// Test cases
		new Grader(CounterGeotaskTest.class, "CounterGeotask tests").run();
		new Grader(PopulationMonitoringGeotaskTest.class, "PopulationMonitoringGeotask Tests").run();
		new Grader(WarningGeotaskTest.class, "WarningGeotaskTest Tests").run();
		new Grader(MobileObjectTest.class, "MobileObject Tests").run();
		new Grader(GroundTest.class, "Ground Tests").run();
		
		// Total print out
		double total = Grader.getTotal();
		double studentTotal = Grader.getStudentTotal();
		double normalized = (studentTotal / total) * topTotal;
		System.out.println();
		if (total != topTotal)
			System.out.println(String.format(
					"Raw Total Normalized Scores: %.2f/%.2f", studentTotal,
					total));
		System.out.println(String.format("Normalized Total Score: %.2f/%.2f",
				normalized, topTotal));
	}
}
