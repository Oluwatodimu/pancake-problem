public class PancakeShopApp {

    public static void main(String[] args) {

        if (args.length > 0) {
            String option = args[0];

            if (option.equals("1")) {
                NonConcurrentImplementation.nonConcurrentComputation();
            } else if (option.equals("2")) {
                ConcurrentImplementation.concurrentComputation();
            }

        }
    }
}
