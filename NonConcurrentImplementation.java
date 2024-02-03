import java.util.Random;

public class NonConcurrentImplementation {

    public static void nonConcurrentComputation() {

        int maxPancakesUserCanEat = 5;
        int maxPancakesShopkeeperCanMake = 12;
        int totalNumberOfUsers = 3;
        int totalSeconds = 30;

        Random random = new Random();

        int totalPancakesProducedByShopkeeper = 0;
        int[] pancakesConsumedByUsers = new int[totalNumberOfUsers];

        int startTimeSeconds = 0;
        int endTimeSeconds = 0;

        while (endTimeSeconds < totalSeconds) {
            startTimeSeconds = endTimeSeconds;
            endTimeSeconds += 30;

            // Shopkeeper makes pancakes
            int numberOfPancakesMadeByShopkeeper = random.nextInt(maxPancakesShopkeeperCanMake + 1);
            totalPancakesProducedByShopkeeper += numberOfPancakesMadeByShopkeeper;

            // Users decide the number of pancakes to eat
            for (int i = 0; i < totalNumberOfUsers; i++) {
                int numberOfPancakesRequestedByUser = random.nextInt(maxPancakesUserCanEat + 1);
                pancakesConsumedByUsers[i] += Math.min(numberOfPancakesRequestedByUser, maxPancakesUserCanEat);
            }

            // Check if shopkeeper can meet the needs of all users
            int totalRequestedPancakesByUsers = pancakesConsumedByUsers[0] + pancakesConsumedByUsers[1] + pancakesConsumedByUsers[2];
            int unmetPancakeRequests = Math.max(0, totalRequestedPancakesByUsers - totalPancakesProducedByShopkeeper);

            // Display results
            System.out.println("Time: " + startTimeSeconds + "s - " + endTimeSeconds + "s");
            System.out.println("Shopkeeper made " + numberOfPancakesMadeByShopkeeper + " pancakes");
            System.out.println("Users ate " + pancakesConsumedByUsers[0] + ", " + pancakesConsumedByUsers[1] + ", " + pancakesConsumedByUsers[2] + " pancakes");
            System.out.println("Shopkeeper met the needs of all users: " + (unmetPancakeRequests == 0));
            System.out.println("Pancakes wasted: " + Math.max(0, totalPancakesProducedByShopkeeper - totalRequestedPancakesByUsers));
            System.out.println("Unmet pancake orders: " + unmetPancakeRequests);
            System.out.println("-------------------------------------");
        }
    }
}
