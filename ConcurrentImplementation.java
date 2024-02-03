import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ConcurrentImplementation {

    public static void concurrentComputation() {
        int maxPancakesUserCanEat = 5;
        int maxPancakesShopkeeperCanMake = 12;
        int totalNumberOfUsers = 3;
        int totalSeconds = 30;

        Random random = new Random();

        int startTimeSeconds = 0;
        int endTimeSeconds = 0;

        while (endTimeSeconds < totalSeconds) {
            startTimeSeconds = endTimeSeconds;
            endTimeSeconds += 30;

            // Shopkeeper makes pancakes asynchronously
            CompletableFuture<Integer> shopkeeperTask = CompletableFuture.supplyAsync(() ->
                    random.nextInt(maxPancakesShopkeeperCanMake + 1));

            // Users decide the number of pancakes to eat asynchronously
            CompletableFuture<Integer>[] numberOfPancakesToEat = new CompletableFuture[totalNumberOfUsers];

            for (int i = 0; i < totalNumberOfUsers; i++) {
                numberOfPancakesToEat[i] = CompletableFuture.supplyAsync(() ->
                                random.nextInt(maxPancakesUserCanEat + 1))
                        .thenApply(requestedPancakesByUser -> Math.min(requestedPancakesByUser, maxPancakesUserCanEat));
            }

            // Combine shopkeeper and user tasks
            CompletableFuture<Void> allTasks = CompletableFuture.allOf(numberOfPancakesToEat);
            allTasks = allTasks.thenCombine(shopkeeperTask, (unused, unused2) -> null);

            try {

                allTasks.get(); // Wait for completion of all tasks

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            // Retrieve results from tasks
            int shopkeeperProducedPancakes = shopkeeperTask.join();

            // Users eat pancakes
            int[] pancakesConsumedByUsers = new int[totalNumberOfUsers];

            for (int i = 0; i < totalNumberOfUsers; i++) {
                try {

                    pancakesConsumedByUsers[i] = numberOfPancakesToEat[i].get();

                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            // Check if shopkeeper can meet the needs of all users
            int totalRequestedPancakesByUsers = pancakesConsumedByUsers[0] + pancakesConsumedByUsers[1] + pancakesConsumedByUsers[2];
            int unmetPancakeRequests = Math.max(0, totalRequestedPancakesByUsers - shopkeeperProducedPancakes);

            // Display results
            System.out.println("Time: " + startTimeSeconds + "s - " + endTimeSeconds + "s");
            System.out.println("Shopkeeper made " + shopkeeperProducedPancakes + " pancakes");
            System.out.println("Users ate " + pancakesConsumedByUsers[0] + ", " + pancakesConsumedByUsers[1] + ", " + pancakesConsumedByUsers[2] + " pancakes");
            System.out.println("Shopkeeper met the needs of all users: " + (unmetPancakeRequests == 0));
            System.out.println("Pancakes wasted: " + Math.max(0, shopkeeperProducedPancakes - totalRequestedPancakesByUsers));
            System.out.println("Unmet pancake orders: " + unmetPancakeRequests);
            System.out.println("-------------------------------------");
        }
    }
}
