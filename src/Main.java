import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Cell> cells = new ArrayList<>();
        ArrayList<Integer> cellsWithEmptyValues = new ArrayList<>();
        int emptyCell = 0;
        try {
            File myObj = new File("src/test11.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] arrOfLine = line.split(" ");
                Integer value;
                if (arrOfLine[0].equals("VALUE") && !arrOfLine[1].startsWith("$")) {
                    value = Integer.parseInt(arrOfLine[1]);
                } else if (arrOfLine[0].equals("ADD") && !arrOfLine[1].startsWith("$") && !arrOfLine[2].startsWith("$")) {
                    value = Integer.parseInt(arrOfLine[1]) + Integer.parseInt(arrOfLine[2]);
                } else if (arrOfLine[0].equals("SUB") && !arrOfLine[1].startsWith("$") && !arrOfLine[2].startsWith("$")) {
                    value = Integer.parseInt(arrOfLine[1]) - Integer.parseInt(arrOfLine[2]);
                } else if (arrOfLine[0].equals("MULT") && !arrOfLine[1].startsWith("$") && !arrOfLine[2].startsWith("$")) {
                    value = Integer.parseInt(arrOfLine[1]) * Integer.parseInt(arrOfLine[2]);
                } else {
                    value = null;
                    cellsWithEmptyValues.add(emptyCell);
                }

                Cell cell = new Cell(arrOfLine[0], arrOfLine[1], arrOfLine[2], value);
                cells.add(cell);
                emptyCell++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        while (!cellsWithEmptyValues.isEmpty()) {
            ArrayList<Integer> cellsWithFilledValues = new ArrayList<>();
            for (Integer emptyValuedCell : cellsWithEmptyValues) {
                Cell cell = cells.get(emptyValuedCell);

                int depValue1 = 0;
                int depValue2 = 0;
                boolean willBeRemoved = true;

                if (cell.getArg1().startsWith("$")) {
                    int num1 = Integer.parseInt(cell.getArg1().replace("$", ""));
                    if (cells.get(num1).getValue() != null) {
                        depValue1 = cells.get(num1).getValue();
                    } else {
                        willBeRemoved = false;
                    }
                } else {
                    depValue1 = Integer.parseInt(cell.getArg1());
                }

                if (cell.getArg2().startsWith("$")) {
                    int num2 = Integer.parseInt(cell.getArg2().replace("$", ""));
                    if (cells.get(num2).getValue() != null) {
                        depValue2 = cells.get(num2).getValue();
                    } else {
                        willBeRemoved = false;
                    }
                } else if (!cell.getArg2().equals("_")) {
                    depValue2 = Integer.parseInt(cell.getArg2());
                }

                if (willBeRemoved) {
                    switch (cell.getOperation()) {
                        case "VALUE":
                            cell.setValue(depValue1);
                            break;
                        case "ADD":
                            cell.setValue(depValue1 + depValue2);
                            break;
                        case "SUB":
                            cell.setValue(depValue1 - depValue2);
                            break;
                        case "MULT":
                            cell.setValue(depValue1 * depValue2);
                            break;
                    }
                    cellsWithFilledValues.add(emptyValuedCell);
                }
            }
            cellsWithEmptyValues.removeIf(cellsWithFilledValues::contains);
        }

        for (Cell cell : cells) {
            System.out.println(cell.getValue());
        }
    }
}