import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/*
 * You should pass all JUnit tests in this class before you try running the FastenerOrderSystem
 * program.  Note that this testing class does not really have enough tests, particularly
 * when testing for illegal attributes.  But it is not bad...
 */
class TestHierarchy {

	public static ArrayList<Fastener> db;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		db = new ArrayList<>();
	}

	// Legal instances
	@Test
	public void test0() throws Exception {
										// size, length, gauge, finish, price/unit, unit size
		CommonNail toAdd = new CommonNail("16D", 3.5, 8.0, "Bright", 174.29, 2450);
		assertNotNull(toAdd);
		db.add(toAdd);
	}
	@Test
	public void test1() throws Exception {
										// size, length, gauge, finish, price/unit, unit size
		CommonNail toAdd = new CommonNail("10D", 3.0, 9.0, "Bright", 21.69, 345);
		assertNotNull(toAdd);
		db.add(toAdd);
	}
	@Test
	public void test2() throws Exception {
										// size, length, gauge, finish, price/unit, unit size
		CommonNail toAdd = new CommonNail("60D", 6.0, 2.0, "Bright", 153.29, 550);
		assertNotNull(toAdd);
		db.add(toAdd);
	}
	@Test
	public void test3() throws Exception {
									// thread,  material, finish, price/unit, unit size
		WingNut toAdd = new WingNut("1/4-20", "Stainless Steel", "Plain", 0.5528, 1);
		assertNotNull(toAdd);
		db.add(toAdd);
	}
	@Test
	public void test4() throws Exception {
									// thread,  material, finish, price/unit, unit size
		WingNut toAdd = new WingNut("5/16-18", "Steel", "Zinc", 18.225, 50);
		assertNotNull(toAdd);
		db.add(toAdd);
	}
	@Test
	public void test5() throws Exception {
											// length, thread,  material, finish, price/unit, unit size
		CarriageBolt toAdd = new CarriageBolt(1.0, "3/8-16", "Steel", "Zinc", 0.2985, 1);
		assertNotNull(toAdd);
		db.add(toAdd);
	}
	@Test
	public void test6() throws Exception {
											// length, thread,  material, finish, price/unit, unit size
		CarriageBolt toAdd = new CarriageBolt(0.75, "1/4-20", "Stainless Steel", "Plain", 18.825, 50);
		assertNotNull(toAdd);
		db.add(toAdd);
	}
	@Test
	public void test7() throws Exception {
											// length, thread,  material, finish, price/unit, unit size
		CarriageBolt toAdd = new CarriageBolt(12.0, "1/2-13", "Steel", "Hot Dipped Galvanized", 3.72, 1);
		assertNotNull(toAdd);
		db.add(toAdd);
	}
	@Test
	public void test8() throws Exception {
									// length, thread,  material, finish, head, drive, point, price/unit, unit size
		WoodScrew toAdd = new WoodScrew(2.0, "#8-13", "Steel", "ACQ 1000 Hour", "Flat", "Philips", "Type 17", 44.10, 750);
		assertNotNull(toAdd);
		db.add(toAdd);
	}
	@Test
	public void test9() throws Exception {
									// length, thread,  material, finish, head, drive, point, price/unit, unit size
		WoodScrew toAdd = new WoodScrew(0.75, "#8-15", "Steel", "Zinc", "Flat", "Square", "Sharp", 8.84, 100);
		assertNotNull(toAdd);
		db.add(toAdd);
	}
	@Test
	public void test10() throws Exception {
									// length, thread,  material, finish, head, drive, point, price/unit, unit size
		WoodScrew toAdd = new WoodScrew(3.0, "#10-13", "Steel", "Zinc", "Flat", "Square", "Sharp", 24.63, 100);
		assertNotNull(toAdd);
		db.add(toAdd);
	}

	// Illegal instances
	@Test
	public void test11() throws Exception {
									// size, length, gauge, finish, price/unit, unit size
		assertThrows(IllegalFastener.class, 
				() -> new CommonNail("11D", 3.0, 2, "Hot Dipped Galvanized", 153.29, 550));
	}
	@Test
	public void test12() throws Exception {
									// size, length, gauge, finish, price/unit, unit size
		assertThrows(IllegalFastener.class,
				() -> new CommonNail("10D", 1.5, 9.0, "Bright", 21.69, 345));
	}
	@Test
	public void test13() throws Exception {
									// size, length, gauge, finish, price/unit, unit size
		assertThrows(IllegalFastener.class,
				() -> new CommonNail("10D", 3.0, 6.0, "Bright", 21.69, 345));
	}
	@Test
	public void test14() throws Exception {
									// size, length, gauge, finish, price/unit, unit size
		assertThrows(IllegalFastener.class,
				() -> new CommonNail("10D", 3.0, 6.0, "Plain", 21.69, 345));
	}
	@Test
	public void test15() throws Exception {
									// thread,  material, finish, price/unit, unit size
		assertThrows(IllegalFastener.class,
				() -> new WingNut("1/4-20", "Stainless Steel", "Black Phosphate", 0.5528, 1));
	}
	@Test
	public void test16() throws Exception {
								// thread,  material, finish, price/unit, unit size
		assertThrows(IllegalFastener.class,
				() -> new WingNut("15/16-18", "Steel", "Zinc", 18.225, 50));
	}
	@Test
	public void test17() throws Exception {
									// length, thread,  material, finish, price/unit, unit size
		assertThrows(IllegalFastener.class,
				() -> new CarriageBolt(1.0, "3/8-16", "Nylon", "Zinc", 0.2985, 1));
	}
	@Test
	public void test18() throws Exception {
									// length, thread,  material, finish, price/unit, unit size
		assertThrows(IllegalFastener.class,
				() -> new CarriageBolt(0.05, "1/4-20", "Stainless Steel", "Plain", 18.825, 50));
	}
	@Test
	public void test19() throws Exception {
									// length, thread,  material, finish, price/unit, unit size
		assertThrows(IllegalFastener.class,
				() -> new CarriageBolt(12.0, "1/2-13", "Steel", "Lubricated", 3.72, 1));
	}
	@Test
	public void test20() throws Exception {
								// length, thread,  material, finish, head, drive, point, price/unit, unit size
		assertThrows(IllegalFastener.class,
				() -> new WoodScrew(2.0, "#8-13", "Steel", "ACQ 1000 Hour", "Flat", "Philips", "Blunt", 44.10, 750));
	}
	@Test
	public void test21() throws Exception {
								// length, thread,  material, finish, head, drive, point, price/unit, unit size
		assertThrows(IllegalFastener.class,
				() -> new WoodScrew(25.0, "#8-15", "Steel", "Zinc", "Flat", "Square", "Sharp", 8.84, 100));
	}
	@Test
	public void test22() throws Exception {
								// length, thread,  material, finish, head, drive, point, price/unit, unit size
		assertThrows(IllegalFastener.class,
				() -> new WoodScrew(3.0, "#10-13", "Aluminium", "Zinc", "Flat", "Square", "Sharp", 24.63, 100));
	}
	
	// This method will save the collection of legal Fastener objects to be used
	// by the FastenerOrderSystem class.	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		String dbFile = "Database.dat";
		File dbFileObject = new File(dbFile);
		if (dbFileObject.exists())
			dbFileObject.delete();
		db.trimToSize();
		// Using a lambda function here:
		db.sort((Fastener f1, Fastener f2) -> f1.toString().compareToIgnoreCase(f2.toString()));
		for (Fastener item : db)
			System.out.println(item);
		// Save the ArrayList so it can be used by the order system class.
    	try (ObjectOutputStream binFileOut = new ObjectOutputStream(new FileOutputStream(dbFile))) {
            binFileOut.writeObject(db);
        }
	}

}