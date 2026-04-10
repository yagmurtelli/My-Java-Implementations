public class main {

	 static class OperationNotSupportedException extends Exception {
	        public OperationNotSupportedException(String message) {
	            super(message);
	        }
	    }

	    public static void selectionSort(String[] arr) {
	        for (int i = 0; i < arr.length - 1; i++) {
	            int minIdx = i;
	            for (int j = i + 1; j < arr.length; j++) {
	                if (arr[j].compareTo(arr[minIdx]) < 0) {
	                    minIdx = j;
	                }
	            }
	            String temp = arr[minIdx];
	            arr[minIdx] = arr[i];
	            arr[i] = temp;
	        }
	    }

	    public static int binarySearch(String[] arr, String target) {
	        int low = 0;
	        int high = arr.length - 1;
	        while (low <= high) {
	            int mid = (low + high) / 2;
	            int cmp = arr[mid].compareTo(target);
	            if (cmp == 0) {
	                return mid;
	            } else if (cmp < 0) {
	                low = mid + 1;
	            } else {
	                high = mid - 1;
	            }
	        }
	        return -1;
	   
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            java.io.BufferedReader reader = new java.io.BufferedReader(
                new java.io.InputStreamReader(System.in)
            );

            System.out.println("Enter a comma-separated list of words:");
            String[] words = reader.readLine().split(",");

            for (int i = 0; i < words.length; i++) {
                words[i] = words[i].trim();
            }

            System.out.println("Enter operation (sort/search):");
            String operation = reader.readLine().trim().toLowerCase();

            if (!operation.equals("sort") && !operation.equals("search")) {
                throw new OperationNotSupportedException("Error: " + operation + " is not supported");
            }

            if (operation.equals("sort")) {
                selectionSort(words);
                System.out.println(java.util.Arrays.toString(words));
            } else {
                System.out.println("Enter the target word:");
                String target = reader.readLine().trim();
                selectionSort(words); // Ensure sorted
                int index = binarySearch(words, target);
                if (index >= 0) {
                    System.out.println("Item '" + target + "' found at index " + index);
                } else {
                    System.out.println("Item '" + target + "' not found");
                }
            }

        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

	}

