import java.util.ArrayList;
import java.util.Scanner;

public class FoodOrderingTaskFinal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstName = askFirstName(scanner);
        String surName = askSurName(scanner);
        String chosenTitle = askTitle(scanner);

        String displayName = getDisplayName(chosenTitle, firstName, surName);

        System.out.println(displayName + ", welcome and thanks for choosing us!");
        System.out.println();

        int cutleryChoice; //Declare outside loop so it's accessible inside

        while (true) { //Retry loop to allow user to reselect choice if input is invalid
            try {
                System.out.println("Would you like some plastic cutlery with your order? Please choose: \n1 If you would \n2 If not");
                cutleryChoice = scanner.nextInt();

                if (cutleryChoice == 1) {
                    System.out.println("Certainly, we'll bring it with your food.");
                    scanner.nextLine();
                    break; //Break out of loop is user input is valid
                } else if (cutleryChoice == 2) {
                    System.out.println("No problem at all, we'll leave it out.");
                    scanner.nextLine();
                    break; //Break out of loop is user input is valid
                } else {
                    System.out.println("Sorry I didn't quite catch that, please choose option 1 or 2.");
                } //Do not accept any option other than 1 or 2
            } catch (Exception e) {
                System.out.println("Sorry, I don't know what you mean, please choose option 1 or 2."); //Only allow int input
                scanner.next(); //Clear invalid token left from previous input
            }
        }

        //Print menu items and prices
        System.out.println();
        System.out.println("Now let me show you our menu:");
        System.out.println("MENU");
        System.out.println("================");
        System.out.println("Starters:");
        System.out.println("========");
        printStarterArray();
        System.out.println("========");
        System.out.println("Mains:");
        System.out.println("========");
        printMainArray();
        System.out.println("========");
        System.out.println("Desserts:");
        System.out.println("========");
        printDessertArray();
        System.out.println("========");
        System.out.println("Drinks:");
        System.out.println("========");
        printDrinkArray();
        System.out.println("========");
        System.out.println("Special Offer: 10% discount with loyalty card!");
        System.out.println("You can also order a custom dish but these are more expensive and a set price: £11.50 for starters, £15 for mains and £7.50 for desserts.");
        System.out.println("================");
        System.out.println();

        int allergy; //Declare outside loop so it's accessible inside

        while (true) { //Retry loop
            try {
                System.out.println("Now before we start, are you allergic to any of the following: \n1 for No \n2 for Garlic \n3 for Lentils \n4 for Seafood \n5 for Peanuts \n6 for Dairy \n7 for Other");
                allergy = scanner.nextInt(); //Allergy = user input

                if (allergy < 1 || allergy > 7) { //Custom guard to only accept input from 1-7 as these are the only options
                    System.out.println("Please choose one of the options listed.");
                    continue; //Return to start of loop if input is not from 1-7
                }
                break; //break out of loop upon successful user input
            } catch (Exception e) {
                System.out.println("Please enter a whole number from 1 to 7.");
                scanner.next(); //Clear invalid token
            }
        }

        scanner.nextLine();
        //Again, declare variables outside the switch statement as you can't declare them inside and assume no allergies
        boolean garlicAllergy = false;
        boolean lentilAllergy = false;
        boolean seafoodAllergy = false;
        boolean peanutAllergy = false;
        boolean dairyAllergy = false;

        switch (allergy) { //Switch to either allow user to order anything from the menu or block certain menu choices if they are allergic to it
            case 1 ->
                    System.out.println("Perfect, you can order anything from the menu!"); //Allow user to choose any item
            case 2 -> {
                //If they are allergic, update boolean value and inform the user their dietary requirements have been noted
                garlicAllergy = true;
                System.out.println("Garlic allergy recorded.");
            }
            case 3 -> {
                lentilAllergy = true;
                System.out.println("Lentil allergy recorded.");
            }
            case 4 -> {
                seafoodAllergy = true;
                System.out.println("Seafood allergy recorded.");
            }
            case 5 -> {
                peanutAllergy = true;
                System.out.println("Peanut allergy recorded.");
            }
            case 6 -> {
                dairyAllergy = true;
                System.out.println("Dairy allergy recorded - all our desserts on the menu contain dairy so you will need to order a custom item if you want dessert.");
            }
            case 7 ->
                    System.out.println("If you have any other allergies, I'm afraid these can't be accommodated with our existing menu, but you can order custom dishes to suit any dietary requirements.");
            /*Can't cover all allergies so inform the user that for any allergies beyond those listed, our menu can't accommodate them with substitutions
             **so they should order a custom dish to prevent and allergic reactions
             */
        }

        System.out.println();
        System.out.println("So, let's begin your order!");
        System.out.println();

        int orders = askOrderCounter(scanner);

        //Create ArrayLists to hold the names of items and ordered and similarly price
        ArrayList<String> orderedItems = new ArrayList<>();
        ArrayList<Double> itemPrices = new ArrayList<>();

        int starter; //Declare variables outside the outer confirmation retry loop so they're accessible
        String starterName = "";
        double starterPrice = 0.0;

        /* Loop through order process however many times user wants based on previous inputs
         * to allow them to make multiple orders for family or friends
         */
        System.out.println();
        for (int orderNumber = 0; orderNumber < orders; orderNumber++) {
            System.out.println("Please place your order for order number " + (orderNumber + 1) + ".");
            boolean starterConfirmed = false;
            /* Declare boolean as false to be changed at the end in order to break out of for loop on order 2+
             * as without this, user will be stuck in infinite loop if attempting to retry order on any order > 1st
             * but declaring as false at the start resets the loop after each order is confirmed
             */

            while (true) {
                /*Declare outer loop to allow the confirm order section i.e., let user choose meal again
                 * if they're not happy with it
                 */


                while (true) { //Inner retry loop
                    try {
                        System.out.println("For your starter, please choose from the following:");
                        if (!garlicAllergy) { // If user has garlic allergy, DO NOT display garlic bread as an option
                            System.out.println("1 For Garlic Bread");
                        }
                        if (!lentilAllergy) {
                            System.out.println("2 For Lentil Soup");
                        }
                        if (!seafoodAllergy) {
                            System.out.println("3 For Prawn Cocktail");
                        }
                        System.out.println("4 For Custom Dish (These are more expensive)"); //Allow user to choose custom dish
                        System.out.println("5 To skip starter"); //Allow user to skip course
                        starter = scanner.nextInt();

                        if (starter == 1 && garlicAllergy) { //If user is allergic, do not let them order the item
                            System.out.println("Sorry, but you're allergic to this item so you can't order it.");
                            continue;
                        }
                        if (starter == 2 && lentilAllergy) {
                            System.out.println("Sorry, but you're allergic to this item so you can't order it.");
                            continue;
                        }
                        if (starter == 3 && seafoodAllergy) {
                            System.out.println("Sorry, but you're allergic to this item so you can't order it.");
                            continue;
                        }
                        if (starter < 1 || starter > 5) { //Guard to ensure input is within range
                            System.out.println("Please choose one of the allocated options.");
                            continue;
                        }
                        break;
                    } catch (Exception e) { //Catch invalid input
                        System.out.println("Please enter a whole number from 1 to 5.");
                        scanner.next();
                    }
                }

                scanner.nextLine(); //Clear newline from nextInt

                switch (starter) { //Switch for started choice to output order choice and price
                    case 1 -> {
                        starterName = "Garlic Bread";
                        starterPrice = 4.50;
                    }
                    case 2 -> {
                        starterName = "Lentil Soup";
                        starterPrice = 4.99;
                    }
                    case 3 -> {
                        starterName = "Prawn Cocktail";
                        starterPrice = 9.99;
                    }
                    case 4 -> {
                        System.out.println("Please enter the name of your custom starter:");
                        String customStarter = scanner.nextLine(); //This line could be removed and replaced with customStarter = scanner.nextline();
                        starterName = customStarter;
                        starterPrice = 11.50;
                    }
                    case 5 -> {
                        starterName = "No starter";
                        starterPrice = 0.0;
                    }
                }

                System.out.println("You have chosen " + starterName + ": Please select: \n1 if you are happy with this and want to continue? \n2 if you would like to choose another option?");
                //Print order and let the user choose if they are happy to proceed with their choice or want to change it

                int starterConfirmation;
                while (true) {
                    try {
                        starterConfirmation = scanner.nextInt();

                        if (starterConfirmation == 1) {
                            System.out.println("Great choice!");
                            starterConfirmed = true; //Move on to next order/course if user is happy with their choice
                            break; //Only breaks inner loop
                        } else if (starterConfirmation == 2) {
                            System.out.println("No problem, please choose your starter again.");
                            System.out.println();
                            break; //Breaks out of inner loop but without starterConfirmed being true, takes them back to the start of the loop to rechoose
                        } else {
                            System.out.println("Please enter either 1 or 2.");
                        }
                    } catch (Exception e) {
                        System.out.println("Please choose 1 or 2.");
                        scanner.next();
                    }
                }
                if (starterConfirmed) { //If starterConfirmed is true - user is happy, add order and price to ArrayLists
                    orderedItems.add(starterName);
                    itemPrices.add(starterPrice);
                    break; //break outer loop while (true) and continue on
                }
            }
        }

        System.out.println("Great, your starter choices have been recorded. let's move on to the mains.");
        System.out.println();

        /* Main course choice works exactly the same as starter so has not been commented as previous comments are
         * still relevant and applicable here
         */
        int main; //Declare variables outside the outer confirmation retry loop so they're accessible
        String mainName = "";
        double mainPrice = 0.0;

        System.out.println();
        for (int orderNumber = 0; orderNumber < orders; orderNumber++) {
            System.out.println("Please place your order for order number " + (orderNumber + 1) + ".");
            boolean mainConfirmed = false;

            while (true) {


                while (true) {
                    try {
                        System.out.println("For your main, please choose from the following:");
                        if (!seafoodAllergy) {
                            System.out.println("1 For Fish and Chips");
                        }
                        if (!peanutAllergy) {
                            System.out.println("2 For Vegetarian Curry");
                        }
                        if (!dairyAllergy) {
                            System.out.println("3 For Cottage Pie");
                        }
                        System.out.println("4 For Custom Dish (These are more expensive)");
                        System.out.println("5 To skip main course");
                        main = scanner.nextInt();

                        if (main == 1 && seafoodAllergy) {
                            System.out.println("Sorry, but you're allergic to this item so you can't order it.");
                            continue;
                        }
                        if (main == 2 && peanutAllergy) {
                            System.out.println("Sorry, but you're allergic to this item so you can't order it.");
                            continue;
                        }
                        if (main == 3 && dairyAllergy) {
                            System.out.println("Sorry, but you're allergic to this item so you can't order it.");
                            continue;
                        }
                        if (main < 1 || main > 5) {
                            System.out.println("Please choose one of the allocated options.");
                            continue;
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Please enter a whole number from 1 to 5.");
                        scanner.next();
                    }
                }

                scanner.nextLine(); //Clear newline from nextInt

                switch (main) {
                    case 1 -> {
                        mainName = "Fish and Chips";
                        mainPrice = 12.50;
                    }
                    case 2 -> {
                        mainName = "Vegetarian Curry";
                        mainPrice = 9.99;
                    }
                    case 3 -> {
                        mainName = "Cottage Pie";
                        mainPrice = 11.50;
                    }
                    case 4 -> {
                        System.out.println("Please enter the name of your custom main course:");
                        String customMain = scanner.nextLine();
                        mainName = customMain;
                        mainPrice = 15;
                    }
                    case 5 -> {
                        mainName = "No main";
                        mainPrice = 0.0;
                    }
                }

                System.out.println("You have chosen " + mainName + ": Please select: \n1 if you are happy with this and want to continue? \n2 if you would like to choose another option?");

                int mainConfirmation;
                while (true) {
                    try {
                        mainConfirmation = scanner.nextInt();

                        if (mainConfirmation == 1) {
                            System.out.println("Great choice!");
                            mainConfirmed = true;
                            break; //Only breaks inner loop
                        } else if (mainConfirmation == 2) {
                            System.out.println("No problem, please choose your main course again.");
                            System.out.println();
                            break;
                        } else {
                            System.out.println("Please enter either 1 or 2.");
                        }
                    } catch (Exception e) {
                        System.out.println("Please choose 1 or 2.");
                        scanner.next();
                    }
                }
                if (mainConfirmed) {
                    orderedItems.add(mainName);
                    itemPrices.add(mainPrice);
                    break; //break outer loop while (true) and continue on
                }
            }
        }

        System.out.println("Excellent, your main course choices have been recorded. let's move on to dessert.");
        System.out.println();

        /* Dessert choice works exactly the same as starter so has not been commented as previous comments are
         * still relevant and applicable here - minor changes at allergies commented
         */
        int dessert; //Declare variables outside the outer confirmation retry loop so they're accessible
        String dessertName = "";
        double dessertPrice = 0.0;

        System.out.println();
        for (int orderNumber = 0; orderNumber < orders; orderNumber++) {
            System.out.println("Please place your order for order number " + (orderNumber + 1) + ".");
            boolean dessertConfirmed = false;

            while (true) {


                while (true) {
                    try {
                        System.out.println("For your dessert, please choose from the following:");
                        if (!dairyAllergy) {
                            /* One allergy user for all desserts - user was informed earlier
                             * that if they have a dairy allergy, they must choose a custom dish or skip dessert
                             */
                            System.out.println("1 For Trio of Ice-Cream");
                            System.out.println("2 For Apple Pie");
                            System.out.println("3 For Cheesecake");
                        }
                        System.out.println("4 For Custom Dish (These are more expensive)");
                        System.out.println("5 To skip dessert");
                        dessert = scanner.nextInt();

                        if (dairyAllergy && dessert != 4 && dessert != 5) { //Let user know why options 1 to 3 are invalid
                            System.out.println("Sorry, but all our menu desserts contain dairy so you cannot order them with your allergy.");
                            continue;
                        }
                        if (dessert < 1 || dessert > 5) {
                            System.out.println("Please choose one of the allocated options.");
                            continue;
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Please enter a whole number from 1 to 5.");
                        scanner.next();
                    }
                }

                scanner.nextLine(); //Clear newline from nextInt

                switch (dessert) {
                    case 1 -> {
                        dessertName = "Trio of Ice-Cream";
                        dessertPrice = 4.50;
                    }
                    case 2 -> {
                        dessertName = "Apple Pie";
                        dessertPrice = 5.20;
                    }
                    case 3 -> {
                        dessertName = "Cheesecake";
                        dessertPrice = 6.50;
                    }
                    case 4 -> {
                        System.out.println("Please enter the name of your custom dessert:");
                        String customDessert = scanner.nextLine();
                        dessertName = customDessert;
                        dessertPrice = 7.50;
                    }
                    case 5 -> {
                        dessertName = "No dessert";
                        dessertPrice = 0.0;
                    }
                }

                System.out.println("You have chosen " + dessertName + ": Please select: \n1 if you are happy with this and want to continue? \n2 if you would like to choose another option?");

                int dessertConfirmation;
                while (true) {
                    try {
                        dessertConfirmation = scanner.nextInt();

                        if (dessertConfirmation == 1) {
                            System.out.println("Great choice!");
                            dessertConfirmed = true;
                            break; //Only breaks inner loop
                        } else if (dessertConfirmation == 2) {
                            System.out.println("No problem, please choose your dessert again.");
                            System.out.println();
                            break;
                        } else {
                            System.out.println("Please enter either 1 or 2.");
                        }
                    } catch (Exception e) {
                        System.out.println("Please choose 1 or 2.");
                        scanner.next();
                    }
                }
                if (dessertConfirmed) {
                    orderedItems.add(dessertName);
                    itemPrices.add(dessertPrice);
                    break; //break outer loop while (true) and continue on
                }
            }
        }

        System.out.println("Excellent, all your food choices have been recorded. Finally, what would you like to drink?");
        System.out.println();

        /* Drink choice works exactly the same as starter so has not been commented as previous comments are still
         * relevant and applicable here - no allergy or custom order section here as drinks are only what's in stock
         */
        int drink; //Declare variables outside the outer confirmation retry loop so they're accessible
        String drinkName = "";
        double drinkPrice = 0.0;

        System.out.println();
        for (int orderNumber = 0; orderNumber < orders; orderNumber++) {
            System.out.println("Please place your order for order number " + (orderNumber + 1) + ".");
            boolean drinkConfirmed = false;

            while (true) {


                while (true) {
                    try {
                        System.out.println("For your drink, please choose from the following:");
                        System.out.println("1 Tea");
                        System.out.println("2 Coffee");
                        System.out.println("3 Lemonade");
                        System.out.println("4 For no drink");
                        drink = scanner.nextInt();

                        if (drink < 1 || drink > 4) {
                            System.out.println("Please choose one of the allocated options.");
                            continue;
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Please enter a whole number from 1 to 4.");
                        scanner.next();
                    }
                }

                scanner.nextLine(); //Clear newline from nextInt

                switch (drink) {
                    case 1 -> {
                        drinkName = "Tea";
                        drinkPrice = 2.99;
                    }
                    case 2 -> {
                        drinkName = "Coffee";
                        drinkPrice = 3.50;
                    }
                    case 3 -> {
                        drinkName = "Lemonade";
                        drinkPrice = 2.50;
                    }
                    case 4 -> {
                        drinkName = "No drink";
                        drinkPrice = 0.0;
                    }
                }

                System.out.println("You have chosen " + drinkName + ": Please select: \n1 if you are happy with this and want to continue? \n2 if you would like to choose another option?");

                int drinkConfirmation;
                while (true) {
                    try {
                        drinkConfirmation = scanner.nextInt();

                        if (drinkConfirmation == 1) {
                            System.out.println("Great choice!");
                            drinkConfirmed = true;
                            break; //Only breaks inner loop
                        } else if (drinkConfirmation == 2) {
                            System.out.println("No problem, please choose your drink again.");
                            System.out.println();
                            break;
                        } else {
                            System.out.println("Please enter either 1 or 2.");
                        }
                    } catch (Exception e) {
                        System.out.println("Please choose 1 or 2.");
                        scanner.next();
                    }
                }
                if (drinkConfirmed) {
                    orderedItems.add(drinkName);
                    itemPrices.add(drinkPrice);
                    break; //break outer loop while (true) and continue on
                }
            }
        }
        System.out.println("\nYour full order summary: "); //Display order summary
        for (String finalOrder : orderedItems) { //Loop through ArrayList orderedItems to allow them to be displayed
            System.out.println("- " + finalOrder); //Print receipt
            System.out.println(); //Print newline
        }

        boolean loyaltyCard = askLoyaltyCard(scanner); //Initialise boolean for loyalty card and declare true/false based on method response

        double finalPrice = 0; //Initialise finalPrice

        for (double totalPrice : itemPrices) { //Loop through ArrayList itemPrices
            finalPrice += totalPrice; //Set finalPrice = to sum of itemPrices
        }

        if (loyaltyCard) { //If loyaltyCard is true THEN
            double discount = (finalPrice / 10); //Calculate 10% discount
            double finalPriceWithDiscount = finalPrice - discount; //Work out final price with discount applied
            System.out.printf("Dear %s, your total to pay with loyalty card discount is: £%.2f\nAnd you have saved: £%.2f", displayName, finalPriceWithDiscount, discount);
            //Display final cost + discount
        } else {
            System.out.printf("Dear %s, your total to pay with no discount is: £%.2f", displayName, finalPrice);
            //Display final cost
        }
    }

    /**
     * * Helper methods defined below
     **/

    //Capitalise first letter of name and lowercase the rest
    private static String capitalise(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    //Get display name
    private static String getDisplayName(String title, String firstName, String surName) {
        String fullName = capitalise(firstName) + " " + capitalise(surName);
        if (title == null || title.isEmpty()) {
            return fullName;
        }
        return title + " " + fullName;
    }

    //Get first name with input validation (.isEmpty)
    private static String askFirstName(Scanner scanner) {
        String firstName; //Declare outside loop so variable is accessible

        while (true) { //Retry loop
            System.out.println("Hello and welcome to our online restaurant ordering service, what is your first name?");
            firstName = scanner.nextLine().trim();

            if (firstName.isEmpty()) { //Guard to prevent empty user input
                System.out.println("Name can't be blank - Please enter your name.");
                continue; //Let user retry loop if input empty
            }
            if (!firstName.matches("[a-zA-Z ]+")) {
                System.out.println("Please use letters and spaces only.");
                continue;
            }
            return firstName; //Upon valid input exit the method and pass the name back to main
        }
    }

    //Get second name with input validation (.isEmpty)
    private static String askSurName(Scanner scanner) {
        String surName; //Declare outside loop so variable is accessible

        while (true) { //Retry loop
            System.out.println("And what is your surname?");
            surName = scanner.nextLine().trim();

            if (surName.isEmpty()) { //Guard to prevent empty user input
                System.out.println("Surname can't be blank - Please enter your name.");
                continue; //Let user retry loop if input empty
            }
            if (!surName.matches("[a-zA-Z ]+")) {
                System.out.println("Please use letters and spaces only.");
                continue;
            }
            return surName; //Upon valid input exit the method and pass the name back to main
        }
    }

    //Ask user for their preferred title (can be empty if they do not wish to use one)
    private static String askTitle(Scanner scanner) {
        String title;
        while (true) {
            System.out.println("And what is your title? Please choose from Mr, Mrs, Miss, or Other.");
            title = scanner.nextLine().trim();

            if (title.isEmpty()) {
                System.out.println("Please choose Mr, Mrs, Miss, or Other.");
                continue;
            }
            if (!title.matches("[a-zA-Z ]+")) {
                System.out.println("Please use letters and spaces only.");
                continue;
            }
            if (!title.equals("Mr") && !title.equals("Mrs") && !title.equals("Miss") && !title.equals("Other")) {
                System.out.println("Please choose Mr, Mrs, Miss, or Other.");
                continue;
            }
            break;
        }

        return switch (title) {
            case "Mr", "Mrs", "Miss" -> title;
            case "Other" -> {
                System.out.println("Please enter your preferred title, if you don't want one, leave this blank.");
                String customTitle = scanner.nextLine().trim();
                yield customTitle;
            }
            default -> { //Switch expression must have a default option even if it's unreachable with your guards
                System.out.println("Please choose one of the aforementioned options.t");
                yield "";
            }
        };
    }

    //Method with loop to allow user to make multiple orders
    private static int askOrderCounter(Scanner scanner) {
        int orders; //Declare outside again

        while (true) { //Retry loop
            try { //Loop allowing the user to make multiple (from 1 to 5 orders) in case they are ordering for their family or friends
                System.out.println("How many orders are you placing today?");
                orders = scanner.nextInt();

                if (orders < 1 || orders > 5) { //Guard to make min orders 1 and max orders 5
                    System.out.println("Sorry we can't fulfill your request, there needs to be a minimum of 1 order and maximum of 5.");
                    continue;
                }
                System.out.println("Perfect, you'd like to make " + orders + " orders.");
                return orders;
            } catch (Exception e) { //Catch invalid input
                System.out.println("Please enter a whole number between from 1 to 5.");
                scanner.next(); //Clear bad token
            } //Loop before
        }
    }

    //Method to ask user if they have a loyalty card and save the answer to be used in discount calculation
    private static boolean askLoyaltyCard(Scanner scanner) {
        while (true) { //Retry loop
            try {
                System.out.println("Do you have a loyalty card with us? \n1 = Yes \n2 = No"); //Ask user if they have a loyalt card
                int loyaltyChoice = scanner.nextInt();

                if (loyaltyChoice == 1) {
                    System.out.println("Excellent, you'll have a 10% discount applied to your final price, thanks for sticking with us!");
                    return true; //If user chooses 1, set loyaltyCard as true
                } else if (loyaltyChoice == 2) {
                    System.out.println("No worries, you'll be charged full price for your order.");
                    return false; //If user chooses 2, set loyaltyCard as false
                } else {
                    System.out.println("Please choose either 1 or 2.");
                }
            } catch (Exception e) {
                System.out.println("Please choose 1 or 2.");
                scanner.next();
            }
        }
    }

    //Array to hold starter items
    private static void printStarterArray() {
        String[] starterMenu = {"Garlic Bread - £4.50", "Lentil Soup - £4.99", "Prawn Cocktail - £9.99"};
        for (String starterItems : starterMenu) {
            System.out.println(starterItems);
        }
    }

    //Array to hold main items
    private static void printMainArray() {
        String[] mainMenu = {"Fish and Chips - £12.50", "Vegetarian Curry - £9.99", "Cottage Pie - £11.50"};
        for (String mainItems : mainMenu) {
            System.out.println(mainItems);
        }
    }

    //Array to hold dessert items
    private static void printDessertArray() {
        String[] dessertMenu = {"Trio of Ice Creams - £4.50", "Apple Pie - £5.20", "Cheesecake - £6.50"};
        for (String dessertItems : dessertMenu) {
            System.out.println(dessertItems);
        }
    }

    //Array to hold drink items
    private static void printDrinkArray() {
        String[] drinkMenu = {"Tea - £2.99", "Coffee - £3.50", "Lemonade - £2.50"};
        for (String drinkItems : drinkMenu) {
            System.out.println(drinkItems);
        }
    }
}