import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

class Customer{
    String customerName;
    String customerPhone;
    String customerAddress;
    double squareFootage;

    Customer(String customerName, String customerPhone, String customerAddress, double squareFootage) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.squareFootage = squareFootage;
    }
}

class Commercial extends Customer{

    private String propertyName;
    private double weeklyRate;
    private boolean multiPropDiscount;

    Commercial(String customerName, String customerPhone, String customerAddress, double squareFootage, String propertyName, boolean multiPropDiscount) {
        super(customerName, customerPhone, customerAddress, squareFootage);
        this.weeklyRate = .005;
        this.propertyName = propertyName;
        this.multiPropDiscount = multiPropDiscount;
    }

    void commercialWeeklyCharges(){
        NumberFormat numFormat = NumberFormat.getNumberInstance();
        numFormat.setGroupingUsed(true);
        numFormat.setMaximumFractionDigits(2);
        numFormat.setMinimumFractionDigits(2);
        double adjustedRate;
        System.out.println("-----------------------");
        System.out.println("Customer name: " + customerName);
        System.out.println("The property name is: " + propertyName);
        System.out.println("Address: " + customerAddress);
        System.out.println("Phone Number: " + customerPhone);
        System.out.println("Total Square footage: " + squareFootage);
        if (multiPropDiscount) {
            adjustedRate = weeklyRate - .10 * weeklyRate;
            System.out.println("Qualifies for multiple property discount: Yes");

        } else{
            adjustedRate = weeklyRate;
            System.out.println("Qualifies for multiple property discount: No");

        }

        double total = squareFootage * adjustedRate;
        String formattedTotal = numFormat.format(total);
        System.out.println("Your total weekly charges are: $" + formattedTotal);
        System.out.println("-----------------------");

    }
}

class Residential extends Customer{
    private boolean senior;
    private double weeklyRate;
    Residential(String customerName, String customerPhone, String customerAddress, double squareFootage, boolean senior) {
        super(customerName, customerPhone, customerAddress, squareFootage);
        this.weeklyRate = .006;
        this.senior = senior;

    }

    void residentialWeeklyCharges() {
        NumberFormat numFormat = NumberFormat.getNumberInstance();
        numFormat.setGroupingUsed(true);
        numFormat.setMaximumFractionDigits(2);
        numFormat.setMinimumFractionDigits(2);
        double adjustedRate;
        System.out.println("-----------------------");
        System.out.println("Customer name: " + customerName);
        System.out.println("Address: " + customerAddress);
        System.out.println("Phone Number: " + customerPhone);
        System.out.println("Total Square footage: " + squareFootage);

        if (senior) {
            adjustedRate = weeklyRate - .15 * weeklyRate;
            System.out.println("Qualifies for senior discount: Yes");

        } else{
            adjustedRate = weeklyRate;
            System.out.println("Qualifies for senior discount: No");

        }
        double total = squareFootage * adjustedRate;
        String formattedTotal = numFormat.format(total);
        System.out.println("Your total weekly charges are: $" + formattedTotal);
        System.out.println("-----------------------");
    }
}

public class Main {


    private static void getUserInput(){

        Scanner userInput = new Scanner(System.in);

        int userChoice;

        try {
            do

                {displayMenu();

                userChoice = userInput.nextInt();

                switch (userChoice) {
                    case 1:
                        residential();
                        break;
                    case 2:
                        commercial();
                        break;
                    case 3:
                        break;
                        default:
                            System.out.println("Please enter a valid choice.\n");

                }

            } while (userChoice != 3);
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid choice.\n");
            getUserInput();
        }

    }

    private static void displayMenu() {
        System.out.println("\nWhat kind of property do you own?");
        System.out.println("---------------------------------\n");
        System.out.println("1. Residential\n");
        System.out.println("2. Commercial\n");
        System.out.println("3. Exit Program\n");

    }

    private static void residential(){
        double sqft;
        int age;

        Scanner userInput = new Scanner(System.in);
        boolean seniorDiscount;
        System.out.println("\nPlease enter your name: \n");
        String custName = userInput.nextLine();
        System.out.println("\nPlease enter your phone number: \n");
        String custPhone = userInput.nextLine();
        System.out.println("\nPlease enter your address: \n");
        String custAdd = userInput.nextLine();


        while(true) {
            System.out.println("Enter the property square footage: ");
            try {
                sqft = Double.parseDouble(userInput.nextLine());
                break;

            } catch (NumberFormatException e) {
                System.out.println("The square footage must be a number.  Please try again.");
            }
        }
        while(true) {
            System.out.println("\nplease enter your age for the senior discount: \n");
            try {
                age = Integer.parseInt(userInput.nextLine());
                seniorDiscount = age >= 65;
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("You must enter a valid age.\n");
            }
        }

        Residential customerOne = new Residential(custName,custPhone,custAdd,sqft,seniorDiscount);

        customerOne.residentialWeeklyCharges();

    }

    private static void commercial(){

        Scanner userInput = new Scanner(System.in);
        double sqft;
        boolean multPropDiscount;
        int properties;
        System.out.println("\nMultiple properties qualify for a discount: \n");
        while(true) {

            System.out.println("How many properties to you own?");
            try {
                properties = Integer.parseInt(userInput.nextLine());
                multPropDiscount = properties > 1;
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("You must enter a valid number.\n");
            }
        }
        System.out.println("\nPlease enter your name: \n");
        String custName = userInput.nextLine();
        System.out.println("\nEnter the property name: \n");
        String propName = userInput.nextLine();
        System.out.println("\nPlease enter your phone number: \n");
        String custPhone = userInput.nextLine();
        System.out.println("\nPlease enter your address: \n");
        String custAdd = userInput.nextLine();
        while(true) {
            System.out.println("Enter the property square footage: ");
            try {
                sqft = Double.parseDouble(userInput.nextLine());
                break;

            } catch (NumberFormatException e) {
                System.out.println("The square footage must be a number.  Please try again.\n");
            }
        }

        Commercial customerTwo = new Commercial(custName,custPhone,custAdd,sqft,propName,multPropDiscount);
        customerTwo.commercialWeeklyCharges();
    }

    public static void main(String args[]) {
        getUserInput();
    }
}