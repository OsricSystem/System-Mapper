package tester;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tester.models.Headmate;
import tester.models.Sys;

public class Main {
	private static Scanner in;
	private static File file;
	private static Sys system;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		file = new File("data.dat").getAbsoluteFile();

		try {
			if (!file.exists() || file.length() == 0)
				init();
			else
				setup();

			system.add(new Headmate("Tayto"));
			// edit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		end();
	}

	private static void init() {
		System.out.println("Enter System Name:");
		system = new Sys(in.nextLine());

		try (FileWriter w = new FileWriter(file.getPath())) {
			w.write("System Name:" + system.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void setup() {
		try (Scanner reader = new Scanner(file)) {
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				int index = line.indexOf(':');
				String label = line.substring(0, index);
				String data = line.substring(index + 1);
				if (label.equals("System Name"))
					system = new Sys(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void edit() {
		System.out.println("Entering Edit Mode\ntype 'EXIT' to quit");
		while (true) {
			String inp = in.nextLine();
			if (inp.equals("EXIT"))
				break;
		}
	}

	private static void end() {
		if (!file.exists()) {
			System.out.println("Unable to read generated file");
			return;
		}

		try (Scanner out = new Scanner(file)) {
			while (out.hasNextLine())
				System.out.println(out.nextLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void write(String name, String... types) {
		try {
			FileWriter w = new FileWriter("data.dat");
			if (types.length == 1 && types[0].equals("newSystem")) {
				w.write(String.format("systemname:%s\ndesc:\nheadmates:", name));
			}
			w.close();
		} catch (IOException e) {
		}
	}
}
