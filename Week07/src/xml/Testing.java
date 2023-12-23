package xml;
import java.util.Scanner;


public class Testing {
	private static Scanner scanner = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		int luaChon;
		
		ManageProduct dom = new ManageProduct();
		
		boolean flag = true;
		
		do {
			createMenu();
			luaChon = scanner.nextInt();
			
			switch (luaChon) {
			case 1: 
				scanner.nextLine();
				Product product = createNewProduct();
				dom.addProduct(product);
				break;
			
			case 2: 
				scanner.nextLine();
				System.out.println("Enter productID: ");
				String productID = scanner.nextLine();
				dom.deleteProduct(productID);
				break;
				
			case 3:	
				scanner.nextLine();
				System.out.println("Enter productID: ");
				productID = scanner.nextLine();
				System.out.println("Enter new price: ");
				double donGia = scanner.nextDouble();
				dom.updatePrice(productID, donGia);
				
				break;
			case 4: 
				dom.printAll();
				break;
				
			case 5: 
				dom.writeXMLFile();
				break;
				
			default:
				flag = false;
				break;
			}
		} while (flag);
	}
	
	
	public static Product createNewProduct() {
		System.out.println("Enter productID: ");
		String productID = scanner.nextLine();
		System.out.println("Enter name: ");
		String name = scanner.nextLine();
		System.out.println("Enter manufacture: ");
		String manufacture = scanner.nextLine();
		System.out.println("Enter description: ");
		String description = scanner.nextLine();
		Supplier supplier = createNewSupplier();
		System.out.println("Enter price: ");
		double price = scanner.nextDouble();
		return new Product(productID, name, manufacture, description, supplier, price);
	}
	
	
	public static Supplier createNewSupplier() {
		System.out.println("Enter supplier name: ");
		String name = scanner.nextLine();
		System.out.println("Enter country: ");
		String country = scanner.nextLine();
		System.out.println("Enter website: ");
		String website = scanner.nextLine();
		return new Supplier(name, country, website);
	}
	
	
	private static void createMenu() {
		System.out.println("======MENU======");
		System.out.println("1. Add product");
		System.out.println("2. Delete product");
		System.out.println("3. Update product");
		System.out.println("4. Print all");
		System.out.println("5. Write XML file");
		System.out.println("0. Exit");
		System.out.println("Your choice: ");
	}
}
