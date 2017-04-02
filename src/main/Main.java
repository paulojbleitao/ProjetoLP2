package main;

import easyaccept.EasyAccept;

public class Main {
	
	public static void main(String[] args) throws Exception {
		args = new String[] { "facade.Facade", "acceptance_tests/us1_test.txt",
				"acceptance_tests/us1_test_exception.txt", "acceptance_tests/us2_test.txt",
				"acceptance_tests/us2_test_exception.txt", "acceptance_tests/us3_test.txt",
				"acceptance_tests/us3_test_exception.txt", "acceptance_tests/us4_test.txt",
				"acceptance_tests/us5_test.txt", "acceptance_tests/us6_test.txt",
				"acceptance_tests/us6_test_exception.txt" };
		EasyAccept.main(args);
	}

}
