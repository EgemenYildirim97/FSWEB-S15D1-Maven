package org.example.models;

import java.util.*;

public class Grocery {
    public static List<String> groceryList = new ArrayList<>();
    public static void startGrocery(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nLütfen bir işlem seçin:");
            System.out.println("0: Uygulamayı durdur");
            System.out.println("1: Ürün ekle");
            System.out.println("2: Ürün çıkar");
            System.out.print("Seçiminiz: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Satır sonu karakterini tüket
            switch (choice) {
                case 0:
                    System.out.println("Uygulama durduruluyor...");
                    break;
                case 1:
                    System.out.print("Eklenmesini istediğiniz elemanları giriniz (virgülle ayırarak birden fazla girebilirsiniz): ");
                    String addInput = scanner.nextLine();
                    addItems(addInput);
                    break;
                case 2:
                    System.out.print("Çıkarılmasını istediğiniz elemanları giriniz (virgülle ayırarak birden fazla girebilirsiniz): ");
                    String removeInput = scanner.nextLine();
                    removeItems(removeInput);
                    break;
                default:
                    System.out.println("Geçersiz seçim. Lütfen 0, 1 veya 2 girin.");
                    break;
            }
            printSorted();

        }while (choice != 0);
        scanner.close();
    }
    public static void addItems(String input){
        String[] itemsToAdd = input.split(",");
        for(String item : itemsToAdd){
            String trimmedItem = item.trim();
            if(!trimmedItem.isEmpty()&&!checkItemIsInList(trimmedItem)){
                groceryList.add(trimmedItem);
            }else if (checkItemIsInList(trimmedItem)){
                System.out.println("'"+trimmedItem+"' zaten listede mevcut.");
            }
        }
        sortAndRemoveDuplicates();
    }
    public static void removeItems(String input){
        String[] itemsToRemove = input.split(",");
        for(String item: itemsToRemove){
            String trimmedItem = item.trim();
            if(checkItemIsInList(trimmedItem)){
                groceryList.remove(trimmedItem);
                System.out.println("'"+trimmedItem+"' listeden çıkarıldı.");
            }else {
                System.out.println("'"+trimmedItem+"' listede bulunamadı.");
            }
        }
        sortAndRemoveDuplicates();
    }
    public static boolean checkItemIsInList(String product){
        return groceryList.contains(product.trim());
    }
    public static void printSorted(){
        if(groceryList.isEmpty()){
            System.out.println("Pazar listeniz boş.");
            return;
        }
        System.out.println("\nPazar listeniz:");
        Collections.sort(groceryList);
        for(String item : groceryList){
            System.out.println(item);
        }
    }
    public static void sortAndRemoveDuplicates(){
        Set<String> uniqueItems = new HashSet<>(groceryList);
        groceryList.clear();
        groceryList.addAll(uniqueItems);
        Collections.sort(groceryList);
    }

    public static void main(String[] args) {
        startGrocery();
    }
}
