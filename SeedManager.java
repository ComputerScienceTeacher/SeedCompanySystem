import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class SeedManager {
    private ArrayList<String> availableSeeds = new ArrayList<>();
    private String[] soldOutSeeds = new String[10];
    private int soldOutCount = 0;
    public static Scanner kb = new Scanner(System.in);

    public void saveData(String availableFile, String soldOutFile){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(availableFile))){
            for(String seed: availableSeeds){
                writer.write(seed);
                writer.newLine();
            }
        } catch (IOException e){
            System.out.println("Error saving available seeds: " + e);
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(soldOutFile))){
            for(int i=0; i<soldOutCount; i++){
                writer.write(soldOutSeeds[i]);
                writer.newLine();
            }
        } catch (IOException e){
            System.out.println("Error saving sold-out seeds: " + e);
        }
    }

    public void loadData(String availableFile, String soldOutFile){
        try(BufferedReader reader = new BufferedReader(new FileReader(availableFile))){
            String line;
            while((line=reader.readLine()) != null){
                availableSeeds.add(line);
            }
        } catch(IOException e) {
            System.out.println("Error loading available seeds: " + e);
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(soldOutFile))){
            String line;
            while((line=reader.readLine()) != null){
                soldOutSeeds[soldOutCount++] = line;
            }
        } catch(IOException e) {
            System.out.println("Error loading sold out seeds: " + e);
        }
    }


    public void addSeed(){
        System.out.print("What is the name of the seed you want to add: ");
        String seed = kb.nextLine();
        availableSeeds.add(seed);
    }

    public void sellSeed() throws IndexOutOfBoundsException {
        if(availableSeeds.isEmpty()){
            System.out.println("No seeds available to sell.");
            return;
        }

        viewSeeds();
        int seedIndex = InputValidator.getValidInteger(
            "Enter the index of the seed to sell: ", 0, availableSeeds.size() - 1
        );

        if (soldOutCount >= soldOutSeeds.length) {
            System.out.println("Sold-out array is full.");
            return;
        }

        soldOutSeeds[soldOutCount++] = availableSeeds.remove(seedIndex);
        System.out.println(soldOutSeeds[soldOutCount-1] + " seed sold successfully");
    }

    public void viewSeeds() {
        System.out.println("Available Seeds: " + availableSeeds);
        System.out.print("Sold-Out Seeds: ");
        for (int i = 0; i < soldOutCount; i++) {
            System.out.print(soldOutSeeds[i] + " ");
        }
        System.out.println();
    }
}